package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.AssetManagementDetailDO;
import com.taofeng.webcast.dao.query.AssetManagementDetailQuery;

import java.util.List;

/**
 * <p>资产详情查询</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午11:27
 * @since V1.0
 */
public interface IAssetManagermentDetailExtManager {

    /**
     * 根据条件进行查询
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
