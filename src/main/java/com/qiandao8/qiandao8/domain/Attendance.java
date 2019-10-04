package com.qiandao8.qiandao8.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Attendance {
    private Long id;
    private Long eventId;
    private Integer status;
    private String ipAddr;
    private Date checkInTime;
    private String currentLocation;
    private String verifyPassword;
    private String basicSelcInfo;
    private String listSelcInfo;


}