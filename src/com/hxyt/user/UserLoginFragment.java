package com.hxyt.user;

import com.hxyt.R;
import com.hxyt.view.LoadingView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * 用户登录
 * @author 陈修园
 *
 */
public class UserLoginFragment extends Fragment {
	private View rootView;

	private Button user_login;
	private EditText user_login_name;
	private EditText user_login_password;

	private UserModel userModel;
	
	private String userName;
	private String userPassWord;
	
	private LoadingView loadingView;
	private Button goto_region;
	
	private UserLogin userLogin;
	
	private Button forgot_password;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (savedInstanceState != null) {
			return super.onCreateView(inflater, container, savedInstanceState);

		} else {
			rootView = inflater.inflate(R.layout.fragment_user_login,
					container, false);
			user_login = (Button) rootView.findViewById(R.id.user_login);
			user_login_name = (EditText) rootView
					.findViewById(R.id.user_login_name);
			user_login_password = (EditText) rootView
					.findViewById(R.id.user_login_password);
			goto_region=(Button) rootView.findViewById(R.id.goto_region);
			forgot_password=(Button) rootView.findViewById(R.id.forgot_password);
			iniClick();
			userModel=new UserModel();
			loadingView=new LoadingView(getActivity());
			return rootView;
		}

	}

	private void iniClick() {
		user_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userName=user_login_name.getText().toString();
				userPassWord=user_login_password.getText().toString();
				if (TextUtils.isEmpty(userName)) {
					Toast.makeText(getActivity(), "请输入手机号码", Toast.LENGTH_SHORT).show();
					return ;
				}
				if (TextUtils.isEmpty(userPassWord)) {
					Toast.makeText(getActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
					return ;
				}
				if (userName!=null&&userPassWord!=null) {
					userModel.setUserLoginListener(new Handler(){
						@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what==1) {
								Toast.makeText(getActivity(),"登录成功", Toast.LENGTH_SHORT).show();
								if (userLogin!=null) {
									userLogin.loginInfo();
								}
							}else {
								Toast.makeText(getActivity(),(String) msg.obj, Toast.LENGTH_SHORT).show();
							}
							loadingView.Viewdismiss();
							userLogin.loginInfo();
						}
					});
					userModel.userLogin(userName, userPassWord, getActivity());
					loadingView.show();
				}
			}
		});
		
		goto_region.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (userLogin!=null) {
					userLogin.gotoRegister();
				}
			}
		});
		
		forgot_password.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (userLogin!=null) {
					userLogin.gotoForgotPassword();	
				}
			}
		});
	}
	
	public void setUserLoginListener(UserLogin userLoginSucceed){
		this.userLogin=userLoginSucceed;
	}
	
	/**
	 * 用户登录界面接口
	 * @author 陈修园
	 *
	 */
	public interface UserLogin{
		/**
		 * 用户登录成功接口
		 */
		void loginInfo();
		/**
		 * 用户跳转注册接口
		 */
		void gotoRegister();
		/**
		 * 用户忘记密码跳转
		 */
		void gotoForgotPassword();
	}
	

}
