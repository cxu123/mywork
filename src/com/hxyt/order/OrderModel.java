package com.hxyt.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hxyt.ProjectCommand;
import com.hxyt.utils.HttpJsonTool;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;


/** 
 * 订单功能
 * @author  作者 陈修园: 
 * @date 创建时间：2015-9-18 下午5:07:34 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */

public class OrderModel {
    //第几次获得信息
    private int getInfoCount=0;
    
    //产品列表
    HttpJsonTool getorderHttp;
    Handler orderHandler;
    //产品详细信息
    HttpJsonTool getOrderInfoHttp;
    Handler orderInfo;
    public void getOrder(Context context){
	getInfoCount++;
	getorderHttp=new HttpJsonTool(context);
	HashMap<String, Object> data=new HashMap<String, Object>();
	data.put("", "");
	if (orderHandler!=null) {
	    getorderHttp.SetOnLister(orderHandler);
	//    getorderHttp.StartWork(data, ProjectCommand.ORDER.GET_ORDER_LIST, null, null);
	}
	List<String> datas=new ArrayList<String>();
	for (int i = 0; i <20; i++) {
	    datas.add("第"+(getInfoCount)+"次获得的第"+i+"个数据");
	}
	Message message=Message.obtain();
	message.what=1;
	message.obj=datas;
	orderHandler.sendMessageDelayed(message, 3000);
	
    }
    
    public void setOrderHandler(Handler handler){
	this.orderHandler=handler;
    }

    
    
    
    public void getOrderInfo(Context context){
	getOrderInfoHttp=new HttpJsonTool(context);
	if (orderInfo!=null) {
	    HashMap<String, Object> data=new HashMap<String, Object>();
	    getOrderInfoHttp.SetOnLister(orderInfo);
	    getOrderInfoHttp.StartWork(data, ProjectCommand.ORDER.GET_ORDER_INFO, null, null);
	}
    }
    
    
    public void setOrderInfoHandler(Handler handler){
	this.orderInfo=handler;
    }
    

}
