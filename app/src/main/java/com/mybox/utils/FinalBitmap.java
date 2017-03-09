/**
 * Copyright (c) 2012-2013, Michael Yang 杨福海 (www.yangfuhai.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mybox.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mybox.okgo.R;


public class FinalBitmap {


    private Context mContext;
    private Fragment mFragment;

    private FinalBitmap(Context context) {
        this.mContext = context;
        mFragment = null;
    }


    private FinalBitmap(Fragment fragment) {
        mFragment = fragment;
        mContext = null;
    }

    /**
     * 创建finalbitmap
     *
     * @param ctx
     * @return
     */
    public static FinalBitmap create(Context ctx) {
        return new FinalBitmap(ctx);
    }


    public static synchronized FinalBitmap create(Fragment fragment) {
        return new FinalBitmap(fragment);
    }

    private RequestManager requestManager() {
        RequestManager requestManager = mFragment != null ? Glide.with(mFragment) : Glide.with(mContext);
        return requestManager;
    }

    /**
     * // 缓存策略。
     * // DiskCacheStrategy.SOURCE：缓存原始数据，
     * // DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据，
     * // DiskCacheStrategy.NONE：什么都不缓存，
     * // DiskCacheStrategy.ALL：缓存SOURC和RESULT。
     * // 默认采用DiskCacheStrategy.RESULT策略，
     * // 对于download only操作要使用DiskCacheStrategy.SOURCE。
     *
     * @param imageView
     * @param uri
     */
    public void display(ImageView imageView, String uri) {
        RequestManager requestManager = requestManager();

        requestManager.load(uri)
                .skipMemoryCache(false)//是否将图片放到内存中
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.default_img_1)
                .error(R.drawable.default_img_1)
                .crossFade()
                .into(imageView);

    }


    public void display1(ImageView imageView, String uri) {

        RequestManager requestManager = requestManager();

        requestManager.load(uri)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.default_img_1)
                .error(R.drawable.default_img_1)
                .crossFade()
                .into(imageView);

    }


    public void display2(final ImageView imageView, final String uri) {

        final RequestManager requestManager = requestManager();

        requestManager
                .load(uri).asBitmap()
                .placeholder(R.drawable.default_img_1)
                .error(R.drawable.default_img_1)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        int imageWidth = resource.getWidth();
                        int imageHeight = resource.getHeight();

                        if (imageWidth > imageHeight) {

                            requestManager.load(uri)
                                    .skipMemoryCache(true)
                                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                    .placeholder(R.drawable.default_img_1)
                                    .error(R.drawable.default_img_1)
                                    .crossFade()
                                    .into(imageView);

                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        } else {

                            requestManager.load(uri)
                                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                    .skipMemoryCache(true)
                                    .override(500, 700)
                                    .fitCenter()
                                    .placeholder(R.drawable.default_img_1)
                                    .error(R.drawable.default_img_1)
                                    .crossFade()
                                    .into(imageView);

                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        }
                    }
                });
    }


    public void display3(ImageView imageView, String uri) {

        RequestManager requestManager = requestManager();

        requestManager.load(uri)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(R.drawable.default_img_1)
                .crossFade()
                .into(imageView);

    }


    public void display(ImageView imageView, String uri, boolean hasReplaceError) {
        requestManager().load(uri).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }


    public void display(ImageView imageView, int uri) {
        requestManager().load(uri).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }
}
