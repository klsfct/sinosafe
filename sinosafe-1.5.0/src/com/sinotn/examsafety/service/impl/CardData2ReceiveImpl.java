package com.sinotn.examsafety.service.impl;

import java.util.Date;
import java.util.List;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.dao.ResultCard2DAO;
import com.sinotn.examsafety.dao.ResultExamSumDAO;
import com.sinotn.examsafety.dao.SocketCard2DAO;
import com.sinotn.examsafety.po.ResultCard1;
import com.sinotn.examsafety.po.ResultCard2;
import com.sinotn.examsafety.po.ResultExamSum;
import com.sinotn.examsafety.po.SocketCard1;
import com.sinotn.examsafety.po.SocketCard2;
import com.sinotn.examsafety.service.IMobileDataReceiveService;

public class CardData2ReceiveImpl implements IMobileDataReceiveService {

	private ResultCard2DAO resultCard2DAO = null;

	private SocketCard2DAO socketCard2DAO = null;

	private ResultExamSumDAO resultExamSumDAO = null;
	/*
	 * 每次提交的记录数
	 */
	private int recordNumber = 2000;
	
	private final static String SUBJECT = "2";
	
	@Override
	public void executeDataReceive() {
		
		List<SocketCard2> socketList = this.getSocketCard2DAO().findSocketCard2List(recordNumber);
		if (socketList != null && !socketList.isEmpty()) {
			ResultExamSum resultExamSum = null;
			for (SocketCard2 socketCard : socketList) {
				long examPlace = socketCard.getExamPlace();
				resultExamSum = this.getResultExamSumDAO()
						.findResultExamSum(SUBJECT,examPlace);
				int examRoom = socketCard.getExamRoom();
				ResultCard2 resultCard = this.getResultCard2DAO().getResultCard(examPlace, examRoom);
				if (resultCard == null) {
					resultCard = this.getResultCard(socketCard);
					this.getResultCard2DAO().save(resultCard);
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
						this.getResultCard2DAO().update(resultCard);
						resultExamSum.setCardSum(resultExamSum.getCardSum() + tempSum);
						this.getResultExamSumDAO().update(resultExamSum);
					}
						
				}
				socketCard.setProcessDate(DateUtils.getCurrentDate());
				socketCard.setIsProcess(true);
			}
		}
	}
	private ResultCard2 getResultCard(SocketCard2 socketCard) {
		ResultCard2 resultCard = new ResultCard2();
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

	public ResultCard2DAO getResultCard2DAO() {
		return resultCard2DAO;
	}
	public void setResultCard2DAO(ResultCard2DAO resultCard2DAO) {
		this.resultCard2DAO = resultCard2DAO;
	}
	public SocketCard2DAO getSocketCard2DAO() {
		return socketCard2DAO;
	}
	public void setSocketCard2DAO(SocketCard2DAO socketCard2DAO) {
		this.socketCard2DAO = socketCard2DAO;
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
