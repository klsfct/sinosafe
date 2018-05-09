package com.sinotn.examsafety.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.sinotn.common.utils.PropertiesReader;
import com.sinotn.common.utils.StringUtils;
import com.sinotn.examsafety.service.impl.ResultExamineeServiceImpl;
import com.sinotn.examsafety.vo.ExamPlaceVo;
import com.sinotn.examsafety.vo.ResultExamineeExitVo;
import com.sinotn.examsafety.vo.SubjectVo;

@SuppressWarnings("serial")
public class ResultExamineeAction extends BaseAction {
	private static Logger logger = Logger.getLogger(ResultExamineeAction.class);
	private ResultExamineeServiceImpl resultExamineeServiceImpl = null;
	private List<String[]> resultList = null;
	private List<ExamPlaceVo> examPlaceList = null;
	private String subject;
	private int interval = 5;
	private ExamPlaceVo examPlaceVo = null;
	private int beginRoom;
	private int endRoom;
	private List<ResultExamineeExitVo> resultExamineeVoList = null;
	private List<List<ResultExamineeExitVo>> resultExamineeVoQList = null;
	private int groupField = 1;
	private List<String> verifyResultList = null;
	private String licence = null;
	private String relativePath; // 相对路径
	private String savePath;
	private long roomCbtId;
    private List<SubjectVo> subjectList;
    HttpServletResponse response = ServletActionContext.getResponse();
    private String excelFilePath;
 	/**
	 * 
	 * @return
	 */
	public String findQRoomByPlace() {
		String examAreaId = this.getLoginUser().getAreaId();
		long userExamPlaceId = this.getLoginUser().getExamPlaceId();
		this.examPlaceList = this.getResultExamineeServiceImpl()
				.findExamPlaceList(examAreaId,userExamPlaceId);
		this.subjectList = this.getResultExamineeServiceImpl().findSubject();
		if (examPlaceList.size() == 1) {
			long examPlaceId = examPlaceList.get(0).getExamPlace();
			if (StringUtils.isBlank(subject)) {
				subject = "1";
			}
			this.resultList = this.getResultExamineeServiceImpl()
					.findQRoomByPlace(subject, examPlaceId, interval);
			examPlaceVo = examPlaceList.get(0);
			examPlaceList = null;
		} else {
			if (examPlaceVo != null) {
				long examPlaceId = examPlaceVo.getExamPlace();
				examPlaceVo = this.getResultExamineeServiceImpl()
						.findExamPlaceById(examPlaceId);
				this.resultList = this.getResultExamineeServiceImpl()
						.findQRoomByPlace(subject, examPlaceId, interval);
			}
		}
		if (resultList != null && !resultList.isEmpty()
				&& resultList.get(0).length == 4) {
			return "examcbt";
		} else {
			return SUCCESS;
		}
	}

	/**
	 * 
	 * @param subject
	 * @param examPlaceId
	 * @param beginRoom
	 * @param endRoom
	 * @return
	 */
	public String findQResultExaminee() {
		long examPlaceId = examPlaceVo.getExamPlace();

		if (subject.equals("1")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExaminee1(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("2")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExaminee2(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("3")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExaminee3(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("4")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExaminee4(subject, examPlaceId, beginRoom,
							endRoom);
		}
		else if (subject.equals("5")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExaminee5(subject, examPlaceId, beginRoom,
							endRoom);
		}
		else if (subject.equals("6")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExaminee6(subject, examPlaceId, beginRoom,
							endRoom);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @param subject
	 * @param examPlaceId
	 * @param beginRoom
	 * @param endRoom
	 * @return
	 */
	public String findQResultExamineeCbt() {
		long examPlaceId = examPlaceVo.getExamPlace();

		if (subject.equals("1")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExamineeCbt1(examPlaceId, this.getRoomCbtId());
		} else if (subject.equals("2")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExamineeCbt2(examPlaceId, this.getRoomCbtId());
		} else if (subject.equals("3")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findQResultExamineeCbt3(examPlaceId, this.getRoomCbtId());
		}
		return SUCCESS;
	}

	public String modifyQResultExaminee() {
		for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
			if (resultExamineeVo.getUpload() == null) {
				continue;
			}
			if (!resultExamineeVo.getUploadFileName().toLowerCase()
					.endsWith("jpg")) {
				alertException("系统只允许上传jpg格式的文件类型！");
				//throw new RuntimeException("系统只允许上传jpg格式的文件类型！");
			}
			if (resultExamineeVo.getUpload().length() > 5 * 1024 * 1024) {
				/*throw new RuntimeException(resultExamineeVo.getUploadFileName()
						+ "文件的大小不能超过5M!");*/
				alertException(resultExamineeVo.getUploadFileName()
						+ "文件的大小不能超过5M!");
			}
			try {
				String fileName = System.currentTimeMillis() + resultExamineeVo.getLicence() + ".jpg";
				FileOutputStream fos = new FileOutputStream(getSavePath()
						+ "\\" + subject + "\\" + fileName);
				
				FileInputStream fis = new FileInputStream(
						resultExamineeVo.getUpload());
				resultExamineeVo.setAbsolutePath(this.relativePath + subject
						+ "/" + fileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fis.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (subject.equals("1")) {
			this.getResultExamineeServiceImpl().modifyQResultExaminee1(
					resultExamineeVoList);
		} else if (subject.equals("2")) {
			this.getResultExamineeServiceImpl().modifyQResultExaminee2(
					resultExamineeVoList);
		} else if (subject.equals("3")) {
			this.getResultExamineeServiceImpl().modifyQResultExaminee3(
					resultExamineeVoList);
		} else if (subject.equals("4")) {
			this.getResultExamineeServiceImpl().modifyQResultExaminee4(
					resultExamineeVoList);
		}
		else if (subject.equals("5")) {
			this.getResultExamineeServiceImpl().modifyQResultExaminee5(
					resultExamineeVoList);
		}
		else if (subject.equals("6")) {
			this.getResultExamineeServiceImpl().modifyQResultExaminee6(
					resultExamineeVoList);
		}

		if (roomCbtId > 0) {
			String[][] params = {
					{ "beginRoom", beginRoom + "" },
					{ "endRoom", endRoom + "" },
					{ "examPlaceVo.examPlace", examPlaceVo.getExamPlace() + "" },
					{ "subject", subject },{ "roomCbtId", roomCbtId+"" } };
			alertForm("核查结果上报成功", "findQResultExamineeCbtForModify.action", params);
		} else {
			String[][] params = {
					{ "beginRoom", beginRoom + "" },
					{ "endRoom", endRoom + "" },
					{ "examPlaceVo.examPlace", examPlaceVo.getExamPlace() + "" },
					{ "subject", subject } };
			alertForm("核查结果上报成功", "findQResultExamineeForModify.action", params);
		}

		return null;
	}
	/**
	 * 导入考生信息
	 * @return
	 */
	public String importResultExaminee() {
		try {
			if (excelFilePath != null) {
				File file = new File(excelFilePath);
				// 将file转成multipartFile（如是springMVC接受可不转）
				FileInputStream inputStream = new FileInputStream(file);
		        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
		        /**
		         * 读取excel数据并导入
		         */
				Object[] backMsg = this.resultExamineeServiceImpl.importResultExaminee(multipartFile);
				if ((Boolean) backMsg[1]) {
					this.setAjaxBackDataMain(backMsg[0]);
				}else {
					this.setAjaxBackDataErrorMsg((String)backMsg[2]);
				}
			}else {
				this.setAjaxBackDataErrorMsg(PropertiesReader.getValue("read.file.fail"));
			}
		} catch (Exception e) {
			this.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	public String importExaminee() {
		
		try {
			Object[] backMsg = this.resultExamineeServiceImpl.importExaminee();
			if ((Boolean) backMsg[1]) {
				this.setAjaxBackDataMain(backMsg[0]);
			}else {
				this.setAjaxBackDataErrorMsg("同步失败："+ backMsg[2]);
			}
		} catch (Exception e) {
			this.setAjaxBackDataErrorMsg(e);
		}
		
		return SUCCESS;
		
	}
	public String sumTable() {
		
		try {
			Object[] backMsg = this.resultExamineeServiceImpl.sumTable();
			if ((Boolean) backMsg[1]) {
				this.setAjaxBackDataMain(backMsg[0]);
			}else {
				this.setAjaxBackDataErrorMsg("同步失败："+ backMsg[2]);
			}
		} catch (Exception e) {
			this.setAjaxBackDataErrorMsg(e);
		}
		
		return SUCCESS;
		
	}
	/**
	 * 
	 * @param subject
	 * @param examPlaceId
	 * @param beginRoom
	 * @param endRoom
	 * @return
	 */
	public String findQResultExamineeRpt() {
		long examPlaceId = examPlaceVo.getExamPlace();
		if (subject.equals("1")) {
			this.resultExamineeVoQList = this.getResultExamineeServiceImpl()
					.findQResultExamineeRpt1(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("2")) {
			this.resultExamineeVoQList = this.getResultExamineeServiceImpl()
					.findQResultExamineeRpt2(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("3")) {
			this.resultExamineeVoQList = this.getResultExamineeServiceImpl()
					.findQResultExamineeRpt3(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("4")) {
			this.resultExamineeVoQList = this.getResultExamineeServiceImpl()
					.findQResultExamineeRpt4(subject, examPlaceId, beginRoom,
							endRoom);
		}else if (subject.equals("5")) {
			this.resultExamineeVoQList = this.getResultExamineeServiceImpl()
					.findQResultExamineeRpt5(subject, examPlaceId, beginRoom,
							endRoom);
		}else if (subject.equals("6")) {
			this.resultExamineeVoQList = this.getResultExamineeServiceImpl()
					.findQResultExamineeRpt6(subject, examPlaceId, beginRoom,
							endRoom);
		}
		return SUCCESS;
	}

	public String findNRoomByPlace() {
		String examAreaId = this.getLoginUser().getAreaId();
		long userExamPlaceId = this.getLoginUser().getExamPlaceId();
		this.examPlaceList = this.getResultExamineeServiceImpl()
				.findExamPlaceList(examAreaId, userExamPlaceId);
		this.subjectList = this.getResultExamineeServiceImpl().findSubject();
		if (examPlaceList.size() == 1) {
			long examPlaceId = examPlaceList.get(0).getExamPlace();
			if (StringUtils.isBlank(subject)) {
				subject = "1";
			}
			this.resultList = this.getResultExamineeServiceImpl()
					.findNRoomByPlace(subject, examPlaceId, interval);
			examPlaceVo = examPlaceList.get(0);
			examPlaceList = null;
		} else {
			if (examPlaceVo != null) {
				long examPlaceId = examPlaceVo.getExamPlace();
				examPlaceVo = this.getResultExamineeServiceImpl()
						.findExamPlaceById(examPlaceId);
				this.resultList = this.getResultExamineeServiceImpl()
						.findNRoomByPlace(subject, examPlaceId, interval);
			}
		}
		if (resultList != null && !resultList.isEmpty()
				&& resultList.get(0).length == 4) {
			return "examcbt";
		} else {
			return SUCCESS;
		}

	}

	/**
	 * 
	 * @param subject
	 * @param examPlaceId
	 * @param beginRoom
	 * @param endRoom
	 * @return
	 */
	public String findNResultExaminee() {
		long examPlaceId = examPlaceVo.getExamPlace();
		if (subject.equals("1")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExaminee1(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("2")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExaminee2(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("3")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExaminee3(subject, examPlaceId, beginRoom,
							endRoom);
		} else if (subject.equals("4")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExaminee4(subject, examPlaceId, beginRoom,
							endRoom);
		}else if (subject.equals("5")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExaminee5(subject, examPlaceId, beginRoom,
							endRoom);
		}else if (subject.equals("6")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExaminee6(subject, examPlaceId, beginRoom,
							endRoom);
		}

		verifyResultList = com.sinotn.examsafety.service.impl.BaseCodeUtils
				.findverifyResultList();
		return SUCCESS;
	}

	public String findNResultExamineeCbt() {
		long examPlaceId = examPlaceVo.getExamPlace();
		if (subject.equals("1")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExamineeCbt1(examPlaceId, this.getRoomCbtId());
		} else if (subject.equals("2")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExamineeCbt2(examPlaceId, this.getRoomCbtId());
		} else if (subject.equals("3")) {
			this.resultExamineeVoList = this.getResultExamineeServiceImpl()
					.findNResultExamineeCbt3(examPlaceId, this.getRoomCbtId());
		}
		verifyResultList = com.sinotn.examsafety.service.impl.BaseCodeUtils
				.findverifyResultList();
		return SUCCESS;
	}

	/**
	 * 处理疑似人员
	 * 
	 * @return
	 */
	public String modifyNResultExaminee() {
		if (subject.equals("1")) {
			this.getResultExamineeServiceImpl().modifyNResultExaminee1(
					resultExamineeVoList);
		} else if (subject.equals("2")) {
			this.getResultExamineeServiceImpl().modifyNResultExaminee2(
					resultExamineeVoList);
		} else if (subject.equals("3")) {
			this.getResultExamineeServiceImpl().modifyNResultExaminee3(
					resultExamineeVoList);
		} else if (subject.equals("4")) {
			this.getResultExamineeServiceImpl().modifyNResultExaminee4(
					resultExamineeVoList);
		}

		if (roomCbtId > 0) {
			String[][] params = {
					{ "beginRoom", beginRoom + "" },
					{ "endRoom", endRoom + "" },
					{ "examPlaceVo.examPlace", examPlaceVo.getExamPlace() + "" },
					{ "subject", subject }, { "roomCbtId", roomCbtId + "" } };
			alertForm("核查结果上报成功", "findNResultExamineeCbtForModify.action",
					params);
		} else {
			String[][] params = {
					{ "beginRoom", beginRoom + "" },
					{ "endRoom", endRoom + "" },
					{ "examPlaceVo.examPlace", examPlaceVo.getExamPlace() + "" },
					{ "subject", subject } };
			alertForm("核查结果上报成功", "findNResultExamineeForModify.action", params);
		}
		return null;
	}

	/**
	 * 查看考生四个科目的详细信息
	 * 
	 * @return
	 */
	public String findResultExamineeList() {
		this.resultExamineeVoList = this.getResultExamineeServiceImpl()
				.findResultExamineeList(licence);
		String sessionAreaId = this.getLoginUser().getAreaId();
		String examArea = resultExamineeVoList.get(0).getExamArea();
		if(!examArea.startsWith(sessionAreaId)){
			return this.alertException("无权查询信息！");
		}
		else{
			return SUCCESS;
		}
		
	}
	/**
	 * 
	 * TODO 新增数据导出
	 * @auth <a href="mailto:15662327016@126.com">Libin</a>
	 * @date 2018年1月31日 下午3:20:51
	 */
	/*public void examineeExportExcel() {
		String flag = this.getRequest().getParameter("flag");
		try{
			boolean isProcess = false;
			String fileName;
			if (StringUtils.isNotBlank(flag) && "1".equals(flag)) {
				isProcess = true;
				fileName = System.currentTimeMillis()+"回校人员信息表.xls";
			}else {
				fileName = System.currentTimeMillis()+"缺勤人员信息表.xls";
			}
			ArrayList fieldData  = this.resultExamineeServiceImpl.listResultExamineeByIsProcess(isProcess);
			ArrayList<String> fieldName  = this.resultExamineeServiceImpl.getExcelName(isProcess);
			OutputStream os = this.getResponse().getOutputStream(); // 取得输出流
			fileName = FileUtils.encodeFilename(fileName, getRequest());
			this.getResponse().reset(); // 清空输出流
			this.getResponse().setContentType("application/vnd.ms-excel"); // 定义输出类型
			this.getResponse().setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
			ExcelFileGenerator xlsExportBean = new ExcelFileGenerator(fieldName, fieldData);
			xlsExportBean.expordExcel(os); // 调用生成Excel文件bean
			System.setOut(new PrintStream(os));
			os.flush();
			if (os != null){
		        os.close();
			}
		}catch(Exception e) {
			throw new RuntimeException("导出错误");
		}
	}*/
	
	
	public ResultExamineeServiceImpl getResultExamineeServiceImpl() {
		return resultExamineeServiceImpl;
	}

	public void setResultExamineeServiceImpl(
			ResultExamineeServiceImpl resultExamineeServiceImpl) {
		this.resultExamineeServiceImpl = resultExamineeServiceImpl;
	}

	public int getGroupField() {
		return groupField;
	}

	public void setGroupField(int groupField) {
		this.groupField = groupField;
	}

	public List<String[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<String[]> resultList) {
		this.resultList = resultList;
	}

	public List<ExamPlaceVo> getExamPlaceList() {
		return examPlaceList;
	}

	public void setExamPlaceList(List<ExamPlaceVo> examPlaceList) {
		this.examPlaceList = examPlaceList;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public ExamPlaceVo getExamPlaceVo() {
		return examPlaceVo;
	}

	public void setExamPlaceVo(ExamPlaceVo examPlaceVo) {
		this.examPlaceVo = examPlaceVo;
	}

	public int getBeginRoom() {
		return beginRoom;
	}

	public void setBeginRoom(int beginRoom) {
		this.beginRoom = beginRoom;
	}

	public int getEndRoom() {
		return endRoom;
	}

	public void setEndRoom(int endRoom) {
		this.endRoom = endRoom;
	}

	public List<ResultExamineeExitVo> getResultExamineeVoList() {
		return resultExamineeVoList;
	}

	public void setResultExamineeVoList(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		this.resultExamineeVoList = resultExamineeVoList;
	}

	public List<String> getVerifyResultList() {
		return verifyResultList;
	}

	public void setVerifyResultList(List<String> verifyResultList) {
		this.verifyResultList = verifyResultList;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public List<List<ResultExamineeExitVo>> getResultExamineeVoQList() {
		return resultExamineeVoQList;
	}

	public void setResultExamineeVoQList(
			List<List<ResultExamineeExitVo>> resultExamineeVoQList) {
		this.resultExamineeVoQList = resultExamineeVoQList;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public long getRoomCbtId() {
		return roomCbtId;
	}

	public void setRoomCbtId(long roomCbtId) {
		this.roomCbtId = roomCbtId;
	}

	public List<SubjectVo> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectVo> subjectList) {
		this.subjectList = subjectList;
	}
	


	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	/** 异步获取科目
	 * @return
	 */
	public void getSubjectData() {
		this.subjectList = this.getResultExamineeServiceImpl().findSubject();
		listToJson(subjectList);
		
	}
	/** 异步获取科目
	 * @return
	 */
	public void getExamPlaceData() {
		String examAreaId = super.getAreaId();
		long userExamPlaceId = this.getLoginUser().getExamPlaceId();
		List<ExamPlaceVo> examPlaceVoList = this.getResultExamineeServiceImpl().findExamPlaceList(examAreaId,userExamPlaceId);
		listToJson(examPlaceVoList);
		
	}
	
	/**
	 * List 转 JSON 的公共方法
	 * @param list
	 */
	public void listToJson(List list){
		response.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.println(json);
			out.flush();
		} catch (IOException e) {
			logger.error("ResultExamineeAction--line(579)--IOException:" + e.getMessage());
		} finally{
			out.close();
		}
		
	}
}
