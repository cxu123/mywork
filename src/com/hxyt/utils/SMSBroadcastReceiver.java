package com.hxyt.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2015-7-7 上午9:50:55
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {

	private static MessageListener mMessageListener;
	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	/**
	 * 邀请码
	 */
	private String Invitation_code = "邀请码";
	private String end_invitation_code = "，CE货的";
	/**
	 * 验证码
	 */
	private String Msm_Code = "短信验证码为";
	private String end_STR = "，请您";
	/**
	 * 查看短信是验证码还是邀请码
	 */
	private int type = -1;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		L.v("获得短信", "获得短信");
		String messagecontent="" ;
		if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			for (Object pdu : pdus) {
				SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
				messagecontent=messagecontent+smsMessage.getDisplayMessageBody();
			}
			L.v("content", "长度" + messagecontent.length());
			L.v("content", "->" + messagecontent);
			int size1 = messagecontent.indexOf(Invitation_code);
			L.v("短信获得的str1位置", "--->" + size1);
			int size2 = messagecontent.indexOf(end_STR);
			L.v("短信获得的str2位置", "--->" + size2);
			String code;
			if (size1 == -1) {
				size1 = messagecontent.indexOf(Msm_Code);
				if (size2 == -1) {
					// end_invitation_code
					L.v("content", "长度" + messagecontent.length());
					size2 = messagecontent.indexOf(end_invitation_code);
					L.v("短信获得的str2位置", "--->" + size2);
				}
				type = 1;
				code = messagecontent.substring(size1 + 6, size2);
			} else {
				type = 2;
				if (size2 == -1) {
					L.v("content", "长度" + messagecontent.length());
					size2 = messagecontent.indexOf(end_invitation_code);
					L.v("短信获得的str2位置", "--->" + size2);
				}
				code = messagecontent.substring(size1 + 3, size2);
			}

			if (mMessageListener != null) {
				mMessageListener.onReceived(code, type);
				// abortBroadcast();
			}

		}
	}

	// 回调接口
	/**
	 * 
	 * @author 陈修园
	 * 
	 */
	public interface MessageListener {
		/**
		 * 
		 * @Title: onReceived
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param message
		 * @param @param type =1 是短信验证码 type=2 是邀请码
		 * @return void 返回类型
		 * @throws
		 */
		public void onReceived(String message, int type);
	}

	public void setOnReceivedMessageListener(MessageListener messageListener) {
		this.mMessageListener = messageListener;
	}
}
