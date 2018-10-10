package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.NetworkAnchorDO;
import com.taofeng.webcast.dao.manager.Ext.INetworkAnchorExtManager;
import com.taofeng.webcast.dao.mapper.ext.NetworkAnchorExtMapper;
import com.taofeng.webcast.dao.query.NetworkAnchorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>网络主播详情的数据库操作语句,给service提供接口</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:05
 * @since V1.0
 */
@Component
public class NetworkAnchorExtManagerImpl implements INetworkAnchorExtManager{

    @Autowired
    private NetworkAnchorExtMapper networkAnchorExtMapper;

    /**
     * 根据=条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<NetworkAnchorDO> selectByQuery(NetworkAnchorQuery query) {
        return networkAnchorExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(NetworkAnchorQuery query) {
        return networkAnchorExtMapper.countByQuery(query);
    }

    /**
     * 更新数据（参数可以为空）
     * @param record
     * @param query
     * @return
     */
    @Override
    public Integer updateByQuerySelective(NetworkAnchorDO record, NetworkAnchorQuery query) {
        return networkAnchorExtMapper.updateByQuerySelective(record,query);
    }
}
