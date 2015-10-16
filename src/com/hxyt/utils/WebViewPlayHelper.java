package com.hxyt.utils;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-16 上午9:48:11
 * @version 1.0 播放优酷视频
 * @parameter
 * @since
 * @return
 */
public class WebViewPlayHelper {

	private WebView webView;
	private String url;

	public WebViewPlayHelper(WebView webView, String url) {
		this.webView = webView;
		if (url != null) {
			this.url = url;
		} else {
			this.url = "http://player.youku.com/embed/XMTM0NDQwMDgyMA==";
		}

		ini();
	}

	private void ini() {

		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient());
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

	}

	public void onPause() {
		if (webView != null) {
			webView.onPause();
		}
	}

	public void onResume() {
		if (webView != null) {
			webView.onPause();
		}
	}

}
