package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.AssetManagementDetailDO;

public interface AssetManagementDetailMapper {
    int deleteByPrimaryKey(Long assetDetailId);

    int insert(AssetManagementDetailDO record);

    int insertSelective(AssetManagementDetailDO record);

    AssetManagementDetailDO selectByPrimaryKey(Long assetDetailId);

    int updateByPrimaryKeySelective(AssetManagementDetailDO record);

    int updateByPrimaryKey(AssetManagementDetailDO record);
}