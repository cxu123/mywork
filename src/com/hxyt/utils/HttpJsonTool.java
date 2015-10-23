package com.hxyt.utils;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;



import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hxyt.AppContent;
import com.hxyt.ProjectCommand;
import com.hxyt.bean.HeadJson;
import com.hxyt.bean.Row;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.PreferencesCookieStore;

/**
 * * @author 作者 陈修园:
 * 
 * @date 创建时间：2015-5-22 上午11:42:10
 * @version 1.0
 * @parameter 返回的javabeen，RequestParams,dataMap,PreferencesCookieStore
 *            preferencesCookieStore
 * @since
 * @return 返回list javabeen
 */
public class HttpJsonTool {
    private HttpUtils http;
    private Gson gson;
    private boolean flag = false;
    private PreferencesCookieStore preferencesCookieStore;
    private Handler halHandler;
    // 图片handle
    private Handler imageHandler;
    // log日志是上传文件
    private Handler logHandler;

    // private String url;
    private Context context;
    private Handler halHandlerRow;

    public HttpJsonTool(Context context) {
	// this.result_Class=result_class;
	// this.params=params;

	// preferencesCookieStore=preferencesCookieStore;
	http = new HttpUtils();
	gson = new Gson();
	this.context = context;
	// StartWork();
	// List<> data=new ArrayList<>;
    }

    /**
     * 
     * @param context
     *            上下文
     * @param flag
     *            是否要保存回话 true 保存自动保存到Appcontent
     */
    public HttpJsonTool(Context context, boolean flag) {
	gson = new Gson();
	this.context = context;
	this.flag = flag;
	http = new HttpUtils();
	preferencesCookieStore = new PreferencesCookieStore(context);
    }

    /**
     * 
     * @param url
     *            带有Https协议的
     * @param T
     * @param dataMap
     */
    // public void StartHttpsWork(String url,final Class T , Map<String, Object>
    // dataMap){
    // JsonRequest jr = JsonRequest.getInit();
    // jr.setData(dataMap);
    // //dataMap.put("wd", "电影");
    // String json = gson.toJson(dataMap);
    // RequestParams params = new RequestParams();
    // params.addBodyParameter("jsonParam", json);
    // SSLSocketFactory
    // sslsocketfactory=CustomerSocketFactory.getSocketFactory(context);
    // //Type type=new TypeToken<List<T>>() {
    // //}.getType();
    // if (sslsocketfactory==null) {
    // L.v("sslsocketfactory", "sslsocketfactory--》是空");
    // return;
    // }
    // http.configSSLSocketFactory(sslsocketfactory);
    // http.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {
    //
    // @Override
    // public void onFailure(HttpException arg0, String arg1) {
    // // TODO Auto-generated method stub
    // L.v("HTTPS连接失败", ""+arg0.getMessage());
    // L.v("HTTPS连接失败", "HTTPS连接失败");
    // }
    //
    // @Override
    // public void onSuccess(ResponseInfo<String> arg0) {
    // // TODO Auto-generated method stub
    // L.v("HTTPS连接成功", "HTTPS连接成功");
    // L.v("获得12306的信息", ""+arg0.result);
    // }
    // });
    //
    // }
    //
    //

    public void StartWorkUpFile(Object data, String url, final Type type,
	    Map<String, String> filename) {

	String json = gson.toJson(data);
	L.v("json", json);
	RequestParams params = new RequestParams();
	// params.addHeader(name, value);
	// params.setContentType(contentType)
	// RequestParams params=new req
	params.addHeader("Content-Type",
		"multipart/form-data;boundary=*************************7d");
	// params.setHeader("", "");
	// params.setContentType("multipart/form-data;boundary=*************************7d");
	// params.setBodyEntity(bodyEntity);
	if (filename != null && (filename.size() > 0)) {
	    // params.addHeader("Content-Type","multipart/form-data");
	    // params.setContentType("multipart/form-data");
	    for (Map.Entry<String, String> entry : filename.entrySet()) {
		params.addBodyParameter(entry.getKey(),
			new File(entry.getValue()));
		L.v("filename", "filename---->" + entry.getKey());
	    }
	}
	// String root=SD_Finder.GetPaht();
	// if (root==null) {
	// Toast.makeText(context, "SD没有被发现请检查SD卡是否存在",
	// Toast.LENGTH_LONG).show();
	// }
	// File f=new File(root+GagApi.UP_LOAD_FILE);
	// if (f.exists()) {
	// params.addBodyParameter("unusefile",f);
	// }else {
	// try {
	// f.createNewFile();
	// params.addBodyParameter("unusefile",f);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// File file=new f
	params.addBodyParameter("jsonParam", json);
	if (AppContent.getPreferencesCookieStore() != null) {
	    L.v("CookieStore不为空", "CookieStore不为空");
	    http.configCookieStore(AppContent.getPreferencesCookieStore());
	}

	// http.configSSLSocketFactory(sslSocketFactory);
	http.send(HttpRequest.HttpMethod.POST, url, params,
		new RequestCallBack<String>() {

		    @Override
		    public void onFailure(HttpException arg0, String arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "连接网络失败请检查网络...",
				Toast.LENGTH_LONG).show();
			halHandler.sendEmptyMessage(0);
		    }

		    @Override
		    public void onSuccess(ResponseInfo<String> responseInfo) {
			// TODO Auto-generated method stub

			Gson gson = new Gson();
			String jsonString = responseInfo.result;
			L.v("url连接上后获得的URL", "" + jsonString);
			try {
			    HeadJson headJson = gson.fromJson(new JSONObject(
				    jsonString).getJSONObject("head") + "",
				    HeadJson.class);
			    if ("200".equals(headJson.getMessageStaus())) {
				// 连接成功保存会话
				L.e("是否保存会话", flag + "");
				if (flag) {
				    DefaultHttpClient httpClient = (DefaultHttpClient) http
					    .getHttpClient();
				    L.v("cooke", "保存cooke");
				    List<Cookie> cookies = httpClient
					    .getCookieStore().getCookies();
				    for (int i = 0; i < cookies.size(); i++) {
					Cookie cookie = cookies.get(i);
					System.out.println(cookie);
					preferencesCookieStore
						.addCookie(cookie);
					L.v("cooke", "保存cooke");
				    }
				    AppContent
					    .setPreferencesCookieStore(preferencesCookieStore);
				}
				// 连接成功保存会话
				L.v("上传成功！", "上传成功！");
				L.v("获得的全部json", new JSONObject(jsonString)
					+ "");
				if (type != null) {

				    L.v("获得的json",
					    new JSONObject(jsonString)
						    .getJSONArray("data") + "");
				    List<?> data = gson.fromJson(
					    new JSONObject(jsonString)
						    .getJSONArray("data") + "",
					    type);
				    Message msg = Message.obtain();
				    msg.what = 1;
				    msg.obj = data;
				    halHandler.sendMessage(msg);
				} else {
				    L.v("tyep是空", "是空");
				    Message msg = Message.obtain();
				    msg.what = 1;
				    msg.obj = headJson.getMessageContent();
				    halHandler.sendMessage(msg);
				}
			    } else {
				L.v("操作失败", "headJson.getMessageContent()"
					+ headJson.getMessageContent());
				// Toast.makeText(context,
				// headJson.getMessageContent(),
				// Toast.LENGTH_LONG).show();
				Message msg = Message.obtain();
				msg.what = 0;
				msg.obj = headJson.getMessageContent();
				halHandler.sendMessage(msg);
			    }
			} catch (JsonSyntaxException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (JSONException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

		    }
		});
    }

    /**
     * \
     * 
     * @param data
     *            上传的服务器的数据可以是javabeen
     * @param url
     *            服务器的路径url地址
     * @param type
     *            返回数据是list<javabeen>的type 可以为空如果是空智慧返回1 或者其他 1表示操作成功
     * @param file
     *            上传服务器的文件可以是空 如果是空那么就不上传文件了
     */
    public void StartWork(Object data, String url, final Type type,
	    Map<String, String> filename) {

	String json = gson.toJson(data);
	L.v("json", json);
	RequestParams params = new RequestParams();

	if (filename != null && (filename.size() > 0)) {
	    for (Map.Entry<String, String> entry : filename.entrySet()) {
		params.addBodyParameter(entry.getKey(),
			new File(entry.getValue()));
		L.v("filename", "filename---->" + entry.getKey());
	    }
	}
	params.addBodyParameter("jsonParam", json);
	if (!flag) {

	    if (AppContent.getPreferencesCookieStore() != null) {
		L.v("不为空", "不为空");
		http.configCookieStore(AppContent.getPreferencesCookieStore());
		PreferencesCookieStore preferencesCookieStore = AppContent
			.getPreferencesCookieStore();
		if (preferencesCookieStore == null) {

		}
		List<Cookie> cookies = preferencesCookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
		    L.v("---------", "----------");
		    System.out.print(cookies);
		}
	    }
	}
	L.e("url", url);

	// http.configSSLSocketFactory(sslSocketFactory);
	http.send(HttpRequest.HttpMethod.POST, url, params,
		new RequestCallBack<String>() {

		    @Override
		    public void onFailure(HttpException arg0, String arg1) {
			if (!arg1.equals("Not Found")) {
			    // TODO Auto-generated method stub
			    // Toast.makeText(context, "连接网络失败请检查网络...",
			    // Toast.LENGTH_LONG).show();
			    L.e("连接报错---》", arg1);
			    Message message = Message.obtain();
			    message.what = 0;
			    message.obj = "连接网络失败请检查网络...";
			    halHandler.sendMessage(message);
			}
		    }

		    @Override
		    public void onSuccess(ResponseInfo<String> responseInfo) {
			// TODO Auto-generated method stub

			Gson gson = new Gson();
			String jsonString = responseInfo.result;
			L.v("url连接上后获得的URL", "" + jsonString);
			try {
			    HeadJson headJson = gson.fromJson(new JSONObject(
				    jsonString).getJSONObject("head") + "",
				    HeadJson.class);
			    if ("200".equals(headJson.getMessageStaus())) {
				// 连接成功保存会话
				L.e("连接上", "222");
				if (flag) {
				    L.e("连接上2", "保存会话2");
				    DefaultHttpClient httpClient = (DefaultHttpClient) http
					    .getHttpClient();
				    if (httpClient == null) {
					L.e("httpClient", "是空");
				    }
				    List<Cookie> cookies = httpClient
					    .getCookieStore().getCookies();
				    L.e("cookies的长度", "" + cookies.size());
				    for (int i = 0; i < cookies.size(); i++) {
					Cookie cookie = cookies.get(i);
					System.out.println(cookie);
					preferencesCookieStore
						.addCookie(cookie);
					L.e("保存数据Cookie", "Cookie保存数据");
				    }
				    AppContent
					    .setPreferencesCookieStore(preferencesCookieStore);
				}
				// 返回数据总数
				Row row = gson.fromJson(new JSONObject(
					jsonString).getJSONObject("row") + "",
					Row.class);
				Message msg = Message.obtain();
				msg.what = 1;
				msg.obj = row;
				if (halHandlerRow != null) {
				    halHandlerRow.sendMessage(msg);
				}

				// 连接成功保存会话
				L.v("上传成功！", "上传成功！");
				L.v("获得的全部json", new JSONObject(jsonString)
					+ "");
				if (type != null) {

				    L.v("获得的json",
					    new JSONObject(jsonString)
						    .getJSONArray("data") + "");

				    // 返回数据
				    List<?> data = gson.fromJson(
					    new JSONObject(jsonString)
						    .getJSONArray("data") + "",
					    type);
				    Message msg2 = Message.obtain();
				    msg2.what = 1;
				    msg2.obj = data;
				    halHandler.sendMessage(msg2);
				} else {
				    // L.v("tyep是空", "是空");
				    Message msge = Message.obtain();
				    msge.what = 1;
				    msge.obj = headJson.getMessageContent();
				    halHandler.sendMessage(msge);
				}
			    } else if ("7500".equals(headJson.getMessageStaus())) {
				// 会话没有丢失
				// Toast.makeText(context,
				// "司机师傅对不起,您登录超时请从新登录。",
				// Toast.LENGTH_LONG).show();
				// Intent intent = new Intent(context
				// .getApplicationContext(),
				// UserLoginActivity.class);
				// context.startActivity(intent);
			    } else {
				// L.v("操作失败", "headJson.getMessageContent()"
				// + headJson.getMessageContent());
				// Toast.makeText(context,
				// headJson.getMessageContent(),
				// Toast.LENGTH_LONG).show();
				Message msg = Message.obtain();
				msg.what = 0;
				msg.obj = headJson.getMessageContent();
				halHandler.sendMessage(msg);
			    }
			} catch (JsonSyntaxException e) {
			    // TODO Auto-generated catch block
			    // 如果出现异常那么重新登录为了解决会话没有的问题
			    // Intent intent = new Intent(context
			    // .getApplicationContext(),
			    // UserLoginActivity.class);
			    //
			    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    // context.getApplicationContext().startActivity(
			    // intent);
			    // e.printStackTrace();
			} catch (JSONException e) {
			    // TODO Auto-generated catch block
			    // 如果出现异常那么重新登录为了解决会话没有的问题
			    // Intent intent = new Intent(context
			    // .getApplicationContext(),
			    // UserLoginActivity.class);
			    //
			    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    // context.getApplicationContext().startActivity(
			    // intent);
			    // e.printStackTrace();
			}

		    }
		});
    }

    public void UpLoadFile(String path) {
	RequestParams params = new RequestParams();
	params.addBodyParameter("errorLog", new File(path));
	http.configCookieStore(AppContent.getPreferencesCookieStore());
	http.send(HttpRequest.HttpMethod.POST,
		ProjectCommand.ProjectFolder.UP_LOAD_LOG_FILE, params,
		new RequestCallBack<String>() {

		    @Override
		    public void onFailure(HttpException arg0, String arg1) {
			// TODO Auto-generated method stub
			if (logHandler != null) {
			    logHandler.sendEmptyMessage(0);
			}
		    }

		    @Override
		    public void onSuccess(ResponseInfo<String> arg0) {
			// TODO Auto-generated method stub
			if (logHandler != null) {
			    logHandler.sendEmptyMessage(1);
			}
		    }
		});
    }

    // /**
    // * 多文件下载
    // */
    // public void downLoadFile(final List<String> urls) {
    // final int s = 0;
    // setImageLister(new Handler() {
    // @Override
    // public void handleMessage(Message msg) {
    // // TODO Auto-generated method stub
    // super.handleMessage(msg);
    // if (flag) {
    // s++;
    // downLoadFile(urls.get(s));
    // }
    //
    // }
    // });
    // downLoadFile(urls.get(s));
    // }

    /**
     * 
     * @Title: DownLoadFile
     * @Description: TODO(下载文件)
     * @param @param url 参数说明
     * @return void 返回类型
     * @throws
     */
    public void downLoadFile(final String url) {
	// http.download(url, target, callback);
	final String sd_path;
	final String fileName;
	String rootpath = "";
	if (!SDCardUtils.isSDCardEnable()) {
	    // rootpath = SDCardUtils.getSDCardPath();
	    Message msg = Message.obtain();
	    msg.what = 0;
	    msg.obj = "SD卡不存在";
	    if (imageHandler != null) {
		imageHandler.sendMessage(msg);
	    }
	    return;
	} else {
	    rootpath = SDCardUtils.getSDCardPath();
	}
	fileName = url.substring(url.lastIndexOf(File.separator) + 1);
	sd_path = rootpath + File.separator
		+ ProjectCommand.ProjectFolder.IMAGE_FILE_PATH + File.separator
		+ fileName;
	// http.dow
	http.download(url, sd_path, new RequestCallBack<File>() {

	    @Override
	    public void onFailure(HttpException arg0, String arg1) {
		// TODO Auto-generated method stub
		Message msg = Message.obtain();
		msg.what = 0;
		// L.v("下载失败错误CODE", arg1);
		msg.obj = "下载失败";
		if (imageHandler != null) {
		    imageHandler.sendMessage(msg);
		}
		// imageHandler.sendMessage(msg);
	    }

	    @Override
	    public void onSuccess(ResponseInfo<File> file) {
		// TODO Auto-generated method stub
		Message msg = Message.obtain();
		msg.what = 1;
		msg.obj = file.result.getPath();
		if (imageHandler != null) {
		    imageHandler.sendMessage(msg);
		}
		// imageHandler.sendMessage(msg);
	    }

	});
    }

    /**
     * 
     * @Title: checkLogAndUpload
     * @Description: TODO(检查log并且上传)
     * @param 参数说明
     * @return void 返回类型
     * @throws
     */
    public void checkLogAndUpload(Context context) {
	String rootpath = SDCardUtils.getSDCardPath();
	if (rootpath != null && rootpath.length() > 0) {
	    String path = rootpath + ProjectCommand.ProjectFolder.ROOT_FOLDER
		    + File.separator
		    + ProjectCommand.ProjectFolder.LOG_FILE_PATH;
	    File folder = new File(path);
	    List<String> allLogfile = new ArrayList<String>();
	    LogFileFind filter = new LogFileFind("log");
	    String[] logfilenames = folder.list(filter);
	    if (logfilenames != null && logfilenames.length > 0) {

		allLogfile.addAll(Arrays.asList(logfilenames));
		// 计算有多少个文件
		// int fileSum=allLogfile.size();
		for (int i = 0; i < allLogfile.size(); i++) {
		    upLogFIleUpload(path + "/" + allLogfile.get(i),context);
		}
	    }
	}

    }

    private void upLogFIleUpload(final String path,Context context) {
	HttpJsonTool httpJsonTool = new HttpJsonTool(context);
	httpJsonTool.setLogHandle(new Handler() {
	    @Override
	    public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		if (msg.what == 1) {
		    File file = new File(path);
		    file.delete();
		}
	    }
	});
	httpJsonTool.UpLoadFile(path);
    }

    public void setImageLister(Handler handler) {
	this.imageHandler = handler;
    }

    public void SetOnLister(Handler hander) {
	this.halHandler = hander;
    }

    public void SetRowOnLister(Handler halHandlerRow) {
	this.halHandlerRow = halHandlerRow;
    }

    public interface HttpCallBack {
	public void HttpGetData(String json);
    }

    public void setLogHandle(Handler logHandler) {
	this.logHandler = logHandler;
    }

    // public void GetJeson(Handler handler,) {
    //
    // }
}
