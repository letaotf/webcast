package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDO;
import com.taofeng.webcast.dao.query.NetworkBroadcastTypeQuery;

import java.util.List;

public interface NetworkBroadcastTypeExtMapper {
    /**
     * 根据条件查询
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