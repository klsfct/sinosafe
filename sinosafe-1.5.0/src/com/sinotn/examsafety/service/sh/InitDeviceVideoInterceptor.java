package com.sinotn.examsafety.service.sh;

import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sinotn.examsafety.service.JsonService;
import com.sinotn.examsafety.vo.DeviceInfoVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     InitDeviceVideoInterceptor.java
 * @Description:   初始化视频设备在线状态拦截器类
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月19日 上午8:59:26
 */
@SuppressWarnings("serial")
public class InitDeviceVideoInterceptor extends MethodFilterInterceptor{
	
	private AccessTokenServiceImpl accessTokenServiceImpl;
	private DeviceVideoServiceImpl deviceVideoServiceImpl;

	@Override
	protected String doIntercept(ActionInvocation actioninvocation) throws Exception {
		// 获取令牌
		String acceccTokenStr = accessTokenServiceImpl.getAccessTokenForLoad();
		
		// 获取已经注册的视频设备数量
		long devCount = deviceVideoServiceImpl.findDeviceVideoCountByRegFlag(DeviceVideoServiceImpl.REG_FLAG1);
		double count = (double)devCount/50;
		// 调用
		YsyConsts.DEV_MAP.clear();
		/*for(int i = 0 ;i < devCount ; i = i + 50){*/
		for(int i = 0 ;i < count ; i++){
			JSONObject jsonObject = accessTokenServiceImpl.sendList(acceccTokenStr, i , 50);
			if (null != jsonObject.optString("code") && "200".equals(jsonObject.optString("code"))) {
				Map<String,DeviceInfoVo> map = JsonService.getDevListFromJson(jsonObject.toString());
				YsyConsts.DEV_MAP.putAll(map);
			}
			else {
				throw new RuntimeException("请求超时，请稍后重试！");
			}
		}		
		return actioninvocation.invoke();
	}
	
	
	/**-------------------------------------getter/setter*/

	public AccessTokenServiceImpl getAccessTokenServiceImpl() {
		return accessTokenServiceImpl;
	}

	public void setAccessTokenServiceImpl(
			AccessTokenServiceImpl accessTokenServiceImpl) {
		this.accessTokenServiceImpl = accessTokenServiceImpl;
	}

	public DeviceVideoServiceImpl getDeviceVideoServiceImpl() {
		return deviceVideoServiceImpl;
	}

	public void setDeviceVideoServiceImpl(
			DeviceVideoServiceImpl deviceVideoServiceImpl) {
		this.deviceVideoServiceImpl = deviceVideoServiceImpl;
	}
	

}
