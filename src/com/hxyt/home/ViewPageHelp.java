package com.hxyt.home;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hxyt.ProjectCommand;
import com.hxyt.R;
import com.hxyt.utils.L;
import com.hxyt.utils.SDCardUtils;
import com.lidroid.xutils.BitmapUtils;

import android.R.color;
import android.content.Context;
import android.graphics.Color;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-28 下午5:55:12
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class ViewPageHelp {
	private Context context;
	private ViewPager viewpage;
	private PagerAdapter pagerAdapter;
	private List<ImageView> imageViews = new ArrayList<ImageView>();
	private BitmapUtils bitmapUtils;
	private ViewGroup flagLayout;
	private List<View> flagViews = new ArrayList<View>();
	private int hasChageView = 0;
	private boolean flage = true;
	private int newimageview = 0;
	private Thread thread;
	private Runnable runnable;
	private final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (viewpage != null) {

				viewpage.setCurrentItem(msg.what, true);
				// setFlagImageChange(msg.what);
			}
		};
	};

	public ViewPageHelp(Context context, ViewPager viewpage,
			ViewGroup flagLayout) {
		this.context = context;
		this.viewpage = viewpage;
		this.flagLayout = flagLayout;
		bitmapUtils = new BitmapUtils(context, SDCardUtils.getSDCardPath()
				+ File.separator + ProjectCommand.ProjectFolder.TMP_FILE_PATH);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		String sd_url = SDCardUtils.getSDCardPath()
				+ ProjectCommand.ProjectFolder.IMAGE_FILE_PATH;
		ImageView one = new ImageView(context);
		if (!(new File(sd_url + "back_1.png").exists())) {
			one.setBackgroundResource(R.drawable.nav_1);
		} else {
			bitmapUtils.display(one, sd_url + "back_1.png");
		}
		imageViews.add(one);
		ImageView tow = new ImageView(context);
		if (!(new File(sd_url + "back_2.png").exists())) {
			tow.setBackgroundResource(R.drawable.nav_2);
		} else {
			bitmapUtils.display(tow, sd_url + "back_2.png");
		}
		imageViews.add(tow);

		ImageView three = new ImageView(context);
		if (!(new File(sd_url + "back_3.png").exists())) {
			three.setBackgroundResource(R.drawable.nav_3);
		} else {
			bitmapUtils.display(three, sd_url + "back_3.png");
		}
		imageViews.add(three);

		ImageView four = new ImageView(context);
		if (!(new File(sd_url + "back_4.png").exists())) {
			four.setBackgroundResource(R.drawable.nav_4);

		} else {
			bitmapUtils.display(four, sd_url + "back_4.png");
		}
		imageViews.add(four);

		ImageView five = new ImageView(context);
		if (!(new File(sd_url + "back_5.png").exists())) {
			five.setBackgroundResource(R.drawable.nav_5);
		} else {
			bitmapUtils.display(five, sd_url + "back_5.png");
		}
		imageViews.add(five);

		int s = flagLayout.getChildCount();
		for (int i = 0; i < s; i++) {
			flagViews.add(flagLayout.getChildAt(i));
		}

		pagerAdapter = new PagerAdapter() {

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(imageViews.get(position));
				return imageViews.get(position);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return imageViews.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(imageViews.get(position));
			}
		};
		viewpage.setAdapter(pagerAdapter);
		setFlagImageChange(0);

		changestart();

		viewpage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int poiston) {
				// TODO Auto-generated method stub
				if (poiston != newimageview) {
					newimageview = poiston;
				}
				setFlagImageChange(poiston);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void changestart() {
		runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (flage) {
					if (viewpage != null) {
						if (newimageview == 5) {
							newimageview = 0;
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							return;
							// e.printStackTrace();
						}
						Message msg = Message.obtain();
						msg.what = newimageview;
						if (handler != null) {
							handler.sendMessage(msg);
						}
						newimageview++;

					}

				}
				L.i("广告停止");
			}
		};
		thread = new Thread(runnable);

		thread.start();
	}

	/**
	 * 设置flag的imagview变图片
	 */
	private void setFlagImageChange(int poiston) {
		flagViews.get(poiston).setBackgroundResource(
				R.drawable.circular_view_white);
		if ((poiston != hasChageView)) {
			flagViews.get(hasChageView).setBackgroundResource(
					R.drawable.circular_view_org);
		}
		hasChageView = poiston;
	}

	public void close() {
		flage = false;
		if (thread != null) {
			thread.interrupt();
		}
		L.v("被关闭");
	}

	public void start() {
		if (!flage) {
			flage = true;
			if (thread != null) {
				changestart();
				L.v("被开启");
			}

		}

	}

	/**
	 * 切换Viewpage的图片
	 */
	public void changeViewPagerImage() {
		if (SDCardUtils.isSDCardEnable()) {
			String url = SDCardUtils.getSDCardPath()
					+ ProjectCommand.ProjectFolder.IMAGE_FILE_PATH;
			bitmapUtils.display(imageViews.get(0), url + "back_1.png");
			bitmapUtils.display(imageViews.get(1), url + "back_2.png");
			bitmapUtils.display(imageViews.get(2), url + "back_3.png");
			bitmapUtils.display(imageViews.get(3), url + "back_4.png");
			bitmapUtils.display(imageViews.get(4), url + "back_5.png");
			pagerAdapter.notifyDataSetChanged();
			// for (ImageView view : imageViews) {
			// bitmapUtils.display(view, "");
			// }
		}
	}

}
