package com.sinotn.common.pagequery;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.sinotn.common.exception.BusinessException;
import com.sinotn.common.exception.EncryptException;
import com.sinotn.common.utils.EncryptUtils;

public class PageQueryManage {

	/**
	 * 分页查询DAO接口
	 */
	private PageQueryDAO pageQueryDAO = null;

	/**
	 * 根据分页基础类提供能信息获取分页查询类表
	 * 
	 * @param page
	 *            分页基础类
	 * @return 数据列表
	 */
	@SuppressWarnings("unchecked")
	public PageResult pageQuery(Page page) {
		List<?> poList = null;
		if (page.getEveryPage() < 1) {
			// 不使用分页
			int totalRecords = this.getPageQueryDAO().getResultCount(page);
			if (totalRecords > 0) {
				page.setTotalCount(totalRecords);
				poList = this.getPageQueryDAO().getResults(page);
			}
		} else {
			int totalRecords = this.getPageQueryDAO().getResultCount(page);
			page = PageUtil.createPage(page, totalRecords);
			if (totalRecords > 0) {
				page.setTotalCount(totalRecords);
				poList = this.getPageQueryDAO().getResultByPage(page);
			}
		}
		@SuppressWarnings("rawtypes")
		List voList = new ArrayList();
		try {
			if (poList != null && poList.size() > 0) {
				Class<?> voClass = Class.forName(page.getEntityVo());
				Constructor<?> constructor = voClass.getConstructor(poList.get(
						0).getClass());
				for (Object po : poList) {
					voList.add(constructor.newInstance(po));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			page.setCondition(EncryptUtils.encryptAES(page.getCondition()));
		} catch (EncryptException ex) {
			throw new BusinessException("nvce.url.filter.err");
		}
		PageResult pageResult = new PageResult(page, voList);
		return pageResult;
	}
	
	
	
	/**
	 * 根据分页基础类提供能信息获取分页查询类表
	 * 
	 * @param page
	 *            分页基础类
	 * @return 数据列表
	 */
	@SuppressWarnings("unchecked")
	public PageResult pageQueryAjax(Page page) {
		List<?> poList = null;
		if (page.getEveryPage() < 1) {
			// 不使用分页
			int totalRecords = this.getPageQueryDAO().getResultCount(page);
			if (totalRecords > 0) {
				page.setTotalCount(totalRecords);
				poList = this.getPageQueryDAO().getResults(page);
			}
		} else {
			int totalRecords = this.getPageQueryDAO().getResultCount(page);
			if (totalRecords > 0) {
				page.setTotalCount(totalRecords);
				poList = this.getPageQueryDAO().getResultByPage(page);
			}
		}
		@SuppressWarnings("rawtypes")
		List voList = new ArrayList();
		try {
			if (poList != null && poList.size() > 0) {
				Class<?> voClass = Class.forName(page.getEntityVo());
				Constructor<?> constructor = voClass.getConstructor(poList.get(
						0).getClass());
				for (Object po : poList) {
					voList.add(constructor.newInstance(po));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			page.setCondition(EncryptUtils.encryptAES(page.getCondition()));
		} catch (EncryptException ex) {
			throw new BusinessException("nvce.url.filter.err");
		}
		PageResult pageResult = new PageResult(page, voList);
		return pageResult;
	}
	

	public PageQueryDAO getPageQueryDAO() {
		return pageQueryDAO;
	}

	public void setPageQueryDAO(PageQueryDAO pageQueryDAO) {
		this.pageQueryDAO = pageQueryDAO;
	}

}
