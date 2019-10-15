package com.qiandao8.qiandao8.qo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bert Q
 * ClassName : AttendanceQueryObject
 * Description TODO
 */
@Getter
@Setter
public class AttendanceQueryObject extends AbstractQueryObject {
    /**
     * 高级查询条件:活动id
     */
    private Long aid;
}
