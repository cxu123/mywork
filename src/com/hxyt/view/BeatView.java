package com.hxyt.view;

import java.util.List;

import com.hxyt.utils.L;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-13 上午9:41:00
 * @version 1.0 高仿UC浏览器 加载页面的加载View 闪烁View
 * @parameter
 * @since
 * @return
 */
public class BeatView extends ViewGroup {

	private List<View> childrenView;

	private AnimationSet animationSet;

	public BeatView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		ini();
	}

	@SuppressLint("NewApi")
	public BeatView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		ini();
	}

	public BeatView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		ini();
	}

	public BeatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		ini();
	}

	@Override
	protected ViewGroup.LayoutParams generateLayoutParams(
			ViewGroup.LayoutParams p) {
		return new MarginLayoutParams(p);
	}

	@Override
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
		return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
	}

	private void ini() {

	}

	/**
	 * 先测量自己的宽高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		// 获得子控件的高度
		int childHeight = 0;
		int childWeight = 0;
		int cut = getChildCount();
		for (int i = 0; i < cut; i++) {
			View childView = getChildAt(i);
			MarginLayoutParams lp = (MarginLayoutParams) childView
					.getLayoutParams();
			childWeight = childWeight + childView.getMeasuredWidth()
					+ lp.leftMargin + lp.rightMargin;
			// 获得子控件的最高高度
			childHeight = (childHeight > (childView.getMeasuredHeight()
					+ lp.topMargin + lp.bottomMargin) ? childHeight
					: (childView.getMeasuredHeight()
							+ childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin));
		}

		// 如果宽是类容包裹那么就是子控件的宽度
		if (modeWidth == MeasureSpec.AT_MOST) {
			sizeWidth = childWeight+(childWeight/2);
		}
		// 如果高是内容包裹那么就是子控件的高度
		if (modeHeight == MeasureSpec.AT_MOST) {
			sizeHeight = childHeight+(childHeight/2);
		}
		setMeasuredDimension(sizeWidth, sizeHeight);
	}

	// 全部第子View只能是剧中显示
	/**
	 * 在设置子控件的宽高
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int mViewGroupWidth = getMeasuredWidth();
		int mViewGroupHight = getMeasuredHeight();

		int cut = getChildCount();
		// 全部子控件 的高度
		int childHeightTotal = 0;
		// 全部子控件的宽宽度
		int childWightTotal = 0;
		for (int i = 0; i < cut; i++) {
			View view = getChildAt(i);
			childWightTotal = childWightTotal + view.getMeasuredWidth();
			// childHeightTotal = childHeightTotal +
			// view.getMeasuredHeight()+view.;
			// L.v("childWightTotal="+childWightTotal);
		}
		int viewStart = mViewGroupWidth - childWightTotal;
		if (childWightTotal <= mViewGroupWidth) {
			// 即用来表示右边又来记录上一个view的边界
			int childWidth = (viewStart / 2);
			for (int i = 0; i < cut; i++) {
				int left = 0;
				int top = 0;
				int right = 0;
				int bottom = 0;
				View view = getChildAt(i);
				left = childWidth;
				right = childWidth + view.getMeasuredWidth();
				childWidth = right+10;
				top = (mViewGroupHight / 2) - (view.getMeasuredHeight() / 2);
				bottom = (mViewGroupHight / 2) + (view.getMeasuredHeight() / 2);
				view.layout(left, top, right, bottom);
				
			}
		} else {
			L.e("子控件总宽度比父控件宽度宽");
		}
	}
	
	
	public void startViewAnimation(){
		
		int cut=getChildCount();
		for (int i = 0; i < cut; i++) {
			View view=getChildAt(i);
			setViewAnimation(view, i);
		}
		
		
		
		
	}

	private void setViewAnimation(final View view, int i) {
		animationSet = new AnimationSet(true);
		// 参数1：x轴的初始值
		// 参数2：x轴收缩后的值
		// 参数3：y轴的初始值
		// 参数4：y轴收缩后的值
		// 参数5：确定x轴坐标的类型
		// 参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
		// 参数7：确定y轴坐标的类型
		// 参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
		final ScaleAnimation scaleAnimation_start = new ScaleAnimation(0.5f, 1.0f,
				0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);

		final ScaleAnimation scaleAnimation_end = new ScaleAnimation(1.0f, 0.5f,
				1.0f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation_start.setDuration(1000);
		scaleAnimation_start.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				view.clearAnimation();
				// animationSet.reset();
				view.startAnimation(scaleAnimation_end);
			}
		});
		scaleAnimation_end.setDuration(1000);
		scaleAnimation_end.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				view.clearAnimation();
				view.startAnimation(scaleAnimation_start);
			}
		});
		if ((i%2)==0) {
			animationSet.addAnimation(scaleAnimation_start);
		}else {
			animationSet.addAnimation(scaleAnimation_end);
		}
		
		view.startAnimation(animationSet);
	}

}
