package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.IntergityValuesDO;

/**
 * <p>诚信值查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IIntergityValuesManager {

    /**
     * 根据主键id删除
     * @param intergityId
     * @return
     */
    Integer deleteByPrimaryKey(Long intergityId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(IntergityValuesDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(IntergityValuesDO record);

    /**
     * 根据主键查找
     * @param intergityId
     * @return
     */
    IntergityValuesDO selectByPrimaryKey(Long intergityId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(IntergityValuesDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(IntergityValuesDO record);
}
