package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.IntergityValuesDO;

public interface IntergityValuesMapper {
    int deleteByPrimaryKey(Long intergityId);

    int insert(IntergityValuesDO record);

    int insertSelective(IntergityValuesDO record);

    IntergityValuesDO selectByPrimaryKey(Long intergityId);

    int updateByPrimaryKeySelective(IntergityValuesDO record);

    int updateByPrimaryKey(IntergityValuesDO record);
}