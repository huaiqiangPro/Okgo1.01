package com.mybox.adpter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.model.RecommendGuessYouLove;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.okgo.R;
import com.mybox.utils.FinalBitmap;
import com.mybox.utils.FitScreenUtil;
import com.mybox.utils.Logs;

import java.util.List;

/********************
 * 作者：malus
 * 日期：16/1/4
 * 时间：上午11:00
 * 注释：首页（推荐页）横向滑动的适配器
 ********************/
public class RecommendHorizontalAdapter extends MyBaseAdapter {
    private static final String TAG = "RecommendHorizontalAdapter";
    private Context context;
    private String Type;
    //是否双标题
    private String isDoubleTitle;
    private FinalBitmap fb;

    public RecommendHorizontalAdapter(Context context, List items) {
        this.items = items;
        this.context = context;
        fb = FinalBitmap.create(context);
    }

    public String getIsDoubleTitle() {
        return isDoubleTitle;
    }

    @Override
    public int getCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    public void setIsDoubleTitle(String isDoubleTitle) {
        this.isDoubleTitle = isDoubleTitle;
    }


    public RecommendHorizontalAdapter(Context context, List items, String Type) {
        this.items = items;
        this.context = context;
        this.Type = Type;
        fb = FinalBitmap.create(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        GridItemViewHolder gridItemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.home_listview_item_gridview_item, null);
            gridItemHolder = new GridItemViewHolder();
            gridItemHolder.updateTitleTextView = (TextView) convertView
                    .findViewById(R.id.guozi_tvUpdateTitle1);

            gridItemHolder.title = (TextView) convertView
                    .findViewById(R.id.guozi_label);
            gridItemHolder.doubleTitle = (TextView) convertView.findViewById(R.id.guozi_label_double);

            gridItemHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.guozi_ivPic);
            gridItemHolder.imageViewBg = (ImageView) convertView
                    .findViewById(R.id.guozi_ivPic2);
            gridItemHolder.type = (TextView) convertView.findViewById(R.id.classify_type);
            gridItemHolder.tvLabel = (TextView) convertView.findViewById(R.id.guozi_tvlabel);
            gridItemHolder.mImageViewContent = convertView.findViewById(R.id.guozi_rlBigVodnew);
            convertView.setTag(gridItemHolder);
        } else {
            gridItemHolder = (GridItemViewHolder) convertView.getTag();
            if (gridItemHolder == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.home_listview_item_gridview_item, null);
                gridItemHolder = new GridItemViewHolder();
                gridItemHolder.updateTitleTextView = (TextView) convertView
                        .findViewById(R.id.guozi_tvUpdateTitle1);
                gridItemHolder.title = (TextView) convertView
                        .findViewById(R.id.guozi_label);
                gridItemHolder.doubleTitle = (TextView) convertView.findViewById(R.id.guozi_label_double);
                gridItemHolder.imageView = (ImageView) convertView
                        .findViewById(R.id.guozi_ivPic);
                gridItemHolder.imageViewBg = (ImageView) convertView
                        .findViewById(R.id.guozi_ivPic2);
                gridItemHolder.type = (TextView) convertView.findViewById(R.id.classify_type);
                gridItemHolder.tvLabel = (TextView) convertView.findViewById(R.id.guozi_tvlabel);
                gridItemHolder.mImageViewContent = convertView.findViewById(R.id.guozi_rlBigVodnew);
                convertView.setTag(gridItemHolder);
            }
        }
        if (items == null) {
            return null;
        }
        final int pos = position % items.size();
        if (pos > 0) {
            convertView.setPadding(20, 0, 0, 0);
        }
        if (items.get(pos) != null) {
            try {
                if (items.get(pos) instanceof RecommendHomeColumnListInfo.DataEntity.ItemListEntity) {
                    RecommendHomeColumnListInfo.DataEntity.ItemListEntity bean = (RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(pos);
                    if (!TextUtils.isEmpty(Type) && "5".equals(Type)) {
                        FitScreenUtil.setParams(gridItemHolder.mImageViewContent, 11);
                        FitScreenUtil.setParams(gridItemHolder.imageView, 11);
                    } else {
                        FitScreenUtil.setParams(gridItemHolder.mImageViewContent, 31);
                        FitScreenUtil.setParams(gridItemHolder.imageView, 31);
                    }

                    if (null != bean.getCornerStr() && !"".equals(bean.getCornerStr()) && null != bean.getCornerColour() && !"".equals(bean.getCornerColour())) {
                        gridItemHolder.type.setVisibility(View.VISIBLE);
                        gridItemHolder.type.setText(bean.getCornerStr());
                        gridItemHolder.type.setBackgroundColor(Color.parseColor(bean.getCornerColour()));
                        //Logs.e("字符串转换颜色", "====>" + Color.parseColor(bean.getCornerColour()));
                    }
                    gridItemHolder.tvLabel.setVisibility(View.GONE);
                    gridItemHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Logs.e(TAG, bean.getImgUrl());
                    fb.display(gridItemHolder.imageView, bean.getImgUrl());

                    if (isDoubleTitle != null && isDoubleTitle.equals("1")) {
                        //是双标题
                        //是双标题，有副标题的话，把副标题显示在标题下面，把vsetType显示在蒙层，如果没有，则相应的，不显示
                        //是双标题，标题单行
                        gridItemHolder.title.setMaxLines(1);
                        //底部标题的情况
                        if (!TextUtils.isEmpty(bean.getBrief())) {
                            //如果有副标题，显示副标题在标题下面
                            gridItemHolder.doubleTitle.setVisibility(View.VISIBLE);
                            gridItemHolder.doubleTitle.setText(bean.getBrief());
                        } else {
                            gridItemHolder.doubleTitle.setVisibility(View.GONE);
                        }

                        //蒙层情况
                        if (!TextUtils.isEmpty(bean.getVsetType())) {
                            //没有副标题，显示vsetType
                            gridItemHolder.updateTitleTextView.setVisibility(View.VISIBLE);
                            gridItemHolder.updateTitleTextView.setGravity(Gravity.RIGHT);
                            gridItemHolder.updateTitleTextView.setText(bean.getVsetType());
                        } else {
                            //没有vsetType，不显示蒙层
                            gridItemHolder.updateTitleTextView.setVisibility(View.GONE);
                        }

                    } else {
                        //不是双标题
                        //不是双标题，标题双行
                        gridItemHolder.title.setLines(2);
                        gridItemHolder.doubleTitle.setVisibility(View.GONE);
                        //非双标题状态，只显示蒙层，如果有副标题，把副标题显示在蒙层，如果没有，把vsetType显示在蒙层，否则都不显示
                        if (!TextUtils.isEmpty(bean.getBrief())) {
                            //如果有副标题，显示副标题
                            gridItemHolder.updateTitleTextView.setVisibility(View.VISIBLE);
                            gridItemHolder.updateTitleTextView.setGravity(Gravity.LEFT);
                            gridItemHolder.updateTitleTextView.setText(bean.getBrief());
                        } else if (!TextUtils.isEmpty(bean.getVsetType())) {
                            //没有副标题，显示vsetType
                            gridItemHolder.updateTitleTextView.setVisibility(View.VISIBLE);
                            gridItemHolder.updateTitleTextView.setGravity(Gravity.RIGHT);
                            gridItemHolder.updateTitleTextView.setText(bean.getVsetType());
                        } else {
                            //没有副标题，没有vsetType。啥都不显示
                            gridItemHolder.updateTitleTextView.setVisibility(View.GONE);
                        }
                    }
                    gridItemHolder.title.setVisibility(View.VISIBLE);
                    gridItemHolder.title.setText(bean.getTitle());
                    gridItemHolder.title.setTextColor(Color.BLACK);
                } else if (items.get(pos) instanceof RecommendGuessYouLove.NewResEntity) {
                    RecommendGuessYouLove.NewResEntity bean = (RecommendGuessYouLove.NewResEntity) items.get(pos);
//                    FitScreenUtil.setParams(gridItemHolder.mImageViewContent, 2519);
//                    FitScreenUtil.setParams(gridItemHolder.imageView, 2519);

                    gridItemHolder.tvLabel.setVisibility(View.GONE);
                    fb.display(gridItemHolder.imageView, bean.getPicUrl());

                    gridItemHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);


                    gridItemHolder.title.setVisibility(View.VISIBLE);
                    gridItemHolder.title.setText(bean.getTitle());
                    gridItemHolder.title.setLines(2);
                    gridItemHolder.title.setTextColor(Color.BLACK);

                    //gridItemHolder.doubleTitle.setText(bean.getSubTitle());
                    gridItemHolder.updateTitleTextView.setVisibility(View.GONE);//副标题蒙层


                }
            } catch (Exception e) {
                Logs.e(TAG, e.toString());
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(null, null, pos, 0);
                }
            }
        });
        return convertView;
    }

    private LiveChannelItemListener mListener;

    public void setListener(LiveChannelItemListener mListener) {
        this.mListener = mListener;
    }

    // GridItiewHolder
    public static class GridItemViewHolder {
        //标题和副标题
        public TextView title;
        public TextView doubleTitle;
        public TextView tvLabel;
        public TextView type;
        public ImageView imageView;
        public ImageView imageViewBg;
        public TextView updateTitleTextView;
        View mImageViewContent;
    }
}
