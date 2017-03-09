package com.mybox.net.retrofit;


import com.mybox.net.retrofit.toolbox.HttpParams;
import com.orhanobut.logger.Logger;

/**
 * Created by yuerq on 16/7/7.
 */
public class ApiConnection {

    public static <T> Builder<T> createGet(Class<T> type) {
        return new Builder<T>().method(Method.GET).type(type);
    }

    public static <T> Builder<T> createPost(Class<T> type) {
        return new Builder<T>().method(Method.POST).type(type);
    }

    public interface  Method {
        int GET = 0;
        int POST = 1;
        int DELETE = 2;
        int PUT = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }

    public static class Builder<T> {

        private String url;

        private HttpParams params;

        private boolean shouldCache = true;

        private int method = Method.GET;

        private Class<T> type;

        private int timeoutMs = Network.DEFAULT_TIME_OUT;


        public  Builder() {
        }

        public Builder<T> url(String url) {
            this.url = url;
            return this;
        }

        Builder<T> method(int method) {
            this.method = method;
            return this;
        }

        public Builder<T> params(HttpParams params) {
            this.params = params;
            return this;
        }

        public Builder<T> shouldCache(boolean shouldCache) {
            this.shouldCache = shouldCache;
            return this;
        }

        Builder<T> type(Class<T> type) {
            this.type = type;
            return this;
        }

        public Call<T> requestCall() {
            build();
            Call<T> okHttpCall = new OkHttpCall<>(this);
            Call call = new UICallWrapper(okHttpCall);
            return  call;
        }

        private Builder<T> build() {

            if (params == null) {
                params = new HttpParams();
            }
            if (url != null) {
                if (method == Method.GET) {
                    url += params.getUrlParams();
                }
            } else {
                Logger.e("url is null");
            }

            return this;
        }


        int getTimeoutMs() {
            return timeoutMs;
        }

        String getUrl() {
            return url;
        }

        HttpParams getParams() {
            return params;
        }

        int getMethod() {
            return method;
        }

        Class getType() {
            return type;
        }

        boolean isShouldCache() {
            return shouldCache;
        }
    }
}
