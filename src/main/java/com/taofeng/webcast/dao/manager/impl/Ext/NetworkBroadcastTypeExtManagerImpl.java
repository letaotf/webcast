package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDO;
import com.taofeng.webcast.dao.manager.Ext.INetworkBroadcastTypeExtManager;
import com.taofeng.webcast.dao.mapper.ext.NetworkBroadcastTypeExtMapper;
import com.taofeng.webcast.dao.query.NetworkBroadcastTypeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>网络直播类型查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class NetworkBroadcastTypeExtManagerImpl implements INetworkBroadcastTypeExtManager {

    @Autowired
    private NetworkBroadcastTypeExtMapper networkBroadcastTypeExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<NetworkBroadcastTypeDO> selectByQuery(NetworkBroadcastTypeQuery query) {
        return networkBroadcastTypeExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(NetworkBroadcastTypeQuery query) {
        return networkBroadcastTypeExtMapper.countByQuery(query);
    }
}
