package com.taofeng.webcast.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>创建编号</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午3:06
 * @since V1.0
 */
public class CreatOrderNoUtil {

    private static final ThreadLocal<DateFormat> df
            = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    /**
     * 创建用户编号
     * @param date
     * @return
     */
    public static String createUserNO(Date date){
        String randomCode = RandomCodeUtil.getRandomCodeFouth();
        return new StringBuilder("U").append(df.get().format(date)).append(randomCode).toString();
    }

    /**
     * 创建商品编号
     * @param date
     * @return
     */
    public static String createGoodsNO(Date date){
        String randomCode = RandomCodeUtil.getRandomCodeSix();
        return new StringBuilder("G").append(df.get().format(date)).append(randomCode).toString();
    }

    /**
     * 创建资产编号
     * @param date
     * @return
     */
    public static String createAssetNO(Date date){
        String randomCode = RandomCodeUtil.getRandomCodeSix();
        return new StringBuilder("A").append(df.get().format(date)).append(randomCode).toString();
    }

    /**
     * 创建主播编号
     * @param date
     * @return
     */
    public static String createNetworkBroadCastNO(Date date){
        String randomCode = RandomCodeUtil.getRandomCodeSix();
        return new StringBuilder("").append(df.get().format(date)).append(randomCode).toString();
    }

}
