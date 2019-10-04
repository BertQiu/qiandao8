package com.qiandao8.qiandao8.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.SessionContext;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.mapper.ActivityMapper;
import com.qiandao8.qiandao8.service.IActivityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Bert Q
 * ClassName : ActivityServiceImpl
 * Description TODO
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public ServerResponse createActivity(Activity activity) {
        try {
            if (StringUtils.isBlank(activity.getEventName())
                    || activity.getStartTime() == null
                    || activity.getEndTime() == null) {
                return ServerResponse.createByErrorMessage("创建活动失败!非法参数！");
            }
            activity.setId(null);
            activity.setStatus(Activity.STATES_NORMAL);
            activity.setParticipantsNums(0);
            activity.setOriginatorId(SessionContext.getCurrentUser().getId());

            activity.setBasicSelc(objectMapper.writeValueAsString(activity.getBasicComponents()));
            activity.setListSelc(objectMapper.writeValueAsString(activity.getListComponents()));

            if (activityMapper.insert(activity) > 0) {
                // 将最近一次生成的活动放到session中
                SessionContext.putNearestActivity(activity);
                return ServerResponse.createBySuccessMessage("创建活动成功！");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("创建活动失败!");
        }

        return ServerResponse.createByErrorMessage("创建活动失败!");
    }

    @Override
    public ServerResponse getParticipantNumbers(Long id) {
        return ServerResponse.createBySuccess(activityMapper.getParticipantsNum(id));
    }

    @Override
    public ServerResponse getActivity(Long aid) {
        System.out.println(aid);
        return ServerResponse.createBySuccess(activityMapper.selectByPrimaryKey(aid));
    }

    @Override
    public ServerResponse getActivity() {
        Activity nearestActivity = SessionContext.getNearestActivity();
        if (nearestActivity == null) {
            return ServerResponse.createByErrorMessage("查询失败！");
        }
        return ServerResponse.createBySuccess(activityMapper.selectByPrimaryKey(nearestActivity.getId()));
    }
}
