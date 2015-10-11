package com.hxyt.other;

import com.hxyt.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-9 上午9:57:20
 * @version 1.0 用户协议
 * @parameter
 * @since
 * @return
 */
public class UserAgreement extends Fragment {

	WebView userAgreement;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		if (savedInstanceState != null) {
			return super.onCreateView(inflater, container, savedInstanceState);

		} else {
			View view = inflater.inflate(R.layout.fragment_user_agreement, container,
					false);
			userAgreement=(WebView) view.findViewById(R.id.user_word);
			ini();
			return view;
		}
		
	}

	private void ini(){
		String url = "file:///android_asset/web/hxyt_user_agreement.html";
		userAgreement.loadUrl(url);
		userAgreement.setWebViewClient(new WebViewClient());
		WebSettings webSettings = userAgreement.getSettings();
		webSettings.setJavaScriptEnabled(true);
	}
	
}
