package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * (RTFSC)
 * 描述 Okgo1.0
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2017/2/17
 */
public class HeadHttp {
    public static void main(String args[]) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http:/www.imooc.com").addHeader("User-Agent","from okgo http").build();
        try {
            Response response =client.newCall(request).execute();
            if(response.isSuccessful()){
                Headers headers =response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(headers.name(i)+" : "+headers.value(i));
                }
                //System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
