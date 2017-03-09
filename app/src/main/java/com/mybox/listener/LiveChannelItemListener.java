package com.mybox.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


import com.mybox.AppContext;
import com.mybox.model.RecommendHomeColumnListInfo;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.utils.Logs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * 描述 CBoxV6.1
 *
 * @Author: 作者  huaiqiang
 * @Version: 版本  1.0  2016/5/18
 * @ vtype @
 * 1--->单视频;2--->视频集竖图(VMS视频集);3--->视频集横图(VMS视频集);4--->栏目横图(VMS视频集);
 * 5--->爱西柚视频集;6--->特辑(CMS视频集);7--->url打开浏览器(普通H5)8--->直播频道 ;
 * 9--->分类(专题);11--->投票(H5);12--->答题(H5);13--->抽奖(H5);14--->评论(原生);15--->春晚互动;
 * 16--->多视角1(直播);17--->多视角2(直播);18--->秀场直播 19--->互动列表
 * //TODO 21--->VR直播 ;22--->VR点播
 * <p>
 * 其中
 * 分类专题页：categoryId--->5、6、7、8、9
 * //TODO 11--->奥运模板、12--->两列横图模板（16/9）、13--->三列竖图模板
 */
public class
LiveChannelItemListener implements AdapterView.OnItemClickListener, View.OnClickListener {
    private String TAG = "LiveChannelItemListener";
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private int position;
    private List items;
    private Context mContext;
    private RecommendedHomeBean.DataEntity.ColumnListEntity recommendedHomeBean;// 综艺，电视剧，瀑布流的实体类
    private long lastClickTime = 0;

    public LiveChannelItemListener(Context context, List items) {
        this.mContext = context;
        this.items = items;
    }

    public LiveChannelItemListener(Context mContext, List items, int position) {
        this.mContext = mContext;
        this.items = items;
        this.position = position;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

        if (items.get(position).getClass().equals(String.class)) {
            return;
        }
        try {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onRecommendItemClick(items, position, i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param items
     * @param position
     * @param i
     */
    private void onRecommendItemClick(List items, int position, int i) {
        if (mContext == null)
            mContext = AppContext.getInstance();

        Logs.e("TAG","点中-------------------");
        if (items.get(position).getClass().equals(ArrayList.class)) {
            if (((List) items.get(position)).get(0) instanceof RecommendedHomeBean.DataEntity.Interactlive1Bean) {
                //互动直播
                RecommendedHomeBean.DataEntity.Interactlive1Bean bean = (RecommendedHomeBean.DataEntity.Interactlive1Bean) ((List) items.get(position)).get(i);
                eventClick(bean, "宫格推荐区");

            } else if (((List) items.get(position)).get(0) instanceof RecommendedHomeBean.DataEntity.LiveCategoryListEntity) {

            } else if (((List) items.get(position)).get(0) instanceof RecommendedHomeBean.DataEntity.NormalLiveListEntity) {
                //正在直播
            }
        } else {
            //下方列表  包括横向滑动
            if (((RecommendedHomeBean.DataEntity.ColumnListEntity) items.get(position)).getInfo() != null) {
                if (((RecommendedHomeBean.DataEntity.ColumnListEntity) items.get(position)).getInfo() instanceof ArrayList) {


                } else {

                }
            }
        }
    }

    //宫格点击事件
    private void eventClick(RecommendedHomeBean.DataEntity.Interactlive1Bean bean, String title) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        int type = Integer.valueOf(bean.getVtype());

    }

    //大图点击事件
    private void eventClick(RecommendHomeColumnListInfo.DataEntity.BigImgEntity bean, String title) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        int type = Integer.valueOf(bean.getVtype());

    }

    //顶部大图以外的单击事件
    private void eventClick(RecommendHomeColumnListInfo.DataEntity.ItemListEntity bean, String title) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        int type = Integer.valueOf(bean.getVtype());

    }

    //精选轮播大图
    public void eventClickByBanner() {

        {
            if (mContext == null)
                mContext = AppContext.getInstance();
            if (items == null || items.size() == 0)
                return;

            if (items.get(0) instanceof RecommendedHomeBean.DataEntity.BigImgEntity) {
                RecommendedHomeBean.DataEntity.BigImgEntity bean = (RecommendedHomeBean.DataEntity.BigImgEntity) items.get(position);

                int type = 0;
                try {
                    type = Integer.valueOf(bean.getVtype());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getTag() == null) {
            return;
        }

        String[] tTags = v.getTag().toString().split(",");

        if (tTags == null || tTags.length == 0) {
            return;
        }

        int tPosition = Integer.parseInt(tTags[0]);
        int tIndex = Integer.parseInt(tTags[1]);

        if (items == null || items.isEmpty()) {
            return;
        }
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}