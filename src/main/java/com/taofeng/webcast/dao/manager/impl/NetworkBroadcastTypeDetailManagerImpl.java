package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;
import com.taofeng.webcast.dao.manager.INetworkBroadcastTypeDetailsManager;
import com.taofeng.webcast.dao.mapper.NetworkBroadcastTypeDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>网络直播类型详情查询语</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class NetworkBroadcastTypeDetailManagerImpl implements INetworkBroadcastTypeDetailsManager{

    @Autowired
    private NetworkBroadcastTypeDetailsMapper networkBroadcastTypeDetailsMapper;

    /**
     * 根据主键id删除
     * @param networkBroadcastTypeDetailId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long networkBroadcastTypeDetailId) {
        return networkBroadcastTypeDetailsMapper.deleteByPrimaryKey(networkBroadcastTypeDetailId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(NetworkBroadcastTypeDetailsDO record) {
        return networkBroadcastTypeDetailsMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Long insertSelective(NetworkBroadcastTypeDetailsDO record) {
        return networkBroadcastTypeDetailsMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param typeId
     * @return
     */
    @Override
    public NetworkBroadcastTypeDetailsDO selectByPrimaryKey(Long typeId) {
        return networkBroadcastTypeDetailsMapper.selectByPrimaryKey(typeId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(NetworkBroadcastTypeDetailsDO record) {
        return networkBroadcastTypeDetailsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(NetworkBroadcastTypeDetailsDO record) {
        return networkBroadcastTypeDetailsMapper.updateByPrimaryKey(record);
    }
}
