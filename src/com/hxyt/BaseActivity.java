package com.hxyt;

import com.hxyt.utils.L;
import com.hxyt.utils.SystemBarTintManager;
import com.hxyt.view.LoadingView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * @author 陈修园
 * @date 2015-7-17 10:59:15
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public abstract class BaseActivity extends Activity {
	protected String tag = "";
	// protected String TAG="";
	protected AppContent app;
	private LoadingView loadingView;

	// protected String
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		app = (AppContent) getApplication();
		app.appManager.addActivity(this);
		tag = this.getLocalClassName();
		loadingView = new LoadingView(this);
		iniActionbarColor();
		findViews();
		init(savedInstanceState);
		setViewOnlister();

	}

	private void iniActionbarColor() {
		// TODO Auto-generated method stub

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.statusbar_bg);

	}

	/**
	 * 首先运行findViews();
	 */
	protected abstract void findViews();

	/**
	 * 然后才是init();
	 */
	protected abstract void init(Bundle savedInstanceState);

	/**
	 * 最后是setViewOnlister();
	 */
	protected abstract void setViewOnlister();

	protected void log(String str) {
		if (tag != null && tag.length() > 0) {
			L.v(tag, str);
		}
	}

	protected void showToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_LONG).show();
	}

	/**
	 * Toast方法
	 * 
	 * @param str
	 * @param flag
	 *            不传入或者为true 的时候显示
	 */
	protected void showToast(String str, boolean flag) {
		if (flag) {
			Toast.makeText(this, str, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		}

	}

	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	protected void openActivity(Class<?> pClass, Intent intent) {
		if (intent != null) {
			startActivity(intent);

		} else {
			startActivity(new Intent(this, pClass));
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		app.appManager.finishActivity(this);
	}

	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	public void showLoadingView() {
		loadingView.show();
	}

	public void dismissLoadingView() {
		loadingView.Viewdismiss();
	}

	public void finishActivity() {


		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		app.appManager.finishActivity(this);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

		
		
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		// super.startActivity(intent);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if (app == null) {
			app = (AppContent) getApplication();
		}
	}

}
