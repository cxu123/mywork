package com.hxyt.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-16 下午3:48:33
 * @version 1.0 建设银行圆形菜单
 * @parameter
 * @since
 * @return
 */
public class CycloidalMenu extends ViewGroup {

	@SuppressLint("NewApi")
	public CycloidalMenu(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("NewApi")
	public CycloidalMenu(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public CycloidalMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CycloidalMenu(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub

		/**
		 * 根据传入的参数，分别获取测量模式和测量值
		 */
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		int height = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		// menu item测量模式
		int childMode = MeasureSpec.EXACTLY;

		int defChildWidth = width / 2;
		int defChildHeight = height / 5;

		// 做出文字内容的孩子显示
		int contentChildWidth = width / 6;
		int contentChildHeight = height / 2;

		int cut = getChildCount();

		// 计算menu item的尺寸；以及和设置好的模式，去对item进行测量
		int makeMeasureSpec = -1;

		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		for (int i = 0; i < cut; i++) {
			View view = getChildAt(i);
			if (i == 0) {

				view.measure(contentChildWidth, contentChildHeight);
			} else {
				view.measure(defChildWidth, defChildHeight);
			}

		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int cut = getChildCount();
		int lastRingt = 0;
		for (int i = 0; i < cut; i++) {
			View view = getChildAt(i);
			view.layout(lastRingt + 20, 0, lastRingt + view.getMeasuredWidth(),
					view.getMeasuredHeight());
			lastRingt = view.getMeasuredWidth() + lastRingt;
		}
	}

	// @Override
	// public LayoutParams generateLayoutParams(AttributeSet attrs) {
	// // TODO Auto-generated method stub
	// return (LayoutParams) new MarginLayoutParams(getContext(), attrs);
	// }

}
