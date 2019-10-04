package com.qiandao8.qiandao8.domain;

import lombok.*;

/**
 * @author Bert Q
 * ClassName : TestComponent
 * Description TODO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasicComponent {

    private String title;
    private String remarks;
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
