package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDO;

/**
 * <p>网络直播类型查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface INetworkBroadcastTypeManager {

    /**
     * 根据主键id删除
     * @param typeId
     * @return
     */
    Integer deleteByPrimaryKey(Long typeId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(NetworkBroadcastTypeDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(NetworkBroadcastTypeDO record);

    /**
     * 根据主键查找
     * @param typeId
     * @return
     */
    NetworkBroadcastTypeDO selectByPrimaryKey(Long typeId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(NetworkBroadcastTypeDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(NetworkBroadcastTypeDO record);
}
