package com.qiandao8.qiandao8.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

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
        System.out.println(src+SALT);
        return enCrypt(src + SALT);
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

//    private static String converMD5(String instr) {
//        char[] a = instr.toCharArray();
//        for (int i = 0; i < a.length; i++) {
//            a[i] = (char) (a[i] ^ 't');
//        }
//        return new String(a);
//    }

}
