package com.mybox.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;
import com.mybox.utils.FitScreenUtil;
import com.mybox.utils.Logs;

import java.util.List;


/**
 * (RTFSC)
 * 描述 CBoxV6.1
 *
 * @Author: 作者  huaiqiang
 * @Version: 版本  1.0  2016/5/18
 */
public class Recommend4LiveListAdapter extends MyBaseAdapter {
    private static final String TAG = "GridViewLiveAdapter";
    private Context context;
    private FinalBitmap fb;
    private LiveChannelItemListener mListener;

    public Recommend4LiveListAdapter(Context context, List items) {
        this.items = items;
        this.context = context;
        fb = FinalBitmap.create(context);
    }

    public void setListener(LiveChannelItemListener mListener) {
        this.mListener = mListener;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        GridItemViewHolder gridItemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_living_item_gridview_item, null);
            gridItemHolder = new GridItemViewHolder();
            gridItemHolder.updateTitleTextView = (TextView) convertView.findViewById(R.id.guozi_label);
            gridItemHolder.imageView = (ImageView) convertView.findViewById(R.id.guozi_ivPic);
            gridItemHolder.line = convertView.findViewById(R.id.liveing_item_line);
            gridItemHolder.mImageViewContent = convertView.findViewById(R.id.mliving_item_layout);
            convertView.setTag(gridItemHolder);
        } else {
            gridItemHolder = (GridItemViewHolder) convertView.getTag();
        }
        if (position >= getCount()-1) {
            gridItemHolder.line.setVisibility(View.GONE);
        } else {
            gridItemHolder.line.setVisibility(View.VISIBLE);
        }
        if (items.get(position) != null) {
            try {
                if (items.get(position) instanceof RecommendedHomeBean.DataEntity.LiveCategoryListEntity) {
                    RecommendedHomeBean.DataEntity.LiveCategoryListEntity bean = (RecommendedHomeBean.DataEntity.LiveCategoryListEntity) items.get(position);
                    FitScreenUtil.setParams(gridItemHolder.mImageViewContent, 6);
                    fb.display(gridItemHolder.imageView, bean.getImgUrl());
                    gridItemHolder.updateTitleTextView.setText(bean.getTitle());

                }
            } catch (Exception e) {
                Logs.e(TAG, e.toString());
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(null, null, position, 0);
                }
            }
        });
        return convertView;
    }


    // GridItiewHolder
    public static class GridItemViewHolder {

        private ImageView imageView;
        private TextView updateTitleTextView;
        private View mImageViewContent;
        private View line;
    }
}
