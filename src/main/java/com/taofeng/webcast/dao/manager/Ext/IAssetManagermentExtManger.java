package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.AssetManagementDO;
import com.taofeng.webcast.dao.query.AssetManagementQuery;

import java.util.List;

/**
 * <p>资产管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午10:56
 * @since V1.0
 */
public interface IAssetManagermentExtManger {
    /**
     * 查询出来的数量
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
