package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.NetworkAnchorDO;
import com.taofeng.webcast.dao.manager.INetworkAnchorManager;
import com.taofeng.webcast.dao.mapper.NetworkAnchorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>web_user表的数据库操作语句,给service提供接口</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:05
 * @since V1.0
 */
@Component
public class NetworkAnchorManagerImpl implements INetworkAnchorManager{

    @Autowired
    private NetworkAnchorMapper networkAnchorMapper;

    @Override
    public Integer deleteByPrimaryKey(Long networkAnchorId) {
        return networkAnchorMapper.deleteByPrimaryKey(networkAnchorId);
    }

    @Override
    public Integer insert(NetworkAnchorDO record) {
        return networkAnchorMapper.insert(record);
    }

    @Override
    public Long insertSelective(NetworkAnchorDO record) {
        return networkAnchorMapper.insertSelective(record);
    }

    @Override
    public NetworkAnchorDO selectByPrimaryKey(Long uesrId) {
        return networkAnchorMapper.selectByPrimaryKey(uesrId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(NetworkAnchorDO record) {
        return networkAnchorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(NetworkAnchorDO record) {
        return networkAnchorMapper.updateByPrimaryKey(record);
    }

}
