package com.qiandao8.qiandao8.mapper;

import com.qiandao8.qiandao8.domain.Activity;
import java.util.List;

public interface ActivityMapper {

    int insert(Activity record);

    Activity selectByPrimaryKey(Long id);

    List<Activity> selectAll();

    int updateByPrimaryKey(Activity record);
}