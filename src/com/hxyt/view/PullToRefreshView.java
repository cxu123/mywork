package com.hxyt.view;

import java.util.Timer;
import java.util.TimerTask;

import com.hxyt.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** 
 * @author  作者 陈修园: 
 * @date 创建时间：2015-9-23 上午9:31:40 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */

/**
 * @author Administrator
 * 
 */
public class PullToRefreshView extends RelativeLayout {

    /**
     * 头部View 下拉刷新View
     */
    private View headView;

    /**
     * 底部View 上拉加载更多View
     */
    private View bottomView;

    /**
     * 中间内容信息View
     */
    private View contentView;

    /**
     * 是否是第一次进行layout？
     */
    private boolean isLayout = true;

    /**
     * 下拉的距离
     */
    private float pullDownY = 0;
    /**
     * 上拉的距离
     */
    private float pullUpY = 0;

    /*
     * / 过滤多点触碰
     */
    private int mEvents;

    public PullToRefreshView(Context context) {
	super(context);
	ini(context);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
	super(context, attrs);
	ini(context);
    }

    public PullToRefreshView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	ini(context);
    }

    private void ini(Context context) {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
	if (isLayout) {
	    // TODO Auto-generated method stub
	    int i = getChildCount();
	    isLayout = false;
	    if (i > 3) {
		headView = getChildAt(0);
		contentView = getChildAt(1);
		bottomView = getChildAt(2);
	    } else {
		throw new ExceptionInInitializerError(
			"必须要有下拉和上拉的View和内容View，共3个，你只定义了" + i + "个");
	    }
	}
	/**
	 * 头部View的位置
	 */
	headView.layout(0,
		(int) (pullDownY + pullUpY) - headView.getMeasuredHeight(),
		headView.getMeasuredWidth(), (int) (pullDownY + pullUpY));
	/**
	 * 显示内容的View的位置
	 */
	contentView.layout(0, (int) (pullDownY + pullUpY),
		contentView.getMeasuredWidth(), (int) (pullDownY + pullUpY)
			+ contentView.getMeasuredHeight());
	/**
	 * 底部View的位置
	 */
	bottomView.layout(0,
		(int) (pullDownY + pullUpY) + headView.getMeasuredHeight(),
		bottomView.getMeasuredWidth(),
		(int) (pullDownY + pullUpY) + contentView.getMeasuredHeight()
			+ bottomView.getMeasuredHeight());

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
	// TODO Auto-generated method stub
	switch (ev.getActionMasked()) {
	case MotionEvent.ACTION_DOWN:

	    break;
	case MotionEvent.ACTION_POINTER_DOWN:
	case MotionEvent.ACTION_POINTER_UP:
	    // 过滤多点触碰
	    mEvents = -1;
	    break;
	case MotionEvent.ACTION_MOVE:
	    break;
	case MotionEvent.ACTION_UP:
	    break;
	default:
	    break;
	}

	 super.dispatchTouchEvent(ev);
	 return true;
    }

    /**
     * 刷新加载回调接口
     * 
     */
    public interface OnRefreshListener {
	/**
	 * 刷新操作
	 */
	void onRefresh(PullToRefreshView pullToRefreshView);

	/**
	 * 加载操作
	 */
	void onLoadMore(PullToRefreshView pullToRefreshView);
    }

}
