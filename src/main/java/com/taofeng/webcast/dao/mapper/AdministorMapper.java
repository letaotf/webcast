package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.AdministorDO;

public interface AdministorMapper {
    int deleteByPrimaryKey(Long administorId);

    int insert(AdministorDO record);

    long insertSelective(AdministorDO record);

    AdministorDO selectByPrimaryKey(Long administorId);

    int updateByPrimaryKeySelective(AdministorDO record);

    int updateByPrimaryKey(AdministorDO record);
}