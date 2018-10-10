package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.DealComplainDO;
import com.taofeng.webcast.dao.manager.IDealComplainManager;
import com.taofeng.webcast.dao.mapper.DealComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>投诉表sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class DealComplainManagerImpl implements IDealComplainManager{

    @Autowired
    private DealComplainMapper dealComplainMapper;

    /**
     * 根据主键id删除
     * @param dealComplainId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long dealComplainId) {
        return dealComplainMapper.deleteByPrimaryKey(dealComplainId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(DealComplainDO record) {
        return dealComplainMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(DealComplainDO record) {
        return dealComplainMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param dealComplainId
     * @return
     */
    @Override
    public DealComplainDO selectByPrimaryKey(Long dealComplainId) {
        return dealComplainMapper.selectByPrimaryKey(dealComplainId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(DealComplainDO record) {
        return dealComplainMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(DealComplainDO record) {
        return dealComplainMapper.updateByPrimaryKey(record);
    }
}
