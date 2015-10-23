package com.hxyt.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-22 上午10:38:49
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class RoundImageView extends ImageView {

	@SuppressLint("NewApi")
	public RoundImageView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		iniData(context);
	}

	public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		iniData(context);
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		iniData(context);
	}

	public RoundImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		iniData(context);

	}

	private void iniData(Context context) {

	}

}
