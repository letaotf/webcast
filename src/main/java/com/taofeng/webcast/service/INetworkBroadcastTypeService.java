package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.NetworkBroadcastDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AddNetworkBroadcastForm;
import com.taofeng.webcast.common.form.DeleteNetworkBroadcastForm;
import com.taofeng.webcast.common.form.NetworkBroadcastForm;
import com.taofeng.webcast.common.form.UpdateNetworkBroadcastForm;

/**
 * <p>节目管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:31
 * @since V1.0
 */
public interface INetworkBroadcastTypeService {

    /**
     * 查询节目清单
     * @param form
     * @return
     */
    GeneralResult<PageResult<NetworkBroadcastDTO>> queryNetworkBroadcastType(NetworkBroadcastForm form);

    /**
     * 查询节目清单详情
     * @param networkBroadcastTypeDetailId
     * @return
     */
    GeneralResult<NetworkBroadcastDTO> queryNetworkBroadcastTypeDetail(Long networkBroadcastTypeDetailId);

    /**
     * 添加节目
     * @param form
     * @return
     */
    GeneralResult<Boolean> addNetworkBroadcastType(AddNetworkBroadcastForm form);

    /**
     * 更新节目
     * @param form
     * @return
     */
    GeneralResult<Boolean> updateNetworkBroadcastType(UpdateNetworkBroadcastForm form);

    /**
     * 删除节目
     * @param form
     * @return
     */
    GeneralResult<Boolean> deleteNetworkBroadcastType(DeleteNetworkBroadcastForm form);

}
