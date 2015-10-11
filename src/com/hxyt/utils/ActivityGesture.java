package com.hxyt.utils;

import com.hxyt.R;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-25 下午5:46:35
 * @version 1.0
 * @parameter Android activity 手势控制器
 * @since
 * @return
 */

public class ActivityGesture {
    Context context;
    private GestureDetector mGestureDetector;
    private int verticalMinDistance = 180;
    private int minVelocity = 0;
    // android.view.GestureDetector.OnGestureListener
    private OnGestureListener onGestureListener;
    private Activity hostActivity;

    public ActivityGesture(Activity act) {
	hostActivity = act;
	this.context = act;
	iniClass();

    }

    @SuppressWarnings("deprecation")
    private void iniClass() {

	onGestureListener = new OnGestureListener() {

	    @Override
	    public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public boolean onScroll(MotionEvent e1, MotionEvent e2,
		    float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public boolean onFling(MotionEvent e1, MotionEvent e2,
		    float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		 if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
		     
		         // 切换Activity
		         // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
		         // startActivity(intent);
		        // Toast.makeText(hostActivity, "向左手势", Toast.LENGTH_SHORT).show();
		     } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
		 
		         // 切换Activity
		         // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
		         // startActivity(intent);
		         //Toast.makeText(hostActivity, "向右手势", Toast.LENGTH_SHORT).show();
			 
		         hostActivity.finish();
		        // PendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		     }
		 
		     return false;
	    }

	    @Override
	    public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	    }
	};
	
	mGestureDetector = new GestureDetector(onGestureListener);
	
    }

    public boolean setOnTouch(MotionEvent event){
	return mGestureDetector.onTouchEvent(event);
    }
    
    public void setTouchEvent(MotionEvent ev){
	mGestureDetector.onTouchEvent(ev);
    }
    
}
