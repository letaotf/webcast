package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.AssetManagementDO;
import com.taofeng.webcast.dao.query.AssetManagementQuery;

import java.util.List;

public interface AssetManagementExtMapper {

    /**
     * 根据条件查询查列表
     * @param query
     * @return
     */
    List<AssetManagementDO> selectByQuery(AssetManagementQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(AssetManagementQuery query);

}