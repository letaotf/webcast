package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.AssetManagementDO;

public interface AssetManagementMapper {
    int deleteByPrimaryKey(Long assetId);

    int insert(AssetManagementDO record);

    int insertSelective(AssetManagementDO record);

    AssetManagementDO selectByPrimaryKey(Long assetId);

    int updateByPrimaryKeySelective(AssetManagementDO record);

    int updateByPrimaryKey(AssetManagementDO record);
}