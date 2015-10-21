package com.hxyt.home;

import com.hxyt.AppContent;
import com.hxyt.ProjectCommand;
import com.hxyt.R;
import com.hxyt.order.OrderInfoActivity;
import com.hxyt.other.testActivity;
import com.hxyt.utils.CallPhone;
import com.hxyt.utils.L;
import com.hxyt.utils.T;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-22 上午9:20:02
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class HomeFrament extends Fragment {

	private Button strart_ActiButton;
	private ViewPager viewPager;
	private ViewGroup flagLayout;
	private Button call_us;
	private Button chageimage;
	private AppContent app;
	private ImageView callUs;

	/**
	 * 最热项目
	 */
	private View hotProject;
	/**
	 * 第一个推荐项目
	 */
	private View projectOne;
	private ImageView project_one_company_investment;
	

	/**
	 * 第二个推荐项目
	 */
	private View projectTwo;
	private ImageView project_two_company_investment;
	

	/**
	 * 第三个推荐项目
	 */
	private View projectThree;
	private ImageView project_three_company_investment;
	/**
	 * 最热项目投资
	 */
	private ImageView hot_project_invest;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// L.v("首页");
		if (savedInstanceState != null) {
			return super.onCreateView(inflater, container, savedInstanceState);
		} else {
			View view = inflater.inflate(R.layout.fragment_home, container,
					false);
			viewPager = (ViewPager) view.findViewById(R.id.ad_viewpage);
			flagLayout = (ViewGroup) view.findViewById(R.id.flagLayout);
			callUs = (ImageView) view.findViewById(R.id.call_us);
			hotProject = view.findViewById(R.id.hotProject);
			projectOne = view.findViewById(R.id.projectOne);
			projectTwo = view.findViewById(R.id.projectTwo);
			projectThree = view.findViewById(R.id.projectThree);
			hot_project_invest = (ImageView) view.findViewById(R.id.hot_project_invest);
			project_one_company_investment= (ImageView) view.findViewById(R.id.project_two_company_investment);
			project_two_company_investment= (ImageView) view.findViewById(R.id.project_three_company_investment);
			project_three_company_investment= (ImageView) view.findViewById(R.id.project_four_company_investment);
			init();
			iniClick();
			return view;
		}
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		app = (AppContent) getActivity().getApplication();
		app.viewPageHelp = new ViewPageHelp(getActivity(), viewPager,
				flagLayout);
	}

	/**
	 * 初始化点击事件
	 */
	private void iniClick() {
		// 呼叫客服中心
		callUs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CallPhone.call(ProjectCommand.US_PHONE_NUMBER, getActivity());
			}
		});
		// 最热项目点击事件
		hotProject.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "跳转到最热项目详细信息界面");
			}
		});
		// 最热项目投资点击事件
		hot_project_invest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "跳转到项目投资界面");
			}
		});
		// 项目1点击事件
		projectOne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "跳转到项目1详细界面");
			}
		});
		//第一个项目投资按钮
		project_one_company_investment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "第一个项目投资按钮");
			}
		});
		// 项目2点击事件
		projectTwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "跳转到项目2详细界面");
			}
		});
		
	
		
		//第二个项目投资按钮
		project_two_company_investment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "第二个项目投资按钮");
			}
		});
		// 项目3点击事件
		projectThree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "跳转到项目3详细界面");
			}
		});
		//第三个项目投资按钮
		project_three_company_investment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				T.showShortToo(getActivity(), "第三个项目投资按钮");
			}
		});
		
	}

	/**
	 * 下面是生命周期
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		if (app != null) {
			if (app.viewPageHelp != null) {
				app.viewPageHelp.close();
				app.viewPageHelp = null;
			}

		}

		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();

	}

}
