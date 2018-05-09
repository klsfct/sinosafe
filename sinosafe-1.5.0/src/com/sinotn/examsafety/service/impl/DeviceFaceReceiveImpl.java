package com.sinotn.examsafety.service.impl;

import java.util.Date;
import java.util.List;

import com.sinotn.common.exception.DaoException;
import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.dao.DeviceFaceInfoDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.dao.SocketDeviceFaceDAO;
import com.sinotn.examsafety.po.DeviceFaceInfo;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.SocketDeviceFace;
import com.sinotn.examsafety.service.IFaceDataReceiveService;
import com.sinotn.examsafety.struct.BaseStruct;
import com.sinotn.examsafety.struct.FacePlaceStruct;

public class DeviceFaceReceiveImpl implements IFaceDataReceiveService {
	private DeviceFaceInfoDAO deviceFaceInfoDAO = null;

	private SocketDeviceFaceDAO socketDeviceFaceDAO = null;

	private ExamPlaceDAO examPlaceDAO = null;

	@Override
	public void executeDataReceive() {
		
		List<SocketDeviceFace> socketList = this.getSocketDeviceFaceDAO().findSocketDeviceFaceList();
		if(socketList !=null && !socketList.isEmpty()){
			for(SocketDeviceFace socket : socketList){
				long examPlaceId = socket.getExamPlace();
				ExamPlace examPlace = this.getExamPlaceDAO()
						.findObjectById(examPlaceId);
				if(examPlace!=null){
					String imei = socket.getImeiNo();
					DeviceFaceInfo deviceFaceInfo = this.getDeviceFaceInfoDAO()
							.findObjectById(imei);
					if (deviceFaceInfo == null) {
						// 保存新数据
						deviceFaceInfo = this.getDeviceFaceInfo(deviceFaceInfo,
								socket, examPlace);
						this.getDeviceFaceInfoDAO().save(deviceFaceInfo);
					} else {
						// 更新已有数据
						Date faceDate = socket.getCreateDate();
						if (faceDate.compareTo(deviceFaceInfo.getCreateDate()) > 0) {
							deviceFaceInfo = this.getDeviceFaceInfo(deviceFaceInfo,
									socket, examPlace);
							this.getDeviceFaceInfoDAO().update(deviceFaceInfo);
						}
					}
					// 将每次文件处理记录到考点流水
					SocketDeviceFace socketPlace = this.getSocketPlace(socket,
							examPlace);
					this.getSocketDeviceFaceDAO().update(socketPlace);
				}
				
			}
		}
	}

	private SocketDeviceFace getSocketPlace(SocketDeviceFace socket,
			ExamPlace examPlace) {
		socket.setExamArea(examPlace.getExamArea());
		socket.setExamAreaName(examPlace.getExamAreaName());
		socket.setExamPlace(examPlace.getExamPlace());
		socket.setExamPlaceName(examPlace.getExamPlaceName());
		socket.setExamProvince(examPlace.getExamProvince());
		socket.setExamProvinceName(examPlace.getExamProvinceName());
		socket.setModifyDate(DateUtils.getCurrentDate());
		socket.setIsProcess(true);
		return socket;
	}

	private DeviceFaceInfo getDeviceFaceInfo(DeviceFaceInfo deviceFaceInfo,
			SocketDeviceFace socket, ExamPlace examPlace) {
		if (deviceFaceInfo == null) {
			deviceFaceInfo = new DeviceFaceInfo();
		}
		deviceFaceInfo.setCreateDate(socket.getCreateDate());
		deviceFaceInfo.setExamArea(examPlace.getExamArea());
		deviceFaceInfo.setExamAreaName(examPlace.getExamAreaName());
		deviceFaceInfo.setExamPlace(examPlace.getExamPlace());
		deviceFaceInfo.setExamPlaceName(examPlace.getExamPlaceName());
		deviceFaceInfo.setExamProvince(examPlace.getExamProvince());
		deviceFaceInfo.setExamProvinceName(examPlace.getExamProvinceName());
		deviceFaceInfo.setImeiNo(socket.getImeiNo());
		deviceFaceInfo.setModifyDate(DateUtils.getCurrentDate());
		deviceFaceInfo.setVerifyPrecision(socket.getVerifyPrecision());
		deviceFaceInfo.setSubject(socket.getSubject());
		deviceFaceInfo.setFilename(socket.getFilename());
		return deviceFaceInfo;
	}

	public DeviceFaceInfoDAO getDeviceFaceInfoDAO() {
		return deviceFaceInfoDAO;
	}

	public void setDeviceFaceInfoDAO(DeviceFaceInfoDAO deviceFaceInfoDAO) {
		this.deviceFaceInfoDAO = deviceFaceInfoDAO;
	}

	public SocketDeviceFaceDAO getSocketDeviceFaceDAO() {
		return socketDeviceFaceDAO;
	}

	public void setSocketDeviceFaceDAO(SocketDeviceFaceDAO socketDeviceFaceDAO) {
		this.socketDeviceFaceDAO = socketDeviceFaceDAO;
	}

	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}

}
