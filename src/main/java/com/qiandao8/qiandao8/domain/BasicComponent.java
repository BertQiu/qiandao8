package com.qiandao8.qiandao8.domain;

import lombok.*;

/**
 * @author Bert Q
 * 基础组件类（文本组件）
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasicComponent {

    /**
     * 文本组件的标题
     */
    private String title;
    /**
     * 文本组件的备注（placeholder）
     */
    private String remarks;
    /**
     * 用户所填入的内容
     */
    private String content;

    public BasicComponent(String title) {
        this.title = title;
    }

    public BasicComponent(String remarks, String content) {
        this.remarks = remarks;
        this.content = content;
    }

    /**
     * 需要的字符串格式: 姓名-aaa,手机
     * 用 " , "分割每个对象
     */
    public static final String OBJECT_SEPARATOR = ",";

    /**
     * 用 " - "分割标题和备注
     */
    public static final String TITLE_REMARK_SEPARATOR = "-";

}
