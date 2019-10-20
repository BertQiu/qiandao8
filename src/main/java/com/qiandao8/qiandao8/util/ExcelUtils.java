package com.qiandao8.qiandao8.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.domain.Attendance;
import com.qiandao8.qiandao8.domain.BasicComponent;
import com.qiandao8.qiandao8.domain.ListComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bert Q , GG(记得自己改）
 * ClassName : ExcelUtils
 * Description Excel工具类
 */
@Component
public class ExcelUtils {

    private static ObjectMapper jsonParser;

    public static String EXCEL_FILE_FOLDER_ROOT;
    public static String EXCEL_FILE_FOLDER_RULES;
    public static String EXCEL_FILE_FOLDER_EXAMPLE;
    public static String EXCEL_FILE_FOLDER_ATTENDANCE;

    @Autowired
    public void setJsonParser(ObjectMapper jsonParser) {
        ExcelUtils.jsonParser = jsonParser;
    }

    @Value("${Excel.fileFolder.root}")
    public void setExcelFileFolderRoot(String excelFileFolderRoot) {
        EXCEL_FILE_FOLDER_ROOT = excelFileFolderRoot;
    }

    @Value("${Excel.fileFolder.rules}")
    public  void setExcelFileFolderRules(String excelFileFolderRules) {
        EXCEL_FILE_FOLDER_RULES = excelFileFolderRules;
    }

    @Value("${Excel.fileFolder.example}")
    public  void setExcelFileFolderExample(String excelFileFolderExample) {
        EXCEL_FILE_FOLDER_EXAMPLE = excelFileFolderExample;
    }

    @Value("${Excel.fileFolder.attendance}")
    public  void setExcelFileFolderAttendance(String excelFileFolderAttendance) {
        EXCEL_FILE_FOLDER_ATTENDANCE = excelFileFolderAttendance;
    }

    /**
     * 解析两个参数，得到对应的值，在指定目录下生成Excel表格
     * 指定目录：/excels/attendance/${aid.hashcode}.xls
     * @param activity 活动实体类
     * @param attendances 签到信息集合
     * @return 文件所在的相对路径
     */
    public static String createExcelFile(Activity activity, List<Attendance> attendances) throws Exception {
       /*
        * excel 标题
        */
        String activityName = activity.getActivityName();
        String startTime = DateUtils.dateFormat(activity.getStartTime());
        String endTime = DateUtils.dateFormat(activity.getEndTime());

        // ------------- Design By GG --------------
        for (Attendance attendance : attendances) {
            // ---------- 得到了所有的基本签到信息
            List<BasicComponent> bcs = jsonParser.readValue(attendance.getBasicSelcInfo(),
                    jsonParser.getTypeFactory().constructParametricType(List.class, BasicComponent.class));
            for (BasicComponent bc : bcs) {
                /* ###############程序编写要求####################
                 *   自己去得到他的title，content
                 *   title只要得到一次，还有你要判断为空的情况，（即：如果没有选择文本组件
                 *   |--姓名--|--学号--|  title只需要从第一个组件（bc对象）中获取，
                 *   |  张三  |  0012  |  第一个组件的的content
                 *   |  李四  |  0013  |  第二个组件的的content
                 *
                 *   ...etc
                 */
            }
            // ---------- 得到了所有的下拉列表签到信息
            List<ListComponent> lss = jsonParser.readValue(attendance.getBasicSelcInfo(),
                    jsonParser.getTypeFactory().constructParametricType(List.class, ListComponent.class));
            for (ListComponent ls : lss) {
                /* ###############程序编写要求####################
                 *   自己去得到他的title，selectedOption
                 *   title只要得到一次，还有你要判断为空的情况，（即：如果没有选择下拉组件
                 *   |--姓名--|--学号--|  title只需要从第一个组件（bc对象）中获取，
                 *   |  张三  |  0012  |  第一个组件的的selectedOption
                 *   |  李四  |  0013  |  第二个组件的的selectedOption
                 *
                 *   ...etc
                 */
            }
            // 最后一列组件 要一个签到信息，
            String checkInTime = DateUtils.dateFormat(attendance.getCheckInTime());


            // -------------      END       -----------
        }
        // 返回一个地址
        return null;
    }

    /**
     * 解析用户上传的excel文件
     * @return
     */
    public String[] parseSignInRules(String excelPath) {
        String[] ret = new String[3];
        /* 要求：
         * 给你一个excelPath，读取到excel
         * 将他里面的信息解析出来
         * 将标题封装到 String[0]
         * 将文本组件(basicComponent)解析出来，转换成JsonString，将String封装到String[1]
         * 将下拉组件(basicComponent)解析出来，转换成JsonString，将String封装到String[2]
         *
         * ↓↓ 将对象转换为jsonString 的方法 ↓↓
         * String string = jsonParser.writeValueAsString(需要被转换的对象);
         *
         */

        // ------------- Design By GG --------------






        // -------------      END     --------------
        return ret;
    }

}
