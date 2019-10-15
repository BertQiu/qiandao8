package com.qiandao8.qiandao8.mapper;

import com.qiandao8.qiandao8.domain.Attendance;

import java.util.List;

public interface AttendanceMapper {

    int insert(Attendance attendance);

    Attendance selectByPrimaryKey(Long id);

    List<Attendance> selectAll();

    int updateByPrimaryKey(Attendance attendance);

    List<Attendance> listAttendanceByAid(Long aid);
}