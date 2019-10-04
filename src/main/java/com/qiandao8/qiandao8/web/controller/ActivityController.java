package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Bert Q
 * ClassName : ActivityController
 * Description TODO
 */
@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @RequestMapping("createOneActivity.do")
    @ResponseBody
    public ServerResponse createOneActivity(Activity activity) {
        return activityService.createActivity(activity);
    }

    @RequestMapping("createRoutineActivity.do")
    @ResponseBody
    public ServerResponse createRoutineActivity() {
        return null;
    }
}
