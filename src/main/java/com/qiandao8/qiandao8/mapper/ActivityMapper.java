package com.qiandao8.qiandao8.mapper;

import com.qiandao8.qiandao8.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {

    int insert(Activity record);

    Activity selectByPrimaryKey(Long id);

    List<Activity> selectAll();

    int updateByPrimaryKey(Activity record);

    int updateParticipantsNumByPK(@Param("id") Long id, @Param("participantsNum") Integer participantsNum);

    int getParticipantsNum(Long id);
}