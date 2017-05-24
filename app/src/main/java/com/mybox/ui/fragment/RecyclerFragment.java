package com.mybox.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.classic.adapter.CommonRecyclerAdapter;
import com.classic.adapter.interfaces.ImageLoad;
import com.mybox.AppContext;
import com.mybox.adpter.NewsAdapter;
import com.mybox.adpter.Recommend5LiveListAdapter;
import com.mybox.adpter.RecommendGridViewAdapter;
import com.mybox.adpter.RecommendType8ListAdapter;
import com.mybox.base.BaseComponentFragment;
import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.listener.MoreOnClickListener;
import com.mybox.model.AdBigImageData;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;
import com.mybox.utils.FitScreenUtil;
import com.mybox.utils.Logs;
import com.mybox.utils.SizeUtils;
import com.mybox.widget.AutoScrollViewPager;
import com.mybox.widget.GridViewWithHeaderAndFooter;
import com.mybox.widget.PointView;
import com.mybox.widget.XListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 使用开源CommmenAdapter的RecyclerView
 */

public class RecyclerFragment extends BaseComponentFragment<RecommendPresenter> implements RecommendView,
        CommonRecyclerAdapter.OnItemClickListener, CommonRecyclerAdapter.OnItemLongClickListener {
    private static final String TAG = "Rec3Fragment";
    private RecommendAdapter adapter;
    private LayoutInflater mLayoutInflater;
    private FinalBitmap fb;
    private SparseArray<RecommendViewHolder> convertViews = new SparseArray<>();
    private SparseArray<LiveChannelItemListener> mListeners = new SparseArray<>();
    private SparseArray<MoreOnClickListener> mMoreListeners = new SparseArray<>();
    private List mContentList;
    private AutoScrollViewPager mConvenientBanner;
    private AdBigImageData mLunBoTuAd;
    private List<RecommendedHomeBean.DataEntity.BigImgEntity> mBannerList;
    private View mRootView;
    private View mRecommendHeadView;
    private RecyclerView mRemmendNewListView;
    private TextView mViewFlowTitle;
    private PointView mPointView;

    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_recycler, container, false);
        initView();
        initData();
        initAction();
        return mRootView;
    }

    protected void initView() {

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsAdapter = new NewsAdapter(getActivity(), R.layout.item_none_picture, NewsDataSource.getNewsList());
        mNewsAdapter.setOnItemClickListener(this);
        mNewsAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setAdapter(mNewsAdapter);
    }

    protected void initData() {
        if (AppContext.getBasePath() != null) {
            String mRecUrl = "http://cbox.cntv.cn/json2015/topshouye/tuijianaoyun/index.json";
            if (mRecUrl != null) {
                mPresenter.loadData(mRecUrl);
            }
        }
    }

    protected void initAction() {}

    /**
     * templateType ＝    1， 大图＋2列小橫图  2， 2列小橫图 3，4左图右文  5，大图＋3列小橫图  6，7横向滑动 8 瀑布流 9 一张大图
     */
    private void initContentData(RecommendedHomeBean mRecommendedHomeBean) {
        Logs.e(TAG, "-------------------------> initContentData ");
        try {
            synchronized (RecyclerFragment.class) {

                mContentList = new ArrayList();
                if (mRecommendedHomeBean.getData() != null) {
                    if (!"0".equals(mRecommendedHomeBean.getData().getInteractliveControl())                         //互动直播
                            && !mRecommendedHomeBean.getData().getInteractlive1().isEmpty()) {

                        mContentList.add(mRecommendedHomeBean.getData().getInteractlive1());
                    }

                    if (mRecommendedHomeBean.getData().getColumnList() != null
                            && !mRecommendedHomeBean.getData().getColumnList().isEmpty()) {                    //通用模块集合 1.2.8

                        List mlist = new ArrayList();
                        for (int i = 0; i < mRecommendedHomeBean.getData().getColumnList().size(); i++) {
                            if (mRecommendedHomeBean.getData().getColumnList().get(i)
                                    instanceof RecommendedHomeBean.DataEntity.ColumnListEntity
                                    && (mRecommendedHomeBean.getData().getColumnList().get(i)).getInfo() != null) {
                                if (!TextUtils.isEmpty(mRecommendedHomeBean.getData().getColumnList().get(i).getShowControl()) &&
                                        "1".equals(mRecommendedHomeBean.getData().getColumnList().get(i).getShowControl())) {

                                    String type = (mRecommendedHomeBean.getData().getColumnList().get(i)).getTemplateType();
                                    if (type != null) {
                                        if ("1".equals(type)) {
                                            //因为1要大图
                                            try {
                                                mlist.add((mRecommendedHomeBean.getData().getColumnList().get(i)).clone());
                                                mlist.add((mRecommendedHomeBean.getData().getColumnList().get(i)));
                                            } catch (CloneNotSupportedException e) {
                                                e.printStackTrace();
                                            }
                                        } else if ("2".equals(type) || "8".equals(type)) {
                                            mlist.add((mRecommendedHomeBean.getData().getColumnList().get(i)));
                                        }
                                    }
                                }
                            }
                        }

                        mContentList.addAll(mlist);
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
                    try {

                        if (mContentList.get(i) != null
                                && mContentList.get(i) instanceof ArrayList
                                && !((List) mContentList.get(i)).isEmpty()) {

                            if (((List) mContentList.get(i)).get(0) instanceof RecommendedHomeBean.DataEntity.Interactlive1Bean) { //互动直播  6.15  新增

                                List mInteractliveList = (List<RecommendedHomeBean.DataEntity.Interactlive1Bean>) mContentList.get(i);

                                for (Iterator it = mInteractliveList.iterator(); it.hasNext(); ) {

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


                                MyBaseAdapter adapter = new Recommend5LiveListAdapter(AppContext.getInstance(), mInteractliveList);
                                ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                                LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                mListeners.put(i, listener);
                                ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                ((Recommend5LiveListAdapter) adapter).setListener(mListeners.get(i));


                            }

                        } else {
                            if (mContentList.get(i).getClass().equals(RecommendedHomeBean.DataEntity.ColumnListEntity.class)) {
                                RecommendedHomeBean.DataEntity.ColumnListEntity mColumnListEntity
                                        = (RecommendedHomeBean.DataEntity.ColumnListEntity) mContentList.get(i);

                                if (mColumnListEntity.getInfo() != null) {

                                    if (mColumnListEntity.isClone()) {

                                        holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnListEntity.getTitle()));
                                        if (null != mColumnListEntity.getAdImgUrl() && !"".equals(mColumnListEntity.getAdImgUrl())) {
                                            holder.ad_iv.setVisibility(View.VISIBLE);
                                            fb.display(holder.ad_iv, mColumnListEntity.getAdImgUrl());
                                        }
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(1);

                                        List list = null;
                                        if (mColumnListEntity.getInfo() instanceof RecommendHomeColumnListInfo) {
                                            if (((RecommendHomeColumnListInfo) mColumnListEntity.getInfo()).getData().getBigImg().size() > 1) {
                                                list = ((RecommendHomeColumnListInfo) mColumnListEntity.getInfo()).getData().getBigImg().subList(0, 1);
                                            } else {
                                                list = ((RecommendHomeColumnListInfo) mColumnListEntity.getInfo()).getData().getBigImg();
                                            }
                                        }


                                        MyBaseAdapter adapter = new RecommendGridViewAdapter(AppContext.getInstance(), list, (holder.mGridView).getLayoutParams());

                                        if (adapter != null && adapter instanceof RecommendGridViewAdapter) {
                                            if (mContentList != null) {
                                                if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                                    ((RecommendGridViewAdapter) adapter).setIsDoubleTitle(mColumnListEntity.getIsDoubleTitle());
                                                }
                                            }
                                        }

                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);
                                        LiveChannelItemListener listener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                        mListeners.put(i, listener);
                                        ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                        ((RecommendGridViewAdapter) adapter).setListener(mListeners.get(i));


                                        if (!TextUtils.isEmpty(mColumnListEntity.getCategoryControl())
                                                && "1".equals(mColumnListEntity.getCategoryControl())) {
                                            holder.mMoreView.setVisibility(View.VISIBLE);
                                            if (mMoreListeners.get(i) == null) {
                                                MoreOnClickListener moreOnClickListener = new MoreOnClickListener(getActivity(), mContentList, i);
                                                mMoreListeners.put(i, moreOnClickListener);
                                            }
                                            holder.mMoreView.setOnClickListener(mMoreListeners.get(i));
                                        }
                                    } else if (mColumnListEntity.getTemplateType() != null) {
                                        String templateType = mColumnListEntity.getTemplateType();
                                        List<RecommendHomeColumnListInfo.DataEntity.ItemListEntity> mItemList =
                                                ((RecommendHomeColumnListInfo) mColumnListEntity.getInfo()).getData().getItemList();
                                        MyBaseAdapter adapter;
                                        MoreOnClickListener moreListener;
                                        LiveChannelItemListener itemListener;
                                        switch (templateType) {
                                            case "1":
                                            case "2":

                                                if (templateType != null && ("1".equals(templateType))) {
                                                    holder.mGridViewTextContent.setVisibility(View.GONE);
                                                    holder.mLineView.setVisibility(View.GONE);
                                                } else {
                                                    if (!TextUtils.isEmpty(mColumnListEntity.getCategoryControl())
                                                            && "1".equals(mColumnListEntity.getCategoryControl())) {
                                                        holder.mMoreView.setVisibility(View.VISIBLE);
                                                        if (mMoreListeners.get(i) == null) {
                                                            moreListener = new MoreOnClickListener(getActivity(), mContentList, i);
                                                            mMoreListeners.put(i, moreListener);
                                                        }
                                                        holder.mMoreView.setOnClickListener(mMoreListeners.get(i));
                                                    }
                                                }
                                                holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnListEntity.getTitle()));
                                                if (null != mColumnListEntity.getAdImgUrl() && !"".equals(mColumnListEntity.getAdImgUrl())) {
                                                    holder.ad_iv.setVisibility(View.VISIBLE);
                                                    fb.display(holder.ad_iv, mColumnListEntity.getAdImgUrl());
                                                }

                                                ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(2);
                                                adapter = new RecommendGridViewAdapter(AppContext.getInstance(), mItemList);

                                                if (adapter != null && adapter instanceof RecommendGridViewAdapter) {
                                                    if (mContentList != null) {
                                                        if (mContentList.get(i) != null && mContentList.get(i) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                                                            RecommendedHomeBean.DataEntity.ColumnListEntity entity = mColumnListEntity;
                                                            ((RecommendGridViewAdapter) adapter).setIsDoubleTitle(entity.getIsDoubleTitle());
                                                        }
                                                    }
                                                }
                                                ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);
                                                itemListener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                                mListeners.put(i, itemListener);
                                                ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                                ((RecommendGridViewAdapter) adapter).setListener(mListeners.get(i));
                                                break;
                                            case "8":

                                                holder.mGridViewTextContent.setVisibility(View.GONE);

                                                holder.mGridViewLabel.setText(String.format(AppContext.getInstance().getString(R.string.vodnew_title_replace), mColumnListEntity.getTitle()));
                                                if (null != mColumnListEntity.getAdImgUrl() && !"".equals(mColumnListEntity.getAdImgUrl())) {
                                                    holder.ad_iv.setVisibility(View.VISIBLE);
                                                    fb.display(holder.ad_iv, mColumnListEntity.getAdImgUrl());
                                                }
                                                ((GridViewWithHeaderAndFooter) holder.mGridView).setNumColumns(1);
                                                ((GridViewWithHeaderAndFooter) holder.mGridView).setVerticalSpacing(0);

                                                try {
                                                    for (Iterator it = mItemList.iterator(); it.hasNext(); ) {
                                                        RecommendHomeColumnListInfo.DataEntity.ItemListEntity str =
                                                                (RecommendHomeColumnListInfo.DataEntity.ItemListEntity) it.next();
                                                        if ("0".equals(str.getIsShow())) {
                                                            it.remove();
                                                        }
                                                    }
                                                    if (mItemList.size() >= 20) {
                                                        mItemList = mItemList.subList(0, 20);
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }


                                                adapter = new RecommendType8ListAdapter(AppContext.getInstance(), mItemList);
                                                ((RecommendType8ListAdapter) adapter).setType(3);

                                                ((GridViewWithHeaderAndFooter) holder.mGridView).setAdapter(adapter);

                                                if (!TextUtils.isEmpty(mColumnListEntity.getCategoryControl()) &&
                                                        "1".equals(mColumnListEntity.getCategoryControl())) {
                                                    holder.mMoreView.setVisibility(View.VISIBLE);
                                                    if (mMoreListeners.get(i) == null) {
                                                        MoreOnClickListener listener = new MoreOnClickListener(getActivity(), mContentList, i);
                                                        mMoreListeners.put(i, listener);
                                                    }
                                                    holder.mMoreView.setOnClickListener(mMoreListeners.get(i));

                                                }
                                                itemListener = new LiveChannelItemListener(getActivity(), mContentList, i);
                                                mListeners.put(i, itemListener);
                                                ((GridViewWithHeaderAndFooter) holder.mGridView).setOnItemClickListener(mListeners.get(i));
                                                ((RecommendType8ListAdapter) adapter).setListener(mListeners.get(i));

                                                break;

                                            default:
                                                break;


                                        }

                                    }

                                }
                            }
                        }
                    } catch (Exception e) {
                        Logs.e(TAG, e.toString());
                    }
                    if (holder != null) {
                        convertViews.put(i, holder);
                    }

                }

                initBigImageItem(mRecommendedHomeBean);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initBigImageItem(RecommendedHomeBean mRecommendedHomeBean) {


        try {
            mBannerList = mRecommendedHomeBean.getData().getBigImg();
            //IsShow
            for (Iterator it = mBannerList.iterator(); it.hasNext(); ) {
                RecommendedHomeBean.DataEntity.BigImgEntity str = (RecommendedHomeBean.DataEntity.BigImgEntity) it.next();
                if (str.getIsShow() != null && str.getIsShow().equals("0")) {
                    it.remove();
                    Logs.e(TAG, "  remove 滚动图 isShow == 0");
                }

            }

            if (mLunBoTuAd != null && !"ad".equals(mBannerList.get(mBannerList.size() - 1).getOrder())) {
                RecommendedHomeBean.DataEntity.BigImgEntity lastAd = new RecommendedHomeBean.DataEntity.BigImgEntity();
                lastAd.setTitle(mLunBoTuAd.getText());
                lastAd.setImgUrl(mLunBoTuAd.getUrl());
                lastAd.setPcUrl(mLunBoTuAd.getClick());
                lastAd.setVtype("7");
                lastAd.setOrder("ad");
                mBannerList.add(lastAd);

                Logs.e(TAG, "  add 轮播图 ads");
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
                    ImageView view = new ImageView(container.getContext());
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    fb.display(view, mBannerList.get(position).getImgUrl());
                    container.addView(view);
                    return view;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });

            mViewFlowTitle = (TextView) mRecommendHeadView.findViewById(R.id.tvBannerTitle);// 推荐轮播大图的标题
            // 推荐轮播大图的标题
            mViewFlowTitle.setText(mBannerList.get(0).getTitle());

            mPointView = (PointView) mRecommendHeadView.findViewById(R.id.viewflowindiclay);
            mPointView.setPointCount(mBannerList.size());
            mPointView.setCurrentIndex(0);

            mConvenientBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int i) {
                    mViewFlowTitle.setText(mBannerList.get(i).getTitle());
                    mPointView.setCurrentIndex(i);
                }
            });

            mConvenientBanner.setScrollFactgor(5);
            mConvenientBanner.setOffscreenPageLimit(4);
            mConvenientBanner.startAutoScroll();
            mConvenientBanner.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
                @Override
                public void onPageClick(AutoScrollViewPager autoScrollPager, int position) {
                    //eventClick(mBannerList.get(position));
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataSuccess(RecommendedHomeBean mRecommendedHomeBean) {
        initContentData(mRecommendedHomeBean);
    }

    @Override public void getDataFail(String msg) {}

    @Override public void showLoading(String msg) {}

    @Override public void hideLoading() {}

    @Override public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        Toast.makeText(getActivity(), "RecyclerView onItemClick,position:" + position,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        Toast.makeText(getActivity(),
                "RecyclerView onItemLongClick,position:" + position, Toast.LENGTH_SHORT).show();
    }
}
