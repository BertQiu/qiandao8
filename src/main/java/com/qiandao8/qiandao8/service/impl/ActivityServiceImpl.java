package com.qiandao8.qiandao8.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.common.SessionContext;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.domain.UserInfo;
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
            if (StringUtils.isBlank(activity.getActivityName())
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
        // TODO 暂时没做权限检查：因为单纯返回活动签到人数的数据，不涉及到其他敏感信息
        Integer participantsNum = activityMapper.getParticipantsNum(id);
        if (participantsNum == null) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(participantsNum);
    }

    @Override
    public ServerResponse getActivity(Long aid) {
        UserInfo currentUser = SessionContext.getCurrentUser();
        // 权限检查
        if (currentUser == null && SessionContext.getEnableAttendActivityPermission() == null) {
            return ServerResponse.createByErrorMessage("没有足够的权限");
        }
        updateActivitiesStatus();

        Activity activity = activityMapper.selectByPrimaryKey(aid);
        if (activity == null) {
            return ServerResponse.createByErrorMessage("未查询到对应活动！");
        }
        // 判断当前登录用户是否有权限查询他
        if (currentUser != null && !activity.getOriginatorId().equals(currentUser.getId())) {
            return ServerResponse.createByErrorMessage("没有足够的权限");
        }
        return ServerResponse.createBySuccess(activity);
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

        Activity activity = activityMapper.selectByPrimaryKey(nearestActivity.getId());
        if (activity == null) {
            return ServerResponse.createByErrorMessage("未查询到对应活动！");
        }
        return ServerResponse.createBySuccess(activity);
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

    @Override
    @Transactional
    public ServerResponse deleteActivities(Long aid) {
        int effectCount = activityMapper.deleteActivity(aid, SessionContext.getCurrentUser().getId());
        if (effectCount > 0) {
            return ServerResponse.createBySuccessMessage("操作成功！");
        }
        return ServerResponse.createByErrorMessage("操作失败！");
    }

}
