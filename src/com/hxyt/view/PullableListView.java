package com.hxyt.view;

import com.hxyt.view.PullToRefreshLayout.Pullable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * 
 * @author 陈修园
 * 可下拉刷新的ListView
 * 实现接口来
 *
 */
public class PullableListView extends ListView implements Pullable
{

	public PullableListView(Context context)
	{
		super(context);
	}

	public PullableListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableListView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	
	}

	

	
	@Override
	public boolean canPullDown()
	{
		if (getChildAt(0)==null) {
			return true;
		}
		if (getCount() == 0)
		{
			// 没有item的时候也可以下拉刷新
			return true;
		} else if (getFirstVisiblePosition() == 0
				&& getChildAt(0).getTop() >= 0)
		{
			// 滑到ListView的顶部了
			return true;
		} else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
		if (getCount() == 0)
		{
			// 没有item的时候也可以上拉加载
			return true;
		} else if (getLastVisiblePosition() == (getCount() - 1))
		{
			// 滑到底部了
			if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
					&& getChildAt(
							getLastVisiblePosition()
									- getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
			{
				return true;
			}
		}
		return false;
	}

//	@Override
//	public void onScrollStateChanged(AbsListView view, int scrollState) {
//		// TODO Auto-generated method stub
//		L.v("scrollState="+scrollState);
//	}
//
//	@Override
//	public void onScroll(AbsListView view, int firstVisibleItem,
//			int visibleItemCount, int totalItemCount) {
//		// TODO Auto-generated method stub
//		L.v("visibleItemCount="+visibleItemCount);
//		L.v("visibleItemCount="+totalItemCount);
//	}
}
