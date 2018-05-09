package com.sinotn.examsafety.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.List;

import org.apache.log4j.Logger;

import struct.JavaStruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.action.ResultExamineeAction;
import com.sinotn.examsafety.dao.BaseCodeDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.dao.ExamineeDAO;
import com.sinotn.examsafety.dao.SubjectDAO;
import com.sinotn.examsafety.po.BaseCode;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.Examinee;
import com.sinotn.examsafety.po.Subject;
import com.sinotn.examsafety.struct.AreaStruct;
import com.sinotn.examsafety.struct.ExamineeStruct;
import com.sinotn.examsafety.struct.RoomStruct;
import com.sinotn.examsafety.vo.SysUsersVo;

public class DataExportManager {
	private static Logger logger = Logger.getLogger(DataExportManager.class);
	private BaseCodeDAO baseCodeDAO = null;
	private ExamPlaceDAO examPlaceDAO = null;
	private ExamineeDAO examineeDAO = null;
	private String filePath = "";
	private SubjectDAO subjectDAO;
	private String faceFilePath = "C:\\Users\\SinotnLibin\\Desktop\\广西测试人脸识别客户端";
	private String handFilePath = "C:\\Users\\SinotnLibin\\Desktop\\广西测试人工核查客户端";
	/**
	 * 考区数据导出
	 */
	public void areaDataExport() {
		System.out.println("areaDataExport......start");
		String fileName = "city";
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		FileOutputStream fos = null;
		AreaStruct struct = null;
		for (BaseCode province : provinceList) {
			try {
				List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
						province.getId());
				if (areaList.size() > 0) {
					File file = new File(filePath + province.getId()
							+ province.getName());
					if (!file.exists()) {
						file.mkdir();
					}
					fos = new FileOutputStream(file.getPath() + "\\" + fileName);
				} else {
					continue;
				}
				for (Object temp : areaList) {
					Object[] value = (Object[]) temp;
					String areaId = (String) value[0];
					String areaName = (String) value[1];
					struct = new AreaStruct();
					struct.setCode(Integer.parseInt(areaId.substring(5, 9)));
					struct.setName(areaName);
					byte[] b = JavaStruct.pack(struct, ByteOrder.LITTLE_ENDIAN);
					fos.write(b);
					fos.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		System.out.println("areaDataExport......end");
	}

	/**
	 * 手持机读卡考点数据导出
	 */
	public void cardPlaceDataExport() {
		System.out.println("placeDataExport......start");
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		FileOutputStream fos = null;
		AreaStruct struct = null;
		File file = null;
		for (BaseCode province : provinceList) {
			try {
				List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
						province.getId());
				if (areaList.size() > 0) {
					file = new File(filePath + province.getId()
							+ province.getName());
					if (!file.exists()) {
						file.mkdir();
					}

				} else {
					continue;
				}
				for (Object temp : areaList) {
					Object[] value = (Object[]) temp;
					String areaId = (String) value[0];

					List<?> examPlaceList = this.getExamineeDAO()
							.findExamCardPlaceList(areaId);
					if (examPlaceList.size() > 0) {
						fos = new FileOutputStream(file.getPath() + "\\"
								+ areaId.substring(5, 9));
					}
					for (Object tempPlace : examPlaceList) {
						Object[] placeValue = (Object[]) tempPlace;
						long placeId = (Long) placeValue[0];
						String placeName = (String) placeValue[1];
						struct = new AreaStruct();
						struct.setCode(Integer.parseInt(String.valueOf(placeId)));
						if (placeName.length() > 22) {
							struct.setName(placeName.substring(0, 22));
						} else {
							struct.setName(placeName);
						}

						byte[] b = JavaStruct.pack(struct,
								ByteOrder.LITTLE_ENDIAN);
						fos.write(b);
						fos.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		System.out.println("placeDataExport......end");
	}

	/**
	 * 人脸识别考点数据导出
	 */
	public void facePlaceDataExport() {
		System.out.println("placeDataExport......start");
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		FileOutputStream fos = null;
		AreaStruct struct = null;
		File file = null;
		for (BaseCode province : provinceList) {
			try {
				List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
						province.getId());
				if (areaList.size() > 0) {
					file = new File(filePath + province.getId()
							+ province.getName());
					if (!file.exists()) {
						file.mkdir();
					}

				} else {
					continue;
				}
				for (Object temp : areaList) {
					Object[] value = (Object[]) temp;
					String areaId = (String) value[0];
					List<?> examPlaceList = this.getExamineeDAO()
							.findExamineePlaceList(areaId);
					if (examPlaceList.size() > 0) {
						fos = new FileOutputStream(file.getPath() + "\\"
								+ areaId.substring(5, 9));
					}
					for (Object tempPlace : examPlaceList) {
						Object[] placeValue = (Object[]) tempPlace;
						long placeId = (Long) placeValue[0];
						String placeName = (String) placeValue[1];
						struct = new AreaStruct();
						struct.setCode(Integer.parseInt(String.valueOf(placeId)));
						struct.setName(placeName);
						byte[] b = JavaStruct.pack(struct,
								ByteOrder.LITTLE_ENDIAN);
						fos.write(b);
						fos.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		System.out.println("placeDataExport......end");
	}

	/**
	 * 考场数据导出
	 */
	public void roomDataExport() {
		System.out.println("roomDataExport......start");
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		FileOutputStream fos = null;
		RoomStruct struct = null;
		File file = null;
		for (BaseCode province : provinceList) {
			try {
				List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
						province.getId());
				if (areaList.size() > 0) {
					file = new File(filePath + province.getId()
							+ province.getName());
					if (!file.exists()) {
						file.mkdir();
					}
				} else {
					continue;
				}
				for (Object temp : areaList) {
					Object[] value = (Object[]) temp;
					String areaId = (String) value[0];
					List<ExamPlace> examPlaceList = this.getExamPlaceDAO()
							.findExamPlaceList(areaId);
					for (ExamPlace examPlace : examPlaceList) {
						long examPlaceId = examPlace.getExamPlace();
						List<?> examRoomList = this.getExamineeDAO()
								.findExamRoomList(examPlaceId);
						if (examRoomList.size() > 0) {
							fos = new FileOutputStream(file.getPath() + "\\"
									+ examPlaceId);
						}
						for (Object temp1 : examRoomList) {
							Object[] value1 = (Object[]) temp1;
							struct = new RoomStruct();
							struct.setRoomNo(Short.parseShort(String
									.valueOf(value1[0])));
							struct.setExamineeSum(Byte.parseByte(String
									.valueOf(value1[1])));
							byte[] b = JavaStruct.pack(struct,
									ByteOrder.LITTLE_ENDIAN);
							fos.write(b);
							fos.flush();
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		System.out.println("roomDataExport......end");
	}

	/**
	 * 考场数据导出
	 */
	public void examineeDataExport() {
		System.out.println("examineeDataExport......start");
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		FileOutputStream fos = null;
		ExamineeStruct struct = null;
		File file = null;
		for (BaseCode province : provinceList) {
			try {
				List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
						province.getId());
				if (areaList.size() > 0) {
					file = new File(filePath + province.getId()
							+ province.getName());
					if (!file.exists()) {
						file.mkdir();
					}
				} else {
					continue;
				}
				for (Object temp : areaList) {
					Object[] value = (Object[]) temp;
					String areaId = (String) value[0];
					List<?> examPlaceList = this.getExamineeDAO()
							.findExamineePlaceList(areaId);
					for (Object tempPlace : examPlaceList) {
						Object[] placeValue = (Object[]) tempPlace;
						long examPlaceId = (Long) placeValue[0];
						List<Examinee> examineeList = this.getExamineeDAO()
								.findExaminee(examPlaceId);
						if (examineeList.size() > 0) {
							fos = new FileOutputStream(file.getPath() + "\\"
									+ examPlaceId);
						}
						for (Examinee examinee : examineeList) {
							struct = new ExamineeStruct();
							struct.setLicence(examinee.getLicence().trim());
							struct.setIdentity(examinee.getIdentity().trim());
							struct.setExamineeName(examinee.getExamineeName()
									.trim());
							struct.setExamRoom(1);
							struct.setSeatNumber(1);
							byte[] b = JavaStruct.pack(struct,
									ByteOrder.LITTLE_ENDIAN);
							fos.write(b);
							fos.flush();
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		System.out.println("examineeDataExport......end");
	}

	/**
	 * 考场数据导出（分科目导出）
	 */
	public void examineeDataExport(String subject) {
		System.out.println("examineeDataExport......start");
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		FileOutputStream fos = null;
		ExamineeStruct struct = null;
		File file = null;
		for (BaseCode province : provinceList) {
			try {
				List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
						province.getId());
				if (areaList.size() > 0) {
					file = new File(filePath + province.getId()
							+ province.getName());
					if (!file.exists()) {
						file.mkdir();
					}
				} else {
					continue;
				}
				for (Object temp : areaList) {
					Object[] value = (Object[]) temp;
					String areaId = (String) value[0];
					List<?> examPlaceList = this.getExamineeDAO()
							.findExamineePlaceList(areaId);
					for (Object tempPlace : examPlaceList) {
						Object[] placeValue = (Object[]) tempPlace;
						long examPlaceId = (Long) placeValue[0];
						List<Examinee> examineeList = this.getExamineeDAO()
								.findExaminee(examPlaceId, subject);
						if (examineeList.size() > 0) {
							fos = new FileOutputStream(file.getPath() + "\\"
									+ subject + "_" + examPlaceId);
						}
						for (Examinee examinee : examineeList) {
							struct = new ExamineeStruct();
							struct.setLicence(examinee.getLicence().trim());
							struct.setIdentity(examinee.getIdentity().trim());
							struct.setExamineeName(examinee.getExamineeName()
									.trim());
							struct.setExamRoom(examinee.getExamRoom());
							struct.setSeatNumber(examinee.getSeatNumber());
							byte[] b = JavaStruct.pack(struct,
									ByteOrder.LITTLE_ENDIAN);
							fos.write(b);
							fos.flush();
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		System.out.println("examineeDataExport......end");
	}

	public BaseCodeDAO getBaseCodeDAO() {
		return baseCodeDAO;
	}

	public void setBaseCodeDAO(BaseCodeDAO baseCodeDAO) {
		this.baseCodeDAO = baseCodeDAO;
	}

	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	/**
	 * 人工核查科目文件导出
	 * 
	 * @return
	 */
	public void checkSubjectDataExport() {
		File file = null;
		JsonArray jsonArray = new JsonArray();
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		for (BaseCode province : provinceList) {
			List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
					province.getId());
			if (areaList.size() > 0) {
				file = new File(filePath 
						+ province.getName());
				if (!file.exists()) {
					file.mkdir();
				}
			}
		}
		// 查询考区信息
		String fileName = "SUBJECT.txt";
		List<Subject> subjectList = this.subjectDAO.findSubjectList();
		if (null != subjectList && subjectList.size() > 0) {
			for (Subject subject : subjectList) {
				JsonObject dataJson = new JsonObject();
				dataJson.addProperty("id", subject.getId().toString().trim());
				dataJson.addProperty("name", subject.getName().trim());
				jsonArray.add(dataJson);
			}

			try {
				FileWriter fileWriter = new FileWriter(new File(file.getPath()
						+ "\\" + fileName));
				BufferedWriter bw = new BufferedWriter(fileWriter);
				bw.write(jsonArray.toString());
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 人工核查考点文件导出
	 * 
	 * @return
	 */
	public void checkPlaceDataExport() {
		File file = null;
		JsonArray jsonArray = new JsonArray();
		String fileName = "AREA_PLACE.txt";
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		for (BaseCode province : provinceList) {
			List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
					province.getId());
			if (areaList.size() > 0) {
				// 省节点数据
				JsonObject provinceJson = new JsonObject();
				provinceJson.addProperty("provinceId", province.getId().trim());
				provinceJson.addProperty("provinceName", province.getName()
						.trim());
				provinceJson.addProperty("level", "1");

				// 考区节点数据
				JsonArray children = new JsonArray();
				for (Object temp : areaList) {
					Object[] value = (Object[]) temp;
					String areaId = (String) value[0];
					String araeName = (String) value[1];
					JsonObject areaJson = new JsonObject();
					areaJson.addProperty("areaId", areaId);
					areaJson.addProperty("areaName", araeName.trim());
					areaJson.addProperty("level", "2");

					// 考点节点数据
					JsonArray examPlaceJsonArr = new JsonArray();
					List<?> examPlaceList = this.getExamineeDAO()
							.findExamineePlaceList(areaId);
					if (examPlaceList.size() > 0) {
						for (Object placeObj : examPlaceList) {
							Object[] place = (Object[]) placeObj;
							long placeId = (Long) place[0];
							String placeName = (String) place[1];
							JsonObject examPlaceJson = new JsonObject();
							examPlaceJson.addProperty("placeId", placeId + "");
							examPlaceJson.addProperty("placeName",
									placeName.trim());
							examPlaceJsonArr.add(examPlaceJson);
						}

					}
					areaJson.add("placeList", examPlaceJsonArr);
					children.add(areaJson);
				}
				provinceJson.add("areaList", children);
				jsonArray.add(provinceJson);

				file = new File(filePath + province.getId()
						+ province.getName());
				if (!file.exists()) {
					file.mkdir();
				}
			}

		}
		try {
			FileWriter fileWriter = new FileWriter(new File(file.getPath()
					+ "\\" + fileName));
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 人工核查系统考生信息导出
	 * 
	 * @return
	 */
	public void checkExamineeDataExport() {
		String fileName = "EXAMINEE.txt";
		File file = null;
		JsonArray jsonArray = new JsonArray();
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		for (BaseCode province : provinceList) {
			List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
					province.getId());
			if (areaList.size() > 0) {
				file = new File(filePath + province.getId()
						+ province.getName());
				if (!file.exists()) {
					file.mkdir();
				}
			}
		}
		// 查询科目表
		List<Subject> subjectList = this.subjectDAO.findSubjectList();
		JsonObject examineeListJson = new JsonObject();
		if (null != subjectList && subjectList.size() > 0) {
			for (Subject subject : subjectList) {
				List<?> examineeList = this.examineeDAO
						.findExamineeListBySubject(subject.getId());
				JsonArray examJsonArray = new JsonArray();
				if (null != examineeList && examineeList.size() > 0) {
					for (Object examinee : examineeList) {
						Object[] object = (Object[]) examinee;
						JsonObject examineeJson = new JsonObject();
						examineeJson.addProperty("examineeName", object[0]
								.toString().trim());
						examineeJson.addProperty("identity", object[1]
								.toString().trim());
						examineeJson.addProperty("licence", object[2]
								.toString().trim());
						examineeJson.addProperty("examPlaceName", object[3]
								.toString().trim());
						examineeJson.addProperty("subjectName", subject
								.getName().trim());
						String examBeginDate = DateUtils.formatDateTime(
								subject.getBeginDate(),
								DateUtils.C_YYYYMMDDHHMM);
						String examEndDate = DateUtils.formatDateTime(
								subject.getEndDate(), DateUtils.C_YYYYMMDDHHMM);
						examineeJson.addProperty("examDate", examBeginDate
								+ "至" + examEndDate);
						examineeJson.addProperty("examRoom", object[4]
								.toString().trim());
						examineeJson.addProperty("seatNumber", object[5]
								.toString().trim());
						examineeJson.addProperty("examPlace", object[6]
								.toString().trim());
						examJsonArray.add(examineeJson);
					}
					examineeListJson.add(subject.getId().toString(),
							examJsonArray);
				}

			}
			jsonArray.add(examineeListJson);
		}
		try {
			FileWriter fileWriter = new FileWriter(new File(file.getPath()
					+ "\\" + fileName));
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void examineeDataExportByExamPlace(String subject, long examPlaceId) {
		FileOutputStream fos = null;
		ExamineeStruct struct = null;
		File file = null;
		try {
			// 检查考点是否存在
			ExamPlace examPlace = this.examPlaceDAO.findObjectById(examPlaceId);
			if (null != examPlace) {
				file = new File(filePath  + "\\" + examPlaceId + "\\base");
				// 创建目录
				if (!file.exists()) {
					file.mkdir();
				}
				List<Examinee> examineeList = this.getExamineeDAO()
						.findExaminee(examPlaceId, subject);
				if (examineeList.size() > 0) {
					fos = new FileOutputStream(file.getPath() + "\\" + subject
							+ "_" + examPlaceId);
				}
				for (Examinee examinee : examineeList) {
					struct = new ExamineeStruct();
					struct.setLicence(examinee.getLicence().trim());
					struct.setIdentity(examinee.getIdentity().trim());
					struct.setExamineeName(examinee.getExamineeName().trim());
					struct.setExamRoom(examinee.getExamRoom());
					struct.setSeatNumber(examinee.getSeatNumber());
					byte[] b = JavaStruct.pack(struct, ByteOrder.LITTLE_ENDIAN);
					fos.write(b);
					fos.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	/**
	 * 根据登录用户所属考点导出考点信息
	 * 
	 * @param examPlaceId
	 */
	public void checkPlaceDataExport(long examPlaceId) {

		File file = null;
		JsonArray jsonArray = new JsonArray();
		String fileName = "AREA_PLACE.txt";
		//考点信息
		ExamPlace examPlace = this.examPlaceDAO.findObjectById(examPlaceId);
		// 省节点数据
		JsonObject provinceJson = new JsonObject();
		provinceJson.addProperty("provinceId", examPlace.getExamProvince().trim());
		provinceJson.addProperty("provinceName", examPlace.getExamProvinceName().trim());
		provinceJson.addProperty("level", "1");
		// 考区节点数据
		JsonArray children = new JsonArray();
		JsonObject areaJson = new JsonObject();
		areaJson.addProperty("areaId", examPlace.getExamArea());
		areaJson.addProperty("areaName", examPlace.getExamAreaName().trim());
		areaJson.addProperty("level", "2");

		// 考点节点数据
		JsonArray examPlaceJsonArr = new JsonArray();
		JsonObject examPlaceJson = new JsonObject();
		examPlaceJson.addProperty("placeId", examPlace.getExamPlace() + "");
		examPlaceJson.addProperty("placeName", examPlace.getExamPlaceName().trim());
		examPlaceJsonArr.add(examPlaceJson);

		areaJson.add("placeList", examPlaceJsonArr);
		children.add(areaJson);
		provinceJson.add("areaList", children);
		jsonArray.add(provinceJson);

		file = new File(handFilePath + "\\HandSystem-Alpha-" + examPlaceId + "\\data");
		if (!file.exists()) {
			file.mkdir();
		}

		try {
			FileWriter fileWriter = new FileWriter(new File(file.getPath()
					+ "\\" + fileName));
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void checkExamineeDataExport(Long examPlaceId) {

		String fileName = "EXAMINEE.txt";
		File file = null;
		JsonArray jsonArray = new JsonArray();
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		for (BaseCode province : provinceList) {
			List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
					province.getId());
			if (areaList.size() > 0) {
				file = new File(handFilePath + "\\HandSystem-Alpha-" + examPlaceId + "\\data");
				if (!file.exists()) {
					file.mkdir();
				}
			}
		}
		// 查询科目表
		List<Subject> subjectList = this.subjectDAO.findSubjectList();
		JsonObject examineeListJson = new JsonObject();
		if (null != subjectList && subjectList.size() > 0) {
			for (Subject subject : subjectList) {
				List<?> examineeList = this.examineeDAO
						.findExamineeListBySubject(subject.getId(), examPlaceId);
				JsonArray examJsonArray = new JsonArray();
				if (null != examineeList && examineeList.size() > 0) {
					for (Object examinee : examineeList) {
						Object[] object = (Object[]) examinee;
						JsonObject examineeJson = new JsonObject();
						examineeJson.addProperty("examineeName", object[0]
								.toString().trim());
						examineeJson.addProperty("identity", object[1]
								.toString().trim());
						examineeJson.addProperty("licence", object[2]
								.toString().trim());
						examineeJson.addProperty("examPlaceName", object[3]
								.toString().trim());
						examineeJson.addProperty("subjectName", subject
								.getName().trim());
						String examBeginDate = DateUtils.formatDateTime(
								subject.getBeginDate(),
								DateUtils.C_YYYYMMDDHHMM);
						String examEndDate = DateUtils.formatDateTime(
								subject.getEndDate(), DateUtils.C_YYYYMMDDHHMM);
						examineeJson.addProperty("examDate", examBeginDate
								+ "至" + examEndDate);
						examineeJson.addProperty("examRoom", object[4]
								.toString().trim());
						examineeJson.addProperty("seatNumber", object[5]
								.toString().trim());
						examineeJson.addProperty("examPlace", object[6]
								.toString().trim());
						examJsonArray.add(examineeJson);
					}
					examineeListJson.add(subject.getId().toString(),
							examJsonArray);
				}

			}
			jsonArray.add(examineeListJson);
		}
		try {
			FileWriter fileWriter = new FileWriter(new File(file.getPath()
					+ "\\" + fileName));
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void areaDataExportByExamPlace(Long examPlaceId) {
		String fileName = "city";
		FileOutputStream fos = null;
		AreaStruct struct = null;
			try {
				ExamPlace examPlace =  this.getExamPlaceDAO().findObjectById(examPlaceId);
				if (null != examPlace) {
					File file = new File(faceFilePath  + "\\" + examPlaceId + "\\base");
					if (!file.exists()) {
						file.mkdir();
					}
					fos = new FileOutputStream(file.getPath() + "\\" + fileName);
					String areaId = examPlace.getExamArea();
					String areaName = examPlace.getExamAreaName();
					struct = new AreaStruct();
					struct.setCode(Integer.parseInt(areaId.substring(5, 9)));
					struct.setName(areaName);
					byte[] b = JavaStruct.pack(struct, ByteOrder.LITTLE_ENDIAN);
					fos.write(b);
					fos.flush();
				} else {
					logger.error("考点不存在：" + examPlaceId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
	
		
	}

	public void facePlaceDataExportByExamPlace(Long examPlaceId) {
		FileOutputStream fos = null;
		AreaStruct struct = null;
		File file = null;
			try {
				ExamPlace examPlace =  this.getExamPlaceDAO().findObjectById(examPlaceId);
				if (null != examPlace) {
					file = new File(faceFilePath  + "\\" + examPlaceId + "\\base");
					if (!file.exists()) {
						file.mkdir();
					}
					String areaId = examPlace.getExamArea();
					fos = new FileOutputStream(file.getPath() + "\\"
								+ areaId.substring(5, 9));
					String placeName = examPlace.getExamPlaceName();
					struct = new AreaStruct();
					struct.setCode(Integer.parseInt(String.valueOf(examPlaceId)));
					struct.setName(placeName);
					byte[] b = JavaStruct.pack(struct,ByteOrder.LITTLE_ENDIAN);
					fos.write(b);
					fos.flush();
						
				} else {
					logger.error("考点不存在：" + examPlaceId);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
	
		
	}

	public void checkSubjectDataExport(Long examPlaceId) {

		File file = null;
		JsonArray jsonArray = new JsonArray();
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		for (BaseCode province : provinceList) {
			List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
					province.getId());
			if (areaList.size() > 0) {
				file = new File(handFilePath + "\\HandSystem-Alpha-" + examPlaceId + "\\data");
				if (!file.exists()) {
					file.mkdir();
				}
			}
		}
		// 查询考区信息
		String fileName = "SUBJECT.txt";
		List<Subject> subjectList = this.subjectDAO.findSubjectList();
		if (null != subjectList && subjectList.size() > 0) {
			for (Subject subject : subjectList) {
				JsonObject dataJson = new JsonObject();
				dataJson.addProperty("id", subject.getId().toString().trim());
				dataJson.addProperty("name", subject.getName().trim());
				jsonArray.add(dataJson);
			}

			try {
				FileWriter fileWriter = new FileWriter(new File(file.getPath()
						+ "\\" + fileName));
				BufferedWriter bw = new BufferedWriter(fileWriter);
				bw.write(jsonArray.toString());
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
	}

	public static void main(String[] args) {
		long id = 14501001L;
		short ids = Short.parseShort(String.valueOf(id));
		System.out.println(ids);
	}
}
