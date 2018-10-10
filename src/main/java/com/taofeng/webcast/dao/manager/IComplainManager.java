package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.ComplainDO;

/**
 * <p>投诉表sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IComplainManager {

    /**
     * 根据主键id删除
     * @param complainId
     * @return
     */
    Integer deleteByPrimaryKey(Long complainId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(ComplainDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(ComplainDO record);

    /**
     * 根据主键查找
     * @param complainId
     * @return
     */
    ComplainDO selectByPrimaryKey(Long complainId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(ComplainDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(ComplainDO record);
}
