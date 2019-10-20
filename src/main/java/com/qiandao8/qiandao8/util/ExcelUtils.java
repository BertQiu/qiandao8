package com.qiandao8.qiandao8.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.domain.Attendance;
import com.qiandao8.qiandao8.domain.BasicComponent;
import com.qiandao8.qiandao8.domain.ListComponent;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static String PROJECT_ROOT = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static String EXCEL_FILE_FOLDER_ROOT;
    public static String EXCEL_FILE_FOLDER_RULES;
    public static String EXCEL_FILE_FOLDER_EXAMPLE;
    public static String EXCEL_FILE_FOLDER_ATTENDANCE;

    public static final String DEFAULT_SURFIX = ".xlsx";

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
     *
     * @param activity    活动实体类
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
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(activityName);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(activityName);
        cell.setCellStyle(setCellStyle(workbook));
        // ---------- 得到了所有的基本签到信息
        int rowNumber = 1;
        int componentNumber = 0;
        for (Attendance attendance : attendances) {
            List<BasicComponent> bcs = jsonParser.readValue(attendance.getBasicSelcInfo(),
                    jsonParser.getTypeFactory().constructParametricType(List.class, BasicComponent.class));
            rowNumber++;
            int cellNumber = 0;
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
                fillAttendanceInfo(sheet, rowNumber, cellNumber, bc.getTitle(), bc.getContent());
                cellNumber++;
                componentNumber++;
            }
            // ---------- 得到了所有的下拉列表签到信息
            List<ListComponent> lss = jsonParser.readValue(attendance.getListSelcInfo(),
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
                fillAttendanceInfo(sheet, rowNumber, cellNumber, ls.getTitle(), ls.getSelectedOption());
                cellNumber++;
                componentNumber++;
            }
            // 最后一列组件 要一个签到信息，
            String checkInTime = DateUtils.dateFormat(attendance.getCheckInTime());
            fillAttendanceInfo(sheet, rowNumber, cellNumber, "签到时间", checkInTime);
            // -------------      END       -----------
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, componentNumber / attendances.size()));
        // 判断文件夹是否存在
        File targetFilePath = new File(PROJECT_ROOT +EXCEL_FILE_FOLDER_ATTENDANCE);
        if(!targetFilePath.exists()){
            targetFilePath.mkdirs();
        }
        //
        FileOutputStream outputStream = new FileOutputStream(PROJECT_ROOT
                +EXCEL_FILE_FOLDER_ATTENDANCE
                +activityName + activity.getId()+DEFAULT_SURFIX);

        workbook.write(outputStream);
        outputStream.close();
        // 返回一个地址
        return null;
    }

    /**
     * 解析用户上传的excel文件
     *
     * @return
     */
    public static String parseSignInRules(String excelPath) {
        String ret = "";
        /* 要求：
         * 给你一个excelPath，读取到excel
         * 将他里面的信息解析出来
         * 将文本组件(basicComponent)解析出来，转换成JsonString，
         * ↓↓ 将对象转换为jsonString 的方法 ↓↓
         * String string = jsonParser.writeValueAsString(需要被转换的对象);
         *
         */

        // ------------- Design By GG --------------
        //读取地址,按需求改动
        Workbook workbook = null;
        try {
            if (excelPath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(new FileInputStream(new File(excelPath)));
            } else {
                workbook = new XSSFWorkbook(new FileInputStream(new File(excelPath)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = null;
        String[] titles = new String[row.getLastCellNum()];
        for (int i = 0; i < row.getLastCellNum(); i++) {
            titles[i] = row.getCell(i).getStringCellValue();
        }
        List<List<BasicComponent>> list = new ArrayList<>();
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            List<BasicComponent> basicComponentList = new ArrayList<>();
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                BasicComponent basicComponent = new BasicComponent();
                basicComponent.setTitle(titles[j]);
                basicComponent.setContent(cell.getStringCellValue());
                basicComponentList.add(basicComponent);
            }
            list.add(basicComponentList);
        }
        try {
            ret = jsonParser.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // -------------      END     --------------
        return ret;
    }

    /**
     * JJ
     *
     * @param sheet      所在的sheet页
     * @param rowNumber  行数
     * @param cellNumber 列数
     * @param title      组件标题
     * @param content    组件内容
     */
    private static void fillAttendanceInfo(Sheet sheet, int rowNumber, int cellNumber,
                                          String title,
                                          String content) {
        sheet.setColumnWidth(cellNumber, title.getBytes().length * 2 * 256);
        if (sheet.getColumnWidth(cellNumber) < content.getBytes().length * 2 * 256) {
            sheet.setColumnWidth(cellNumber, content.getBytes().length * 2 * 256);
        }
        Row row = null;
        if (sheet.getLastRowNum() < rowNumber) {
            row = sheet.createRow(rowNumber);
        } else {
            row = sheet.getRow(rowNumber);
        }
        Cell cell = row.createCell(cellNumber);
        cell.setCellValue(content);
        if (rowNumber != 2) {
            return;
        }
        if (sheet.getRow(1) == null) {
            row = sheet.createRow(1);
            cell = row.createCell(cellNumber);
            cell.setCellValue(title);
        } else {
            row = sheet.getRow(1);
            cell = row.createCell(cellNumber);
            cell.setCellValue(title);
        }
        return;
    }

    /**
     * JJ
     *
     * @param workbook 设置格式的单元格所在的Workbook
     * @return
     */
    private static CellStyle setCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontName("等线");
        font.setFontHeightInPoints((short) 28);
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }
}
