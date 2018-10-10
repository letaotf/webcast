package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.AssetManagementDTO;
import com.taofeng.webcast.common.DTO.AssetManagementDetailDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AssetDetailsForm;
import com.taofeng.webcast.common.form.AssetManagementForm;
import com.taofeng.webcast.dao.domain.AssetManagementDO;

import java.util.List;

/**
 * <p>资产管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:58
 * @since V1.0
 */
public interface IAssetManagementService {

    /**
     * 查询资产列表
     * @param form
     * @return
     */
    GeneralResult<PageResult<AssetManagementDTO>> queryAssetList(AssetManagementForm form);

    /**
     * 查询资产详情
     * @param form
     * @return
     */
    GeneralResult<PageResult<AssetManagementDetailDTO>> queryAssetListDetails(AssetDetailsForm form);

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return 资产详情
     */
    GeneralResult<List<AssetManagementDetailDTO>> queryAssetDetailBy(Long userId);

    /**
     * 根据用户id查询主播资产
     * @param userId
     * @return
     */
    AssetManagementDO queryAssetInfoBy(Long userId);
}
