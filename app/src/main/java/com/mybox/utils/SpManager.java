package com.mybox.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yuerq on 16/9/19.
 */
public class SpManager {

    public static final String VERSION_SP = "guide_pre";
    private static SpManager ourInstance;

    private Context mContext;

    public static SpManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SpManager(context);
        }
        return ourInstance;
    }

    private SpManager(Context context) {
        mContext = context.getApplicationContext();
    }


    public void putVersion(int versionNo) {
        SharedPreferences sp = mContext.getSharedPreferences(VERSION_SP, Context.MODE_PRIVATE);
        sp.edit().putInt("newversion", versionNo).commit();

    }

    public int getVersionNo() {
        SharedPreferences sp = mContext.getSharedPreferences(VERSION_SP, Context.MODE_PRIVATE);
        return sp.getInt("newversion", 0);
    }

    public String getStoreType() {
        SharedPreferences sp = mContext.getSharedPreferences("save_path", Context.MODE_PRIVATE);
        return sp.getString("store", "phone");
    }

}
