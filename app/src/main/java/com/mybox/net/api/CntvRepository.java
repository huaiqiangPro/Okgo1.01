package com.mybox.net.api;


import com.mybox.model.RecommendedHomeBean;

import rx.Observable;

/**
 * Created by yuerq on 16/9/21.
 */

public interface

CntvRepository {


    Observable<RecommendedHomeBean> getRecommendHome(String url);


//    Observable<OlympicDateBean> getOlympicHome(String url);
//
//    Observable<MarketDataBean> getFlow(String url);
}
