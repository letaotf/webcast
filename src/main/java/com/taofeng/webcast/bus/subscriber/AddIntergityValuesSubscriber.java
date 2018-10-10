package com.taofeng.webcast.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.taofeng.webcast.bus.event.IntergityValuesEvent;
import com.taofeng.webcast.dao.domain.IntergityValuesDO;
import com.taofeng.webcast.dao.manager.IIntergityValuesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>add添加</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/30 下午8:51
 * @since V1.0
 */
@Component
@Slf4j
public class AddIntergityValuesSubscriber {

    @Autowired
    private IIntergityValuesManager intergityValuesManager;

    @Subscribe
    public void addIntergit(IntergityValuesEvent event){
        IntergityValuesDO intergityValuesDO = new IntergityValuesDO();
        intergityValuesDO.setUserId(event.getUserId());
        intergityValuesDO.setValueNum(event.getValueNum());
        intergityValuesManager.insertSelective(intergityValuesDO);
    }
}
