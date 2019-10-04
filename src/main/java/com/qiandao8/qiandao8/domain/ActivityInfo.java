package com.qiandao8.qiandao8.domain;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Bert Q
 * ClassName : EventInfo
 * Description 用于将前台传来的特定字符串的组件转换为特定对象/json串
 */
@Getter
@ToString
public class ActivityInfo {
    @Setter
    private Long parentId;
    @Setter
    private String eventName;




}
