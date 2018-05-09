package com.sinotn.examsafety.service.sh;

import java.util.HashMap;
import java.util.Map;

import com.sinotn.examsafety.vo.DeviceInfoVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     YsySysData.java
 * @Description:   萤石云平台系统常量
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月14日 下午3:48:50
 */
public class YsyConsts {
	/**
	 * 初始化设备在线状态
	 */
	public static Map<String,DeviceInfoVo> DEV_MAP = new HashMap<String,DeviceInfoVo>();
	
	/**
	 * 萤石云账号绑定appKey
	 */
	public static final String APP_KEY = "29c4b33d01d44d9cb6600328fdbfad41";
	/**
	 * 萤石云账号绑定appSecret
	 */
	public static final String APP_SECRET= "122452eae2ebe8a7d4be785379550dd4";
	/**
	 * 萤石云远程根据appKey和secret获取accessToken的URL
	 */
	public static final String GET_TOKEN_URL= "https://open.ys7.com/api/lapp/token/get";
	/**
	 * 萤石云平台添加设备远程请求URL
	 */
	public static final String ADD_DEVICE_URL= "https://open.ys7.com/api/lapp/device/add";
	/**
	 * 萤石云平台删除设备远程请求URL
	 */
	public static final String DEL_DEVICE_URL= "https://open.ys7.com/api/lapp/device/delete";
	/**
	 * 萤石云平台查询设备列表远程请求URL
	 */
	public static final String LIST_URL= "https://open.ys7.com/api/lapp/device/list";
	/**
	 * 萤石云平台修改设备名称远程请求URL
	 */
	public static final String UPDATE_DEVICENAME_URL = "https://open.ys7.com/api/lapp/device/name/update";
	
	
}	
