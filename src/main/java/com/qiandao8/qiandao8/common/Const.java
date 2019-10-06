package com.qiandao8.qiandao8.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bert Q
 * @description //封装项目所使用的常量枚举
 */
public enum Const {
    /**
     * 当前登录用户
     */
    CURRENT_USER(),

    /**
     * 需要更改用户密码的账号
     */
    RESET_PASSWORD_ACCOUNT(),

    /**
     * 最近一次生成的活动
     */
    NEAREST_ACTIVITY(),

    /**
     * 可以进行参与活动的权限
     */
    ENABLE_ATTEND_ACTIVITY_PERMISSION(),
}
