package com.hxyt;

import java.util.ArrayList;
import java.util.List;

public class ProjectCommand {

    public class ProjectFolder {

	/**
	 * 文件Log文件上传路径
	 */
	public static final String UP_LOAD_LOG_FILE = "";

	/**
	 * 下载下来保存的路径
	 */
	public static final String IMAGE_FILE_PATH = "DownLoad";

	/**
	 * 临时文件夹
	 */
	public static final String TMP_FILE_PATH = "Temp";

	/**
	 * 根文件夹汇信银投
	 */
	public static final String ROOT_FOLDER = "hxyt";

	/**
	 * APP运行报错Log
	 */
	public static final String LOG_FILE_PATH = "Log";
	
	/**
	 * APP从服务器下载的路径
	 */
	public static final String APK_DowndLoad_Path="http://www.c-ehd.com"
			+ "/down/sj.apk";
	
    }

    /**
     * 首页信息
     * 
     * @author 陈修园
     * 
     */
    public static class HOME {
	
	/**
	 * 首页广告的路径
	 */
	public static final String[] AD_IMAGE_LIST = new String[] {
		"http://www.duzait.com/image/index/nav_1.jpg",
		"http://www.duzait.com/image/index/nav_2.jpg",
		"http://www.duzait.com/image/index/nav_3.jpg",
		"http://www.duzait.com/image/index/nav_4.jpg",
		"http://www.duzait.com/image/index/nav_5.jpg" };
	
    }

    public class USER {

	/**
	 * 用户登录url
	 */
	public static final String USER_LOGIN = "";

	/**
	 * 用户注册
	 */
	public static final String USER_REGISTER = "";

	/**
	 * 获得验证码
	 */
	public static final String VERIFICATION_CODE = "";

	/**
	 * 用户重置密码
	 */
	public static final String USER_RESET_PASSWORD = "";
    }

    public class ORDER {

	/**
	 * 获取产品列表
	 */
	public static final String GET_ORDER_LIST = "";

	/**
	 * 获得项目详细信息
	 */
	public static final String GET_ORDER_INFO = "";
    }
}
