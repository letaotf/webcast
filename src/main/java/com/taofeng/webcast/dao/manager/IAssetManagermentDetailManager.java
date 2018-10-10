package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.AssetManagementDetailDO;

/**
 * <p>资产详情查询</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午11:27
 * @since V1.0
 */
public interface IAssetManagermentDetailManager {

    /**
     * 根据主键id删除
     * @param assetDetailId
     * @return
     */
    Integer deleteByPrimaryKey(Long assetDetailId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(AssetManagementDetailDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(AssetManagementDetailDO record);

    /**
     * 根据主键查找
     * @param assetDetailId
     * @return
     */
    AssetManagementDetailDO selectByPrimaryKey(Long assetDetailId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(AssetManagementDetailDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(AssetManagementDetailDO record);
}
