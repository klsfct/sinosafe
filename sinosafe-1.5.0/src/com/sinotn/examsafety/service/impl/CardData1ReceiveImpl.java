package com.sinotn.examsafety.service.impl;

import java.util.Date;
import java.util.List;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.dao.ResultCard1DAO;
import com.sinotn.examsafety.dao.ResultExamSumDAO;
import com.sinotn.examsafety.dao.SocketCard1DAO;
import com.sinotn.examsafety.po.ResultCard1;
import com.sinotn.examsafety.po.ResultExamSum;
import com.sinotn.examsafety.po.SocketCard1;
import com.sinotn.examsafety.service.IMobileDataReceiveService;

public class CardData1ReceiveImpl implements IMobileDataReceiveService {

	private ResultCard1DAO resultCard1DAO = null;

	private SocketCard1DAO socketCard1DAO = null;

	private ResultExamSumDAO resultExamSumDAO = null;
	/*
	 * 每次提交的记录数
	 */
	private int recordNumber = 2000;
	
	private final static String SUBJECT = "1";
	
	@Override
	public void executeDataReceive() {
		
		List<SocketCard1> socketList = this.getSocketCard1DAO().findSocketCard1List(recordNumber);
		if (socketList != null && !socketList.isEmpty()) {
			ResultExamSum resultExamSum = null;
			for (SocketCard1 socketCard : socketList) {
				long examPlace = socketCard.getExamPlace();
				resultExamSum = this.getResultExamSumDAO()
						.findResultExamSum(SUBJECT,examPlace);
				int examRoom = socketCard.getExamRoom();
				ResultCard1 resultCard = this.getResultCard1DAO().getResultCard(examPlace, examRoom);
				if (resultCard == null) {
					resultCard = this.getResultCard(socketCard);
					this.getResultCard1DAO().save(resultCard);
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
						this.getResultCard1DAO().update(resultCard);
						resultExamSum.setCardSum(resultExamSum.getCardSum() + tempSum);
						this.getResultExamSumDAO().update(resultExamSum);
					}
						
				}
				socketCard.setProcessDate(DateUtils.getCurrentDate());
				socketCard.setIsProcess(true);
			}
			
		}
	}
	private ResultCard1 getResultCard(SocketCard1 socketCard) {
		ResultCard1 resultCard = new ResultCard1();
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

	public ResultCard1DAO getResultCard1DAO() {
		return resultCard1DAO;
	}

	public void setResultCard1DAO(ResultCard1DAO resultCard1DAO) {
		this.resultCard1DAO = resultCard1DAO;
	}

	public SocketCard1DAO getSocketCard1DAO() {
		return socketCard1DAO;
	}

	public void setSocketCard1DAO(SocketCard1DAO socketCard1DAO) {
		this.socketCard1DAO = socketCard1DAO;
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
