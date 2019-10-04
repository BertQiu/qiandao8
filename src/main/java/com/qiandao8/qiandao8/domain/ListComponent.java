package com.qiandao8.qiandao8.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Bert Q
 * ClassName : ListComponent
 * Description TODO
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListComponent {
    private String title;
    private Map<String,Boolean> options ;

    /**
     * 需要的字符串格式: 性别:男,女-年龄:20,30
     * 用 " - "分割对象
     */
    public static final String OBJECT_SEPARATOR = "-";

    /**
     * 用 " : "分割标题和选项集
     */
    public static final String TITLE_OPTS_SEPARATOR = ":";

    /**
     * 用 " , "分割标题和选项集
     */
    public static final String OPTION_SEPARATOR = ",";

}
