package com.hxyt.home;

import com.hxyt.ProjectCommand;
import com.hxyt.utils.HttpJsonTool;
import com.hxyt.utils.L;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-29 下午5:16:01
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class HomeModle {

    /**
     * 获取轮播广告的http
     */
    private HttpJsonTool getAdImagehttp;
    private Handler adImageHandler;
    /**
     * 获得首先显示信息
     */
    private HttpJsonTool getHomeInfo;

    /**
     * 图片的总数
     */
    private int imagesCount = 0;

    /**
     * 获取广告图片
     * 
     * @param context
     */
    public void getAdImage(Context context) {

	getAdImagehttp = new HttpJsonTool(context);
	getAdImagehttp.setImageLister(new Handler() {
	    @Override
	    public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		imagesCount++;
		if (imagesCount < 5) {
		    L.v("下载第" + (imagesCount + 1) + "附图片");
		    getAdImagehttp
			    .downLoadFile(ProjectCommand.HOME.AD_IMAGE_LIST[imagesCount]);
		} else {
		    Message msgs = Message.obtain();
		    msgs.what=1;
		    adImageHandler.sendMessage(msgs);
		}
	    }
	});
	L.v("下载第一附图片");
	getAdImagehttp
		.downLoadFile(ProjectCommand.HOME.AD_IMAGE_LIST[imagesCount]);
    }

    /**
     * 设置iamge的handle
     * 
     * @param handler
     */
    public void setAdHandler(Handler handler) {
	this.adImageHandler = handler;
    }

}
