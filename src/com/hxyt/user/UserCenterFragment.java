package com.hxyt.user;

import java.io.File;

import cn.jpush.android.data.l;

import com.hxyt.AppContent;
import com.hxyt.ProjectCommand;
import com.hxyt.R;
import com.hxyt.utils.L;
import com.hxyt.utils.SDCardUtils;
import com.lidroid.xutils.BitmapUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-14 下午5:45:41
 * @version 1.0  用户中心
 * @parameter
 * @since
 * @return
 */
public class UserCenterFragment extends Fragment {
	/**
	 * 站内短信
	 */
	private View user_sms_root_view;
	private TextView user_sms;
	/**
	 * 关注项目
	 */
	private View user_attention_project_root_view;
	private TextView user_attention_project;

	/**
	 * 约谈项目
	 */
	private View user_meet_project_root_view;
	private TextView user_meet_project;

	/**
	 * 个人资料
	 */
	private View user_data;
	/**
	 * 我的账户
	 */
	private View user_account;
	/**
	 * 项目管理
	 */
	private View project_managerment;
	/**
	 * 安全中心
	 */
	private View user_safety_center;

	/**
	 * bitmap工具
	 */
	private BitmapUtils bitmapUtils;
	/**
	 * 用户头像
	 */
	private ImageView user_head_image;
	
	
	private UserCenterClick userCenterClick;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			// TODO Auto-generated method stub
			return super.onCreateView(inflater, container, savedInstanceState);
		} else {
			View rootView = inflater.inflate(R.layout.fragment_user_center,
					container, false);
			userCenterClick = new UserCenterClick();
			user_sms_root_view = rootView.findViewById(R.id.user_sms_root_view);
			user_attention_project_root_view = rootView
					.findViewById(R.id.user_attention_project_root_view);
			user_meet_project_root_view = rootView
					.findViewById(R.id.user_meet_project_root_view);
			user_sms = (TextView) rootView.findViewById(R.id.user_sms);
			user_attention_project = (TextView) rootView
					.findViewById(R.id.user_attention_project);
			user_meet_project = (TextView) rootView
					.findViewById(R.id.user_meet_project);

			user_data = rootView.findViewById(R.id.user_data);
			project_managerment = rootView
					.findViewById(R.id.project_managerment);
			user_safety_center = rootView.findViewById(R.id.user_safety_center);
			user_account = rootView.findViewById(R.id.user_account);
			user_head_image=(ImageView) rootView.findViewById(R.id.user_head_image);
			ini();
			iniClick();
			return rootView;
		}
	}

	/**
	 * 初始化数据
	 */
	private void ini() {
		bitmapUtils = new BitmapUtils(getActivity(), SDCardUtils.getSDCardPath()
				+ File.separator + ProjectCommand.ProjectFolder.TMP_FILE_PATH);
	}

	/**
	 * 初始化点击按钮
	 */
	private void iniClick() {

		user_sms_root_view.setOnClickListener(userCenterClick);
		user_attention_project_root_view.setOnClickListener(userCenterClick);
		user_meet_project_root_view.setOnClickListener(userCenterClick);
		user_data.setOnClickListener(userCenterClick);
		user_account.setOnClickListener(userCenterClick);
		project_managerment.setOnClickListener(userCenterClick);
		user_safety_center.setOnClickListener(userCenterClick);
	}

	private class UserCenterClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.user_sms_root_view:
				L.v("站内短信");
				break;
			case R.id.user_attention_project_root_view:
				L.v("关注项目");
				break;

			case R.id.user_meet_project_root_view:
				L.v("约谈项目");
				break;

			case R.id.user_data:
				L.v("个人资料");
				getActivity().startActivity(new Intent(getActivity(),UserDataActivity.class));
				break;

			case R.id.user_account:
				L.v("我的账户");
				getActivity().startActivity(new Intent(getActivity(),UserAccountActivity.class));
				break;
			case R.id.project_managerment:
				L.v("管理项目");
				break;
				
			case R.id.user_safety_center:
				L.v("安全中心");
				break;

			}

		}

	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		AppContent app=(AppContent) getActivity().getApplication();
		if (app.user_head_image_has_change) {
			bitmapUtils.display(user_head_image, app.user_head_image_paht);
		}
	}
	
}
