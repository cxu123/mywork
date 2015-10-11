package com.hxyt.utils;

import java.io.File;
import java.io.FilenameFilter;

import android.content.Context;

/** 
 * @author  作者 陈修园
 * @date 创建时间：2015-6-30 下午3:07:07 
 * @version 1.0 
 * @parameter 	log文件查找 
 * @since  
 * @return  
 */
public class LogFileFind implements FilenameFilter {
	
	private	String extension;
	

	public LogFileFind(String extension){
		this.extension=extension;
	}
	
	
	@Override
	public boolean accept(File directory, String filname) {
		// TODO Auto-generated method stub
		return filname.endsWith(extension);  
	}


	
}
