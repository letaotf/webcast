package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.ComplainDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AddComplainForm;
import com.taofeng.webcast.common.form.ComplainForm;
import com.taofeng.webcast.common.form.DealComplainForm;
import com.taofeng.webcast.common.form.UpdateComplainForm;
import com.taofeng.webcast.dao.domain.DealComplainDO;

import java.util.List;

/**
 * <p>投诉管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:45
 * @since V1.0
 */
public interface IComplainManagementService {
    /**
     * 查询投诉列表
     * @param form
     * @return
     */
    GeneralResult<PageResult<ComplainDTO>> queryComplainList(ComplainForm form);

    /**
     * 查询投诉列表详情
     * @param complainId
     * @return
     */
    GeneralResult<ComplainDTO> queryComplainDetail(Long complainId);

    /**
     * 查询投诉信息列表根据用户ID
     * @param userId 用户ID
     * @param complainStatus 0是投诉人 1是被投诉者
     * @return
     */
    GeneralResult<List<ComplainDTO>> queryComplainDetailBy(Long userId,Integer complainStatus);

    /**
     * 添加投诉等级
     * @param form
     * @return
     */
    GeneralResult<Boolean> dealComplainRank(DealComplainForm form);


    /**
     * 添加投诉
     * @param form
     * @return
     */
    GeneralResult<Boolean> addComplain(AddComplainForm form);


    /**
     * 更新投诉
     * @param form
     * @return
     */
    GeneralResult<Boolean> updateComplain(UpdateComplainForm form);

    /**
     * 根据投诉IDS查询投诉处理详情
     * @param complainIds 投诉IDS
     * @return 投诉处理详情
     */
    List<DealComplainDO> queryComplainDealDetailBy(List<Long> complainIds);

    /**
     * 根据投诉者id 查询用户id
     * @param complainId
     * @return
     */
    Long queryUserIdBy(Long complainId);
}
