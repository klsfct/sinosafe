package com.sinotn.examsafety.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinotn.examsafety.dao.BaseCodeDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.po.BaseCode;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.vo.BaseCodeVo;

public class BaseCodeServiceImpl {
	private BaseCodeDAO baseCodeDAO = null;
	public List<BaseCodeVo> findCodeList(String category) {
		List<BaseCode> baseCodeList = getBaseCodeDAO().findByProperty("category", category, "id");
		List<BaseCodeVo> baseCodeVoList = new ArrayList<BaseCodeVo>();
		for (BaseCode temp : baseCodeList) {
			baseCodeVoList.add(new BaseCodeVo(temp));
		}
		return baseCodeVoList;
	}

	public List<BaseCodeVo> findCodeList(String category, Boolean isEnabled) {
		String[] propertyNames = {"category","isEnabled"};
		Object[] values = {category,isEnabled};
		List<BaseCode> baseCodeList = getBaseCodeDAO().findByPropertyArry(propertyNames, values, "id");
		List<BaseCodeVo> baseCodeVoList = new ArrayList<BaseCodeVo>();
		for (BaseCode temp : baseCodeList) {
			baseCodeVoList.add(new BaseCodeVo(temp));
		}
		return baseCodeVoList;
	}

	public List<BaseCodeVo> findCodeListByPreviousId(String previousId) {
		List<BaseCode> baseCodeList = getBaseCodeDAO().findByProperty("previousId", previousId, "id");
		List<BaseCodeVo> baseCodeVoList = new ArrayList<BaseCodeVo>();
		for (BaseCode temp : baseCodeList) {
			baseCodeVoList.add(new BaseCodeVo(temp));
		}
		return baseCodeVoList;
	}

	public List<BaseCodeVo> findCodeListByPreviousId(String previousId,Boolean isEnabled) {
		String[] propertyNames = {"previousId","isEnabled"};
		Object[] values = {previousId,isEnabled};
		List<BaseCode> baseCodeList = getBaseCodeDAO().findByPropertyArry(propertyNames, values, "id");
		List<BaseCodeVo> baseCodeVoList = new ArrayList<BaseCodeVo>();
		for (BaseCode temp : baseCodeList) {
			baseCodeVoList.add(new BaseCodeVo(temp));
		}
		return baseCodeVoList;
	}
	public BaseCodeVo findBaseCodeById(String id) {
		BaseCode baseCode = this.getBaseCodeDAO().findObjectById(id);
		if (baseCode == null) {
			return null;
		} else {
			return new BaseCodeVo(baseCode);
		}
	}

	public BaseCodeDAO getBaseCodeDAO() {
		return baseCodeDAO;
	}

	public void setBaseCodeDAO(BaseCodeDAO baseCodeDAO) {
		this.baseCodeDAO = baseCodeDAO;
	}

}
