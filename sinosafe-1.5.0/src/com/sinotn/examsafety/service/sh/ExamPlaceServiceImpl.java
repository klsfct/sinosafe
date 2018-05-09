package com.sinotn.examsafety.service.sh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.POIUtils;
import com.sinotn.examsafety.dao.BaseCodeDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.po.BaseCode;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.ResultExaminee1;
import com.sinotn.examsafety.po.ResultExaminee2;
import com.sinotn.examsafety.po.ResultExaminee3;
import com.sinotn.examsafety.po.ResultExaminee4;
import com.sinotn.examsafety.po.ResultExaminee5;
import com.sinotn.examsafety.po.ResultExaminee6;
import com.sinotn.examsafety.vo.ExamPlaceVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * 
 * @Description: 考点信息管理业务层实现类
 * @Author: 李斌
 * @Version: V1.0.0
 * @Date: 2017年7月31日 上午11:24:11
 */
public class ExamPlaceServiceImpl {

	/**
	 * 注入考点信息DAO
	 */
	private ExamPlaceDAO examPlaceDAO = null;

	private BaseCodeDAO baseCodeDAO = null;

	private static final String AREA_FLAG = "3";
	/**
	 * 考点导入文档可选择字段长度位置
	 * 超过6位代表：考点人数
	 * 7：联系人
	 * 8：联系电话
	 */
	
	private static final int [] EXCEL_COLUMNS_OPTION = new int[]{6, 7, 8};

	/**
	 * 导入考点信息
	 * 
	 * @param multipartFile
	 * @return
	 */
	public Object[] importExamPlace(MultipartFile multipartFile) {

		// 导入返回消息数组
		Object[] backMsgArr = new Object[3];
		// 是否导入成功标志
		boolean flag = false;
		// 导入返回消息
		String backMsg = "";
		String[] propertyNames = new String[] { "name", "category", "isEnabled" };
		try {
			// 读取文件
			// 把Excel文件内容转换为一个ArrayList
			List arrayList = new ArrayList();
			arrayList = POIUtils.readExcel(multipartFile);
			// 保存
			for (int i = 0; i < arrayList.size(); i++) {
				String[] valStr = (String[]) arrayList.get(i);

				Object[] values = new Object[] { valStr[2].trim(), "AREA_CITY", true };
				List<BaseCode> baseCodeList = this.baseCodeDAO.findByPropertyArry(propertyNames, values);
						
				if (baseCodeList != null && baseCodeList.size() == 1) {
					for (BaseCode baseCode : baseCodeList) {
						ExamPlace examPlace = new ExamPlace();
						examPlace.setExamPlace(Long.parseLong(valStr[0].trim()));
						examPlace.setExamYear(String.valueOf(DateUtils.getYear()));
						examPlace.setExamProvince(baseCode.getPreviousId());
						examPlace.setExamProvinceName(valStr[1].trim());
						examPlace.setExamArea(baseCode.getId());
						examPlace.setExamAreaName(valStr[2].trim());
						examPlace.setExamPlaceName(valStr[3].trim());
						examPlace.setExamAddr(valStr[4].trim());
						examPlace.setIsEnabled(true);
						//可选字段
						if (valStr.length==EXCEL_COLUMNS_OPTION[0]) {
							examPlace.setExamineeSum(Integer.parseInt(valStr[5]));
						}
						if (valStr.length==EXCEL_COLUMNS_OPTION[1]) {
							examPlace.setLinkManName(valStr[6]);
						}
						if (valStr.length==EXCEL_COLUMNS_OPTION[2]) {
							examPlace.setLinkManPhone(valStr[7]);
						}
						this.examPlaceDAO.saveObj(examPlace);
						examPlace = null;
						baseCode = null;
					}
				}

			}
			backMsgArr[0] = arrayList.size();
			flag = true;
			backMsg = "导入成功！";
		} catch (Exception e) {
			backMsg = "导入发生异常：" + e.getMessage();
		} finally {
			backMsgArr[1] = flag;
			backMsgArr[2] = backMsg;
		}
		return backMsgArr;

	}

	/**
	 * 根据考区代码获取考点信息
	 * 
	 * @param examPlaceVo
	 * @return
	 */
	public ExamPlaceVo findExamPlaceById(ExamPlaceVo examPlaceVo) {
		if (null != examPlaceVo && null != examPlaceVo.getExamPlace()) {
			ExamPlace examPlace = this.examPlaceDAO.findObjectById(examPlaceVo
					.getExamPlace());
			if (null != examPlace) {
				ExamPlaceVo examPlaceVo2 = new ExamPlaceVo(examPlace);
				String lngStr = examPlace.getPointLng();
				String latStr = examPlace.getPointLat();
				if (null != lngStr && !"".equals(lngStr) && null != latStr
						&& !"".equals(latStr)) {
					examPlaceVo2.setPoint(lngStr + "," + latStr);
				}
				return examPlaceVo2;
			}
		}
		return null;
	}

	/**
	 * 获取坐标成功后更新数据库
	 * 
	 * @param examPlaceVo
	 * @param jsonObject
	 */
	public void updatePointForExamPlace(ExamPlaceVo examPlaceVo,
			JSONObject jsonObject) {
		if (null != examPlaceVo && null != examPlaceVo.getExamPlace()) {
			ExamPlace examPlace = this.examPlaceDAO.findObjectById(examPlaceVo
					.getExamPlace());
			if (null != examPlace) {
				// 获取经纬度
				JSONObject pointJsonObject = jsonObject.optJSONObject("result")
						.getJSONObject("location");
				String latStr = pointJsonObject.getString("lat");
				String lngStr = pointJsonObject.getString("lng");
				examPlace.setPointLat(latStr);
				examPlace.setPointLng(lngStr);
				this.examPlaceDAO.update(examPlace);
			}
		}

	}

	/**
	 * 查询考点信息
	 * 
	 * @return
	 */
	public List<ExamPlaceVo> findExamPlace() {
		List<ExamPlaceVo> retnList = new ArrayList<ExamPlaceVo>();
		List<ExamPlace> list = this.examPlaceDAO.findExamPlaceList();
		for (ExamPlace examPlace : list) {
			retnList.add(new ExamPlaceVo(examPlace));
		}
		return retnList;
	}

	/**
	 * 根据考点信息查询考点集合
	 * 
	 * @param examPlaceVo
	 * @return
	 */
	public List<ExamPlaceVo> getExamPlaceByExamArea(ExamPlaceVo examPlaceVo) {
		List<ExamPlaceVo> retnList = new ArrayList<ExamPlaceVo>();
		List<ExamPlace> list = this.examPlaceDAO.findExamPlaceList(examPlaceVo
				.getExamArea());
		for (ExamPlace examPlace : list) {
			retnList.add(new ExamPlaceVo(examPlace));
		}
		return retnList;
	}

	/**
	 * 修改考点经纬度
	 * 
	 * @param examPlaceVo
	 */
	public void updatePoint(ExamPlaceVo examPlaceVo) {
		if (null != examPlaceVo && null != examPlaceVo.getExamPlace()) {
			ExamPlace examPlace = this.examPlaceDAO.findObjectById(examPlaceVo
					.getExamPlace());
			if (null != examPlace) {
				String[] point = examPlaceVo.getPoint().split(",");
				examPlace.setPointLng(point[0]);
				examPlace.setPointLat(point[1]);
				examPlace.setExamAddr(examPlaceVo.getExamAddr());
				examPlace.setExamineeSum(examPlaceVo.getExamineeSum());
				examPlace.setLinkManName(examPlaceVo.getLinkManName());
				examPlace.setLinkManPhone(examPlaceVo.getLinkManPhone());
				examPlace.setExamPlaceName(examPlaceVo.getExamPlaceName());
				this.examPlaceDAO.update(examPlace);
			}
		}

	}

	/**
	 * ----------------------------------------getter/setter
	 * ------------------------
	 */
	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}

	public BaseCodeDAO getBaseCodeDAO() {
		return baseCodeDAO;
	}

	public void setBaseCodeDAO(BaseCodeDAO baseCodeDAO) {
		this.baseCodeDAO = baseCodeDAO;
	}

}
