package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.ComplainDO;
import com.taofeng.webcast.dao.manager.IComplainManager;
import com.taofeng.webcast.dao.mapper.ComplainMapper;
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
public class ComplainManagerImpl implements IComplainManager{

    @Autowired
    private ComplainMapper complainMapper;

    /**
     * 根据主键id删除
     * @param complainId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long complainId) {
        return complainMapper.deleteByPrimaryKey(complainId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(ComplainDO record) {
        return complainMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(ComplainDO record) {
        return complainMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param complainId
     * @return
     */
    @Override
    public ComplainDO selectByPrimaryKey(Long complainId) {
        return complainMapper.selectByPrimaryKey(complainId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(ComplainDO record) {
        return complainMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(ComplainDO record) {
        return complainMapper.updateByPrimaryKey(record);
    }
}
