package com.sinotn.examsafety.action;


import java.util.ArrayList;
import java.util.List;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.service.ISmsService;
import com.sinotn.examsafety.vo.LinkGroupVo;
import com.sinotn.examsafety.vo.LinkManJsonVo;
import com.sinotn.examsafety.vo.LinkManVo;
import com.sinotn.examsafety.vo.SmsInfoVo;

public class SmsAction extends BaseAction {
	private ISmsService smsServiceImpl = null;

	
	private LinkGroupVo linkGroupVo;
	private LinkManVo linkManVo;
	private SmsInfoVo smsInfoVo;
	private List<LinkManVo> linkManList;
	private List<LinkGroupVo> linkGroupList;
	
	private List<LinkManJsonVo> linkManJsonList;
	
	private String receiverList;
	
	
	public String findLinkManJsonList(){
		linkManJsonList = this.getSmsServiceImpl().findLinkManJsonList(this.getLoginUser().getAreaId());
		return SUCCESS;
	}
	
	public String gotoSavelinkGroup(){
		return SUCCESS;
	}
	
	
	public String gotoUpdatelinkGroup(){
		linkGroupVo = this.getSmsServiceImpl().getlinkGroupById(linkGroupVo);
		return SUCCESS;
	}
	/**
	 * 新建联系人组
	 * @return
	 */
	public String savelinkGroup(){
		linkGroupVo.setCreateDate(DateUtils.getCurrentDate());
		linkGroupVo.setCreater(this.getLoginUser().getUserId());
		linkGroupVo.setExamArea(this.getLoginUser().getAreaId());
		this.getSmsServiceImpl().savelinkGroup(linkGroupVo);
		return SUCCESS;
	}
	
	/**
	 * 修改联系人组
	 * @return
	 */
	public String updatelinkGroup(){
		linkGroupVo.setCreateDate(DateUtils.getCurrentDate());
		linkGroupVo.setCreater(this.getLoginUser().getUserId());
		linkGroupVo.setExamArea(this.getLoginUser().getAreaId());
		this.getSmsServiceImpl().updatelinkGroup(linkGroupVo);
		return SUCCESS;
	}
	
	
	/**
	 * 删除联系人组
	 * @return
	 */
	public String deletelinkGroup(){
		this.getSmsServiceImpl().deletelinkGroup(linkGroupVo);
		return SUCCESS;
	}
	
	
	public String gotoSavelinkMan(){
		linkGroupList = this.getSmsServiceImpl().findlinkGroupByArea(this.getLoginUser().getAreaId());
		return SUCCESS;
	}
	
	
	public String gotoUpdatelinkMan(){
		linkGroupList = this.getSmsServiceImpl().findlinkGroupByArea(this.getLoginUser().getAreaId());
		linkManVo = this.getSmsServiceImpl().getLinkManById(linkManVo);
		return SUCCESS;
	}
	
	/**
	 * 新建联系人
	 * @return
	 */
	public String savelinkMan(){
		linkManVo.setCreateDate(DateUtils.getCurrentDate());
		linkManVo.setCreater(this.getLoginUser().getUserId());
		linkManVo.setExamArea(this.getLoginUser().getAreaId());
		this.getSmsServiceImpl().savelinkMan(linkManVo);
		return SUCCESS;
		
	}
	
	
	/**
	 * 修改联系人
	 * @return
	 */
	public String updatelinkMan(){
		linkManVo.setCreateDate(DateUtils.getCurrentDate());
		linkManVo.setCreater(this.getLoginUser().getUserId());
		linkManVo.setExamArea(this.getLoginUser().getAreaId());
		this.getSmsServiceImpl().updatelinkMan(linkManVo);
		return SUCCESS;
		
	}
	
	/**
	 * 删除联系人
	 * @return
	 */
	public String deletelinkMan(){
		this.getSmsServiceImpl().deletelinkMan(linkManVo);
		return SUCCESS;
		
	}
	
	public String gotoSendMessage(){
		return SUCCESS;
	}
	
	
	public String showMessgage(){
		this.getSmsServiceImpl().getSmsInfoById(smsInfoVo);
		return SUCCESS;
	}
	
	/**
	 * 发送短信(废弃)
	 * @return
	 */
	public String sendMessage(){
		
		if(receiverList.length()>0){
			linkManList = new ArrayList<LinkManVo>();
			String[] tempList = receiverList.split(",");
			for(String temp:tempList){
				Long l = Long.parseLong(temp)-100000000;
				LinkManVo tempMan = new LinkManVo();
				tempMan.setId(l);
				linkManList.add(tempMan);
			}
		}
		smsInfoVo.setCreateDate(DateUtils.getCurrentDate());
		smsInfoVo.setCreater(this.getLoginUser().getUserId());
		smsInfoVo.setExamArea(this.getLoginUser().getAreaId());
		this.getSmsServiceImpl().saveSendMessage(linkManList, smsInfoVo);
		alertForm("发送成功！", "findSmsInfo.action", null);
		return null;
	}

	
	
	
	
	public ISmsService getSmsServiceImpl() {
		return smsServiceImpl;
	}

	public void setSmsServiceImpl(ISmsService smsServiceImpl) {
		this.smsServiceImpl = smsServiceImpl;
	}

	public LinkGroupVo getLinkGroupVo() {
		return linkGroupVo;
	}

	public void setLinkGroupVo(LinkGroupVo linkGroupVo) {
		this.linkGroupVo = linkGroupVo;
	}

	public LinkManVo getLinkManVo() {
		return linkManVo;
	}

	public void setLinkManVo(LinkManVo linkManVo) {
		this.linkManVo = linkManVo;
	}

	public SmsInfoVo getSmsInfoVo() {
		return smsInfoVo;
	}

	public void setSmsInfoVo(SmsInfoVo smsInfoVo) {
		this.smsInfoVo = smsInfoVo;
	}

	public List<LinkManVo> getLinkManList() {
		return linkManList;
	}

	public void setLinkManList(List<LinkManVo> linkManList) {
		this.linkManList = linkManList;
	}


	public List<LinkGroupVo> getLinkGroupList() {
		return linkGroupList;
	}


	public void setLinkGroupList(List<LinkGroupVo> linkGroupList) {
		this.linkGroupList = linkGroupList;
	}


	public List<LinkManJsonVo> getLinkManJsonList() {
		return linkManJsonList;
	}


	public void setLinkManJsonList(List<LinkManJsonVo> linkManJsonList) {
		this.linkManJsonList = linkManJsonList;
	}

	public String getReceiverList() {
		return receiverList;
	}

	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
	}


	

	
	
}
