package com.sinotn.examsafety.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sinotn.common.utils.PropertiesReader;
import com.sinotn.examsafety.vo.BaseCodeVo;

public class BaseCodeUtils {

	private static Map<Long, Long> lockMap = null;

	public static BaseCodeServiceImpl getBaseCodeServiceImpl() {
		ApplicationContext ac1 = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext
						.getServletContext());
		BaseCodeServiceImpl baseCodeServiceImpl = (BaseCodeServiceImpl) ac1
				.getBean("baseCodeServiceImpl");
		return baseCodeServiceImpl;
	}

	public static List<BaseCodeVo> findCodeList(String category) {

		List<BaseCodeVo> baseCodeVoList = getBaseCodeServiceImpl()
				.findCodeList(category);
		return baseCodeVoList;
	}

	public static List<BaseCodeVo> findCodeList(String category,
			Boolean isEnabled) {
		List<BaseCodeVo> baseCodeVoList = getBaseCodeServiceImpl()
				.findCodeList(category, isEnabled);
		return baseCodeVoList;
	}

	public static BaseCodeVo findBaseCodeById(String id) {
		return getBaseCodeServiceImpl().findBaseCodeById(id);
	}

	public static List<BaseCodeVo> findCodeListByPreviousId(String previousId,
			Boolean isInclude) {
		List<BaseCodeVo> baseCodeVoList = getBaseCodeServiceImpl()
				.findCodeListByPreviousId(previousId);
		if (isInclude) {
			BaseCodeVo baseCodeVo = getBaseCodeServiceImpl().findBaseCodeById(
					previousId);
			baseCodeVoList.add(0, baseCodeVo);
		}
		return baseCodeVoList;
	}

	public static List<BaseCodeVo> findCodeListByPreviousId(String previousId,
			Boolean isEnabled, Boolean isInclude) {
		List<BaseCodeVo> baseCodeVoList = getBaseCodeServiceImpl()
				.findCodeListByPreviousId(previousId, isEnabled);
		if (isInclude) {
			BaseCodeVo baseCodeVo = getBaseCodeServiceImpl().findBaseCodeById(
					previousId);
			baseCodeVoList.add(0, baseCodeVo);
		}
		return baseCodeVoList;
	}

	/**
	 * 考点Map
	 * 
	 * @return
	 */
	public static Map<Long, Long> getLockMap() {
		if (lockMap == null) {
			lockMap = new HashMap<Long, Long>();
			String path = PropertiesReader.getValue("examPlaceFile");
			try {
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				String examPlaceId = null;
				while ((examPlaceId = br.readLine()) != null) {
					if (examPlaceId != null && !examPlaceId.equals("")) {
						lockMap.put(Long.parseLong(examPlaceId),
								Long.parseLong(examPlaceId));
					}
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("加载考点锁旗--------------------" + lockMap.size());
		}

		return lockMap;

	}

	public static List<String> findverifyResultList() {
		List<String> list = new ArrayList<String>();
		list.add("核查无误");
		list.add("无法确认");
		list.add("违纪");
		list.add("缺考");
		return list;
	}
}
