package com.mybox.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.mybox.utils.TUtil;


/**
 * Created by yuerq on 2016/12/1.
 */

public abstract class BaseLayout<T extends BasePresenter> extends FrameLayout implements BaseView {

    protected T mPresenter;

    public BaseLayout(Context context) {
        this(context, null);
    }

    public BaseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPresenter = TUtil.getT(this, 0);
        mPresenter.attachView(this);
    }

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
