package com.qiandao8.qiandao8.qo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bert Q
 * ClassName : AbstratQueryObject
 * Description TODO
 */
@Getter
@Setter
public abstract class AbstractQueryObject {
    private Integer currentPage;
    private Integer pageSize;
}
