package com.mybox.designmode;

import java.util.HashMap;
import java.util.Map;

/**
 * (RTFSC)
 * 描述 Okgo1.0
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2017/4/1
 * 在程序的初始，将多种单例类型注入到这个统一的管理类，在使用的时候根据key获取对象对应类型的对象。
 * 这种方式是我们可以管理多种类型的单例，并且在使用时可以通过统一的接口进行获取操作，降低了用户的使用成本，
 * 也对用户隐藏了具体实现，降低了耦合度。
 */
public class SingletonManager {
    private static Map<String, Object> objectMap = new HashMap<>();

    private static void putInstance(String key, Object instance) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance);
        }
    }

    public static Object getInstance(String key) {
        return objectMap.get(key);
    }
}
