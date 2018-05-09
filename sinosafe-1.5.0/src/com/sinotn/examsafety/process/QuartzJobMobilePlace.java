package com.sinotn.examsafety.process;

import com.sinotn.examsafety.service.impl.DeviceHandReceiveImpl;

/**
 * 定时任务类
 * 
 * @author zanggc
 * 
 */
public class QuartzJobMobilePlace {

	private DeviceHandReceiveImpl deviceHandReceiveImpl = null;

	public void work() {
		deviceHandReceiveImpl.executeDataReceive();
		//System.out.println("题库清点设备服务启动--");
	}

	public DeviceHandReceiveImpl getDeviceHandReceiveImpl() {
		return deviceHandReceiveImpl;
	}

	public void setDeviceHandReceiveImpl(DeviceHandReceiveImpl deviceHandReceiveImpl) {
		this.deviceHandReceiveImpl = deviceHandReceiveImpl;
	}

}

