package com.hxyt.utils;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import com.lidroid.xutils.BitmapUtils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

/**
 * @author 作者 陈修园
 * @date 创建时间：2015-10-22 下午5:16:49
 * @version 1.0 图片选择 穿入0是用手机拍照 穿入1是图库选择
 * @parameter
 * @since
 * @return
 */
public class TakePictureTool {

	private String imagePath;
	private Intent intent;
	private String path = imagePath + UUID.randomUUID().toString() + ".jpg";
	private Activity activity;
	private File imagefile;
	/**
	 * 拍照、还是图片选择 0是拍照、1是选择图片
	 */
	private int flag = 0;

	public TakePictureTool(String imagePath, Activity activity, int flag) {

		if (imagePath == null || imagePath.length() <= 0) {
			throw new NullPointerException("图片路径为空");
		}
		if (activity == null) {
			throw new NullPointerException("Activity为空");
		}
		this.flag = flag;
		this.imagePath = imagePath;
		this.activity = activity;
		imagefile = new File(imagePath);
		if (!SDCardUtils.isSDCardEnable()) {
			Toast.makeText(activity, "未检测到SD卡，无法进行拍照", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (!imagefile.exists()) {
			imagefile.mkdir();
		}

		startWork();
	}

	private void startWork() {
		if (flag == 0) {
			//相机是100
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagefile));
			activity.startActivityForResult(intent, 100);
		} else {
			intent = new Intent(Intent.ACTION_PICK, null);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					"image/*");
			activity.startActivityForResult(intent, 200);
		}

	}

	/**
	 * 
	 * 如果是相册那么从intent获得url
	 * 
	 */
	public static String getImagePath(Intent data,Activity act) {
		
		if (data == null) {
			return null;
		}
		Uri uri = data.getData();
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = act.managedQuery(uri, proj, null, null, null);
		if (cursor != null) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			if (cursor.getCount() > 0 && cursor.moveToFirst()) {
				String path = cursor.getString(column_index);
				L.v("onActivityResult", "获得图片的地址-->" + path);

				// 压缩图片
				ImageFactory.compressImage(path, 10);
				return path;
				// UpImageToService(key, path);
				// 放大content中
			}
		} else {
			return null;
		}
		return null;
	}

}
