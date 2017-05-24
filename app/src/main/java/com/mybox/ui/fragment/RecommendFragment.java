package com.mybox.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mybox.AppContext;
import com.mybox.adpter.Recommend2LiveAdapter;
import com.mybox.adpter.Recommend4LiveListAdapter;
import com.mybox.adpter.Recommend5LiveListAdapter;
import com.mybox.adpter.RecommendGridViewAdapter;
import com.mybox.adpter.RecommendGridViewyingshijuAdapter;
import com.mybox.adpter.RecommendHorizontalcainixihuanAdapter;
import com.mybox.adpter.RecommendHorizontaljinrikandainAdapter;
import com.mybox.adpter.RecommendType4ListAdapter;
import com.mybox.adpter.RecommendType8ListAdapter;
import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.listener.MoreOnClickListener;
import com.mybox.model.AdBigImageData;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.net.api.DataHelper;
import com.mybox.net.retrofit.Transformers;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;
import com.mybox.utils.FitScreenUtil;
import com.mybox.utils.Logs;
import com.mybox.utils.SizeUtils;
import com.mybox.widget.AutoScrollViewPager;
import com.mybox.widget.GridViewWithHeaderAndFooter;
import com.mybox.widget.HorizontalListView;
import com.mybox.widget.XListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rx.functions.Action1;

/**
 * 描述 CBoxV6.1
 * 首页子模块：精选  位置排第一，不可调
 *
 * @Author: 作者  huaiqiang
 * @Version: 版本  1.0  2016/4/6
 */

public class RecommendFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "NewRecommendFragment";


    private RecommendAdapter adapter;
    private LayoutInflater mLayoutInflater;
    private FinalBitmap fb;
    private SparseArray<RecommendViewHolder> convertViews = new SparseArray<>();
    private SparseArray<LiveChannelItemListener> mListeners = new SparseArray<>();
    private SparseArray<MoreOnClickListener> mMoreListeners = new SparseArray<>();
    private RecommendedHomeBean mRecommendedHomeBean;
    private List mContentList;
    private AutoScrollViewPager mConvenientBanner;

    private View mRootView;
    private View mRecommendHeadView;
    private TextView mViewFlowTitle;
    private XListView mRemmendNewListView;
    private RelativeLayout mHomeAlertContent;
    private TextView mHomeAlertText;
    private ImageView mHomeAlertClose;
    private LinearLayout mRemmendLoading;


    private Context mContext;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mLayoutInflater = LayoutInflater.from(getActivity());
            fb = FinalBitmap.create(getActivity());
            mRootView = mLayoutInflater.inflate(R.layout.fragment_main_rec0, container, false);
            initView();
            initData();
            initAction();
        }
        return mRootView;
    }

    private void initView() {
        Logs.e(TAG, "-------------------------> initView ");
        mRemmendLoading = (LinearLayout) mRootView.findViewById(R.id.tuijian_loading);

        mRemmendNewListView = (XListView) mRootView.findViewById(R.id.remmend_new_list_view);

        mRemmendNewListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRemmendNewListView.setPullLoadEnable(false);
        mRemmendNewListView.setPullRefreshEnable(true);
        mRemmendNewListView.setFooterDividersEnabled(false);

        mHomeAlertContent = (RelativeLayout) mRootView.findViewById(R.id.home_alert_content);
        mHomeAlertText = (TextView) mRootView.findViewById(R.id.home_alert_text);
        mHomeAlertClose = (ImageView) mRootView.findViewById(R.id.home_alert_close);


        mRecommendHeadView = LayoutInflater.from(AppContext.getInstance()).inflate(R.layout.common_banner, null);// 推荐头部
        mConvenientBanner = (AutoScrollViewPager) mRecommendHeadView.findViewById(R.id.convenientBanner);
        mViewFlowTitle = (TextView) mRecommendHeadView.findViewById(R.id.tvBannerTitle);


    }

    private void initData() {

        String mRecUrl = "http://cbox.cntv.cn/json2015/moban60/topshouye/index.json";
        if (mRecUrl != null) {
            getRecommendData(mRecUrl);
        }
    }

    private void getRecommendData(String url) {

        Logs.e(TAG, "-------------------------> getRecommendData ");
        DataHelper.getCntvRepository()
                .getRecommendHome(url)
                .compose(Transformers.<RecommendedHomeBean>applySchedulers())
                .subscribe(new Action1<RecommendedHomeBean>() {
                               @Override
                               public void call(RecommendedHomeBean recommendedHomeBean) {
                                   mRecommendedHomeBean = recommendedHomeBean;
                                   initContentData2();
                                   if (mRemmendNewListView != null) {
                                       mRemmendNewListView.stopRefresh();
                                   }
                               }
                           }

                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                String message = throwable.getMessage();
                                Logs.e(TAG, "-------------------------> Throwable :"+message);
                            }
                        }

                );

    }

    private void initContentData2() {
        Logs.e(TAG, "-------------------------> initContentData2 ");
        try {

            mContentList = new ArrayList();
            RecommendedHomeBean.DataEntity data =
                    mRecommendedHomeBean.getData();
            if (data != null) {

                if (!"0".equals(data.getInteractliveControl()) &&
                        !data.getInteractlive1().isEmpty()) {
                    //互动直播
                    mContentList.add(data.getInteractlive1());
                }
                if (!"0".equals(data.getLiveCategoryControl()) &&
                        !data.getLiveCategoryList().isEmpty()) {
                    //分类直播
                    mContentList.add(data.getLiveCategoryList());
                }
                if (!"0".equals(data.getNormalLiveListControl()) &&
                        !data.getNormalLiveList().isEmpty()) {
                    //正在直播
                    mContentList.add(data.getNormalLiveList());
                }
                if (data.getColumnList() != null && !data.getColumnList().isEmpty()) {

                    List mlist = new ArrayList();
                    for (int i = 0; i < data.getColumnList().size(); i++) {
                        if (data.getColumnList().get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity
                                && (data.getColumnList().get(i)).getInfo() != null) {
                            if (!TextUtils.isEmpty(data.getColumnList().get(i).getShowControl()) &&
                                    "1".equals(data.getColumnList().get(i).getShowControl())) {

                                String type = (data.getColumnList().get(i)).getTemplateType();
                                if (type != null) {
                                    if ("1".equals(type) || "5".equals(type)) {
                                        //因为1，5要大图
                                        try {
                                            mlist.add((data.getColumnList().get(i)).clone());
                                            mlist.add((data.getColumnList().get(i)));
                                        } catch (CloneNotSupportedException e) {
                                            e.printStackTrace();
                                        }
                                    } else if ("2".equals(type) || "3".equals(type) || "4".equals(type) || "6".equals(type) || "7".equals(type) || "8".equals(type)) {
                                        mlist.add((data.getColumnList().get(i)));
                                    }
                                }
                            }
                        }
                    }
                    mContentList.addAll(mlist);
                    mContentList.add("12345");
                }

            }


            for (int i = 0; i < mContentList.size(); i++) {
                RecommendViewHolder holder = new RecommendViewHolder();
                holder.convertView = mLayoutInflater.inflate(R.layout.recommendnew_listview_item_gridview_item, null, true);
                holder.mGridView = holder.convertView.findViewById(R.id.normal_gridview);
                holder.mGridViewTextContent = (LinearLayout) holder.convertView.findViewById(R.id.home_grid_title);
                holder.mLineView = holder.convertView.findViewById(R.id.home_item_gridview_line);
                holder.mGridViewLabel = (TextView) holder.convertView.findViewById(R.id.label);
                holder.ad_iv = (ImageView) holder.convertView.findViewById(R.id.classify_ad_iv);
                holder.mMoreView = holder.convertView.findViewById(R.id.home_list_item_more);

                if (mContentList.get(i).getClass().equals(RecommendedHomeBean.DataEntity.ColumnListEntity.class)) {
                    RecommendedHomeBean.DataEntity.ColumnListEntity mColumnBean =
                            (RecommendedHomeBean.DataEntity.ColumnListEntity) mContentList.get(i);

                    if (mColumnBean.getInfo() != null) {

                        if (mColumnBean.isClone()) {//isClone 是大图，自由一列

                            holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));
                            if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                                holder.ad_iv.setVisibility(View.VISIBLE);
                                fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                            }
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(1);
                            List list = null;
                            if (mColumnBean.getInfo() instanceof RecommendHomeColumnListInfo) {
                                if (((RecommendHomeColumnListInfo) mColumnBean.getInfo()).getData().getBigImg().size() > 1) {
                                    list = ((RecommendHomeColumnListInfo) mColumnBean.getInfo()).getData().getBigImg().subList(0, 1);
                                } else {
                                    list = ((RecommendHomeColumnListInfo) mColumnBean.getInfo()).getData().getBigImg();
                                }
                            }
                            if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) && "1".equals(mColumnBean.getCategoryControl())) {
                                holder.mMoreView.setVisibility(View.VISIBLE);
                                MoreOnClickListener listener = new MoreOnClickListener(getActivity(), mContentList, i);
                                mMoreListeners.put(i, listener);
                                holder.mMoreView.setOnClickListener(mMoreListeners.get(i));
                            }

                            MyBaseAdapter adapter = new RecommendGridViewAdapter(AppContext.getInstance(), list, (holder.mGridView).getLayoutParams());
                            if (adapter != null && adapter instanceof RecommendGridViewAdapter) {
                                if (mContentList != null) {
                                    if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                        RecommendedHomeBean.DataEntity.ColumnListEntity entity = mColumnBean;
                                        ((RecommendGridViewAdapter) adapter).setIsDoubleTitle(entity.getIsDoubleTitle());
                                    }
                                }
                            }
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                            LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                            mListeners.put(i, listener);
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                            ((RecommendGridViewAdapter) adapter).setListener(mListeners.get(i));

                        } else if (mColumnBean.getTemplateType() != null) {//templateType ＝1， 大图＋2列小橫图  2， 2列小橫图 3，4左图右文  5，大图＋3列小橫图  6，7横向滑动

                            MyBaseAdapter adapter;
                            String templateType = mColumnBean.getTemplateType();

                            //1大＋小图模版，小图最多8个
                            //2 全小横图模板最多12张
                            //3 最多5个
                            //4 最多8条
                            //5 大图+小竖图模板，最多小图6张
                            List<RecommendHomeColumnListInfo.DataEntity.ItemListEntity> mItemList = null;
                            if (!"7".equals(templateType)) {
                                mItemList = ((RecommendHomeColumnListInfo) mColumnBean.getInfo()).getData().getItemList();
                                for (Iterator it = mItemList.iterator(); it.hasNext(); ) {
                                    RecommendHomeColumnListInfo.DataEntity.ItemListEntity str =
                                            (RecommendHomeColumnListInfo.DataEntity.ItemListEntity) it.next();
                                    if (str.getIsShow() != null && str.getIsShow().equals("0")) {
                                        it.remove();
                                    }
                                }
                            }


                            switch (templateType) {
                                case "1":
                                case "2":
                                case "5":
                                    if ("1".equals(templateType)) {
                                        if (mItemList.size() > 8) {
                                            mItemList = mItemList.subList(0, 8);
                                        }

                                        holder.mGridViewTextContent.setVisibility(View.GONE);
                                        holder.mLineView.setVisibility(View.GONE);
                                    } else if ("2".equals(templateType)) {
                                        if (mItemList.size() > 12) {
                                            mItemList = mItemList.subList(0, 12);
                                        }

                                        if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                                                "1".equals(mColumnBean.getCategoryControl())) {
                                            holder.mMoreView.setVisibility(View.VISIBLE);
                                            MoreOnClickListener listener = new MoreOnClickListener(getActivity(), mContentList, i);
                                            mMoreListeners.put(i, listener);
                                            holder.mMoreView.setOnClickListener(mMoreListeners.get(i));
                                        }
                                    } else if ("5".equals(templateType)) {
                                        if (mItemList.size() > 6) {
                                            mItemList = mItemList.subList(0, 6);
                                        }

                                        holder.mGridViewTextContent.setVisibility(View.GONE);
                                        holder.mLineView.setVisibility(View.GONE);
                                    }

                                    holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));
                                    if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                                        holder.ad_iv.setVisibility(View.VISIBLE);
                                        fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                                    }

                                    if ("5".equals(templateType)) {
                                        //  3列
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(3);
                                        adapter = new RecommendGridViewyingshijuAdapter(AppContext.getInstance(), mItemList, templateType);

                                        if (adapter != null && adapter instanceof RecommendGridViewyingshijuAdapter) {
                                            if (mContentList != null) {
                                                if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                                    RecommendedHomeBean.DataEntity.ColumnListEntity entity = mColumnBean;
                                                    ((RecommendGridViewyingshijuAdapter) adapter).setIsDoubleTitle(entity.getIsDoubleTitle());
                                                }
                                            }
                                        }
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);
                                    } else {
                                        //   2列
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(2);
                                        adapter = new RecommendGridViewAdapter(AppContext.getInstance(), mItemList);

                                        if (adapter != null && adapter instanceof RecommendGridViewAdapter) {
                                            if (mContentList != null) {
                                                if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                                    RecommendedHomeBean.DataEntity.ColumnListEntity entity = mColumnBean;
                                                    ((RecommendGridViewAdapter) adapter).setIsDoubleTitle(entity.getIsDoubleTitle());
                                                }
                                            }
                                        }
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);
                                    }

                                    if (true) {
                                        LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                        mListeners.put(i, listener);
                                    }
                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
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
                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(1);
                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setVerticalSpacing(0);


                                    if ("3".equals(templateType)) {// 带序号的
                                        if (mItemList.size() > 5) {
                                            mItemList = mItemList.subList(0, 5);
                                        }

                                        adapter = new RecommendType4ListAdapter(AppContext.getInstance(), mItemList, true);
                                        ((RecommendType4ListAdapter) adapter).setType(3);
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);
                                    } else {
                                        if (mItemList.size() > 8) {
                                            mItemList = mItemList.subList(0, 8);
                                        }

                                        adapter = new RecommendType4ListAdapter(AppContext.getInstance(), mItemList);
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);
                                    }
                                    if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                                            "1".equals(mColumnBean.getCategoryControl())) {
                                        holder.mMoreView.setVisibility(View.VISIBLE);
                                        MoreOnClickListener listener = new MoreOnClickListener(getActivity(), mContentList, i);
                                        mMoreListeners.put(i, listener);
                                        holder.mMoreView.setOnClickListener(mMoreListeners.get(i));

                                    }
                                    if (true) {
                                        LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                        mListeners.put(i, listener);
                                    }
                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                    ((RecommendType4ListAdapter) adapter).setListener(mListeners.get(i));
                                    break;

                                case "6"://今日看点
                                case "7"://猜你喜欢

                                    if ("6".equals(templateType)) {
                                        holder.convertView = mLayoutInflater.inflate(R.layout.recommendnew_listview_item_gridview_item6, null, true);
                                        holder.mGridView = (HorizontalListView) holder.convertView.findViewById(R.id.normal_gridview);
                                        holder.mGridViewTextContent = (LinearLayout) holder.convertView.findViewById(R.id.home_grid_title);
                                        holder.mLineView = holder.convertView.findViewById(R.id.home_item_gridview_line);
                                        holder.mGridViewLabel = (TextView) holder.convertView.findViewById(R.id.label);
                                        holder.ad_iv = (ImageView) holder.convertView.findViewById(R.id.classify_ad_iv);
                                        holder.mMoreView = holder.convertView.findViewById(R.id.home_list_item_more);

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

                                        ((HorizontalListView) holder.mGridView).setAdapter(adapter);
                                        ((HorizontalListView) holder.mGridView).setSelection(100);

                                        LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                        mListeners.put(i, listener);
                                        ((HorizontalListView) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                        ((RecommendHorizontaljinrikandainAdapter) adapter).setListener(mListeners.get(i));
                                    } else {

                                        holder.convertView = mLayoutInflater.inflate(R.layout.recommendnew_listview_item_gridview_item7, null, true);
                                        holder.mGridView = (HorizontalListView) holder.convertView.findViewById(R.id.normal_gridview);
                                        holder.mGridViewTextContent = (LinearLayout) holder.convertView.findViewById(R.id.home_grid_title);
                                        holder.mLineView = holder.convertView.findViewById(R.id.home_item_gridview_line);
                                        holder.mGridViewLabel = (TextView) holder.convertView.findViewById(R.id.label);
                                        holder.ad_iv = (ImageView) holder.convertView.findViewById(R.id.classify_ad_iv);
                                        holder.mMoreView = holder.convertView.findViewById(R.id.home_list_item_more);

                                        holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnBean.getTitle()));
                                        if (null != mColumnBean.getAdImgUrl() && !"".equals(mColumnBean.getAdImgUrl())) {
                                            holder.ad_iv.setVisibility(View.VISIBLE);
                                            fb.display(holder.ad_iv, mColumnBean.getAdImgUrl());
                                        }

                                        adapter = new RecommendHorizontalcainixihuanAdapter(AppContext.getInstance(), ((List) mColumnBean.getInfo()));

                                        ((HorizontalListView) holder.mGridView).setAdapter(adapter);
                                        ((HorizontalListView) holder.mGridView).setSelection(100);

                                        LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                        mListeners.put(i, listener);
                                        ((HorizontalListView) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                        ((RecommendHorizontalcainixihuanAdapter) adapter).setListener(mListeners.get(i));
                                    }

                                    if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                                            "1".equals(mColumnBean.getCategoryControl())) {
                                        holder.mMoreView.setVisibility(View.VISIBLE);
                                        MoreOnClickListener listener = new MoreOnClickListener(getActivity(), mContentList, i);
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

                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(1);
                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setVerticalSpacing(0);

                                    if (mItemList.size() >= 20) {
                                        AdBigImageData ad1 = data.getAd1();
                                        if (ad1 != null && !"ad".equals(mItemList.get(7).getOrder())) {
                                            RecommendHomeColumnListInfo.DataEntity.ItemListEntity entity7 = new RecommendHomeColumnListInfo.DataEntity.ItemListEntity();
                                            entity7.setTitle(ad1.getText());
                                            entity7.setImgUrl(ad1.getUrl());
                                            entity7.setPcUrl(ad1.getClick());
                                            entity7.setCornerStr("广告");
                                            entity7.setCornerColour("#0088ff");
                                            entity7.setOrder("ad");
                                            entity7.setVtype("7");
                                            mItemList.add(7, entity7);
                                            Logs.e(TAG, "瀑布流add 08 ---------->");
                                        }

                                        AdBigImageData ad2 = data.getAd2();

                                        if (ad2 != null && !"ad".equals(mItemList.get(19).getOrder())) {
                                            RecommendHomeColumnListInfo.DataEntity.ItemListEntity entity19 = new RecommendHomeColumnListInfo.DataEntity.ItemListEntity();
                                            entity19.setTitle(ad2.getText());
                                            entity19.setImgUrl(ad2.getUrl());
                                            entity19.setPcUrl(ad2.getClick());
                                            entity19.setCornerStr("广告");
                                            entity19.setCornerColour("#0088ff");
                                            entity19.setOrder("ad");
                                            entity19.setVtype("7");
                                            mItemList.add(19, entity19);
                                            Logs.e(TAG, "瀑布流add 20 ---------->");
                                        }

                                        mItemList = mItemList.subList(0, 20);
                                    }


                                    adapter = new RecommendType8ListAdapter(AppContext.getInstance(), mItemList);
                                    ((RecommendType8ListAdapter) adapter).setType(3);

                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                                   if (!TextUtils.isEmpty(mColumnBean.getCategoryControl()) &&
                                            "1".equals(mColumnBean.getCategoryControl())) {
                                        holder.mMoreView.setVisibility(View.VISIBLE);
                                        MoreOnClickListener listener = new MoreOnClickListener(getActivity(), mContentList, i);
                                        mMoreListeners.put(i, listener);
                                        holder.mMoreView.setOnClickListener(mMoreListeners.get(i));

                                    }

                                    LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                    mListeners.put(i, listener);

                                    ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                    ((RecommendType8ListAdapter) adapter).setListener(mListeners.get(i));


                                    break;

                                default:
                                    break;
                            }

                        }

                    }
                } else if (mContentList.get(i).getClass().equals(ArrayList.class)) {

                    if (mContentList.get(i) != null && !((List) mContentList.get(i)).isEmpty()) {

                        if (((List) mContentList.get(i)).get(0) instanceof RecommendedHomeBean.DataEntity.Interactlive1Bean) {//互动直播

                            List mList = (List<RecommendedHomeBean.DataEntity.Interactlive1Bean>) mContentList.get(i);
                            for (Iterator it = mList.iterator(); it.hasNext(); ) {
                                RecommendedHomeBean.DataEntity.Interactlive1Bean str = (RecommendedHomeBean.DataEntity.Interactlive1Bean) it.next();
                                if (str.getIsShow() != null && str.getIsShow().equals("0")) {
                                    it.remove();
                                }
                            }

                            holder.mLineView.setVisibility(View.GONE);
                            holder.mGridViewTextContent.setVisibility(View.GONE);
                            (((GridViewWithHeaderAndFooter) holder.mGridView)).setNumColumns(5);
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setHorizontalSpacing(2);
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setVerticalSpacing(48);

                            LinearLayout.LayoutParams layoutParams
                                    = (LinearLayout.LayoutParams) holder.mGridView.getLayoutParams();
                            int margin = SizeUtils.dip2px(AppContext.getInstance(), 14);
                            layoutParams.setMargins(0, margin, 0, margin);
                            holder.mGridView.setLayoutParams(layoutParams);


                            MyBaseAdapter adapter = new Recommend5LiveListAdapter(AppContext.getInstance(), ((List) mContentList.get(i)));
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                            LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                            mListeners.put(i, listener);
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                            ((Recommend5LiveListAdapter) adapter).setListener(mListeners.get(i));


                        } else if (((List) mContentList.get(i)).get(0) instanceof RecommendedHomeBean.DataEntity.LiveCategoryListEntity) {//分类直播

                            holder = new RecommendViewHolder();
                            holder.convertView = mLayoutInflater.inflate(R.layout.recommend_4live_listview_item_gridview_item, null, true);
                            holder.mGridView = holder.convertView.findViewById(R.id.normal_gridview);
                            holder.mLineView = holder.convertView.findViewById(R.id.home_item_gridview_line);

                            (((GridViewWithHeaderAndFooter) holder.mGridView)).setNumColumns(((List) mContentList.get(i)).size());

                            MyBaseAdapter adapter = new Recommend4LiveListAdapter(AppContext.getInstance(), ((List) mContentList.get(i)));
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                            LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                            mListeners.put(i, listener);
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                            ((Recommend4LiveListAdapter) adapter).setListener(mListeners.get(i));


                        } else if (((List) mContentList.get(i)).get(0) instanceof RecommendedHomeBean.DataEntity.NormalLiveListEntity) {//正在直播

                            holder = new RecommendViewHolder();
                            holder.convertView = mLayoutInflater.inflate(R.layout.recommend_2live_listview_item_gridview_item, null, true);
                            holder.mGridView = holder.convertView.findViewById(R.id.normal_gridview);
                            holder.mLineView = holder.convertView.findViewById(R.id.home_item_gridview_line);
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(1);

                            MyBaseAdapter adapter = new Recommend2LiveAdapter(AppContext.getInstance(), (List) mContentList.get(i));
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                            LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                            mListeners.put(i, listener);
                            ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                            ((Recommend2LiveAdapter) adapter).setListener(mListeners.get(i));

                        }
                    }

                } else if (mContentList.get(i).getClass().equals(String.class)) {
                    //最下面
                    holder.mGridViewTextContent.setVisibility(View.GONE);
                    holder.mLineView.setVisibility(View.GONE);

                    ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(1);
                    List mList = new ArrayList();
                    mList.add(mContentList.get(i));

                    MyBaseAdapter adapter = new RecommendGridViewAdapter(AppContext.getInstance(), mList);
                    ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                }


                if (holder != null) {
                    convertViews.put(i, holder);
                }

            }



            initBigImageItem();

            if (adapter != null) {
                adapter.setItems(mContentList);

                adapter.setConvertViews(convertViews);
                adapter.setListeners(mListeners);
                adapter.notifyDataSetChanged();
            } else {
                adapter = new RecommendAdapter(AppContext.getInstance());
                adapter.setItems(mContentList);
                adapter.setConvertViews(convertViews);
                adapter.setListeners(mListeners);
                if (mRemmendNewListView != null) {
                    mRemmendNewListView.setAdapter(adapter);
                }
            }

            mRemmendLoading.setVisibility(View.GONE);

            if (mRemmendNewListView != null) {
                mRemmendNewListView.stopRefresh();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initBigImageItem() {
        Logs.e(TAG, "-------------------------> initBigImageItem ");
        if (mRemmendNewListView != null && mRemmendNewListView.getHeaderViewsCount() <= 1) {
            mRemmendNewListView.addHeaderView(mRecommendHeadView);
            mRemmendNewListView.setAdapter(null);
        }

        try {
            final List<RecommendedHomeBean.DataEntity.BigImgEntity> mBannerList = mRecommendedHomeBean.getData().getBigImg();
            //IsShow
            for (Iterator it = mBannerList.iterator(); it.hasNext(); ) {

                RecommendedHomeBean.DataEntity.BigImgEntity str = (RecommendedHomeBean.DataEntity.BigImgEntity) it.next();
                if (str.getIsShow() != null && str.getIsShow().equals("0")) {
                    it.remove();
                }

            }

            FitScreenUtil.setParams(mConvenientBanner, 0);

            mConvenientBanner.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return mBannerList.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object o) {
                    return view == o;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {

                    View view = mLayoutInflater.inflate(R.layout.banner_ad_content, null, true);
                    fb.display((ImageView) view.findViewById(R.id.image_banner), mBannerList.get(position).getImgUrl());

                    if ("ad".equals(mBannerList.get(position).getOrder())) {
                        (view.findViewById(R.id.text_ad)).setVisibility(View.VISIBLE);
                    }

                    container.addView(view);

                    return view;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });

            mViewFlowTitle = (TextView) mRecommendHeadView.findViewById(R.id.tvBannerTitle);// 推荐轮播大图的标题
            mViewFlowTitle.setText(mBannerList.get(0).getTitle());

//            mPointView = (PointView) mRecommendHeadView.findViewById(R.id.viewflowindiclay);
//            mPointView.setPointCount(mBannerList.size());
//            mPointView.setCurrentIndex(0);


            if (mBannerList.size() == 1) {
                LinearLayout pointLayout = (LinearLayout) mRecommendHeadView.findViewById(R.id.pointLayout);
                pointLayout.setVisibility(View.GONE);
            } else {
                final TextView tvPoint1 = (TextView) mRecommendHeadView.findViewById(R.id.tvPoint1);
                final TextView tvPoint2 = (TextView) mRecommendHeadView.findViewById(R.id.tvPoint2);
                tvPoint1.setText("1");
                tvPoint2.setText("/" + mBannerList.size());

                mConvenientBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int i) {
                        mViewFlowTitle.setText(mBannerList.get(i).getTitle());
                        //mPointView.setCurrentIndex(i);

                        tvPoint1.setText("" + (i + 1));
                        tvPoint2.setText("/" + mBannerList.size());
                    }
                });

                mConvenientBanner.setScrollFactgor(5);
                mConvenientBanner.setOffscreenPageLimit(4);
                mConvenientBanner.startAutoScroll();
            }


            mConvenientBanner.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
                @Override
                public void onPageClick(AutoScrollViewPager autoScrollPager, int position) {
                    LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mBannerList, position);
                    listener.eventClickByBanner();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAction() {
        Logs.e(TAG, "-------------------------> initAction ");
        if (mHomeAlertText != null) {
            mHomeAlertText.setOnClickListener(this);
        }
        if (mHomeAlertClose != null) {
            mHomeAlertClose.setOnClickListener(this);
        }
        //下拉监听
        if (mRemmendNewListView != null) {
            mRemmendNewListView.setXListViewListener(new XListView.IXListViewListener() {
                @Override
                public void onRefresh() {
                    try {

                        initData();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        if (mRemmendNewListView != null) {
                            mRemmendNewListView.stopRefresh();
                        }
                    }
                }

                @Override
                public void onLoadMore() {

                }

                @Override
                public void onRightSlip() {

                }

                @Override
                public void onLeftSlip() {

                }
            });
        }


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.home_alert_close:
                if (mHomeAlertContent != null) {
                    mHomeAlertContent.setVisibility(View.GONE);
                }
                break;
            case R.id.home_alert_text:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logs.e(TAG, "-------------------------> onResume ");
        if (mConvenientBanner != null) {
            mConvenientBanner.startAutoScroll();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Logs.e(TAG, "-------------------------> onPause ");
        if (mConvenientBanner != null) {
            mConvenientBanner.stopAutoScroll();

        }
    }

}






