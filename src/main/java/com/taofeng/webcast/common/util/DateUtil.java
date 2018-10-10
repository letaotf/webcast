package com.taofeng.webcast.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * <p>日期工具类</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 上午9:23
 * @since V1.0
 */
@Slf4j
public class DateUtil {

    private final static String YYYY_MM_DD = "yyyy-MM-dd";
    private final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 将字符串转换成日期的格式
     * @param dateStr
     * @return
     */
    public static Date convertStringToDateYMD(String dateStr) {
        SimpleDateFormat f = new SimpleDateFormat(YYYY_MM_DD);
        if (Objects.isNull(dateStr)) {
            return null;
        }
        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            log.error("转化为时间失败", e);
        }
        return null;
    }

    /**
     * 把date转化为新版的LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime ofLocalDateTime(Date date) {
        if (date == null) {
            return null;
        } else {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }
    }

    /**
     * 将LocalDateTime转化为String
     * @return
     */
    public static String convertLocalDateTimeToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM);
        return dateTime.format(formatter);
    }
}
