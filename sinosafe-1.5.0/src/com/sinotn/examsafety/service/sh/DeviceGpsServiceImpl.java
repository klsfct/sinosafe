package com.sinotn.examsafety.service.sh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.mapping.Array;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sinotn.examsafety.dao.DeviceGpsDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.po.DeviceGpsInfo;
import com.sinotn.examsafety.po.DeviceVideoInfo;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.vo.DeviceGpsInfoVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @Description:   定位设备管理业务类
 * @Author:        李斌
 * @Version:       V1.0.0 
 * @Date:          2017年8月1日 上午10:09:26
 */
public class DeviceGpsServiceImpl {
	private static Logger logger = Logger.getLogger(DeviceGpsServiceImpl.class);
	/**
	 * 注入定位设备管理DAO
	 */
	private DeviceGpsDAO deviceGpsDAO = null;
	
	/** 
	 * 注入考点管理DAO
	 */
	private ExamPlaceDAO examPlaceDAO = null;
	/**
	 * 将远程获取的设备列表添加到本地数据库中
	 * @param jsonObject
	 * @throws ParseException 
	 */
	public void saveDeviceGpsInfo(JSONObject jsonObject) throws ParseException {
		// 查询数据库中数据，如果有执行修改，没有执行保存
		List<DeviceGpsInfo> loadList = this.deviceGpsDAO.findDevicecGpsList();
		// 初始化考点信息，如果没有初始化考点，查询报空指针
		ExamPlace examPlace = new ExamPlace(0L);
		// 返回标识
		String status = jsonObject.optString("status");
		// 成功
		JSONArray entities = jsonObject.getJSONArray("entities");
		// 存放本地数据库中设备名称的集合
		List<String> entityNameList = new ArrayList<String>();
		
		if ("0".equals(status)) {
			if (null != loadList && loadList.size()>0) {
				// 遍历取到设备名称
				for (int i = 0; i < loadList.size(); i++) {
					DeviceGpsInfo gpsInfo = loadList.get(i);
					String entityNameStr = gpsInfo.getEntityName();
					entityNameList.add(entityNameStr);
				}
				// 循环json数据取到设备名称
				for (int i = 0; i < entities.size(); i++) {
					JSONObject entity = entities.getJSONObject(i);
					String entityNameStr = entity.optString("entity_name");
					String entityDescStr = entity.optString("entity_desc");
					// 如果存在则修改
					if (entityNameList.contains(entityNameStr)) {
						DeviceGpsInfo deviceGpsInfo = this.deviceGpsDAO.findDeviceGpsByEntityName(entityNameStr);
						deviceGpsInfo.setEntityDesc(entityDescStr);
						this.deviceGpsDAO.update(deviceGpsInfo);
					}
					// 不在则添加
					else {
						DeviceGpsInfo deviceGpsInfo = new DeviceGpsInfo();
						deviceGpsInfo.setEntityName(entityNameStr);
						deviceGpsInfo.setEntityDesc(entityDescStr);
						String inputTimeStr = entity.optString("create_time");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date;
						date = sdf.parse(inputTimeStr);
						deviceGpsInfo.setInputTime(date);
						deviceGpsInfo.setIsEnabled("1");
						deviceGpsInfo.setExamPlace(examPlace);
						this.deviceGpsDAO.save(deviceGpsInfo);
					}
				}
				
				
			}
			// 为空遍历json全部执行添加
			else {
				for (int i = 0; i < entities.size(); i++) {
					JSONObject entity = entities.getJSONObject(i);
					DeviceGpsInfo deviceGpsInfo = new DeviceGpsInfo();
					String entityNameStr = entity.optString("entity_name");
					String entityDescStr = entity.optString("entity_desc");
					deviceGpsInfo.setEntityName(entityNameStr);
					deviceGpsInfo.setEntityDesc(entityDescStr);
					String inputTimeStr = entity.optString("create_time");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date;
					date = sdf.parse(inputTimeStr);
					deviceGpsInfo.setInputTime(date);
					deviceGpsInfo.setIsEnabled("1");
					deviceGpsInfo.setExamPlace(examPlace);
					this.deviceGpsDAO.save(deviceGpsInfo);
				}
			}
		}
		// 不成功
		else {
			// 提示异常信息：远程查询失败
			//throw new RuntimeException("远程查询失败！");
		}
	}
	/**
	 * 绑定考点同时创建终点地理围栏
	 * @param deviceGpsInfoVo
	 */
	public void updateExamPlace(DeviceGpsInfoVo deviceGpsInfoVo) {
		if (null != deviceGpsInfoVo && null != deviceGpsInfoVo.getGpsId()) {
			DeviceGpsInfo deviceGpsInfo = this.deviceGpsDAO.findObjectById(deviceGpsInfoVo.getGpsId());
			if (null != deviceGpsInfo) {
				ExamPlace examPlace = this.examPlaceDAO.findObjectById(deviceGpsInfoVo.getExamPlaceVo().getExamPlace());
				deviceGpsInfo.setExamPlace(examPlace);
				String latStr = examPlace.getPointLat();
				String lngStr = examPlace.getPointLng();
				deviceGpsInfo.setEndLat(latStr);
				deviceGpsInfo.setEndLng(lngStr);
				
				String entityName = deviceGpsInfoVo.getEntityName();
				String fenceName = examPlace.getExamPlaceName();
				
				// 远程更新设备描述
				JSONObject jsonObj= BaiduMapConsts.updateEntityDesc(entityName, "初始_" + fenceName);
				if ("0".equals(jsonObj.optString("status"))) {
					// 更新设备描述，初始为未出发状态
					deviceGpsInfo.setEntityDesc("初始_" + fenceName);
				}
				// 根据是否已设置终点围栏判断 执行  创建围栏或更新围栏
				if (null != deviceGpsInfo.getEndFenceId() && !"".equals(deviceGpsInfo.getEndFenceId())) {
					JSONObject jsonObject = BaiduMapConsts.updateCircleFence(deviceGpsInfo.getEndFenceId(), fenceName, entityName, lngStr, latStr);
				}else {
					JSONObject jsonObject = BaiduMapConsts.createCircleFence(fenceName, entityName, lngStr, latStr);
					deviceGpsInfo.setEndFenceId(jsonObject.optString("fence_id"));
				}
				this.deviceGpsDAO.update(deviceGpsInfo);
			}
		}
		
	}
	
	/**
	 * 根据定位设备主键查询设备信息
	 * @param deviceGpsInfoVo
	 * @return
	 */
	public DeviceGpsInfoVo findDeviceGpsById(DeviceGpsInfoVo deviceGpsInfoVo) {
		DeviceGpsInfo deviceGpsInfo = this.deviceGpsDAO.findObjectById(deviceGpsInfoVo.getGpsId());
		DeviceGpsInfoVo deviceGpsInfoVo2 = new DeviceGpsInfoVo(deviceGpsInfo);
		return deviceGpsInfoVo2;
	}
	/**
	 * 根据定位设备名称查询设备信息
	 * @param deviceGpsInfoVo
	 * @return
	 */
	public DeviceGpsInfoVo findDeviceGpsByEntityName(
			DeviceGpsInfoVo deviceGpsInfoVo) {
		String entityName = deviceGpsInfoVo.getEntityName();
		int length = entityName.length();
		DeviceGpsInfo deviceGpsInfo = null;
		if (length>=11) {
			deviceGpsInfo = this.deviceGpsDAO.findDeviceGpsByEntityName(entityName);
		}else {
		    deviceGpsInfo = this.deviceGpsDAO.findDeviceGpsByEntityName("0" + entityName);
		}
		DeviceGpsInfoVo deviceGpsInfoVo2 = new DeviceGpsInfoVo(deviceGpsInfo);
		return deviceGpsInfoVo2;
	}
	/**
	 * 保存起点坐标并创建起点地理围栏
	 * @param deviceGpsInfoVo
	 */
	public void saveBeginPoint(DeviceGpsInfoVo deviceGpsInfoVo) {
		if (null != deviceGpsInfoVo) {
			String beginName = deviceGpsInfoVo.getBeginName();
			String lngStr = deviceGpsInfoVo.getBeginLng();
			String latStr = deviceGpsInfoVo.getBeginLat();
			List<DeviceGpsInfo> loadList = this.deviceGpsDAO.findDevicecGpsList();
			for (int i = 0; i < loadList.size(); i++) {
				DeviceGpsInfo deviceGpsInfo = loadList.get(i);
				deviceGpsInfo.setBeginName(beginName);
				deviceGpsInfo.setBeginLng(lngStr);
				deviceGpsInfo.setBeginLat(latStr);
				String entityName = deviceGpsInfo.getEntityName();
				// 根据是否已设置起点围栏判断 执行  创建围栏或更新围栏
				if (null != deviceGpsInfo.getBeginFenceId() && !"".equals(deviceGpsInfo.getBeginFenceId())) {
					JSONObject jsonObject = BaiduMapConsts.updateCircleFence(deviceGpsInfo.getBeginFenceId(), beginName, entityName, lngStr, latStr);
					this.deviceGpsDAO.update(deviceGpsInfo);
				}else {
					JSONObject jsonObject = BaiduMapConsts.createCircleFence(beginName, entityName, lngStr, latStr);
					deviceGpsInfo.setBeginFenceId(jsonObject.optString("fence_id"));
					this.deviceGpsDAO.save(deviceGpsInfo);
				}
				
			}
		}
	}
	/**
	 * 定时任务更新设备描述
	 */
	public void updateEntityDesc() {
		List<DeviceGpsInfo> gpsInfoPoList = this.deviceGpsDAO.findByFenceId();
		if (null != gpsInfoPoList && gpsInfoPoList.size()>0) {
			for (int i = 0; i < gpsInfoPoList.size(); i++) {
				DeviceGpsInfo deviceGpsInfo = gpsInfoPoList.get(i);
				// 设备名称
				String entityName = deviceGpsInfo.getEntityName();
				// 分别取到起点围栏id和终点围栏id
				String benginFenceId = deviceGpsInfo.getBeginFenceId();
				String endFenceId = deviceGpsInfo.getEndFenceId();
				// 分别请求查询监控对象在起点或者终点围栏内或外
				JSONObject beginJsonObj = BaiduMapConsts.queryStatus(entityName, benginFenceId);
				JSONObject endJsonObj = BaiduMapConsts.queryStatus(entityName, endFenceId);
				// 根据终端名称查询实时位置信息
				JSONObject localtionJsonObj = BaiduMapConsts.findEntityByName(entityName);
				try {
					// 实时更新实时位置
					if ("0".equals(localtionJsonObj.optString("status"))) {
						JSONObject latestLocation = JSONObject.fromObject(localtionJsonObj.optJSONArray("entities").get(0)).getJSONObject("latest_location");
						String longitude = latestLocation.optString("longitude");
						String latitude = latestLocation.optString("latitude");
						String lastTimeStr = JSONObject.fromObject(localtionJsonObj.optJSONArray("entities").get(0)).optString("modify_time");
						Date lastTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastTimeStr);
						// 设置最后坐标位置
						deviceGpsInfo.setLatestLng(longitude);
						deviceGpsInfo.setLatestLat(latitude);
						deviceGpsInfo.setLastTime(lastTime);
						// 根据经纬度获取地址
						JSONObject addrJson = BaiduMapConsts.getAddressByPoint(latitude + "," + longitude);
						if ("0".equals(addrJson.optString("status"))) {
							String address = addrJson.optJSONObject("result").optString("formatted_address");
							deviceGpsInfo.setLatestLocation(address);
						}
						
					}
					// 实时更新设备描述
					if ("0".equals(beginJsonObj.optString("status")) && "0".equals(endJsonObj.optString("status"))) {
						// 获得起点围栏状态
						String beginStatus = JSONObject.fromObject(beginJsonObj.optJSONArray("monitored_statuses").get(0))
								.optString("monitored_status");
						// 获得终点围栏状态
						String endStatus = JSONObject.fromObject(endJsonObj.optJSONArray("monitored_statuses").get(0))
								.optString("monitored_status");
						//如果起点围栏在内，设置为未出发
						if ("in".equals(beginStatus)) {
							String entityDesc = "未出发_" + deviceGpsInfo.getExamPlace().getExamPlaceName();
							JSONObject jsonObject = BaiduMapConsts.updateEntityDesc(entityName, entityDesc);
							if ("0".equals(jsonObject.optString("status"))) {
								deviceGpsInfo.setEntityDesc(entityDesc);
								deviceGpsInfo.setReachFlag("未出发");
							}
							
						}
						//如果起点围栏在内，设置为已到达
						if ("in".equals(endStatus)) {
							String entityDesc = "已到达_" + deviceGpsInfo.getExamPlace().getExamPlaceName();
							JSONObject jsonObject = BaiduMapConsts.updateEntityDesc(entityName, entityDesc);
							if ("0".equals(jsonObject.optString("status"))) {
								deviceGpsInfo.setEntityDesc(entityDesc);
								deviceGpsInfo.setReachFlag("已到达");
							}
							
						}
						//如果都不在，设置为已出发
						if ("out".equals(endStatus) && "out".equals(beginStatus)) {
							String entityDesc = "已出发_" + deviceGpsInfo.getExamPlace().getExamPlaceName();
							JSONObject jsonObject = BaiduMapConsts.updateEntityDesc(entityName, entityDesc);
							if ("0".equals(jsonObject.optString("status"))) {
								deviceGpsInfo.setEntityDesc(entityDesc);
								deviceGpsInfo.setReachFlag("已出发");
							}
							
						}
						//如果都不在，设置为未知状态
						if ("unknown".equals(endStatus) || "unknown".equals(beginStatus)) {
							String entityDesc = "未知_" + deviceGpsInfo.getExamPlace().getExamPlaceName();
							JSONObject jsonObject = BaiduMapConsts.updateEntityDesc(entityName, entityDesc);
							if ("0".equals(jsonObject.optString("status"))) {
								deviceGpsInfo.setEntityDesc(entityDesc);
								deviceGpsInfo.setReachFlag("未知");
							}
							
						}
						
						
						this.deviceGpsDAO.update(deviceGpsInfo);
					}
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			}
		}
	}
	/**
	 * 根据考点代码查询
	 * @param examPlace
	 * @return
	 */
	public List<DeviceGpsInfo> findDeviecGpsByExamType(Long examPlace) {
		
		return this.deviceGpsDAO.findDeviecGpsByExamType(examPlace);
	}
	/**
	 * 同步联系人
	 */
	public void syncLinkMan() {
		List<DeviceGpsInfo> listPO = this.deviceGpsDAO.findDevicecGpsList();
		if (null != listPO && listPO.size()>0) {
			for (DeviceGpsInfo deviceGpsInfo : listPO) {
				boolean condition = deviceGpsInfo.getLinkManName() != null && deviceGpsInfo.getLinkManName() != ""
						&& deviceGpsInfo.getLinkManPhone() != null && deviceGpsInfo.getLinkManPhone() != "";
				if (condition) {
					BaiduMapConsts.updateEntityLink(deviceGpsInfo.getEntityName(), 
							deviceGpsInfo.getLinkManName(), deviceGpsInfo.getLinkManPhone());
				}
			}
		}
	}
	/**
	 * 更新联系人
	 */
	public void updateLinkMan(DeviceGpsInfoVo deviceGpsInfoVo) {
		if (null != deviceGpsInfoVo && null != deviceGpsInfoVo.getGpsId()) {
			DeviceGpsInfo deviceGpsInfo = this.getDeviceGpsDAO().findObjectById(
					deviceGpsInfoVo.getGpsId());
			if (null != deviceGpsInfo) {
				deviceGpsInfo.setLinkManName(deviceGpsInfoVo.getLinkManName());
				deviceGpsInfo.setLinkManPhone(deviceGpsInfoVo.getLinkManPhone());
				this.deviceGpsDAO.update(deviceGpsInfo);
				BaiduMapConsts.updateEntityLink(deviceGpsInfo.getEntityName(), 
						deviceGpsInfo.getLinkManName(), deviceGpsInfo.getLinkManPhone());
			}
		}
		
	}
	
	/** ----------------------------------------getter/setter ------------------------*/
	public DeviceGpsDAO getDeviceGpsDAO() {
		return deviceGpsDAO;
	}

	public void setDeviceGpsDAO(DeviceGpsDAO deviceGpsDAO) {
		this.deviceGpsDAO = deviceGpsDAO;
	}
	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}
	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}
	
	
	
	
	
	

	

}
