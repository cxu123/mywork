package com.hxyt.view;

import com.hxyt.R;
import com.hxyt.utils.L;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Path.Direction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-18 下午4:12:25
 * @version 当构造函数type 0、是View旋转  1、是心跳View
 * @parameter 加载View
 * @since
 * @return
 */

public class LoadingView extends Dialog {
	LayoutInflater inflater;
	LinearLayout layout;
	private AnimationSet mDialogAnim;
	private View mDialog;
	private int type=0;
	public LoadingView(Context context) {
		super(context, R.style.loading_view);
		//ini(context, 0);
	}
	
	/**
	 * 
	 * @param context
	 * @param type--》 为0 时候为默认View  为1时 View 是心跳  
	 */
	public LoadingView(Context context, int type) {
		super(context, R.style.loading_view);
		this.type=type;
		
	}

	// MyDialog selectDialog = new
	// MyDialog(this,R.style.dialog);//创建Dialog并设置样式主题
	// Window win = dialog.getWindow();
	// LayoutParams params = new LayoutParams();
	// params.x = -80;//设置x坐标
	// params.y = -60;//设置y坐标
	// win.setAttributes(params);
	// dialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
	// dialog.show();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ini(getContext());
		
	}
	
	private void ini(Context context ) {

		View mView;
		if (type==0) {
			mView = LayoutInflater.from(context).inflate(R.layout.loading_view,
					null);
		}else {
			mView=LayoutInflater.from(context).inflate(R.layout.loading_beat_view,
					null);
			if (mView==null) {
				L.v("mView已经是空了");
			}
		}
		// Direction
		// mView.setLayoutDirection(layoutDirection);
		Window window = getWindow();
		window.setWindowAnimations(R.style.loading_view_animstyle);
		//layout = (LinearLayout) findViewById(R.layout.loading_view);
		//mView.getBackground().setAlpha(0);
		BeatView beatView=(BeatView) mView.findViewById(R.id.beatView1);
		if (beatView!=null) {
			beatView.startViewAnimation();
		}

		setContentView(mView);
		
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
	}

	public void Viewdismiss() {
		// mDialog.startAnimation(mDialogAnim);
		// cancel();
		dismiss();
	}

}
