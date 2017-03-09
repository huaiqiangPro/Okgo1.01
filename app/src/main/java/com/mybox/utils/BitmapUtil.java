package com.mybox.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileOutputStream;

public class BitmapUtil {
    /**
     * ���ر���ͼƬ
     *
     * @param
     * @return
     */
    public static Bitmap getBitmapFromSDCard(String path) {
        try {
            if (path != null) {
                return BitmapFactory.decodeFile(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // �����ڲ�ͬ����ֱ�Ӵ���ͼƬ
    public static Bitmap temp;

    // ͼƬ����
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);


        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    /**
     * 图片缩放
     * @param bitmap 对象
     * @param w  最大宽
     * @param h  最大高
     * @return  新Bitmap对象
     */
    public static Bitmap approximateZoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width <= w && height <= h)
            return (bitmap);
        // 计算图片缩放比例
        float scale;
        final float sx = w / (float) width;
        final float sy = h / (float) height;
        if (sx <= sy) {
            scale = sy;
        } else {
            scale = sx;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap bit = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        try {
            if (null != bitmap) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bitmap = null;
        }
        return bit;
    }

    public static void saveBitmapToSDCard(Bitmap bitmap, String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------save success-------------------");
    }

    /**
     * 按照高度的百分比压缩
     *
     * @param srcBitmap
     * @param newHeight
     * @return
     */
    public static Bitmap bitmapZoomByHeight(Bitmap srcBitmap, float newHeight) {
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();

        float scaleHeight = ((float) newHeight) / srcHeight;
        float scaleWidth = scaleHeight;

        return bitmapZoomByScale(srcBitmap, scaleWidth, scaleHeight);

    }

    /**
     * 按照高度的百分比压缩
     *
     * @param drawable
     * @param newHeight
     * @return
     */
    public static Bitmap bitmapZoomByHeight(Drawable drawable, float newHeight) {
        Bitmap srcBitmap = drawableToBitmap(drawable);
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();

        float scaleHeight = ((float) newHeight) / srcHeight;
        float scaleWidth = scaleHeight;

        return bitmapZoomByScale(srcBitmap, scaleWidth, scaleHeight);

    }

    /**
     * 使用长宽缩放比缩放
     *
     * @param srcBitmap
     * @param scaleWidth
     * @param scaleHeight
     * @return
     */
    public static Bitmap bitmapZoomByScale(Bitmap srcBitmap, float scaleWidth, float scaleHeight) {
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcWidth,
                srcHeight, matrix, true);
        if (resizedBitmap != null) {
            srcBitmap = null;
            return resizedBitmap;
        } else {
            return srcBitmap;
        }
    }

    /**
     * Drawable转化为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

}
