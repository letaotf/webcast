package com.taofeng.webcast.common.util;

import java.util.Date;
import java.util.Random;

/**
 * <p>生成随机码</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午2:52
 * @since V1.0
 */

public class RandomCodeUtil {

    /**
     * 生成六位的随机码
     * @return
     */
    public static String getRandomCodeSix(){
        //最大生成的随机数<1000000
        Integer randomSum = new Random(new Date().getTime()).nextInt(1000000);
        StringBuilder result = new StringBuilder("000000");
        result.append(randomSum);
        return result.substring(result.length()-6);
    }

    /**
     * 生成四位的随机码
     * @return
     */
    public static String getRandomCodeFouth(){
        //最大生成的随机数<1000000
        Integer randomSum = new Random(new Date().getTime()).nextInt(10000);
        StringBuilder result = new StringBuilder("0000");
        result.append(randomSum);
        return result.substring(result.length()-4);
    }

}
