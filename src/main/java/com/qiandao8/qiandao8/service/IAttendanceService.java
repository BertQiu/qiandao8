package com.qiandao8.qiandao8.service;

import com.qiandao8.qiandao8.common.ServerResponse;

/**
 * @author Bert Q
 * ClassName : IAttendanceService
 * Description TODO
 */
public interface IAttendanceService {

    ServerResponse getCheckInUrl(Long aid);

    ServerResponse getAttendAccess(Long aid, String token);

    ServerResponse attendActivity(String basicSelcInfo, String listSelcInfo,String ipAddr);
}
