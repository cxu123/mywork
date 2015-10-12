package com.hxyt.user;

import com.hxyt.R;
import com.hxyt.utils.L;
import com.hxyt.utils.SMSBroadcastReceiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-22 上午9:18:06
 * @version 1.0 用户注册
 * @parameter
 * @since
 * @return
 */

public class UserRegisterFragment extends Fragment {

	private SMSBroadcastReceiver mSMSBroadcastReceiver;
	private View rootView;
	private EditText user_name;
	private EditText user_verification_code;
	private EditText user_password;
	private Button get_verification_code;
	private ImageView need_user_check;
	private Button user_region;
	private Button goto_login;
	private UserRegisterLister userRegisterLister;
	private boolean userAgreementFlage = true;
	private UserModel userModel;
	private Button read_user_agreement;
	/**
	 * 获得验证码的冷却时间
	 */
	private int getCodeTime = 30;

	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (savedInstanceState != null) {
			return super.onCreateView(inflater, container, savedInstanceState);
		} else {
			rootView = inflater.inflate(R.layout.fragment_user_register,
					container, false);
			userModel = new UserModel();
			ini();
			setViewClickLister();
			startMSMReceiver();
			return rootView;
		}
	}

	private void ini() {
		user_name = (EditText) rootView.findViewById(R.id.user_name);
		user_verification_code = (EditText) rootView
				.findViewById(R.id.user_verification_code);
		user_password = (EditText) rootView.findViewById(R.id.user_password);
		user_region = (Button) rootView.findViewById(R.id.user_region);
		get_verification_code = (Button) rootView
				.findViewById(R.id.verification_code);
		goto_login = (Button) rootView.findViewById(R.id.goto_login);
		need_user_check = (ImageView) rootView
				.findViewById(R.id.need_user_check);
		read_user_agreement = (Button) rootView
				.findViewById(R.id.read_user_agreement);
	}

	private void setViewClickLister() {
		get_verification_code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getVerificationCode();
			}
		});
		goto_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (userRegisterLister != null) {
					userRegisterLister.gotoUserLogin();
				}
			}
		});
		need_user_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (userRegisterLister != null) {
					userRegisterLister.readUserAgreement();
				}
			}
		});
		user_region.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userRegisterStrart();
			}
		});
		need_user_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userAgreementFlage = (userAgreementFlage == false ? true
						: false);
				if (userAgreementFlage) {
					need_user_check.setImageResource(R.drawable.user_check);
				} else {
					need_user_check.setImageResource(R.drawable.user_uncheck);
				}
				L.v(userAgreementFlage);
			}
		});
		read_user_agreement.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (userRegisterLister != null) {
					userRegisterLister.readUserAgreement();
				}

			}
		});

		get_verification_code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getVerificationCode();
				goNext();
			}
		});
	}

	/**
	 * 获得验证码
	 */
	private void getVerificationCode() {

		if ((!TextUtils.isEmpty(user_verification_code.getText()) && (user_verification_code
				.getText().length() == 11))) {
			userModel.setVerificationCodeListener(new Handler(){
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					
						Toast.makeText(getActivity(), (String)msg.obj, Toast.LENGTH_SHORT)
						.show();
					
//						Toast.makeText(getActivity(), (String)msg.obj, Toast.LENGTH_SHORT)
//						.show();
					
				}
			});
			userModel.getVerificationCode(user_verification_code.getText()
					.toString(), getActivity());
		} else {
			Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT)
					.show();
		}

	}

	/**
	 * 用户注册
	 */
	private void userRegisterStrart() {

		String phoneNO = user_name.getText().toString();
		String password = user_password.getText().toString();
		String verificationCode = user_verification_code.getText().toString();
		if (TextUtils.isEmpty(phoneNO) || phoneNO.length() < 11) {
			Toast.makeText(getActivity(), "请输入手机号码或检查手机号码是否输入正确",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(password)) {
			Toast.makeText(getActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(verificationCode)) {
			Toast.makeText(getActivity(), "请输入验证码", Toast.LENGTH_SHORT).show();
			return;
		}
		if (!userAgreementFlage) {
			Toast.makeText(getActivity(), "请输仔细阅读用户协议并且并同意", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		userModel.setUserRegisterListener(new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 1) {
					if (userRegisterLister != null) {
						userRegisterLister.registerSucceed();
					}
				} else {
					Toast.makeText(getActivity(), (String) msg.obj,
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		userModel.userRegister(phoneNO, password, verificationCode,
				getActivity());
	}

	public void setUserRegisterLister(UserRegisterLister registerLister) {
		this.userRegisterLister = registerLister;
	}

	private void startMSMReceiver() {
		// Intent intent = new Intent();
		// intent.setAction("android.intent.action.CALL");
		// this.sendBroadcast(intent);
		mSMSBroadcastReceiver = new SMSBroadcastReceiver();
		// 实例化过滤器并设置要过滤的广播
		IntentFilter intentFilter = new IntentFilter(ACTION);
		intentFilter.setPriority(Integer.MAX_VALUE);
		// 注册广播
		getActivity().registerReceiver(mSMSBroadcastReceiver, intentFilter);
		mSMSBroadcastReceiver
				.setOnReceivedMessageListener(new SMSBroadcastReceiver.MessageListener() {
					@Override
					public void onReceived(String message, int type) {
						if (type == 1) {
							// 验证码
							L.v(message);
						}
						if (type == 2) {
							// 邀请码
							L.v(message);
						}
					}
				});
	}

	/**
	 * 时间计数器
	 */
	protected void goNext() {
		new CountDownTimer(60 * 1000, 1000) {
			@Override
			public void onFinish() {
				// done
				getCodeTime = 60;
				get_verification_code.setEnabled(true);
				get_verification_code.setText("发送验证码");
			}

			@Override
			public void onTick(long arg0) {
				// 每1000毫秒回调的方法
				get_verification_code.setEnabled(false);
				get_verification_code.setText(getCodeTime--+"s");
			}

		}.start();
	}

	/**
	 * 注册Fragment的回调接口
	 * 
	 * @author 陈修园
	 * 
	 */
	public interface UserRegisterLister {
		/**
		 * 注册成功
		 */
		void registerSucceed();

		/**
		 * 阅读用户协议
		 */
		void readUserAgreement();

		/**
		 * 返回登录界面
		 */
		void gotoUserLogin();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		if (getActivity() != null) {
			getActivity().unregisterReceiver(mSMSBroadcastReceiver);
			L.v("解除广播注册");
		}

	}

}
