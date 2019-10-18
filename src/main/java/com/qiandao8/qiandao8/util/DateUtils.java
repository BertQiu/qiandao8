package com.qiandao8.qiandao8.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Bert Q
 * ClassName : DateUtils
 * Description 日期格式化相关类
 */
public class DateUtils {
    private DateUtils() {
        throw new RuntimeException();
    }

    public static final String BASIC_DATE_FORMAT = "yyyy年MM月dd日";

    public static final String BASIC_DATETIME_FORMAT = "yyyy年MM月dd日 HH:mm:ss";

    public static String dateFormat(Date date) {
        return new SimpleDateFormat(BASIC_DATE_FORMAT).format(date);
    }

    public static String dateFormat(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
}
