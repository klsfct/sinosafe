package com.sinotn.examsafety.service.impl;

import java.util.List;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.MD5;
import com.sinotn.examsafety.dao.BaseCodeDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.dao.SysUsersDAO;
import com.sinotn.examsafety.po.BaseCode;
import com.sinotn.examsafety.po.SysUsers;
import com.sinotn.examsafety.vo.SysUsersVo;

public class UsersServiceImpl {

	private SysUsersDAO sysUsersDAO = null;
	private BaseCodeDAO baseCodeDAO = null;
	private ExamPlaceDAO examPlaceDAO = null;

	public SysUsersVo login(String userId, String password) {
		SysUsers sysUsers = this.getSysUsersDAO().findObjectById(userId);
		if (sysUsers == null) {
			return null;
		} else {
			if (sysUsers.getPassword().equals(MD5.md5s(password))) {

				return new SysUsersVo(sysUsers);
			} else {
				return null;
			}
		}
	}

	public void modifyLogout(String userId) {
		SysUsers sysUsers = this.getSysUsersDAO().findObjectById(userId);
		if (sysUsers != null) {
			sysUsers.setLogoutDate(DateUtils.getCurrentDate());
			this.getSysUsersDAO().update(sysUsers);
		}
	}

	public void modifyLogin(String userId, String loginIp) {
		SysUsers sysUsers = this.getSysUsersDAO().findObjectById(userId);
		if (sysUsers != null) {
			sysUsers.setLoginDate(DateUtils.getCurrentDate());
			sysUsers.setLoginIp(loginIp);
			this.getSysUsersDAO().update(sysUsers);
		}
	}

	public void modifyInitSysUsers(SysUsersVo sysUsersVo) {
		String userId = sysUsersVo.getUserId();
		SysUsers sysUsers = this.getSysUsersDAO().findObjectById(userId);
		if (sysUsers != null) {
			sysUsers.setMobile(sysUsersVo.getMobile());
			sysUsers.setPassword(MD5.md5s(sysUsersVo.getPassword()));
			sysUsers.setPrincipal(sysUsersVo.getPrincipal());
			sysUsers.setIsEnabled(true);
			this.getSysUsersDAO().update(sysUsers);
		}
	}

	public void modifyPassword(String userId) {
		SysUsers user = this.getSysUsersDAO().findObjectById(userId);
		user.setPrincipal(null);
		user.setMobile(null);
		user.setPassword(MD5.md5s(user.getUserId()));
		user.setIsEnabled(false);
		this.getSysUsersDAO().update(user);
	}

	public void modifyUser(String userId, String principal, String mobile) {
		SysUsers user = this.getSysUsersDAO().findObjectById(userId);
		user.setPrincipal(principal);
		user.setMobile(mobile);
		this.getSysUsersDAO().update(user);
	}

	public void executeInitUser() {
		List<BaseCode> provinceList = this.getBaseCodeDAO().findProvinceList();
		for (BaseCode province : provinceList) {
			List<?> areaList = this.getExamPlaceDAO().findExamAreaList(
					province.getId());
			SysUsers user = new SysUsers();
			user.setAreaId(province.getId());
			user.setUserId("safe_" + province.getId().substring(5, 7));
			user.setAreaName(province.getName());
			user.setCreateDate(DateUtils.getCurrentDate());
			user.setIsEnabled(false);
			user.setPassword(MD5.md5s(user.getUserId()));
			this.getSysUsersDAO().save(user);
			for (Object temp : areaList) {
				Object[] value = (Object[]) temp;
				String areaId = (String) value[0];
				String areaName = (String) value[1];
				SysUsers user1 = new SysUsers();
				user1.setAreaId(areaId);
				user1.setUserId("safe_" + areaId.substring(5, 9));
				user1.setAreaName(areaName);
				user1.setCreateDate(DateUtils.getCurrentDate());
				user1.setIsEnabled(false);
				user1.setPassword(MD5.md5s(user.getUserId()));
				this.getSysUsersDAO().save(user1);
			}
		}
	}

	public SysUsersVo getUser(String userId) {
		SysUsers user = this.getSysUsersDAO().findObjectById(userId);
		return new SysUsersVo(user);
	}

	public SysUsersDAO getSysUsersDAO() {
		return sysUsersDAO;
	}

	public void setSysUsersDAO(SysUsersDAO sysUsersDAO) {
		this.sysUsersDAO = sysUsersDAO;
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

}
