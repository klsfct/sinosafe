package com.sinotn.examsafety.service.impl;

import java.util.List;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.dao.DeviceHandInfoDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.dao.SocketDeviceHandDAO;
import com.sinotn.examsafety.po.DeviceHandInfo;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.SocketDeviceHand;
import com.sinotn.examsafety.service.IMobileDataReceiveService;

public class DeviceHandReceiveImpl implements IMobileDataReceiveService {

	private DeviceHandInfoDAO deviceHandInfoDAO = null;

	private SocketDeviceHandDAO socketDeviceHandDAO = null;

	private ExamPlaceDAO examPlaceDAO = null;

	private int recordNumber = 20;
	@Override
	public void executeDataReceive() {
		List<SocketDeviceHand> socketList = this.getSocketDeviceHandDAO().findSocketDeviceHandList(recordNumber);
		for(SocketDeviceHand socket : socketList){
			DeviceHandInfo deviceHandInfo = this.getDeviceHandInfoDAO()
					.findObjectById(socket.getImeiNo());
			ExamPlace examPlace = this.getExamPlaceDAO().findObjectById(socket.getExamPlace());
			if (deviceHandInfo == null) {
				// 保存新数据
				deviceHandInfo = this.getDeviceHandInfo(deviceHandInfo,socket,examPlace);
				this.getDeviceHandInfoDAO().save(deviceHandInfo);
			} else {
				if (socket.getCreateDate().compareTo(deviceHandInfo.getCreateDate()) > 0) {
					deviceHandInfo = this.getDeviceHandInfo(deviceHandInfo,socket,examPlace);
					this.getDeviceHandInfoDAO().update(deviceHandInfo);
				}

			}
			// 将每次文件处理记录到考点流水
			socket.setIsProcess(true);
			this.getSocketDeviceHandDAO().update(socket);
		}
	}

	private DeviceHandInfo getDeviceHandInfo(DeviceHandInfo deviceHandInfo ,SocketDeviceHand socket, ExamPlace examPlace) {
		if (deviceHandInfo == null) {
			deviceHandInfo = new DeviceHandInfo();
		}
		deviceHandInfo.setCreateDate(socket.getCreateDate());
		deviceHandInfo.setDumpEnergy(socket.getDumpEnergy());
		deviceHandInfo.setExamArea(examPlace.getExamArea());
		deviceHandInfo.setExamAreaName(examPlace.getExamAreaName());
		deviceHandInfo.setExamPlace(examPlace.getExamPlace());
		deviceHandInfo.setExamPlaceName(examPlace.getExamPlaceName());
		deviceHandInfo.setExamProvince(examPlace.getExamProvince());
		deviceHandInfo.setExamProvinceName(examPlace.getExamProvinceName());
		deviceHandInfo.setImeiNo(socket.getImeiNo());
		deviceHandInfo.setModifyDate(DateUtils.getCurrentDate());
		deviceHandInfo.setSignalStrength(socket.getSignalStrength());
		deviceHandInfo.setFilename(socket.getFilename());
		String deviceType = socket.getDeviceType();
		if(deviceType.equals("00001001") || deviceType.equals("00001002") || deviceType.equals("00001003")){
			deviceHandInfo.setDeviceType("手持机");
		}
		else if(deviceType.equals("00001011") || deviceType.equals("00001013")){
			deviceHandInfo.setDeviceType("智能手机");
		}
		else if(deviceType.equals("00001021") || deviceType.equals("00001023")){
			deviceHandInfo.setDeviceType("平板电脑");
		}
		else{
			deviceHandInfo.setDeviceType("");
		}
		return deviceHandInfo;
	}

	public DeviceHandInfoDAO getDeviceHandInfoDAO() {
		return deviceHandInfoDAO;
	}

	public void setDeviceHandInfoDAO(DeviceHandInfoDAO deviceHandInfoDAO) {
		this.deviceHandInfoDAO = deviceHandInfoDAO;
	}

	public SocketDeviceHandDAO getSocketDeviceHandDAO() {
		return socketDeviceHandDAO;
	}

	public void setSocketDeviceHandDAO(SocketDeviceHandDAO socketDeviceHandDAO) {
		this.socketDeviceHandDAO = socketDeviceHandDAO;
	}

	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}
}
