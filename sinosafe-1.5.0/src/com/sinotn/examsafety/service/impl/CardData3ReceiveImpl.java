package com.sinotn.examsafety.service.impl;

import java.util.Date;
import java.util.List;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.dao.ResultCard3DAO;
import com.sinotn.examsafety.dao.ResultExamSumDAO;
import com.sinotn.examsafety.dao.SocketCard3DAO;
import com.sinotn.examsafety.po.ResultCard2;
import com.sinotn.examsafety.po.ResultCard3;
import com.sinotn.examsafety.po.ResultExamSum;
import com.sinotn.examsafety.po.SocketCard2;
import com.sinotn.examsafety.po.SocketCard3;
import com.sinotn.examsafety.service.IMobileDataReceiveService;

public class CardData3ReceiveImpl implements IMobileDataReceiveService {

	private ResultCard3DAO resultCard3DAO = null;

	private SocketCard3DAO socketCard3DAO = null;

	private ResultExamSumDAO resultExamSumDAO = null;
	/*
	 * 每次提交的记录数
	 */
	private int recordNumber = 2000;
	
	private final static String SUBJECT = "3";
	
	@Override
	public void executeDataReceive() {
		
		List<SocketCard3> socketList = this.getSocketCard3DAO().findSocketCard3List(recordNumber);
		if (socketList != null && !socketList.isEmpty()) {
			ResultExamSum resultExamSum = null;
			for (SocketCard3 socketCard : socketList) {
				long examPlace = socketCard.getExamPlace();
				resultExamSum = this.getResultExamSumDAO()
						.findResultExamSum(SUBJECT,examPlace);
				int examRoom = socketCard.getExamRoom();
				ResultCard3 resultCard = this.getResultCard3DAO().getResultCard(examPlace, examRoom);
				if (resultCard == null) {
					resultCard = this.getResultCard(socketCard);
					this.getResultCard3DAO().save(resultCard);
					resultExamSum.setCardSum(resultExamSum.getCardSum() + resultCard.getExamCard());
					this.getResultExamSumDAO().update(resultExamSum);
				} else {
					Date mobileDate = socketCard.getBatchDate();
					Date resultDate = resultCard.getBatchDate();
					if (mobileDate.compareTo(resultDate) > 0) {
						int tempSum = socketCard.getExamCard() - resultCard.getExamCard();// 考点对象累计数据减去已经删除的数据
						resultCard.setExamCard(socketCard.getExamCard());
						resultCard.setScanDate(socketCard.getScanDate());
						resultCard.setBatchDate(socketCard.getBatchDate());
						resultCard.setFilename(socketCard.getFilename());
						this.getResultCard3DAO().update(resultCard);
						resultExamSum.setCardSum(resultExamSum.getCardSum() + tempSum);
						this.getResultExamSumDAO().update(resultExamSum);
					}
						
				}
				socketCard.setProcessDate(DateUtils.getCurrentDate());
				socketCard.setIsProcess(true);
			}
		}
	}
	private ResultCard3 getResultCard(SocketCard3 socketCard) {
		ResultCard3 resultCard = new ResultCard3();
		resultCard.setScanDate(socketCard.getScanDate());
		resultCard.setBatchDate(socketCard.getBatchDate());
		resultCard.setExamPlace(socketCard.getExamPlace());
		resultCard.setExamRoom(socketCard.getExamRoom());
		resultCard.setImeiNo(socketCard.getImeiNo());
		resultCard.setProcessDate(DateUtils.getCurrentDate());
		resultCard.setExamCard(socketCard.getExamCard());
		resultCard.setSubject(socketCard.getSubject());
		resultCard.setProcessDate(DateUtils.getCurrentDate());
		resultCard.setFilename(socketCard.getFilename());
		return resultCard;
	}

	public ResultCard3DAO getResultCard3DAO() {
		return resultCard3DAO;
	}
	public void setResultCard3DAO(ResultCard3DAO resultCard3DAO) {
		this.resultCard3DAO = resultCard3DAO;
	}
	public SocketCard3DAO getSocketCard3DAO() {
		return socketCard3DAO;
	}
	public void setSocketCard3DAO(SocketCard3DAO socketCard3DAO) {
		this.socketCard3DAO = socketCard3DAO;
	}
	public ResultExamSumDAO getResultExamSumDAO() {
		return resultExamSumDAO;
	}

	public void setResultExamSumDAO(ResultExamSumDAO resultExamSumDAO) {
		this.resultExamSumDAO = resultExamSumDAO;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	
}
