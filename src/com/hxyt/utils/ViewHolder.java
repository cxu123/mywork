package com.hxyt.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-16 上午11:02:23
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class ViewHolder {
	private SparseArray<View> holderViews;
	private int mPosition;
	private View mConvertView;

	public ViewHolder(Context context, int layoutId, ViewGroup parent,
			int position) {
		this.mPosition = position;
		this.holderViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	public static ViewHolder getViewHolder(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, layoutId, parent, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	/**
	 * 通过ViewID获取控件
	 * 
	 * @param viewID
	 *            ；
	 * @return view
	 */
	public <T extends View> T getView(int viewId) {
		View view = holderViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			holderViews.put(viewId, view);
			return (T) view;
		} else {
			return (T) view;
		}

	}

	public View getConvertView() {
		return mConvertView;
	}
}
