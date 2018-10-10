package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDO;
import com.taofeng.webcast.dao.manager.INetworkBroadcastTypeManager;
import com.taofeng.webcast.dao.mapper.NetworkBroadcastTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>网络直播类型查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class NetworkBroadcastTypeManagerImpl implements INetworkBroadcastTypeManager{

    @Autowired
    private NetworkBroadcastTypeMapper networkBroadcastTypeMapper;

    /**
     * 根据主键id删除
     * @param typeId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long typeId) {
        return networkBroadcastTypeMapper.deleteByPrimaryKey(typeId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(NetworkBroadcastTypeDO record) {
        return networkBroadcastTypeMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(NetworkBroadcastTypeDO record) {
        return networkBroadcastTypeMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param typeId
     * @return
     */
    @Override
    public NetworkBroadcastTypeDO selectByPrimaryKey(Long typeId) {
        return networkBroadcastTypeMapper.selectByPrimaryKey(typeId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(NetworkBroadcastTypeDO record) {
        return networkBroadcastTypeMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(NetworkBroadcastTypeDO record) {
        return networkBroadcastTypeMapper.updateByPrimaryKey(record);
    }
}
