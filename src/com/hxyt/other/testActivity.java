package com.hxyt.other;

import android.os.Bundle;

import com.hxyt.BaseActivity;
import com.hxyt.R;
import com.hxyt.view.LoadingView;

/** 
 * @author  作者 陈修园
 * @date 创建时间：2015-10-13 上午10:04:55 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class testActivity extends BaseActivity {

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		
		//setContentView(R.layout.loading_beat_view);
		
		
		
		setContentView(R.layout.activity_test);
		LoadingView loadingView=new LoadingView(this,1);
		loadingView.show();
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub

	}

}
