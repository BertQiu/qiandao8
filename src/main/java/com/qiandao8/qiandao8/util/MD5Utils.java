package com.qiandao8.qiandao8.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Bert Q
 * @description //MD5 加盐值工具类
 * @date 9:21 2019/10/2
 */
@Component
public class MD5Utils {
    private static String SALT;

    @Value("${MD5.salt}")
    public void setSALT(String salt) {
        SALT = salt;
    }

    public static String getEncodingMD5(String src) {
//        return enCrypt(src + SALT);
        return src;//不使用加密嘿嘿嘿
    }


    private static String enCrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] s = md.digest(str.getBytes());
            String ss;
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                ss = Integer.toHexString(s[i] & 0xff);
                if (ss.length() == 1) {
                    result.append("0").append(ss);
                } else {
                    result.append(ss);
                }
            }
            return result.toString();
        } catch (Exception em) {
            em.printStackTrace();
        }
        return null;
    }
}
