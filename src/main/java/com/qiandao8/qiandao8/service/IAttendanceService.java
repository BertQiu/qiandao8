package com.qiandao8.qiandao8.service;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.qo.AttendanceQueryObject;

/**
 * @author Bert Q
 * ClassName : IAttendanceService
 * Description TODO
 */
public interface IAttendanceService {

    ServerResponse getCheckInUrl(Long aid);

    boolean getAttendAccess(Long aid, String token);

    ServerResponse attendActivity(String basicSelcInfo, String listSelcInfo,String ipAddr);

    /**
     * 获取用户即将签到的那个活动的全部信息
     */
    ServerResponse getAttendActivityInfo();

    /**
     * 获取用户发起的某项活动的全部参与者签到信息
     */
    ServerResponse listAttendanceInfo(AttendanceQueryObject queryObject);
}
