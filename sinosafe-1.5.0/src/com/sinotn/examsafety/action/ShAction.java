package com.sinotn.examsafety.action;


import java.util.List;

import com.sinotn.examsafety.service.ISmsService;
import com.sinotn.examsafety.vo.LinkGroupVo;
import com.sinotn.examsafety.vo.SmsTaskVo;
import com.sinotn.examsafety.vo.SmsTemplateVo;

public class ShAction extends BaseAction {
	
	
	//业务注入开始-----------------------------
	private ISmsService smsServiceImpl = null;
	
	//业务注入结束-----------------------------
	
	
	//页面VO开始-----------------------------
	private List<LinkGroupVo> linkGroupList;
	private LinkGroupVo linkGroupVo;
	private SmsTemplateVo smsTemplateVo;
	private SmsTaskVo smsTaskVo;
	//页面VO结束-----------------------------
	/**
	 * 新建分组
	 * 
	 * @return
	 */
	public String addLinkGroup() {
		try {
			this.getSmsServiceImpl().savelinkGroup(linkGroupVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 修改更新分组
	 * 
	 * @return
	 */
	public String modifyLinkGroup() {
		try {
			this.getSmsServiceImpl().updatelinkGroup(linkGroupVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}

	/**
	 * 删除分组
	 * 
	 * @return
	 */
	public String removeLinkGroup() {
		try {
			this.getSmsServiceImpl().deletelinkGroup(linkGroupVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}

	/**
	 * 获得考试分组
	 * 
	 * @return
	 */
	public String getLinkGroup() {
		if(null!=linkGroupVo){
			linkGroupVo = this.getSmsServiceImpl().getlinkGroupById(linkGroupVo);
		}
		return SUCCESS;
	}
	public ISmsService getSmsServiceImpl() {
		return smsServiceImpl;
	}

	public void setSmsServiceImpl(ISmsService smsServiceImpl) {
		this.smsServiceImpl = smsServiceImpl;
	}

	public List<LinkGroupVo> getLinkGroupList() {
		return linkGroupList;
	}

	public void setLinkGroupList(List<LinkGroupVo> linkGroupList) {
		this.linkGroupList = linkGroupList;
	}

	public LinkGroupVo getLinkGroupVo() {
		return linkGroupVo;
	}

	public void setLinkGroupVo(LinkGroupVo linkGroupVo) {
		this.linkGroupVo = linkGroupVo;
	}

	public SmsTemplateVo getSmsTemplateVo() {
		return smsTemplateVo;
	}

	public void setSmsTemplateVo(SmsTemplateVo smsTemplateVo) {
		this.smsTemplateVo = smsTemplateVo;
	}
	
	

	public SmsTaskVo getSmsTaskVo() {
		return smsTaskVo;
	}

	public void setSmsTaskVo(SmsTaskVo smsTaskVo) {
		this.smsTaskVo = smsTaskVo;
	}
	
	
}
