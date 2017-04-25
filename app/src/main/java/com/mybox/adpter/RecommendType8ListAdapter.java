package com.mybox.adpter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mybox.AppContextLike;
import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;

import java.util.List;

public class RecommendType8ListAdapter extends MyBaseAdapter {

    private Context mContext;
    private FinalBitmap fb;
    private boolean isThree;
    //左图右文  不同type样式不一
    private int type;

    public RecommendType8ListAdapter(Context mContext, List normalLiveList) {
        this.items = normalLiveList;
        this.mContext = mContext;
        fb = FinalBitmap.create(mContext);
    }

    public RecommendType8ListAdapter(Context mContext, List normalLiveList, boolean isThree) {
        this.items = normalLiveList;
        this.mContext = mContext;
        fb = FinalBitmap.create(mContext);
        this.isThree = isThree;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        try {
            Glide.get(AppContextLike.getInstance()).clearMemory();
            Glide.get(mContext).clearMemory();
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.templatetype_8_item, null, false);
                holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_pic);
                holder.mTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.mTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.mTvTag = (TextView) convertView.findViewById(R.id.tv_tag);

                holder.line = convertView.findViewById(R.id.type_8_bottom_line);
                holder.mImageViewContent = (RelativeLayout) convertView.findViewById(R.id.imageview_content);
                holder.mtimeContent = (LinearLayout) convertView.findViewById(R.id.time_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            /**
             * 导致图片上下间距不一致（后期图片大小固定了）
             */
            if (type == 3) {
                // 瀑布流，设置了出现距左边距偏大
//                FitScreenUtil.setParams(holder.mImageViewContent, 3169);
            } else if (type == 7) {
//                FitScreenUtil.setParams(holder.mImageViewContent, 343);
            } else {
//                FitScreenUtil.setParams(holder.mImageViewContent, 71);
            }

            if (position >= getCount() - 1) {
                holder.line.setVisibility(View.GONE);
            } else {
                holder.line.setVisibility(View.VISIBLE);
            }

            if (items.get(position) instanceof RecommendHomeColumnListInfo.DataEntity.ItemListEntity) {
                String imgUrl = ((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getImgUrl();

                String title = ((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getTitle();

                String vtype = ((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getVtype();
                String video_length = ((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getVideo_length();
                if (title != null) {
                    holder.mTitle.setText(title);
                }
                if (imgUrl != null) {
                    fb.display(holder.mImageView, imgUrl);//大图
                }

                //时长怎么取:单视频取时长
                if (!TextUtils.isEmpty(vtype) && vtype.equals("1")) {
                    if (!TextUtils.isEmpty(video_length)) {
                        holder.mtimeContent.setVisibility(View.VISIBLE);
                        holder.mTime.setText(video_length);
                    }
                } else {
                    holder.mtimeContent.setVisibility(View.GONE);
                }

                String cornerStr = ((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getCornerStr();
                String cornerColour = ((RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(position)).getCornerColour();
                if (!TextUtils.isEmpty(cornerStr) ) {
                    holder.mTvTag.setVisibility(View.VISIBLE);

                    int strokeColor = Color.RED;
                    if (!TextUtils.isEmpty(cornerColour)) {
                        try {
                            strokeColor = Color.parseColor(cornerColour);//边框颜色
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    int strokeWidth = 2; // 3dp 边框宽度
                    int roundRadius = 2; // 8dp 圆角半径

                    GradientDrawable gd = new GradientDrawable();
                    gd.setCornerRadius(roundRadius);
                    gd.setStroke(strokeWidth, strokeColor);
                    gd.setColor(Color.TRANSPARENT);

                    holder.mTvTag.setBackgroundDrawable(gd);

                    holder.mTvTag.setText(" " + cornerStr + " ");
                    holder.mTvTag.setTextColor(strokeColor);


                } else {
                    holder.mTvTag.setVisibility(View.GONE);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private LiveChannelItemListener mListener;

    public void setListener(LiveChannelItemListener mListener) {
        this.mListener = mListener;
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTvTag;
        TextView mTitle;
        TextView mTime;
        RelativeLayout mImageViewContent;
        LinearLayout mtimeContent;
        View line;
    }

    public void setType(int type) {
        this.type = type;
    }
}