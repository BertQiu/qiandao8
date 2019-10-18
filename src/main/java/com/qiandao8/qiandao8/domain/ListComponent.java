package com.qiandao8.qiandao8.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Bert Q
 * 下拉组件类
 */
@Getter
@NoArgsConstructor
public class ListComponent {
    /**
     * 标题
     */
    private String title;
    /**
     * 被选择的哪项
     */
    private String selectedOption;
    /**
     * 下拉聊表选项
     */
    private List<String> options ;

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

    public ListComponent(String title, List<String> options) {
        this.title = title;
        this.options = options;
    }
}
