/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package com.mhc.hummer.core.bus.subscriber
 * @author 星璇（xingxuan@maihaoche.com）
 * @date 2018年03月30日 11:25
 * @Copyright 2017-2020 www.maihaoche.com Inc. All rights reserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.taofeng.webcast.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.taofeng.webcast.bus.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p> 运单订阅者 </p>
 *
 * @author 星璇（xingxuan@maihaoche.com）
 * @date 2018年03月30日 11:25
 * @since V1.0
 */
@Component
@Slf4j
public class TestSubscriber {

    @Subscribe
    public void bindingCarrier(TestEvent event) {
        log.info("receive event:[{}]",event);

    }

}
