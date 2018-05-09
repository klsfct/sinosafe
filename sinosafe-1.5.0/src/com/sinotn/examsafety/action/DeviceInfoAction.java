package com.sinotn.examsafety.action;

import java.util.List;

import com.sinotn.examsafety.service.impl.BaseCodeUtils;
import com.sinotn.examsafety.service.impl.DeviceInfoServiceImpl;
import com.sinotn.examsafety.vo.BaseCodeVo;

@SuppressWarnings("serial")
public class DeviceInfoAction extends BaseAction {
	private DeviceInfoServiceImpl deviceInfoServiceImpl = null;
	private List<String[]> resultList = null;
	private int groupField = 1;

	public String groupDeviceFaceByArea() {
		String areaId = this.getAreaId();
		int level = this.getLevel();
		this.resultList = this.getDeviceInfoServiceImpl().groupDeviceFaceByArea(
				areaId,level,groupField);
		return SUCCESS;
	}

	public String groupDeviceHandByArea() {
		String areaId = this.getAreaId();
		int level = this.getLevel();
		this.resultList = this.getDeviceInfoServiceImpl().groupDeviceHandByArea(areaId,level,groupField);
		return SUCCESS;
	}

	public DeviceInfoServiceImpl getDeviceInfoServiceImpl() {
		return deviceInfoServiceImpl;
	}

	public void setDeviceInfoServiceImpl(
			DeviceInfoServiceImpl deviceInfoServiceImpl) {
		this.deviceInfoServiceImpl = deviceInfoServiceImpl;
	}

	public List<String[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<String[]> resultList) {
		this.resultList = resultList;
	}

	public int getGroupField() {
		return groupField;
	}

	public void setGroupField(int groupField) {
		this.groupField = groupField;
	}

}
