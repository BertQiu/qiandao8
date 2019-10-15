package com.qiandao8.qiandao8.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Attendance {
    private Long id;
    private Long activityId;
    private Integer status;
    private String ipAddr;
    private Date checkInTime;
    private String currentLocation;
    private String verifyPassword;
    private String basicSelcInfo;
    private String listSelcInfo;

    /**
     * 表示签到状态处于正常 states
     */
    public static final int STATES_NORMAL = 0;

    /**
     * 表示迟到了
     */
    public static final int STATES_LATE = 1;
}