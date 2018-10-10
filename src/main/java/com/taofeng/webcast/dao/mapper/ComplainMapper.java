package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.ComplainDO;

public interface ComplainMapper {
    int deleteByPrimaryKey(Long complainId);

    int insert(ComplainDO record);

    int insertSelective(ComplainDO record);

    ComplainDO selectByPrimaryKey(Long complainId);

    int updateByPrimaryKeySelective(ComplainDO record);

    int updateByPrimaryKey(ComplainDO record);
}