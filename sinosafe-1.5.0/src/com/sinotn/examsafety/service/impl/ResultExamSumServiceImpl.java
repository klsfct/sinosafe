package com.sinotn.examsafety.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.sinotn.common.utils.StringUtils;
import com.sinotn.examsafety.dao.BaseCodeDAO;
import com.sinotn.examsafety.dao.DeviceFaceInfoDAO;
import com.sinotn.examsafety.dao.ExamineeDAO;
import com.sinotn.examsafety.dao.ResultCard1DAO;
import com.sinotn.examsafety.dao.ResultCard2DAO;
import com.sinotn.examsafety.dao.ResultCard3DAO;
import com.sinotn.examsafety.dao.ResultExamSumDAO;
import com.sinotn.examsafety.dao.ResultExaminee1DAO;
import com.sinotn.examsafety.po.BaseCode;
import com.sinotn.examsafety.po.DeviceFaceInfo;
import com.sinotn.examsafety.po.ResultCard1;
import com.sinotn.examsafety.po.ResultCard2;
import com.sinotn.examsafety.po.ResultCard3;
import com.sinotn.examsafety.po.ResultExamSum;
import com.sinotn.examsafety.vo.BaseCodeVo;
import com.sinotn.examsafety.vo.CountByExamPlaceVo;
import com.sinotn.examsafety.vo.GxCountByExamPlace;
import com.sinotn.examsafety.vo.MonitorMapItemVo;
import com.sinotn.examsafety.vo.MonitorMapVo;
import com.sinotn.examsafety.vo.ShMonitorMapItemVo;
import com.sinotn.examsafety.vo.ShMonitorMapVo;

public class ResultExamSumServiceImpl {

	private ResultExamSumDAO resultExamSumDAO = null;
	private ResultExaminee1DAO resultExaminee1DAO = null;
	private ResultCard1DAO resultCard1DAO = null;
	private ResultCard2DAO resultCard2DAO = null;
	private ResultCard3DAO resultCard3DAO = null;

	private ExamineeDAO examineeDAO = null;

	private DeviceFaceInfoDAO deviceFaceInfoDAO = null;
	/**
	 * 2017.12.12 新增 作用：根据登录地区ID查询登录地区名称，为地图设置name
	 * 
	 * @author Libin
	 */
	private BaseCodeDAO baseCodeDAO = null;

	public List<String[]> groupPassByArea(String areaId, String subject,
			int level, int groupField) {
		List<?> list = null;
		if (level == 1) {
			list = this.getResultExamSumDAO().groupPassByProvinceArea(
					areaId + "%", subject);
		} else if (level == 2) {
			if (groupField == 1) {
				list = this.getResultExamSumDAO().groupPassByExamArea(areaId,
						subject);
			} else if (groupField == 2) {
				list = this.getResultExamSumDAO().groupPassByExamPlace(
						areaId + "%", subject);
			}
		} else if (level == 3) {
			list = this.getResultExamSumDAO().groupPassByExamPlace(areaId,
					subject);
		}
		return this.convertStruct(list, 2, 3, 2);
	}

	public List<String[]> groupCardByArea(String areaId, String subject,
			int level, int groupField) {
		List<?> list = null;
		if (level == 1) {
			list = this.getResultExamSumDAO().groupCardByProvinceArea(
					areaId + "%", subject);
		} else if (level == 2) {
			if (groupField == 1) {
				list = this.getResultExamSumDAO().groupCardByExamArea(areaId,
						subject);
			} else if (groupField == 2) {
				list = this.getResultExamSumDAO().groupCardByExamPlace(
						areaId + "%", subject);
			}
		} else if (level == 3) {
			list = this.getResultExamSumDAO().groupCardByExamPlace(areaId,
					subject);
		}
		return this.convertStruct(list, 2, 3, 2);
	}

	public List<String[]> groupFaceByArea(String areaId, String subject,
			int level, int groupField, long examPlaceId) {
		List<?> list = null;
		if (level == 1) {
			list = this.getResultExamSumDAO().groupFaceByProvinceArea(
					areaId + "%", subject);
		} else if (level == 2) {
			if (groupField == 1) {
				list = this.getResultExamSumDAO().groupFaceByExamArea(areaId,
						subject);
			} else if (groupField == 2) {
				list = this.getResultExamSumDAO().groupFaceByExamPlace(
						areaId + "%", subject);
			}
		} else if (level == 3) {
			list = this.getResultExamSumDAO().groupFaceByExamPlace(areaId,
					subject);
		}else {
			list = this.getResultExamSumDAO().groupFaceByExamPlace(examPlaceId, subject);
		}
		return this.convertStruct(list, 2, 4, 3);
	}

	public List<String[]> groupCardByExamRoom(long examPlaceId, String subject) {
		List<String[]> resultList = new ArrayList<String[]>();
		List<?> list = this.getResultCard1DAO().groupCardByExamRoom(
				examPlaceId, subject);
		for (Object temp : list) {
			Object[] value = (Object[]) temp;
			String[] result = new String[4];
			result[0] = (String) value[0];
			result[1] = (String) value[1];
			result[2] = Integer.toString((Integer) value[2]);
			result[3] = Integer.toString((Integer) value[3]);
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * 处理问题卡
	 * 
	 * @param subject
	 * @param examPlaceId
	 * @param examRoom
	 */
	public synchronized void modifyCardByRoom(String subject, long examPlaceId,
			int examRoom) {
		long standSum = this.getExamineeDAO().findStandRoomExaminees(
				examPlaceId, examRoom);
		long currentSum = 0;
		ResultExamSum resultExamSum = this.getResultExamSumDAO()
				.findResultExamSum(subject, examPlaceId);
		if (subject.equals("1")) {
			ResultCard1 resultCard = this.getResultCard1DAO().getResultCard(
					examPlaceId, examRoom);
			currentSum = resultCard.getExamCard();
			if (currentSum < standSum) {
				resultCard.setExamCard(resultCard.getExamCard() + 1);
				this.getResultCard1DAO().update(resultCard);
				resultExamSum.setCardSum(resultExamSum.getCardSum() + 1);
				this.getResultExamSumDAO().update(resultExamSum);
			} else {
				throw new RuntimeException("考场题卡数已满，修改错误！");
			}

		} else if (subject.equals("2")) {
			ResultCard2 resultCard = this.getResultCard2DAO().getResultCard(
					examPlaceId, examRoom);
			currentSum = resultCard.getExamCard();
			if (currentSum < standSum) {
				resultCard.setExamCard(resultCard.getExamCard() + 1);
				this.getResultCard2DAO().update(resultCard);
				resultExamSum.setCardSum(resultExamSum.getCardSum() + 1);
				this.getResultExamSumDAO().update(resultExamSum);
			} else {
				throw new RuntimeException("考场题卡数已满，修改错误！");
			}
		} else if (subject.equals("3")) {
			ResultCard3 resultCard = this.getResultCard3DAO().getResultCard(
					examPlaceId, examRoom);
			currentSum = resultCard.getExamCard();
			if (currentSum < standSum) {
				resultCard.setExamCard(resultCard.getExamCard() + 1);
				this.getResultCard3DAO().update(resultCard);
				resultExamSum.setCardSum(resultExamSum.getCardSum() + 1);
				this.getResultExamSumDAO().update(resultExamSum);
			} else {
				throw new RuntimeException("考场题卡数已满，修改错误！");
			}
		}
	}

	/**
	 * 全国监控界面
	 * 
	 * @param subject
	 * @return List<String[]>
	 *         String[]{0:行政区名称,1:总数,2:安检人数,3:参考率,4:人脸识别通过人数,5:通过率}
	 */
	public MonitorMapVo monitorByArea(String subject) {
		MonitorMapVo monitorMapVo = new MonitorMapVo();
		// 地图数据
		DecimalFormat formate = new DecimalFormat("##.#%");
		List<?> list = this.getResultExamSumDAO().monitorByArea(subject);
		List<MonitorMapItemVo> mapItemList = new ArrayList<MonitorMapItemVo>();
		MonitorMapItemVo itemVo = null;
		int sum = 0;// 累加总数
		int passSum = 0;// 累加安检人数
		int photoYSum = 0;// 累加人脸通过人数
		for (Object object : list) {
			Object[] temp = (Object[]) object;
			itemVo = new MonitorMapItemVo();
			itemVo.setName(BaseCodeUtils.findBaseCodeById((String) temp[0])
					.getAliax1()); // 获取省份简称
			itemVo.setValue(((BigDecimal) temp[3]).toString());// 参考率
			itemVo.setD1((String) temp[0]);// 地区代码
			itemVo.setD2(((Integer) temp[2]).toString());// 安检人数
			itemVo.setD3(((BigDecimal) temp[5]).toString());// 通过率
			itemVo.setD4(((Integer) temp[4]).toString());// 人脸识别通过人数
			itemVo.setD5(((Integer) temp[1]).toString());// 总数
			sum = sum + (Integer) temp[1];
			passSum = passSum + (Integer) temp[2];
			photoYSum = photoYSum + (Integer) temp[4];
			mapItemList.add(itemVo);
		}

		MonitorMapItemVo totalMapItem = new MonitorMapItemVo(); // 合计数据
		// 计算总数
		totalMapItem.setValue(formate.format((passSum * 1.0) / sum));
		totalMapItem.setD1("0");
		totalMapItem.setD2(Integer.toString(passSum));
		totalMapItem.setD3(passSum == 0 ? "0" : formate
				.format((photoYSum * 1.0) / passSum));
		totalMapItem.setD4(Integer.toString(photoYSum));
		totalMapItem.setD5(Integer.toString(sum));
		monitorMapVo.setMapItemList(mapItemList);
		monitorMapVo.setTotalMapItem(totalMapItem);
		return monitorMapVo;
	}

	/**
	 * 省监控界面
	 * 
	 * @param subject
	 * @return List<String[]>
	 *         String[]{0:行政区名称,1:总数,2:安检人数,3:参考率,4:人脸识别通过人数,5:通过率}
	 */
	public MonitorMapVo monitorByProvince(String areaId, String subject) {
		MonitorMapVo monitorMapVo = new MonitorMapVo();
		DecimalFormat formate = new DecimalFormat("##.#%");
		List<?> list = this.getResultExamSumDAO().monitorByProvince(areaId,
				subject);
		List<MonitorMapItemVo> mapItemList = new ArrayList<MonitorMapItemVo>();
		MonitorMapItemVo itemVo = null;
		int sum = 0;// 累加总数
		int passSum = 0;// 累加安检人数
		int photoYSum = 0;// 累加人脸通过人数
		for (Object object : list) {
			Object[] temp = (Object[]) object;
			itemVo = new MonitorMapItemVo();
			itemVo.setName(BaseCodeUtils.findBaseCodeById((String) temp[0])
					.getName()); // 行政区名称
			itemVo.setValue(((BigDecimal) temp[3]).toString());// 参考率
			itemVo.setD1((String) temp[0]);// 地区代码
			itemVo.setD2(((Integer) temp[2]).toString());// 安检人数
			itemVo.setD3(((BigDecimal) temp[5]).toString());// 通过率
			itemVo.setD4(((Integer) temp[4]).toString());// 人脸识别通过人数
			itemVo.setD5(((Integer) temp[1]).toString());// 总数
			sum = sum + (Integer) temp[1];
			passSum = passSum + (Integer) temp[2];
			photoYSum = photoYSum + (Integer) temp[4];
			mapItemList.add(itemVo);
		}

		MonitorMapItemVo totalMapItem = new MonitorMapItemVo(); // 合计数据
		// 计算总数
		totalMapItem.setValue(formate.format((passSum * 1.0) / sum));
		totalMapItem.setD1("0");
		totalMapItem.setD2(Integer.toString(passSum));
		totalMapItem.setD3(passSum == 0 ? "0" : formate
				.format((photoYSum * 1.0) / passSum));
		totalMapItem.setD4(Integer.toString(photoYSum));
		totalMapItem.setD5(Integer.toString(sum));
		monitorMapVo.setMapItemList(mapItemList);
		monitorMapVo.setTotalMapItem(totalMapItem);

		monitorMapVo.setMapItemList(mapItemList);
		monitorMapVo.setTotalMapItem(totalMapItem);

		String mapName = BaseCodeUtils.findBaseCodeById(areaId).getAliax1();// 获取省份简称
		monitorMapVo.setMapName(mapName);

		List<String> barTitleList = new ArrayList<String>();
		List<BaseCodeVo> tempCodeList = BaseCodeUtils.findCodeListByPreviousId(
				areaId, true, false);
		for (BaseCodeVo tempCode : tempCodeList) {
			barTitleList.add(tempCode.getName());
		}
		monitorMapVo.setBarTitleList(barTitleList);

		return monitorMapVo;
	}

	/**
	 * 考区监控
	 * 
	 * @param areaId
	 * @param subject
	 * @return List<String[]>
	 *         String[]{0:考点名称,1:通道IMEI,2:通道类型,3:精度,4:安检人数,5:未通过人数,6:已通过人数}
	 */
	public List<String[]> monitorByPlace(String areaId, String subject) {
		List<?> list = this.getResultExamSumDAO().monitorByPlace(areaId,
				subject);
		List<String[]> resultList = new ArrayList<String[]>();
		String[] value = null;
		for (Object object : list) {
			Object[] temp = (Object[]) object;
			value = new String[7];
			value[0] = (String) temp[0]; // 考点名称
			String imei = (String) temp[1];// imei编号
			value[1] = (String) temp[1];// imei编号
			DeviceFaceInfo deviceFaceInfo = this.getDeviceFaceInfoDAO()
					.findObjectById(imei);
			if (deviceFaceInfo == null) {
				value[2] = "";// 通道类型
				value[3] = "";// 精度
			} else {
				value[2] = "";// this.getPassType(deviceFaceInfo.getPassType());//通道类型
				value[3] = "";// this.getVerPrecision(deviceFaceInfo.getVerifyPrecision());//精度
			}
			value[4] = ((Integer) temp[2]).toString();// 安检人数
			value[5] = ((Integer) temp[3]).toString();// 未通过人数
			value[6] = ((Integer) temp[4]).toString();// 已通过人数
			resultList.add(value);
		}
		return resultList;
	}

	/**
	 * 上海市获取地市地图统计数据
	 * 
	 * @param areaId
	 * @param subject
	 *            {0:地区名称,1:总数,2:经度纬度,3:地区代码,4:参考率,5:参考人数,6:通过率,7:通过人数,8:总人数}
	 * 
	 */
	public ShMonitorMapVo shMonitorByCity(String areaId, String subject) {
		DecimalFormat formate = new DecimalFormat("##.#%");
		ShMonitorMapVo shMonitorMapVo = new ShMonitorMapVo();
		List<ShMonitorMapItemVo> monitorMapItemVos = new ArrayList<ShMonitorMapItemVo>();// 但数据集合
		ShMonitorMapItemVo shMonitorMapItemVo = new ShMonitorMapItemVo();// 总数据
		ShMonitorMapItemVo mapItemVo = new ShMonitorMapItemVo();

		List<?> list = this.getResultExamSumDAO().ShMonitorByCity(areaId,
				subject);

		int d1 = 0;// 参考人数
		int d2 = 0;// 通过人数
		int d3 = 0;// 总人数

		BaseCode cityCode = this.baseCodeDAO.findObjectById(areaId);

		shMonitorMapVo.setMapName(cityCode.getName());// 地图名称
		if (list != null && list.size() > 0) {
			for (Object object : list) {
				Object[] temp = (Object[]) object;
				mapItemVo = new ShMonitorMapItemVo();
				String[] jw = { temp[3].toString(), temp[4].toString() };
				mapItemVo.setValue(jw);
				mapItemVo.setName(temp[2].toString());
				mapItemVo.setD1(temp[5].toString());
				mapItemVo.setD2(formate.format(Double.parseDouble(temp[7]
						.toString()) / 100));
				mapItemVo.setD3(temp[6].toString());
				mapItemVo.setD4(formate.format(Double.parseDouble(temp[9]
						.toString()) / 100));
				mapItemVo.setD5(temp[8].toString());
				mapItemVo.setD6(temp[5].toString());
				d1 += (Integer) temp[6];
				d2 += (Integer) temp[8];
				d3 += (Integer) temp[5];
				// mapItemVo.setLinkName(temp[10].toString());
				// mapItemVo.setLinkPhone(temp[11].toString());
				monitorMapItemVos.add(mapItemVo);

			}
			shMonitorMapVo.setMapItemList(monitorMapItemVos);

			shMonitorMapItemVo.setName("总计");
			shMonitorMapItemVo.setD2(formate.format(d1 * 1.0 / d3));
			shMonitorMapItemVo.setD3(d1 + "");
			shMonitorMapItemVo.setD4(formate.format(d2 * 1.0 / d1));
			shMonitorMapItemVo.setD5(d2 + "");
			shMonitorMapItemVo.setD6(d3 + "");
			shMonitorMapVo.setTotalMapItem(shMonitorMapItemVo);
			return shMonitorMapVo;
		}
		return null;
	}

	/**
	 * 上海地图题卡监控监控界面
	 * 
	 * @return
	 */
	public ShMonitorMapVo shMonitorByCard(String areaId, String subject) {
		DecimalFormat formate = new DecimalFormat("##.#%");
		ShMonitorMapVo shMonitorMapVo = new ShMonitorMapVo();
		List<ShMonitorMapItemVo> monitorMapItemVos = new ArrayList<ShMonitorMapItemVo>();// 但数据集合
		ShMonitorMapItemVo shMonitorMapItemVo = new ShMonitorMapItemVo();// 总数据
		ShMonitorMapItemVo mapItemVo = new ShMonitorMapItemVo();

		List<?> list = this.getResultExamSumDAO().ShMonitorCradByCity(areaId,
				subject);

		int d1 = 0;// 参考人数
		int d2 = 0;// 总人数
		shMonitorMapVo.setMapName("上海题卡");// 地图名称
		if (list != null && list.size() > 0) {
			for (Object object : list) {
				Object[] temp = (Object[]) object;
				mapItemVo = new ShMonitorMapItemVo();
				String[] jw = { temp[3].toString(), temp[4].toString() };
				mapItemVo.setValue(jw);
				mapItemVo.setName(temp[2].toString());
				mapItemVo.setD1(temp[5].toString());
				mapItemVo.setD2(formate.format(Double.parseDouble(temp[7]
						.toString()) / 100));
				mapItemVo.setD3(temp[6].toString());
				mapItemVo.setD4(temp[5].toString());
				mapItemVo.setLinkName(temp[8].toString());
				mapItemVo.setLinkPhone(temp[9].toString());
				d1 += (Integer) temp[6];
				d2 += (Integer) temp[5];
				monitorMapItemVos.add(mapItemVo);

			}
			shMonitorMapVo.setMapItemList(monitorMapItemVos);

			shMonitorMapItemVo.setName("总计");
			shMonitorMapItemVo.setD2(formate.format(d1 * 1.0 / d2));
			shMonitorMapItemVo.setD3(d1 + "");
			shMonitorMapItemVo.setD4(d2 + "");
			shMonitorMapVo.setTotalMapItem(shMonitorMapItemVo);
			return shMonitorMapVo;
		}
		return null;
	}

	/**
	 * 
	 * @param list
	 * @param separate
	 *            字符串与数字分隔
	 * @param molecule
	 *            分子
	 * @param denominator
	 *            分母
	 * @return
	 */
	private List<String[]> convertStruct(List<?> list, int separate,
			int molecule, int denominator) {
		// 基础数据长度
		int baseDatalength = 0;
		// 循环数据库查询结果，构造基础数据列表
		Object[] value = null;
		String[] result = null;
		List<String[]> resultList = new ArrayList<String[]>();
		if (!list.isEmpty()) {
			baseDatalength = ((Object[]) list.get(0)).length + 1;
		} else {
			return resultList;
		}

		// 声明合计项
		int[] sum = new int[baseDatalength];
		DecimalFormat formate = new DecimalFormat("##.#%");
		for (Object object : list) {
			result = new String[baseDatalength];
			value = (Object[]) object;
			for (int i = 0; i < value.length; i++) {
				if (i < separate) {
					result[i] = (String) value[i];
				} else {
					result[i] = Integer.toString((Integer) value[i]);
					sum[i] = sum[i] + (Integer) value[i];
				}
			}
			if (Integer.parseInt(result[denominator]) > 0) {
				result[baseDatalength - 1] = formate.format(Double
						.parseDouble(result[molecule])
						/ Integer.parseInt(result[denominator]));// 比例
			} else {
				result[baseDatalength - 1] = formate.format(0);// 比例
			}

			resultList.add(result);
		}
		result = new String[baseDatalength];
		for (int j = 0; j < baseDatalength; j++) {
			if (j < separate) {
				result[j] = "合计";
			} else {
				result[j] = Integer.toString(sum[j]);
			}
		}
		if (sum[denominator] > 0) {
			result[baseDatalength - 1] = formate.format(sum[molecule] * 1.0
					/ sum[denominator]);// 比例
		} else {
			result[baseDatalength - 1] = formate.format(0);// 比例
		}
		resultList.add(result);
		return resultList;
	}

	public void setResultExamSumDAO(ResultExamSumDAO resultExamSumDAO) {
		this.resultExamSumDAO = resultExamSumDAO;
	}

	public ResultCard1DAO getResultCard1DAO() {
		return resultCard1DAO;
	}

	public void setResultCard1DAO(ResultCard1DAO resultCard1DAO) {
		this.resultCard1DAO = resultCard1DAO;
	}

	public ExamineeDAO getExamineeDAO() {
		return examineeDAO;
	}

	public void setExamineeDAO(ExamineeDAO examineeDAO) {
		this.examineeDAO = examineeDAO;
	}

	public DeviceFaceInfoDAO getDeviceFaceInfoDAO() {
		return deviceFaceInfoDAO;
	}

	public void setDeviceFaceInfoDAO(DeviceFaceInfoDAO deviceFaceInfoDAO) {
		this.deviceFaceInfoDAO = deviceFaceInfoDAO;
	}

	public ResultExamSumDAO getResultExamSumDAO() {
		return resultExamSumDAO;
	}

	public ResultCard2DAO getResultCard2DAO() {
		return resultCard2DAO;
	}

	public void setResultCard2DAO(ResultCard2DAO resultCard2DAO) {
		this.resultCard2DAO = resultCard2DAO;
	}

	public ResultCard3DAO getResultCard3DAO() {
		return resultCard3DAO;
	}

	public void setResultCard3DAO(ResultCard3DAO resultCard3DAO) {
		this.resultCard3DAO = resultCard3DAO;
	}

	public BaseCodeDAO getBaseCodeDAO() {
		return baseCodeDAO;
	}

	public void setBaseCodeDAO(BaseCodeDAO baseCodeDAO) {
		this.baseCodeDAO = baseCodeDAO;
	}

	public ResultExaminee1DAO getResultExaminee1DAO() {
		return resultExaminee1DAO;
	}

	public void setResultExaminee1DAO(ResultExaminee1DAO resultExaminee1DAO) {
		this.resultExaminee1DAO = resultExaminee1DAO;
	}

	public CountByExamPlaceVo countByExamData(String paramPlaceId,
			String paramAreaId, String subject) {
		CountByExamPlaceVo countByExamPlaceVo = new CountByExamPlaceVo();
		// 根据考点查询统计数据
		if (!StringUtils.isEmpty(paramPlaceId)) {
			List<?> list = this.getResultExamSumDAO().getCountByExamPlace(
					paramPlaceId, subject);
			String examPlaceId = null;
			String examPlaceName = null;
			int total = 0, passN = 0, passY = 0, count = 0;
			for (Object object : list) {
				Object[] temp = (Object[]) object;
				examPlaceId = (String) temp[0];
				examPlaceName = (String) temp[1];
				total += Integer.parseInt(temp[3].toString());
				passN += Integer.parseInt(temp[4].toString());
				passY += Integer.parseInt(temp[5].toString());
			}
			count = this.getResultExamSumDAO().countByExamPlace(paramPlaceId, subject);
			countByExamPlaceVo.setExamPlaceId(examPlaceId);
			countByExamPlaceVo.setExamPlaceName(examPlaceName);
			countByExamPlaceVo.setTotal(total);
			countByExamPlaceVo.setPassN(passN);
			countByExamPlaceVo.setPassY(passY);
			countByExamPlaceVo.setImeiNoTotal(list.size());
			countByExamPlaceVo.setCount(count);
		}
		// 根据考区查询统计数据
		else if (!StringUtils.isEmpty(paramAreaId)) {
			List<?> list = this.getResultExamSumDAO().getCountByExamArea(
					paramAreaId, subject);
			int total = 0, passN = 0, passY = 0, count = 0;
			for (Object object : list) {
				Object[] temp = (Object[]) object;
				total += Integer.parseInt(temp[3].toString());
				passN += Integer.parseInt(temp[4].toString());
				passY += Integer.parseInt(temp[5].toString());
			}
			count = this.getResultExamSumDAO().countByExamArea(paramAreaId, subject);
			countByExamPlaceVo.setTotal(total);
			countByExamPlaceVo.setPassN(passN);
			countByExamPlaceVo.setPassY(passY);
			countByExamPlaceVo.setImeiNoTotal(list.size());
			countByExamPlaceVo.setCount(count);
		}

		// 只根据科目查询
		else {
			List<?> list = this.getResultExamSumDAO()
					.getCountBySubject(subject);
			int total = 0, passN = 0, passY = 0, count = 0;
			for (Object object : list) {
				Object[] temp = (Object[]) object;
				total += Integer.parseInt(temp[3].toString());
				passN += Integer.parseInt(temp[4].toString());
				passY += Integer.parseInt(temp[5].toString());
			}
			count = this.getResultExamSumDAO().countBySubject(subject);
			countByExamPlaceVo.setTotal(total);
			countByExamPlaceVo.setPassN(passN);
			countByExamPlaceVo.setPassY(passY);
			countByExamPlaceVo.setImeiNoTotal(list.size());
			countByExamPlaceVo.setCount(count);
		}

		return countByExamPlaceVo;

	}

	public GxCountByExamPlace countSubjectByExamPlace(String paramPlaceId, String paramAreaId) {
		GxCountByExamPlace gxCountByExamPlace = new GxCountByExamPlace();
		List<?> list = this.getResultExamSumDAO().countSubjectByExamPlace(paramPlaceId, paramAreaId);
		if (null != list && list.size() > 0) {
			for (Object object : list) {
				Object[] temp = (Object[]) object;
				gxCountByExamPlace.setD1(temp[0].toString());
				gxCountByExamPlace.setD2(temp[1].toString());
				gxCountByExamPlace.setD3(temp[2].toString());
				gxCountByExamPlace.setD4(temp[3].toString());
				gxCountByExamPlace.setD5(temp[4].toString());
				gxCountByExamPlace.setD6(temp[5].toString());
				gxCountByExamPlace.setD7(temp[6].toString());
				gxCountByExamPlace.setD8(temp[7].toString());
				gxCountByExamPlace.setD9(temp[8].toString());
				gxCountByExamPlace.setD10(temp[9].toString());
				gxCountByExamPlace.setD11(temp[10].toString());
				gxCountByExamPlace.setD12(temp[11].toString());
				gxCountByExamPlace.setD13(temp[12].toString());
				gxCountByExamPlace.setD14(temp[13].toString());
				gxCountByExamPlace.setD15(temp[14].toString());
				gxCountByExamPlace.setD16(temp[15].toString());
				gxCountByExamPlace.setD17(temp[16].toString());
				gxCountByExamPlace.setD18(temp[17].toString());
				gxCountByExamPlace.setD19(temp[18].toString());
				gxCountByExamPlace.setD20(temp[19].toString());
				gxCountByExamPlace.setD21(temp[20].toString());
				gxCountByExamPlace.setD22(temp[21].toString());
			}
		}
		return gxCountByExamPlace;
	}

	

}
