package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.NetworkAnchorDO;
import com.taofeng.webcast.dao.query.NetworkAnchorQuery;

import java.util.List;

/**
 * <p>管理员详情查询接口</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:04
 * @since V1.0
 */
public interface INetworkAnchorExtManager {

    /**
     * 根据=条件进行查询
     * @param query
     * @return
     */
    List<NetworkAnchorDO> selectByQuery(NetworkAnchorQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(NetworkAnchorQuery query);

    /**
     * 更新数据（参数可以为空）
     * @param record
     * @param query
     * @return
     */
    Integer updateByQuerySelective(NetworkAnchorDO record, NetworkAnchorQuery query);
}
