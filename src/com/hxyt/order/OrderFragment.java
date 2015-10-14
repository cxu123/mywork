package com.hxyt.order;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.GCMParameterSpec;

import com.hxyt.R;
import com.hxyt.utils.L;
import com.hxyt.utils.T;
import com.hxyt.view.LoadingView;
import com.hxyt.view.PullToRefreshLayout;
import com.hxyt.view.PullToRefreshLayout.OnRefreshListener;
import com.hxyt.view.PullToRefreshView;
import com.hxyt.view.PullableListView;

import android.app.SearchManager.OnCancelListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-21 上午11:26:51
 * @version 1.0
 * @parameter 订单信息
 * @since
 * @return
 */

public class OrderFragment extends Fragment {
	private OnItemOnclick itemclick;
	private PullableListView pullableListView;
	private PullToRefreshLayout pullToRefreshLayout;
	private List<String> data = new ArrayList<String>();
	private LoadingView loadingView;
	private CountDownTimer timer;
	private OrderModel orderModel = new OrderModel();
	/**
	 * listView的适配器
	 */
	private ArrayAdapter adapter;

	public void setOnListener(OnItemOnclick lister) {
		itemclick = lister;
	}

	public interface OnItemOnclick {
		void getItem();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (savedInstanceState != null) {
			return super.onCreateView(inflater, container, savedInstanceState);
		} else {

			View view = inflater.inflate(R.layout.fragment_order, container,
					false);
			pullableListView = (PullableListView) view
					.findViewById(R.id.pullableListView1);
			pullToRefreshLayout = (PullToRefreshLayout) view
					.findViewById(R.id.PullLayout);
			loadingView = new LoadingView(getActivity(),1);
			loadingView.show();
			iniData();

			return view;
		}
	}

	private void iniData() {
		orderModel.setOrderHandler(new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 1) {
					// List<String> d=(List<String>) msg.obj;
					// for (int i = 0; i < d.size(); i++) {
					// data.add(d.get(i));
					// }
					iniListView((List<String>) msg.obj);
				} else {
					loadingView.dismiss();
					T.show(getActivity(), (String) msg.obj);
				}
			}
		});
		orderModel.getOrder(getActivity());

		pullToRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				orderModel.setOrderHandler(new Handler() {
					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						super.handleMessage(msg);

						if (msg.what == 1) {
							iniListView((List<String>) msg.obj);
							pullToRefreshLayout
									.refreshFinish(PullToRefreshLayout.SUCCEED);
						} else {
							pullToRefreshLayout
									.refreshFinish(PullToRefreshLayout.FAIL);
						}
					}
				});
				orderModel.getOrder(getActivity());
			}

			@Override
			public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				orderModel.setOrderHandler(new Handler() {
					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						super.handleMessage(msg);
						if (msg.what == 1) {
							addDataListView((List<String>) msg.obj);
							pullToRefreshLayout
									.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						} else {
							pullToRefreshLayout
									.loadmoreFinish(PullToRefreshLayout.FAIL);
						}
					}
				});
				orderModel.getOrder(getActivity());
			}
		});
	}

	/**
	 * 更新data
	 * 
	 * @param d
	 */
	private void iniListView(List<String> d) {
		if (data != null) {
			data.clear();
		}
		int s = d.size();
		for (int i = 0; i < s; i++) {
			data.add(d.get(i));
		}
		loadingView.Viewdismiss();
		//如果宿主activity已经destiny那么就return
		if (getActivity()==null) {
			//L.e("activity==null");
			return;
		}
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_expandable_list_item_1, data);
		pullableListView.setAdapter(adapter);
		pullableListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				T.show(getActivity(), parent.getItemAtPosition(position)
						.toString());
			}
		});
	}

	/**
	 * 关闭更新
	 */
	private void addDataListView(List<String> d) {
		if (data != null) {
			int s = d.size();
			for (int i = 0; i < s; i++) {
				data.add(d.get(i));
			}
		}
		int cut = pullableListView.getCount();
		adapter.notifyDataSetChanged();
		L.v((pullableListView.getCount() / 2) - 1);
		pullableListView.smoothScrollToPosition(cut + 2);

	}

	/**
	 * 传入需要的参数，设置给arguments
	 * 
	 * @param argument
	 * @return
	 */
	public static OrderFragment newInstance(String argument) {
		Bundle bundle = new Bundle();
		bundle.putString("", argument);
		OrderFragment contentFragment = new OrderFragment();
		contentFragment.setArguments(bundle);
		return contentFragment;
	}
	
	
}
