package com.sinotn.examsafety.action;

import java.io.File;

import org.apache.log4j.Logger;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.po.FileResource;
import com.sinotn.examsafety.service.impl.FileResourceServiceImpl;
import com.sinotn.examsafety.vo.FileResourceVo;
import com.sinotn.examsafety.vo.SysUsersVo;

/**
 * 文件管理控制器
 * 
 * @author SinotnLibin
 * 
 */
@SuppressWarnings("serial")
public class FileResourceAction extends BaseAction {

	private static Logger logger = Logger.getLogger(FileResourceAction.class);

	private FileResourceServiceImpl fileResourceServiceImpl;
	
	private static final String NOW_DATE_FORMAT =  DateUtils.getCurFormatDate(DateUtils.C_YYYY_MM_DD) +"/";
	
	private static final String EXAMINEE_FILE_DIR = "/FileUpload/examinee/";
	
	private static final String USER_FILE_DIR = "/FileUpload/user/";
	
	private static final Long UPLOAD_FILE_LENGTH = 104857600L;
	
	private FileResourceVo fileResourceVo;
	
	/**
	 * 上传文件
	 */
	private File uploadFile;
	
	
	/**
	 * 文件上传
	 */
	public String uploadFile() {
		try {
			if (null != uploadFile) {
				// 文件检测
				boolean flag = validateFile(uploadFile);
				if (flag) {
					SysUsersVo sysUsersVo = getLoginUser();
					int level = sysUsersVo.getLevel();
					StringBuilder dir = new StringBuilder();
					// 省级用户登录 上传用户文件
					if (level == 2) {
						dir.append(USER_FILE_DIR + NOW_DATE_FORMAT);
					}
					// 超级管理员登录 上传考生文件
					if (level == 1) {
						dir.append(EXAMINEE_FILE_DIR + NOW_DATE_FORMAT);
					}
					// 指定上传目录
					dir.append(super.getLoginUser().getUserId() + "/");
					// 项目服务器路径
					String projectServerPath = getProjectPath() + dir;
					// 上传文件绝对路径
					String path = getLoadPath(dir.toString());
					// 指定文件名
					FileResource fileResource= this.fileResourceServiceImpl.uploadFile(uploadFile, projectServerPath, path, sysUsersVo, fileResourceVo);
					this.setAjaxBackDataMain(fileResource);
				}else {
					this.setAjaxBackDataErrorMsg("上传文件大小超过限制100MB！");
				}
			} else {
				this.setAjaxBackDataErrorMsg("文件上传失败！");
			}
		} catch (Exception e) {
			this.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;

	}
	/**
	 * 文件检测
	 * @param uploadFile2
	 * @return
	 */
	private boolean validateFile(File file) {
		boolean flag = false;
		// 检测文件大小
		long fileSize = file.length();
		if (fileSize < UPLOAD_FILE_LENGTH) {
			flag = true;
		}
		logger.debug(file.getName() + ": " + fileSize);
		return flag;
	}

	/**
	 * 文件删除
	 * @return
	 */
	public String deleteFile(){
		this.fileResourceServiceImpl.deleteFile(fileResourceVo);
		return SUCCESS;
	}
	
	/**
	 * 获取项目服务器路径
	 * @return
	 */
	private String getProjectPath(){
		
		String projectServerPath = this.getRequest().getScheme()
				+ "://" + this.getRequest().getServerName() + ":"
				+ this.getRequest().getServerPort()
				+ this.getRequest().getContextPath();
		return projectServerPath;
	}
	/**
	 * 获取项目本地路径
	 * @param dir 指定上传目录
	 * @return
	 */
	private String getLoadPath(String dir){
		String path = this.getRequest().getSession().getServletContext().getRealPath(dir);
		return path;
				
	}
	
	
	
	/****************************** get/set ***********************/
	public FileResourceServiceImpl getFileResourceServiceImpl() {
		return fileResourceServiceImpl;
	}

	public void setFileResourceServiceImpl(
			FileResourceServiceImpl fileResourceServiceImpl) {
		this.fileResourceServiceImpl = fileResourceServiceImpl;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public FileResourceVo getFileResourceVo() {
		return fileResourceVo;
	}

	public void setFileResourceVo(FileResourceVo fileResourceVo) {
		this.fileResourceVo = fileResourceVo;
	}
	public static void main(String[] args) {
		System.out.println(DateUtils.getCurFormatDate(DateUtils.C_YYYY_MM_DD));
	}


}
