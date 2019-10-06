package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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


    @RequestMapping(value = "getCheckInUrl.do" ,method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCheckInUrl(Long aid) {
        return attendanceService.getCheckInUrl(aid);
    }

    @RequestMapping(value = "getAttendAccess.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getAttendAccess(Long aid, String token) {
        return attendanceService.getAttendAccess(aid, token);
    }

    @RequestMapping(value = "attendActivity.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse attendActivity(String basicSelcInfo, String listSelcInfo, HttpServletRequest request) {
        return attendanceService.attendActivity(basicSelcInfo,listSelcInfo,request.getRemoteAddr());
    }
}
