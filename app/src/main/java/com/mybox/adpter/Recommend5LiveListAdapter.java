
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
import com.mybox.utils.Logs;

import java.util.List;


/**
 * 描述 CBoxV6.1
 * @Author: 作者  huaiqiang
 * @Version: 版本  1.0  2016/9/22
 */
public class Recommend5LiveListAdapter extends MyBaseAdapter {
    private static final String TAG = "Recommend5LiveListAdapter";
    private Context context;
    private FinalBitmap fb;
    private LiveChannelItemListener mListener;

    public Recommend5LiveListAdapter(Context context, List items) {
        this.items = items;
        this.context = context;
        fb = FinalBitmap.create(context);
    }

    public void setListener(LiveChannelItemListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 1、宫格导航入口，大于5条小于10条，显示一行，大于10条，显示为两行
     */
    @Override
    public int getCount() {
        if (items == null) {
            return 0;
        }
        else if (items.size() > 10) {
            return 10;
        } else if (items.size() < 10 && items.size() > 5) {
            return 5;
        }
        else {
            return items.size();
        }

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        GridItemViewHolder gridItemHolder;
        if (convertView == null) {
            gridItemHolder = new GridItemViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.recommend_5_gridview_item, null);
            gridItemHolder.textView = (TextView) convertView.findViewById(R.id.guozi_label);
            gridItemHolder.imageView = (ImageView) convertView.findViewById(R.id.guozi_ivPic);
            gridItemHolder.mImageViewContent = convertView.findViewById(R.id.mliving_item_layout);
            convertView.setTag(gridItemHolder);
        } else {
            gridItemHolder = (GridItemViewHolder) convertView.getTag();
        }

        if (items.get(position) != null) {
            try {
                if (items.get(position) instanceof RecommendedHomeBean.DataEntity.Interactlive1Bean) {
                    RecommendedHomeBean.DataEntity.Interactlive1Bean bean = (RecommendedHomeBean.DataEntity.Interactlive1Bean) items.get(position);

                    fb.display(gridItemHolder.imageView, bean.getImgUrl());
                    gridItemHolder.textView.setText(bean.getTitle());
                }
            } catch (Exception e) {
                Logs.e(TAG, e.toString());
            }
        }
        // 9宫格单击事件
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


    public static class GridItemViewHolder {
        //标题和副标题
        public TextView title;
        public TextView type;
        public ImageView imageView;
        public TextView textView;
        View mImageViewContent;

    }
}
