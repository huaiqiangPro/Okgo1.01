package com.mybox.net.retrofit;

import com.mybox.net.executor.TransformerManager;

import rx.Observable;

/**
 * Created by yuerq on 16/9/21.
 */

public class Transformers {

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return TransformerManager.getsInstance().applySchedulers();
    }
}
