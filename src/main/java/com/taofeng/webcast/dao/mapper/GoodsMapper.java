package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.GoodsDO;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    GoodsDO selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(GoodsDO record);

    int updateByPrimaryKey(GoodsDO record);
}