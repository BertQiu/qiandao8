package com.qiandao8.qiandao8.util;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Bert Q
 * ClassName : FileUtils
 * Description TODO
 */
public class FileUtils {

    public static String PROJECT_ROOT = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static final String SEPARATER = ".";

    /**
     * 如输入123.txt
     * 返回txt
     * @param fileName
     * @return
     */
    public static String getFileType(String fileName) {
        int i = -1;
         i = fileName.lastIndexOf(".");
        if (i == -1) {
            throw new RuntimeException("不是有效的文件名");
        }
        return fileName.substring(i+1);
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFilePath = new File(filePath);
        if(!targetFilePath.exists()){
            targetFilePath.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static boolean isExists(String url) {
       return new File(url).exists();
    }
}
