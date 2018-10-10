package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.AdministorDO;
import com.taofeng.webcast.dao.manager.IAdministorManager;
import com.taofeng.webcast.dao.mapper.AdministorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>管理员查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class AdministorManagerImpl implements IAdministorManager{

    @Autowired
    private AdministorMapper administorMapper;

    /**
     * 根据主键id删除
     * @param administorId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long administorId) {
        return administorMapper.deleteByPrimaryKey(administorId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(AdministorDO record) {
        return administorMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Long insertSelective(AdministorDO record) {
        return administorMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param administorId
     * @return
     */
    @Override
    public AdministorDO selectByPrimaryKey(Long administorId) {
        return administorMapper.selectByPrimaryKey(administorId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(AdministorDO record) {
        return administorMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(AdministorDO record) {
        return administorMapper.updateByPrimaryKey(record);
    }
}
