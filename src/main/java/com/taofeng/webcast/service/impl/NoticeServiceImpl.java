package com.taofeng.webcast.service.impl;

import com.taofeng.webcast.common.DTO.NoticeDTO;
import com.taofeng.webcast.common.Enum.ErrorCodeEnum;
import com.taofeng.webcast.common.Enum.RecordStatusEnum;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.form.NoticeForm;
import com.taofeng.webcast.common.util.BeanCopierUtil;
import com.taofeng.webcast.dao.domain.NoticesDO;
import com.taofeng.webcast.dao.manager.Ext.INoticesExtManager;
import com.taofeng.webcast.dao.manager.INoticeManager;
import com.taofeng.webcast.dao.query.NoticeQuery;
import com.taofeng.webcast.service.INoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>公告管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:58
 * @since V1.0
 */
@Service
@Slf4j
public class NoticeServiceImpl implements INoticeService{

    @Autowired
    private INoticeManager noticesManager;
    @Autowired
    private INoticesExtManager noticesExtManager;

    /**
     * 添加公告
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> addNotice(NoticeForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"添加公告时,系统错误");
        }
        //设置条件
        NoticesDO noticesDO = new NoticesDO();
        noticesDO.setTitle(form.getTitle());
        noticesDO.setContent(form.getContent());
        Integer result = noticesManager.insertSelective(noticesDO);
        if(result>=1){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.error("","插入失败");
    }

    /**
     * 查询公告新
     * @return 公告信息
     */
    @Override
    public GeneralResult<List<NoticeDTO>> queryNoticeInfo() {
        //设置查询条件
        NoticeQuery query = new NoticeQuery();
        query.setPageNo(1);
        query.setPageSize(3);
        query.setOrderByClause("gmt_create Desc");
        query.createCriteria().andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        //查询结果的拼接
        List<NoticesDO> noticesDOS = noticesExtManager.selectByQuery(query);
        List<NoticeDTO> noticeDTOS = new ArrayList<>();
        if(CollectionUtils.isEmpty(noticesDOS)){
            return GeneralResult.create("","没有公告存在");
        }
        noticesDOS.forEach(noticesDO -> {
            NoticeDTO noticeDTO = BeanCopierUtil.convert(noticesDO,NoticeDTO.class);
            noticeDTOS.add(noticeDTO);
        });
        return GeneralResult.success(noticeDTOS);
    }
}
