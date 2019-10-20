package com.qiandao8.qiandao8.service;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.domain.UserInfo;

/**
 * @author Bert Q
 * ClassName : UserInfoService
 * Description 处理用户信息相关
 */
public interface IUserInfoService {

    /**
     * 用户注册
     * @return
     */
    ServerResponse register(UserInfo userInfo);

    /**
     * 用户登录
     * @return
     */
    ServerResponse login(String username,String password,String ip);

    /**
     * 更新用户信息
     * @param nickName
     * @param email
     * @param phoneNumber
     * @return
     */
    ServerResponse updateUserInfo(String nickName,String email,String phoneNumber);

    /**
     * 忘记密码
     * 知道账号，昵称，绑定手机号码便可以找回
     * @param username
     * @param nickName
     * @param phoneNumber
     * @return
     */
    ServerResponse forgetPassword(String username, String nickName, String phoneNumber);

    /**
     * 重置密码
     * @param newPassword
     * @return
     */
    ServerResponse changePassword(String newPassword);

    ServerResponse logout();

    ServerResponse getCurrentUser();
}
