package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.TimeDO;
import com.taofeng.webcast.dao.manager.ITimeManager;
import com.taofeng.webcast.dao.mapper.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>时间查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class TimeManagerImpl implements ITimeManager{

    @Autowired
    private TimeMapper timeMapper;

    /**
     * 根据主键id删除
     * @param timeId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long timeId) {
        return timeMapper.deleteByPrimaryKey(timeId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(TimeDO record) {
        return timeMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(TimeDO record) {
        return timeMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param timeId
     * @return
     */
    @Override
    public TimeDO selectByPrimaryKey(Long timeId) {
        return timeMapper.selectByPrimaryKey(timeId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(TimeDO record) {
        return timeMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(TimeDO record) {
        return timeMapper.updateByPrimaryKey(record);
    }
}
