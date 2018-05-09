package com.sinotn.examsafety.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.POIUtils;
import com.sinotn.common.utils.PropertiesReader;
import com.sinotn.common.utils.StringUtils;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.dao.ExamineeDAO;
import com.sinotn.examsafety.dao.ResultExamSumDAO;
import com.sinotn.examsafety.dao.ResultExaminee1DAO;
import com.sinotn.examsafety.dao.ResultExaminee2DAO;
import com.sinotn.examsafety.dao.ResultExaminee3DAO;
import com.sinotn.examsafety.dao.ResultExaminee4DAO;
import com.sinotn.examsafety.dao.ResultExaminee5DAO;
import com.sinotn.examsafety.dao.ResultExaminee6DAO;
import com.sinotn.examsafety.dao.SubjectDAO;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.Examinee;
import com.sinotn.examsafety.po.ResultExamSum;
import com.sinotn.examsafety.po.ResultExaminee1;
import com.sinotn.examsafety.po.ResultExaminee2;
import com.sinotn.examsafety.po.ResultExaminee3;
import com.sinotn.examsafety.po.ResultExaminee4;
import com.sinotn.examsafety.po.ResultExaminee5;
import com.sinotn.examsafety.po.ResultExaminee6;
import com.sinotn.examsafety.po.Subject;
import com.sinotn.examsafety.vo.ExamPlaceVo;
import com.sinotn.examsafety.vo.ResultExamineeExitVo;
import com.sinotn.examsafety.vo.SubjectVo;
public class ResultExamineeServiceImpl {
	private static Logger logger = Logger.getLogger(ResultExamineeServiceImpl.class);
	private ResultExaminee1DAO resultExaminee1DAO = null;
	private ResultExaminee2DAO resultExaminee2DAO = null;
	private ResultExaminee3DAO resultExaminee3DAO = null;
	private ResultExaminee4DAO resultExaminee4DAO = null;
	private ResultExaminee5DAO resultExaminee5DAO = null;
	private ResultExaminee6DAO resultExaminee6DAO = null;
	private ExamineeDAO examineeDAO = null;
	private ExamPlaceDAO examPlaceDAO = null;
	private ResultExamSumDAO resultExamSumDAO = null;
	private SubjectDAO subjectDAO;
	private static final String[] SUBJECT_ARR = new String[]{"1","2","3","4","5","6"};
	private static final int SUM_INIT_DATA = 0;
	
	/*public ArrayList<String> getExcelName(boolean isProess) {
		ArrayList<String> rsList = new ArrayList();
		if (isProess) {
			String[] strArray = {"院系名称","姓名","性别","学号","一级学科代码","一级学科名称","专业代码","专业名称","报到时间"};
				for (int i = 0; i < strArray.length; i++) {
					rsList.add(strArray[i]);
			}
		}else {
			String[] strArray = {"院系名称","姓名","性别","学号","一级学科代码","一级学科名称","专业代码","专业名称"};
				for (int i = 0; i < strArray.length; i++) {
					rsList.add(strArray[i]);
			}
		}
		return rsList;
	}*/
	
	/*public ArrayList listResultExamineeByIsProcess(boolean isProess) {
		List<ResultExaminee1> resultExaminee1List = this.resultExaminee1DAO.findByProperty("isProcess", isProess);
		ArrayList dataList = new ArrayList();
		if (null != resultExaminee1List && resultExaminee1List.size()>0) {
			for (ResultExaminee1 resultExaminee1 : resultExaminee1List) {
				List tempList=new ArrayList();
				tempList.add(resultExaminee1.getCollegeName());
				tempList.add(resultExaminee1.getExamineeName());
				tempList.add(resultExaminee1.getExamineeSex());
				tempList.add(resultExaminee1.getLicence());
				tempList.add(resultExaminee1.getSubjectCode());
				tempList.add(resultExaminee1.getSubjectName());
				tempList.add(resultExaminee1.getSpecialtyCode());
				tempList.add(resultExaminee1.getSpecialtyName());
				if (isProess) {
					
					tempList.add(DateUtils.getDate2SStr(resultExaminee1.getCreateDate()));
				}
				dataList.add(tempList);
			}
			return dataList;
		}
		return dataList;
	}*/
	public ExamPlaceVo findExamPlaceById(long examPlaceId) {
		ExamPlace examPlace = this.getExamPlaceDAO()
				.findObjectById(examPlaceId);
		if (examPlace != null) {
			return new ExamPlaceVo(examPlace);
		} else {
			return null;
		}
	}
	/**
	 * 根据考区获得考点列表
	 * 
	 * @param examAreaId
	 * @return
	 */
	public List<ExamPlaceVo> findExamPlaceList(String examAreaId, long examPlaceId) {
		List<ExamPlace> examPlaceList = this.getExamPlaceDAO()
				.findExamPlaceList(examAreaId, examPlaceId);
		List<ExamPlaceVo> examPlaceVoList = new ArrayList<ExamPlaceVo>();
		ExamPlaceVo examPlaceVo = null;
		for (ExamPlace temp : examPlaceList) {
			examPlaceVo = new ExamPlaceVo(temp);
			examPlaceVoList.add(examPlaceVo);
		}
		return examPlaceVoList;
	}

	/**
	 * 根据缺考考点和考场间隔个数获得考场列表，提供打印选择
	 * 
	 * @param examPlaceId
	 * @param findRoomByPlace
	 */
	public List<String[]> findQRoomByPlace(String subject, long examPlaceId,
			int interval) {
		ExamPlace examPlace = this.getExamPlaceDAO()
				.findObjectById(examPlaceId);
		String examArea = examPlace.getExamArea();
		List<?> list = new ArrayList<Object>();
		list = this.getResultExaminee1DAO().findQRoomByPlace(subject,
				examPlaceId);
		return findRoomByPlace(list, interval);
		/*if (subject.equals("4")) {
			list = this.getResultExaminee1DAO().findQRoomByPlace(subject,
					examPlaceId);
			return findRoomByPlace(list, interval);
		} else {
			
			if (examArea.equals("AREA_2306") || examArea.equals("AREA_3205")
					|| examArea.equals("AREA_4500")
					|| examArea.equals("AREA_5009")
					|| examArea.equals("AREA_6402")) {
				list = this.getResultExaminee1DAO().findQRoomByPlaceCbt(
						subject, examPlaceId);
				List<String[]> resultList = new ArrayList<String[]>();
				String[] result = null;
				Object[] value = null;
				for (Object object : list) {
					result = new String[4];
					value = (Object[]) object;
					result[0] = ((Long) value[0]).toString();
					result[1] = (String) value[1];
					result[2] = ((Long) value[2]).toString();
					resultList.add(result);
				}
				return resultList;
			} else {
				list = this.getResultExaminee1DAO().findQRoomByPlace(subject,
						examPlaceId);
				return findRoomByPlace(list, interval);
			}
		}*/
	}

	/**
	 * 缺考打印
	 * 
	 * @param subject
	 * @param examPlaceId
	 * @param beginRoom
	 * @param endRoom
	 * @return
	 */
	public List<List<ResultExamineeExitVo>> findQResultExamineeRpt1(
			String subject, long examPlaceId, int beginRoom, int endRoom) {
		List<List<ResultExamineeExitVo>> result = new ArrayList<List<ResultExamineeExitVo>>();
		for (int i = beginRoom; i <= endRoom; i++) {
			List<ResultExaminee1> list = this.getResultExaminee1DAO()
					.findQResultExamineeRpt(subject, examPlaceId, i);
			if (list.size() > 0) {
				List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
				ResultExamineeExitVo resultExamineeVo = null;
				for (ResultExaminee1 temp : list) {
					resultExamineeVo = new ResultExamineeExitVo();
					resultExamineeVo.setExamAreaName(temp.getExamAreaName());
					resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
					resultExamineeVo.setExamRoom(temp.getExamRoom());
					resultExamineeVo.setSeatNumber(temp.getSeatNumber());
					resultExamineeVo.setExamineeName(temp.getExamineeName());
					resultExamineeVo.setLicence(temp.getLicence());
					resultExamineeVo.setIdentity(temp.getIdentity());
					resultExamineeVo.setSubject(temp.getSubject());
					resultExamineeVo.setPhotoPath(temp.getPhotoPath());
					resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
					voList.add(resultExamineeVo);
				}
				result.add(voList);
			}
		}
		return result;
	}

	public List<List<ResultExamineeExitVo>> findQResultExamineeRpt2(
			String subject, long examPlaceId, int beginRoom, int endRoom) {
		List<List<ResultExamineeExitVo>> result = new ArrayList<List<ResultExamineeExitVo>>();
		for (int i = beginRoom; i <= endRoom; i++) {
			List<ResultExaminee2> list = this.getResultExaminee2DAO()
					.findQResultExamineeRpt(subject, examPlaceId, i);
			if (list.size() > 0) {
				List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
				ResultExamineeExitVo resultExamineeVo = null;
				for (ResultExaminee2 temp : list) {
					resultExamineeVo = new ResultExamineeExitVo();
					resultExamineeVo.setExamAreaName(temp.getExamAreaName());
					resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
					resultExamineeVo.setExamRoom(temp.getExamRoom());
					resultExamineeVo.setSeatNumber(temp.getSeatNumber());
					resultExamineeVo.setExamineeName(temp.getExamineeName());
					resultExamineeVo.setLicence(temp.getLicence());
					resultExamineeVo.setIdentity(temp.getIdentity());
					resultExamineeVo.setSubject(temp.getSubject());
					resultExamineeVo.setPhotoPath(temp.getPhotoPath());
					resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
					voList.add(resultExamineeVo);
				}
				result.add(voList);
			}
		}
		return result;
	}

	public List<List<ResultExamineeExitVo>> findQResultExamineeRpt3(
			String subject, long examPlaceId, int beginRoom, int endRoom) {
		List<List<ResultExamineeExitVo>> result = new ArrayList<List<ResultExamineeExitVo>>();
		for (int i = beginRoom; i <= endRoom; i++) {
			List<ResultExaminee3> list = this.getResultExaminee3DAO()
					.findQResultExamineeRpt(subject, examPlaceId, i);
			if (list.size() > 0) {
				List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
				ResultExamineeExitVo resultExamineeVo = null;
				for (ResultExaminee3 temp : list) {
					resultExamineeVo = new ResultExamineeExitVo();
					resultExamineeVo.setExamAreaName(temp.getExamAreaName());
					resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
					resultExamineeVo.setExamRoom(temp.getExamRoom());
					resultExamineeVo.setSeatNumber(temp.getSeatNumber());
					resultExamineeVo.setExamineeName(temp.getExamineeName());
					resultExamineeVo.setLicence(temp.getLicence());
					resultExamineeVo.setIdentity(temp.getIdentity());
					resultExamineeVo.setSubject(temp.getSubject());
					resultExamineeVo.setPhotoPath(temp.getPhotoPath());
					resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
					voList.add(resultExamineeVo);
				}
				result.add(voList);
			}
		}
		return result;
	}

	public List<List<ResultExamineeExitVo>> findQResultExamineeRpt4(
			String subject, long examPlaceId, int beginRoom, int endRoom) {
		List<List<ResultExamineeExitVo>> result = new ArrayList<List<ResultExamineeExitVo>>();
		for (int i = beginRoom; i <= endRoom; i++) {
			List<ResultExaminee4> list = this.getResultExaminee4DAO()
					.findQResultExamineeRpt(subject, examPlaceId, i);
			if (list.size() > 0) {
				List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
				ResultExamineeExitVo resultExamineeVo = null;
				for (ResultExaminee4 temp : list) {
					resultExamineeVo = new ResultExamineeExitVo();
					resultExamineeVo.setExamAreaName(temp.getExamAreaName());
					resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
					resultExamineeVo.setExamRoom(temp.getExamRoom());
					resultExamineeVo.setSeatNumber(temp.getSeatNumber());
					resultExamineeVo.setExamineeName(temp.getExamineeName());
					resultExamineeVo.setLicence(temp.getLicence());
					resultExamineeVo.setIdentity(temp.getIdentity());
					resultExamineeVo.setSubject(temp.getSubject());
					resultExamineeVo.setPhotoPath(temp.getPhotoPath());
					resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
					voList.add(resultExamineeVo);
				}
				result.add(voList);
			}
		}
		return result;
	}
	public List<List<ResultExamineeExitVo>> findQResultExamineeRpt5(
			String subject, long examPlaceId, int beginRoom, int endRoom) {
		List<List<ResultExamineeExitVo>> result = new ArrayList<List<ResultExamineeExitVo>>();
		for (int i = beginRoom; i <= endRoom; i++) {
			List<ResultExaminee5> list = this.getResultExaminee5DAO()
					.findQResultExamineeRpt(subject, examPlaceId, i);
			if (list.size() > 0) {
				List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
				ResultExamineeExitVo resultExamineeVo = null;
				for (ResultExaminee5 temp : list) {
					resultExamineeVo = new ResultExamineeExitVo();
					resultExamineeVo.setExamAreaName(temp.getExamAreaName());
					resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
					resultExamineeVo.setExamRoom(temp.getExamRoom());
					resultExamineeVo.setSeatNumber(temp.getSeatNumber());
					resultExamineeVo.setExamineeName(temp.getExamineeName());
					resultExamineeVo.setLicence(temp.getLicence());
					resultExamineeVo.setIdentity(temp.getIdentity());
					resultExamineeVo.setSubject(temp.getSubject());
					resultExamineeVo.setPhotoPath(temp.getPhotoPath());
					resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
					voList.add(resultExamineeVo);
				}
				result.add(voList);
			}
		}
		return result;
	}public List<List<ResultExamineeExitVo>> findQResultExamineeRpt6(
			String subject, long examPlaceId, int beginRoom, int endRoom) {
		List<List<ResultExamineeExitVo>> result = new ArrayList<List<ResultExamineeExitVo>>();
		for (int i = beginRoom; i <= endRoom; i++) {
			List<ResultExaminee6> list = this.getResultExaminee6DAO()
					.findQResultExamineeRpt(subject, examPlaceId, i);
			if (list.size() > 0) {
				List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
				ResultExamineeExitVo resultExamineeVo = null;
				for (ResultExaminee6 temp : list) {
					resultExamineeVo = new ResultExamineeExitVo();
					resultExamineeVo.setExamAreaName(temp.getExamAreaName());
					resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
					resultExamineeVo.setExamRoom(temp.getExamRoom());
					resultExamineeVo.setSeatNumber(temp.getSeatNumber());
					resultExamineeVo.setExamineeName(temp.getExamineeName());
					resultExamineeVo.setLicence(temp.getLicence());
					resultExamineeVo.setIdentity(temp.getIdentity());
					resultExamineeVo.setSubject(temp.getSubject());
					resultExamineeVo.setPhotoPath(temp.getPhotoPath());
					resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
					voList.add(resultExamineeVo);
				}
				result.add(voList);
			}
		}
		return result;
	}
	public List<ResultExamineeExitVo> findQResultExaminee1(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee1> list = this.getResultExaminee1DAO()
				.findQResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee1 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findQResultExamineeCbt1(long examPlaceId,
			long examRoomCbt) {
		List<ResultExaminee1> list = this.getResultExaminee1DAO()
				.findQResultExamineeCbt(examPlaceId,examRoomCbt);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		// 考生
		Examinee examinee = null;
		for (ResultExaminee1 temp : list) {
			examinee = this.getExamineeDAO().findObjectById(temp.getLicence());
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(examinee.getExamRoomNameCbt());// 机考考场
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(examinee.getSeatNumberCbt());// 机考座位
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findQResultExaminee2(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee2> list = this.getResultExaminee2DAO()
				.findQResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee2 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findQResultExamineeCbt2(long examPlaceId,
			long examRoomCbt) {
		List<ResultExaminee2> list = this.getResultExaminee2DAO()
				.findQResultExamineeCbt(examPlaceId,examRoomCbt);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		// 考生
		Examinee examinee = null;
		for (ResultExaminee2 temp : list) {
			examinee = this.getExamineeDAO().findObjectById(temp.getLicence());
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(examinee.getExamRoomNameCbt());// 机考考场
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(examinee.getSeatNumberCbt());// 机考座位
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findQResultExaminee3(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee3> list = this.getResultExaminee3DAO()
				.findQResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee3 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}
	public List<ResultExamineeExitVo> findQResultExamineeCbt3(long examPlaceId,
			long examRoomCbt) {
		List<ResultExaminee3> list = this.getResultExaminee3DAO()
				.findQResultExamineeCbt(examPlaceId,examRoomCbt);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		// 考生
		Examinee examinee = null;
		for (ResultExaminee3 temp : list) {
			examinee = this.getExamineeDAO().findObjectById(temp.getLicence());
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(examinee.getExamRoomNameCbt());// 机考考场
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(examinee.getSeatNumberCbt());// 机考座位
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findQResultExaminee4(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee4> list = this.getResultExaminee4DAO()
				.findQResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee4 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	/**
	 * 根据疑似考点和考场间隔个数获得考场列表，提供打印选择
	 * 
	 * @param examPlaceId
	 * @param findRoomByPlace
	 */
	public List<String[]> findNRoomByPlace(String subject, long examPlaceId,
			int interval) {
		ExamPlace examPlace = this.getExamPlaceDAO()
				.findObjectById(examPlaceId);
		String examArea = examPlace.getExamArea();
		List<?> list = new ArrayList<Object>();
		list = this.getResultExaminee1DAO().findNRoomByPlace(subject,
				examPlaceId);
		return findRoomByPlace(list, interval);
		/*if (subject.equals("4")) {
			list = this.getResultExaminee1DAO().findNRoomByPlace(subject,
					examPlaceId);
			return findRoomByPlace(list, interval);
		} else {
			if (examArea.equals("AREA_2306") || examArea.equals("AREA_3205")
					|| examArea.equals("AREA_4505")
					|| examArea.equals("AREA_5009")
					|| examArea.equals("AREA_6402")) {
				list = this.getResultExaminee1DAO().findNRoomByPlaceCbt(
						subject, examPlaceId);
				List<String[]> resultList = new ArrayList<String[]>();
				String[] result = null;
				Object[] value = null;
				for (Object object : list) {
					result = new String[4];
					value = (Object[]) object;
					result[0] = ((Long) value[0]).toString();
					result[1] = (String) value[1];
					result[2] = ((Long) value[2]).toString();
					resultList.add(result);
				}
				return resultList;
			} else {
				list = this.getResultExaminee1DAO().findNRoomByPlace(subject,
						examPlaceId);
				return findRoomByPlace(list, interval);
			}
		}*/

	}

	/**
	 * 人脸识别未通过
	 * 
	 * @param subject
	 * @param examPlaceId
	 * @param beginRoom
	 * @param endRoom
	 * @return
	 */
	public List<ResultExamineeExitVo> findNResultExaminee1(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee1> list = this.getResultExaminee1DAO()
				.findNResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee1 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	// 机考信息
	public List<ResultExamineeExitVo> findNResultExamineeCbt1(long examPlaceId,
			long examRoomCbt) {

		List<ResultExaminee1> list = this.getResultExaminee1DAO()
				.findNResultExamineeCbt(examPlaceId, examRoomCbt);

		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		// 考生
		Examinee examinee = null;
		for (ResultExaminee1 temp : list) {
			examinee = this.getExamineeDAO().findObjectById(temp.getLicence());
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(examinee.getExamRoomNameCbt());// 机考考场
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(examinee.getSeatNumberCbt());// 机考座位
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findNResultExaminee2(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee2> list = this.getResultExaminee2DAO()
				.findNResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee2 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	// 机考信息
	public List<ResultExamineeExitVo> findNResultExamineeCbt2(long examPlaceId,
			long examRoomCbt) {

		List<ResultExaminee2> list = this.getResultExaminee2DAO()
				.findNResultExamineeCbt(examPlaceId, examRoomCbt);

		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		// 考生
		Examinee examinee = null;
		for (ResultExaminee2 temp : list) {
			examinee = this.getExamineeDAO().findObjectById(temp.getLicence());
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(examinee.getExamRoomNameCbt());// 机考考场
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(examinee.getSeatNumberCbt());// 机考座位
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findNResultExaminee3(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee3> list = this.getResultExaminee3DAO()
				.findNResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee3 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	// 机考信息
	public List<ResultExamineeExitVo> findNResultExamineeCbt3(long examPlaceId,
			long examRoomCbt) {

		List<ResultExaminee3> list = this.getResultExaminee3DAO()
				.findNResultExamineeCbt(examPlaceId, examRoomCbt);

		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		// 考生
		Examinee examinee = null;
		for (ResultExaminee3 temp : list) {
			examinee = this.getExamineeDAO().findObjectById(temp.getLicence());
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(examinee.getExamRoomNameCbt());// 机考考场
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(examinee.getSeatNumberCbt());// 机考座位
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}

	public List<ResultExamineeExitVo> findNResultExaminee4(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee4> list = this.getResultExaminee4DAO()
				.findNResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee4 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}
	public List<ResultExamineeExitVo> findNResultExaminee5(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee5> list = this.getResultExaminee5DAO()
				.findNResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee5 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}public List<ResultExamineeExitVo> findNResultExaminee6(String subject,
			long examPlaceId, int beginRoom, int endRoom) {
		List<ResultExaminee6> list = this.getResultExaminee6DAO()
				.findNResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee6 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setIsPass(temp.getIsPass());
			resultExamineeVo.setPhotoPrecision(temp.getPhotoPrecision());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setCreateDate(temp.getCreateDate());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setRelativePath(temp.getRelativePath());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			resultExamineeVo.setAbsolutePath(temp.getAbsolutePath());
			voList.add(resultExamineeVo);
		}
		return voList;
	}
	public void modifyNResultExaminee1(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee1 resultExaminee = null;
		int sum = 0;
		ResultExamSum resultExamSum = null;
		long examPlace = 0;
		String subject = null;
		if (resultExamineeVoList.size() > 0) {
			String licence = resultExamineeVoList.get(0).getLicence();
			resultExaminee = this.getResultExaminee1DAO().findObjectById(
					licence);
			examPlace = resultExaminee.getExamPlace();
			subject = resultExaminee.getSubject();

		}
		synchronized (BaseCodeUtils.getLockMap().get(examPlace)) {
			resultExamSum = this.getResultExamSumDAO().findResultExamSum(
					subject, examPlace);
			for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
				String licence = resultExamineeVo.getLicence();
				resultExaminee = this.getResultExaminee1DAO().findObjectById(
						licence);
				
				String verify = resultExamineeVo.getVerifyResult();
				
				if (!resultExaminee.getIsVerify()) {
					sum++;
				}
				
				resultExaminee.setVerifyResult(resultExamineeVo
						.getVerifyResult());
				resultExaminee.setIsVerify(true);
				resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
				this.getResultExaminee1DAO().update(resultExaminee);
			}
			resultExamSum.setPhotoSumNy(resultExamSum.getPhotoSumNy() + sum);
			resultExamSum.setPhotoSumNn(resultExamSum.getPhotoSumNn() - sum);
			this.getResultExamSumDAO().update(resultExamSum);
		}

	}

	public void modifyNResultExaminee2(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee2 resultExaminee = null;
		int sum = 0;
		ResultExamSum resultExamSum = null;
		long examPlace = 0;
		String subject = null;
		if (resultExamineeVoList.size() > 0) {
			String licence = resultExamineeVoList.get(0).getLicence();
			resultExaminee = this.getResultExaminee2DAO().findObjectById(
					licence);
			examPlace = resultExaminee.getExamPlace();
			subject = resultExaminee.getSubject();

		}
		synchronized (BaseCodeUtils.getLockMap().get(examPlace)) {
			resultExamSum = this.getResultExamSumDAO().findResultExamSum(
					subject, examPlace);
			for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
				String licence = resultExamineeVo.getLicence();
				resultExaminee = this.getResultExaminee2DAO().findObjectById(
						licence);
				if (!resultExaminee.getIsVerify()) {
					sum++;
				}
				resultExaminee.setVerifyResult(resultExamineeVo
						.getVerifyResult());
				resultExaminee.setIsVerify(true);
				resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
				this.getResultExaminee2DAO().update(resultExaminee);
			}
			resultExamSum.setPhotoSumNy(resultExamSum.getPhotoSumNy() + sum);
			resultExamSum.setPhotoSumNn(resultExamSum.getPhotoSumNn() - sum);
			this.getResultExamSumDAO().update(resultExamSum);
		}

	}

	public void modifyNResultExaminee3(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee3 resultExaminee = null;
		int sum = 0;
		ResultExamSum resultExamSum = null;
		long examPlace = 0;
		String subject = null;
		if (resultExamineeVoList.size() > 0) {
			String licence = resultExamineeVoList.get(0).getLicence();
			resultExaminee = this.getResultExaminee3DAO().findObjectById(
					licence);
			examPlace = resultExaminee.getExamPlace();
			subject = resultExaminee.getSubject();

		}
		synchronized (BaseCodeUtils.getLockMap().get(examPlace)) {
			resultExamSum = this.getResultExamSumDAO().findResultExamSum(
					subject, examPlace);
			for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
				String licence = resultExamineeVo.getLicence();
				resultExaminee = this.getResultExaminee3DAO().findObjectById(
						licence);
				if (!resultExaminee.getIsVerify()) {
					sum++;
				}
				resultExaminee.setVerifyResult(resultExamineeVo
						.getVerifyResult());
				resultExaminee.setIsVerify(true);
				resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
				this.getResultExaminee3DAO().update(resultExaminee);
			}
			resultExamSum.setPhotoSumNy(resultExamSum.getPhotoSumNy() + sum);
			resultExamSum.setPhotoSumNn(resultExamSum.getPhotoSumNn() - sum);
			this.getResultExamSumDAO().update(resultExamSum);
		}

	}

	public void modifyNResultExaminee4(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee4 resultExaminee = null;
		int sum = 0;
		ResultExamSum resultExamSum = null;
		long examPlace = 0;
		String subject = null;
		if (resultExamineeVoList.size() > 0) {
			String licence = resultExamineeVoList.get(0).getLicence();
			resultExaminee = this.getResultExaminee4DAO().findObjectById(
					licence);
			examPlace = resultExaminee.getExamPlace();
			subject = resultExaminee.getSubject();

		}
		synchronized (BaseCodeUtils.getLockMap().get(examPlace)) {
			resultExamSum = this.getResultExamSumDAO().findResultExamSum(
					subject, examPlace);
			for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
				String licence = resultExamineeVo.getLicence();
				resultExaminee = this.getResultExaminee4DAO().findObjectById(
						licence);
				if (!resultExaminee.getIsVerify()) {
					sum++;
				}
				resultExaminee.setVerifyResult(resultExamineeVo
						.getVerifyResult());
				resultExaminee.setIsVerify(true);
				resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
				this.getResultExaminee4DAO().update(resultExaminee);
			}
			resultExamSum.setPhotoSumNy(resultExamSum.getPhotoSumNy() + sum);
			resultExamSum.setPhotoSumNn(resultExamSum.getPhotoSumNn() - sum);
			this.getResultExamSumDAO().update(resultExamSum);
		}

	}

	public void modifyQResultExaminee1(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee1 resultExaminee = null;
		for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
			String licence = resultExamineeVo.getLicence();
			resultExaminee = this.getResultExaminee1DAO().findObjectById(
					licence);
			resultExaminee.setIsVerify(true);
			resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
			resultExaminee.setVerifyResult(resultExamineeVo.getVerifyResult());
			if (null == resultExaminee.getAbsolutePath()) {
				resultExaminee.setAbsolutePath(resultExamineeVo.getAbsolutePath());
			}
			
			this.getResultExaminee1DAO().update(resultExaminee);
		}
	}

	public void modifyQResultExaminee2(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee2 resultExaminee = null;
		for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
			String licence = resultExamineeVo.getLicence();
			resultExaminee = this.getResultExaminee2DAO().findObjectById(
					licence);
			resultExaminee.setIsVerify(true);
			resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
			resultExaminee.setVerifyResult(resultExamineeVo.getVerifyResult());
			if (null == resultExaminee.getAbsolutePath()) {
				resultExaminee.setAbsolutePath(resultExamineeVo.getAbsolutePath());
			}
			this.getResultExaminee2DAO().update(resultExaminee);
		}
	}

	public void modifyQResultExaminee3(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee3 resultExaminee = null;
		for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
			String licence = resultExamineeVo.getLicence();
			resultExaminee = this.getResultExaminee3DAO().findObjectById(
					licence);
			resultExaminee.setIsVerify(true);
			resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
			resultExaminee.setVerifyResult(resultExamineeVo.getVerifyResult());
			if (null == resultExaminee.getAbsolutePath()) {
				resultExaminee.setAbsolutePath(resultExamineeVo.getAbsolutePath());
			}
			this.getResultExaminee3DAO().update(resultExaminee);
		}
	}

	public void modifyQResultExaminee4(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee4 resultExaminee = null;
		for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
			String licence = resultExamineeVo.getLicence();
			resultExaminee = this.getResultExaminee4DAO().findObjectById(
					licence);
			resultExaminee.setIsVerify(true);
			resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
			resultExaminee.setVerifyResult(resultExamineeVo.getVerifyResult());
			if (null == resultExaminee.getAbsolutePath()) {
				resultExaminee.setAbsolutePath(resultExamineeVo.getAbsolutePath());
			}
			this.getResultExaminee4DAO().update(resultExaminee);
		}
	}
	public void modifyQResultExaminee5(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee5 resultExaminee = null;
		for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
			String licence = resultExamineeVo.getLicence();
			resultExaminee = this.getResultExaminee5DAO().findObjectById(
					licence);
			resultExaminee.setIsVerify(true);
			resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
			resultExaminee.setVerifyResult(resultExamineeVo.getVerifyResult());
			if (null == resultExaminee.getAbsolutePath()) {
				resultExaminee.setAbsolutePath(resultExamineeVo.getAbsolutePath());
			}
			this.getResultExaminee5DAO().update(resultExaminee);
		}
	}
	public void modifyQResultExaminee6(
			List<ResultExamineeExitVo> resultExamineeVoList) {
		ResultExaminee6 resultExaminee = null;
		for (ResultExamineeExitVo resultExamineeVo : resultExamineeVoList) {
			String licence = resultExamineeVo.getLicence();
			resultExaminee = this.getResultExaminee6DAO().findObjectById(
					licence);
			resultExaminee.setIsVerify(true);
			resultExaminee.setVerifyDate(DateUtils.getCurrentDate());
			resultExaminee.setVerifyResult(resultExamineeVo.getVerifyResult());
			if (null == resultExaminee.getAbsolutePath()) {
				resultExaminee.setAbsolutePath(resultExamineeVo.getAbsolutePath());
			}
			this.getResultExaminee6DAO().update(resultExaminee);
		}
	}

	public List<ResultExamineeExitVo> findResultExamineeList(String licence) {
		List<ResultExamineeExitVo> resultExamineeVoList = new ArrayList<ResultExamineeExitVo>();
		Examinee examinee = this.getExamineeDAO().findObjectById(licence);
		ResultExaminee1 resultExaminee1 = this.getResultExaminee1DAO()
				.getResultExaminee(licence);
		ResultExaminee2 resultExaminee2 = this.getResultExaminee2DAO()
				.getResultExaminee(licence);
		ResultExaminee3 resultExaminee3 = this.getResultExaminee3DAO()
				.getResultExaminee(licence);
		ResultExaminee4 resultExaminee4 = this.getResultExaminee4DAO()
				.getResultExaminee(licence);
		ResultExaminee5 resultExaminee5 = this.getResultExaminee5DAO()
				.getResultExaminee(licence);
		ResultExaminee6 resultExaminee6 = this.getResultExaminee6DAO()
				.getResultExaminee(licence);

		//ResultExamineeExitVo vo1 = new ResultExamineeExitVo();
		if(null!=resultExaminee1){
			ResultExamineeExitVo vo1 = new ResultExamineeExitVo();
			vo1.setExamArea(examinee.getExamArea());
			vo1.setExamineeName(examinee.getExamineeName());
			vo1.setSex(examinee.getSex());
			vo1.setPhotoPath(examinee.getPhotoPath());
			vo1.setIdentity(examinee.getIdentity());
			vo1.setExamAreaName(examinee.getExamAreaName());
			vo1.setExamPlaceName(examinee.getExamPlaceName());
			vo1.setLicence(examinee.getLicence());
			vo1.setExamRoom(examinee.getExamRoom());
			vo1.setSeatNumber(examinee.getSeatNumber());
	
			vo1.setSubject(resultExaminee1.getSubject());
			vo1.setIsPass(resultExaminee1.getIsPass());
			vo1.setCreateDate(resultExaminee1.getCreateDate());
			vo1.setIsVerify(resultExaminee1.getIsVerify());
			vo1.setVerifyDate(resultExaminee1.getVerifyDate());
			vo1.setVerifyResult(resultExaminee1.getVerifyResult());
			vo1.setAbsolutePath(resultExaminee1.getAbsolutePath());
			resultExamineeVoList.add(vo1);
		}
		//ResultExamineeExitVo vo2 = new ResultExamineeExitVo();
		if(null!=resultExaminee2){
			ResultExamineeExitVo vo2 = new ResultExamineeExitVo();
			vo2.setExamArea(examinee.getExamArea());
			vo2.setExamineeName(examinee.getExamineeName());
			vo2.setSex(examinee.getSex());
			vo2.setPhotoPath(examinee.getPhotoPath());
			vo2.setIdentity(examinee.getIdentity());
			vo2.setExamAreaName(examinee.getExamAreaName());
			vo2.setExamPlaceName(examinee.getExamPlaceName());
			vo2.setLicence(examinee.getLicence());
			vo2.setExamRoom(examinee.getExamRoom());
			vo2.setSeatNumber(examinee.getSeatNumber());
			
			vo2.setSubject(resultExaminee2.getSubject());
			vo2.setIsPass(resultExaminee2.getIsPass());
			vo2.setCreateDate(resultExaminee2.getCreateDate());
			vo2.setIsVerify(resultExaminee2.getIsVerify());
			vo2.setVerifyDate(resultExaminee2.getVerifyDate());
			vo2.setVerifyResult(resultExaminee2.getVerifyResult());
			vo2.setAbsolutePath(resultExaminee2.getAbsolutePath());
			resultExamineeVoList.add(vo2);
		}
		//ResultExamineeExitVo vo3 = new ResultExamineeExitVo();
		if(null!=resultExaminee3){
			ResultExamineeExitVo vo3 = new ResultExamineeExitVo();
			vo3.setExamArea(examinee.getExamArea());
			vo3.setExamineeName(examinee.getExamineeName());
			vo3.setSex(examinee.getSex());
			vo3.setPhotoPath(examinee.getPhotoPath());
			vo3.setIdentity(examinee.getIdentity());
			vo3.setExamAreaName(examinee.getExamAreaName());
			vo3.setExamPlaceName(examinee.getExamPlaceName());
			vo3.setLicence(examinee.getLicence());
			vo3.setExamRoom(examinee.getExamRoom());
			vo3.setSeatNumber(examinee.getSeatNumber());
			
			vo3.setSubject(resultExaminee3.getSubject());
			vo3.setIsPass(resultExaminee3.getIsPass());
			vo3.setCreateDate(resultExaminee3.getCreateDate());
			vo3.setIsVerify(resultExaminee3.getIsVerify());
			vo3.setVerifyDate(resultExaminee3.getVerifyDate());
			vo3.setVerifyResult(resultExaminee3.getVerifyResult());
			vo3.setAbsolutePath(resultExaminee3.getAbsolutePath());
			resultExamineeVoList.add(vo3);
		}

		//ResultExamineeExitVo vo4 = new ResultExamineeExitVo();
		if(null!=resultExaminee4){
			ResultExamineeExitVo vo4 = new ResultExamineeExitVo();
			vo4.setExamArea(examinee.getExamArea());
			vo4.setExamineeName(examinee.getExamineeName());
			vo4.setSex(examinee.getSex());
			vo4.setPhotoPath(examinee.getPhotoPath());
			vo4.setIdentity(examinee.getIdentity());
			vo4.setExamAreaName(examinee.getExamAreaName());
			vo4.setExamPlaceName(examinee.getExamPlaceName());
			vo4.setLicence(examinee.getLicence());
			vo4.setExamRoom(examinee.getExamRoom());
			vo4.setSeatNumber(examinee.getSeatNumber());
			
			vo4.setSubject(resultExaminee4.getSubject());
			vo4.setIsPass(resultExaminee4.getIsPass());
			vo4.setCreateDate(resultExaminee4.getCreateDate());
			vo4.setIsVerify(resultExaminee4.getIsVerify());
			vo4.setVerifyDate(resultExaminee4.getVerifyDate());
			vo4.setVerifyResult(resultExaminee4.getVerifyResult());
			vo4.setAbsolutePath(resultExaminee4.getAbsolutePath());
			resultExamineeVoList.add(vo4);
		}
		if(null!=resultExaminee5){
			ResultExamineeExitVo vo5 = new ResultExamineeExitVo();
			vo5.setExamArea(examinee.getExamArea());
			vo5.setExamineeName(examinee.getExamineeName());
			vo5.setSex(examinee.getSex());
			vo5.setPhotoPath(examinee.getPhotoPath());
			vo5.setIdentity(examinee.getIdentity());
			vo5.setExamAreaName(examinee.getExamAreaName());
			vo5.setExamPlaceName(examinee.getExamPlaceName());
			vo5.setLicence(examinee.getLicence());
			vo5.setExamRoom(examinee.getExamRoom());
			vo5.setSeatNumber(examinee.getSeatNumber());
			
			vo5.setSubject(resultExaminee5.getSubject());
			vo5.setIsPass(resultExaminee5.getIsPass());
			vo5.setCreateDate(resultExaminee5.getCreateDate());
			vo5.setIsVerify(resultExaminee5.getIsVerify());
			vo5.setVerifyDate(resultExaminee5.getVerifyDate());
			vo5.setVerifyResult(resultExaminee5.getVerifyResult());
			vo5.setAbsolutePath(resultExaminee5.getAbsolutePath());
			resultExamineeVoList.add(vo5);
		}
		if(null!=resultExaminee6){
			ResultExamineeExitVo vo6 = new ResultExamineeExitVo();
			vo6.setExamArea(examinee.getExamArea());
			vo6.setExamineeName(examinee.getExamineeName());
			vo6.setSex(examinee.getSex());
			vo6.setPhotoPath(examinee.getPhotoPath());
			vo6.setIdentity(examinee.getIdentity());
			vo6.setExamAreaName(examinee.getExamAreaName());
			vo6.setExamPlaceName(examinee.getExamPlaceName());
			vo6.setLicence(examinee.getLicence());
			vo6.setExamRoom(examinee.getExamRoom());
			vo6.setSeatNumber(examinee.getSeatNumber());
			
			vo6.setSubject(resultExaminee6.getSubject());
			vo6.setIsPass(resultExaminee6.getIsPass());
			vo6.setCreateDate(resultExaminee6.getCreateDate());
			vo6.setIsVerify(resultExaminee6.getIsVerify());
			vo6.setVerifyDate(resultExaminee6.getVerifyDate());
			vo6.setVerifyResult(resultExaminee6.getVerifyResult());
			vo6.setAbsolutePath(resultExaminee6.getAbsolutePath());
			resultExamineeVoList.add(vo6);
		}
		/*resultExamineeVoList.add(vo1);
		resultExamineeVoList.add(vo2);
		resultExamineeVoList.add(vo3);
		resultExamineeVoList.add(vo4);*/
		return resultExamineeVoList;
	}

	private List<String[]> findRoomByPlace(List<?> list, int interval) {
		List<String[]> resultList = new ArrayList<String[]>();
		if (list.size() == 0) {
			return resultList;
		}
		String[] result = null;
		Object[] value = null;
		int roomNo = 0;
		long roomExaminees = 0;
		int count = 0;
		int beginRoom = 0;
		for (Object object : list) {
			value = (Object[]) object;
			roomNo = (Integer) value[0];
			count++;
			roomExaminees = roomExaminees + (Long) value[1];
			if (count % interval == 1) {
				beginRoom = roomNo;
			} else if (count % interval == 0) {
				result = new String[3];
				if (interval == 1) {
					result[0] = Integer.toString(roomNo);
				} else {
					result[0] = Integer.toString(beginRoom);
				}
				result[1] = Integer.toString(roomNo);
				result[2] = Long.toString(roomExaminees);
				resultList.add(result);
				roomExaminees = 0;
				count = 0;
			}
		}
		if (count > 0) {
			result = new String[3];
			result[0] = Integer.toString(beginRoom);
			result[1] = Integer.toString(roomNo);
			result[2] = Long.toString(roomExaminees);
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * 
	 * @param list
	 * @param maxPos
	 * @return
	 */
	private List<String[]> convertStruct(List<?> list, int maxPos) {
		// 基础数据，包括合计数据
		List<String[]> baseDataList = new ArrayList<String[]>();
		// 基础数据长度
		int baseDatalength = 0;
		if (!list.isEmpty()) {
			baseDatalength = ((Object[]) list.get(0)).length;
		}
		// 循环数据库查询结果，构造基础数据列表
		Object[] value = null;
		String[] baseData = null;
		// 声明合计项
		int[] total = new int[baseDatalength];
		// 声明每行数据总数的最大项
		int max = 0;
		for (Object object : list) {
			value = (Object[]) object;
			baseData = new String[baseDatalength];
			for (int i = 0; i < baseDatalength; i++) {
				if (i < maxPos) {
					baseData[i] = (String) value[i];
				} else {
					baseData[i] = ((Integer) value[i]).toString();
				}
				// 类型数据计算合计项
				if (i >= maxPos) {
					total[i] = total[i] + (Integer) value[i];
				}
				// 关注所有数据总人数最大数，为了计算统计条宽度
				if (i == maxPos) {// 获取关注的最大值
					int tempMax = ((Integer) value[i]).intValue();
					if (tempMax > max) {
						max = tempMax;
					}
				}
			}
			baseDataList.add(baseData);
		}
		// 返回的统计结果，view层直接显示
		List<String[]> resultList = new ArrayList<String[]>();
		String[] result = null;
		DecimalFormat formate = new DecimalFormat("##.#%");
		for (String[] temp : baseDataList) {
			int j = -1;
			result = new String[baseDatalength * 2];
			for (int i = 0; i < temp.length; i++) {
				if (i < maxPos) {// 第一项是分组统计列名
					result[++j] = temp[i];
				} else if (i == maxPos) {// 小计总数项
					result[++j] = temp[i];
					if (total[i] == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(Double
								.parseDouble(temp[i]) / max);
					}

				} else {
					result[++j] = temp[i];
					if (Integer.parseInt(temp[maxPos]) == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(Double
								.parseDouble(temp[i]) / max);
					}

				}
			}
			if (max == 0) {
				result[j + 1] = formate.format(0);
			} else {
				result[j + 1] = formate.format(Double.parseDouble(temp[maxPos])
						/ max);
			}
			resultList.add(result);
		}
		// 插入合计项
		if (resultList.size() > 0) {
			result = new String[baseDatalength * 2];
			int j = -1;
			for (int i = 0; i < total.length; i++) {
				if (i < maxPos) {// 第一项是分组统计列名
					result[++j] = "合计";
				} else if (i == maxPos) {// 小计总数项
					result[++j] = Integer.toString(total[i]);
					if (total[i] == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(total[i] / total[i]);
					}

				} else {
					result[++j] = Integer.toString(total[i]);
					if (total[maxPos] == 0) {
						result[++j] = formate.format(0);
					} else {
						result[++j] = formate.format(total[i]
								/ (double) total[maxPos]);
					}
				}
			}
			if (max == 0) {
				result[j + 1] = formate.format(0);
			} else {
				result[j + 1] = formate.format(1);
			}
			resultList.add(result);
		}
		return resultList;
	}

	public ResultExamSumDAO getResultExamSumDAO() {
		return resultExamSumDAO;
	}

	public void setResultExamSumDAO(ResultExamSumDAO resultExamSumDAO) {
		this.resultExamSumDAO = resultExamSumDAO;
	}

	public ResultExaminee1DAO getResultExaminee1DAO() {
		return resultExaminee1DAO;
	}

	public void setResultExaminee1DAO(ResultExaminee1DAO resultExaminee1DAO) {
		this.resultExaminee1DAO = resultExaminee1DAO;
	}

	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}

	public ResultExaminee2DAO getResultExaminee2DAO() {
		return resultExaminee2DAO;
	}

	public void setResultExaminee2DAO(ResultExaminee2DAO resultExaminee2DAO) {
		this.resultExaminee2DAO = resultExaminee2DAO;
	}

	public ResultExaminee3DAO getResultExaminee3DAO() {
		return resultExaminee3DAO;
	}

	public void setResultExaminee3DAO(ResultExaminee3DAO resultExaminee3DAO) {
		this.resultExaminee3DAO = resultExaminee3DAO;
	}

	public ResultExaminee4DAO getResultExaminee4DAO() {
		return resultExaminee4DAO;
	}

	public void setResultExaminee4DAO(ResultExaminee4DAO resultExaminee4DAO) {
		this.resultExaminee4DAO = resultExaminee4DAO;
	}

	public ExamineeDAO getExamineeDAO() {
		return examineeDAO;
	}

	public void setExamineeDAO(ExamineeDAO examineeDAO) {
		this.examineeDAO = examineeDAO;
	}
	
	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

	public ResultExaminee5DAO getResultExaminee5DAO() {
		return resultExaminee5DAO;
	}

	public void setResultExaminee5DAO(ResultExaminee5DAO resultExaminee5DAO) {
		this.resultExaminee5DAO = resultExaminee5DAO;
	}

	public ResultExaminee6DAO getResultExaminee6DAO() {
		return resultExaminee6DAO;
	}

	public void setResultExaminee6DAO(ResultExaminee6DAO resultExaminee6DAO) {
		this.resultExaminee6DAO = resultExaminee6DAO;
	}

	/**
	 * 获取考试场次
	 * @return
	 */
	public List<SubjectVo> findSubject() {
		List<Subject> subjectList = this.subjectDAO.findSubjectList();
		List<SubjectVo> subjectVoList = new ArrayList<SubjectVo>();
		SubjectVo subjectVo = null;
		if (null != subjectList && subjectList.size()>0) {
			for (Subject subject : subjectList) {
				subjectVo = new SubjectVo(subject);
				subjectVoList.add(subjectVo);
			}
		}
		return subjectVoList;
	}

	public List<ResultExamineeExitVo> findQResultExaminee5(String subject,
			long examPlaceId, int beginRoom, int endRoom) {

		List<ResultExaminee5> list = this.getResultExaminee5DAO()
				.findQResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee5 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			voList.add(resultExamineeVo);
		}
		return voList;
	
	}
	public List<ResultExamineeExitVo> findQResultExaminee6(String subject,
			long examPlaceId, int beginRoom, int endRoom) {

		List<ResultExaminee6> list = this.getResultExaminee6DAO()
				.findQResultExaminee(subject, examPlaceId, beginRoom, endRoom);
		List<ResultExamineeExitVo> voList = new ArrayList<ResultExamineeExitVo>();
		ResultExamineeExitVo resultExamineeVo = null;
		for (ResultExaminee6 temp : list) {
			resultExamineeVo = new ResultExamineeExitVo();
			resultExamineeVo.setExamAreaName(temp.getExamAreaName());
			resultExamineeVo.setExamPlaceName(temp.getExamPlaceName());
			resultExamineeVo.setExamRoom(temp.getExamRoom());
			resultExamineeVo.setSeatNumber(temp.getSeatNumber());
			resultExamineeVo.setExamineeName(temp.getExamineeName());
			resultExamineeVo.setLicence(temp.getLicence());
			resultExamineeVo.setIdentity(temp.getIdentity());
			resultExamineeVo.setSubject(temp.getSubject());
			resultExamineeVo.setVerifyResult(temp.getVerifyResult());
			resultExamineeVo.setPhotoPath(temp.getPhotoPath());
			resultExamineeVo.setIsVerify(temp.getIsVerify());
			resultExamineeVo.setVerifyDate(temp.getVerifyDate());
			voList.add(resultExamineeVo);
		}
		return voList;
	
	}
	/**
	 * 读取excel数据，每一行为一个对象，批量保存
	 * @param multipartFile
	 * @return
	 */
	public Object[] importResultExaminee(MultipartFile multipartFile) {
		// 导入返回消息数组
		Object [] backMsgArr = new Object[3];
		// 是否导入成功标志
		boolean flag = false;
		// 导入返回消息
		String backMsg = "";
		
		try {
			//读取文件
			// 把Excel文件内容转换为一个ArrayList
			List arrayList = new ArrayList();
			arrayList = POIUtils.readExcel(multipartFile);
			// 判断文档是否有数据
			if (null != arrayList && arrayList.size()>0) {
				// 查询考点信息列表
				List<ExamPlace> examPlaceList = this.examPlaceDAO.findExamPlaceList();
				List<String> examPlaceNameList = new ArrayList<String>();
				// 检查考点是否存在
				if (null != examPlaceList && examPlaceList.size()>0) {
					for (ExamPlace examPlace : examPlaceList) {
						examPlaceNameList.add(examPlace.getExamPlaceName());
					}
					//保存
					for (int i = 0; i < arrayList.size(); i++) {
						String[] valStr = (String[]) arrayList.get(i);
							if ("1".equals(valStr[0])) {
								ResultExaminee1 resultExaminee = new ResultExaminee1();
								resultExaminee.setIsVerify(false);
								resultExaminee.setIsProcess(false);
								resultExaminee.setSubject(valStr[0].trim());
								resultExaminee.setLicence(valStr[1].trim());
								resultExaminee.setExamPlaceName(valStr[2].trim());
								resultExaminee.setExamRoom(Long.parseLong(valStr[3].trim()));
								int seatNumber = Integer.parseInt(valStr[4].trim());
								byte b = (byte) seatNumber;
								resultExaminee.setSeatNumber(b);
								resultExaminee.setExamineeName(valStr[5].trim());
								resultExaminee.setIdentity(valStr[6].trim());
								// 根据考点名称更新考点相应字段
								if (examPlaceNameList.contains(valStr[2])) {
									for (ExamPlace examPlace : examPlaceList) {
										if (valStr[2].equals(examPlace.getExamPlaceName())) {
											resultExaminee.setExamProvince(examPlace.getExamProvince());
											resultExaminee.setExamProvinceName(examPlace.getExamProvinceName());
											resultExaminee.setExamArea(examPlace.getExamArea());
											resultExaminee.setExamAreaName(examPlace.getExamAreaName());
											resultExaminee.setExamPlace(examPlace.getExamPlace());
											
										}
									}
								}else {
									throw new RuntimeException(PropertiesReader.getValue("examPlace.empty.examinee") + valStr[2] + "==" + valStr[6] );
								}
								
								this.getResultExaminee1DAO().saveObj(resultExaminee);
							}
							
							if ("2".equals(valStr[0])) {
								ResultExaminee2 resultExaminee = new ResultExaminee2();
								resultExaminee.setIsVerify(false);
								resultExaminee.setIsProcess(false);
								resultExaminee.setSubject(valStr[0].trim());
								resultExaminee.setLicence(valStr[1].trim());
								resultExaminee.setExamPlaceName(valStr[2].trim());
								resultExaminee.setExamRoom(Long.parseLong(valStr[3].trim()));
								int seatNumber = Integer.parseInt(valStr[4].trim());
								byte b = (byte) seatNumber;
								resultExaminee.setSeatNumber(b);
								resultExaminee.setExamineeName(valStr[5].trim());
								resultExaminee.setIdentity(valStr[6].trim());
								// 根据考点名称更新考点相应字段
								if (examPlaceNameList.contains(valStr[2])) {
									for (ExamPlace examPlace : examPlaceList) {
										if (valStr[2].equals(examPlace.getExamPlaceName())) {
											resultExaminee.setExamProvince(examPlace.getExamProvince());
											resultExaminee.setExamProvinceName(examPlace.getExamProvinceName());
											resultExaminee.setExamArea(examPlace.getExamArea());
											resultExaminee.setExamAreaName(examPlace.getExamAreaName());
											resultExaminee.setExamPlace(examPlace.getExamPlace());
											
										}
									}
								}else {
									throw new RuntimeException(PropertiesReader.getValue("examPlace.empty.examinee") + valStr[2] + "==" + valStr[6] );
								}
								
								this.getResultExaminee2DAO().saveObj(resultExaminee);
							}
							if ("3".equals(valStr[0])) {
								ResultExaminee3 resultExaminee = new ResultExaminee3();
								resultExaminee.setIsVerify(false);
								resultExaminee.setIsProcess(false);
								resultExaminee.setSubject(valStr[0].trim());
								resultExaminee.setLicence(valStr[1].trim());
								resultExaminee.setExamPlaceName(valStr[2].trim());
								resultExaminee.setExamRoom(Long.parseLong(valStr[3].trim()));
								int seatNumber = Integer.parseInt(valStr[4].trim());
								byte b = (byte) seatNumber;
								resultExaminee.setSeatNumber(b);
								resultExaminee.setExamineeName(valStr[5].trim());
								resultExaminee.setIdentity(valStr[6].trim());
								// 根据考点名称更新考点相应字段
								if (examPlaceNameList.contains(valStr[2])) {
									for (ExamPlace examPlace : examPlaceList) {
										if (valStr[2].equals(examPlace.getExamPlaceName())) {
											resultExaminee.setExamProvince(examPlace.getExamProvince());
											resultExaminee.setExamProvinceName(examPlace.getExamProvinceName());
											resultExaminee.setExamArea(examPlace.getExamArea());
											resultExaminee.setExamAreaName(examPlace.getExamAreaName());
											resultExaminee.setExamPlace(examPlace.getExamPlace());
											
										}
									}
								}else {
									throw new RuntimeException(PropertiesReader.getValue("examPlace.empty.examinee") + valStr[2] + "==" + valStr[6] );
								}
								
								this.getResultExaminee3DAO().saveObj(resultExaminee);
							}
							if ("4".equals(valStr[0])) {
								ResultExaminee4 resultExaminee = new ResultExaminee4();
								resultExaminee.setIsVerify(false);
								resultExaminee.setIsProcess(false);
								resultExaminee.setSubject(valStr[0].trim());
								resultExaminee.setLicence(valStr[1].trim());
								resultExaminee.setExamPlaceName(valStr[2].trim());
								resultExaminee.setExamRoom(Long.parseLong(valStr[3].trim()));
								int seatNumber = Integer.parseInt(valStr[4].trim());
								byte b = (byte) seatNumber;
								resultExaminee.setSeatNumber(b);
								resultExaminee.setExamineeName(valStr[5].trim());
								resultExaminee.setIdentity(valStr[6].trim());
								// 根据考点名称更新考点相应字段
								if (examPlaceNameList.contains(valStr[2])) {
									for (ExamPlace examPlace : examPlaceList) {
										if (valStr[2].equals(examPlace.getExamPlaceName())) {
											resultExaminee.setExamProvince(examPlace.getExamProvince());
											resultExaminee.setExamProvinceName(examPlace.getExamProvinceName());
											resultExaminee.setExamArea(examPlace.getExamArea());
											resultExaminee.setExamAreaName(examPlace.getExamAreaName());
											resultExaminee.setExamPlace(examPlace.getExamPlace());
											
										}
									}
								}else {
									throw new RuntimeException(PropertiesReader.getValue("examPlace.empty.examinee") + valStr[2] + "==" + valStr[6] );
								}
								
								this.getResultExaminee4DAO().saveObj(resultExaminee);
							}
							if ("5".equals(valStr[0])) {
								ResultExaminee5 resultExaminee = new ResultExaminee5();
								resultExaminee.setIsVerify(false);
								resultExaminee.setIsProcess(false);
								resultExaminee.setSubject(valStr[0].trim());
								resultExaminee.setLicence(valStr[1].trim());
								resultExaminee.setExamPlaceName(valStr[2].trim());
								resultExaminee.setExamRoom(Long.parseLong(valStr[3].trim()));
								int seatNumber = Integer.parseInt(valStr[4].trim());
								byte b = (byte) seatNumber;
								resultExaminee.setSeatNumber(b);
								resultExaminee.setExamineeName(valStr[5].trim());
								resultExaminee.setIdentity(valStr[6].trim());
								// 根据考点名称更新考点相应字段
								if (examPlaceNameList.contains(valStr[2])) {
									for (ExamPlace examPlace : examPlaceList) {
										if (valStr[2].equals(examPlace.getExamPlaceName())) {
											resultExaminee.setExamProvince(examPlace.getExamProvince());
											resultExaminee.setExamProvinceName(examPlace.getExamProvinceName());
											resultExaminee.setExamArea(examPlace.getExamArea());
											resultExaminee.setExamAreaName(examPlace.getExamAreaName());
											resultExaminee.setExamPlace(examPlace.getExamPlace());
											
										}
									}
								}else {
									throw new RuntimeException(PropertiesReader.getValue("examPlace.empty.examinee") + valStr[2] + "==" + valStr[6] );
								}
								
								this.getResultExaminee5DAO().saveObj(resultExaminee);
							}
							if ("6".equals(valStr[0])) {
								ResultExaminee6 resultExaminee = new ResultExaminee6();
								resultExaminee.setIsVerify(false);
								resultExaminee.setIsProcess(false);
								resultExaminee.setSubject(valStr[0].trim());
								resultExaminee.setLicence(valStr[1].trim());
								resultExaminee.setExamPlaceName(valStr[2].trim());
								resultExaminee.setExamRoom(Long.parseLong(valStr[3].trim()));
								int seatNumber = Integer.parseInt(valStr[4].trim());
								byte b = (byte) seatNumber;
								resultExaminee.setSeatNumber(b);
								resultExaminee.setExamineeName(valStr[5].trim());
								resultExaminee.setIdentity(valStr[6].trim());
								// 根据考点名称更新考点相应字段
								if (examPlaceNameList.contains(valStr[2])) {
									for (ExamPlace examPlace : examPlaceList) {
										if (valStr[2].equals(examPlace.getExamPlaceName())) {
											resultExaminee.setExamProvince(examPlace.getExamProvince());
											resultExaminee.setExamProvinceName(examPlace.getExamProvinceName());
											resultExaminee.setExamArea(examPlace.getExamArea());
											resultExaminee.setExamAreaName(examPlace.getExamAreaName());
											resultExaminee.setExamPlace(examPlace.getExamPlace());
											
										}
									}
								}else {
									throw new RuntimeException(PropertiesReader.getValue("examPlace.empty.examinee") + valStr[2] + "==" + valStr[6] );
								}
								
								this.getResultExaminee6DAO().saveObj(resultExaminee);
							}
						}
					backMsgArr[0] = arrayList.size();
					flag = true;
					backMsg = PropertiesReader.getValue("examinee.import.success");
				}else {
					backMsg = PropertiesReader.getValue("examPlace.empty");
				}
			}else {
				backMsg = PropertiesReader.getValue("examinee.excel.empty");
			}
		} catch (Exception e) {
			backMsg = PropertiesReader.getValue("import.exception.tips") + e.getMessage();
		} finally{
			backMsgArr[1] = flag;
			backMsgArr[2] = backMsg;
		}
		
		return backMsgArr;
	}
	public Object[] importExaminee() {
		Object [] backMsgArr = new Object[3];
		boolean flag = false;
		String backMsg = "";
		// 存放所有考生信息（含重复）
		List<Object> list = new ArrayList<Object>();
		// 存放所有考生信息（按照准考证号去重复）
		List<Object> finalList = new ArrayList<Object>();
		try {
			// 查询所有科目表考生信息
			List<?> examinee1List = this.resultExaminee1DAO.findResultExamineeListBySubject(SUBJECT_ARR[0]);
			List<?> examinee2List = this.resultExaminee1DAO.findResultExamineeListBySubject(SUBJECT_ARR[1]);
			List<?> examinee3List = this.resultExaminee1DAO.findResultExamineeListBySubject(SUBJECT_ARR[2]);
			List<?> examinee4List = this.resultExaminee1DAO.findResultExamineeListBySubject(SUBJECT_ARR[3]);
			List<?> examinee5List = this.resultExaminee1DAO.findResultExamineeListBySubject(SUBJECT_ARR[4]);
			List<?> examinee6List = this.resultExaminee1DAO.findResultExamineeListBySubject(SUBJECT_ARR[5]);
			// 将科目表考生信息遍历添加到总集合中
			if (null != examinee1List && examinee1List.size()>0) {
				for (Object object : examinee1List) {
					list.add(object);
				}
			}
			if (null != examinee2List && examinee2List.size()>0) {
				for (Object object : examinee2List) {
					list.add(object);
				}		
			}
			if (null != examinee3List && examinee3List.size()>0) {
				for (Object object : examinee3List) {
					list.add(object);
				}
			}
			if (null != examinee4List && examinee4List.size()>0) {
				for (Object object : examinee4List) {
					list.add(object);
				}
			}
			if (null != examinee5List && examinee5List.size()>0) {
				for (Object object : examinee5List) {
					list.add(object);
				}
			}
			if (null != examinee6List && examinee6List.size()>0) {
				for (Object object : examinee6List) {
					list.add(object);
				}
			}
			// 按照准考证号去重复，将已有字段值插入examinee表中
			HashMap<String, String> hashMap = new HashMap<String, String>();
			for (Object object : list) {
				if (null == object) {
	                continue;
	            }
				Object [] temp = (Object[]) object;
				String licence = (String) temp[0];
				String value = hashMap.get(licence);
				if (StringUtils.isBlank(value)) {
					hashMap.put(licence, licence);
					finalList.add(object);
				}else {
					continue;
				}
			}
			 hashMap.clear();
			 //System.out.println(finalList.size());
			 // 循环插入考生表
			 if (null != finalList && finalList.size()>0) {
				for (Object object : finalList) {
					Object [] temp = (Object[]) object;
					Examinee examinee = new Examinee();
					examinee.setLicence((String)temp[0]);
					examinee.setExamYear(String.valueOf(DateUtils.getYear()));
					examinee.setExamProvince((String)temp[1]);
					examinee.setExamProvinceName((String)temp[2]);
					examinee.setExamArea((String)temp[3]);
					examinee.setExamAreaName((String)temp[4]);
					examinee.setExamPlace(Long.valueOf(String.valueOf(temp[5])));
					examinee.setExamPlaceName((String)temp[6]);
					examinee.setExamRoom(Long.parseLong(String.valueOf(temp[7])));
					int seatNumber = Integer.parseInt(String.valueOf(temp[8]));
					byte b = (byte) seatNumber;
					examinee.setSeatNumber(b);
					examinee.setExamineeName(((String)temp[9]));
					examinee.setIdentity((String)temp[10]);
					String photoPath = (String) temp[11];
					if (!StringUtils.isBlank(photoPath)) {
						examinee.setPhotoPath(photoPath);
					}
					// 根据身份证号判断性别
					String sGender = "未知";
			        String sCardNum = ((String) temp[10]).substring(16, 17);
			        if (Integer.parseInt(sCardNum) % 2 != 0) {
			            sGender = "男";//男
			        } else {
			            sGender = "女";//女
			        }
			        examinee.setSex(sGender);
					this.examineeDAO.saveObj(examinee);
					examinee = null;
				}
			}
			backMsgArr [0] = finalList.size();
			flag = true;
			backMsg = PropertiesReader.getValue("examinee.sync.success");//
		} catch (Exception e) {
			backMsg = PropertiesReader.getValue("examinee.sync.exception") + e.getMessage();//
		}finally{
			backMsgArr[1] = flag;
			backMsgArr[2] = backMsg;
		}
		
		return backMsgArr;
		
	}
	public Object[] sumTable() {
		Object [] backMsgArr = new Object[3];
		boolean flag = false;
		String backMsg = "";
		// 存放所有考生统计信息
		List<Object> list = new ArrayList<Object>();
		try {
			List<?> sumSubject1List = this.resultExaminee1DAO.sumDataBySubject(SUBJECT_ARR[0]);
			List<?> sumSubject2List = this.resultExaminee1DAO.sumDataBySubject(SUBJECT_ARR[1]);
			List<?> sumSubject3List = this.resultExaminee1DAO.sumDataBySubject(SUBJECT_ARR[2]);
			List<?> sumSubject4List = this.resultExaminee1DAO.sumDataBySubject(SUBJECT_ARR[3]);
			List<?> sumSubject5List = this.resultExaminee1DAO.sumDataBySubject(SUBJECT_ARR[4]);
			List<?> sumSubject6List = this.resultExaminee1DAO.sumDataBySubject(SUBJECT_ARR[5]);
			if (null != sumSubject1List && sumSubject1List.size()>0) {
				for (Object object : sumSubject1List) {
					list.add(object);
				}
			}
			if (null != sumSubject2List && sumSubject2List.size()>0) {
				for (Object object : sumSubject2List) {
					list.add(object);
				}		
			}
			if (null != sumSubject3List && sumSubject3List.size()>0) {
				for (Object object : sumSubject3List) {
					list.add(object);
				}
			}
			if (null != sumSubject4List && sumSubject4List.size()>0) {
				for (Object object : sumSubject4List) {
					list.add(object);
				}
			}
			if (null != sumSubject5List && sumSubject5List.size()>0) {
				for (Object object : sumSubject5List) {
					list.add(object);
				}
			}
			if (null != sumSubject6List && sumSubject6List.size()>0) {
				for (Object object : sumSubject6List) {
					list.add(object);
				}
			}
			// 生成随机4位数
			long id;
			Random random = new Random();
			if (null != list && list.size()>0) {
				for (Object object : list) {
					Object [] temp = (Object[]) object;
					ResultExamSum resultExamSum = new ResultExamSum(SUM_INIT_DATA, SUM_INIT_DATA, SUM_INIT_DATA,
							SUM_INIT_DATA, SUM_INIT_DATA, SUM_INIT_DATA, SUM_INIT_DATA, SUM_INIT_DATA, 
							SUM_INIT_DATA, SUM_INIT_DATA, SUM_INIT_DATA, SUM_INIT_DATA);
					id = random.nextLong();
					resultExamSum.setId(id);
					resultExamSum.setSubject((String)temp[0]);
					ExamPlace examPlace = new ExamPlace(Long.valueOf(String.valueOf(temp[1])));
					resultExamSum.setExamPlace(examPlace);
					resultExamSum.setTotal(Integer.parseInt(String.valueOf(temp[2])));
					this.resultExamSumDAO.saveObj(resultExamSum);
					resultExamSum = null;
				}
			}
			
			
			backMsgArr [0] = list.size();
			flag = true;
			backMsg = PropertiesReader.getValue("examinee.count.success");//
		} catch (Exception e) {
			backMsg = PropertiesReader.getValue("examinee.count.exception") + e.getMessage();//
		}finally{
			backMsgArr[1] = flag;
			backMsgArr[2] = backMsg;
		}
		
		return backMsgArr;
		
		
	}

	public static void main(String[] args) {
		int i =30;
		byte b = (byte) i;
		System.out.println(b);
	}

	

}
