package com.mybox.base;

import android.content.Context;
import android.database.DataSetObserver;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据适配器基础类
 * 
 * @Description
 * @author Cheng Yong
 * @version 1.0 2012-7-15
 * @class MyBaseAdapter
 */
public abstract class MyBaseAdapter extends BaseAdapter {
	protected List items = new ArrayList();
	protected Context mContext;


	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observer != null) {
			super.unregisterDataSetObserver(observer);
		}
	}

	@Override
	public int getCount() {
		if (items == null) {
			return 0;
		} else {
			return items.size();
		}
	}

	@Override
	public Object getItem(int position) {
		if (items == null) {
			return null;
		} else {
			return items.get(position);
		}
	}

	/**
	 * 覆盖填充
	 *
	 * @param items
	 * @Description
	 * @author Cheng Yong
	 * @version 1.0 2012-7-10
	 */
	public void setItems(List items) {
		this.items.clear();
		if (items != null) {
			this.items.addAll(items);
		}
	}



	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 获取集合
	 * 
	 * @Description
	 * @return
	 * @author Cheng Yong
	 * @version 1.0 2012-7-10
	 */
	public List getItems() {
		return items;
	}


	public void clear() {
		items.clear();
		notifyDataSetChanged();
	}

	public void changeData(List items) {
		if (items != null) {
			this.items = items;
			this.notifyDataSetChanged();
		}
	}

	/**
	 * 非覆盖填充
	 *
	 * @param items
	 * @Description
	 * @author Cheng Yong
	 * @version 1.0 2012-7-10
	 */
	public void addItems(List items) {
		this.items.addAll(items);
		this.notifyDataSetChanged();
	}
}
