package com.qiandao8.qiandao8.service.impl;

import com.qiandao8.qiandao8.common.Const;
import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.domain.UserInfo;
import com.qiandao8.qiandao8.mapper.UserInfoMapper;
import com.qiandao8.qiandao8.service.IUserInfoService;
import com.qiandao8.qiandao8.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * @author Bert Q
 * ClassName : UserServiceImpl
 * Description TODO
 */
@Service
public class UserServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HttpSession httpSession;

    @Override
    @Transactional
    public ServerResponse register(UserInfo userInfo) {
        // 对userInfo做一些过滤和校验
        userInfo.setId(null);
        // 用户名，密码，昵称的非空检查
        if (StringUtils.isAnyBlank(userInfo.getUsername(), userInfo.getPassword(), userInfo.getNickName())) {
            return ServerResponse.createByErrorMessage("注册失败！请检查必填内容是否正确填写！");
        }
        // 将密码改为MD5加密
        userInfo.setPassword(MD5Utils.getEncodingMD5(userInfo.getPassword()));
        try {
            if (userInfoMapper.insert(userInfo)==1) {
                return ServerResponse.createBySuccessMessage("注册成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("注册失败！用户名已存在！");
        }
        return ServerResponse.createByErrorMessage("注册失败！请联系管理员！");
    }

    @Override
    public ServerResponse login(String username, String password) {
        UserInfo currentUser = userInfoMapper.getUserInfoByUsernameAndPassword(username, MD5Utils.getEncodingMD5(password));
        // 查询不到这个用户
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("登录失败，请检查用户名或密码！");
        }
        //将当前用户对象存放到session中
        httpSession.setAttribute(Const.CURRENT_USER.name(),currentUser);
        //更新最后一次登录的时间
        userInfoMapper.updateLastLoginTimeByPK(currentUser.getId());
        return ServerResponse.createBySuccessMessage("登录成功！");
    }

    @Override
    @Transactional
    public ServerResponse updateUserInfo(String nickName, String email, String phoneNumber) {
        UserInfo currentUser = (UserInfo) httpSession.getAttribute(Const.CURRENT_USER.name());
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("请先登录！");
        }
        currentUser.setNickName(nickName);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);

        try{
            if (userInfoMapper.updateUserInfo(currentUser)==1) {
                return ServerResponse.createBySuccess("修改成功！");
            }
        }catch(Exception e){
        e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess("修改了0条数据！");
    }

    @Override
    public ServerResponse forgetPassword(String username, String nickName, String phoneNumber) {
        int count = userInfoMapper.getUserInfoByUnameNnameAndPhone(username, nickName, phoneNumber);
        // 存在用户
        if (count > 0) {
            httpSession.setAttribute(Const.RESET_PASSWORD_ACCOUNT.name(),username);
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("未查询到对应用户");
    }

    @Override
    @Transactional
    public ServerResponse changePassword(String newPassword) {
        String username = (String) httpSession.getAttribute(Const.RESET_PASSWORD_ACCOUNT.name());
        if (username == null) {
            return ServerResponse.createByErrorMessage("更新密码失败！");
        }
        int count = userInfoMapper.updatePasswordByUsername(username, MD5Utils.getEncodingMD5(newPassword));
        if (count > 0) {
            httpSession.removeAttribute(Const.RESET_PASSWORD_ACCOUNT.name());
            return ServerResponse.createBySuccess("更新密码成功！");
        }
        return ServerResponse.createByErrorMessage("更新密码失败！");
    }


}
