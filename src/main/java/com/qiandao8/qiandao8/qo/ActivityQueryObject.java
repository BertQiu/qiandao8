package com.qiandao8.qiandao8.qo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Bert Q
 * ClassName : ActivityQueryObject
 * Description TODO
 */
@Getter
@Setter
public class ActivityQueryObject extends AbstractQueryObject {

    /**
     * 高级查询条件:活动名称
     */
    private String activityName;

    /**
     * 高级查询条件:用户的发起者id
     */
    private Long originatorId;

    /**
     * 高级查询条件:用户的发起者
     */
    private String originator;

    /**
     * 高级查询条件:活动状态
     */
    private Integer status;

    /**
     * 高级查询条件:活动起始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 高级查询条件:活动结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
