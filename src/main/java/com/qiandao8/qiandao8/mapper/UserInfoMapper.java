package com.qiandao8.qiandao8.mapper;

import com.qiandao8.qiandao8.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    UserInfo getUserInfoByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updateUserInfo(UserInfo userInfo);

    int getUserInfoByUnameNnameAndPhone(@Param("username") String username,
                                        @Param("nickName") String nickName,
                                        @Param("phoneNumber") String phoneNumber);

    int updatePasswordByUsername(@Param("username") String username, @Param("password") String newPassword);

    void updateLastLoginTimeByPK(Long PK);
}