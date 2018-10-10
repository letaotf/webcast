package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.NetworkAnchorDO;

/**
 * <p>主播详情查询</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:04
 * @since V1.0
 */
public interface INetworkAnchorManager {
    /**
     * 根据主键id删除
     * @param networkAnchorId
     * @return
     */
    Integer deleteByPrimaryKey(Long networkAnchorId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(NetworkAnchorDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Long insertSelective(NetworkAnchorDO record);

    /**
     * 根据主键查找
     * @param networkAnchorId
     * @return
     */
    NetworkAnchorDO selectByPrimaryKey(Long networkAnchorId);

    /**
     * 根据主键进行更新
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(NetworkAnchorDO record);

    /**
     * 根据主键进行更新
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(NetworkAnchorDO record);

}
