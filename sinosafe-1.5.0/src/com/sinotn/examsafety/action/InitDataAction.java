package com.sinotn.examsafety.action;

import org.apache.log4j.Logger;

import com.sinotn.examsafety.service.impl.SocketExamineeServiceImpl;

/**
 * 初始化数据表action
 * Copyright (c) 2018 by Sinotn	
 * @author Libin
 * @date   2018年4月2日 上午9:37:15
 */
public class InitDataAction extends BaseAction{
	
	private static Logger logger = Logger.getLogger(InitDataAction.class);
	
	private static final long serialVersionUID = 7487178833091579488L;
	
	private SocketExamineeServiceImpl socketExamineeServiceImpl;
	/**
	 * 清空流水表
	 * @return
	 */
	public String deleteScoket(){
		try {
			this.socketExamineeServiceImpl.truncateAllSocket();
		} catch (Exception e) {
			logger.error("清空流水表action异常：" + e);
		}
		return SUCCESS;
	}
	/**
	 * 初始化考生数据
	 * @return
	 */
	public String initResultExaminee(){
		try {
			this.socketExamineeServiceImpl.initResultExaminee();
		} catch (Exception e) {
			logger.error("初始化考生数据action异常：" + e);
		}
		return SUCCESS;
	}
	/**
	 * 初始化统计表
	 * @return
	 */
	public String initResultExamineeSum(){
		try {
			this.socketExamineeServiceImpl.initResultExamineeSum();
		} catch (Exception e) {
			logger.error("初始化统计表action异常：" + e);
		}
		return SUCCESS;
		
	}
	
	
	
	public SocketExamineeServiceImpl getSocketExamineeServiceImpl() {
		return socketExamineeServiceImpl;
	}
	public void setSocketExamineeServiceImpl(
			SocketExamineeServiceImpl socketExamineeServiceImpl) {
		this.socketExamineeServiceImpl = socketExamineeServiceImpl;
	}
	
	
}
