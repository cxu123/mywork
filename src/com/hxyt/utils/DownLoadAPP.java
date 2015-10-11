package com.hxyt.utils;

import java.io.File;

import com.hxyt.ProjectCommand;
import com.hxyt.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * * @author 作者 陈修园:
 * 
 * @date 创建时间：2015-5-13 下午1:27:59
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class DownLoadAPP extends Service {
	private int BuildId = 10111;
	private NotificationManager notificationManager;
	private Notification notification;
	private String tag = "CE货的下载中...";
	private String root;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		L.v("DownLoadAPP--service", "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		L.v("service", "onCreate");
		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		L.v("service", "onStartCommand");

		if (!SDCardUtils.isSDCardEnable()) {
			L.e("未能找打SD卡");
		} else {
			root = SDCardUtils.getSDCardPath();
			StrartDownLoadAPK();
		}
		return super.onStartCommand(intent, flags, startId);

	}

	@SuppressWarnings("deprecation")
	private void StrartDownLoadAPK() {

		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notification = new Notification(R.drawable.ic_launcher, "CE货的",
				System.currentTimeMillis());
		notification.contentView = new RemoteViews(getPackageName(),
				R.layout.down_load_app);
		notification.contentView.setProgressBar(R.id.pb, 100, 0, false);
		notificationManager.notify(BuildId, notification);

		L.v("StrartDownLoadAPK", "StrartDownLoadAPK");
		HttpUtils httpUtils = new HttpUtils();

		String file_path_and_name = root + File.separator
				+ ProjectCommand.ProjectFolder.IMAGE_FILE_PATH + "sj.apk";
		httpUtils.download(ProjectCommand.ProjectFolder.APK_DowndLoad_Path,
				file_path_and_name, false, true, new RequestCallBack<File>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						L.v("onFailure", arg1);
						// L.v("onFailure", arg0.getLocalizedMessage());
						// L.v("onFailure", arg0.);
						// L.v("onFailure", arg0.getLocalizedMessage());
						notificationManager.cancel(BuildId);
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						// TODO Auto-generated method stub
						super.onLoading(total, current, isUploading);

						if (current > 0) {
							notification.contentView.setTextViewText(
									R.id.down_tv,
									tag+ StringHelper.myPercent(current,
													total));
							double d = StringHelper.doublePercent(current,
									total) * 100;
							notification.contentView.setProgressBar(R.id.pb,
									100, (int) d, false);
							notificationManager.notify(BuildId, notification);
						}
					}

					@Override
					public void onSuccess(ResponseInfo<File> responseInfo) {
						// TODO Auto-generated method stub
						L.v("下载成功！", responseInfo.result.getPath());
						Intent i = new Intent(Intent.ACTION_VIEW);
						i.setDataAndType(
								Uri.parse("file://"
										+ responseInfo.result.getPath()),
								"application/vnd.android.package-archive");
						i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						DownLoadAPP.this.startActivity(i);
						// stopSelf();
						notificationManager.cancel(BuildId);
					}
				});
		// L.v("stopSelf", "stopSelf");

	}

}
