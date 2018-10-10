package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.AnchorDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AnchorForm;
import com.taofeng.webcast.common.form.AuditAnchorForm;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>网络主播管理AO</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午4:29
 * @since V1.0
 */
@Service
@Slf4j
public interface IAnchorManagementService {

    /**
     * 查询网络主播的申请记录
     * @param form
     * @return
     */
    GeneralResult<PageResult<AnchorDTO>> queryAnchorApplyRecord(AnchorForm form);

    /**
     * 审核网络主播
     * @param form
     * @return
     */
    GeneralResult<Boolean> auditAnchor(AuditAnchorForm form);
}
