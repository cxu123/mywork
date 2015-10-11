package com.hxyt.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-29 下午12:00:17
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class CallPhone {

    public final static void call(String phoneNO, Activity activity) {
	Intent intent = new Intent();
	System.out.println("real电话");
	// 激活源代码,添加intent对象
	intent.setAction("android.intent.action.CALL");
	intent.addCategory("android.intent.category.DEFAULT");
	intent.setData(Uri.parse("tel:" + phoneNO));
	// 激活Intent
	activity.startActivity(intent);
    }

}
