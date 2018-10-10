package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.NetworkAnchorDO;

public interface NetworkAnchorMapper {
    int deleteByPrimaryKey(Long networkAnchorId);

    int insert(NetworkAnchorDO record);

    long insertSelective(NetworkAnchorDO record);

    NetworkAnchorDO selectByPrimaryKey(Long networkAnchorId);

    int updateByPrimaryKeySelective(NetworkAnchorDO record);

    int updateByPrimaryKey(NetworkAnchorDO record);
}