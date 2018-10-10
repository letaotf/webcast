package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDO;
import com.taofeng.webcast.dao.query.NetworkBroadcastTypeQuery;

import java.util.List;

/**
 * <p>网络直播类型查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface INetworkBroadcastTypeExtManager {

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    List<NetworkBroadcastTypeDO> selectByQuery(NetworkBroadcastTypeQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(NetworkBroadcastTypeQuery query);
}
