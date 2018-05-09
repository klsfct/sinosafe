package com.sinotn.examsafety.service.sh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.dao.DeviceVideoInfoDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.po.DeviceVideoInfo;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.vo.DeviceGpsInfoVo;
import com.sinotn.examsafety.vo.DeviceVideoInfoVo;
import com.sinotn.examsafety.vo.ExamPlaceVo;
import com.sinotn.examsafety.vo.ZtreeJsonVO;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @ClassName:     DeviceVideoServiceImpl.java
 * @Description:   视频设备管理业务类
 * 
 * @author:    李斌
 * @version:   V1.0  
 * @Date:      2017年7月12日 下午1:33:12
 */
public class DeviceVideoServiceImpl {
	
	/**
	 * 初始状态码
	 */
	public static final String REG_FLAG0 = "0"; 
	/**
	 * 注册成功状态码
	 */
	public static final String REG_FLAG1 = "1";
	/**
	 * 注册失败状态码
	 */
	public static final String REG_FLAG2 = "2";
	
	
	/**
	 * 注入视频设备DAO
	 */
	private DeviceVideoInfoDAO deviceVideoInfoDAO = null;
	
	/**
	 * 注入考点信息DAO
	 */
	private ExamPlaceDAO examPlaceDAO = null;
	
	/**
	 * 根据市区ID查询考点信息集合
	 * @param examArea	（市区ID）
	 * @param i			（启用标志）
	 * @return List<ExamPlaceVo>	（地区信息集合）
	 */
	public List<ExamPlaceVo> findExamPlaceList(String examArea, int i) {
		List<ExamPlace> list = examPlaceDAO.findExamPlaceList(examArea, i);
		List<ExamPlaceVo> listVo = new ArrayList<ExamPlaceVo>();
		if (list != null && list.size()>0) {
			for(ExamPlace po:list){
				listVo.add(new ExamPlaceVo(po));
			}
		}
		return listVo;
	}
	/**
	 * 保存视频设备信息
	 * @param deviceVideoInfoVo
	 */
	public void saveDeviceVideo(DeviceVideoInfoVo deviceVideoInfoVo) {
		DeviceVideoInfo deviceVideoInfo = new DeviceVideoInfo();
		deviceVideoInfo.setDeviceName(deviceVideoInfoVo.getDeviceName());
		deviceVideoInfo.setDeviceNumber(deviceVideoInfoVo.getDeviceType()+deviceVideoInfoVo.getDeviceNumber());
		deviceVideoInfo.setDeviceSerial(deviceVideoInfoVo.getDeviceSerial());
		deviceVideoInfo.setDeviceType(deviceVideoInfoVo.getDeviceType());
		deviceVideoInfo.setInputTime(deviceVideoInfoVo.getInputTime());
		deviceVideoInfo.setRegFlag(DeviceVideoServiceImpl.REG_FLAG0);
		deviceVideoInfo.setInputTime(deviceVideoInfoVo.getInputTime());
		deviceVideoInfo.setValidateCode(deviceVideoInfoVo.getValidateCode());
		ExamPlace examPlace = new ExamPlace();
		examPlace.setExamPlace(deviceVideoInfoVo.getExamPlaceVo().getExamPlace());
		deviceVideoInfo.setExamPlace(examPlace);
		this.getDeviceVideoInfoDAO().save(deviceVideoInfo);
	}
	/**
	 * 根据视频设备序列号查询设备信息
	 * @return	DeviceVideoInfoVo
	 */
	public DeviceVideoInfoVo findDeviceVideoBySerial(
			DeviceVideoInfoVo deviceVideoInfoVo) {
		if (null != deviceVideoInfoVo && null != deviceVideoInfoVo.getDeviceSerial()) {
			DeviceVideoInfo deviceVideoInfo = this.getDeviceVideoInfoDAO().findObjectById(
					deviceVideoInfoVo.getDeviceSerial());
			
			if (null != deviceVideoInfo) {
				DeviceVideoInfoVo deviceVideoInfoVo2 = new DeviceVideoInfoVo(deviceVideoInfo);
				String numberStr = deviceVideoInfoVo2.getDeviceNumber().substring(1);
				deviceVideoInfoVo2.setDeviceNumber(numberStr);
				return deviceVideoInfoVo2;
			}
		}
		return null;
	}
	/**
	 * 更新视频设备信息
	 * @param result （设备名称是否修改标志）
	 */
	public String updateDeviceVideo(DeviceVideoInfoVo deviceVideoInfoVo) {
		String result = "";
		if (null != deviceVideoInfoVo && null != deviceVideoInfoVo.getDeviceSerial()) {
			DeviceVideoInfo deviceVideoInfo = this.getDeviceVideoInfoDAO().findObjectById(
					deviceVideoInfoVo.getDeviceSerial());
			
			if (null != deviceVideoInfo) {
				// 判断设备名称是否修改，如果是返回1代表修改，否则返回0
				if (!deviceVideoInfo.getDeviceName().equals(deviceVideoInfoVo.getDeviceName())) {
					result = "1";
				}else {
					result = "0";
				}
				deviceVideoInfo.setDeviceName(deviceVideoInfoVo.getDeviceName());
				deviceVideoInfo.setDeviceNumber(deviceVideoInfoVo.getDeviceType() + deviceVideoInfoVo.getDeviceNumber());
				deviceVideoInfo.setDeviceType(deviceVideoInfoVo.getDeviceType());
				deviceVideoInfo.setValidateCode(deviceVideoInfoVo.getValidateCode());
				deviceVideoInfo.setLinkManName(deviceVideoInfoVo.getLinkManName());
				deviceVideoInfo.setLinkManPhone(deviceVideoInfoVo.getLinkManPhone());
				ExamPlace examPlace = new ExamPlace();
				examPlace.setExamPlace(deviceVideoInfoVo.getExamPlaceVo().getExamPlace());
				deviceVideoInfo.setExamPlace(examPlace);
				this.getDeviceVideoInfoDAO().update(deviceVideoInfo);
			}
		}
		return result;
	}
	/**
	 * 修改注册状态
	 * @param deviceVideoInfoVo
	 * @param jsonObject		
	 * @return result （注册成功标志）
	 */
	public String updateDeviceVideoByDeviceSerial(DeviceVideoInfoVo deviceVideoInfoVo,
			JSONObject jsonObject) {
		String result = "";
		if (null != deviceVideoInfoVo && null != deviceVideoInfoVo.getDeviceSerial()) {
			DeviceVideoInfo deviceVideoInfo = this.getDeviceVideoInfoDAO().findObjectById(
					deviceVideoInfoVo.getDeviceSerial());
			if (null != deviceVideoInfo) {
				// 设置注册信息
				String code = jsonObject.getString("code");
				String msg = jsonObject.getString("msg");
				deviceVideoInfo.setBackCode(code + ";");
				deviceVideoInfo.setBackMsg(msg + ";");
				// 如果成功，或者提示设备已被自己添加则都认为成功
				if ("200".equals(code) || "20017".equals(code)) {
					deviceVideoInfo.setRegFlag(REG_FLAG1);
					deviceVideoInfo.setBackCode("200" + ";");
					deviceVideoInfo.setBackMsg("操作成功!" + ";");
					deviceVideoInfo.setRegTime(DateUtils.getCurrentDate());
					result = "1";
				}
				else {
					deviceVideoInfo.setRegFlag(REG_FLAG2);
					result = "0";
				}
				
				this.getDeviceVideoInfoDAO().update(deviceVideoInfo);
				
			}
		}
		return result;
	}
	/**
	 * 修改设备名称
	 * @param deviceVideoInfoVo
	 * @param jsonObject
	 */
	public void updateDeviceNameByDeviceSerial(
			DeviceVideoInfoVo deviceVideoInfoVo, JSONObject jsonObject) {
		if (null != deviceVideoInfoVo && null != deviceVideoInfoVo.getDeviceSerial()) {
			DeviceVideoInfo deviceVideoInfo = this.getDeviceVideoInfoDAO().findObjectById(
					deviceVideoInfoVo.getDeviceSerial());
			if (null != deviceVideoInfo) {
				// 获得之前的code和msg
				String oldCode = deviceVideoInfo.getBackCode().split(";")[0] + ";";
				String oldMsg = deviceVideoInfo.getBackMsg().split(";")[0] + ";";
				// 设置修改设备名称返回信息
				String code = oldCode + jsonObject.getString("code");
				String msg = oldMsg + jsonObject.getString("msg");
				deviceVideoInfo.setBackCode(code);
				deviceVideoInfo.setBackMsg(msg);
				this.getDeviceVideoInfoDAO().update(deviceVideoInfo);
			}
		}
		
	}
	/**
	 * 删除设备
	 * @param deviceVideoInfo
	 */
	public void deleteDeviceVideoInfo(DeviceVideoInfo deviceVideoInfo) {
		if (null != deviceVideoInfo && null != deviceVideoInfo.getDeviceSerial()) {
			this.deviceVideoInfoDAO.delete(deviceVideoInfo);
		}
		
	}
	/**
	 * 批量注册前查询未注册的设备信息集合
	 * @param regFlag	（未注册标注）
	 * @return	List<DeviceVideoInfoVo>
	 */
	public List<DeviceVideoInfoVo> findDeviceVideoInfoByRegFlag(String regFlag) {
		List<DeviceVideoInfoVo> retnList = new ArrayList<DeviceVideoInfoVo>();
		List<DeviceVideoInfo> list = this.deviceVideoInfoDAO.findByProperty("regFlag", regFlag);
		for(DeviceVideoInfo po:list){
			retnList.add(new DeviceVideoInfoVo(po));
		}
		return retnList;
		
	}
	/**
	 * 根据注册状态查询符合条件的数量
	 * @param regFlag	（注册状态）
	 * @return long (符合条件的数量)
	 */
	public long findDeviceVideoCountByRegFlag(String regFlag) {
		return this.deviceVideoInfoDAO.findDeviceVideoCountByRegFlag(regFlag);
		
	}
	/**
	 * 根据设备类别查询符合条件的数量
	 * @param deviceType(设备类别)
	 * @return long (符合条件的数量)
	 */
	public long findDeviceVideoCountByDeviceType(String deviceType) {
		return this.deviceVideoInfoDAO.findDeviceVideoCountByDeviceType(deviceType);
	}
	/**
	 * 获取全部视频列表返回前台
	 * @returnList<ZtreeJsonVO>
	 */
	public List<ZtreeJsonVO> getZtreeJsonList() {
		// 获取设备信息列表
		List<DeviceVideoInfo> listPO = findDeviceVideoInfo();
		if (null != listPO && listPO.size()>0) {
			List<ZtreeJsonVO> ztreeJsonList = getZtreeJsonVOList(listPO);
			 // 初始化父节点
			ZtreeJsonVO zVoA = new ZtreeJsonVO("1", "0", "进场监控", true);
			ZtreeJsonVO zVoB = new ZtreeJsonVO("2", "0", "考场监控", true);
			ZtreeJsonVO zVoC = new ZtreeJsonVO("3", "0", "保密室监控", true);
			ztreeJsonList.add(zVoA);
			ztreeJsonList.add(zVoB);
			ztreeJsonList.add(zVoC);
			return ztreeJsonList;
		}
		return null;
		
		
	}
	/**
	 * 根据视频类别查询视频集合返回前台
	 * @param deviceVideoInfoVo
	 * @return
	 */
	public List<ZtreeJsonVO> getZtreeJsonListByVideoType(
			DeviceVideoInfoVo deviceVideoInfoVo) {
		if (null != deviceVideoInfoVo && !"".equals(deviceVideoInfoVo.getDeviceType())) {
			// 获取设备信息列表
			String deviceTypeStr = deviceVideoInfoVo.getDeviceType();
			List<DeviceVideoInfo> listPO = findDeviceVideoInfoByVideoType(deviceTypeStr);
			if (null != listPO && listPO.size()>0) {
				// 初始化子节点
				List<ZtreeJsonVO> ztreeJsonList = getZtreeJsonVOList(listPO);
				// 初始化父节点
				ZtreeJsonVO ztreeJsonVO = null;
				if ("A".equals(deviceTypeStr)) {
					ztreeJsonVO = new ZtreeJsonVO("1", "0", "进场监控", true);
				}
				if ("B".equals(deviceTypeStr)) {
					ztreeJsonVO = new ZtreeJsonVO("2", "0", "保密室监控", true);		
				}
				if ("C".equals(deviceTypeStr)) {
					ztreeJsonVO= new ZtreeJsonVO("3", "0", "车载监控", true);
				}
				ztreeJsonList.add(ztreeJsonVO);
				
				return ztreeJsonList;
			}
		}
		return null;
		
	}
	/**
	 * 组织视频列表传入前台格式
	 * @return
	 */
	public List<ZtreeJsonVO> getZtreeJsonVOList(List<DeviceVideoInfo> listPO) {
		List<ZtreeJsonVO> ztreeJsonList = new ArrayList<ZtreeJsonVO>();
		List<DeviceVideoInfoVo> listVO = new ArrayList<DeviceVideoInfoVo>();
		// po转vo
		if (null != listPO && listPO.size()>0) {
			for (DeviceVideoInfo po : listPO) {
				listVO.add(new DeviceVideoInfoVo(po));
			}
		}
		// 组织子节点数据
		if (null != listVO && listVO.size()>0) {
			for (int i = 0; i < listVO.size(); i++) {
				ZtreeJsonVO zJsonVO = null;
				DeviceVideoInfoVo deviceVideoInfoVo = listVO.get(i);
				String deviceName = deviceVideoInfoVo.getDeviceName();
				String deviceType = deviceVideoInfoVo.getDeviceType();
				String deviceSerial = deviceVideoInfoVo.getDeviceSerial();
				String devStatus = deviceVideoInfoVo.getDevStatus();
				// 根据设备类别设置pid
				if ("A".equals(deviceType)) {
					zJsonVO = new ZtreeJsonVO();
					zJsonVO.setpId("1");
				}
				if ("B".equals(deviceType)) {
					zJsonVO = new ZtreeJsonVO();
					zJsonVO.setpId("2");
				}
				if ("C".equals(deviceType)) {
					zJsonVO = new ZtreeJsonVO();
					zJsonVO.setpId("3");
				}
				zJsonVO.setIsOnline(devStatus);
				// 根据远程获取的在线状态  设置icon图标
				if ("1".equals(devStatus)) {
					zJsonVO.setIcon(ZtreeJsonVO.ONLINE_ICON_SRC);
					
				}
				if ("0".equals(devStatus)) {
					zJsonVO.setIcon(ZtreeJsonVO.NOT_ONLINE_ICON_SRC);
				}
				zJsonVO.setName(deviceName);
				zJsonVO.setId(deviceSerial);
				zJsonVO.setLinkManName(deviceVideoInfoVo.getLinkManName());
				zJsonVO.setLinkManPhone(deviceVideoInfoVo.getLinkManPhone());
				ztreeJsonList.add(zJsonVO);
			}
		}
		return ztreeJsonList;
	}
	/**
	 * 根据视频类别查询视频集合
	 * @param deviceType
	 * @return
	 */
	private List<DeviceVideoInfo> findDeviceVideoInfoByVideoType(
			String deviceType) {
		return this.deviceVideoInfoDAO.findDeviceVideoInfoByVideoType(DeviceVideoServiceImpl.REG_FLAG1, deviceType);
	}
	/**
	 * 查询注册成功视频设备信息
	 * @return List<DeviceVideoInfo>
	 */
	public List<DeviceVideoInfo> findDeviceVideoInfo() {
		return this.deviceVideoInfoDAO.findDeviceVideoInfo(DeviceVideoServiceImpl.REG_FLAG1);
	}
	/**
	 * 根据考点获取视频对象
	 * @param examPlace
	 * @return
	 */
	public DeviceVideoInfoVo findDeviceVideoByExamPlace(
			Long examPlace) {
		String [] propertyNames = {"examPlace.examPlace", "deviceType"};
		Object [] values = {examPlace, "C"};
		//List<DeviceVideoInfo> listPO = this.deviceVideoInfoDAO.findByProperty("examPlace.examPlace", examPlace);
		List<DeviceVideoInfo> listPO = this.deviceVideoInfoDAO.findByPropertyArry(propertyNames, values);
		if (null != listPO && listPO.size()>0) {
			for (DeviceVideoInfo deviceVideoInfo : listPO) {
				DeviceVideoInfoVo deviceVideoInfoVo = new DeviceVideoInfoVo(deviceVideoInfo);
				return deviceVideoInfoVo;
			}
		}
		return null;
	}
	
	/** ----------------------------------------getter/setter ------------------------*/
	public DeviceVideoInfoDAO getDeviceVideoInfoDAO() {
		return deviceVideoInfoDAO;
	}

	public void setDeviceVideoInfoDAO(DeviceVideoInfoDAO deviceVideoInfoDAO) {
		this.deviceVideoInfoDAO = deviceVideoInfoDAO;
	}

	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}
	
	
	
	
	
	
	
	
	
	
	
}
