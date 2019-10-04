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
 * ClassName : LoginController
 * Description TODO
 */
@Controller
public class LoginController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "login.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password) {
        return userInfoService.login(username,password);
    }

    @RequestMapping(value = "logout.do" ,method = RequestMethod.GET)
    @ResponseBody
    @RequireLogin
    public ServerResponse logout(String username, String password) {
        return userInfoService.logout();
    }
}
