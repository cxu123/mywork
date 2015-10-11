package com.hxyt.order;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.hxyt.BaseActivityOnTouch;
import com.hxyt.R;
import com.hxyt.utils.T;

/**
 * @author 作者 陈修园:
 * @date 创建时间：2015-9-28 上午11:19:11
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class OrderInfoActivity extends BaseActivityOnTouch {

    private ListView listView;
    private Button button;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<String>();

    @Override
    protected void findViews() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activity_orderinfo);
	button = (Button) findViewById(R.id.order_button_1);
	listView = (ListView) findViewById(R.id.order_listView_1);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	for (int i = 0; i < 100; i++) {
	    dataList.add("" + i);
	}
	adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, dataList);
	listView.setAdapter(adapter);
    }

    @Override
    protected void setViewOnlister() {
	// TODO Auto-generated method stub
	button.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		T.show(OrderInfoActivity.this, "点击的是button");
	    }
	});

	listView.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		// TODO Auto-generated method stub
		T.show(OrderInfoActivity.this, "点击的是第" + position + "个数据");
	    }
	});
    }

}
