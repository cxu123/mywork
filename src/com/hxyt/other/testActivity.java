package com.hxyt.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.baofoo.sdk.BaofooPayActivity;
import com.hxyt.BaseActivity;
import com.hxyt.R;
import com.hxyt.baofoo.BaoFooPayUtils;
import com.hxyt.utils.WebViewPlayHelper;
import com.hxyt.view.LoadingView;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-13 上午10:04:55
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class testActivity extends BaseActivity {

	TextView payresult;

	Button button;

	Button play_video;

	WebView play_view;

	WebViewPlayHelper webViewPlayHelper;

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub

		// setContentView(R.layout.loading_beat_view);

		setContentView(R.layout.activity_test);
		LoadingView loadingView = new LoadingView(this, 1);
		loadingView.show();
		payresult = (TextView) findViewById(R.id.payresult);
		button = (Button) findViewById(R.id.startpay);
		play_video = (Button) findViewById(R.id.play_video);
		play_view = (WebView) findViewById(R.id.play_view);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BaoFooPayUtils baoFooPayUtils = new BaoFooPayUtils(null,
						testActivity.this);
			}
		});

		play_video.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webViewPlayHelper = new WebViewPlayHelper(play_view, null);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 100) {
			String result = "";
			String msg = "";
			if (data == null || data.getExtras() == null) {
				payresult.setText("支付已被取消");
			} else {
				result = data.getExtras().getString(
						BaofooPayActivity.PAY_RESULT);// -1:失败 0:取消 1:成功 10:处理中
				msg = data.getExtras().getString(BaofooPayActivity.PAY_MESSAGE);
				payresult.setText(msg);

			}

		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (webViewPlayHelper != null) {
			webViewPlayHelper.onPause();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (webViewPlayHelper != null) {
			webViewPlayHelper.onResume();
		}
	}

}
