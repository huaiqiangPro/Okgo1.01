package com.mybox.listener;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.mybox.model.RecommendedHomeBean;

import java.util.List;


/**
 * (RTFSC)
 * 描述 CBoxV6.1
 *
 * @Author: 作者  huaiqiang
 * @Version: 版本  1.0  2016/5/18
 */

public class MoreOnClickListener implements View.OnClickListener {
    private int position;
    private List items;
    private Context mContext;

    public MoreOnClickListener(Context mContext, List items, int position) {
        this.mContext = mContext;
        this.items = items;
        this.position = position;
    }

    /**
     * CategoryId 1-8 保持线上逻辑,9热榜 ,10 互动
     * * 其中
     * 分类专题页：categoryId--->5、6、7、8、9
     * //TODO 11--->奥运模板、12--->两列横图模板（16/9）、13--->三列竖图模板
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        try {
            if (items.get(position) instanceof RecommendedHomeBean.DataEntity.ColumnListEntity) {
                RecommendedHomeBean.DataEntity.ColumnListEntity bean = (RecommendedHomeBean.DataEntity.ColumnListEntity) items.get(position);
                if (bean != null && !TextUtils.isEmpty(bean.getCategoryControl()) && "1".equals(bean.getCategoryControl())) {
                    if (!TextUtils.isEmpty(bean.getCategoryId())) {
                        int id;
                        try {
                            id = Integer.parseInt(bean.getCategoryId());
                        } catch (Exception e) {
                            e.printStackTrace();
                            id = -1;
                        }
                        Intent intent = new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        if (id != -1) {
                            switch (id) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
//                                case 9:
//                                    intent.setClass(mContext, Cat7Activity.class);
//                                    intent.putExtra("listurl", bean.getCategoryUrl());
//                                    intent.putExtra("tag", "分类");
//                                    intent.putExtra("category", bean.getCategoryId());
//                                    intent.putExtra("cid", bean.getCategoryCid());
//                                    intent.putExtra("adid", bean.getCategoryAdid());
//                                    intent.putExtra("headtitle", bean.getTitle());
//                                    intent.putExtra("order", bean.getOrder());
//                                    mContext.startActivity(intent);
//                                    Crumb.setCrumb(Crumb.LAYER3.value(), bean.getTitle() + "_更多");
//                                    System.out.print(Crumb.getCrumb());
//                                    break;
//                                case 10:
//                                    if (mContext instanceof MainActivity) {
//                                        ((MainActivity) mContext).mTabHost.setCurrentTab(3);
//                                    }
//                                    break;
//                                case 11:
//                                    intent.putExtra("listurl", bean.getCategoryUrl());
//                                    intent.putExtra("title", bean.getTitle());
//                                    intent.putExtra("tag", "奥运专题");
//                                    intent.putExtra("adid", bean.getCategoryAdid());
//                                    intent.setClass(AppContext.getInstance(), Cat11Activity.class);
//                                    mContext.startActivity(intent);
//                                    // 推荐-精选里的奥运会，将二级改为2016奥运会
////                                    Crumb.setCrumb(Crumb.LAYER2.value(), "2016奥运会");
//                                    Crumb.setCrumb(Crumb.LAYER3.value(), bean.getTitle() + "_更多");
//                                    System.out.print(Crumb.getCrumb());
//                                    break;
//                                case 12:
//                                case 13:
//                                    intent.putExtra(VrAreaUIEnum.VR_AREA_TYPE.name(), id + "");
//                                    intent.putExtra(VrAreaUIEnum.VR_AREA_TITLE.name(), bean.getTitle());
//                                    intent.putExtra(VrAreaUIEnum.VR_AREA_URL.name(), bean.getCategoryUrl());
//                                    intent.setClass(AppContext.getInstance(), VRAreaActivity.class);
//                                    mContext.startActivity(intent);
//                                    Crumb.setCrumb(Crumb.LAYER3.value(), bean.getTitle() + "_更多");
//                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
