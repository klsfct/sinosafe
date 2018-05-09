package com.sinotn.examsafety.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

import struct.JavaStruct;
import struct.StructException;
import struct.StructUnpacker;

import com.sinotn.common.utils.FileUtils;
import com.sinotn.examsafety.service.impl.DeviceFaceReceiveImpl;
import com.sinotn.examsafety.struct.FacePlaceStruct;

/**
 * 定时任务类
 * 
 * @author zanggc
 * 
 */
public class QuartzJobFacePlace {

	private DeviceFaceReceiveImpl deviceFaceReceiveImpl = null;

	public void work() {
		deviceFaceReceiveImpl.executeDataReceive();
		//System.out.println("人脸U盾设备绑定考点服务启动--");
	}

	public DeviceFaceReceiveImpl getDeviceFaceReceiveImpl() {
		return deviceFaceReceiveImpl;
	}

	public void setDeviceFaceReceiveImpl(
			DeviceFaceReceiveImpl deviceFaceReceiveImpl) {
		this.deviceFaceReceiveImpl = deviceFaceReceiveImpl;
	}
}
