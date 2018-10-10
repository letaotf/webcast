package com.taofeng.webcast;

import com.google.common.eventbus.EventBus;
import com.taofeng.webcast.bus.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:22
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackages = {"com.taofeng.webcast"})
@ContextConfiguration(locations={"classpath:mapping/*.xml"})
@Slf4j
public class UserTest {

    @Test
    public void testEventBUs(){
        EventBus eventBus = new EventBus();
        eventBus.post(new TestEvent());
    }

}
