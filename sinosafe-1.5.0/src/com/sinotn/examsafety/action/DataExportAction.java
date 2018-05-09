package com.sinotn.examsafety.action;

import java.util.List;

import com.sinotn.examsafety.po.Subject;
import com.sinotn.examsafety.service.impl.DataExportManager;
import com.sinotn.examsafety.service.impl.SubjectServiceImpl;
import com.sinotn.examsafety.service.sh.ExamPlaceServiceImpl;
import com.sinotn.examsafety.vo.ExamPlaceVo;

@SuppressWarnings("serial")
public class DataExportAction extends BaseAction {

	private DataExportManager dataExportManager = null;
	private ExamPlaceServiceImpl examPlaceServiceImpl;
	private SubjectServiceImpl subjectServiceImpl;
	private String subject;

	/**
	 * 考区CITY文件导出
	 * 
	 * @return
	 */
	public String areaDataExport() {
		// 等级
		int level = this.getLoginUser().getLevel();
		if (level == 4) {
			this.getDataExportManager().areaDataExportByExamPlace(this.getLoginUser().getExamPlaceId());
		}else {
			this.getDataExportManager().areaDataExport();
		}
		
		return SUCCESS;
	}

	/**
	 * 手持机读卡考点文件导出
	 * 
	 * @return
	 */
	public String cardPlaceDataExport() {
		this.getDataExportManager().cardPlaceDataExport();
		return SUCCESS;
	}

	/**
	 * 人脸识别考点文件导出
	 * 
	 * @return
	 */
	public String facePlaceDataExport() {
		// 等级
		int level = this.getLoginUser().getLevel();
		if (level == 4) {
			this.getDataExportManager().facePlaceDataExportByExamPlace(this.getLoginUser().getExamPlaceId());
		}else {
			this.getDataExportManager().facePlaceDataExport();
		}
		
		return SUCCESS;
	}

	/**
	 * 考场文件导出
	 * 
	 * @return
	 */
	public String roomDataExport() {
		this.getDataExportManager().roomDataExport();
		return SUCCESS;
	}

	/**
	 * 考生文件导出
	 * 
	 * @return
	 */
	public String examineeDataExport() {
		if (null != subject && !"".equals(subject)) {
			// 等级
			int level = this.getLoginUser().getLevel();
			if (level == 4) {
				this.getDataExportManager().examineeDataExportByExamPlace(
						subject, this.getLoginUser().getExamPlaceId());
			} else {
				this.getDataExportManager().examineeDataExport(subject);
			}

		} else {
			this.getDataExportManager().examineeDataExport();
		}
		return SUCCESS;

	}

	/**
	 * 人工核查科目文件导出
	 * 
	 * @return
	 */
	public String checkSubjectDataExport() {
		this.getDataExportManager().checkSubjectDataExport();
		return SUCCESS;
	}

	/**
	 * 人工核查考点文件导出
	 * 
	 * @return
	 */
	public String checkPlaceDataExport() {
		// 等级
		int level = this.getLoginUser().getLevel();
		if (level == 4) {
			this.getDataExportManager().checkPlaceDataExport(
					this.getLoginUser().getExamPlaceId());
		} else {
			this.getDataExportManager().checkPlaceDataExport();
		}

		return SUCCESS;
	}

	/**
	 * 人工核查系统考生信息导出
	 * 
	 * @return
	 */
	public String checkExamineeDataExport() {
		// 等级
		int level = this.getLoginUser().getLevel();
		if (level == 4) {
			this.getDataExportManager().checkExamineeDataExport(
					this.getLoginUser().getExamPlaceId());
		} else {
			this.getDataExportManager().checkExamineeDataExport();
		}

		return SUCCESS;

	}

	public DataExportManager getDataExportManager() {
		return dataExportManager;
	}

	public void setDataExportManager(DataExportManager dataExportManager) {
		this.dataExportManager = dataExportManager;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public ExamPlaceServiceImpl getExamPlaceServiceImpl() {
		return examPlaceServiceImpl;
	}

	public void setExamPlaceServiceImpl(ExamPlaceServiceImpl examPlaceServiceImpl) {
		this.examPlaceServiceImpl = examPlaceServiceImpl;
	}

	public SubjectServiceImpl getSubjectServiceImpl() {
		return subjectServiceImpl;
	}

	public void setSubjectServiceImpl(SubjectServiceImpl subjectServiceImpl) {
		this.subjectServiceImpl = subjectServiceImpl;
	}

	/**
	 * 根据考点导出考区，考点，考生数据文件
	 * @return
	 */
	public String faceExportDataByExamPlace() {
		// 查询所有考点
		List<ExamPlaceVo> examPlaceList = this.examPlaceServiceImpl.findExamPlace();
		// 查询科目
		List<Subject> subjectList = this.subjectServiceImpl.findSubject();
		if (null != examPlaceList && examPlaceList.size()>0) {
			// 循环导出
			for (ExamPlaceVo examPlaceVo : examPlaceList) {
				System.out.println("人脸数据导入开始：" + examPlaceVo.getExamPlace() + examPlaceVo.getExamPlaceName());
				long beginTime = System.currentTimeMillis();
				/**
				 * 导出人脸识别
				 */
				// 1.导出考区
				this.getDataExportManager().areaDataExportByExamPlace(examPlaceVo.getExamPlace());
				// 2.导出考点
				this.getDataExportManager().facePlaceDataExportByExamPlace(examPlaceVo.getExamPlace());
				// 3.导出考生
				if (null != subjectList && subjectList.size()>0) {
					for (Subject subject : subjectList) {
						this.getDataExportManager().examineeDataExportByExamPlace(subject.getId().toString(), examPlaceVo.getExamPlace());
					}
				}
				long endTime = System.currentTimeMillis();
				float excTime = (float)(endTime-beginTime)/1000;  
				System.out.println("人脸数据导入结束：" + examPlaceVo.getExamPlace() 
						+ examPlaceVo.getExamPlaceName() + "(耗时：" + excTime + "秒)" );
				beginTime = 0;
				endTime = 0;
				excTime = 0;
			}
		}
		return SUCCESS;
		
	}
	/**
	 * 根据考点导出考区，考点，考生数据文件
	 * @return
	 */
	public String checkExportDataByExamPlace() {
		// 查询所有考点
		List<ExamPlaceVo> examPlaceList = this.examPlaceServiceImpl.findExamPlace();
		if (null != examPlaceList && examPlaceList.size()>0) {
			// 循环导出
			for (ExamPlaceVo examPlaceVo : examPlaceList) {
				System.out.println("人工核查数据导入开始：" + examPlaceVo.getExamPlace() + examPlaceVo.getExamPlaceName());
				long beginTime = System.currentTimeMillis();
				/**
				 * 导出人工核查
				 */
				// 1.考点
				this.getDataExportManager().checkPlaceDataExport(examPlaceVo.getExamPlace());
						
				// 2.科目
				this.getDataExportManager().checkSubjectDataExport(examPlaceVo.getExamPlace());
				// 3.考生
				this.getDataExportManager().checkExamineeDataExport(examPlaceVo.getExamPlace());
				
				long endTime = System.currentTimeMillis();
				float excTime = (float)(endTime-beginTime)/1000;  
				System.out.println("人工核查数据导入结束：" + examPlaceVo.getExamPlace() 
						+ examPlaceVo.getExamPlaceName() + "(耗时：" + excTime + "秒)" );
				beginTime = 0;
				endTime = 0;
				excTime = 0;
			}
		}
		return SUCCESS;
		
	}
}
