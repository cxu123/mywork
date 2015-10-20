package com.hxyt.other;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hxyt.BaseActivity;
import com.hxyt.R;
import com.hxyt.home.HomeActivity;
import com.hxyt.utils.SPUtils;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-25 下午2:54:48
 * @version 1.0  
 * @parameter
 * @since
 * @return  用户直营设置向导
 */

public class GuidanceActivity extends Activity {

    private ViewPager guidance_viewpage;
    private List<View> flagView;
    private LinearLayout flagLayout;
    private PagerAdapter pagerAdapter;
    private List<ImageView> imageViews;
    private int flagTag=0;
    private Button exitGuid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        findViews();
        init(savedInstanceState);
    }
    
    private void findViews() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activity_guidance);
	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	guidance_viewpage = (ViewPager) findViewById(R.id.guidance_viewpage);
	flagLayout = (LinearLayout) findViewById(R.id.flagLayout);
	flagView = new ArrayList<View>();
	imageViews = new ArrayList<ImageView>();
	exitGuid=(Button) findViewById(R.id.exitGuid);
	SPUtils.put(this, "FirstLogin", 1);
    }

    private void init(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	int count = flagLayout.getChildCount();
	ImageView a = new ImageView(GuidanceActivity.this);
	a.setBackgroundResource(R.drawable.guide_image1);
	ImageView b = new ImageView(GuidanceActivity.this);
	b.setBackgroundResource(R.drawable.guide_image2);
	ImageView c = new ImageView(GuidanceActivity.this);
	c.setBackgroundResource(R.drawable.guide_image3);
	imageViews.add(a);
	imageViews.add(b);
	imageViews.add(c);
	for (int i = 0; i < count; i++) {
	    flagView.add(flagLayout.getChildAt(i));
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
		return arg0==arg1;
	    }

	    @Override
	    public int getCount() {
		// TODO Auto-generated method stub
		return imageViews.size();
	    }

	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(imageViews.get(position));
	    }
	};
	iniViewPage();
	setFlagViewColor(0);
	exitGuid.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(GuidanceActivity.this,HomeActivity.class));
		GuidanceActivity.this.finish();
		//overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	    }
	});
    }

    private void iniViewPage(){
	guidance_viewpage.setAdapter(pagerAdapter);
	guidance_viewpage.setOnPageChangeListener(new OnPageChangeListener() {
	    
	    @Override
	    public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		setFlagViewColor(position);
		
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
    
    private void setFlagViewColor(int position){
	flagView.get(flagTag).setBackgroundResource(R.drawable.circular_view_org);
	flagView.get(position).setBackgroundResource(R.drawable.circular_view_white);
	flagTag=position;
	if (exitGuid.getVisibility()!=View.GONE) {
	    exitGuid.setVisibility(View.GONE);
	}
	if (position==2) {
	    exitGuid.setVisibility(View.VISIBLE);
	}
    }
    

}
