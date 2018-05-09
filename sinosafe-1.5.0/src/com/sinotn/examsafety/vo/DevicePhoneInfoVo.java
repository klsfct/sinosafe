package com.sinotn.examsafety.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sinotn.examsafety.po.DevicePhoneInfo;

/**
 * DevicePhoneInfo entity. @author MyEclipse Persistence Tools
 */

public class DevicePhoneInfoVo {

	// Fields

	private String phoneId;
	private String deviceName;
	private String deviceNumber;
	private String phoneModel;
	private String phoneOs;
	private String phone;
	private String weixinId;
	private String weixinName;
	private Date createDate;

	// Constructors

	/** default constructor */
	public DevicePhoneInfoVo() {
	}

	/** minimal constructor */
	public DevicePhoneInfoVo(String phoneId) {
		this.phoneId = phoneId;
	}

	/** full constructor */
	public DevicePhoneInfoVo(String phoneId, String deviceName,
			String deviceNumber, String phoneModel, String phoneOs,
			String phone, String weixinId, String weixinName, Date createDate) {
		this.phoneId = phoneId;
		this.deviceName = deviceName;
		this.deviceNumber = deviceNumber;
		this.phoneModel = phoneModel;
		this.phoneOs = phoneOs;
		this.phone = phone;
		this.weixinId = weixinId;
		this.weixinName = weixinName;
		this.createDate = createDate;
	}
	
	public DevicePhoneInfoVo(DevicePhoneInfo devicePhoneInfo) {
		this.phoneId = devicePhoneInfo.getPhoneId();
		this.deviceName = devicePhoneInfo.getDeviceName();
		this.deviceNumber = devicePhoneInfo.getDeviceNumber();
		this.phoneModel = devicePhoneInfo.getPhoneModel();
		this.phoneOs = devicePhoneInfo.getPhoneOs();
		this.phone = devicePhoneInfo.getPhone();
		this.weixinId = devicePhoneInfo.getWeixinId();
		this.weixinName = devicePhoneInfo.getWeixinName();
		this.createDate = devicePhoneInfo.getCreateDate();
	}

	// Property accessors

	public String getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
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

	public String getPhoneModel() {
		return this.phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getPhoneOs() {
		return this.phoneOs;
	}

	public void setPhoneOs(String phoneOs) {
		this.phoneOs = phoneOs;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixinId() {
		return this.weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getWeixinName() {
		return this.weixinName;
	}

	public void setWeixinName(String weixinName) {
		this.weixinName = weixinName;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}