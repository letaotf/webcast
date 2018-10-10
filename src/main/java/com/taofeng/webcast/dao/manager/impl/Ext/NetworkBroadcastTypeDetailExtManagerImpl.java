package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;
import com.taofeng.webcast.dao.manager.Ext.INetworkBroadcastTypeDetailsExtManager;
import com.taofeng.webcast.dao.mapper.ext.NetworkBroadcastTypeDetailsExtMapper;
import com.taofeng.webcast.dao.query.NetworkBroadcastTypeDetailsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>网络直播类型详情查询语</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class NetworkBroadcastTypeDetailExtManagerImpl implements INetworkBroadcastTypeDetailsExtManager {

    @Autowired
    private NetworkBroadcastTypeDetailsExtMapper networkBroadcastTypeDetailsExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<NetworkBroadcastTypeDetailsDO> selectByQuery(NetworkBroadcastTypeDetailsQuery query) {
        return networkBroadcastTypeDetailsExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(NetworkBroadcastTypeDetailsQuery query) {
        return networkBroadcastTypeDetailsExtMapper.countByQuery(query);
    }

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    @Override
    public int updateByQuerySelective(NetworkBroadcastTypeDetailsDO record, NetworkBroadcastTypeDetailsQuery query) {
        return networkBroadcastTypeDetailsExtMapper.updateByQuerySelective(record,query);
    }
}
