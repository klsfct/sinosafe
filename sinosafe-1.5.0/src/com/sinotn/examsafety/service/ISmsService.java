package com.sinotn.examsafety.service;

import java.util.List;

import com.sinotn.examsafety.vo.LinkGroupVo;
import com.sinotn.examsafety.vo.LinkManJsonVo;
import com.sinotn.examsafety.vo.LinkManVo;
import com.sinotn.examsafety.vo.SmsInfoVo;
import com.sinotn.examsafety.vo.SmsTaskVo;
import com.sinotn.examsafety.vo.SmsTemplateVo;

public interface ISmsService {

	public final static String SUCCESS = "success";

	public final static String FAIL = "fail";

	// 消息类型 短信1
	public final static String TASK_TYPE_SMS = "1";
	// 消息类型 微信2
	public final static String TASK_TYPE_WX = "2";

	/**
	 * 根据地区查询联系人
	 * 
	 * @return
	 */
	public List<LinkManJsonVo> findLinkManJsonList(String area);

	/**
	 * 根据地区查询联系人组
	 * 
	 * @return
	 */
	public List<LinkGroupVo> findlinkGroupByArea(String area);

	/**
	 * 查询联系人组
	 * 
	 * @return
	 */
	public LinkGroupVo getlinkGroupById(LinkGroupVo linkGroupVo);

	/**
	 * 新建联系人组
	 * 
	 * @return
	 */
	public void savelinkGroup(LinkGroupVo linkGroupVo);

	/**
	 * 修改联系人组
	 * 
	 * @return
	 */
	public void updatelinkGroup(LinkGroupVo linkGroupVo);

	/**
	 * 删除联系人组
	 * 
	 * @return
	 */
	public void deletelinkGroup(LinkGroupVo linkGroupVo);

	/**
	 * 查询联系人
	 * 
	 * @return
	 */
	public LinkManVo getLinkManById(LinkManVo linkManVo);

	/**
	 * 新建联系人
	 * 
	 * @return
	 */
	public void savelinkMan(LinkManVo linkManVo);

	/**
	 * 修改联系人
	 * 
	 * @return
	 */
	public void updatelinkMan(LinkManVo linkManVo);

	/**
	 * 删除联系人
	 * 
	 * @return
	 */
	public void deletelinkMan(LinkManVo linkManVo);

	/**
	 * 查询短信
	 * 
	 * @return
	 */
	public SmsInfoVo getSmsInfoById(SmsInfoVo smsInfoVo);

	/**
	 * 发送短信
	 * 
	 * @return
	 */
	public void saveSendMessage(List<LinkManVo> linkManList, SmsInfoVo smsInfoVo);

	/**
	 * 新增指令模版
	 * 
	 * @param smsTemplateVo
	 */
	public void saveSmsTemplate(SmsTemplateVo smsTemplateVo, String creater,
			String createIp, String examArea, String examAreaName);

	/**
	 * 更新指令模版
	 * 
	 * @param smsTemplateVo
	 * @param creater
	 * @param createIp
	 */
	public void updateSmsTemplate(SmsTemplateVo smsTemplateVo, String creater,
			String createIp, String examArea, String examAreaName);

	/**
	 * 刪除指令模版
	 * 
	 * @param smsTemplateVo
	 */
	public void delSmsTemplate(SmsTemplateVo smsTemplateVo);

	/**
	 * 根据指令ID查询指令模版对象
	 * 
	 * @param smsTemplateVo
	 * @return
	 */
	public SmsTemplateVo findSmsTemplateById(SmsTemplateVo smsTemplateVo);

	/**
	 * 根据指令ID查询指令下发对象
	 * 
	 * @param smsTemplateVo
	 * @return
	 */
	public SmsTaskVo findSmsTaskById(SmsTaskVo smsTaskVo);

	/**
	 * 新增指令下发
	 * 
	 * @param smsTaskVo
	 */
	public void saveSmsTask(SmsTaskVo smsTaskVo, Integer length,
			String[] tempList, String creater, String createIp, String examArea, String examAreaName);

	/**
	 * 更新指令下发
	 * 
	 * @param smsTaskVo
	 */
	public void updateSmsTask(SmsTaskVo smsTaskVo);

	/**
	 * 删除指令下发
	 * 
	 * @param smsTaskVo
	 */
	public void delSmsTask(SmsTaskVo smsTaskVo);

	/**
	 * 根据联系人ID获取微信消息列表
	 * 
	 * @param linkManId
	 * @return
	 */
	public List<SmsInfoVo> findWxSmsInfoByLinkManId(Long linkManId);

	/**
	 * 修改已读状态和回复消息
	 * 
	 * @param smsInfoVo
	 */
	public String updateReadFlagAndReplyMsg(SmsInfoVo smsInfoVo);

	/**
	 * 修改已读状态和查询详细信息
	 * 
	 * @param smsInfoVo
	 */
	public String updateReadFlagAndFindReplyMsg(SmsInfoVo smsInfoVo);

	/**
	 * 发送短信和微信消息
	 * 
	 * @param smsTaskVo
	 */
	public void saveSendMessage(SmsTaskVo smsTaskVo);
	/**
	 * 单条发送短信
	 * @param creater
	 * @param createIp
	 * @param userId
	 * @param phone
	 * @param name
	 * @param areaId
	 * @param smsTaskVo
	 */
	public void saveMessageOnly(String creater, String createIp, String userId, String phone, String name, String areaId, SmsTaskVo smsTaskVo);

}
