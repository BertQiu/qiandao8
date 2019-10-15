package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.annotation.RequireLogin;
import com.qiandao8.qiandao8.qo.AttendanceQueryObject;
import com.qiandao8.qiandao8.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bert Q
 * ClassName : AttendanceController
 * Description TODO
 */
@Controller
@RequestMapping("attend")
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;


    @RequestMapping(value = "getCheckInUrl.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCheckInUrl(Long aid) {
        return attendanceService.getCheckInUrl(aid);
    }

    /**
     * 通过aid，token得到签到的权限，如果有权限，则跳转到签到页面
     * 如果没有权限，则返回错误页面
     * @param aid
     * @param token
     * @param response
     */
    @RequestMapping(value = "getAttendAccess.do", method = RequestMethod.GET)
    public void getAttendAccess(Long aid, String token,HttpServletResponse response) {
        boolean isSuccess = attendanceService.getAttendAccess(aid, token);
        try {
            if (isSuccess) {
                response.sendRedirect("/attendActivity.html");
            } else {
                response.sendRedirect("/error/disabled.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户即将签到的那个活动的全部信息
     * @return
     */
    @RequestMapping(value = "getAttendActivityInfo.do" ,method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getAttendActivityInfo() {
        return  attendanceService.getAttendActivityInfo();
    }

    @RequestMapping(value = "attendActivity.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse attendActivity(String basicSelcInfo, String listSelcInfo, HttpServletRequest request) {
        return attendanceService.attendActivity(basicSelcInfo, listSelcInfo, request.getRemoteAddr());
    }

    /**
     * 获取用户发起的某项活动的全部参与者签到信息
     */
    @RequestMapping(value = "listAttendanceInfo.do", method = RequestMethod.GET)
    @ResponseBody
    @RequireLogin
    public ServerResponse listAttendanceInfo(AttendanceQueryObject queryObject) {
        return attendanceService.listAttendanceInfo(queryObject);
    }
}
