package com.taofeng.webcast.bus.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>删除诚信值事</p >件
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/21 下午2:42
 * @since V1.0
 */
@Builder
@ToString
@Getter
@AllArgsConstructor
public class DeductIntergityValuesEvent {

    /**
     * 投诉id
     */
    private Long complainId;

    /**
     * 需要删除的诚信值
     */
    private Double deductValues;
}
