package com.qiandao8.qiandao8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.domain.Attendance;
import com.qiandao8.qiandao8.util.ExcelUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Qiandao8ApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        Activity activity = new Activity();
        activity.setStartTime(new Date());
        activity.setEndTime(new Date(2111, 2, 2));

        Attendance attendance = new Attendance();
        attendance.setBasicSelcInfo("[{\"title\":\"姓名\",\"content\":\"究极无敌暴龙兽\"},{\"title\":\"学校\",\"content\":\"\"},{\"title\":\"学号\",\"content\":\"\"},{\"title\":\"公司\",\"content\":\"\"},{\"title\":\"QQ\",\"content\":\"\"},{\"title\":\"邮箱\",\"content\":\"\"}]");
        attendance.setListSelcInfo("[{\"title\":\"41\",\"options\":[\"3123\"],\"selectedOption\":\"3123\"}]");

        Attendance attendance2 = new Attendance();
        attendance2.setBasicSelcInfo("[{\"title\":\"姓名\",\"content\":\"究123暴龙兽\"},{\"title\":\"学校\",\"content\":\"\"},{\"title\":\"学号\",\"content\":\"\"},{\"title\":\"公司\",\"content\":\"\"},{\"title\":\"QQ\",\"content\":\"\"},{\"title\":\"邮箱\",\"content\":\"\"}]");
        attendance2.setListSelcInfo("[{\"title\":\"41\",\"options\":[\"3123\"],\"selectedOption\":\"3123\"}]");

        List<Attendance> attendances = new ArrayList<>();
        attendances.add(attendance2);
        attendances.add(attendance);
        attendances.add(attendance2);

        String string = new ObjectMapper().writeValueAsString(attendances);
        System.out.println(string);

//        String excelFile = ExcelUtils.createExcelFile(activity, attendances);
//        System.out.println(excelFile);

    }

}
