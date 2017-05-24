package com.mybox.net.retrofit;

import android.content.Context;

import com.mybox.AppContext;
import com.mybox.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yuerq on 16/7/14.
 */
public class InterceptorFactory {

    private static final HttpLoggingInterceptor LOGGING_INTERCEPTOR = new HttpLoggingInterceptor();

    static {
        LOGGING_INTERCEPTOR.setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Context context = AppContext.getInstance();

            Request request = chain.request();
            if (request.cacheControl().noCache()) {

            } else if(!NetUtils.isNetworkAvailable(context)){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            return originalResponse;
        }
    };

    public static Interceptor getLoggingInterceptor() {
        return LOGGING_INTERCEPTOR;
    }

    public static Interceptor getCacheControlInterceptor() {
        return REWRITE_CACHE_CONTROL_INTERCEPTOR;
    }

}
