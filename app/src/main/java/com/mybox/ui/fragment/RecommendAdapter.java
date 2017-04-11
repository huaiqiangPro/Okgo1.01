package com.mybox.ui.fragment;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mybox.AppContext;
import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;

import java.util.List;



/**
 * Created by ykglove on 2015/9/23.
 */
public class RecommendAdapter extends MyBaseAdapter {
    private static final String TAG = "RecommendAdapter";
    private Context mContext;
    private FinalBitmap fb;
    private LayoutInflater inflater;
    private SparseArray<RecommendViewHolder> convertViews = new SparseArray<>();
    private SparseArray<LiveChannelItemListener> mListeners = new SparseArray<>();
    private int ADposition = 0;
    //是否显示双标题

    public RecommendAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(AppContext.getInstance());
        fb = FinalBitmap.create(mContext);
    }
    public void setListeners(SparseArray mListeners) {
        this.mListeners = mListeners;
    }

    public void setConvertViews(SparseArray<RecommendViewHolder> convertViews) {this.convertViews = convertViews;}

    @Override
    public void setItems(List items) {
        super.setItems(items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Glide.get(AppContext.getInstance()).clearMemory();
        Glide.get(mContext).clearMemory();
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.recommendnew_recyclerview_item, null);
            holder.mContentView = (LinearLayout) convertView.findViewById(R.id.content_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mContentView.removeAllViews();
        if (convertViews.get(position) != null) {
            if (convertViews.get(position).convertView.getParent() != null) {
                ((LinearLayout) convertViews.get(position).convertView.getParent()).removeView((convertViews.get(position).convertView));
            }
            holder.mContentView.addView(convertViews.get(position).convertView);
        }
        if (convertView == null) {
            convertView = new View(mContext);
        }
        return convertView;
    }

    static class ViewHolder {
        LinearLayout mContentView;
    }

}
