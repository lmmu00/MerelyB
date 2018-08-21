package com.merelyb.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils
 * @作者: LiM
 * @创建时间: 2018-08-21 14:56
 * @Description: ${Description}
 */
public class CommonUtils {

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * bean转Map
     *
     * @param object
     * @return
     */
    public Map<String, Object> objectToMap(Object object) throws Exception {
        if (object == null) {
            return null;
        }
        Map<String, Object> mapBean = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            // 过滤class属性
            if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(object);

                mapBean.put(key, value);
            }

        }
        return mapBean;
    }

}
