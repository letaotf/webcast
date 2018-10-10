package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.AssetManagementDO;
import com.taofeng.webcast.dao.manager.Ext.IAssetManagermentExtManger;
import com.taofeng.webcast.dao.mapper.ext.AssetManagementExtMapper;
import com.taofeng.webcast.dao.query.AssetManagementQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>资产管理查询</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午10:57
 * @since V1.0
 */
@Component
public class AssetManagermentExtMangerImpl implements IAssetManagermentExtManger {


    @Autowired
    private AssetManagementExtMapper assetManagementExtMapper;

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public List<AssetManagementDO> selectByQuery(AssetManagementQuery query) {
        return assetManagementExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(AssetManagementQuery query) {
        return assetManagementExtMapper.countByQuery(query);
    }
}
