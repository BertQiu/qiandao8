package com.qiandao8.qiandao8.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Bert Q
 * 登录用户实体类
 */
@Getter
@Setter
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