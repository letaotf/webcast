package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.UserDO;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserDO record);

    Long insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}