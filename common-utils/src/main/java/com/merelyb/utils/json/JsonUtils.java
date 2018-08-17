package com.merelyb.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.json
 * @作者: LiM
 * @创建时间: 2018-07-27 10:57
 * @Description: JSON 工具类
 */
public class JsonUtils {

    private static Gson gson = null;

    static {
        gson = new GsonBuilder()
                .disableHtmlEscaping()
                .serializeSpecialFloatingPointValues()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")//日期时间格式
                .create();
    }

    /**
     * 传入对象转换成json对象
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2Json(T obj) {
        return gson.toJson(obj);
    }

    /**
     * 传入对象List转换成json数组对象
     *
     * @param objs
     * @param <T>
     * @return
     */
    public static <T> String objs2Json(List<T> objs) {
        if (CollectionUtils.isNotEmpty(objs)) {
            return gson.toJson(objs);
        }
        return null;
    }

    /**
     * json转换成对象
     *
     * @param json
     * @return
     */
    public static <T> T json2Obj(String json, Class<T> obj) {
        return gson.fromJson(json, obj);
    }

    /**
     * json转换成设定的对象
     *
     * @param json      要转换的字符串
     * @param typeToken 转换类型，例如果想转换成list中存在user对象：new TypeToken<List<User>>(){}
     * @return
     */
    public static <T> T json2Objs(String json, TypeToken typeToken) {
        return (T) gson.fromJson(json, typeToken.getType());
    }

    /**
     * 在字符串不为 json格式的情况下 返回true
     * <pre>
     *     JsonUtils.isNotJson("[")==true
     *     JsonUtils.isNotJson("[]")==false
     * </pre>
     *
     * @param json 需要校验的json  可能为null
     * @return {@code true}  如果不是合法的json的话
     */
    public static boolean isNotJson(String json) {
        return !isJson(json);
    }

    /**
     * 在字符串为json的情况下 返回 true
     * <pre>
     *     JsonUtils.isJson("[]")==true
     *     JsonUtils.isJson("[")==false
     * </pre>
     *
     * @param json 需要校验的json  可能为null
     * @return {@code true}  如果是合法的json的话
     */
    public static boolean isJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
