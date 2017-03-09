package com.mybox.net.retrofit;


import com.mybox.net.retrofit.toolbox.HttpParams;
import com.orhanobut.logger.Logger;



/**
 * Created by yuerq on 16/7/8.
 */
public class HttpTools {

    public static <T> Call<T> get(String url, Class<T> type) {

        return ApiConnection.createGet(type)
                .url(url)
                .requestCall();
    }

    public static <T> Call<T> get(String url, Class<T> type, HttpParams params) {

        return ApiConnection.createGet(type)
                .url(url)
                .params(params)
                .requestCall();
    }

    public static <T> Call<T> get(String url, Class<T> type, HttpParams params, boolean shouldCache) {

        return ApiConnection.createGet(type)
                .url(url)
                .params(params)
                .shouldCache(shouldCache)
                .requestCall();
    }

    public static <T> Call<T> post(String url, Class<T> type, HttpParams params) {

        return ApiConnection.createPost(type)
                .url(url)
                .params(params)
                .requestCall();
    }

    public static void get(String url, final HttpCallback httpCallback) {
        get(url, null, httpCallback);
    }

    public static void get(String url, final HttpCallback httpCallback, boolean shoudCache) {
        ApiConnection.createGet(String.class)
                .url(url)
                .shouldCache(shoudCache)
                .requestCall()
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        httpCallback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        httpCallback.onFailure(-1, t.getMessage());
                    }
                });
    }

    public static void get(String url, HttpParams params, final HttpCallback httpCallback) {

        if (httpCallback == null) {
            Logger.e("httpCallback is null");
            return;
        }
        ApiConnection.createGet(String.class)
                .url(url)
                .params(params)
                .requestCall()
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        httpCallback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        httpCallback.onFailure(-1, t.getMessage());
                    }
                });
    }

    public static void post(String url, final HttpCallback httpCallback) {
        post(url, null, httpCallback);
    }

    public static void post(String url, HttpParams params, final HttpCallback httpCallback) {

        ApiConnection.createPost(String.class)
                .url(url)
                .params(params)
                .requestCall()
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        httpCallback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        httpCallback.onFailure(-1, t.getMessage());
                    }
                });
    }

}
