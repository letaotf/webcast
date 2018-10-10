package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDO;

public interface NetworkBroadcastTypeMapper {
    int deleteByPrimaryKey(Long typeId);

    int insert(NetworkBroadcastTypeDO record);

    int insertSelective(NetworkBroadcastTypeDO record);

    NetworkBroadcastTypeDO selectByPrimaryKey(Long typeId);

    int updateByPrimaryKeySelective(NetworkBroadcastTypeDO record);

    int updateByPrimaryKey(NetworkBroadcastTypeDO record);
}