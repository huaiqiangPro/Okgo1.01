package com.mybox.utils;

import com.mybox.AppContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/*
 * (RTFSC)
 * @Version: 版本  1.0  2016/6/9
 */
public class HttpURLConnectionUtil {

    public static String getZNTJurl() {
        String zntjUrl = null;
        if (AppContext.getBasePath() != null) {
            zntjUrl = AppContext.getBasePath().get("zntj_index_url");
            if (zntjUrl != null) {
                zntjUrl = getZNTJurl(zntjUrl);
            }
        }
        return zntjUrl;
    }

    /**
     * 获取智能推荐URL
     *
     * @param url
     * @return
     */
    public static String getZNTJurl(final String url) {


        String userId = null;
        //IMEI、MAC、FA、Aid的获取方法，采用国双提供的API函数
        String mStrIMEI = MD5.getImei(AppContext.getInstance());//IMEI
        String finalmStrIMEI = MD5.checkParameter(mStrIMEI, 32);
        String mMacAddress = MD5.getMacAddress(AppContext.getInstance());//MAC
        String finalmMacAddress = MD5.checkParameter(mMacAddress, 32);
        String mAndroidId = MD5.generateAndroidId(AppContext.getInstance());//AndroidId
        String finalmAndroidId = MD5.checkParameter(mAndroidId, 32);


        if (finalmStrIMEI != null && !finalmStrIMEI.equals("")) {
            userId = finalmStrIMEI;
        } else if (finalmMacAddress != null && !finalmMacAddress.equals("")) {
            userId = finalmMacAddress;
        } else if (finalmAndroidId != null && !finalmAndroidId.equals("")) {
            userId = finalmAndroidId;
        } else {
            userId = "-1";
        }
        return url + userId + "/-1";

    }



}
