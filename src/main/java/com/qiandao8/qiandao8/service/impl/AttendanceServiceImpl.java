package com.qiandao8.qiandao8.service.impl;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.SessionContext;
import com.qiandao8.qiandao8.common.TokenCache;
import com.qiandao8.qiandao8.domain.Attendance;
import com.qiandao8.qiandao8.mapper.ActivityMapper;
import com.qiandao8.qiandao8.mapper.AttendanceMapper;
import com.qiandao8.qiandao8.service.IAttendanceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author Bert Q
 * ClassName : AttendanceServiceImpl
 * Description TODO
 */
@Service
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ServerResponse getCheckInUrl(Long aid) {
        // 先检查库中是否有这个活动且在活动期内
        if (activityMapper.getOngoingActivity(aid) < 1) {
            return ServerResponse.createByErrorMessage("未查询到对应活动");
        }
        // 缓存中存放 uuid：活动id 键值对作为token
        String token = UUID.randomUUID().toString();
        TokenCache.setKey(token,aid.toString());
        // 返回一份uuidToken给前端
        return ServerResponse.createBySuccess(token);
    }

    @Override
    public ServerResponse getAttendAccess(Long aid, String token) {
        // 判断传来的token是否在缓存中能查询到对应的活动
        if (StringUtils.equals(TokenCache.getValue(token), aid.toString())) {
            // 给用户存一个session表示他可以对哪个活动进行下一步签到操作了
            SessionContext.putEnableAttendActivityPermission(aid);
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("请重试");
    }

    @Override
    @Transactional
    public ServerResponse attendActivity(String basicSelcInfo, String listSelcInfo,String ipAddr) {
        Long aid = SessionContext.getEnableAttendActivityPermission();
        if (aid == null) {
            return ServerResponse.createByErrorMessage("没有权限");
        }
        Attendance attendance = new Attendance();
        attendance.setEventId(aid);
        attendance.setBasicSelcInfo(basicSelcInfo);
        attendance.setListSelcInfo(listSelcInfo);
        // TODO 二期再完成迟到判断
        attendance.setStatus(Attendance.STATES_NORMAL);
        attendance.setIpAddr(ipAddr);
        attendance.setCheckInTime(new Date());

        if (attendanceMapper.insert(attendance) == 0) {
            return ServerResponse.createByErrorMessage("插入失败");
        }

        // 对原项目签到人数进行更新
        activityMapper.increaseParticipantsNumByPK(aid);


        SessionContext.removeEnableAttendActivityPermission();

        return ServerResponse.createBySuccessMessage("签到成功！");
    }
}
