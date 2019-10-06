package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.annotation.RequireLogin;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.qo.ActivityQueryObject;
import com.qiandao8.qiandao8.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "createOneActivity.do",method = RequestMethod.POST)
    @ResponseBody
    @RequireLogin
    public ServerResponse createOneActivity(Activity activity) {
        return activityService.createActivity(activity);
    }

    @RequestMapping(value = "createRoutineActivity.do",method = RequestMethod.POST)
    @ResponseBody
    @RequireLogin
    public ServerResponse createRoutineActivity() {
        // TODO 二期再完成
        return null;
    }

    @RequestMapping(value = "getParticipantNumbers.do",method = RequestMethod.GET)
    @ResponseBody
    @RequireLogin
    public ServerResponse getParticipantNumbers(Long aid) {
        return activityService.getParticipantNumbers(aid);
    }

    @RequestMapping(value = "getActivity.do/{aid}" ,method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getActivity(@PathVariable Long aid) {
        return activityService.getActivity(aid);
    }

    @RequestMapping(value = "getActivity.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getActivity() {
        return activityService.getActivity();
    }

    @RequestMapping(value = "listAllActivities.do", method = RequestMethod.GET)
    @ResponseBody
    @RequireLogin
    public ServerResponse listAllActivities(ActivityQueryObject queryObject) {
        return activityService.listActivities(queryObject);
    }


}
