package com.mybox.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.model.RecommendGuessYouLove;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.okgo.R;
import com.mybox.utils.FinalBitmap;

import java.util.List;


public class RecommendType4ListAdapter extends MyBaseAdapter {

    private Context mContext;
    private FinalBitmap fb;
    private boolean isThree;
    //左图右文  不同type样式不一
    private int type;

    public RecommendType4ListAdapter(Context mContext, List normalLiveList) {
        this(mContext, normalLiveList, false);
    }

    public RecommendType4ListAdapter(Context mContext, List normalLiveList, boolean isThree) {
        this.items = normalLiveList;
        this.mContext = mContext;
        fb = FinalBitmap.create(mContext);
        this.isThree = isThree;
    }

    @Override
    public int getCount() {
        if (isThree && items.size() >= 5) {
            return items == null ? 0 : 5;
        } else {
            return items == null ? 0 : items.size();
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.templatetype_4_item, null, false);
            holder.mImageView = (ImageView) convertView
                    .findViewById(R.id.ivPic);
            holder.mNumTexxtView = (TextView) convertView.findViewById(R.id.iv_num);
            holder.mTitleTextView = (TextView) convertView.findViewById(R.id.label_title);
            holder.mContentTextView = (TextView) convertView.findViewById(R.id.label_content);
            holder.line = convertView.findViewById(R.id.type_4_bottom_line);
            holder.mImageViewContent = (RelativeLayout) convertView.findViewById(R.id.imageview_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /**
         * 导致图片上下间距不一致（后期图片大小固定了）
         */
//        FitScreenUtil.setParams(holder.mImageView, 7);
        if (type == 3) {
//            FitScreenUtil.setParams(holder.mImageViewContent, 3169);
        } else if (type == 7) {
//            FitScreenUtil.setParams(holder.mImageViewContent, 343);
        } else {
//            FitScreenUtil.setParams(holder.mImageViewContent, 71);
        }

        if (position >= getCount() - 1) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }

        if (isThree) {
            holder.mNumTexxtView.setVisibility(View.VISIBLE);
            if (position <= 2) {
                holder.mNumTexxtView.setBackgroundColor(mContext.getResources().getColor(R.color.home_item_item_bg1));
            } else {
                holder.mNumTexxtView.setBackgroundColor(mContext.getResources().getColor(R.color.home_item_item_bg2));
            }
            holder.mNumTexxtView.setText(" " + (position + 1) + " ");
        } else {
            holder.mNumTexxtView.setVisibility(View.GONE);
        }
        if (items.get(position) instanceof RecommendHomeColumnListInfo.DataEntity.ItemListEntity) {
            fb.display(holder.mImageView, ((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getImgUrl());
            holder.mTitleTextView.setText(((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getTitle());
            holder.mContentTextView.setText(((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getBrief());
        } else if (items.get(position) instanceof RecommendGuessYouLove.NewResEntity) {
            fb.display(holder.mImageView, ((RecommendGuessYouLove.NewResEntity) items.get(position)).getPicUrl());
            holder.mTitleTextView.setText(((RecommendGuessYouLove.NewResEntity) items.get(position)).getTitle());
            holder.mContentTextView.setText(((RecommendGuessYouLove.NewResEntity) items.get(position)).getSubTitle());
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

    private LiveChannelItemListener mListener;

    public void setListener(LiveChannelItemListener mListener) {
        this.mListener = mListener;
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mNumTexxtView;
        TextView mTitleTextView;
        TextView mContentTextView;
        RelativeLayout mImageViewContent;
        View line;
    }

    public void setType(int type) {
        this.type = type;
    }
}