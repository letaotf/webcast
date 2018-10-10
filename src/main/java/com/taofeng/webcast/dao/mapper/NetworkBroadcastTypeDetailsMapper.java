package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;

public interface NetworkBroadcastTypeDetailsMapper {
    int deleteByPrimaryKey(Long networkBroadcastTypeDetailId);

    int insert(NetworkBroadcastTypeDetailsDO record);

    Long insertSelective(NetworkBroadcastTypeDetailsDO record);

    NetworkBroadcastTypeDetailsDO selectByPrimaryKey(Long networkBroadcastTypeDetailId);

    int updateByPrimaryKeySelective(NetworkBroadcastTypeDetailsDO record);

    int updateByPrimaryKey(NetworkBroadcastTypeDetailsDO record);
}