package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.DealComplainDO;

public interface DealComplainMapper {
    int deleteByPrimaryKey(Long dealComplainId);

    int insert(DealComplainDO record);

    int insertSelective(DealComplainDO record);

    DealComplainDO selectByPrimaryKey(Long dealComplainId);

    int updateByPrimaryKeySelective(DealComplainDO record);

    int updateByPrimaryKey(DealComplainDO record);
}