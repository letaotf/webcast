package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.AssetManagementDO;
import com.taofeng.webcast.dao.manager.IAssetManagermentManger;
import com.taofeng.webcast.dao.mapper.AssetManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>资产管理查询</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午10:57
 * @since V1.0
 */
@Component
public class AssetManagermentMangerImpl implements IAssetManagermentManger{


    @Autowired
    private AssetManagementMapper assetManagementMapper;

    /**
     * 根据主键id删除
     * @param assetId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long assetId) {
        return assetManagementMapper.deleteByPrimaryKey(assetId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(AssetManagementDO record) {
        return assetManagementMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(AssetManagementDO record) {
        return assetManagementMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param assetId
     * @return
     */
    @Override
    public AssetManagementDO selectByPrimaryKey(Long assetId) {
        return assetManagementMapper.selectByPrimaryKey(assetId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(AssetManagementDO record) {
        return assetManagementMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(AssetManagementDO record) {
        return assetManagementMapper.updateByPrimaryKey(record);
    }
}
