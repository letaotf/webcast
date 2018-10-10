package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;

/**
 * <p>网络直播类型详情查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface INetworkBroadcastTypeDetailsManager {

    /**
     * 根据主键id删除
     * @param networkBroadcastTypeDetailId
     * @return
     */
    Integer deleteByPrimaryKey(Long networkBroadcastTypeDetailId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(NetworkBroadcastTypeDetailsDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Long insertSelective(NetworkBroadcastTypeDetailsDO record);

    /**
     * 根据主键查找
     * @param networkBroadcastTypeDetailId
     * @return
     */
    NetworkBroadcastTypeDetailsDO selectByPrimaryKey(Long networkBroadcastTypeDetailId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(NetworkBroadcastTypeDetailsDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(NetworkBroadcastTypeDetailsDO record);
}
