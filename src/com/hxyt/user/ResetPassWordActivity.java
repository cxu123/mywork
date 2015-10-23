package com.hxyt.user;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxyt.BaseActivity;
import com.hxyt.R;

/** 
 * @author  作者 陈修园
 * @date 创建时间：2015-10-23 下午4:21:50 
 * @version 1.0 用户重设密码
 * @parameter  
 * @since  
 * @return  
 */
public class ResetPassWordActivity extends BaseActivity {

	private ImageView head_left_image;
	private TextView head_title;
	
	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_reset_pass_word);
		head_left_image=(ImageView) findViewById(R.id.head_left_image);
		head_title=(TextView) findViewById(R.id.head_title);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		head_left_image.setVisibility(View.VISIBLE);
		head_title.setText("修改密码");
	}

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub
		head_left_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ResetPassWordActivity.this.finish();
			}
		});
	}

}
