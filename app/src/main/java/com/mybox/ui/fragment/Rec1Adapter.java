package com.mybox.ui.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybox.AppContext;
import com.mybox.adpter.RecommendGridViewAdapter;
import com.mybox.adpter.RecommendGridViewyingshijuAdapter;
import com.mybox.adpter.RecommendHorizontalcainixihuanAdapter;
import com.mybox.adpter.RecommendHorizontaljinrikandainAdapter;
import com.mybox.adpter.RecommendType4ListAdapter;
import com.mybox.adpter.RecommendType8ListAdapter;
import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.listener.MoreOnClickListener;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;
import com.mybox.widget.GridViewWithHeaderAndFooter;
import com.mybox.widget.HorizontalListView;

import java.util.List;

/**
 * (RTFSC)
 * 描述 Okgo1.0
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2017/2/27
 */
public class Rec1Adapter extends BaseAdapter {
    //type ＝
    // 1， 大图＋2列小橫图
    // 2， 2列小橫图
    // 3，4左图右文
    // 5，大图＋3列小橫图
    // 6，7横向滑动
    // 8，瀑布流
    private final FinalBitmap fb;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List mContentList;
    private RecommendViewHolder holder;

    private SparseArray<LiveChannelItemListener> mListeners = new SparseArray<>();
    private SparseArray<MoreOnClickListener> mMoreListeners = new SparseArray<>();

    public Rec1Adapter(Context context, List data) {
        mContentList = data;
        mContext = context;
        fb = FinalBitmap.create(mContext);
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mContentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int i) {

        int type = -1;

        if (mContentList.get(i).getClass().equals(RecommendedHomeBean.DataEntity.ColumnListEntity.class)) {
            RecommendedHomeBean.DataEntity.ColumnListEntity mColumnBean
                    = (RecommendedHomeBean.DataEntity.ColumnListEntity) mContentList.get(i);

            if (mColumnBean.getInfo() != null) {
                if (mColumnBean.isClone()) {
                    type = 0;
                } else if (mColumnBean.getTemplateType() != null) {
                    String t = mColumnBean.getTemplateType();
                    type = Integer.valueOf(t);
                }
            }
        }
        Log.e("TAG", "getItemViewType-------> type: " + type);
        return type;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        String type = getItemViewType(i) + "";

        if (convertView == null) {
            holder = new RecommendViewHolder();
            switch (type) {

                case "6"://今日看点
                    convertView = mLayoutInflater.inflate(R.layout.recommendnew_listview_item_gridview_item6, null, true);
                    holder.mGridView =  (HorizontalListView)convertView.findViewById(R.id.normal_gridview);
                    holder.mGridViewTextContent = (LinearLayout) convertView.findViewById(R.id.home_grid_title);
                    holder.mLineView = convertView.findViewById(R.id.home_item_gridview_line);
                    holder.mGridViewLabel = (TextView) convertView.findViewById(R.id.label);
                    holder.ad_iv = (ImageView) convertView.findViewById(R.id.classify_ad_iv);
                    holder.mMoreView = convertView.findViewById(R.id.home_list_item_more);
                    break;
                case "7"://猜你喜欢
                    convertView = mLayoutInflater.inflate(R.layout.recommendnew_listview_item_gridview_item7, null, true);
                    holder.mGridView =   (HorizontalListView)convertView.findViewById(R.id.normal_gridview);
                    holder.mGridViewTextContent = (LinearLayout) convertView.findViewById(R.id.home_grid_title);
                    holder.mLineView = convertView.findViewById(R.id.home_item_gridview_line);
                    holder.mGridViewLabel = (TextView) convertView.findViewById(R.id.label);
                    holder.ad_iv = (ImageView) convertView.findViewById(R.id.classify_ad_iv);
                    holder.mMoreView = convertView.findViewById(R.id.home_list_item_more);
                    break;
                default:
                    convertView = mLayoutInflater.inflate(R.layout.recommendnew_listview_item_gridview_item, null, true);
                    holder.mGridView = (GridViewWithHeaderAndFooter)convertView.findViewById(R.id.normal_gridview);
                    holder.mGridViewTextContent = (LinearLayout) convertView.findViewById(R.id.home_grid_title);
                    holder.mLineView = convertView.findViewById(R.id.home_item_gridview_line);
                    holder.mGridViewLabel = (TextView) convertView.findViewById(R.id.label);
                    holder.ad_iv = (ImageView) convertView.findViewById(R.id.classify_ad_iv);
                    holder.mMoreView = convertView.findViewById(R.id.home_list_item_more);
                    break;
            }
            convertView.setTag(holder);
        } else {//有tag时
            holder = (RecommendViewHolder) convertView.getTag();
        }


        //填充item的数据
        MyBaseAdapter adapter=null;

        RecommendedHomeBean.DataEntity.ColumnListEntity mColumnBean = (RecommendedHomeBean.DataEntity.ColumnListEntity) mContentList.get(i);
        List<RecommendHomeColumnListInfo.DataEntity.ItemListEntity> mItemList= ((RecommendHomeColumnListInfo) mColumnBean.getInfo()).getData().getItemList();
        switch (type) {
            case "0":
                holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));
                if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                    holder.ad_iv.setVisibility(View.VISIBLE);
                    fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                }
                ((GridViewWithHeaderAndFooter)holder.mGridView).setNumColumns(1);
                List list = null;
                if (((RecommendHomeColumnListInfo) mColumnBean.getInfo()).getData().getBigImg().size() >= 1) {
                    list = ((RecommendHomeColumnListInfo) mColumnBean.getInfo()).getData().getBigImg().subList(0, 1);
                }
                if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) && "1".equals(mColumnBean.getCategoryControl())) {
                    holder.mMoreView.setVisibility(View.VISIBLE);
                    MoreOnClickListener listener1 = new MoreOnClickListener(mContext, mContentList, i);
                    mMoreListeners.put(i, listener1);
                    holder.mMoreView.setOnClickListener(mMoreListeners.get(i));
                }

                adapter = new RecommendGridViewAdapter(AppContext.getInstance(), list, (holder.mGridView).getLayoutParams());
                if (adapter != null && adapter instanceof RecommendGridViewAdapter) {
                    if (mContentList != null) {
                        if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                            RecommendedHomeBean.DataEntity.ColumnListEntity entity = mColumnBean;
                            ((RecommendGridViewAdapter) adapter).setIsDoubleTitle(entity.getIsDoubleTitle());
                        }
                    }
                }
                ((GridViewWithHeaderAndFooter)holder.mGridView).setAdapter(adapter);

                LiveChannelItemListener listener2 = new LiveChannelItemListener(mContext, mContentList, i);
                mListeners.put(i, listener2);
                ((GridViewWithHeaderAndFooter)holder.mGridView).setOnItemClickListener(mListeners.get(i));
                ((RecommendGridViewAdapter) adapter).setListener(mListeners.get(i));
                break;
            case "1":
            case "2":
            case "5":
                holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));
                if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                    holder.ad_iv.setVisibility(View.VISIBLE);
                    fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                }


                if ("1".equals(type)) {
                    if (mItemList.size() > 8) {
                        mItemList = mItemList.subList(0, 8);
                    }

                    holder.mGridViewTextContent.setVisibility(View.GONE);
                    holder.mLineView.setVisibility(View.GONE);
                } else if ("2".equals(type)) {
                    if (mItemList.size() > 12) {
                        mItemList = mItemList.subList(0, 12);
                    }

                    if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                            "1".equals(mColumnBean.getCategoryControl())) {
                        holder.mMoreView.setVisibility(View.VISIBLE);
                        MoreOnClickListener listener = new MoreOnClickListener(mContext, mContentList, i);
                        mMoreListeners.put(i, listener);
                        holder.mMoreView.setOnClickListener(mMoreListeners.get(i));
                    }
                } else if ("5".equals(type)) {
                    if (mItemList.size() > 6) {
                        mItemList = mItemList.subList(0, 6);
                    }

                    holder.mGridViewTextContent.setVisibility(View.GONE);
                    holder.mLineView.setVisibility(View.GONE);
                }


                if ("5".equals(type)) {
                    //  3列
                    ((GridViewWithHeaderAndFooter)holder.mGridView).setNumColumns(3);
                    adapter = new RecommendGridViewyingshijuAdapter(AppContext.getInstance(), mItemList, type);

                    if (adapter != null && adapter instanceof RecommendGridViewyingshijuAdapter) {
                        if (mContentList != null) {
                            if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                RecommendedHomeBean.DataEntity.ColumnListEntity entity = mColumnBean;
                                ((RecommendGridViewyingshijuAdapter) adapter).setIsDoubleTitle(entity.getIsDoubleTitle());
                            }
                        }
                    }
                    ((GridViewWithHeaderAndFooter)holder.mGridView).setAdapter(adapter);
                } else{
                    //   2列
                    ((GridViewWithHeaderAndFooter)holder.mGridView).setNumColumns(2);
                    adapter = new RecommendGridViewAdapter(AppContext.getInstance(), mItemList);

                    if (adapter != null && adapter instanceof RecommendGridViewAdapter) {
                        if (mContentList != null) {
                            if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                RecommendedHomeBean.DataEntity.ColumnListEntity entity = mColumnBean;
                                ((RecommendGridViewAdapter) adapter).setIsDoubleTitle(entity.getIsDoubleTitle());
                            }
                        }
                    }
                    ((GridViewWithHeaderAndFooter)holder.mGridView).setAdapter(adapter);
                }

                if (true) {
                    LiveChannelItemListener listener = new LiveChannelItemListener(mContext, mContentList, i);
                    mListeners.put(i, listener);
                }
                ((GridViewWithHeaderAndFooter)holder.mGridView).setOnItemClickListener(mListeners.get(i));
                if (adapter instanceof RecommendGridViewyingshijuAdapter) {
                    ((RecommendGridViewyingshijuAdapter) adapter).setListener(mListeners.get(i));
                } else {
                    ((RecommendGridViewAdapter) adapter).setListener(mListeners.get(i));

                }

                break;
            case "3":
            case "4":

                holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));//设置标题
                if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                    holder.ad_iv.setVisibility(View.VISIBLE);
                    fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                }
                ((GridViewWithHeaderAndFooter)holder.mGridView).setNumColumns(1);
                ((GridViewWithHeaderAndFooter)holder.mGridView).setVerticalSpacing(0);


                if ("3".equals(type)) {// 带序号的
                    if (mItemList.size() > 5) {
                        mItemList = mItemList.subList(0, 5);
                    }

                    adapter = new RecommendType4ListAdapter(AppContext.getInstance(), mItemList, true);
                    ((RecommendType4ListAdapter) adapter).setType(3);
                    ((GridViewWithHeaderAndFooter)holder.mGridView).setAdapter(adapter);
                } else {
                    if (mItemList.size() > 8) {
                        mItemList = mItemList.subList(0, 8);
                    }

                    adapter = new RecommendType4ListAdapter(AppContext.getInstance(), mItemList);
                    ((GridViewWithHeaderAndFooter)holder.mGridView).setAdapter(adapter);
                }
                if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                        "1".equals(mColumnBean.getCategoryControl())) {
                    holder.mMoreView.setVisibility(View.VISIBLE);
                    MoreOnClickListener listener = new MoreOnClickListener(mContext, mContentList, i);
                    mMoreListeners.put(i, listener);
                    holder.mMoreView.setOnClickListener(mMoreListeners.get(i));

                }
                if (true) {
                    LiveChannelItemListener listener = new LiveChannelItemListener(mContext, mContentList, i);
                    mListeners.put(i, listener);
                }
                ((GridViewWithHeaderAndFooter)holder.mGridView).setOnItemClickListener(mListeners.get(i));
                ((RecommendType4ListAdapter) adapter).setListener(mListeners.get(i));
                break;

            case "6"://今日看点
            case "7"://猜你喜欢

                if ("6".equals(type)) {


                    holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));
                    if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                        holder.ad_iv.setVisibility(View.VISIBLE);
                        fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                    }

                    adapter = new RecommendHorizontaljinrikandainAdapter(AppContext.getInstance(), mItemList);

                    if (adapter != null && adapter instanceof RecommendHorizontaljinrikandainAdapter) {
                        if (mContentList != null) {
                            if (mContentList.get(i) != null
                                    && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                ((RecommendHorizontaljinrikandainAdapter) adapter).setIsDoubleTitle(mColumnBean.getIsDoubleTitle());
                            }
                        }
                    }

                    ((HorizontalListView)holder.mGridView).setAdapter(adapter);
                    ((HorizontalListView)holder.mGridView).setSelection(100);

                    LiveChannelItemListener listener = new LiveChannelItemListener(mContext, mContentList, i);
                    mListeners.put(i, listener);
                    ((HorizontalListView)holder.mGridView).setOnItemClickListener(mListeners.get(i));
                    ((RecommendHorizontaljinrikandainAdapter) adapter).setListener(mListeners.get(i));
                } else {

                    holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));
                    if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                        holder.ad_iv.setVisibility(View.VISIBLE);
                        fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                    }

                    adapter = new RecommendHorizontalcainixihuanAdapter(AppContext.getInstance(), ((List) mColumnBean.getInfo()));

                    ((HorizontalListView)holder.mGridView).setAdapter(adapter);
                    ((HorizontalListView)holder.mGridView).setSelection(100);

                    LiveChannelItemListener listener = new LiveChannelItemListener(mContext, mContentList, i);
                    mListeners.put(i, listener);
                    ((HorizontalListView)holder.mGridView).setOnItemClickListener(mListeners.get(i));
                    ((RecommendHorizontalcainixihuanAdapter) adapter).setListener(mListeners.get(i));
                }

                if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                        "1".equals(mColumnBean.getCategoryControl())) {
                    holder.mMoreView.setVisibility(View.VISIBLE);
                    MoreOnClickListener listener = new MoreOnClickListener(mContext, mContentList, i);
                    mMoreListeners.put(i, listener);
                    holder.mMoreView.setOnClickListener(mMoreListeners.get(i));

                }
                break;
            case "8":

                holder.mGridViewTextContent.setVisibility(View.GONE);
                holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));//设置标题
                if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                    holder.ad_iv.setVisibility(View.VISIBLE);
                    fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                }

                ((GridViewWithHeaderAndFooter)holder.mGridView).setNumColumns(1);
                ((GridViewWithHeaderAndFooter)holder.mGridView).setVerticalSpacing(0);

                if (mItemList.size() >= 20) {
                    mItemList = mItemList.subList(0, 20);
                }


                adapter = new RecommendType8ListAdapter(AppContext.getInstance(), mItemList);
                ((RecommendType8ListAdapter) adapter).setType(3);

                ((GridViewWithHeaderAndFooter)holder.mGridView).setAdapter(adapter);

                if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                        "1".equals(mColumnBean.getCategoryControl())) {
                    holder.mMoreView.setVisibility(View.VISIBLE);
                    MoreOnClickListener listener = new MoreOnClickListener(mContext, mContentList, i);
                    mMoreListeners.put(i, listener);
                    holder.mMoreView.setOnClickListener(mMoreListeners.get(i));

                }

                LiveChannelItemListener listener = new LiveChannelItemListener(mContext, mContentList, i);
                mListeners.put(i, listener);

                ((GridViewWithHeaderAndFooter)holder.mGridView).setOnItemClickListener(mListeners.get(i));
                ((RecommendType8ListAdapter) adapter).setListener(mListeners.get(i));


                break;

            default:
                break;
        }

        return convertView;
    }

    public static class RecommendViewHolder {
        public View mGridView;
        public LinearLayout mGridViewTextContent;
        public TextView mGridViewLabel;
        public ImageView ad_iv;
        public View mLineView;
        public View mMoreView;
    }
}
