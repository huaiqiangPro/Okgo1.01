package com.mybox.base;

/**
 * Created by yuerq on 16/7/4.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
