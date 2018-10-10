package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.BuyGoodsDO;
import com.taofeng.webcast.dao.manager.IBuyGoodsManager;
import com.taofeng.webcast.dao.mapper.BuyGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/10 下午4:24
 * @since V1.0
 */
@Component
public class BuyGoodsManagerImpl implements IBuyGoodsManager{

    @Autowired
    private BuyGoodsMapper buyGoodsMapper;

    @Override
    public Integer deleteByPrimaryKey(Long buyGoodsId) {
        return buyGoodsMapper.deleteByPrimaryKey(buyGoodsId);
    }

    @Override
    public Integer insert(BuyGoodsDO record) {
        return buyGoodsMapper.insert(record);
    }

    @Override
    public Integer insertSelective(BuyGoodsDO record) {
        return buyGoodsMapper.insertSelective(record);
    }

    @Override
    public BuyGoodsDO selectByPrimaryKey(Long buyGoodsId) {
        return buyGoodsMapper.selectByPrimaryKey(buyGoodsId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BuyGoodsDO record) {
        return buyGoodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(BuyGoodsDO record) {
        return buyGoodsMapper.updateByPrimaryKey(record);
    }
}
