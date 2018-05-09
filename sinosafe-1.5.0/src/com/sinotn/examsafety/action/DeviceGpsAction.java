package com.sinotn.examsafety.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.scheduling.annotation.Scheduled;

import net.sf.json.JSONObject;

import com.sinotn.examsafety.po.DeviceGpsInfo;
import com.sinotn.examsafety.service.sh.AccessTokenServiceImpl;
import com.sinotn.examsafety.service.sh.BaiduMapConsts;
import com.sinotn.examsafety.service.sh.DeviceGpsServiceImpl;
import com.sinotn.examsafety.service.sh.DeviceVideoServiceImpl;
import com.sinotn.examsafety.service.sh.SendUrlConsts;
import com.sinotn.examsafety.service.sh.YsyConsts;
import com.sinotn.examsafety.vo.DeviceGpsInfoVo;
import com.sinotn.examsafety.vo.DeviceVideoInfoVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @Description:   定位设备管理action
 * @Author:        李斌
 * @Version:       V1.0.0 
 * @Date:          2017年8月1日 上午10:09:50
 */
@SuppressWarnings("serial")
public class DeviceGpsAction extends BaseAction{
	private static Logger logger = Logger.getLogger(DeviceGpsAction.class);
	
	// 注入定位设备管理业务层
	private DeviceGpsServiceImpl deviceGpsServiceImpl = null;
	
	private DeviceVideoServiceImpl deviceVideoServiceImpl = null;
	
	private AccessTokenServiceImpl accessTokenServiceImpl = null;
	// 定位设备信息VO
	private DeviceGpsInfoVo deviceGpsInfoVo;
	
	private DeviceVideoInfoVo deviceVideoInfoVo;
	
	private String accessToken;
	
	private String appKey;
	
	private String linkManPhone;
	private String linkManName;
	/**
	 * 获取设备
	 * @throws ParseException 
	 */
	public String getDeviceGps() throws ParseException {
		try {
			// 发送查询终端远程请求
			JSONObject jsonObject = BaiduMapConsts.getEntity();
			if ("0".equals(jsonObject.optString("status"))) {
				// 将查询到设备添加到本地数据库中
				this.deviceGpsServiceImpl.saveDeviceGpsInfo(jsonObject);
			}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 同步联系人
	 * @return
	 */
	public String syncLinkMan() {
		try {
			this.deviceGpsServiceImpl.syncLinkMan();
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 更新联系方式
	 */
	public String updateLinkMan(){
		try {
			this.deviceGpsServiceImpl.updateLinkMan(deviceGpsInfoVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 跳转到批量设置起点坐标界面
	 * @return
	 */
	public String gotoSetBeginPonit() {
		
		return SUCCESS;
		
	}
	/**
	 * 批量设置起点坐标同时创建起点地理围栏
	 * @return
	 */
	public String setBeginPonit() {
		try {
			this.deviceGpsServiceImpl.saveBeginPoint(deviceGpsInfoVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 跳转到绑定考点界面
	 * @return
	 */
	public String gotoSetExamPlace() {
		try {
			deviceGpsInfoVo = this.deviceGpsServiceImpl.findDeviceGpsById(deviceGpsInfoVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 绑定考点同时创建终点地理围栏
	 * @return
	 */
	public String setExamPlace() {
		try {
		//后台校验考点唯一性
		String result = this.findDeviecGpsByExamType();
		if ("true".equals(result)) {
			this.deviceGpsServiceImpl.updateExamPlace(deviceGpsInfoVo);
		}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 定时更新设备描述和实时位置
	 */
	public void updateEntityDesc() {
		this.deviceGpsServiceImpl.updateEntityDesc();
	}
	/**
	 * 根据考点信息查询设备集合
	 * @return  true: 有符合数据  false：无数据
	 */
	public String findDeviecGpsByExamType() {
		List<DeviceGpsInfo> list = this.deviceGpsServiceImpl.findDeviecGpsByExamType(deviceGpsInfoVo.getExamPlaceVo().getExamPlace());
		String result = "true";
		if (null != list && list.size()>0) {
			result = "false";
		}
		return result;
		
	}
	/**
	 * AJAX校验考点唯一性
	 */
	public void verifyExamPlace() {
		String result = this.findDeviecGpsByExamType();
		//将校验结果输出到界面
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if (null != outputStream) {
					outputStream.close();
				}
				
			} catch (IOException e) {
				logger.error("DeviceGpsAction--line(159)--IOException:" + e.getMessage());
			} 
		}
        
	}
	/**
	 * 根据gpsID查询视频对象
	 * @return
	 */
	public String getDeviceVideoInfoByGpsId() {
		if (null != deviceGpsInfoVo && !"".equals(deviceGpsInfoVo.getEntityName())) {
			// 根据gpsID 查询gps对象
			DeviceGpsInfoVo deviceGpsInfo = this.deviceGpsServiceImpl.findDeviceGpsByEntityName(deviceGpsInfoVo);
			// 根据考点获取视频对象
			Long examPlace = deviceGpsInfo.getExamPlaceVo().getExamPlace();
			deviceVideoInfoVo = this.deviceVideoServiceImpl.findDeviceVideoByExamPlace(examPlace);
			//获取token
			accessToken = accessTokenServiceImpl.getAccessTokenForLoad();
			appKey = YsyConsts.APP_KEY;
		}
		return SUCCESS;
		
	}
	/**
	 * 根据gpsID查询GPS对象
	 * @return
	 */
	public String goSaveMessageOnlyByGpsId(){
		deviceGpsInfoVo = this.deviceGpsServiceImpl.findDeviceGpsByEntityName(deviceGpsInfoVo);
		this.linkManName = deviceGpsInfoVo.getLinkManName();
		this.linkManPhone = deviceGpsInfoVo.getLinkManPhone();
		return SUCCESS;
		
	}
	/**
	 * 跳转到编辑联系人
	 */
	public String gotoUpdateLinkMan() {
		deviceGpsInfoVo = this.deviceGpsServiceImpl.findDeviceGpsById(deviceGpsInfoVo);
		return SUCCESS;
		
	}
	/** ----------------------------------------getter/setter ------------------------*/
	public DeviceGpsInfoVo getDeviceGpsInfoVo() {
		return deviceGpsInfoVo;
	}

	public void setDeviceGpsInfoVo(DeviceGpsInfoVo deviceGpsInfoVo) {
		this.deviceGpsInfoVo = deviceGpsInfoVo;
	}

	public DeviceGpsServiceImpl getDeviceGpsServiceImpl() {
		return deviceGpsServiceImpl;
	}

	public void setDeviceGpsServiceImpl(DeviceGpsServiceImpl deviceGpsServiceImpl) {
		this.deviceGpsServiceImpl = deviceGpsServiceImpl;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public DeviceVideoServiceImpl getDeviceVideoServiceImpl() {
		return deviceVideoServiceImpl;
	}
	public void setDeviceVideoServiceImpl(
			DeviceVideoServiceImpl deviceVideoServiceImpl) {
		this.deviceVideoServiceImpl = deviceVideoServiceImpl;
	}
	public AccessTokenServiceImpl getAccessTokenServiceImpl() {
		return accessTokenServiceImpl;
	}
	public void setAccessTokenServiceImpl(
			AccessTokenServiceImpl accessTokenServiceImpl) {
		this.accessTokenServiceImpl = accessTokenServiceImpl;
	}
	public DeviceVideoInfoVo getDeviceVideoInfoVo() {
		return deviceVideoInfoVo;
	}
	public void setDeviceVideoInfoVo(DeviceVideoInfoVo deviceVideoInfoVo) {
		this.deviceVideoInfoVo = deviceVideoInfoVo;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getLinkManPhone() {
		return linkManPhone;
	}
	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}
	public String getLinkManName() {
		return linkManName;
	}
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	
}
