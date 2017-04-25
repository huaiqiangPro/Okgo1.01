package com.mybox;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.mybox.net.retrofit.InterceptorFactory;
import com.mybox.net.retrofit.Network;
import com.mybox.tinker.Log.MyLogImp;
import com.mybox.tinker.util.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * Created by MAC on 16/4/15.
 */
@DefaultLifeCycle(
        application = "com.mybox.AppContext",
        flags = ShareConstants.TINKER_ENABLE_ALL)
public class AppContextLike extends DefaultApplicationLike {

    public AppContextLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();
        TinkerManager.setUpgradeRetryEnable(true);
        TinkerInstaller.setLogIml(new MyLogImp());


        TinkerManager.installTinker(this);


    }

    public static Application MainContext;

    private static Map<String, String> mBasePath = new HashMap<>();

    public static Application getInstance() {
        return MainContext;
    }

    public static Map<String, String> getBasePath() {
        return mBasePath;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MainContext = getApplication();

        initOkhttp();

    }

    private void initOkhttp() {
        //设置okhttp缓存
        File cacheFolder = getApplication().getCacheDir();
        Cache cache = new Cache(cacheFolder, 50 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(InterceptorFactory.getCacheControlInterceptor());

        OkHttpClient okHttpClient = builder.build();
        Network.getInstance().init(okHttpClient);
    }


}