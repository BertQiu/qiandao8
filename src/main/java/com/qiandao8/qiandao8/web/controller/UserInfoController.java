package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.annotation.RequireLogin;
import com.qiandao8.qiandao8.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Bert Q
 * ClassName : UserInfoController
 * Description 用户信息处理相关
 */
@Controller
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "updateUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    @RequireLogin
    public ServerResponse updateUserInfo(String nickName, String email, String phoneNumber) {
        return userInfoService.updateUserInfo(nickName, email, phoneNumber);
    }

    @RequestMapping(value = "forgetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetPassword(String username, String nickName, String phoneNumber) {
        return userInfoService.forgetPassword(username, nickName, phoneNumber);
    }

    @RequestMapping(value = "changePassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse changePassword(String newPassword) {
        return userInfoService.changePassword(newPassword);
    }

    @RequestMapping(value = "getCurrentUser.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCurrentUser() {
        return userInfoService.getCurrentUser();
    }

}
