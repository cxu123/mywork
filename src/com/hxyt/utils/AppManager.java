package com.hxyt.utils;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * @author 锟斤拷锟斤拷 锟斤拷锟斤拷园
 * @date 锟斤拷锟斤拷时锟戒：2015-7-17 锟斤拷锟斤拷11:21:10
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class AppManager {

	// Activity栈
	private static Stack<Activity> activityStack;
	// 锟斤拷锟斤拷模式
	private static AppManager instance;



	/**
	 * 锟斤拷一实锟斤拷
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 锟斤拷锟紸ctivity锟斤拷锟斤拷栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 锟斤拷取锟斤拷前Activity锟斤拷锟斤拷栈锟斤拷锟斤拷锟揭伙拷锟窖癸拷锟侥ｏ拷
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 缁撴潫Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 鍏抽棴Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 鍏抽棴鍏ㄩ儴Activity
	 */
	public void finishAllActivity() {
		for (int i = 0; i < activityStack.size(); i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 閫�鍑篴pp
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}
