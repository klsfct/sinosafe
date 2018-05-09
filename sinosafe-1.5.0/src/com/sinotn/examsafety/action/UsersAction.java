package com.sinotn.examsafety.action;

import java.util.List;

import com.sinotn.common.utils.LoginUtils;
import com.sinotn.common.utils.MD5;
import com.sinotn.examsafety.service.impl.BaseCodeUtils;
import com.sinotn.examsafety.service.impl.UsersServiceImpl;
import com.sinotn.examsafety.vo.BaseCodeVo;
import com.sinotn.examsafety.vo.SysUsersVo;

@SuppressWarnings("serial")
public class UsersAction extends BaseAction {
	private UsersServiceImpl usersServiceImpl = null;
	
	private SysUsersVo userVo = null;
	private String confirmPassword = null;
	
	public String login(){
		String userId = userVo.getUserId();
		String password = userVo.getPassword();
		userVo = this.getUsersServiceImpl().login(userId, password);
		//处理验证码:判断验证码输入的是否正确
		boolean flag = LoginUtils.isCheckNum(super.getRequest());
		if(userVo==null){
			//用户名密码错误
			throw new RuntimeException("用户名或密码错误，请重新填写！");
		}
		else{
			if(!flag){
				throw new RuntimeException("验证码输入有误,请重新输入！");
			}else {
				if(MD5.md5s(password).equals(MD5.md5s(userId))){
					//初始密码，重置密码必须补全信息
					this.getSession().setAttribute("tempUserId", userId);
					return "REGISTER";
				}
				else{
					this.getUsersServiceImpl().modifyLogin(userId, this.getRequestIp());
					String areaId = userVo.getAreaId();
					BaseCodeVo area = BaseCodeUtils.findBaseCodeById(areaId);
					int level = Integer.parseInt(area.getFlag());
					// 根据所属考点长度控制左侧菜单栏
					if (null != userVo.getExamPlaceId() && userVo.getExamPlaceId().toString().length()>5) {
						level = 4;
					}
					List<BaseCodeVo> areaList = BaseCodeUtils.findCodeListByPreviousId(areaId, true);
					userVo.setLevel(level);
					userVo.setAreaList(areaList);
					userVo.setMenu("menu" + level + ".jsp");
					userVo.setLeftFlag("left" + level + ".html");
					this.getSession().setAttribute("loginSession", userVo);
				}
			}
		}
		return SUCCESS;
	}
	public String logout(){
		userVo = (SysUsersVo)this.getSession().getAttribute("loginSession");
		if(userVo!=null){
			String userId = userVo.getUserId();
			this.getUsersServiceImpl().modifyLogout(userId);
			this.getSession().removeAttribute("loginSession");
		}
		return SUCCESS;
	}
	public String modifyInitSysUsers(){
		if(!userVo.getPassword().trim().equals(confirmPassword.trim())){
			throw new RuntimeException("输入密码与确认密码不一致，请重新填写！");
		}
		String tempUserId = (String)this.getSession().getAttribute("tempUserId");
		userVo.setUserId(tempUserId);
		this.getUsersServiceImpl().modifyInitSysUsers(userVo);
		this.getSession().removeAttribute("tempUserId");
		return SUCCESS;
	}
	public String resetPassword(){
		String sessionAreaId = this.getLoginUser().getAreaId();
		String userId = userVo.getUserId();
		userVo = this.getUsersServiceImpl().getUser(userId);
		String userAreaId = userVo.getAreaId();
		if(userAreaId.startsWith(sessionAreaId)){
			this.getUsersServiceImpl().modifyPassword(userId);
		}
		return SUCCESS;
	}
	public String modifyUser(){
		String userId = this.getLoginUser().getUserId();
		String principal = userVo.getPrincipal();
		String mobile = userVo.getMobile();
		this.getUsersServiceImpl().modifyUser(userId, principal, mobile);
		return SUCCESS;
	}
	public String getUser(){
		String sessionAreaId = this.getLoginUser().getAreaId();
		String userId = userVo.getUserId();
		userVo = this.getUsersServiceImpl().getUser(userId);
		String userAreaId = userVo.getAreaId();
		if(!userAreaId.startsWith(sessionAreaId)){
			throw new RuntimeException("系统错误！");
		}
		return SUCCESS;
	}
	
	public String executeInitUser(){
		this.getUsersServiceImpl().executeInitUser();
		return SUCCESS;
	}
	public UsersServiceImpl getUsersServiceImpl() {
		return usersServiceImpl;
	}

	public void setUsersServiceImpl(UsersServiceImpl usersServiceImpl) {
		this.usersServiceImpl = usersServiceImpl;
	}
	public SysUsersVo getUserVo() {
		return userVo;
	}
	public void setUserVo(SysUsersVo userVo) {
		this.userVo = userVo;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
