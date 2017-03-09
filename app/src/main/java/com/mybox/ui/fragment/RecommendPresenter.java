package com.mybox.ui.fragment;


import com.mybox.base.RxPresenter;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.net.api.DataHelper;
import com.mybox.net.retrofit.Transformers;

import rx.Subscriber;

/**
 * (RTFSC)
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2016/9/3
 */
public class RecommendPresenter extends RxPresenter<RecommendView> {


    public void loadData(String detailUrl) {

        addSubscription(DataHelper
                .getCntvRepository()
                .getRecommendHome(detailUrl)
                .compose(Transformers.<RecommendedHomeBean>applySchedulers())
                .subscribe(new Subscriber<RecommendedHomeBean>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getDataFail(e.toString());
                    }

                    @Override
                    public void onNext(RecommendedHomeBean recommendedHomeBean) {
                        mView.getDataSuccess(recommendedHomeBean);
                    }
                }));

    }
}

