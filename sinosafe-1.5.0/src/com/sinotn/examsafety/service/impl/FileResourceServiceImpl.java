package com.sinotn.examsafety.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.FileUtils;
import com.sinotn.examsafety.dao.FileResourceDAO;
import com.sinotn.examsafety.po.FileResource;
import com.sinotn.examsafety.vo.FileResourceVo;
import com.sinotn.examsafety.vo.SysUsersVo;

/**
 * 文件管理业务类
 * 
 * @author SinotnLibin
 * 
 */
public class FileResourceServiceImpl {
	private static Logger logger = Logger.getLogger(FileResourceServiceImpl.class);

	private FileResourceDAO fileResourceDAO;
	
	/**
	 * 文件上传
	 * 
	 * @param uploadFile
	 *            文件
	 * @param projectServerPath
	 *            项目服务器路径
	 * @param path
	 *            本地长传路径
	 * @return
	 */
	public FileResource uploadFile(File uploadFile, String projectServerPath,
			String path, SysUsersVo sysUsersVo, FileResourceVo fileResourceVo) {
		try {
			
			String newFileName = System.currentTimeMillis() + fileResourceVo.getFrFileType();
			File newFile = new File(new File(path), newFileName);
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			// 读取保存在临时目录下的上传文件，写入到新的文件中
			FileUtils.copyFileToFile(uploadFile, newFile);
			String loadFilePath = path + "\\" +newFileName;
			String serverFilePath = projectServerPath + "\\" + newFileName;
			// 保存到资源文件表中
			FileResource fileResource = new FileResource();
			fileResource.setFrCreateDate(new Date());
			fileResource.setFrCreateUser(sysUsersVo.getUserId());
			fileResource.setFrExamArea(sysUsersVo.getAreaId());
			fileResource.setFrExamAreaName(sysUsersVo.getAreaName());
			fileResource.setFrFileName(newFileName);
			fileResource.setFrFileSize(String.valueOf(newFile.getAbsoluteFile().length()));
			fileResource.setFrFileType(fileResourceVo.getFrFileType());
			fileResource.setFrFileRemarks(fileResourceVo.getFrFileRemarks());
			fileResource.setFrIsEnable(true);
			fileResource.setFrUploadIp(sysUsersVo.getLoginIp());
			fileResource.setFrLocalPath(loadFilePath);
			fileResource.setFrWebPath(serverFilePath);
			this.fileResourceDAO.saveObj(fileResource);
			return fileResource;
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}
	/**
	 * 文件删除
	 * @param fileResourceVo
	 */
	public void deleteFile(FileResourceVo fileResourceVo) {
		try {
			if (null != fileResourceVo) {
				FileResource fileResource = this.fileResourceDAO.findObjectById(fileResourceVo.getFrId());
				if (null != fileResource) {
					// 删除文件
					boolean backMsg = FileUtils.delFile(fileResource.getFrLocalPath());
					// 删除数据
					if (backMsg) {
						this.fileResourceDAO.delete(fileResource);
					}else {
						throw new RuntimeException("删除文件失败！");
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	/****************************** get/set ***********************/
	public FileResourceDAO getFileResourceDAO() {
		return fileResourceDAO;
	}

	public void setFileResourceDAO(FileResourceDAO fileResourceDAO) {
		this.fileResourceDAO = fileResourceDAO;
	}

	

	
}
