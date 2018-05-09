package com.sinotn.examsafety.vo;

import java.util.Date;

import com.sinotn.examsafety.po.FileResource;


@SuppressWarnings("serial")
public class FileResourceVo implements java.io.Serializable {
	// Fields

	private String frId;
	private Date frCreateDate;
	private Date frModfiyDate;
	private Boolean frIsEnable;
	private String frLocalPath;
	private String frWebPath;
	private String frFileName;
	private String frFileSize;
	private String frCreateUser;
	private String frFileType;
	private String frUploadIp;
	private String frFileRemarks;
	private String frExamArea;
	private String frExamAreaName;
	
	// Constructors

	/** default constructor */
	public FileResourceVo() {
	}

	/** minimal constructor */
	public FileResourceVo(String frId) {
		this.frId = frId;
	}

	/** full constructor */
	public FileResourceVo(String frId, Date frCreateDate, Date frModfiyDate,
			Boolean frIsEnable, String frLocalPath, String frWebPath,
			String frFileName, String frFileSize, String frCreateUser,
			String frFileType, String frUploadIp, String frFileRemarks,
			String frExamArea, String frExamAreaName) {
		this.frId = frId;
		this.frCreateDate = frCreateDate;
		this.frModfiyDate = frModfiyDate;
		this.frIsEnable = frIsEnable;
		this.frLocalPath = frLocalPath;
		this.frWebPath = frWebPath;
		this.frFileName = frFileName;
		this.frFileSize = frFileSize;
		this.frCreateUser = frCreateUser;
		this.frFileType = frFileType;
		this.frUploadIp = frUploadIp;
		this.frFileRemarks = frFileRemarks;
		this.frExamArea = frExamArea;
		this.frExamAreaName = frExamAreaName;
	}
	
	/** full constructor */
	public FileResourceVo(FileResource fileResource) {
		this.frId = fileResource.getFrId();
		this.frCreateDate = fileResource.getFrCreateDate();
		this.frModfiyDate = fileResource.getFrModfiyDate();
		this.frIsEnable = fileResource.getFrIsEnable();
		this.frLocalPath = fileResource.getFrLocalPath();
		this.frWebPath = fileResource.getFrWebPath();
		this.frFileName = fileResource.getfrFileName();
		this.frFileSize = fileResource.getFrFileSize();
		this.frCreateUser = fileResource.getFrCreateUser();
		this.frFileType = fileResource.getFrFileType();
		this.frUploadIp = fileResource.getFrUploadIp();
		this.frFileRemarks = fileResource.getFrFileRemarks();
		this.frExamArea = fileResource.getFrExamArea();
		this.frExamAreaName = fileResource.getFrExamAreaName();
	}
	// Property accessors

	public String getFrId() {
		return this.frId;
	}

	public void setFrId(String frId) {
		this.frId = frId;
	}

	public Date getFrCreateDate() {
		return this.frCreateDate;
	}

	public void setFrCreateDate(Date frCreateDate) {
		this.frCreateDate = frCreateDate;
	}

	public Date getFrModfiyDate() {
		return this.frModfiyDate;
	}

	public void setFrModfiyDate(Date frModfiyDate) {
		this.frModfiyDate = frModfiyDate;
	}

	public Boolean getFrIsEnable() {
		return this.frIsEnable;
	}

	public void setFrIsEnable(Boolean frIsEnable) {
		this.frIsEnable = frIsEnable;
	}

	public String getFrLocalPath() {
		return this.frLocalPath;
	}

	public void setFrLocalPath(String frLocalPath) {
		this.frLocalPath = frLocalPath;
	}

	public String getFrWebPath() {
		return this.frWebPath;
	}

	public void setFrWebPath(String frWebPath) {
		this.frWebPath = frWebPath;
	}

	public String getFrFileName() {
		return frFileName;
	}

	public void setFrFileName(String frFileName) {
		this.frFileName = frFileName;
	}

	public String getFrFileSize() {
		return this.frFileSize;
	}

	public void setFrFileSize(String frFileSize) {
		this.frFileSize = frFileSize;
	}

	public String getFrCreateUser() {
		return this.frCreateUser;
	}

	public void setFrCreateUser(String frCreateUser) {
		this.frCreateUser = frCreateUser;
	}

	public String getFrFileType() {
		return this.frFileType;
	}

	public void setFrFileType(String frFileType) {
		this.frFileType = frFileType;
	}

	public String getFrUploadIp() {
		return this.frUploadIp;
	}

	public void setFrUploadIp(String frUploadIp) {
		this.frUploadIp = frUploadIp;
	}

	public String getFrFileRemarks() {
		return this.frFileRemarks;
	}

	public void setFrFileRemarks(String frFileRemarks) {
		this.frFileRemarks = frFileRemarks;
	}

	public String getFrExamArea() {
		return this.frExamArea;
	}

	public void setFrExamArea(String frExamArea) {
		this.frExamArea = frExamArea;
	}

	public String getFrExamAreaName() {
		return this.frExamAreaName;
	}

	public void setFrExamAreaName(String frExamAreaName) {
		this.frExamAreaName = frExamAreaName;
	}

	@Override
	public String toString() {
		return "FileResource [frId=" + frId + ", frCreateDate=" + frCreateDate
				+ ", frModfiyDate=" + frModfiyDate + ", frIsEnable="
				+ frIsEnable + ", frLocalPath=" + frLocalPath + ", frWebPath="
				+ frWebPath + ", frFileName=" + frFileName + ", frFileSize="
				+ frFileSize + ", frCreateUser=" + frCreateUser
				+ ", frFileType=" + frFileType + ", frUploadIp=" + frUploadIp
				+ ", frFileRemarks=" + frFileRemarks + ", frExamArea="
				+ frExamArea + ", frExamAreaName=" + frExamAreaName + "]";
	}
	
}
