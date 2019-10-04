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
}
