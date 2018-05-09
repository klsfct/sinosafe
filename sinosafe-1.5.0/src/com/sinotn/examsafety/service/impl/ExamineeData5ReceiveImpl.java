package com.sinotn.examsafety.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.StringUtils;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.dao.ExamineeDAO;
import com.sinotn.examsafety.dao.ResultExamSumDAO;
import com.sinotn.examsafety.dao.ResultExaminee5DAO;
import com.sinotn.examsafety.dao.ResultExamineeExitDAO;
import com.sinotn.examsafety.dao.SocketExaminee5DAO;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.Examinee;
import com.sinotn.examsafety.po.ResultExamSum;
import com.sinotn.examsafety.po.ResultExaminee5;
import com.sinotn.examsafety.po.ResultExamineeExit;
import com.sinotn.examsafety.po.SocketExaminee5;
import com.sinotn.examsafety.service.IFaceDataReceiveService;

public class ExamineeData5ReceiveImpl implements IFaceDataReceiveService {
	private static Logger logger = Logger.getLogger(ExamineeData5ReceiveImpl.class);
	private ResultExaminee5DAO resultExaminee5DAO = null;

	private SocketExaminee5DAO socketExaminee5DAO = null;

	private ResultExamineeExitDAO resultExamineeExitDAO = null;

	private ResultExamSumDAO resultExamSumDAO = null;

	private ExamineeDAO examineeDAO = null;
	
	private ExamPlaceDAO examPlaceDAO = null;
	/*
	 * 每次提交的记录数
	 */
	private int recordNumber = 2000;

	private final static String SUBJECT = "5";

	@Override
	public void executeDataReceive() {
		// 从socketExaminee5中取出待处理数据
		List<SocketExaminee5> socketList = this.getSocketExaminee5DAO()
				.findSocketExaminee5List(recordNumber);
		if (socketList != null && !socketList.isEmpty()) {
			for (SocketExaminee5 socketExaminee : socketList) {
				Date faceDate = socketExaminee.getCreateDate();// 安检时间
				long examPlaceId = socketExaminee.getExamPlace();// 考点ID
				String licence = socketExaminee.getLicence();// 准考证号
				ResultExamSum resultExamSum = this.getResultExamSumDAO()
						.findResultExamSum(SUBJECT, examPlaceId);// 获得考点统计对象
				// 两种处理模式0为进场模式、1为退场模式
				if (socketExaminee.getIsException()) {// 退场数据处理
					ResultExamineeExit exitExaminee = this
							.getResultExamineeExitDAO().getResultExamineeExit(
									SUBJECT, licence);
					if (exitExaminee == null) {
						exitExaminee = new ResultExamineeExit();
						Examinee examinee = this.getExamineeDAO()
								.findObjectById(licence);
						exitExaminee.setLicence(licence);
						exitExaminee.setSubject(SUBJECT);
						exitExaminee.setImeiNo(socketExaminee.getImeiNo());
						exitExaminee.setCreateDate(socketExaminee
								.getCreateDate());
						exitExaminee.setModifyDate(DateUtils.getCurrentDate());
						exitExaminee.setPhotoResult(socketExaminee
								.getPhotoResult());
						exitExaminee.setIsPass(socketExaminee.getIsPass());
						exitExaminee.setFaceExamineeName(socketExaminee
								.getFaceExamineeName());
						exitExaminee.setFaceIndetityDate(socketExaminee
								.getFaceIndetityDate());
						exitExaminee.setPhotoPrecision(socketExaminee
								.getPhotoPrecision());
						exitExaminee.setFilename(socketExaminee.getFilename());
						exitExaminee.setAbsolutePath(socketExaminee
								.getAbsolutePath());
						exitExaminee.setRelativePath(socketExaminee
								.getRelativePath());
						exitExaminee
								.setExamProvince(examinee.getExamProvince());
						exitExaminee.setExamProvinceName(examinee
								.getExamProvinceName());
						exitExaminee.setExamArea(examinee.getExamArea());
						exitExaminee
								.setExamAreaName(examinee.getExamAreaName());
						exitExaminee.setExamPlace(examinee.getExamPlace());
						exitExaminee.setExamPlaceName(examinee
								.getExamPlaceName());
						exitExaminee.setExamRoom(examinee.getExamRoom());
						exitExaminee.setSeatNumber(examinee.getSeatNumber());
						exitExaminee
								.setExamineeName(examinee.getExamineeName());
						exitExaminee.setIdentity(examinee.getIdentity());
						exitExaminee.setPhotoPath(examinee.getPhotoPath());
						this.getResultExamineeExitDAO().save(exitExaminee);
						// 计数
						resultExamSum.setPassExitSum(resultExamSum
								.getPassExitSum() + 1);
					} else if (exitExaminee.getCreateDate() != null
							&& faceDate.compareTo(exitExaminee.getCreateDate()) > 0) {
						exitExaminee.setImeiNo(socketExaminee.getImeiNo());
						exitExaminee.setCreateDate(socketExaminee
								.getCreateDate());
						exitExaminee.setModifyDate(DateUtils.getCurrentDate());
						exitExaminee.setPhotoResult(socketExaminee
								.getPhotoResult());
						exitExaminee.setIsPass(socketExaminee.getIsPass());
						exitExaminee.setFaceExamineeName(socketExaminee
								.getFaceExamineeName());
						exitExaminee.setFaceIndetityDate(socketExaminee
								.getFaceIndetityDate());
						exitExaminee.setPhotoPrecision(socketExaminee
								.getPhotoPrecision());
						exitExaminee.setFilename(socketExaminee.getFilename());
						exitExaminee.setAbsolutePath(socketExaminee
								.getAbsolutePath());
						exitExaminee.setRelativePath(socketExaminee
								.getRelativePath());
						this.getResultExamineeExitDAO().update(exitExaminee);
					}

				} else {
					// 进场数据处理
					try {
						ResultExaminee5 resultExaminee = this
								.getResultExaminee5DAO().getResultExaminee(licence);// 场次一考生对象
						
						if (resultExaminee != null) {
							/**
							 * 根据考点ID更新socket表省、市、考点名称字段
							 */
							ExamPlace examPlace = this.examPlaceDAO.findObjectById(socketExaminee.getExamPlace());
							if (null != examPlace) {
								socketExaminee.setExamProvince(examPlace.getExamProvince());
								socketExaminee.setExamProvinceName(examPlace.getExamProvinceName());
								socketExaminee.setExamArea(examPlace.getExamArea());
								socketExaminee.setExamAreaName(examPlace.getExamAreaName());
								socketExaminee.setExamPlaceName(examPlace.getExamPlaceName());
							}
							else {
								logger.error("数据考点不存在——异常ID：【Table：SOCKET_EXAMINEE_5  ID:" + socketExaminee.getId() + "】");
							}
							
							Date resultDate = resultExaminee.getCreateDate();// 处理时间
							if (!resultExaminee.getIsProcess()) {
								resultExaminee = getResultExaminee(resultExaminee,
										socketExaminee);
								
								this.getResultExaminee5DAO().update(resultExaminee);
								// 安检人数
								resultExamSum
										.setPassSum(resultExamSum.getPassSum() + 1);
								// 二代证扫描已识别人数计数
								if (resultExaminee.getFaceExamineeName() == null
										|| resultExaminee.getFaceExamineeName()
												.equals("")) {
									resultExamSum.setIdentitySumN(resultExamSum
											.getIdentitySumN() + 1);
								} else {
									resultExamSum.setIdentitySumY(resultExamSum
											.getIdentitySumY() + 1);
								}
								// 安检计数
								resultExamSum.setPassNormalSum(resultExamSum
										.getPassNormalSum() + 1);

								// 人脸识别通过计数
								if (resultExaminee.getIsPass()) {
									resultExamSum.setPhotoSumY(resultExamSum
											.getPhotoSumY() + 1);
								} else {
									resultExamSum.setPhotoSumN(resultExamSum
											.getPhotoSumN() + 1);
									resultExamSum.setPhotoSumNn(resultExamSum
											.getPhotoSumN());
								}
							} else if (faceDate.compareTo(resultDate) > 0
									&& !resultExaminee.getIsVerify().booleanValue()
									&& !resultExaminee.getIsPass()) {
								resultExaminee = getResultExaminee(resultExaminee,
										socketExaminee);
								this.getResultExaminee5DAO().update(resultExaminee);
								// 正常通道计数
								resultExamSum.setPassNormalSum(resultExamSum
										.getPassNormalSum() + 1);
								resultExamSum.setPassSpecialSum(resultExamSum
										.getPassSpecialSum() - 1);
								// 人脸识别通过计数
								if (resultExaminee.getIsPass().booleanValue()) {
									resultExamSum.setPhotoSumY(resultExamSum
											.getPhotoSumY() + 1);
									resultExamSum.setPhotoSumN(resultExamSum
											.getPhotoSumN() - 1);
									resultExamSum.setPhotoSumNn(resultExamSum
											.getPhotoSumN());
								}
							}
						}else {
							logger.error("数据异常——异常ID：【Table：SOCKET_EXAMINEE_5  ID:" + socketExaminee.getId() + "】");
						}
					} catch (Exception e) {
						logger.error("无效数据——异常ID：【Table：SOCKET_EXAMINEE_5  ID:" + socketExaminee.getId() + "】");
					}
					
					}
				socketExaminee.setIsProcess(true);
				socketExaminee.setModifyDate(DateUtils.getCurrentDate());
				}
					
				}
				
	}

	

	private ResultExaminee5 getResultExaminee(ResultExaminee5 resultExaminee,
			SocketExaminee5 socketExaminee) {
		
		resultExaminee.setImeiNo(socketExaminee.getImeiNo());
		resultExaminee.setCreateDate(socketExaminee.getCreateDate());
		resultExaminee.setModifyDate(DateUtils.getCurrentDate());
		resultExaminee.setPhotoResult(socketExaminee.getPhotoResult());
		resultExaminee.setIsPass(socketExaminee.getIsPass());
		resultExaminee.setFaceExamineeName(socketExaminee.getFaceExamineeName());
		resultExaminee.setFaceIndetityDate(socketExaminee.getFaceIndetityDate());
		resultExaminee.setPhotoPrecision(socketExaminee.getPhotoPrecision());
		resultExaminee.setFilename(socketExaminee.getFilename());
		resultExaminee.setIsProcess(true);
		resultExaminee.setAbsolutePath(socketExaminee.getAbsolutePath());
		resultExaminee.setRelativePath(socketExaminee.getRelativePath());
		
		/**
		 * 更新：
		 * 检测是否有报名照片，如没有，则更新photoPath为absolutePath
		 * 时间：2018.3.21
		 * 李斌
		 */
		if (StringUtils.isEmpty(resultExaminee.getPhotoPath())) {
			resultExaminee.setPhotoPath(socketExaminee.getRelativePath());
		}
		return resultExaminee;
	}

	

	public ResultExaminee5DAO getResultExaminee5DAO() {
		return resultExaminee5DAO;
	}



	public void setResultExaminee5DAO(ResultExaminee5DAO resultExaminee5DAO) {
		this.resultExaminee5DAO = resultExaminee5DAO;
	}



	public SocketExaminee5DAO getSocketExaminee5DAO() {
		return socketExaminee5DAO;
	}



	public void setSocketExaminee5DAO(SocketExaminee5DAO socketExaminee5DAO) {
		this.socketExaminee5DAO = socketExaminee5DAO;
	}



	public ResultExamineeExitDAO getResultExamineeExitDAO() {
		return resultExamineeExitDAO;
	}

	public void setResultExamineeExitDAO(
			ResultExamineeExitDAO resultExamineeExitDAO) {
		this.resultExamineeExitDAO = resultExamineeExitDAO;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

	public ResultExamSumDAO getResultExamSumDAO() {
		return resultExamSumDAO;
	}

	public void setResultExamSumDAO(ResultExamSumDAO resultExamSumDAO) {
		this.resultExamSumDAO = resultExamSumDAO;
	}

	public ExamineeDAO getExamineeDAO() {
		return examineeDAO;
	}

	public void setExamineeDAO(ExamineeDAO examineeDAO) {
		this.examineeDAO = examineeDAO;
	}

	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}

}