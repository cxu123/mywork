package com.hxyt.home;

import com.hxyt.AppContent;
import com.hxyt.R;
import com.hxyt.order.OrderInfoActivity;
import com.hxyt.other.testActivity;
import com.hxyt.utils.CallPhone;
import com.hxyt.utils.L;

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
			strart_ActiButton = (Button) view.findViewById(R.id.button1);
			viewPager = (ViewPager) view.findViewById(R.id.ad_viewpage);
			flagLayout = (ViewGroup) view.findViewById(R.id.flagLayout);
			call_us = (Button) view.findViewById(R.id.call_us);
			chageimage = (Button) view.findViewById(R.id.chageimage);
			init();
			iniClick();
			return view;
		}
	}

	private void init() {
		app = (AppContent) getActivity().getApplication();
		app.viewPageHelp = new ViewPageHelp(getActivity(), viewPager,
				flagLayout);
	}

	private void iniClick() {
		strart_ActiButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						OrderInfoActivity.class);
				getActivity().startActivity(intent);

			}
		});

		call_us.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// CallPhone.call("4000256756", getActivity());
				getActivity().startActivity(
						new Intent(getActivity(), testActivity.class));
			}
		});
		chageimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				app.viewPageHelp.changeViewPagerImage();
			}
		});
	}

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
