package com.sinotn.examsafety.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.service.sh.BaiduMapConsts;
import com.sinotn.examsafety.service.sh.ExamPlaceServiceImpl;
import com.sinotn.examsafety.vo.ExamPlaceVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @Description:   考点信息管理action
 * @Author:        李斌
 * @Version:       V1.0.0 
 * @Date:          2017年7月31日 上午11:21:53
 */
@SuppressWarnings("serial")
public class ExamPlaceAction extends BaseAction{
	private static Logger logger = Logger.getLogger(ExamPlaceAction.class);
	
	// 注入考点信息管理业务层
	private ExamPlaceServiceImpl examPlaceServiceImpl = null;
	
	// 考点信息VO
	private ExamPlaceVo examPlaceVo;
	
	private String excelFilePath;
	/**
	 * 考点信息导入
	 * @return
	 */
	public String importExamPlace() {

		try {
			if (excelFilePath != null) {
				File file = new File(excelFilePath);
				// 将file转成multipartFile（如是springMVC接受可不转）
				FileInputStream inputStream = new FileInputStream(file);
		        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
		        /**
		         * 读取excel数据并导入
		         */
				Object[] backMsg = this.examPlaceServiceImpl.importExamPlace(multipartFile);
				if ((Boolean) backMsg[1]) {
					this.setAjaxBackDataMain(backMsg[0]);
				}else {
					this.setAjaxBackDataErrorMsg("导入失败！请仔细检查数据是否正确和阅读导入说明！");
				}
			}else {
				this.setAjaxBackDataErrorMsg("加载文件失败!");
			}
		} catch (Exception e) {
			this.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	
		
	}
	/**
	 * 跳转到签到人管理
	 * @return
	 */
	public String gotoSignin() {
		return SUCCESS;
		
	}
	/**
	 * 根据考点地址获取坐标
	 * @return
	 */
	public String getPointByExamAddr(){
		try {
			ExamPlaceVo examPlaceVoTemp = this.examPlaceServiceImpl.findExamPlaceById(examPlaceVo);
			String examAddr = examPlaceVoTemp.getExamAddr().trim();
			if (!"".equals(examAddr) && null != examAddr) {
				JSONObject jsonObject = BaiduMapConsts.getPointByAddress(examAddr);
				// 获取返回成功状态码。0为成功，其他数字为失败
				String backStatus = jsonObject.optString("status");
				if ("0".equals(backStatus)) {
					// 更新数据库
					this.examPlaceServiceImpl.updatePointForExamPlace(examPlaceVoTemp, jsonObject);
				}
			}
			else {
				throw new RuntimeException("地址为空！");
			}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 批量获取坐标经纬度
	 * @return
	 */
	public String batchGetPoint() {
		try {
			List<ExamPlaceVo> list = this.examPlaceServiceImpl.findExamPlace();
			if (null != list && list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					examPlaceVo = list.get(i);
					if (!"".equals(examPlaceVo.getExamAddr()) && null != examPlaceVo.getExamAddr()) {
						getPointByExamAddr();
					}
				}
			}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 跳转到人工校对信息
	 */
	public String gotoUpdatePoint() {
		
		examPlaceVo = this.examPlaceServiceImpl.findExamPlaceById(examPlaceVo);
		return SUCCESS;
	}
	/**
	 * 修改经纬度
	 * @return
	 */
	public String updatePoint() {
		try {
			this.examPlaceServiceImpl.updatePoint(examPlaceVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 根据考点id获取考点信息
	 * @return
	 */
	public String getExamPlaceByExamArea() {
		try {
			List<ExamPlaceVo> list = this.examPlaceServiceImpl.getExamPlaceByExamArea(examPlaceVo);
			super.setAjaxBackDataMain(list);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/** ----------------------------------------getter/setter ------------------------*/
	public ExamPlaceServiceImpl getExamPlaceServiceImpl() {
		return examPlaceServiceImpl;
	}

	public void setExamPlaceServiceImpl(ExamPlaceServiceImpl examPlaceServiceImpl) {
		this.examPlaceServiceImpl = examPlaceServiceImpl;
	}

	public ExamPlaceVo getExamPlaceVo() {
		return examPlaceVo;
	}

	public void setExamPlaceVo(ExamPlaceVo examPlaceVo) {
		this.examPlaceVo = examPlaceVo;
	}
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	
	
}
