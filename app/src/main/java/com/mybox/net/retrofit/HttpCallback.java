package com.mybox.net.retrofit;

/**
 * Created by yuerq on 16/9/6.
 */
public abstract class HttpCallback {

    /**
     * Http请求成功时回调
     *
     * @param t HttpRequest返回信息
     */
    public void onSuccess(String t) {
    }

    /**
     * Http请求失败时回调
     *
     * @param errorNo 错误码
     * @param strMsg  错误原因
     */
    public void onFailure(int errorNo, String strMsg) {

    }
}
