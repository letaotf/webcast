package com.taofeng.webcast.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.taofeng.webcast.bus.event.DeductIntergityValuesEvent;
import com.taofeng.webcast.common.Enum.RecordStatusEnum;
import com.taofeng.webcast.dao.domain.IntergityValuesDO;
import com.taofeng.webcast.dao.manager.Ext.IIntergityValuesExtManager;
import com.taofeng.webcast.dao.manager.IIntergityValuesManager;
import com.taofeng.webcast.dao.query.IntergityValuesQuery;
import com.taofeng.webcast.service.IComplainManagementService;
import com.taofeng.webcast.service.IntergitValuesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/21 下午2:45
 * @since V1.0
 */
@Component
@Slf4j
public class IntergityValuesSubscriber {

    @Autowired
    private IntergitValuesService intergitValuesService;
    @Autowired
    private IIntergityValuesExtManager intergityValuesExtManager;
    @Autowired
    private IIntergityValuesManager intergityValuesManager;
    @Autowired
    private IComplainManagementService complainManagementService;

    @Subscribe
    public void deductIntergityValues(DeductIntergityValuesEvent event){
        //先查询出用户ID
        Long userId = complainManagementService.queryUserIdBy(event.getComplainId());
        if(Objects.isNull(userId)){
            log.info("根据投诉ID{},查询用户id为空",event.getComplainId());
        }
        //将原来的诚信值置为查询出来
        IntergityValuesDO intergityValuesDO = intergitValuesService.queryIntergitValuesBy(userId);
        if(Objects.isNull(intergityValuesDO)){
            log.info("根据用户ID{},查询诚信值信息id为空",event.getComplainId());
        }
        Double values = intergityValuesDO.getValueNum();
        //将原来的诚信值置为无效
        IntergityValuesDO record = new IntergityValuesDO();
        record.setStatus(RecordStatusEnum.NO_VALID.getCode());
        IntergityValuesQuery query = new IntergityValuesQuery();
        query.createCriteria().andStatusEqualTo(RecordStatusEnum.VALID.getCode())
                .andUserIdEqualTo(userId);
        intergityValuesExtManager.updateByQuerySelective(record,query);
        //插入新的数据
        IntergityValuesDO result = new IntergityValuesDO();
        result.setUserId(userId);
        result.setValueNum(values-event.getDeductValues());
        intergityValuesManager.insertSelective(result);
    }
}
