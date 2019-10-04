package com.qiandao8.qiandao8.service.impl;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.mapper.ActivityMapper;
import com.qiandao8.qiandao8.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bert Q
 * ClassName : ActivityServiceImpl
 * Description TODO
 */
@Service
public class ActivityServiceImpl implements IActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ServerResponse createActivity(Activity activity) {
        System.out.println(activity);

        return ServerResponse.createBySuccess(activity);
    }
}
