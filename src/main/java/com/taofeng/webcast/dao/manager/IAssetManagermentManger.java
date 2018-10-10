package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.AssetManagementDO;

/**
 * <p>资产管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午10:56
 * @since V1.0
 */
public interface IAssetManagermentManger {
    /**
     * 根据主键id删除
     * @param assetId
     * @return
     */
    Integer deleteByPrimaryKey(Long assetId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(AssetManagementDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(AssetManagementDO record);

    /**
     * 根据主键查找
     * @param assetId
     * @return
     */
    AssetManagementDO selectByPrimaryKey(Long assetId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(AssetManagementDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(AssetManagementDO record);
}
