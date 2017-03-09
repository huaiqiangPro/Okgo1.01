package com.mybox.utils;

/**
 * Created by yuerq on 16/9/9.
 */
public class JSON {

    public static final <T> T parseObject(String text, Class<T> clazz) {
        return JsonUtils.toBean(clazz, text);
    }
}
