package com.sinotn.examsafety.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.sinotn.examsafety.dao.DeviceFaceInfoDAO;
import com.sinotn.examsafety.dao.DeviceHandInfoDAO;
import com.sinotn.examsafety.vo.BaseCodeVo;

public class DeviceInfoServiceImpl {
	/**
	 * 人脸识别设备DAO注入
	 */
	private DeviceFaceInfoDAO deviceFaceInfoDAO = null;
	/**
	 * 手持机设备DAO注入
	 */
	private DeviceHandInfoDAO deviceHandInfoDAO = null;

	public List<String[]> groupDeviceFaceByArea(String areaId, int level,
			int groupField) {
		List<String[]> resultList = new ArrayList<String[]>();
		if (level == 1) {
			resultList = this.groupDeviceFaceByProvinceArea(areaId + "%");
		} else if (level == 2) {
			if (groupField == 1) {
				resultList = this.groupDeviceFaceByExamArea(areaId);
			} else if (groupField == 2) {
				resultList = this.groupDeviceFaceByExamPlace(areaId+"%");
			}
		} else if (level == 3) {
			resultList = this.groupDeviceFaceByExamPlace(areaId);
		}
		return resultList;
	}

	public List<String[]> groupDeviceHandByArea(String areaId, int level,
			int groupField) {
		List<String[]> resultList = new ArrayList<String[]>();
		if (level == 1) {
			resultList = this.groupDeviceHandByProvinceArea(areaId + "%");
		} else if (level == 2) {
			if (groupField == 1) {
				resultList = this.groupDeviceHandByExamArea(areaId);
			} else if (groupField == 2) {
				resultList = this.groupDeviceHandByExamPlace(areaId+"%");
			}
		} else if (level == 3) {
			resultList = this.groupDeviceHandByExamPlace(areaId);
		}
		return resultList;
	}

	private List<String[]> groupDeviceFaceByProvinceArea(String areaId) {
		return this.convertStruct(this.getDeviceFaceInfoDAO()
				.groupDeviceFaceByProvinceArea(areaId), 2);
	}

	private List<String[]> groupDeviceFaceByExamArea(String areaId) {
		return this.convertStruct(this.getDeviceFaceInfoDAO()
				.groupDeviceFaceByExamArea(areaId), 2);
	}

	private List<String[]> groupDeviceFaceByExamPlace(String areaId) {
		return this.convertStruct(this.getDeviceFaceInfoDAO()
				.groupDeviceFaceByExamPlace(areaId), 2);
	}

	private List<String[]> groupDeviceHandByProvinceArea(String areaId) {
		return this.convertStruct(this.getDeviceHandInfoDAO()
				.groupDeviceHandByProvinceArea(areaId), 2);
	}

	private List<String[]> groupDeviceHandByExamArea(String areaId) {
		return this.convertStruct(this.getDeviceHandInfoDAO()
				.groupDeviceHandByExamArea(areaId), 2);
	}

	private List<String[]> groupDeviceHandByExamPlace(String areaId) {
		return this.convertStruct(this.getDeviceHandInfoDAO()
				.groupDeviceHandByExamPlace(areaId), 2);
	}

	/**
	 * 
	 * @param list
	 * @param maxPos
	 * @return
	 */
	private List<String[]> convertStruct(List<?> list, int maxPos) {
		// 基础数据，包括合计数据
		List<String[]> baseDataList = new ArrayList<String[]>();
		// 基础数据长度
		int baseDatalength = 0;
		if (!list.isEmpty()) {
			baseDatalength = ((Object[]) list.get(0)).length;
		}
		// 循环数据库查询结果，构造基础数据列表
		Object[] value = null;
		String[] baseData = null;
		// 声明合计项
		int[] total = new int[baseDatalength];
		// 声明每行数据总数的最大项
		int max = 0;
		for (Object object : list) {
			value = (Object[]) object;
			baseData = new String[baseDatalength];
			for (int i = 0; i < baseDatalength; i++) {
				if (i < maxPos) {
					baseData[i] = (String) value[i];
				} else {
					baseData[i] = ((Integer) value[i]).toString();
				}
				// 类型数据计算合计项
				if (i >= maxPos) {
					total[i] = total[i] + (Integer) value[i];
				}
				// 关注所有数据总人数最大数，为了计算统计条宽度
				if (i == maxPos) {// 获取关注的最大值
					int tempMax = ((Integer) value[i]).intValue();
					if (tempMax > max) {
						max = tempMax;
					}
				}
			}
			baseDataList.add(baseData);
		}
		// 返回的统计结果，view层直接显示
		List<String[]> resultList = new ArrayList<String[]>();
		String[] result = null;
		DecimalFormat formate = new DecimalFormat("##.#%");
		for (String[] temp : baseDataList) {
			int j = -1;
			result = new String[baseDatalength * 2];
			for (int i = 0; i < temp.length; i++) {
				if (i < maxPos) {// 第一项是分组统计列名
					result[++j] = temp[i];
				} else if (i == maxPos) {// 小计总数项
					result[++j] = temp[i];
					if (total[i] == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(Double
								.parseDouble(temp[i]) / total[i]);
					}

				} else {
					result[++j] = temp[i];
					if (Integer.parseInt(temp[maxPos]) == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(Double
								.parseDouble(temp[i])
								/ Double.parseDouble(temp[maxPos]));
					}

				}
			}
			if (max == 0) {
				result[j + 1] = formate.format(0);
			} else {
				result[j + 1] = formate.format(Double.parseDouble(temp[maxPos])
						/ max);
			}
			resultList.add(result);
		}
		// 插入合计项
		if (resultList.size() > 0) {
			result = new String[baseDatalength * 2];
			int j = -1;
			for (int i = 0; i < total.length; i++) {
				if (i < maxPos) {// 第一项是分组统计列名
					result[++j] = "合计";
				} else if (i == maxPos) {// 小计总数项
					result[++j] = Integer.toString(total[i]);
					if (total[i] == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(total[i] / total[i]);
					}

				} else {
					result[++j] = Integer.toString(total[i]);
					if (total[maxPos] == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(total[i]
								/ (double) total[maxPos]);
					}
				}
			}
			if (max == 0) {
				result[j + 1] = formate.format(0);
			} else {
				result[j + 1] = formate.format(1);
			}
			resultList.add(result);
		}
		return resultList;
	}

	public DeviceFaceInfoDAO getDeviceFaceInfoDAO() {
		return deviceFaceInfoDAO;
	}

	public void setDeviceFaceInfoDAO(DeviceFaceInfoDAO deviceFaceInfoDAO) {
		this.deviceFaceInfoDAO = deviceFaceInfoDAO;
	}

	public DeviceHandInfoDAO getDeviceHandInfoDAO() {
		return deviceHandInfoDAO;
	}

	public void setDeviceHandInfoDAO(DeviceHandInfoDAO deviceHandInfoDAO) {
		this.deviceHandInfoDAO = deviceHandInfoDAO;
	}
}
