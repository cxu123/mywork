package com.hxyt.home;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxyt.AppContent;
import com.hxyt.BaseActivity;
import com.hxyt.BaseFragmentActivity;
import com.hxyt.R;
import com.hxyt.news.ProductNewsFrament;
import com.hxyt.order.OrderFragment;
import com.hxyt.other.GuidanceActivity;
import com.hxyt.other.UserAgreement;
import com.hxyt.user.UserLoginFragement;
import com.hxyt.user.UserLoginFragement.UserLogin;
import com.hxyt.user.UserRegisterFragment;
import com.hxyt.user.UserRegisterFragment.UserRegisterLister;
import com.hxyt.utils.L;
import com.hxyt.utils.SPUtils;
import com.hxyt.utils.T;

public class HomeActivity extends BaseFragmentActivity {

	private LinearLayout first;
	private LinearLayout secend;
	private LinearLayout threed;
	private LinearLayout four;
	private FragmentManager fragmentManager;
	// 现在变色的item
	// 上一个点击的按钮
	private int nowButtonNo = 0;
	// 各个功能
	private OrderFragment orderFragment;
	private HomeFrament homeFrament;
	private ProductNewsFrament productNewsFrament;
	private UserRegisterFragment userRegionFragment;
	private UserLoginFragement userLoginFragement;
	private UserAgreement userAgreement;

	private ImageButton one, two, three, image_four;
	// 点击下的颜色
	private String checkColor = "#EE7424";

	private TextView imageStrHome;
	private TextView imageStrProject;
	private TextView imageStrMy;
	private TextView imageStrOther;

	/**
	 * 各个Fragement接口
	 */
	private UserLogin userLogin;
	private UserRegisterLister userRegisterLister;
	/**
	 * 顶部image
	 */
	private ImageView headImage;
	/**
	 * 顶部左边文字
	 */
	private TextView headLeft;
	/**
	 * 顶部标题
	 */
	private TextView headtitle;

	/**
	 * UI 3 用户的多个情况的标记 1、用户登录UI 2、用户注册UI 3、用户协议UI
	 */
	private int userIntFlag = 1;

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activit_home);
		first = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
		secend = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
		threed = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
		four = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);
		one = (ImageButton) findViewById(R.id.btn_tab_bottom_weixin);
		two = (ImageButton) findViewById(R.id.btn_tab_bottom_friend);
		three = (ImageButton) findViewById(R.id.btn_tab_bottom_contact);
		image_four = (ImageButton) findViewById(R.id.btn_tab_bottom_setting);
		fragmentManager = HomeActivity.this.getSupportFragmentManager();
		imageStrHome = (TextView) findViewById(R.id.home_home);
		imageStrProject = (TextView) findViewById(R.id.home_project);
		imageStrMy = (TextView) findViewById(R.id.home_my);
		imageStrOther = (TextView) findViewById(R.id.home_other);
		headImage = (ImageView) findViewById(R.id.head_left_image);
		headLeft = (TextView) findViewById(R.id.head_left);
		headtitle = (TextView) findViewById(R.id.head_title);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		/**
		 * 用户登录接口
		 */
		userLogin = new UserLogin() {

			@Override
			public void loginInfo() {
				// TODO Auto-generated method stub
				L.v("用户登录成功");
			}

			@Override
			public void gotoRegister() {
				// TODO Auto-generated method stub
				L.v("用户注册");
				hideFragmentsAndChangeImage(5);
			}

			@Override
			public void gotoForgotPassword() {
				// TODO Auto-generated method stub
				L.v("忘记密码");
			}
		};
		/**
		 * 用户注册接口
		 */
		userRegisterLister = new UserRegisterLister() {

			@Override
			public void registerSucceed() {
				// TODO Auto-generated method stub

			}

			@Override
			public void readUserAgreement() {
				// TODO Auto-generated method stub
				hideFragmentsAndChangeImage(6);
			}

			@Override
			public void gotoUserLogin() {
				// TODO Auto-generated method stub
				userIntFlag = 1;
				hideFragmentsAndChangeImage(3);
			}
		};
		
		if (savedInstanceState == null) {
			hideFragmentsAndChangeImage(1);
		} else {
			L.e("数据已经被恢复不用");
			// nowButtonNo = savedInstanceState.getInt("nowButtonNo");
			// hideFragmentsAndChangeImage(0);
			// nowButtonNo = 0;
			hideFragmentsAndChangeImage(savedInstanceState
					.getInt("nowButtonNo"));
			nowButtonNo = savedInstanceState.getInt("nowButtonNo");
			// hideFragmentAll();
		}
	}

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub
		first.setOnClickListener(new bottomViewClick());
		secend.setOnClickListener(new bottomViewClick());
		threed.setOnClickListener(new bottomViewClick());
		four.setOnClickListener(new bottomViewClick());

	}

	private void hideFragmentAll() {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (homeFrament != null) {
			// 开启一个Fragment事务
			transaction.hide(homeFrament);

		}
		if (orderFragment != null) {
			transaction.hide(orderFragment);
		}
		if (userLoginFragement != null) {
			transaction.hide(userLoginFragement);
		}
		if (productNewsFrament != null) {
			transaction.hide(productNewsFrament);
		}
	}

	/**
	 * UI显示隐藏处理事件
	 * 
	 * @param id
	 *            1、首页 2、项目 3、用户 4、其他 5、注册 6、用户协议 {3、包含的UI有 (5、注册 3、用户登录
	 *            6、用户协议)}
	 * 
	 */
	private void hideFragmentsAndChangeImage(int id) {
		// if (fragmentManager==null) {
		//
		// }
		//
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 隐藏上一个frament 和改变图标颜色
		if (id != nowButtonNo) {

			switch (nowButtonNo) {
			case 1:
				if (homeFrament != null) {
					// 开启一个Fragment事务
					transaction.hide(homeFrament);
					app.viewPageHelp.close();
				}
				// one.setImageDrawable(new Drawable(checkColor));#FF5654
				// one.setBackgroundColor(Color.parseColor("#FF5654"));
				one.setBackgroundResource(R.drawable.home);
				imageStrHome.setTextColor(Color.parseColor("#1E1F1F"));
				// imageStrHome.settextco
				break;
			case 2:
				if (orderFragment != null) {
					transaction.hide(orderFragment);
				}
				// two.setBackgroundColor(Color.parseColor("#7836FF"));
				two.setBackgroundResource(R.drawable.project);
				imageStrProject.setTextColor(Color.parseColor("#1E1F1F"));
				break;
			case 3:

				if (userLoginFragement != null) {
					transaction.hide(userLoginFragement);
				}
				// three.setBackgroundColor(Color.parseColor("#7AA6DD"));
				three.setBackgroundResource(R.drawable.my);
				imageStrMy.setTextColor(Color.parseColor("#1E1F1F"));
				break;
			case 4:
				if (productNewsFrament != null) {
					transaction.hide(productNewsFrament);
				}
				// image_four.setBackgroundColor(Color.parseColor("#BB33DD"));
				image_four.setBackgroundResource(R.drawable.other);
				imageStrOther.setTextColor(Color.parseColor("#1E1F1F"));
				break;
			case 5:
				if (userRegionFragment != null) {
					transaction.hide(userRegionFragment);
				}
				three.setBackgroundResource(R.drawable.my);
				imageStrMy.setTextColor(Color.parseColor("#1E1F1F"));
				break;
			case 6:
				if (userAgreement != null) {
					transaction.remove(userAgreement);
				}
				three.setBackgroundResource(R.drawable.my);
				imageStrMy.setTextColor(Color.parseColor("#1E1F1F"));
				break;
			}

			// 显示frament
			switch (id) {
			case 1:
				if (homeFrament != null) {
					// 开启一个Fragment事务
					transaction.show(homeFrament);
					app.viewPageHelp.start();
				} else {
					homeFrament = new HomeFrament();
					L.v("创建首页");
					transaction.add(R.id.id_content, homeFrament);
					// transaction.addToBackStack(null);

				}
				homeIni();
				one.setBackgroundResource(R.drawable.home_selected);
				imageStrHome.setTextColor(Color.parseColor(checkColor));
				break;
			case 2:
				if (orderFragment != null) {
					transaction.show(orderFragment);
				} else {
					orderFragment = new OrderFragment();
					transaction.add(R.id.id_content, orderFragment);
				}
				projectIni();
				two.setBackgroundResource(R.drawable.project_selected);
				imageStrProject.setTextColor(Color.parseColor(checkColor));
				break;
			case 3:
				if (userIntFlag == 1) {
					if (userLoginFragement != null) {

						transaction.show(userLoginFragement);
					} else {
						userLoginFragement = new UserLoginFragement();
						userLoginFragement.setUserLoginListener(userLogin);
						transaction.add(R.id.id_content, userLoginFragement);
					}
					userLoginIni();
					three.setBackgroundResource(R.drawable.my_selecte);
					imageStrMy.setTextColor(Color.parseColor(checkColor));
				} else if (userIntFlag == 2) {
					if (userRegionFragment != null) {
						transaction.show(userRegionFragment);
					} else {
						userRegionFragment = new UserRegisterFragment();
						// userAgreement.
						transaction.add(R.id.id_content, userRegionFragment);
					}
					id = 5;
					userRegionIni();
					three.setBackgroundResource(R.drawable.my_selecte);
					imageStrMy.setTextColor(Color.parseColor(checkColor));
					// break;

				} else if (userIntFlag == 3) {
					if (userAgreement != null) {
						transaction.show(userAgreement);
					} else {
						userAgreement = new UserAgreement();
						// userAgreement.
						transaction.add(R.id.id_content, userAgreement);
					}
					id = 6;
					userAgreementIni();
					three.setBackgroundResource(R.drawable.my_selecte);
					imageStrMy.setTextColor(Color.parseColor(checkColor));
					// break;
				}

				break;
			case 4:
				if (productNewsFrament != null) {
					transaction.show(productNewsFrament);
				} else {
					productNewsFrament = new ProductNewsFrament();
					transaction.add(R.id.id_content, productNewsFrament);
				}
				otherIni();
				image_four.setBackgroundResource(R.drawable.other_selecte);
				imageStrOther.setTextColor(Color.parseColor(checkColor));
				break;
			case 5:
				if (userRegionFragment != null) {
					transaction.show(userRegionFragment);
				} else {
					userRegionFragment = new UserRegisterFragment();
					userRegionFragment
							.setUserRegisterLister(userRegisterLister);
					transaction.add(R.id.id_content, userRegionFragment);
				}
				userRegionIni();
				three.setBackgroundResource(R.drawable.my_selecte);
				imageStrMy.setTextColor(Color.parseColor(checkColor));
				break;
			case 6:
				if (userAgreement != null) {
					transaction.show(userAgreement);
				} else {
					userAgreement = new UserAgreement();
					// userAgreement.
					transaction.add(R.id.id_content, userAgreement);
				}
				userAgreementIni();
				three.setBackgroundResource(R.drawable.my_selecte);
				imageStrMy.setTextColor(Color.parseColor(checkColor));
				break;
			}

			transaction.commit();
			nowButtonNo = id;
		}
	}

	private class bottomViewClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.id_tab_bottom_weixin:
				hideFragmentsAndChangeImage(1);
				break;
			case R.id.id_tab_bottom_friend:
				hideFragmentsAndChangeImage(2);

				break;

			case R.id.id_tab_bottom_contact:
				hideFragmentsAndChangeImage(3);
				break;

			case R.id.id_tab_bottom_setting:
				hideFragmentsAndChangeImage(4);
				break;

			}
		}

	}

	/**
	 * 用户注册UI初始化
	 */
	private void userRegionIni() {
		userIntFlag = 2;
		headImage.setVisibility(View.VISIBLE);
		headLeft.setText("登录");
		headtitle.setText("注册");
		headImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// L.v("点击了");
				userIntFlag = 1;
				hideFragmentsAndChangeImage(3);
			}
		});
		headLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userIntFlag = 1;
				hideFragmentsAndChangeImage(3);
			}
		});
	}

	/**
	 * 用户登录UI初始化
	 */
	private void userLoginIni() {
		userIntFlag = 1;
		headImage.setVisibility(View.INVISIBLE);

		headLeft.setText("");
		headtitle.setText("登录");

	}

	/**
	 * 首页UI初始化
	 */
	private void homeIni() {

		headImage.setVisibility(View.INVISIBLE);
		headLeft.setText("");
		headtitle.setText("首页");
	}

	/**
	 * 项目UI初始化
	 */
	private void projectIni() {

		headImage.setVisibility(View.INVISIBLE);
		headLeft.setText("");
		headtitle.setText("项目");
	}

	/**
	 * My UI初始化
	 */
	private void myIni() {

		headImage.setVisibility(View.INVISIBLE);
		headLeft.setText("");
		headtitle.setText("我");
	}

	/**
	 * 其他 UI初始化
	 */
	private void otherIni() {

		headImage.setVisibility(View.INVISIBLE);
		headLeft.setText("");
		headtitle.setText("其他");
	}

	/**
	 * 用户协议UI初始化
	 */
	private void userAgreementIni() {
		userIntFlag = 3;
		headImage.setVisibility(View.VISIBLE);
		headLeft.setText("注册");
		headtitle.setText("嘟仔投用户协议");
		headImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hideFragmentsAndChangeImage(5);
			}
		});
		headLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hideFragmentsAndChangeImage(5);
			}
		});
	}

	/**
	 * 
	 * 以下是系统函数
	 * 
	 */

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		// 保存当前的点击的是哪一个
		outState.putInt("nowButtonNo", nowButtonNo);

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if (app != null) {
			if (app.viewPageHelp != null) {
				app.viewPageHelp.start();
			}
		} else {
			app = (AppContent) getApplication();
		}
		T.show(this, "Home Restart");
		L.e("Home Restart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (app != null) {
			if (app.viewPageHelp != null) {
				app.viewPageHelp.close();
			}
		} else {
			app = (AppContent) getApplication();
		}

		L.e("Home stop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		L.e("home destroy");
	}
}
