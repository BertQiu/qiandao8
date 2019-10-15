package com.qiandao8.qiandao8.mapper;

import com.qiandao8.qiandao8.domain.Activity;
import com.qiandao8.qiandao8.qo.ActivityQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ActivityMapper {

    int insert(Activity record);

    Activity selectByPrimaryKey(Long id);

    Activity selectEnableActivityByPk(Long id);

    List<Activity> selectAll();

    int updateByPrimaryKey(Activity record);

    int increaseParticipantsNumByPK(Long id);

    int updateParticipantsNumByPK(@Param("id") Long id, @Param("participantsNum") Integer participantsNum);

    Integer getParticipantsNum(Long id);

    List<Activity> listActivitiesByQueryObj(ActivityQueryObject queryObject);

    List<Long> listFinishedActivities(Date endTime);

    int updateActivitiesStatus(@Param("id") Long id,@Param("status") Integer status);

    int getOngoingActivity(Long id);

    int deleteActivity(@Param("id")Long id,@Param("originatorId")Long originatorId);

    int checkActivityCreatedByOid(@Param("aid")Long aid,@Param("oid")Long oid);
}