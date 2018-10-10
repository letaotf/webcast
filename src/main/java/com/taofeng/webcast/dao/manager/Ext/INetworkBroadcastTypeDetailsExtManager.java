package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;
import com.taofeng.webcast.dao.query.NetworkBroadcastTypeDetailsQuery;

import java.util.List;

/**
 * <p>网络直播类型详情查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface INetworkBroadcastTypeDetailsExtManager {

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    List<NetworkBroadcastTypeDetailsDO> selectByQuery(NetworkBroadcastTypeDetailsQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(NetworkBroadcastTypeDetailsQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(NetworkBroadcastTypeDetailsDO record,NetworkBroadcastTypeDetailsQuery query);

}
