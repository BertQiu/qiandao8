package com.qiandao8.qiandao8.web.controller;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.annotation.RequireLogin;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.qo.ActivityQueryObject;
import com.qiandao8.qiandao8.service.IActivityService;
import com.qiandao8.qiandao8.util.ExcelUtils;
import com.qiandao8.qiandao8.util.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Bert Q
 * ClassName : ActivityController
 * Description TODO
 */
@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @RequestMapping(value = "createOneActivity.do", method = RequestMethod.POST)
    @ResponseBody
    @RequireLogin
    public ServerResponse createOneActivity(Activity activity) {
        return activityService.createActivity(activity);
    }

    @RequestMapping(value = "createRoutineActivity.do", method = RequestMethod.POST)
    @ResponseBody
    @RequireLogin
    public ServerResponse createRoutineActivity(Activity activity,@RequestParam("fileName") String fileName) {
        System.out.println(activity);
        System.out.println(fileName);
        return activityService.createRoutineActivity(activity);
    }

    @RequestMapping(value = "getParticipantNumbers.do", method = RequestMethod.GET)
    @ResponseBody
    @RequireLogin
    public ServerResponse getParticipantNumbers(Long aid) {
        return activityService.getParticipantNumbers(aid);
    }

    @RequestMapping(value = "getActivity.do/{aid}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getActivity(@PathVariable Long aid) {
        return activityService.getActivity(aid);
    }

    @RequestMapping(value = "getActivity.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getActivity() {
        return activityService.getActivity();
    }

    @RequestMapping(value = "listAllActivities.do", method = RequestMethod.GET)
    @ResponseBody
    @RequireLogin
    public ServerResponse listAllActivities(ActivityQueryObject queryObject) {
        return activityService.listActivities(queryObject);
    }

    @RequestMapping(value = "deleteActivities.do", method = RequestMethod.GET)
    @ResponseBody
    @RequireLogin
    public ServerResponse deleteActivities(Long aid) {
        return activityService.deleteActivities(aid);
    }

    @RequestMapping(value = "downloadHintExcel.do", method = RequestMethod.GET)
    @RequireLogin
    public void downloadHintExcel(HttpServletRequest request, HttpServletResponse response) {
        String folderPath = Objects.requireNonNull(ClassUtils.getDefaultClassLoader().getResource("")).getPath() + "/excels/example";
        String fileName = "example.xls";
        try (
                //jdk7新特性，可以直接写到try()括号里面，java会自动关闭
                InputStream inputStream = new FileInputStream(new File(folderPath, fileName));
                OutputStream outputStream = response.getOutputStream()
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);   // 设置文件名
            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "uploadRuleExcel.do", method = RequestMethod.POST)
    @ResponseBody
    @RequireLogin
    public ServerResponse uploadRuleExcel(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();  //获取上传文件原名
        String newFileName = UUID.randomUUID().toString()+FileUtils.SEPARATER+FileUtils.getFileType(fileName);//使用uuid生成新的文件名
        String filePath = (ClassUtils.getDefaultClassLoader().getResource("").getPath() + ExcelUtils.EXCEL_FILE_FOLDER_RULES);
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
        return ServerResponse.createBySuccess(newFileName);
    }
}
