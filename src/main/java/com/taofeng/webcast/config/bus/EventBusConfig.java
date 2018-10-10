package com.taofeng.webcast.config.bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>事件总线配置</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/1 上午10:15
 * @since V1.0
 */
@Configuration
@Slf4j
public class EventBusConfig {
    @Value("${spring.bus.corePoolSize:50}")
    private Integer corePoolSize;
    /**
     * 全局事件总线
     * @return
     */
    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    /**
     * 全局异步事件总线
     * @return
     */
    @Bean
    public AsyncEventBus asyncEventBus() {
        return new AsyncEventBus(busExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public ExecutorService busExecutor() {
        return Executors.newScheduledThreadPool(corePoolSize);
    }
}
