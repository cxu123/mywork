package com.hxyt.baofoo;

import com.baofoo.sdk.BaofooPayActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-14 上午11:21:26
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class BaoFooPayUtils {

	/**
	 * 订单ID 通过服务器获得不填写则为默认的
	 * 开发阶段
	 */
	private String orderNo;
	
	private String defOrderNo="201510140110000400545460";
	
	public final static int REQUEST_CODE_BAOFOO_SDK = 100;
	
	private Activity activity;
	
	/**
	 * 
	 * @param orderNo 订单序列号
	 */
	public BaoFooPayUtils(String orderNo,Activity activity){
		this.orderNo=orderNo;
		this.activity=activity;
		startWork();
	}
	
	
	private void startWork(){
		Intent payintent = new Intent(activity, BaofooPayActivity.class);
		
		if (orderNo==null||orderNo.length()<=0) {
			payintent.putExtra(BaofooPayActivity.PAY_TOKEN,
					defOrderNo);
			payintent.putExtra(BaofooPayActivity.PAY_BUSINESS, false);
			Toast.makeText(activity, "订单流水号为空，启用测试模式", Toast.LENGTH_LONG).show();
		}else {
			payintent.putExtra(BaofooPayActivity.PAY_TOKEN,
					orderNo);
			payintent.putExtra(BaofooPayActivity.PAY_BUSINESS, true);
			//Toast.makeText(activity, "订单流水号为空，启用测试模式", Toast.LENGTH_LONG).show();
		}
		activity.startActivityForResult(payintent, REQUEST_CODE_BAOFOO_SDK);
		
	}
	
	

	
	
}
