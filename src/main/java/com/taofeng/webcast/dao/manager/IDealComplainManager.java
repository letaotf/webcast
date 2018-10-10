package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.DealComplainDO;

/**
 * <p>投诉表sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IDealComplainManager {

    /**
     * 根据主键id删除
     * @param dealComplainId
     * @return
     */
    Integer deleteByPrimaryKey(Long dealComplainId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(DealComplainDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(DealComplainDO record);

    /**
     * 根据主键查找
     * @param dealComplainId
     * @return
     */
    DealComplainDO selectByPrimaryKey(Long dealComplainId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(DealComplainDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(DealComplainDO record);
}
