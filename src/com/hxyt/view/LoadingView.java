package com.hxyt.view;

import com.hxyt.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Path.Direction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-18 下午4:12:25
 * @version 1.0
 * @parameter 加载View
 * @since
 * @return
 */

public class LoadingView extends Dialog {
	LayoutInflater inflater;
	LinearLayout layout;
	private AnimationSet mDialogAnim;
	private View mDialog;

	public LoadingView(Context context) {
		super(context, R.style.loading_view);
		ini(context);
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

	private void ini(Context context) {
		// mDialog=getWindow().getDecorView().findViewById(android.R.id.content);
		// mDialogAnim=(AnimationSet) OptAnimationLoader.loadAnimation(context,
		// R.anim.modal_out);
		// mDialogAnim.setAnimationListener(new AnimationListener() {
		//
		// @Override
		// public void onAnimationStart(Animation animation) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onAnimationRepeat(Animation animation) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onAnimationEnd(Animation animation) {
		// // TODO Auto-generated method stub
		// mDialog.setVisibility(View.GONE);
		// mDialog.post(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// //dismiss();
		// cancel();
		// }
		// });
		// }
		// });
		View mView = LayoutInflater.from(context).inflate(
				R.layout.loading_view, null);
		//Direction
		//mView.setLayoutDirection(layoutDirection);
		Window window = getWindow();
		window.setWindowAnimations(R.style.loading_view_animstyle);
		layout = (LinearLayout) findViewById(R.layout.loading_view);
		mView.getBackground().setAlpha(0);
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
