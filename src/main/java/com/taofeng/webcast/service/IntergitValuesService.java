package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.IntegerRecordDTO;
import com.taofeng.webcast.common.DTO.IntergitValuesDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.IntergitValuesForm;
import com.taofeng.webcast.common.form.UpdateIntergitValuesForm;
import com.taofeng.webcast.dao.domain.IntergityValuesDO;

/**
 * <p>诚信值管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 上午11:56
 * @since V1.0
 */
public interface IntergitValuesService {
    /**
     * 查询诚信值的列表
     * @param form
     * @return
     */
    GeneralResult<PageResult<IntergitValuesDTO>> queryIntergitValuesList(IntergitValuesForm form);

    /**
     * 查询诚信值的主要的扣分项
     * @param userId 用户ID
     * @return 诚信值记录
     */
    GeneralResult<IntegerRecordDTO> queryIntergitValuesRecord(Long userId);

    /**
     * 修改诚信值
     * @param form
     * @return
     */
    GeneralResult<Boolean> updateIntergitValues(UpdateIntergitValuesForm form);

    /**
     * 根据用户ID查询某一用户的诚信值信息
     * @param userId 用户ID
     * @return 诚信值记录
     */
    IntergityValuesDO queryIntergitValuesBy(Long userId);

}
