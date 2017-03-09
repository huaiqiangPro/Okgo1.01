package com.mybox.net.executor;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuerq on 16/8/7.
 */
public class TransformerManager {

    private static TransformerManager sInstance;

    private Observable.Transformer schedulerTransformer;


    private TransformerManager() {
        schedulerTransformer = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.from(ThreadExecutorManager.getInstance().getScheduler()))
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static TransformerManager getsInstance() {

        if (sInstance == null) {
            sInstance = new TransformerManager();
        }

        return sInstance;

    }

    @SuppressWarnings("unchecked")
    public <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulerTransformer;
    }
}
