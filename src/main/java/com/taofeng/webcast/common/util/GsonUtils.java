package com.taofeng.webcast.common.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/15 下午10:27
 * @since V1.0
 */
public class GsonUtils {

    private static Gson gson = null;

    private GsonUtils() {
    }

    public static String toJsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }

        return gsonString;
    }

    public static <T> T toObject(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }

        return t;
    }

    public static <T> T toObject(String gsonString, Type typeOfT) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, typeOfT);
        }

        return t;
    }

    public static <T> List<Map<String, T>> toListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = (List)gson.fromJson(gsonString, (new TypeToken<List<Map<String, T>>>() {
            }).getType());
        }

        return list;
    }

    public static <T> Map<String, T> toMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = (Map)gson.fromJson(gsonString, (new TypeToken<Map<String, T>>() {
            }).getType());
        }

        return map;
    }

    public static Gson getGson() {
        Gson gson = (new GsonBuilder()).serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").disableInnerClassSerialization().disableHtmlEscaping().create();
        return gson;
    }

    static {
        if (gson == null) {
            gson = new Gson();
        }

    }
}
