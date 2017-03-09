package com.mybox.ui.fragment;


import com.mybox.base.BaseView;
import com.mybox.model.RecommendedHomeBean;

/**
 * (RTFSC)
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2016/9/3
 */
public interface RecommendView extends BaseView {

    void getDataSuccess(RecommendedHomeBean model);

    void getDataFail(String msg);
}
