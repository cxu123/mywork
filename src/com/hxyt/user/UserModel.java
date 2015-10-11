package com.hxyt.user;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.hxyt.ProjectCommand;
import com.hxyt.bean.UserInfo;
import com.hxyt.utils.Base64Util;
import com.hxyt.utils.HttpJsonTool;
import com.hxyt.utils.SPUtils;

import android.content.Context;
import android.os.Handler;

/**
 * 
 * @author 陈修园 用户登录、注册功能
 */
public class UserModel {

	HttpJsonTool userLoginHttp;
	HttpJsonTool userRegionHttp;
	HttpJsonTool verificationCodeHttp;
	HttpJsonTool userResetPassWordHttp;
	Handler user_ResetPassWord_Handler;
	Handler user_Longi_Handler;
	Handler user_register_Handler;
	Handler code_handle;
	private Type type = new TypeToken<List<UserInfo>>() {}.getType();
	

//	public UserModel(Context context) {
//		this.userLoginHttp = new HttpJsonTool(context);
//	}

	/**
	 * 用户登录
	 * 
	 * @param userName
	 * @param userPassWord
	 */
	public void userLogin(String userName, String userPassWord,Context context) {
		userLoginHttp=new HttpJsonTool(context,true);
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("userName", userName);
		data.put("userPassWord", userPassWord);
		userLoginHttp.SetOnLister(user_Longi_Handler);
		userLoginHttp.StartWork(data, ProjectCommand.USER.USER_LOGIN, type, null);
	}

	/**
	 * 设置用户登录监听
	 */
	public void setUserLoginListener(Handler handler) {
		this.user_Longi_Handler=handler;
	}

	/**
	 * 用户注册监听设置
	 * @param handler
	 */
	public void setUserRegisterListener(Handler handler){
		this.user_register_Handler=handler;
	}
	
	/**
	 * 用户注册
	 */
	public void userRegister(String phoneNO,String password,String verificationCode,Context context){
		userRegionHttp=new HttpJsonTool(context);
		HashMap< String, String> data=new HashMap<String, String>();
		userRegionHttp.SetOnLister(user_register_Handler);
		userRegionHttp.StartWork(data, ProjectCommand.USER.USER_REGISTER, null, null);
	}
	
	
	/**
	 * 设置获得验证码监听
	 * @param handler
	 */
	public void setVerificationCodeListener(Handler handler){
		this.code_handle=handler;
	}
	
	
	/**
	 * 用户获得验证码
	 */
	public void getVerificationCode(String phoneNO,Context context){
		verificationCodeHttp=new HttpJsonTool(context);
		HashMap<String, String> data=new HashMap<String, String>();
		verificationCodeHttp.SetOnLister(code_handle);
		verificationCodeHttp.StartWork(data, ProjectCommand.USER.VERIFICATION_CODE, null, null);
	}
	
	/**
	 * 保存用户名和密码
	 * @param userName
	 * @param userPassWord
	 */
	public void savePassWord(String userName,String userPassWord,Context context){
		String pw=Base64Util.toHex(userPassWord);
		SPUtils.put(context, "userName", userName);
		SPUtils.put(context, "userPassWord", pw);
	}
	/**
	 * 用户自动登录
	 * @param context
	 * @param handler
	 * @return
	 */
	public boolean userAUTOLogin(Context context,Handler handler){
		String userName=(String) SPUtils.get(context, "userName", "err");
		String userPassWord=Base64Util.fromHex((String) SPUtils.get(context, "userPassWord", "err"));
		setUserLoginListener(handler);
		userLogin(userName, userPassWord, context);
		return false;
	}
	
	public void setUserSetPassWordListener(Handler handler){
		this.user_ResetPassWord_Handler=handler;
	}
	
	/**
	 * 用户充值密码
	 */
	public void userSetPassWord(Context context,String oldPassWord,String newPassWord,String userId){
		userResetPassWordHttp=new HttpJsonTool(context);
		userResetPassWordHttp.SetOnLister(user_register_Handler);
		HashMap<String, String> data=new HashMap<String, String>();
		data.put("userId", userId);
		data.put("newPassWord", newPassWord);
		data.put("oldPassWord", oldPassWord);
		userResetPassWordHttp.StartWork(data, ProjectCommand.USER.USER_RESET_PASSWORD, null, null);
	}
	
	
	
}
