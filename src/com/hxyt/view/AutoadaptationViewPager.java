package com.hxyt.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/** 
 * @author  作者 陈修园
 * @date 创建时间：2015-10-20 下午2:21:01 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class AutoadaptationViewPager extends ViewPager {

	public AutoadaptationViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AutoadaptationViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		
		
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}
	
}
