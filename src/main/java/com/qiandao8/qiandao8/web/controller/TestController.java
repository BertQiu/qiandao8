package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Bert Q
 * ClassName : TestController
 * Description TODO
 */
@Controller
public class TestController {
    @Autowired
    private  IUserInfoService userInfoService;

}
