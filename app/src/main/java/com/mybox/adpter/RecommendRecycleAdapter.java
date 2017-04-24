package com.mybox.adpter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;

import java.util.List;

/**
 * (RTFSC)
 * 描述 Okgo1.01
 *
 * @Author: 作者  GHQ
 * @Version: 版本  1.0  2017/4/18
 */
public class RecommendRecycleAdapter extends CommonRecyclerAdapter{
    public RecommendRecycleAdapter(@NonNull Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public RecommendRecycleAdapter(@NonNull Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, Object item, int position) {

    }
}
