package com.mybox;

import android.app.Application;

import com.mybox.net.retrofit.InterceptorFactory;
import com.mybox.net.retrofit.Network;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * Created by MAC on 16/4/15.
 */
public class AppContext extends Application {
    public static AppContext MainContext;

    private static Map<String, String> mBasePath = new HashMap<>();

    public static AppContext getInstance() {
        return MainContext;
    }


    public static Map<String, String> getBasePath() {
        return mBasePath;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MainContext = this;

        initOkhttp();

    }

    private void initOkhttp() {
        //设置okhttp缓存
        File cacheFolder = this.getCacheDir();
        Cache cache = new Cache(cacheFolder, 50 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(InterceptorFactory.getCacheControlInterceptor());

        OkHttpClient okHttpClient = builder.build();
        Network.getInstance().init(okHttpClient);
    }


}