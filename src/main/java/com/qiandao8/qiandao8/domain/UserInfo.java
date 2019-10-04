package com.qiandao8.qiandao8.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserInfo {
    private Long id;
    private String username;
    private String nickName;
    private String password;
    private String email;
    private String phoneNumber;
    private Date createTime;
    private Date updateTime;
    private Date lastLoginTime;
}