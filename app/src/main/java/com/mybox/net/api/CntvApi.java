package com.mybox.net.api;

import com.mybox.model.RecommendGuessYouLove;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.net.retrofit.HttpTools;
import com.mybox.net.retrofit.Call;

import rx.Observable;

/**
 * Created by yuerq on 16/9/6.
 */
public class CntvApi {
    /**
     * 推荐页
     * @param url
     * @return
     */
    public static Observable<RecommendedHomeBean> getRecommendHomeData(String url) {
        return HttpTools.get(url, RecommendedHomeBean.class, null).toObservable();
    }

    public static Call<RecommendHomeColumnListInfo> getRecommendHomeColumnData(String url) {
        return HttpTools.get(url, RecommendHomeColumnListInfo.class, null);
    }

    public static Call<RecommendGuessYouLove> getRecommendGuessYouLove(String url) {
        return HttpTools.post(url, RecommendGuessYouLove.class, null);
    }

}
