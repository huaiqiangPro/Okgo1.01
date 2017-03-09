package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * (RTFSC)
 * 描述 Okgo1.0
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2017/2/19
 */
public class QueryHttp {
    public static void main(String[] args) {
        OkHttpClient client =new OkHttpClient();
        HttpUrl httpUrl =HttpUrl.parse("")
                .newBuilder()
                .addQueryParameter("","")
                .build();
        String url = httpUrl.toString();
        System.out.println(url);

        Request request = new Request.Builder().url(url).build();

        try {
            Response response =client.newCall(request).execute();
            if (response.isSuccessful()){
                System.out.println(response.body().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
