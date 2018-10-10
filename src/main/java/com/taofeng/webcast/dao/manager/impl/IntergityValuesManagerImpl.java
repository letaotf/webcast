package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.IntergityValuesDO;
import com.taofeng.webcast.dao.manager.IIntergityValuesManager;
import com.taofeng.webcast.dao.mapper.IntergityValuesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>诚信值sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class IntergityValuesManagerImpl implements IIntergityValuesManager{

    @Autowired
    private IntergityValuesMapper intergityValuesMapper;

    /**
     * 根据主键id删除
     * @param intergityId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long intergityId) {
        return intergityValuesMapper.deleteByPrimaryKey(intergityId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(IntergityValuesDO record) {
        return intergityValuesMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(IntergityValuesDO record) {
        return intergityValuesMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param intergityId
     * @return
     */
    @Override
    public IntergityValuesDO selectByPrimaryKey(Long intergityId) {
        return intergityValuesMapper.selectByPrimaryKey(intergityId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(IntergityValuesDO record) {
        return intergityValuesMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(IntergityValuesDO record) {
        return intergityValuesMapper.updateByPrimaryKey(record);
    }
}
