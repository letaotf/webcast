package com.taofeng.webcast.config.bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>注册事件消费者逻辑到事件总线</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/1 上午10:15
 * @since V1.0
 */
@Component
public class EventSubscribeBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // for each method in the bean
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            // check the annotations on that method
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                // if it contains the Subscribe annotation
                if (annotation.annotationType().equals(Subscribe.class)) {
                    // 如果这是一个Guava @Subscribe注解的事件监听器方法，说明所在bean实例
                    // 对应一个Guava事件监听器类，将该bean实例注册到Guava事件总线
                    eventBus.register(bean);
                    asyncEventBus.register(bean);
                    return bean;
                }
            }
        }

        return bean;
    }
}
