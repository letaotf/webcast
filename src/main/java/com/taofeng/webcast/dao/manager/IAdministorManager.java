package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.AdministorDO;

/**
 * <p>管理员查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IAdministorManager {

    /**
     * 根据主键id删除
     * @param administorId
     * @return
     */
    Integer deleteByPrimaryKey(Long administorId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(AdministorDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Long insertSelective(AdministorDO record);

    /**
     * 根据主键查找
     * @param administorId
     * @return
     */
    AdministorDO selectByPrimaryKey(Long administorId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(AdministorDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(AdministorDO record);
}
