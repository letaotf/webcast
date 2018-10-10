package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.AssetManagementDetailDO;
import com.taofeng.webcast.dao.manager.Ext.IAssetManagermentDetailExtManager;
import com.taofeng.webcast.dao.mapper.ext.AssetManagementDetailExtMapper;
import com.taofeng.webcast.dao.query.AssetManagementDetailQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>资产详情查询</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午11:28
 * @since V1.0
 */
@Component
public class AssetManagermentDetailExtManagerImpl implements IAssetManagermentDetailExtManager {

    @Autowired
    private AssetManagementDetailExtMapper assetManagementDetailExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<AssetManagementDetailDO> selectByQuery(AssetManagementDetailQuery query) {
        return assetManagementDetailExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(AssetManagementDetailQuery query) {
        return assetManagementDetailExtMapper.countByQuery(query);
    }
}
