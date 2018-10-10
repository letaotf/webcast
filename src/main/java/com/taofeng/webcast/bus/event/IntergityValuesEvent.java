package com.taofeng.webcast.bus.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>初始化诚信值</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/30 下午8:48
 * @since V1.0
 */
@Builder
@ToString
@Getter
@AllArgsConstructor
public class IntergityValuesEvent {

    /**
     * 用户iD
     */
    private Long userId;

    /**
     * 诚信值
     */
    private Double valueNum;

}
