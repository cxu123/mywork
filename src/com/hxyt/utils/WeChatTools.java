package com.hxyt.utils;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/** 
 * @author  作者 陈修园 微信分享工具类
 * @date 创建时间：2015-10-12 上午10:34:47 
 * @version 1.0 只实现分享到微信 
 * @parameter  
 * @since  
 * @return  
 */
public class WeChatTools {

	/**
	 * 微信开发网站申请的APPID
	 */
	public static String APPID="wxe3e2730e92e047ff";
	
	private IWXAPI api;
	/**
	 * 上下文
	 */
	private Context context;
	
	
	
	public WeChatTools(Context context){
		this.context =context;
		regToWeChat();
	}
	
	
	public void shareStringToWeChat(String contentStr){
		
		// 初始化一个WXTextObject对象
		WXTextObject textObj = new WXTextObject();
		textObj.text = contentStr;
		// 用WXTextObject对象初始化一个WXMediaMessage对象
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		// 发送文本类型的消息时，title字段不起作用
		// msg.title = "Will be ignored";
		msg.description = "将进酒";

		// 构造一个Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
		req.message = msg;
		req.scene =  SendMessageToWX.Req.WXSceneTimeline;
		
		// 调用api接口发送数据到微信
		api.sendReq(req);
	}
	
	
	/**
	 * 注册到微信
	 */
	private void regToWeChat(){
		api=WXAPIFactory.createWXAPI(context, APPID,true);
		api.registerApp(APPID);
	}
	/**
	 * 添加时间戳
	 * @param type
	 * @return
	 */
	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
}
