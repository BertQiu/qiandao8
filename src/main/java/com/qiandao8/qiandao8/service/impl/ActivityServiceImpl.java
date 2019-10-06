package com.qiandao8.qiandao8.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiandao8.qiandao8.common.Const;
import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.SessionContext;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.mapper.ActivityMapper;
import com.qiandao8.qiandao8.qo.ActivityQueryObject;
import com.qiandao8.qiandao8.service.IActivityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


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
    private ObjectMapper jsonParser;

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

            if (activity.getEndTime().getTime() < activity.getStartTime().getTime()) {
                activity.setEndTime(activity.getStartTime());
            }

            activity.setBasicSelc(jsonParser.writeValueAsString(activity.getBasicComponents()));
            activity.setListSelc(jsonParser.writeValueAsString(activity.getListComponents()));

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
        // 权限检查
        if (SessionContext.getCurrentUser() == null && SessionContext.getEnableAttendActivityPermission() == null) {
            return ServerResponse.createByErrorMessage("没有足够的权限");
        }

        updateActivitiesStatus();
        return ServerResponse.createBySuccess(activityMapper.selectByPrimaryKey(aid));
    }

    @Override
    public ServerResponse getActivity() {
        // 权限检查
        if (SessionContext.getCurrentUser() == null && SessionContext.getEnableAttendActivityPermission() == null) {
            return ServerResponse.createByErrorMessage("没有足够的权限");
        }

        updateActivitiesStatus();
        Activity nearestActivity = SessionContext.getNearestActivity();
        if (nearestActivity == null) {
            return ServerResponse.createByErrorMessage("查询失败！");
        }
        return ServerResponse.createBySuccess(activityMapper.selectByPrimaryKey(nearestActivity.getId()));
    }

    @Override
    public ServerResponse listActivities(ActivityQueryObject queryObject) {
        updateActivitiesStatus();

        queryObject.setOriginatorId(SessionContext.getCurrentUser().getId());
        PageHelper.startPage(queryObject.getCurrentPage(), queryObject.getPageSize());
        PageInfo<Activity> data = new PageInfo<>(activityMapper.listActivitiesByQueryObj(queryObject));
        return ServerResponse.createBySuccess(data);
    }

    /**
     * 在每次查询之前，调用一次这个方法，更新活动的状态
     */
    @Override
    @Transactional
    public void updateActivitiesStatus() {
        List<Long> activities = activityMapper.listFinishedActivities(new Date());
        for (Long activityId : activities) {
            activityMapper.updateActivitiesStatus(activityId, Activity.STATES_FINISH);
        }
    }

}
