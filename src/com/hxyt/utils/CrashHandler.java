package com.hxyt.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2015-6-23 下午3:39:51
 * @version 1.0 用来捕获app崩溃信息
 * @parameter
 * @since
 * @return
 */
public class CrashHandler implements UncaughtExceptionHandler {
	public static final String TAG = "TEST";
	// CrashHandler 实例
	private static CrashHandler INSTANCE = new CrashHandler();
	private Context mContext;
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	// 用来存储设备信息和异常信息
	private Map<String, String> infos = new HashMap<String, String>();
	// 用来显示Toast中的信息
	private static String error = "服务器正在维护中";
	private static final Map<String, String> regexMap = new HashMap<String, String>();
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
			Locale.CHINA);

	 private TelephonyManager telephonyManager;  
	
	private CrashHandler() {
		//
	}

	public static CrashHandler getInstance() {
		initMap();
		return INSTANCE;
	}

	public void init(Context context) {
		mContext = context;
		// 获取系统默认的 UncaughtException 处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该 CrashHandler 为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
		L.d("TEST", "Crash:init");
	}

	/**
	 * ' * 当 UncaughtException 发生时会转入该函数来处理
	 */

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
			L.d("TEST", "defalut");
		} else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				L.e(TAG, "error : "+ e);
			}
			// 退出程序
			android.os.Process.killProcess(android.os.Process.myPid());
			// mDefaultHandler.uncaughtException(thread, ex);
			System.exit(1);
		}
	}

	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		// 收集设备参数信息
		saveCrashInfo2File(ex);
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}.start();

		return true;
	}

	public void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			L.e(TAG, "an error occured when collect package info");
		}

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
				L.d(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				L.e(TAG, "an error occured when collect crash info");
			}
		}
	}

	/**
	 * 保存错误信息到文件中 *
	 * 
	 * @param ex
	 * @return 返回文件名称,便于将文件传送到服务器
	 */
	private String saveCrashInfo2File(Throwable ex) {
		StringBuffer sb = getTraceInfo(ex);
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		try {
			long timestamp = System.currentTimeMillis();
			String time = formatter.format(new Date());
			telephonyManager=(TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
			String fileName = "crash-" + time + "-" + telephonyManager.getLine1Number() + ".log";
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				String path = Environment.getExternalStorageDirectory()
						+ "/hxyt/Log/";
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return fileName;
		} catch (Exception e) {
			L.e(TAG, "an error occured while writing file...");
		}
		return null;
	}

	/**
	 * 
	 */
	public static StringBuffer getTraceInfo(Throwable e) {
		StringBuffer sb = new StringBuffer();
		Throwable ex = e.getCause() == null ? e : e.getCause();
		StackTraceElement[] stacks = ex.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			if (i == 0) {
				setError(ex.toString());
			}

			sb.append("class: ").append(stacks[i].getClassName())
					.append("; method: ").append(stacks[i].getMethodName())
					.append("; line: ").append(stacks[i].getLineNumber())
					.append(";  Exception: ").append(ex.toString() + "\n");
		}
		L.d(TAG, sb.toString());
		return sb;
	}

	/**
	 * 241 * 设置错误的提示语 242 * @param e 243
	 */
	public static void setError(String e) {
		Pattern pattern;
		Matcher matcher;
		for (Entry<String, String> m : regexMap.entrySet()) {
			L.d(TAG, e + "key:" + m.getKey() + "; value:" + m.getValue());
			pattern = Pattern.compile(m.getKey());
			matcher = pattern.matcher(e);
			if (matcher.matches()) {
				error = m.getValue();
				break;
			}
		}
	}

	/**
	 * 初始化错误的提示语
	 */
	private static void initMap() {
		regexMap.put(".*NullPointerException.*", "空指针");
		regexMap.put(".*ClassNotFoundException.*", "没找到");
		regexMap.put(".*ArithmeticException.*", "运算条件时，抛出此异常");
		regexMap.put(".*ArrayIndexOutOfBoundsException.*", "索引为负或大于等于数组大小");
		regexMap.put(".*IllegalArgumentException.*", "向方法传递了一个不合法或不正确的参数");
		regexMap.put(".*IllegalAccessException.*", "安全权限异常");
		regexMap.put(".*SecturityException.*", "违背安全原则异常");
		regexMap.put(".*NumberFormatException.*", "字符串转换为数字异常");
		regexMap.put(".*OutOfMemoryError.*", "内存溢出");
		regexMap.put(".*StackOverflowError.*", "死循环本");
		regexMap.put(".*RuntimeException.*", "运行时异常");
	}
}
