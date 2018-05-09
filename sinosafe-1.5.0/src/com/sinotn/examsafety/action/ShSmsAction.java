package com.sinotn.examsafety.action;

import java.util.Date;
import java.util.List;

import com.sinotn.examsafety.service.ISmsService;
import com.sinotn.examsafety.vo.DeviceGpsInfoVo;
import com.sinotn.examsafety.vo.LinkManVo;
import com.sinotn.examsafety.vo.SmsInfoVo;
import com.sinotn.examsafety.vo.SmsTaskVo;
import com.sinotn.examsafety.vo.SmsTemplateVo;
import com.sinotn.examsafety.vo.SysUsersVo;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @Description:   指令管理
 * @Author:        李斌
 * @Version:       V1.0.0 
 * @Date:          2017年8月8日 下午2:57:45
 */
@SuppressWarnings("serial")
public class ShSmsAction extends BaseAction{
	
	// 指令业务注入
	private ISmsService smsServiceImpl = null;
	
	// 指令模版VO
	private SmsTemplateVo smsTemplateVo;
	
	private DeviceGpsInfoVo deviceGpsInfoVo;
	// 指令下发VO
	private SmsTaskVo smsTaskVo;
	
	// 指令记录VO
	private SmsInfoVo smsInfoVo;
	
	// 联系人VO
	private LinkManVo linkManVo;
	
	private String linkManIdStr;
	private String linkManPhone;
	private String linkManName;

	/**
	 * 跳转到新增指令模版界面
	 * @return
	 */
	public String gotoSaveSmsTemplate() {
		return SUCCESS;
		
	}
	
	/**
	 * 跳转到修改指令模版界面
	 * @return
	 */
	public String gotoUpdateSmsTemplate() {
		smsTemplateVo = this.smsServiceImpl.findSmsTemplateById(smsTemplateVo);
		return SUCCESS;
		
	}
	/**
	 * 执行新增指令模版
	 * @return
	 */
	public String saveSmsTemplate() {
		try {
			String creater = this.getLoginUser().getPrincipal();
			String createIp = this.getLoginUser().getLoginIp();
			String examArea = this.getLoginUser().getAreaId();
			String examAreaName = this.getLoginUser().getAreaName();
			this.smsServiceImpl.saveSmsTemplate(smsTemplateVo, creater, createIp, examArea, examAreaName);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 执行更新指令模版
	 * @return
	 */
	public String updateSmsTemplate() {
		try {
			String creater = this.getLoginUser().getPrincipal();
			String createIp = this.getLoginUser().getLoginIp();
			String examArea = this.getLoginUser().getAreaId();
			String examAreaName = this.getLoginUser().getAreaName();
			this.smsServiceImpl.updateSmsTemplate(smsTemplateVo, creater, createIp, examArea, examAreaName);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	
	/**
	 * 执行删除指令模版
	 * @return
	 */
	public String delSmsTemplate() {
		try {
			this.smsServiceImpl.delSmsTemplate(smsTemplateVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	
	/**
	 * 跳转到新增指令下发界面
	 * @return
	 */
	public String gotoSaveSmsTask() {
		return SUCCESS;
		
	}
	
	/**
	 * 跳转到修改指令下发界面
	 * @return
	 */
	public String gotoUpdateSmsTask() {
		smsTaskVo = this.smsServiceImpl.findSmsTaskById(smsTaskVo);
		return SUCCESS;
		
	}
	/**
	 * 执行新增指令下发
	 * @return
	 */
	public String saveSmsTask() {
		try {
			// 获取联系人字符串，将其转换成数组，循环向smsinfo表中添加数据
			String linkManIdStr = smsTaskVo.getReceiverList();
			if (null != linkManIdStr && !"".equals(linkManIdStr)) {
				String[] tempList = linkManIdStr.split(",");
				String creater = this.getLoginUser().getPrincipal();
				String createIp = this.getLoginUser().getLoginIp();
				String examArea = this.getLoginUser().getAreaId();
				String examAreaName = this.getLoginUser().getAreaName();
				this.smsServiceImpl.saveSmsTask(smsTaskVo, tempList.length, tempList, creater, createIp, examArea, examAreaName);
			}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 执行新增指令下发(微信端)
	 * @return
	 */
	public String saveWxSmsTask() {
		try {
			String linkManIdStr = smsTaskVo.getReceiverList();
			smsTaskVo.setTaskType("1");
			smsTaskVo.setWaitSendDate(new Date());
			if (null != linkManIdStr && !"".equals(linkManIdStr)) {
				String[] tempList = linkManIdStr.split(",");
				String creater = smsTaskVo.getCreater();
				String createIp = this.getRequestIp();
				String examArea = this.getAreaId();
				String examAreaName = "微信小程序";
				this.smsServiceImpl.saveSmsTask(smsTaskVo, tempList.length, tempList, creater, createIp, examArea, examAreaName);
			}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	
	/**
	 * 执行删除指令下发
	 * @return
	 */
	public String delSmsTask() {
		try {
			
			this.smsServiceImpl.delSmsTask(smsTaskVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg("删除失败：此任务不可删除！");
		}
		return SUCCESS;
		
	}
	/**
	 * 根据联系人ID获取微信消息列表 
	 * @return
	 */
	public String findWxSmsInfoByLinkManId(){
		try {
			Long linkManId = linkManVo.getId();
			List<SmsInfoVo> list = this.smsServiceImpl.findWxSmsInfoByLinkManId(linkManId);
			super.setAjaxBackDataMain(list);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		
		return SUCCESS;
	}
	/**
	 * 触发更新已读状态和回复消息
	 */
	public String updateReadFlagAndReplyMsg() {
		try {
			String resultStr = this.smsServiceImpl.updateReadFlagAndReplyMsg(smsInfoVo);
			super.setAjaxBackDataMain(resultStr);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		
		return SUCCESS;
	}
	/**
	 * 修改已读状态和查询详细信息
	 * @return
	 */
	public String updateReadFlagAndFindReplyMsg() {
		try {
			String resultStr = this.smsServiceImpl.updateReadFlagAndFindReplyMsg(smsInfoVo);
			super.setAjaxBackDataMain(resultStr);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		
		return SUCCESS;
	}
	/**
	 * 定时器发送消息(批量)
	 * @return
	 */
	public String sendMessage() {
		try {
			this.smsServiceImpl.saveSendMessage(smsTaskVo);
		} catch (Exception e) {
			e.printStackTrace();
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 跳转单条发送页面
	 * @return
	 */
	public String goSaveMessageOnly() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	/**
	 * 单条发送短信创建短信信息
	 * @return
	 */
	public String saveMessageOnly() {
		try {
			String creater = this.getLoginUser().getPrincipal();
			String createIp = this.getLoginUser().getLoginIp();
			String userId = this.getLoginUser().getUserId();
			String areaId = this.getLoginUser().getAreaId();
			
			smsTaskVo.setTaskType(SmsTaskVo.tyskType.ONE.getCode());//类型1短信
			this.smsServiceImpl.saveMessageOnly(creater, createIp, userId, 
					deviceGpsInfoVo.getLinkManPhone(), deviceGpsInfoVo.getLinkManName(), areaId, smsTaskVo);
		} catch (Exception e) {
			e.printStackTrace();
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	/**
	 * 单条发送短信创建短信信息
	 * @return
	 */
	public String saveMessageByExam() {
		SysUsersVo userVo = this.getLoginUser();
		String creater = "管理员";
		String createIp = "";
		String userId = "AREA_3199";
		String areaId = "AREA_3199";
		if(null!=userVo){
			creater = this.getLoginUser().getPrincipal();
			createIp = this.getLoginUser().getLoginIp();
			userId = this.getLoginUser().getUserId();
			areaId = this.getLoginUser().getAreaId();
		}
		
		smsTaskVo.setTaskType(SmsTaskVo.tyskType.ONE.getCode());//类型1短信
		this.smsServiceImpl.saveMessageOnly(creater, createIp, userId, 
				linkManPhone,linkManName, areaId, smsTaskVo);
		return SUCCESS;
		
	}
	
	/** ----------------------------------------getter/setter ------------------------*/
	public ISmsService getSmsServiceImpl() {
		return smsServiceImpl;
	}

	public void setSmsServiceImpl(ISmsService smsServiceImpl) {
		this.smsServiceImpl = smsServiceImpl;
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

	public SmsInfoVo getSmsInfoVo() {
		return smsInfoVo;
	}

	public void setSmsInfoVo(SmsInfoVo smsInfoVo) {
		this.smsInfoVo = smsInfoVo;
	}

	public LinkManVo getLinkManVo() {
		return linkManVo;
	}

	public void setLinkManVo(LinkManVo linkManVo) {
		this.linkManVo = linkManVo;
	}

	public String getLinkManIdStr() {
		return linkManIdStr;
	}

	public void setLinkManIdStr(String linkManIdStr) {
		this.linkManIdStr = linkManIdStr;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public DeviceGpsInfoVo getDeviceGpsInfoVo() {
		return deviceGpsInfoVo;
	}

	public void setDeviceGpsInfoVo(DeviceGpsInfoVo deviceGpsInfoVo) {
		this.deviceGpsInfoVo = deviceGpsInfoVo;
	}

}
