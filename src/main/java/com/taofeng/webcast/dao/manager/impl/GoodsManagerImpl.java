package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.GoodsDO;
import com.taofeng.webcast.dao.manager.IGoodsManager;
import com.taofeng.webcast.dao.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>商品表sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class GoodsManagerImpl implements IGoodsManager{

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 根据主键id删除
     * @param goodsId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long goodsId) {
        return goodsMapper.deleteByPrimaryKey(goodsId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(GoodsDO record) {
        return goodsMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(GoodsDO record) {
        return goodsMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param goodsId
     * @return
     */
    @Override
    public GoodsDO selectByPrimaryKey(Long goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(GoodsDO record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(GoodsDO record) {
        return goodsMapper.updateByPrimaryKey(record);
    }
}
