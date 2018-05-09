package com.sinotn.examsafety.service.impl;

import org.apache.log4j.Logger;

import com.sinotn.examsafety.dao.SocketExamineeDAO;

/**
 * 流水表service
 * @author Libin
 *
 */
public class SocketExamineeServiceImpl {
	
	private static Logger logger = Logger.getLogger(SocketExamineeServiceImpl.class);
	
	private SocketExamineeDAO socketExamineeDAO;
	
	
	/**
	 * 清空所有流水表数据
	 */
	public void truncateAllSocket() {
		String [] tableNameArr = {"SocketExaminee1", "SocketExaminee2", "SocketExaminee3",
				"SocketExaminee4", "SocketExaminee5", "SocketExaminee6"};
		try {
			for (int i = 0; i < tableNameArr.length; i++) {
				this.socketExamineeDAO.truncateAllSocket(tableNameArr[i]);
			}
		} catch (Exception e) {
			logger.error("清空流水表service异常：" + e);
		}
	}
	/**
	 * 初始化考生数据
	 */
	public void initResultExaminee() {
		String [] tableNameArr = {"ResultExaminee1", "ResultExaminee2", "ResultExaminee3",
				"ResultExaminee4", "ResultExaminee5", "ResultExaminee6"};
		try {
			for (int i = 0; i < tableNameArr.length; i++) {
				this.socketExamineeDAO.initResultExaminee(tableNameArr[i]);
			}
		} catch (Exception e) {
			logger.error("清空流水表service异常：" + e);
		}
	}
	/**
	 * 初始化统计表
	 */
	public void initResultExamineeSum() {
		String tableName = "ResultExamSum";
		try {
			this.socketExamineeDAO.initResultExamineeSum(tableName);
		} catch (Exception e) {
			logger.error("清空流水表service异常：" + e);
		}
		
	}
	public SocketExamineeDAO getSocketExamineeDAO() {
		return socketExamineeDAO;
	}


	public void setSocketExamineeDAO(SocketExamineeDAO socketExamineeDAO) {
		this.socketExamineeDAO = socketExamineeDAO;
	}
	


	
	
	
}
