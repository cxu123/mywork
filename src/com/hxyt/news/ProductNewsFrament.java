package com.hxyt.news;

import com.hxyt.R;
import com.hxyt.baofoo.BaoFooPayUtils;
import com.hxyt.utils.DownLoadAPP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-22 上午9:19:28
 * @version 1.0 产品信息模块
 * @parameter
 * @since
 * @return
 */

public class ProductNewsFrament extends Fragment {

	private Button downLoadAPK;

	private Button pay;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (savedInstanceState != null) {
			return super.onCreateView(inflater, container, savedInstanceState);
		} else {
			View view = inflater.inflate(R.layout.fragment_product_news,
					container, false);
			downLoadAPK=(Button) view.findViewById(R.id.down);
			pay=(Button) view.findViewById(R.id.pay);
			ini();
			return view;
		}
		
		
		
	}
	
	private void ini(){
		
		downLoadAPK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(getActivity(),DownLoadAPP.class );
				getActivity().startService(intent);
			}
		});
		
		
		pay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BaoFooPayUtils baoFooPayUtils=new BaoFooPayUtils(null, getActivity());
			}
		});
	}
	
	
}
