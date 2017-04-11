package com.mybox.adpter;

import android.content.Context;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.mybox.imageload.PicassoImageLoad;
import com.mybox.model.News;
import com.mybox.R;
import com.mybox.ui.fragment.Consts;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends CommonRecyclerAdapter<News> {

    private PicassoImageLoad mImageLoad;


    public NewsAdapter(Context context, int layoutResId, List<News> data) {
        super(context, layoutResId, data);
        mImageLoad = new PicassoImageLoad();
    }

    @Override
    public int getLayoutResId(News item, int position) {
        int layoutResId = -1;
        switch (item.getNewsType()) {
            case News.TYPE_NONE_PICTURE:
                layoutResId = R.layout.item_none_picture;
                break;
            case News.TYPE_SINGLE_PICTURE:
                layoutResId = R.layout.item_single_picture;
                break;
            case News.TYPE_MULTIPLE_PICTURE:
                layoutResId = R.layout.item_multiple_picture;
                break;
        }
        return layoutResId;
    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, News item, int position) {
        helper.setImageLoad(mImageLoad);
        switch (item.getNewsType()) {
            case News.TYPE_NONE_PICTURE:
                helper.setText(R.id.item_none_picture_title, item.getTitle())
                        .setText(R.id.item_none_picture_author,
                                String.format(Locale.CHINA, Consts.FORMAT_AUTHOR,
                                        item.getAuthor()))
                        .setText(R.id.item_none_picture_date,
                                Consts.DATE_FORMAT.format(new Date(item.getReleaseTime())))
                        .setText(R.id.item_none_picture_intro, item.getIntro());
                break;
            case News.TYPE_SINGLE_PICTURE:
                helper.setText(R.id.item_single_picture_title, item.getTitle())
                        .setText(R.id.item_single_picture_author,
                                String.format(Locale.CHINA, Consts.FORMAT_AUTHOR,
                                        item.getAuthor()))
                        .setText(R.id.item_single_picture_date,
                                Consts.DATE_FORMAT.format(new Date(item.getReleaseTime())))
                        .setImageUrl(R.id.item_single_picture_cover, item.getCoverUrl());
                break;
            case News.TYPE_MULTIPLE_PICTURE:
                String[] urls = item.getCoverUrl().split(Consts.URL_SEPARATOR);
                helper.setText(R.id.item_multiple_picture_intro, item.getIntro())
                        .setImageUrl(R.id.item_multiple_picture_cover_left, urls[0])
                        .setImageUrl(R.id.item_multiple_picture_cover_right, urls[1]);
                break;
        }
    }
}