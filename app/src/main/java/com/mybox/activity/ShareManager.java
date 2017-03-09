package com.mybox.activity;

public class ShareManager {
    private static ShareManager shareManager = null;

    public static ShareManager getInstance() {
        if (shareManager == null) {
            synchronized (ShareManager.class) {
                if (shareManager == null) {
                    shareManager = new ShareManager();
                }
            }
        }
        return shareManager;
    }
    private ShareManager(){

    }
}