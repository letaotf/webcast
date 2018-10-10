package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.TimeDO;

public interface TimeMapper {
    int deleteByPrimaryKey(Long timeId);

    int insert(TimeDO record);

    int insertSelective(TimeDO record);

    TimeDO selectByPrimaryKey(Long timeId);

    int updateByPrimaryKeySelective(TimeDO record);

    int updateByPrimaryKey(TimeDO record);
}