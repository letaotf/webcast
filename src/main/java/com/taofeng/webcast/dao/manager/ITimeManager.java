package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.TimeDO;

/**
 * <p>时间查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface ITimeManager {

    /**
     * 根据主键id删除
     * @param timeId
     * @return
     */
    Integer deleteByPrimaryKey(Long timeId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(TimeDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(TimeDO record);

    /**
     * 根据主键查找
     * @param timeId
     * @return
     */
    TimeDO selectByPrimaryKey(Long timeId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(TimeDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(TimeDO record);
}
