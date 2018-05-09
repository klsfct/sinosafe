package com.sinotn.examsafety.action;

import java.util.Date;
import java.util.List;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.service.impl.BaseCodeServiceImpl;
import com.sinotn.examsafety.service.impl.BaseCodeUtils;
import com.sinotn.examsafety.service.impl.ResultExamSumServiceImpl;
import com.sinotn.examsafety.service.impl.ResultExamineeServiceImpl;
import com.sinotn.examsafety.vo.BaseCodeVo;
import com.sinotn.examsafety.vo.CountByExamPlaceVo;
import com.sinotn.examsafety.vo.ExamPlaceVo;
import com.sinotn.examsafety.vo.GroupVo;
import com.sinotn.examsafety.vo.GxCountByExamPlace;
import com.sinotn.examsafety.vo.MonitorMapVo;
import com.sinotn.examsafety.vo.ShMonitorMapVo;
import com.sinotn.examsafety.vo.SubjectVo;

@SuppressWarnings("serial")
public class ResultExamSumAction extends BaseAction {
	private ResultExamSumServiceImpl resultExamSumServiceImpl = null;
	private ResultExamineeServiceImpl resultExamineeServiceImpl = null;
	private List<ExamPlaceVo> examPlaceList = null;
	private List<String[]> resultList = null;
	private ExamPlaceVo examPlaceVo = null;
	private String subject = "1";
	private int groupField = 1;
	private int examRoom = 0;
	
	private String areaName;
	private GroupVo groupVo = null;
	private MonitorMapVo monitorMapVo;
	
	private ShMonitorMapVo shMonitorMapVo; 
	
	private GxCountByExamPlace gxCountByExamPlace;
	
	private CountByExamPlaceVo countByExamPlaceVo;
	private BaseCodeServiceImpl baseCodeServiceImpl;
	private String cityCenterLng;
	private String cityCenterLat;
	
	private List<SubjectVo> subjectList;
	/**
	 * 广西小程序接口新增字段
	 */
	// 考区ID
	private String paramAreaId;
	// 考点ID
	private String paramPlaceId;
	/**
	 * 题卡统计
	 * 
	 * @return
	 */
	public String groupCardByArea() {
		String areaId = this.getAreaId();
		int level = this.getLevel();
		this.resultList = this.getResultExamSumServiceImpl().groupCardByArea(
				areaId, subject, level, groupField);
		return SUCCESS;
	}

	public String groupCardByRoom() {
		this.resultList = this.getResultExamSumServiceImpl()
				.groupCardByExamRoom(examPlaceVo.getExamPlace(), subject);
		return SUCCESS;
	}

	/**
	 * 修正题卡数
	 * 
	 * @return
	 */
	public String findCardByRoom() {
		String examAreaId = this.getLoginUser().getAreaId();
		long userExamPlaceId = this.getLoginUser().getExamPlaceId();
		this.examPlaceList = this.getResultExamineeServiceImpl()
				.findExamPlaceList(examAreaId, userExamPlaceId);
		if (examPlaceList.size() == 1) {
			long examPlaceId = examPlaceList.get(0).getExamPlace();
			this.resultList = this.getResultExamSumServiceImpl()
					.groupCardByExamRoom(examPlaceId, subject);
			examPlaceVo = examPlaceList.get(0);
			examPlaceList = null;
		} else {
			if (examPlaceVo != null) {
				long examPlaceId = examPlaceVo.getExamPlace();
				//examPlaceVo = this.getResultExamineeServiceImpl().findExamPlaceById(examPlaceId);
				this.resultList = this.getResultExamSumServiceImpl()
						.groupCardByExamRoom(examPlaceId, subject);
			}
		}
		return SUCCESS;
	}

	/**
	 * 修正题卡数
	 * 
	 * @param examPlaceId
	 * @param examRoom
	 * @return
	 */
	public String modifyCardByRoom() {
		this.getResultExamSumServiceImpl().modifyCardByRoom(subject,
				examPlaceVo.getExamPlace(), examRoom);
		return SUCCESS;
	}

	/**
	 * 统计参考率
	 * @return
	 */
	public String groupPassByArea() {
		String areaId = this.getAreaId();
		int level = this.getLevel();
		this.resultList = this.getResultExamSumServiceImpl().groupPassByArea(
				areaId, subject, level, groupField);
		return SUCCESS;
	}
	
	/**
	 * 统计识别率
	 * @return
	 */
	public String groupFaceByArea() {
		String areaId = this.getAreaId();
		int level = this.getLoginUser().getLevel();
		long examPlace = this.getLoginUser().getExamPlaceId();
		this.resultList = this.getResultExamSumServiceImpl().groupFaceByArea(
				areaId, subject, level, groupField, examPlace);
		return SUCCESS;
	}

	/**
	 * 跳转至全国监控界面
	 */
	public String gotoMonitorByArea(){
		//根据时间获取当前应显示科目
		if(null==subject){
			subject = this.getNowSubjectByDate();
		}
		return SUCCESS;
	}
	
	/**
	 * 全国监控界面
	 */
	public String monitorByArea(){
		try {
			monitorMapVo = this.getResultExamSumServiceImpl().monitorByArea(subject);
			super.setAjaxBackDataMain(monitorMapVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转至省监控界面
	 */
	public String gotoMonitorByProvince(){
		//根据时间获取当前应显示科目
		if(null==subject){
			subject = this.getNowSubjectByDate();
		}
		subject = "1";
		return SUCCESS;
	}
	
	/**
	 * 省监控界面
	 * @return
	 */
	public String monitorByProvince(){
		try {
			monitorMapVo = this.getResultExamSumServiceImpl().monitorByProvince(super.getAreaId(), subject);
			super.setAjaxBackDataMain(monitorMapVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	public String monitorByPlace(){
		String areaId = this.getAreaId();
		int level = Integer.parseInt(BaseCodeUtils.findBaseCodeById(areaId).getFlag());
		if(level > 1){
			this.resultList = this.getResultExamSumServiceImpl().monitorByPlace(areaId + "%", subject);
		}
		
		return SUCCESS;
	}
	
	public String gotoMonitorByCity(){
		paramAreaId = this.getLoginUser().getAreaId();
		areaName = this.getLoginUser().getAreaName();
		/**
		 * 根据登录用户ID查询城市中心经纬度
		 */
		BaseCodeVo  baseCodeVo = this.baseCodeServiceImpl.findBaseCodeById(paramAreaId);
		cityCenterLng = baseCodeVo.getCityCenterLng();
		cityCenterLat = baseCodeVo.getCityCenterLat();
		//科目
		this.subjectList = this.getResultExamineeServiceImpl().findSubject();
		this.getRequest().setAttribute("paramAreaId", paramAreaId);
		this.getRequest().setAttribute("areaName", areaName);
		this.getRequest().setAttribute("cityCenterLng", cityCenterLng);
		this.getRequest().setAttribute("cityCenterLat", cityCenterLat);
		this.getRequest().setAttribute("subjectList", subjectList);
		return SUCCESS;
	}
	
	/**
	 * 上海市获取地市地图统计数据
	 * @return
	 */
	public String findMonitorByCity(){
		try {
			if (null != paramAreaId && !"".equals(paramAreaId)) {
				shMonitorMapVo = this.getResultExamSumServiceImpl().shMonitorByCity(paramAreaId, subject);
			}else {
				throw new RuntimeException("您还没有登陆！"); 
			}
			super.setAjaxBackDataMain(shMonitorMapVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 上海地图题卡监控监控界面
	 * @return
	 */
	public String findMonitorByCard(){
		try {
			shMonitorMapVo = this.getResultExamSumServiceImpl().shMonitorByCard("AREA_1311", subject);
			super.setAjaxBackDataMain(shMonitorMapVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	//根据时间获取当前科目
	private String getNowSubjectByDate(){
		Date now = new Date();
		Date d1 = DateUtils.getStr2LDate("2017-09-16 12:00:00");
		Date d2 = DateUtils.getStr2LDate("2017-09-16 23:00:00");
		Date d3 = DateUtils.getStr2LDate("2017-09-17 12:00:00");
		Date d4 = DateUtils.getStr2LDate("2017-09-17 23:00:00");
		
		if(now.getTime()<d1.getTime()){
			return "1";
		}else if(now.getTime()<d2.getTime()){
			return "2";
		}else if(now.getTime()<d3.getTime()){
			return "3";
		}else{
			return "4";
		}
	}
	
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public ResultExamSumServiceImpl getResultExamSumServiceImpl() {
		return resultExamSumServiceImpl;
	}

	public void setResultExamSumServiceImpl(
			ResultExamSumServiceImpl resultExamSumServiceImpl) {
		this.resultExamSumServiceImpl = resultExamSumServiceImpl;
	}

	public List<String[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<String[]> resultList) {
		this.resultList = resultList;
	}

	public int getGroupField() {
		return groupField;
	}

	public void setGroupField(int groupField) {
		this.groupField = groupField;
	}

	public ResultExamineeServiceImpl getResultExamineeServiceImpl() {
		return resultExamineeServiceImpl;
	}

	public void setResultExamineeServiceImpl(
			ResultExamineeServiceImpl resultExamineeServiceImpl) {
		this.resultExamineeServiceImpl = resultExamineeServiceImpl;
	}

	public List<ExamPlaceVo> getExamPlaceList() {
		return examPlaceList;
	}

	public void setExamPlaceList(List<ExamPlaceVo> examPlaceList) {
		this.examPlaceList = examPlaceList;
	}

	public ExamPlaceVo getExamPlaceVo() {
		return examPlaceVo;
	}

	public void setExamPlaceVo(ExamPlaceVo examPlaceVo) {
		this.examPlaceVo = examPlaceVo;
	}

	public int getExamRoom() {
		return examRoom;
	}

	public void setExamRoom(int examRoom) {
		this.examRoom = examRoom;
	}

	public MonitorMapVo getMonitorMapVo() {
		return monitorMapVo;
	}

	public void setMonitorMapVo(MonitorMapVo monitorMapVo) {
		this.monitorMapVo = monitorMapVo;
	}

	public String getParamAreaId() {
		return paramAreaId;
	}

	public void setParamAreaId(String paramAreaId) {
		this.paramAreaId = paramAreaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public GroupVo getGroupVo() {
		return groupVo;
	}

	public void setGroupVo(GroupVo groupVo) {
		this.groupVo = groupVo;
	}
	
	public String getParamPlaceId() {
		return paramPlaceId;
	}

	public void setParamPlaceId(String paramPlaceId) {
		this.paramPlaceId = paramPlaceId;
	}

	public BaseCodeServiceImpl getBaseCodeServiceImpl() {
		return baseCodeServiceImpl;
	}

	public void setBaseCodeServiceImpl(BaseCodeServiceImpl baseCodeServiceImpl) {
		this.baseCodeServiceImpl = baseCodeServiceImpl;
	}

	public String getCityCenterLng() {
		return cityCenterLng;
	}

	public void setCityCenterLng(String cityCenterLng) {
		this.cityCenterLng = cityCenterLng;
	}

	public String getCityCenterLat() {
		return cityCenterLat;
	}

	public void setCityCenterLat(String cityCenterLat) {
		this.cityCenterLat = cityCenterLat;
	}

	public List<SubjectVo> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectVo> subjectList) {
		this.subjectList = subjectList;
	}

	/******************************** 广西小程序统计接口  ******************************************/
	/**
	 * 按考点分科目统计报名人数和参考人数
	 * @return
	 */
	public String countSubjectByExamPlace(){
		try {
			gxCountByExamPlace = this.getResultExamSumServiceImpl().countSubjectByExamPlace(paramPlaceId, paramAreaId);
			super.setAjaxBackDataMain(gxCountByExamPlace);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 根据考点，考区统计各科目的通过人数
	 * 考点和考区参数只传一个
	 * 传考区，统计该考区下所有的通过人数
	 * 传考点，统计该考点下所有的通过人数
	 * @return
	 */
	public String countByExamData(){
		try{
			countByExamPlaceVo = this.getResultExamSumServiceImpl().countByExamData(paramPlaceId, paramAreaId, subject);
			super.setAjaxBackDataMain(countByExamPlaceVo);
		}catch(Exception e){
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	
}
