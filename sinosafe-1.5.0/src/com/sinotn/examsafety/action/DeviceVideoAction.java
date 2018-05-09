package com.sinotn.examsafety.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.po.DeviceVideoInfo;
import com.sinotn.examsafety.service.impl.BaseCodeUtils;
import com.sinotn.examsafety.service.sh.AccessTokenServiceImpl;
import com.sinotn.examsafety.service.sh.DeviceVideoServiceImpl;
import com.sinotn.examsafety.service.sh.YsyConsts;
import com.sinotn.examsafety.vo.BaseCodeVo;
import com.sinotn.examsafety.vo.DeviceVideoInfoVo;
import com.sinotn.examsafety.vo.ExamPlaceVo;
import com.sinotn.examsafety.vo.ZtreeJsonVO;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     DeviceVideoAction.java
 * @Description:   视频设备信息查询action
 * @author:    李斌
 * @Version:   V1.0  
 * @date:      2017年7月12日 下午1:29:35
 */
@SuppressWarnings("serial")
public class DeviceVideoAction extends BaseAction{
	private static Logger logger = Logger.getLogger(DeviceVideoAction.class);
	// 注入视频管理业务层
	private DeviceVideoServiceImpl deviceVideoServiceImpl = null;
	
	// 注入视频设备令牌业务类
	private AccessTokenServiceImpl accessTokenServiceImpl = null;
	
	// 视频设备VO
	private DeviceVideoInfoVo deviceVideoInfoVo;
	
	// 返回JSON类型的zTree
	private List<ZtreeJsonVO> zJsonList;
	
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * 跳转到新增视频设备信息界面
	 */
	public String gotoSaveDeviceVideo(){
		return SUCCESS;
	}
	
	/**
	 * 获取考点省份
	 */
	public void getBaseProvince(){
		List<BaseCodeVo> listPro = BaseCodeUtils.findCodeList("AREA_PROVINCE", true);
		try {
			listToJson(listPro);
		} catch (Exception e) {
			logger.error("DeviceVideoAction--line(70)--Exception:" + e.getMessage());
		}
	}
	/**
	 * 获取考点市区
	 */
	public void getBaseArea(){
		String previousId = getRequest().getParameter("previousId");
		List<BaseCodeVo> listArea = BaseCodeUtils.findCodeListByPreviousId(previousId, true, false);
		try {
			listToJson(listArea);
		} catch (Exception e) {
			logger.error("DeviceVideoAction--line(82)--Exception:" + e.getMessage());
		}
	}
	/**
	 * 获取考点信息
	 */
	public void getBaseExamPalce(){
		String examArea = getRequest().getParameter("examArea");
		List<ExamPlaceVo> listExamPalce = deviceVideoServiceImpl.findExamPlaceList(examArea, 1);
		try {
			listToJson(listExamPalce);
		} catch (Exception e) {
			logger.error("DeviceVideoAction--line(94)--Exception:" + e.getMessage());
		}
	}
	/**
	 * 保存新增视频设备
	 */
	public String saveDeviceVideo() {
		try {
			deviceVideoInfoVo.setInputTime(DateUtils.getCurrentDate());
			this.getDeviceVideoServiceImpl().saveDeviceVideo(deviceVideoInfoVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 跳转到修改视频设备信息
	 */
	public String gotoUpdateDeviceVideo() {
		deviceVideoInfoVo = this.getDeviceVideoServiceImpl().findDeviceVideoBySerial(deviceVideoInfoVo);
		return SUCCESS;
	}
	/**
	 * 更新视频设备信息
	 */
	public String updateDeviceVideo() {
		try {
			String flag = this.getDeviceVideoServiceImpl().updateDeviceVideo(deviceVideoInfoVo);
			// 根据返回标识判断是否远程更新设备名称
			if ("1".equals(flag)) {
				String accessTokenStr = accessTokenServiceImpl.getAccessTokenForLoad();
				String deviceSerial = deviceVideoInfoVo.getDeviceSerial();
				String deviceName = deviceVideoInfoVo.getDeviceName();
				httpUpdateDeciveName(accessTokenStr, deviceSerial, deviceName);
			}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 注册设备
	 */
	public String regDeviceVideo() {
		try {
		deviceVideoInfoVo = this.getDeviceVideoServiceImpl().findDeviceVideoBySerial(deviceVideoInfoVo);
		// 获取设备序列号
		String deviceSerial = deviceVideoInfoVo.getDeviceSerial();
		// 获取设备验证码
		String validateCode = deviceVideoInfoVo.getValidateCode();
		// 获取设备名称
		String deviceName = deviceVideoInfoVo.getDeviceName();
		// 获取令牌
		String accessTokenStr = accessTokenServiceImpl.getAccessTokenForLoad();
		// 添加设备返回值
		JSONObject jsonObjectAdd = accessTokenServiceImpl.sendAddDevice(accessTokenStr, 
				deviceSerial, validateCode);
		DeviceVideoInfoVo deviceVideoInfoVo = new DeviceVideoInfoVo();
		deviceVideoInfoVo.setDeviceSerial(deviceSerial);
		String flagStr = this.deviceVideoServiceImpl.updateDeviceVideoByDeviceSerial(deviceVideoInfoVo, 
				jsonObjectAdd);
		// 根据修改成功的返回值判断是否进行修改设备名称
		if ("1".equals(flagStr)) {
			this.httpUpdateDeciveName(accessTokenStr, deviceSerial, deviceName);
		}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 远程修改设备名称
	 */
	public void httpUpdateDeciveName(String accessTokenStr, String deviceSerial, String deviceName) {
		// 远程修改
		JSONObject jsonObject = accessTokenServiceImpl.senUpdateDeviceName(accessTokenStr, 
				deviceSerial, deviceName);
		if ("200".equals(jsonObject.optString("code"))) {
			// 本地更新
			this.deviceVideoServiceImpl.updateDeviceNameByDeviceSerial(deviceVideoInfoVo, jsonObject);
		}
		
	}
	/**
	 * 删除设备
	 */
	public String delDeviceVideo() {
		try {
			// 获取设备序列号
			String deviceSerial = deviceVideoInfoVo.getDeviceSerial();
			DeviceVideoInfo deviceVideoInfo = new DeviceVideoInfo();
			deviceVideoInfo.setDeviceSerial(deviceSerial);
			this.deviceVideoServiceImpl.deleteDeviceVideoInfo(deviceVideoInfo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 批量注册
	 */
	public String batchRegDeviceVideo() {
		try {
		// 查询到没有注册的视频设备
		List<DeviceVideoInfoVo> list = this.deviceVideoServiceImpl.
				findDeviceVideoInfoByRegFlag(DeviceVideoServiceImpl.REG_FLAG0);
		if (null != list && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				deviceVideoInfoVo = list.get(i);
				regDeviceVideo();
			}
		}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * AJAX获取各个类别下监控设备数量
	 */
	public void getDeviceTypeCount() {
		String deviceType = deviceVideoInfoVo.getDeviceType();
		long devCount = this.deviceVideoServiceImpl.findDeviceVideoCountByDeviceType(deviceType);
		int count = new Long(devCount).intValue();
		if (count==0) {
			count = 1;
		}else {
			count = count + 1;
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.print(count);
		} catch (IOException e) {
			logger.error("DeviceVideoAction--line(230)--IOException:" + e.getMessage());
		} finally{
			if (outputStream != null) {
				 try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("DeviceVideoAction--line(236)--IOException:" + e.getMessage());
				}
			}
		}
        
	}
	/**
	 * 前端获取zTree的JSON格式
	 */
	public String getDeviceVideoJson() {
		try {
			zJsonList = this.deviceVideoServiceImpl.getZtreeJsonList();
			super.setAjaxBackDataMain(zJsonList);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		return "success";
	}
	/**
	 * 根据视频类别前端获取zTree的JSON格式
	 */
	public String getDeviceVideoJsonByVideoType() {
		try {
			zJsonList = this.deviceVideoServiceImpl.getZtreeJsonListByVideoType(deviceVideoInfoVo);
			super.setAjaxBackDataMain(zJsonList);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		return "success";
	}
	/**
	 * 获取令牌和appkey
	 * @return
	 */
	public String getDeviceVideoTocken() {
		try {
			String accessTokenStr = accessTokenServiceImpl.getAccessTokenForLoad();
			Map<String, String> tokenMap = new HashMap<String, String>();
			tokenMap.put("appkey", YsyConsts.APP_KEY);
			tokenMap.put("accessToken", accessTokenStr);
			super.setAjaxBackDataMain(tokenMap);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		return "success";
	}
	
	/**
	 * List 转 JSON 的公共方法
	 * @param list
	 */
	public void listToJson(List list){
		response.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.println(json);
			out.flush();
		} catch (IOException e) {
			logger.error("DeviceVideoAction--line(300)--IOException:" + e.getMessage());
		} finally{
			out.close();
		}
		
	}
	/**
	 * 跳转到视频监控界面
	 * @return
	 */
	public String gotoVideo() {
		
		return SUCCESS;
		
	}
	/** ----------------------------------------getter/setter ------------------------*/
	public DeviceVideoServiceImpl getDeviceVideoServiceImpl() {
		return deviceVideoServiceImpl;
	}

	public void setDeviceVideoServiceImpl(
			DeviceVideoServiceImpl deviceVideoServiceImpl) {
		this.deviceVideoServiceImpl = deviceVideoServiceImpl;
	}

	public DeviceVideoInfoVo getDeviceVideoInfoVo() {
		return deviceVideoInfoVo;
	}

	public void setDeviceVideoInfoVo(DeviceVideoInfoVo deviceVideoInfoVo) {
		this.deviceVideoInfoVo = deviceVideoInfoVo;
	}

	public AccessTokenServiceImpl getAccessTokenServiceImpl() {
		return accessTokenServiceImpl;
	}

	public void setAccessTokenServiceImpl(
			AccessTokenServiceImpl accessTokenServiceImpl) {
		this.accessTokenServiceImpl = accessTokenServiceImpl;
	}

	public List<ZtreeJsonVO> getzJsonList() {
		return zJsonList;
	}

	public void setzJsonList(List<ZtreeJsonVO> zJsonList) {
		this.zJsonList = zJsonList;
	}
	

}
