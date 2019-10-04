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


}
