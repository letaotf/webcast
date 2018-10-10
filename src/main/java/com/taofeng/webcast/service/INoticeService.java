package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.NoticeDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.form.NoticeForm;

import java.util.List;

/**
 * <p>公告管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:58
 * @since V1.0
 */
public interface INoticeService {

    /**
     * 添加公告
     * @param form
     * @return
     */
    GeneralResult<Boolean> addNotice(NoticeForm form);

    /**
     * 查询公告新
     * @return 公告信息
     */
    GeneralResult<List<NoticeDTO>> queryNoticeInfo();
}
