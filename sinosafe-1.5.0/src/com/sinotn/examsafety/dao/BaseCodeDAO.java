package com.sinotn.examsafety.dao;

import java.util.List;

import com.sinotn.examsafety.po.BaseCode;

public class BaseCodeDAO extends BaseDAO<BaseCode, String> {

	private static final String CATEGORY_PROVINCE = "AREA_PROVINCE";
	private static final String CATEGORY_LEVEL_1 = "1";
	private static final String CATEGORY_LEVEL_2 = "2";
	private static final String CATEGORY_LEVEL_3 = "3";
	/**
	 * 获得省对象集合
	 * @return
	 */
	public List<BaseCode> findProvinceList(){
		String propertyName = "flag";
		Object value = CATEGORY_LEVEL_2;
		String sort = "ID ASC";
		return this.findByProperty(propertyName, value, sort);
	}
}
