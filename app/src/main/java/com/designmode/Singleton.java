package com.designmode;

/**
 * (RTFSC)
 * 描述 Okgo1.0
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2017/4/1
 * 静态内部类单例模式
 * 当第一次加载Singleton类时并不会初始化sInstance
 * 只有在第一次调用Singleton的getInstance方法时才会导致sInstance被初始化。
 * 因此，第一次调用getInstance方法会导致虚拟机加载SingletonHolder类，
 * 这种方式不仅能够确保线程安全，也能够保证单例对象的唯一性，
 * 同时也延迟了单例的实例化，所以这是推荐的单例模式实现方式。
 */
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {

        return SingletonHolder.sSingleton;
    }

    private static class SingletonHolder {
        private static final Singleton sSingleton = new Singleton();
    }
}

class SingleTon {
    private static volatile SingleTon singleTon = null;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }

}
