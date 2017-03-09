package com.mybox.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5(String info) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(info.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        //16位加密，从第9位到25位
        //return md5StrBuff.substring(8, 24).toString().toUpperCase();//16位
        return md5StrBuff.toString().toUpperCase();//32位
    }

    // MD5変換
    public static String Md5(String str) {
        if (str != null && !str.equals("")) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'a', 'b', 'c', 'd', 'e', 'f'};
                byte[] md5Byte = md5.digest(str.getBytes("UTF8"));
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < md5Byte.length; i++) {
                    sb.append(HEX[(int) (md5Byte[i] & 0xff) / 16]);
                    sb.append(HEX[(int) (md5Byte[i] & 0xff) % 16]);
                }
                str = sb.toString();
            } catch (NoSuchAlgorithmException e) {
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static String md5(String input) {
        String result = input;
        if (input != null && !"".equals(input)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(input.getBytes());
                BigInteger e = new BigInteger(1, md.digest());

                for (result = e.toString(16); result.length() < 32; result = "0" + result) {
                    ;
                }
            } catch (NoSuchAlgorithmException var4) {
                var4.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 获取Android本机MAC
     *
     * @return Mac Address
     */
    public static String getMacAddress(Context activity) {
        if (isEmulator(activity)) {
            return "";
        } else {
            WifiManager wifi = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String mac = info.getMacAddress();
            if (null != mac) {
                mac = mac.replaceAll(":", "").toUpperCase();
                mac = md5(mac);
                return mac;
            }
            return "-";
        }

    }

    /**
     * 获得设备IMEI标识
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = manager.getDeviceId();
            return md5(imei);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * android 不存在idfa所以返回"-"
     *
     * @return
     */
    public static String getIDFA() {

        return "-";
    }


    /**
     * 初次获取设备唯一标识,经过md5处理
     * <p/>
     * 9774d56d682e549c是模拟器的唯一标识，排除
     *
     * @param mContext
     */
    public static String generateAndroidId(Context mContext) {
        // Try to get the ANDROID_ID
        String android_id = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (android_id == null || android_id.equals("9774d56d682e549c") || android_id.length() < 15) {
            return "-";
        }

        android_id = md5(android_id);
        return android_id;
    }

    //判断当前设备是否是模拟器。如果返回TRUE，则当前是模拟器，不是返回FALSE
    public static boolean isEmulator(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (imei != null && imei.equals("000000000000000")) {
                return true;
            }
            return (Build.MODEL.equals("sdk")) || (Build.MODEL.equals("google_sdk"));
        } catch (Exception ioe) {

        }
        return false;
    }

    //对字符串做检查的函数
    public static String checkParameter(String s, int len) {
        if (s == null) {
            return "-";
        }
        if (s.length() <= len) {
            return s;
        } else {
            return s.substring(0, len);
        }
    }

}
