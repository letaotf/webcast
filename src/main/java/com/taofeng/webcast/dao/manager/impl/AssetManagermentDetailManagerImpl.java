package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.AssetManagementDetailDO;
import com.taofeng.webcast.dao.manager.IAssetManagermentDetailManager;
import com.taofeng.webcast.dao.mapper.AssetManagementDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>资产详情查询</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午11:28
 * @since V1.0
 */
@Component
public class AssetManagermentDetailManagerImpl implements IAssetManagermentDetailManager{

    @Autowired
    private AssetManagementDetailMapper assetManagementDetailMapper;

    /**
     * 根据主键id删除
     * @param assetDetailId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long assetDetailId) {
        return assetManagementDetailMapper.deleteByPrimaryKey(assetDetailId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(AssetManagementDetailDO record) {
        return assetManagementDetailMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(AssetManagementDetailDO record) {
        return assetManagementDetailMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param assetDetailId
     * @return
     */
    @Override
    public AssetManagementDetailDO selectByPrimaryKey(Long assetDetailId) {
        return assetManagementDetailMapper.selectByPrimaryKey(assetDetailId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(AssetManagementDetailDO record) {
        return assetManagementDetailMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(AssetManagementDetailDO record) {
        return assetManagementDetailMapper.updateByPrimaryKey(record);
    }
}
