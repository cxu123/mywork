package com.hxyt.user;

import java.io.File;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxyt.BaseActivity;
import com.hxyt.ProjectCommand;
import com.hxyt.R;
import com.hxyt.user.pay.UserWithdrawDepositActivity;
import com.hxyt.utils.SDCardUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-23 上午10:02:16
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class UserAccountActivity extends BaseActivity {

	private ImageView head_left_image;
	private TextView head_title;
	/**
	 * 用户头像
	 */
	private ImageView user_account_user_head_portrait;

	/**
	 * bitmap工具
	 */
	private BitmapUtils bitmapUtils;
	/**
	 * 提现
	 */
	private View user_meet_project_root_view;
	
	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_user_account);
		head_left_image = (ImageView) findViewById(R.id.head_left_image);
		head_left_image.setVisibility(View.VISIBLE);
		head_title = (TextView) findViewById(R.id.head_title);
		user_account_user_head_portrait = (ImageView) findViewById(R.id.user_account_user_head_portrait);
		user_meet_project_root_view=findViewById(R.id.user_meet_project_root_view);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		head_title.setText("我的账户");
		bitmapUtils = new BitmapUtils(this, SDCardUtils.getSDCardPath()
				+ File.separator + ProjectCommand.ProjectFolder.TMP_FILE_PATH);
	}

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub
		head_left_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserAccountActivity.this.finish();
			}
		});
		//提现按钮去提现activity
		user_meet_project_root_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserAccountActivity.this.openActivity(UserWithdrawDepositActivity.class);
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (app.user_head_image_has_change) {
			if (bitmapUtils!=null) {
				bitmapUtils.display(user_account_user_head_portrait, app.user_head_image_paht);
			}else {
				bitmapUtils = new BitmapUtils(this, SDCardUtils.getSDCardPath()
						+ File.separator + ProjectCommand.ProjectFolder.TMP_FILE_PATH);
				bitmapUtils.display(user_account_user_head_portrait, app.user_head_image_paht);
			}
			
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if (app.user_head_image_has_change) {
			if (bitmapUtils!=null) {
				bitmapUtils.display(user_account_user_head_portrait, app.user_head_image_paht);
			}else {
				bitmapUtils = new BitmapUtils(this, SDCardUtils.getSDCardPath()
						+ File.separator + ProjectCommand.ProjectFolder.TMP_FILE_PATH);
				bitmapUtils.display(user_account_user_head_portrait, app.user_head_image_paht);
			}
			
		}
	}

}
