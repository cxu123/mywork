package com.hxyt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

/**
 * @author 作者 陈修远E-mail:
 * @date 创建时间：2015-6-8 上午11:32:38
 * @version 1.0
 * @parameter 用来压缩图片
 * @since
 * @return
 */
public class ImageFactory {
	/**
	 * 
	 * @Title: 压缩图片传入路径 int 为比例 s 放缩比例
	 * @Description: TODO(压缩图片)
	 * @param @param path 参数说明
	 * @return void 返回类型
	 * @throws
	 */
	public static void compressImage(String path,int s) {
		final BitmapFactory.Options optionss = new BitmapFactory.Options();
		
		optionss.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, optionss);
		int height=optionss.outHeight;
		int width=optionss.outWidth;
		Log.v("height", ""+height);
		Log.v("width", ""+width);
		height=height/s;
		width=width/s;
		Log.v("height", ""+height);
		Log.v("width", ""+width);
		optionss.inSampleSize = caculateInSampleSize(optionss, width, height);
        optionss.inJustDecodeBounds = false;
		Bitmap finalbitmap=BitmapFactory.decodeFile(path,optionss);
		

		/**
		 * 下面开始保存文件
		 */
		File f = new File(path);
		//Log.v("f.length()除以1024", "3除以1024总长"+(((f.length()/1024)/1024)/1024));
		if (f.length()<=(1024*400)) {
			Log.v("f.length()", "总长度"+f.length()+"");
			Log.v("图片太小不用了", "图片太小不用了");
			return;
		}else {
			
		}
	
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
		boolean flag=	finalbitmap.compress(Bitmap.CompressFormat.PNG, 50, out);
		if (flag) {
		//	Log.v("获得流成功", "获得流成功");
		}
			out.flush();
			out.close();
		//	Log.i("图片工厂", "已经保存");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	 /**
     * caculate the bitmap sampleSize
     * @param path
     * @return
     */
    public final static int caculateInSampleSize(BitmapFactory.Options options, int rqsW, int rqsH) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (rqsW == 0 || rqsH == 0) return 1;
        if (height > rqsH || width > rqsW) {
            final int heightRatio = Math.round((float) height/ (float) rqsH);
            final int widthRatio = Math.round((float) width / (float) rqsW);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
	
}
