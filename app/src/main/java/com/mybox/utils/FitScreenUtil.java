package com.mybox.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mybox.AppContextLike;


public class FitScreenUtil {
    protected static int mScrrenW;
    protected static int mScrrenH;

    public static void setParams(View view, int temp, ViewGroup.LayoutParams params2) {
        int[] mSreenSize = UIUtils.getScrren(AppContextLike.getInstance());
        mScrrenH = mSreenSize[1];
        mScrrenW = mSreenSize[0];
        ViewGroup.LayoutParams params = view.getLayoutParams();
        switch (temp) {
            case 1:// 模板一头部片
                // 宽916
                // 高515
                params.width = mScrrenW - ((LinearLayout.LayoutParams) params2).leftMargin - ((LinearLayout.LayoutParams) params2).rightMargin - view.getPaddingLeft() - view.getPaddingRight();
                params.height = mScrrenW * 515 / 916;
                break;
            case 2:// 模板一头部片
                // 宽916
                // 高515
                params.width = mScrrenW - ((LinearLayout.LayoutParams) params2).leftMargin - ((LinearLayout.LayoutParams) params2).rightMargin - view.getPaddingLeft();
//                params.height = mScrrenW * 515 / 916;
                break;
        }
    }

    /**
     * 适配
     *
     * @param view
     * @param temp
     */
    public static void setParams(View view, int temp) {
        int[] mSreenSize = UIUtils.getScrren(AppContextLike.getInstance());
        mScrrenH = mSreenSize[1];
        mScrrenW = mSreenSize[0];
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        switch (temp) {
            // 首页滑动大图按16:9显示
            case 0:// Banner 16:9
                // 宽916
                // 高515
                params.width = mScrrenW;// 屏幕宽度
                params.height = mScrrenW * 9 / 16;// 屏幕高度
                break;
            case 1:// 模板一头部片
                // 宽916
                // 高515
                params.width = mScrrenW - 50 - view.getPaddingLeft() - view.getPaddingRight();
                params.height = mScrrenW * 515 / 916;
                break;
            case 2:// 模板2右侧大图片
                // 宽448
                // 高553
                params.width = mScrrenW / 2;
                params.height = (params.width * 553) / 448;
                Logs.e("模板2", "模板2" + params.height + "*" + params.width);
                break;
            case 3:// 两列数据
                // 宽330
                // 高186
                params.width = mScrrenW / 2 - view.getPaddingLeft() - view.getPaddingRight() - 40;
                params.height = mScrrenW / 2 * 186 / 330;
                break;
            case 4:// 模板三（三列数据）
                // 宽212
                // 高268
                params.width = mScrrenW / 3;
                params.height = mScrrenW / 3 * 268 / 212;
                break;
            case 5:// 模板三（三列数据）
                // 宽680
                // 高380
                params.width = mScrrenW;
                params.height = mScrrenW * 380 / 680;
                break;
            case 6:
                // 宽330
                // 高186
                params.width = mScrrenW / 4;
                params.height = mScrrenW * 260 / 1440;
                break;
            case 7:// 两列数据
                // 宽330
                // 高186
                params.width = (int) (mScrrenW / 3);
                params.height = (int) (mScrrenW / 3 * 135 / 240);
                break;
            case 8:// 大图4:3
                params.width = mScrrenW - view.getLeft() - view.getRight();

                params.height = (int) ((mScrrenW - view.getLeft() - view.getRight()) / 4 * 3 - view.getTop() - view.getBottom());
                break;
            case 9:// 模板一头部片
                // 宽916
                // 高515
                params.width = mScrrenW;
                params.height = mScrrenW * 266 / 720;
                break;
            case 10:// 模板一头部片
                // 宽916
                // 高515
                params.width = params.height;
                break;
            case 11:// 模板三（三列数据）
                // 宽212  106   53
                // 高268  134   67
                params.width = mScrrenW * 224 / 750;
                params.height = mScrrenH * 280 / 1334;
                break;
            case 12:// 模板三（三列数据）
                // 宽212
                // 高268
                params.width = mScrrenW;
                params.height = mScrrenH * 150 / 1334;
                break;
            case 13:// 广告横
                // 宽212
                // 高268
                params.width = mScrrenW / 2;
                params.height = 100;
            case 14:// 广告树
                // 宽212
                // 高268
                params.width = mScrrenW / 2;
                params.height = mScrrenW / 2 * 5 / 4;
            case 15:// banner
                // 宽720
                // 高112
                params.width = mScrrenW;
                params.height = mScrrenW * 112 / 720;
                break;
            case 16:
                params.width = mScrrenW * 240 / 750;
                params.height = mScrrenW * 240 * 8 / 11 / 750;
                break;
            case 17:
                params.width = mScrrenW;
                params.height = mScrrenH / 5;
                break;
            case 18:
                //宽180
                //高100
                params.width = mScrrenW * 170 / 750;
                params.height = mScrrenW * 45 / 750;
                break;
            case 19:
                //宽345
                //高260
                params.width = mScrrenW * 345 / 750;
                params.height = mScrrenW * 260 / 750;
                break;
            case 20:
                params.width = mScrrenW * 240 / 750;
                params.height = mScrrenW * 180 / 750;
                break;
            case 2519:
                //宽345
                //高260
                params.width = mScrrenW * 276 / 750;
                params.height = mScrrenW * 208 / 750;
                break;

            case 32:
                // 宽344
                // 高194
                params.width = mScrrenW * 345 / 750;
                params.height = mScrrenW * 345 / 750;
                break;
            case 33://两列数据4:3 和1000一样
                // 宽330
                // 高186
                params.width = (mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2) - SizeUtils.dip2px(AppContextLike.getInstance(), 6)) / 2;
                params.height = params.width * 3 / 4;
                break;
            case 35:// （2 列数据）
                // 宽324
                // 高576
                params.height = view.getMeasuredWidth() * 324 / 576;
                break;
            case 36:// 3 列数据
                // 宽330
                // 高186
                params.width = (mScrrenW) / 3;
                params.height = (mScrrenW) / 3 * 280 / 384;
                break;
            case 333:// 两列数据
                // 宽330
                // 高186
                params.width = mScrrenW / 2 - view.getPaddingLeft() - view.getPaddingRight() - 40;
                break;
            case 44:// 模板三（三列数据）
                // 宽212
                // 高268
                params.width = mScrrenW / 3 - view.getPaddingLeft() - view.getPaddingRight() - 40;
                params.height = mScrrenW / 3 * 9 / 8;
                break;
            case 444:// 模板三（三列数据）
                // 宽212
                // 高268
                params.width = mScrrenW / 3 - view.getPaddingLeft() - view.getPaddingRight() - 40;
                break;
            case 45:// 模板三（三列数据）
                // 宽212
                // 高268
                params.width = mScrrenW;
                params.height = mScrrenW;
                break;
            case 160:// 模板三（三列数据）
                // 宽212
                // 高268
                params.width = mScrrenW * 2 / 7;
                params.height = mScrrenW / 3 * 186 / 330;
                break;
            case 71:// 左图右文  16/9
                // 宽438
                // 高246
                params.width = (int) (mScrrenW / 3);
                params.height = (int) (mScrrenW / 3 / 16 * 9);
                break;

            case 72:// 左图右文  4/3
                // 宽438
                // 高
                params.width = (int) (mScrrenW / 3);
                params.height = (int) (mScrrenW / 3 / 4 * 3);
                break;
            case 31:
                // 宽344
                // 高194
                params.width = mScrrenW * 345 / 750;
                params.height = mScrrenH * 194 / 1334;
                break;
            case 169:// 16:9 宽度占满屏幕
                // 宽212
                // 高268
                params.width = mScrrenW;
                params.height = mScrrenW * 9 / 16;
                break;
            case 22:// 两列数据
                // 宽500
                // 高376
                params.width = mScrrenW / 2;
                params.height = mScrrenW / 2 * 376 / 500;
                break;
            case 23:// 两列数据
                // 宽324
                // 高406
                params.width = mScrrenW / 3;
                params.height = mScrrenW / 3 * 406 / 324;
                break;
            case 24:// 两列数据
                // 宽500
                // 高376
                params.width = mScrrenW;
                params.height = mScrrenW * 168 / 1080;
                break;
            case 343:
                //3分之一宽度
                // 宽：高  4:3
                params.width = (int) (mScrrenW / 3);
                params.height = (int) (mScrrenW / 4);
                break;
            case 3169:
                //3分之一宽度
                // 宽：高  16:9
                params.width = (int) (mScrrenW / 3);
                params.height = (int) (mScrrenW * 3 / 16);
                break;
            case 99:
                //3分之一宽度
                // 宽：高  1:1
                params.width = (int) (mScrrenW / 3);
                params.height = (int) (mScrrenW / 3);
                break;
            case 100:
                params.width = (int) (mScrrenW);
                params.height = (int) (mScrrenW / 3);
                break;
            case 95:
                //普通直播
                params.width = (int) (mScrrenW);
                params.height = (int) (mScrrenW / 8.6);
                break;
            case 43:
                //宽345
                //高260
                params.width = (mScrrenW - 40) * 276 / 750;
                params.height = (mScrrenW - 40) * 208 / 750;
                break;
            case 201:
                //实况直播单张横图
                //宽1248
                //高702

                params.height = (int) ((mScrrenW - 192) * 702 / 1248.0);
                params.width = (int) (params.height * 1248.0 / 702);
                break;
            case 202:
                //实况直播2张：竖图
                //宽：高＝4:5
                params.width = (mScrrenW - 228) / 2;
                params.height = (int) ((mScrrenW - 228) / 1.6);
                break;
            case 203:
                //实况直播2张：横图
                params.width = (mScrrenW - 228) / 2;
                params.height = (int) ((mScrrenW - 228) / 2.0 * 216 / 384.0);
                break;
            case 204:
                //实况直播3张横图
                //宽1248
                //高702
                params.width = (mScrrenW - 288) / 3;
                params.height = (int) ((mScrrenW - 288) / 3.0 * 216 / 384.0);
                break;
            case 205:
                //实况直播3张：竖图
                //宽：高＝4:5
                params.width = (mScrrenW - 288) / 3;
                params.height = (int) ((mScrrenW - 288) / 2.4);
                break;

            // 专题gridview双列，每个图4:3显示,专题9模板，两列数据4:3
            case 1000:
                //  距左右9dp,中间2个图6dp。 将dp转换为px工具
                params.width = (mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2) - SizeUtils.dip2px(AppContextLike.getInstance(), 6)) / 2;
                params.height = params.width * 3 / 4;

                break;
            case 1001:
                // 推荐首页，3列 4:5图片
                // 36为距两边的距离，24为中间两个图的距离   二列
                params.width = (mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2) - (SizeUtils.dip2px(AppContextLike.getInstance(), 6) * 2)) / 2;
                params.height = params.width * 4 / 5;
                break;

            // 16;9 直播-王牌栏目
            case 1002:
                params.width = (mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2) - SizeUtils.dip2px(AppContextLike.getInstance(), 6)) / 2;
                params.height = params.width * 9 / 16;
                break;

            case 1003:
                // 电视剧3列4:5
                // 36为距两边的距离，24为中间两个图的距离   三列
                params.width = (mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2) - (SizeUtils.dip2px(AppContextLike.getInstance(), 6) * 2)) / 3;
                params.height = params.width * 5 / 4;
                break;


            // 16;9 大图
            case 1004:
                params.width = (mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2));
                params.height = params.width * 9 / 16;
                break;

            // 16;9 瀑布流
            case 1005:
                params.width = (mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2));
                params.height = params.width * 9 / 16;
                break;

            case 91:
                params.width = mScrrenW - (SizeUtils.dip2px(AppContextLike.getInstance(), 9) * 2);
                params.height = params.width * 9 / 16;
                break;
            default:
        }
        view.setLayoutParams(params);
    }

}
