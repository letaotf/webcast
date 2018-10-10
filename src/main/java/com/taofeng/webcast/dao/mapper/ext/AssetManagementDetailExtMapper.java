package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.AssetManagementDetailDO;
import com.taofeng.webcast.dao.query.AssetManagementDetailQuery;

import java.util.List;

public interface AssetManagementDetailExtMapper {
    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<AssetManagementDetailDO> selectByQuery(AssetManagementDetailQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(AssetManagementDetailQuery query);
}