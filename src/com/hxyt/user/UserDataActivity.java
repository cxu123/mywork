package com.hxyt.user;

import java.io.File;
import java.util.UUID;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxyt.BaseActivity;
import com.hxyt.ProjectCommand;
import com.hxyt.R;
import com.hxyt.utils.L;
import com.hxyt.utils.SDCardUtils;
import com.hxyt.utils.TakePictureTool;
import com.lidroid.xutils.BitmapUtils;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-22 上午10:51:22
 * @version 1.0 个人资料activity
 * @parameter
 * @since
 * @return
 */
public class UserDataActivity extends BaseActivity {

	private ImageView head_left_image;

	private TextView headTitle;
	/**
	 * 保存按钮
	 */
	private Button user_data_save;

	/**
	 * 用户头像
	 */
	private ImageView user_data_user_head_image;
	/**
	 * 拍照工具
	 */
//	private TakePictureTool takePictureTool;

	/**
	 * 用户拍照获得的路径
	 */
	private String user_head_image_paht_act = "";
	
	/**
	 * 用户是否选择了图片
	 */
	private boolean user_head_image_change=false;
	
	/**
	 * bitmap工具
	 */
	private BitmapUtils bitmapUtils;
	
	/**
	 * 修改密码
	 */
	private Button user_data_reset_pass_word;

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_user_data);
		bitmapUtils = new BitmapUtils(this, SDCardUtils.getSDCardPath()
				+ File.separator + ProjectCommand.ProjectFolder.TMP_FILE_PATH);
		head_left_image = (ImageView) findViewById(R.id.head_left_image);
		head_left_image.setVisibility(View.VISIBLE);
		headTitle = (TextView) findViewById(R.id.head_title);
		user_data_save = (Button) findViewById(R.id.user_data_save);
		user_data_user_head_image = (ImageView) findViewById(R.id.user_data_user_head_image);
		user_data_reset_pass_word=(Button) findViewById(R.id.user_data_reset_pass_word);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		headTitle.setText("个人资料");
	}

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub
		head_left_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDataActivity.this.finish();
			}
		});
		user_data_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (user_head_image_change) {
					app.user_head_image_has_change=user_head_image_change;
				}
				
			}
		});
		// 拍照
		user_data_user_head_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user_head_image_paht_act = SDCardUtils.getSDCardPath()
						+ File.separator
						+ ProjectCommand.ProjectFolder.TMP_FILE_PATH
						+ UUID.randomUUID().toString() + ".jpg";
				L.v(app.user_head_image_paht);
				L.v(user_head_image_paht_act);
				// takePictureTool = new
				// TakePictureTool(user_head_image_paht_act,
				// UserDataActivity.this, 0);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File imagefile = new File(user_head_image_paht_act);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(imagefile));
				startActivityForResult(intent, 100);
			}
		});
		user_data_reset_pass_word.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDataActivity.this.openActivity(ResetPassWordActivity.class);
			}
		});
	}

	
	/**
	 * 接收用户拍照的图片
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == -1) {
			switch (requestCode) {
			case 200:
				// 相册
				app.user_head_image_paht = TakePictureTool.getImagePath(data,
						UserDataActivity.this);
				user_head_image_change=true;
				L.v(user_head_image_paht_act);
				break;

			case 100:
				// 相机
				L.v(user_head_image_paht_act);
				app.user_head_image_paht = user_head_image_paht_act;
				bitmapUtils.display(user_data_user_head_image,
						user_head_image_paht_act);
				user_head_image_change=true;
				break;
			}
		}

	}
}
