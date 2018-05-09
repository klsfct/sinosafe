package com.sinotn.examsafety.process;

import java.util.List;

import com.sinotn.examsafety.service.IFaceDataReceiveService;

/**
 * 定时任务类
 * 
 * @author zanggc
 * 
 */
public class QuartzJobFaceExaminee {

	private List<IFaceDataReceiveService> dataReceiveList = null;

	public void work() {
		long beginTime = System.currentTimeMillis();
		int i = 1;
		if(dataReceiveList != null){
			for(IFaceDataReceiveService service:dataReceiveList){
				service.executeDataReceive();
				//System.out.println("安检数据接收服务启动--" + i);
				i++;
			}
		}
		long endTime = System.currentTimeMillis();
		long interval = endTime - beginTime;
		//System.out.println((interval/1000) + "秒");
	}

	public List<IFaceDataReceiveService> getDataReceiveList() {
		return dataReceiveList;
	}

	public void setDataReceiveList(List<IFaceDataReceiveService> dataReceiveList) {
		this.dataReceiveList = dataReceiveList;
	}
}
