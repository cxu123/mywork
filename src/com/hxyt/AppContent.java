package com.hxyt;

import cn.jpush.android.api.JPushInterface;

import com.hxyt.home.HomeModle;
import com.hxyt.home.ViewPageHelp;
import com.hxyt.utils.AppManager;
import com.hxyt.utils.CrashHandler;
import com.hxyt.utils.L;
import com.hxyt.utils.T;
import com.lidroid.xutils.util.PreferencesCookieStore;

import android.R.string;
import android.app.Application;
import android.os.Handler;
import android.os.Message;

/** 
 * @author  陈修元
 * @date 2015-7-17 11:20:29 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class AppContent extends Application {
	/**
	 * activity 保存
	 */
	public AppManager appManager;
	/**
	 * 用户信息保存
	 */
	//public UserInfoBeen userInfoBeen;
	
	/**
	 * 登录会话
	 */
	public static PreferencesCookieStore preferencesCookieStore;
	
	public ViewPageHelp viewPageHelp;
	/**
	 * 用户头像的图片路径
	 */
	public String user_head_image_paht="";
	/**
	 * 用户图片的路径是否改变
	 */
	public boolean user_head_image_has_change=false;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		appManager=AppManager.getAppManager();
		//下载广告图片
		downloadImage();
		CrashHandler crashHandler=CrashHandler.getInstance();
		//crashHandler.init(this);
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
	}
	
	public static void setPreferencesCookieStore(
			PreferencesCookieStore preferencesCookieStore) {
		AppContent.preferencesCookieStore = preferencesCookieStore;
	}
	
	public static PreferencesCookieStore getPreferencesCookieStore(){
		return AppContent.preferencesCookieStore;
	}
	
	
	
	
	/**
	 * 下载服务器网络图片
	 */
	private void downloadImage(){
	    HomeModle homeModle=new HomeModle();
	    homeModle.setAdHandler(new Handler(){
		@Override
		public void handleMessage(Message msg) {
		    // TODO Auto-generated method stub
		    if (msg.what==1) {
			reSetAdImage();
			L.i("5副图片已经下载完成");
		    }
		    super.handleMessage(msg);
		}
	    });
	    homeModle.getAdImage(getApplicationContext());
	    
	}
	
	/**
	 * 重新设置
	 */
	private void reSetAdImage(){
	    if (viewPageHelp!=null) {
		
	    }
	}
}
