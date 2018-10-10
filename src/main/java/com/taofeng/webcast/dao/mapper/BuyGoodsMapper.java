package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.BuyGoodsDO;

public interface BuyGoodsMapper {
    int deleteByPrimaryKey(Long buyGoodsId);

    int insert(BuyGoodsDO record);

    int insertSelective(BuyGoodsDO record);

    BuyGoodsDO selectByPrimaryKey(Long buyGoodsId);

    int updateByPrimaryKeySelective(BuyGoodsDO record);

    int updateByPrimaryKey(BuyGoodsDO record);
}