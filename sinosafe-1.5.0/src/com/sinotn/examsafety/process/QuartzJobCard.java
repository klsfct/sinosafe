package com.sinotn.examsafety.process;

import java.util.List;

import com.sinotn.examsafety.service.IMobileDataReceiveService;

/**
 * 定时任务类
 * 
 * @author zanggc
 * 
 */
public class QuartzJobCard {

	private List<IMobileDataReceiveService> mobileDataReceiveList = null;
	public void work() {
		int i = 1;
		if(mobileDataReceiveList != null){
			for(IMobileDataReceiveService service:mobileDataReceiveList){
				service.executeDataReceive();
				//System.out.println("题卡数据接收服务启动--" + i);
				i++;
			}
		}
	}

	public List<IMobileDataReceiveService> getMobileDataReceiveList() {
		return mobileDataReceiveList;
	}

	public void setMobileDataReceiveList(
			List<IMobileDataReceiveService> mobileDataReceiveList) {
		this.mobileDataReceiveList = mobileDataReceiveList;
	}
	
}
