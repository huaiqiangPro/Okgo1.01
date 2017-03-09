package com.mybox.net.api;

import android.text.TextUtils;

import com.mybox.model.RecommendGuessYouLove;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.utils.HttpURLConnectionUtil;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by yuerq on 16/9/21.
 */

public class CntvDataRepository implements CntvRepository {
    @Override
    public Observable<RecommendedHomeBean> getRecommendHome(String url) {

        return CntvApi.getRecommendHomeData(url).doOnNext(new Action1<RecommendedHomeBean>() {
            @Override
            public void call(RecommendedHomeBean recommendedHomeBean) {

                if (recommendedHomeBean.getData() != null && recommendedHomeBean.getData().getColumnList() != null) {

                    for (int i = 0; i < recommendedHomeBean.getData().getColumnList().size(); i++) {

                        final String templateUrl = recommendedHomeBean.getData().getColumnList().get(i).getTemplateUrl();
                        String templateType = recommendedHomeBean.getData().getColumnList().get(i).getTemplateType();
                        String zntjUrl;

                        if (!TextUtils.isEmpty(templateUrl) && !"7".equals(templateType)) {

                            try {
                                RecommendHomeColumnListInfo columnListInfo = CntvApi.getRecommendHomeColumnData(templateUrl).execute().body();
                                recommendedHomeBean.getData().getColumnList().get(i).setInfo(columnListInfo);
                            } catch (Exception e) {

                            }
                        } else if ("7".equals(templateType) && !TextUtils.isEmpty(zntjUrl = HttpURLConnectionUtil.getZNTJurl())) {
                            try {
                                RecommendGuessYouLove recommendGuessYouLove = CntvApi.getRecommendGuessYouLove(zntjUrl).execute().body();

                                List newRes = recommendGuessYouLove.getNewRes();
                                List hotRes = recommendGuessYouLove.getHotRes();
                                List relRes = recommendGuessYouLove.getRelRes();

                                if (newRes != null) {

                                    if (hotRes != null && !hotRes.isEmpty()) {
                                        newRes.addAll(hotRes);
                                    }
                                    if (relRes != null && !relRes.isEmpty()) {
                                        newRes.addAll(relRes);
                                    }
                                }

                                if (newRes != null && !newRes.isEmpty()) {
                                    recommendedHomeBean.getData().getColumnList().get(i).setInfo(newRes);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        });
    }

}
