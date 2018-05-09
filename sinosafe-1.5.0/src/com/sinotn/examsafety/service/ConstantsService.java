package com.sinotn.examsafety.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义系统常量
 */
public class ConstantsService {

	public static Map<String,String> videoDeviceTypeMap = null;
	
	public static Map<String,String> ObjectMap = null;
	
	/**
	 * 初始化视频设备类型map
	 */
	public static Map<String,String> getVideoDeviceTypeMap(){
		if(videoDeviceTypeMap == null){
			videoDeviceTypeMap = new HashMap<String,String>();
			videoDeviceTypeMap.put("A", "进场监控");
			videoDeviceTypeMap.put("B", "保密室监控");
			videoDeviceTypeMap.put("C", "车载监控");
		}
		return videoDeviceTypeMap;
	}
	
	/**
	 * 试卷运输状态
	 * @return
	 */
	public static Map<String,String> getMoveTypeMap(){
		ObjectMap = new HashMap<String,String>();
		ObjectMap.put("1", "出发");
		ObjectMap.put("2", "送达");
		return ObjectMap;
	}
	
	/**
	 * 角色名称
	 */
	public static Map<String,String> getRoleCode(){
		ObjectMap = new HashMap<String,String>();
		ObjectMap.put("1", "管理人员");
		ObjectMap.put("2", "工作人员");
		ObjectMap.put("3", "试卷安全员");
		return ObjectMap;
	}
	
	/**
	 * 答题文字
	 */
	public static Map<String,String> getCharacter(){
		ObjectMap = new HashMap<String,String>();
		ObjectMap.put("01", "汉语");
		return ObjectMap;
	}
	
	
	
}
