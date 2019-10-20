package com.qiandao8.qiandao8.service;

import com.qiandao8.qiandao8.common.ServerResponse;
import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.qo.ActivityQueryObject;

/**
 * @author Bert Q
 * ClassName : IActivityService
 * Description TODO
 */

public interface IActivityService {
    ServerResponse createActivity(Activity activity);

    ServerResponse getParticipantNumbers(Long id);

    ServerResponse getActivity(Long aid);

    ServerResponse getActivity();

    ServerResponse listActivities(ActivityQueryObject queryObject);

    void updateActivitiesStatus();

    ServerResponse deleteActivities(Long aid);

    ServerResponse createRoutineActivity(Activity activity,String excelFileName);
}
