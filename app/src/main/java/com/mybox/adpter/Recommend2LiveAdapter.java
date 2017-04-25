package com.mybox.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mybox.AppContextLike;
import com.mybox.base.MyBaseAdapter;
import com.mybox.listener.LiveChannelItemListener;
import com.mybox.model.LiveChannelProgressBean;
import com.mybox.model.RecommendedHomeBean;
import com.mybox.net.retrofit.HttpCallback;
import com.mybox.net.retrofit.HttpTools;
import com.mybox.R;
import com.mybox.utils.FinalBitmap;

import java.util.List;


public class Recommend2LiveAdapter extends MyBaseAdapter {

    private Context mContext;
    private FinalBitmap fb;

    public Recommend2LiveAdapter(Context mContext, List normalLiveList) {
        this.items = normalLiveList;
        this.mContext = mContext;
        fb = FinalBitmap.create(mContext);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_newsub_normallive_item, null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.ivPic);
            holder.mTextView = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        fb.display3(holder.mImageView, ((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position)).getChannelImg());
        if (TextUtils.isEmpty(((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position)).getTitle1())) {
            holder.mTextView.setText(((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position)).getTitle());
            getLiveChannelInfo((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position), Recommend2LiveAdapter.this, position);
        } else {
            holder.mTextView.setText(((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position)).getTitle1());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(null, null, position, 0);
                }
            }
        });
        return convertView;
    }

    private LiveChannelItemListener mListener;

    public void setListener(LiveChannelItemListener mListener) {
        this.mListener = mListener;
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }
    private void getLiveChannelInfo(RecommendedHomeBean.DataEntity.NormalLiveListEntity bean, final Recommend2LiveAdapter adapter, final int position) {
        if (items.get(position) == null || ((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position)).requestCount > 3) {
            return;
        }
        String baseUrl = AppContextLike.getBasePath().get("living_url");
        String url = baseUrl + "&c=" + bean.getChannelId();

        HttpTools.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                try {
                    List<LiveChannelProgressBean> result = LiveChannelProgressBean.convertFromJsonObject(t);

                    if (items.get(position) != null) {
                        ((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position)).requestCount++;
                    }
                    if (result != null && result.size() != 0) {
                        LiveChannelProgressBean bean = result.get(0);
                        if (items.get(position) != null) {
                            ((RecommendedHomeBean.DataEntity.NormalLiveListEntity) items.get(position)).setTitle1(bean.getT());
                        }

                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }
        });

    }
}