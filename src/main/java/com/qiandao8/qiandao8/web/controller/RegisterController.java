package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.domain.UserInfo;
import com.qiandao8.qiandao8.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Bert Q
 * ClassName : RegisterController
 * Description 注册相关
 */
@Controller
public class RegisterController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "register.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(UserInfo userInfo) {
        return userInfoService.register(userInfo);
    }
}
