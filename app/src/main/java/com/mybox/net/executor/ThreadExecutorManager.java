package com.mybox.net.executor;

/**
 * Created by yuerq on 16/9/21.
 */

public class ThreadExecutorManager {

    private static ThreadExecutorManager sInstance;

    private ThreadExecutor mThreadExecutor;

    private ThreadExecutorManager() {
        mThreadExecutor = new JobExecutor();
    }

    public static ThreadExecutorManager getInstance() {

        if (sInstance == null) {
            sInstance = new ThreadExecutorManager();
        }
        return sInstance;
    }

     public ThreadExecutor getScheduler() {
         return mThreadExecutor;
     }



}
