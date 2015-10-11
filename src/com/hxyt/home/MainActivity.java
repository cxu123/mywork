package com.hxyt.home;

import java.io.File;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hxyt.BaseActivity;
import com.hxyt.ProjectCommand;
import com.hxyt.R;
import com.hxyt.other.GuidanceActivity;
import com.hxyt.utils.ActivityGesture;
import com.hxyt.utils.HttpJsonTool;
import com.hxyt.utils.L;
import com.hxyt.utils.SDCardUtils;
import com.hxyt.utils.SPUtils;

public class MainActivity extends BaseActivity {

	Button button;
	Button user_login;
	Button start_Guidance_Activity;
	ActivityGesture gesture;
	private int firstLogin = 0;

	@SuppressLint("NewApi")
	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
		// View view = findViewById(android.R.id.content);
		// view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		// view.setSystemUiVisibility(View.INVISIBLE);
		button = (Button) findViewById(R.id.test);
		user_login = (Button) findViewById(R.id.user_login);
		start_Guidance_Activity = (Button) findViewById(R.id.start_Guidance_Activity);
		gesture = new ActivityGesture(this);
		firstLogin = (Integer) SPUtils.get(this, "FirstLogin", 0);
	}

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openActivity(Home_activity.class);
			}
		});

		start_Guidance_Activity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openActivity(GuidanceActivity.class);
			}
		});

		user_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// openActivity(User_login_Activity.class);
			}
		});

	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		makeFolder();
		checkUpErrLog();
		if (firstLogin == 0) {
			openActivity(GuidanceActivity.class);
		} else {
			openActivity(GuidanceActivity.class);
		}

	}

	private void checkUpErrLog() {
		HttpJsonTool httpJsonTool = new HttpJsonTool(this);
		httpJsonTool.checkLogAndUpload(this);
	}

	// 创建app说需要的文件夹如app更新文件夹、app临时文件夹等
	private void makeFolder() {
		// TODO Auto-generated method stub
		if (SDCardUtils.isSDCardEnable()) {
			String rootFolder = SDCardUtils.getSDCardPath() + File.separator;
			File root = new File(rootFolder
					+ ProjectCommand.ProjectFolder.ROOT_FOLDER);
			File download = new File(rootFolder
					+ ProjectCommand.ProjectFolder.ROOT_FOLDER + File.separator
					+ ProjectCommand.ProjectFolder.IMAGE_FILE_PATH);
			File log = new File(rootFolder
					+ ProjectCommand.ProjectFolder.ROOT_FOLDER + File.separator
					+ ProjectCommand.ProjectFolder.LOG_FILE_PATH);
			File temp = new File(rootFolder
					+ ProjectCommand.ProjectFolder.ROOT_FOLDER + File.separator
					+ ProjectCommand.ProjectFolder.TMP_FILE_PATH);
			// L.v("root="+root.getPath());
			// L.v("download="+download.getPath());
			// L.v("log="+log.getPath());
			// L.v("temp="+temp.getPath());
			//
			if (!root.exists()) {
				// root.
				root.mkdir();
			}
			if (!download.exists()) {
				download.mkdir();
			}
			if (!log.exists()) {
				log.mkdir();
			}
			if (!temp.exists()) {
				temp.mkdir();
			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gesture.setOnTouch(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		gesture.setTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}
