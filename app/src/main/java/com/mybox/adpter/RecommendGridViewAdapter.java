package com.mybox.adpter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;
import com.mybox.utils.FitScreenUtil;
import com.mybox.utils.Logs;

import java.util.List;


/**
 * (RTFSC)
 * 描述 CBoxV6.1
 *
 * @Author: 作者  huaiqiang   大图-小图，双排，单排
 * @Version: 版本  1.0  2016/5/18
 */
public class RecommendGridViewAdapter extends MyBaseAdapter {
    private static final String TAG = "GridViewAdapter";
    FinalBitmap fb;
    private Context context;
    private String Type;
    private ViewGroup.LayoutParams params;
    private boolean isType6 = false;
    //是否双标题
    private String isDoubleTitle;
    private LiveChannelItemListener mListener;


    public RecommendGridViewAdapter(Context context, List items) {
        this.items = items;
        this.context = context;
        fb = FinalBitmap.create(context);
    }


    public RecommendGridViewAdapter(Context context, List items, ViewGroup.LayoutParams params) {
        this.items = items;
        this.context = context;
        this.params = params;
        fb = FinalBitmap.create(context);
    }


    public RecommendGridViewAdapter(Context context, List items, String Type) {
        this.items = items;
        this.context = context;
        this.Type = Type;
        fb = FinalBitmap.create(context);
    }

    public String getIsDoubleTitle() {
        return isDoubleTitle;
    }

    public void setIsDoubleTitle(String isDoubleTitle) {
        this.isDoubleTitle = isDoubleTitle;
    }

    public void setIsType6(boolean isType6) {
        this.isType6 = isType6;
    }

    @Override
    public View getView(final int arg0, View convertView, ViewGroup arg2) {
        GridItemViewHolder gridItemHolder = null;
        if (convertView == null) {
            gridItemHolder = new GridItemViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_listview_item_gridview_item, null);
            gridItemHolder.updateTitleTextView = (TextView) convertView.findViewById(R.id.guozi_tvUpdateTitle1);
            gridItemHolder.title = (TextView) convertView.findViewById(R.id.guozi_label);
            gridItemHolder.doubleTitle = (TextView) convertView.findViewById(R.id.guozi_label_double);

            gridItemHolder.imageView = (ImageView) convertView.findViewById(R.id.guozi_ivPic);
            gridItemHolder.imageViewBg = (ImageView) convertView.findViewById(R.id.guozi_ivPic2);
            gridItemHolder.type = (TextView) convertView.findViewById(R.id.classify_type);
            gridItemHolder.tvLabel = (TextView) convertView.findViewById(R.id.guozi_tvlabel);
            gridItemHolder.mImageViewContent = convertView.findViewById(R.id.guozi_rlBigVodnew);
            convertView.setTag(gridItemHolder);
        } else {
            gridItemHolder = (GridItemViewHolder) convertView.getTag();
        }
        if (isType6 && arg0 > 0) {
            convertView.setPadding(20, 0, 0, 0);
        }
        if (items.get(arg0) != null) {
            try {
                if (items.get(arg0) instanceof RecommendHomeColumnListInfo.DataEntity.ItemListEntity) {
                    //小图
                    RecommendHomeColumnListInfo.DataEntity.ItemListEntity bean = (RecommendHomeColumnListInfo.DataEntity.ItemListEntity) items.get(arg0);
                    if (!TextUtils.isEmpty(Type) && "5".equals(Type)) {
                        FitScreenUtil.setParams(gridItemHolder.mImageViewContent, 1003);
                        FitScreenUtil.setParams(gridItemHolder.imageView, 1003);
                    } else {
                        FitScreenUtil.setParams(gridItemHolder.mImageViewContent, 1002);
                        FitScreenUtil.setParams(gridItemHolder.imageView, 1002);
                    }
                    if (!TextUtils.isEmpty(bean.getVtype()) && ("11".equals(bean.getVtype()) || "12".equals(bean.getVtype()) || "13".equals(bean.getVtype()) || "14".equals(bean.getVtype()))) {
                        gridItemHolder.tvLabel.setVisibility(View.VISIBLE);
                        if ("11".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("投票");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg11));
                        } else if ("12".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("答题");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg12));
                        } else if ("13".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("抽奖");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg13));
                        } else if ("14".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("话题");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg14));
                        }
                    } else {
                        if (null != bean.getCornerStr() && !"".equals(bean.getCornerStr()) && null != bean.getCornerColour() && !"".equals(bean.getCornerColour())) {
                            gridItemHolder.type.setVisibility(View.VISIBLE);
                            gridItemHolder.type.setText(bean.getCornerStr());
                            gridItemHolder.type.setBackgroundColor(Color.parseColor(bean.getCornerColour()));
                            //Logs.e("字符串转换颜色", "====>" + Color.parseColor(bean.getCornerColour()));
                        }
                        gridItemHolder.tvLabel.setVisibility(View.GONE);
                    }
                    gridItemHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);

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
                            gridItemHolder.doubleTitle.setVisibility(View.INVISIBLE);
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
                } else if (items.get(arg0) instanceof RecommendHomeColumnListInfo.DataEntity.BigImgEntity) {
                    //大图
                    RecommendHomeColumnListInfo.DataEntity.BigImgEntity bean = (RecommendHomeColumnListInfo.DataEntity.BigImgEntity) items.get(arg0);
                    FitScreenUtil.setParams(gridItemHolder.mImageViewContent, 1004);
                    FitScreenUtil.setParams(gridItemHolder.imageView, 1004);
                    FitScreenUtil.setParams(gridItemHolder.updateTitleTextView, 2, params);

                    if (!TextUtils.isEmpty(bean.getVtype()) && ("11".equals(bean.getVtype()) || "12".equals(bean.getVtype()) || "13".equals(bean.getVtype()) || "14".equals(bean.getVtype()))) {
                        gridItemHolder.tvLabel.setVisibility(View.VISIBLE);
                        if ("11".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("投票");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg11));
                        } else if ("12".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("答题");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg12));
                        } else if ("13".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("抽奖");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg13));
                        } else if ("14".equals(bean.getVtype())) {
                            gridItemHolder.tvLabel.setText("话题");
                            gridItemHolder.tvLabel.setBackgroundColor(context.getResources().getColor(R.color.home_hudong_item_bg14));
                        }
                    } else {
                        if (null != bean.getCornerStr() && !"".equals(bean.getCornerStr()) && null != bean.getCornerColour() && !"".equals(bean.getCornerColour())) {
                            gridItemHolder.type.setVisibility(View.VISIBLE);
                            gridItemHolder.type.setText(bean.getCornerStr());
                            gridItemHolder.type.setBackgroundColor(Color.parseColor(bean.getCornerColour()));
                            //Logs.e("字符串转换颜色", "====>" + Color.parseColor(bean.getCornerColour()));
                        }
                        gridItemHolder.tvLabel.setVisibility(View.GONE);
                    }


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
                        gridItemHolder.title.setMaxLines(2);
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

                    gridItemHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    fb.display(gridItemHolder.imageView, bean.getImgUrl());
                    gridItemHolder.title.setVisibility(View.VISIBLE);
                    gridItemHolder.title.setText(bean.getTitle());
                    gridItemHolder.title.setTextColor(Color.BLACK);
                } else if (items.get(arg0) instanceof String) {
                    //最底部
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_listview_item_cctv_item, null); //为了保持和IOS6.1版本一致,先注释掉5.22
                    convertView.setTag(gridItemHolder);
                    View view = convertView.findViewById(R.id.content_view);
                    FitScreenUtil.setParams(gridItemHolder.imageView, 12);
                }
            } catch (Exception e) {
                Logs.e(TAG, e.toString());
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(null, null, arg0, 0);
                }
            }
        });
        return convertView;
    }

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
        public View mImageViewContent;
    }
}
