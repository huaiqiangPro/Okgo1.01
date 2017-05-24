package com.mybox.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;

import com.mybox.AppContext;

import java.io.File;
import java.lang.reflect.InvocationTargetException;


public class GetStorageUtil {

    public static GetStorageUtil instance;

    public GetStorageUtil() {
    }

    public static GetStorageUtil getInstance() {
        if (instance == null) {
            instance = new GetStorageUtil();
        }
        return instance;
    }


    /*
     * 判断是否有sdcard
     *
     * @return
     */
    public boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }


    public String getpath_reflect(Context mContext) {
        String extSdCard = "";
        StorageManager sm = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        // 获取sdcard的路径：外置和内置
        try {
            String[] paths = (String[]) sm.getClass().getMethod("getVolumePaths").invoke(sm);
            String esd = Environment.getExternalStorageDirectory().getPath();
            for (int i = 0; i < paths.length; i++) {
                if (paths[i].equals(esd)) {
                    continue;
                }
                File sdFile = new File(paths[i]);
                if (sdFile.canWrite()) {
                    extSdCard = paths[i];
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return extSdCard;
    }


    /**
     * 获得SD卡总大小
     *
     * @return
     */
    public long getSDTotalSize(String sdPath) {
        try {
            StatFs stat = new StatFs(sdPath);
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            long remainSize = blockSize * totalBlocks;
            return remainSize;
        } catch (Exception e) {
            return 0;
        }
        //        return FormetFileSize(blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public long getSDAvailableSize(String sdPath) {
        try {
            StatFs stat = new StatFs(sdPath);
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            long remainSize = blockSize * availableBlocks;
            return remainSize;
        } catch (Exception e) {
            return 0;
        }
        //        return FormetFileSize(blockSize * availableBlocks);
    }

    /**
     * 获得机身内存总大小
     *
     * @return
     */
    public long getRomTotalSize() {
//		File path = Environment.getDataDirectory();//手机内存
        File path = Environment.getExternalStorageDirectory(); //手机内置sd卡
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long remainSize = blockSize * totalBlocks;
        return remainSize;
        //        return FormetFileSize(blockSize * totalBlocks);
    }

    /**
     * 获得机身可用内存
     *
     * @return
     */
    public long getRomAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        long remainSize = blockSize * availableBlocks;
        return remainSize;
        //        return FormetFileSize(blockSize * availableBlocks);
    }

    /**
     * 判断当前存储的位置剩余的大小是否允许下载
     */
    public boolean isDownload(Context tContext) {
        long remainSize;
        SharedPreferences savePreferences = AppContext.getInstance().getSharedPreferences("save_path", 0);
        if (savePreferences.getString("store", "phone").equals("phone")) {
            remainSize = GetStorageUtil.getInstance().getRomAvailableSize();//获得机身剩余大小
        } else {
            remainSize = GetStorageUtil.getInstance().getSDAvailableSize(GetStorageUtil.getInstance().getpath_reflect(tContext));//获得sd剩余大小
        }

        long tSize = 200 * 1024 * 1024;
        if (tSize > remainSize) {
            return false;
        } else {
            return true;
        }
    }


    //	/*** 转换文件大小单位(b/kb/mb/gb) ***/
    //	public String FormetFileSize(long fileS) {// 转换文件大小
    //		if(fileS == 0){
    //			return "0M";
    //		}
    //		DecimalFormat df = new DecimalFormat("#.00");
    //		String fileSizeString = "";
    //		if (fileS < 1024) {
    //			fileSizeString = df.format((double) fileS) + "B";
    //		} else if (fileS < 1048576) {
    //			fileSizeString = df.format((double) fileS / 1024) + "K";
    //		} else if (fileS < 1073741824) {
    //			fileSizeString = df.format((double) fileS / 1048576) + "M";
    //		} else {
    //			fileSizeString = df.format((double) fileS / 1073741824) + "G";
    //		}
    //		return fileSizeString;
    //	}

}
