package com.mybox.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mybox.AppContextLike;
import com.mybox.base.BaseComponentFragment;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.listener.MoreOnClickListener;
import com.mybox.model.AdBigImageData;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;
import com.mybox.utils.Logs;
import com.mybox.widget.AutoScrollViewPager;
import com.mybox.widget.PointView;
import com.mybox.widget.XListView;

import java.util.ArrayList;
import java.util.List;


public class Rec1Fragment extends BaseComponentFragment<RecommendPresenter> implements RecommendView {
    private static final String TAG = "Rec1Fragment";
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
    private TextView mViewFlowTitle;
    private XListView mRemmendNewListView;
    private PointView mPointView;
    private LinearLayout mRemmendLoading;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutInflater = LayoutInflater.from(getActivity());
        fb = FinalBitmap.create(AppContextLike.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = mLayoutInflater.inflate(R.layout.fragment_main_rec1, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initAction();
    }

    protected void initView() {

        mRemmendLoading = (LinearLayout) mRootView.findViewById(R.id.tuijian_loading);
        mRemmendNewListView = (XListView) mRootView.findViewById(R.id.remmend_new_list_view);
        mRemmendNewListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRemmendNewListView.setFooterDividersEnabled(false);
        mRemmendNewListView.setPullLoadEnable(false);
        mRemmendNewListView.setPullRefreshEnable(true);

        mRecommendHeadView = LayoutInflater.from(AppContextLike.getInstance()).inflate(R.layout.common_banner, null);// 推荐头部
        mConvenientBanner = (AutoScrollViewPager) mRecommendHeadView.findViewById(R.id.convenientBanner);
        mViewFlowTitle = (TextView) mRecommendHeadView.findViewById(R.id.tvBannerTitle);
    }

    protected void initData() {
        if (AppContextLike.getBasePath() != null) {
            String mRecUrl = "http://cbox.cntv.cn/json2015/moban60/topshouye/index.json";
            mRecUrl = "http://cbox.cntv.cn/json2015/topshouye/tuijianaoyun/index.json";
            if (mRecUrl != null) {
                mPresenter.loadData(mRecUrl);
            }
        }
    }

    private void initializeData(RecommendedHomeBean mRecommendedHomeBean) {

        mContentList = new ArrayList();
        RecommendedHomeBean.DataEntity data = mRecommendedHomeBean.getData();
        if (data != null) {
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
            }
        }

        initContentData(mContentList);
    }

    protected void initAction() {
        if (mRemmendNewListView != null) {
            mRemmendNewListView.setXListViewListener(new XListView.IXListViewListener() {
                @Override
                public void onRefresh() {
                    initData();
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

    /**
     * templateType ＝    1， 大图＋2列小橫图  2， 2列小橫图 3，4左图右文  5，大图＋3列小橫图  6，7横向滑动 8 瀑布流 9 一张大图
     * @param mContentList
     */
    private void initContentData(List mContentList) {
        Logs.e(TAG, "-------------------------> initContentData ");
        Rec1Adapter adapter = new Rec1Adapter(AppContextLike.getInstance(), mContentList);
        mRemmendNewListView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mConvenientBanner != null) {
            mConvenientBanner.startAutoScroll();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mConvenientBanner != null) {
            mConvenientBanner.stopAutoScroll();

        }
    }


    @Override
    public void getDataSuccess(RecommendedHomeBean mRecommendedHomeBean) {

        initializeData(mRecommendedHomeBean);
    }



    @Override
    public void getDataFail(String msg) {
        if (mRemmendNewListView != null) {
            mRemmendNewListView.stopRefresh();
        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {
        mRemmendLoading.setVisibility(View.GONE);
        if (mRemmendNewListView != null) {
            mRemmendNewListView.stopRefresh();
        }
    }

}
