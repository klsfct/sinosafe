package com.sinotn.examsafety.vo;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.DeviceVideoInfo;
import com.sinotn.examsafety.service.ConstantsService;
import com.sinotn.examsafety.service.sh.YsyConsts;

/**
 * DeviceVideoInfo entity. @author MyEclipse Persistence Tools
 */

public class DeviceVideoInfoVo{

	// Fields

	private String deviceSerial;
	private ExamPlaceVo examPlaceVo;
	private String deviceName;
	private String deviceNumber;
	private String deviceType;
	private String deviceTypeName;
	private String regFlag;
	private Date inputTime;
	private Date regTime;
	
	private String backCode;
	private String backMsg;
	private String validateCode; 
	private String devStatus;
	private String linkManName;
	private String linkManPhone;
	// Constructors

	/** default constructor */
	public DeviceVideoInfoVo() {
	}

	/** minimal constructor */
	public DeviceVideoInfoVo(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	/** full constructor */
	public DeviceVideoInfoVo(DeviceVideoInfo deviceVideoInfoPo) {
		this.backCode = deviceVideoInfoPo.getBackCode();
		this.backMsg = deviceVideoInfoPo.getBackMsg();
		this.validateCode = deviceVideoInfoPo.getValidateCode();
		this.deviceSerial = deviceVideoInfoPo.getDeviceSerial();
		this.examPlaceVo = new ExamPlaceVo(deviceVideoInfoPo.getExamPlace());
		this.deviceName = deviceVideoInfoPo.getDeviceName();
		this.deviceNumber = deviceVideoInfoPo.getDeviceNumber();
		this.deviceType = deviceVideoInfoPo.getDeviceType();
		this.regFlag = deviceVideoInfoPo.getRegFlag();
		this.inputTime = deviceVideoInfoPo.getInputTime();
		this.regTime = deviceVideoInfoPo.getRegTime();
		this.linkManName = deviceVideoInfoPo.getLinkManName();
		this.linkManPhone = deviceVideoInfoPo.getLinkManPhone();
		this.deviceTypeName = ConstantsService.getVideoDeviceTypeMap().get(deviceType);
		this.devStatus = this.findDevStatus(YsyConsts.DEV_MAP, deviceSerial);
		
	}

	public String findDevStatus(Map<String,DeviceInfoVo> devMap,String deviceSerial){
		
		if(!devMap.containsKey(deviceSerial)){
			return "0";
		}else{
			return devMap.get(deviceSerial).getStatus();
		}
	}
		
	
	// Property accessors

	public String getDeviceSerial() {
		return this.deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public ExamPlaceVo getExamPlaceVo() {
		return examPlaceVo;
	}

	public void setExamPlaceVo(ExamPlaceVo examPlaceVo) {
		this.examPlaceVo = examPlaceVo;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getRegFlag() {
		return this.regFlag;
	}

	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getInputTime() {
		return this.inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getBackCode() {
		return backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public String getBackMsg() {
		return backMsg;
	}

	public void setBackMsg(String backMsg) {
		this.backMsg = backMsg;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getDevStatus() {
		return devStatus;
	}

	public void setDevStatus(String devStatus) {
		this.devStatus = devStatus;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}
	
}