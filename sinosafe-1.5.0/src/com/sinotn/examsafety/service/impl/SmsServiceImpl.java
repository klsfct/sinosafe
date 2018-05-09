package com.sinotn.examsafety.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.dao.LinkGroupDAO;
import com.sinotn.examsafety.dao.LinkManDAO;
import com.sinotn.examsafety.dao.SmsInfoDAO;
import com.sinotn.examsafety.dao.SmsTaskDAO;
import com.sinotn.examsafety.dao.SmsTemplateDAO;
import com.sinotn.examsafety.po.LinkGroup;
import com.sinotn.examsafety.po.LinkMan;
import com.sinotn.examsafety.po.SmsInfo;
import com.sinotn.examsafety.po.SmsTask;
import com.sinotn.examsafety.po.SmsTemplate;
import com.sinotn.examsafety.service.ISmsService;
import com.sinotn.examsafety.vo.LinkGroupVo;
import com.sinotn.examsafety.vo.LinkManJsonVo;
import com.sinotn.examsafety.vo.LinkManVo;
import com.sinotn.examsafety.vo.SmsInfoVo;
import com.sinotn.examsafety.vo.SmsTaskVo;
import com.sinotn.examsafety.vo.SmsTemplateVo;

public class SmsServiceImpl implements ISmsService {

	
	private LinkGroupDAO linkGroupDAO = null;
	private LinkManDAO linkManDAO = null;
	private SmsInfoDAO smsInfoDAO = null;
	private String serverAddress = "http://114.255.71.158:8061/";
	private String userId = "tazx";
	private String password = "Basic6932NoTeBbz";
	private String epid = "109215";
	private Map<String, String> smsSignMap;
	/**
	 * 批量发送的手机号码最大上限
	 */
	private int mobileMaxsum = 50;
	
	
	private SmsTemplateDAO smsTemplateDAO = null;
	private SmsTaskDAO smsTaskDAO = null;
	/**
	 * 新增指令模版
	 */
	@Override
	public void saveSmsTemplate(SmsTemplateVo smsTemplateVo, String creater, String createIp,
			String examArea, String examAreaName) {
		if (null != smsTemplateVo) {
			SmsTemplate smsTemplate = new SmsTemplate();
			smsTemplate.setTemplateName(smsTemplateVo.getTemplateName());
			smsTemplate.setTemplateDescribe(smsTemplateVo.getTemplateDescribe());
			smsTemplate.setCreateDate(new Date());
			smsTemplate.setCreater(creater);
			smsTemplate.setCreateIp(createIp);
			smsTemplate.setExamArea(examArea);
			smsTemplate.setExamAreaName(examAreaName);
			this.smsTemplateDAO.save(smsTemplate);
		}
	}
	/**
	 * 更新指令模版
	 */
	@Override
	public void updateSmsTemplate(SmsTemplateVo smsTemplateVo, String creater,
			String createIp, String examArea, String examAreaName) {
		if (null != smsTemplateVo && !"".equals(smsTemplateVo.getTemplateId())) {
			SmsTemplate smsTemplate = this.smsTemplateDAO.findObjectById(smsTemplateVo.getTemplateId());
			if (null != smsTemplate) {
				smsTemplate.setTemplateName(smsTemplateVo.getTemplateName());
				smsTemplate.setTemplateDescribe(smsTemplateVo.getTemplateDescribe());
				smsTemplate.setCreater(creater);
				smsTemplate.setCreateIp(createIp);
				this.smsTemplateDAO.update(smsTemplate);
			}
		}
	}
	/**
	 * 删除指令模版
	 */
	@Override
	public void delSmsTemplate(SmsTemplateVo smsTemplateVo) {
		if (null != smsTemplateVo && !"".equals(smsTemplateVo.getTemplateId())) {
			SmsTemplate smsTemplate = this.smsTemplateDAO.findObjectById(smsTemplateVo.getTemplateId());
			if (null != smsTemplate) {
				this.smsTemplateDAO.delete(smsTemplate);
			}
		}
	}
	/**
	 * 根据指令ID查询指令模版对象
	 */
	@Override
	public SmsTemplateVo findSmsTemplateById(SmsTemplateVo smsTemplateVo) {
		SmsTemplate smsTemplate = this.smsTemplateDAO.findObjectById(smsTemplateVo.getTemplateId());
		SmsTemplateVo smsTemplateVo2 = new SmsTemplateVo(smsTemplate);
		return smsTemplateVo2;
	}
	/**
	 * 根据指令ID查询指令下发对象
	 */
	@Override
	public SmsTaskVo findSmsTaskById(SmsTaskVo smsTaskVo) {
		SmsTask smsTask = this.smsTaskDAO.findObjectById(smsTaskVo.getTaskId());
		SmsTaskVo smsTaskVo2 = new SmsTaskVo(smsTask);
		return smsTaskVo2;
	}
	/**
	 * 新增指令下发
	 */
	@Override
	public void saveSmsTask(SmsTaskVo smsTaskVo, Integer length, String[] tempList,
			String creater, String createIp, String examArea, String examAreaName) {
		if (null != smsTaskVo) {
			int taskTypeLength = smsTaskVo.getTaskType().length();
			// 根据长度判断
			if (taskTypeLength>1) {
				String [] stringArr= smsTaskVo.getTaskType().split(", ");
				for (int i = 0; i < stringArr.length; i++) {
					SmsTask smsTask = new SmsTask();
					String taskType = stringArr[i];
					smsTask.setTaskType(taskType);
					smsTask.setSmsDescribe(smsTaskVo.getSmsDescribe());
					Long smsLength = (long) smsTaskVo.getSmsDescribe().length();
					smsTask.setSmsLength(smsLength);
					smsTask.setSendCount(length.longValue());
					smsTask.setAcceptFlag(false);
					smsTask.setWaitSendDate(smsTaskVo.getWaitSendDate());
					smsTask.setExamArea(examArea);
					smsTask.setExamAreaName(examAreaName);
					this.smsTaskDAO.save(smsTask);
					this.saveSmsInfo(smsTaskVo, smsTask, tempList, creater);
				}
			}else {
				SmsTask smsTask = new SmsTask();
				String taskType = smsTaskVo.getTaskType();
				smsTask.setTaskType(taskType);
				smsTask.setSmsDescribe(smsTaskVo.getSmsDescribe());
				Long smsLength = (long) smsTaskVo.getSmsDescribe().length();
				smsTask.setSmsLength(smsLength);
				smsTask.setSendCount(length.longValue());
				smsTask.setAcceptFlag(false);
				smsTask.setWaitSendDate(smsTaskVo.getWaitSendDate());
				smsTask.setExamArea(examArea);
				smsTask.setExamAreaName(examAreaName);
				this.smsTaskDAO.save(smsTask);
				this.saveSmsInfo(smsTaskVo, smsTask, tempList, creater);
			}
			
		}
		
	}
	/**
	 * 新增指令记录
	 * @param smsTask
	 */
	public void saveSmsInfo(SmsTaskVo smsTaskVo,SmsTask smsTask, 
			String[] tempList, String creater) {
		// 新增指令下发记录
		for(String linkManIdStr:tempList){
			long linkManId = Long.parseLong(linkManIdStr);
			if (linkManId>=100000000) {
				linkManId = linkManId-100000000;
			}
			LinkMan linkMan = this.linkManDAO.findObjectById(linkManId);
			SmsInfo smsInfo = new SmsInfo();
			SmsTask smsTaskPo = this.smsTaskDAO.findObjectById(smsTask.getTaskId());
			String taskType = smsTask.getTaskType();
			// 如果消息类型是微信，则添加已读标注
			if ("2".equals(taskType)) {
				smsInfo.setReadFlag(false);
			}else {
				smsInfo.setReadFlag(null);
			}
			// 消息类型
			smsInfo.setTaskType(taskType);
			// 考试地区代码
			smsInfo.setExamArea(linkMan.getExamArea());
			// 短信内容
			smsInfo.setMessage(smsTaskVo.getSmsDescribe());
			// 电话
			smsInfo.setPhone(linkMan.getPhone());
			// 人员名
			smsInfo.setLinkman(linkMan.getName());
			// 组名
			smsInfo.setLinkgroup(linkMan.getLinkGroup().getName());
			// 创建人
			smsInfo.setCreater(creater);
			// 创建时间
			Date date = new Date();
			smsInfo.setCreateDate(date);
			// 短信长度
			smsInfo.setMsgNum((long)smsTaskVo.getSmsDescribe().length());
			// 发送状态
			smsInfo.setSendFlag(false);
			// 联系人ID
			smsInfo.setLinkManId(linkManId+"");
			// 微信ID
			smsInfo.setWexinId(linkMan.getWeixinId());
			// taskvo
			smsInfo.setSmsTask(smsTaskPo);
			this.smsInfoDAO.save(smsInfo);
		}
	}
	/**
	 * 更新指令下发
	 */
	@Override
	public void updateSmsTask(SmsTaskVo smsTaskVo) {
		if (null != smsTaskVo && !"".equals(smsTaskVo.getTaskId())) {
			SmsTask smsTask = this.smsTaskDAO.findObjectById(smsTaskVo.getTaskId());
			if (null != smsTask) {
				smsTask.setSmsDescribe(smsTaskVo.getSmsDescribe());
				smsTask.setTaskType(smsTaskVo.getTaskType());
				smsTask.setSmsLength(smsTaskVo.getSmsLength());
				smsTask.setSendCount(smsTaskVo.getSendCount());
				smsTask.setSuccessCount(smsTaskVo.getSuccessCount());
				smsTask.setAcceptFlag(false);
				smsTask.setWaitSendDate(smsTaskVo.getWaitSendDate());
				this.smsTaskDAO.update(smsTask);
			}
		}
		
	}
	/**
	 * 删除指令下发
	 */
	@Override
	public void delSmsTask(SmsTaskVo smsTaskVo) {
		if (null != smsTaskVo && !"".equals(smsTaskVo.getTaskId())) {
			SmsTask smsTask = this.smsTaskDAO.findObjectById(smsTaskVo.getTaskId());
			// 已处理的不能删除
			boolean status = smsTask.getAcceptFlag();
			if (!status) {
				this.smsTaskDAO.delete(smsTask);
			}else {
				throw new  RuntimeException("已找到子记录不可删除！");
			}
		}
		
	}
	
	/**
	 * 根据联系人ID获取微信消息列表 
	 */
	@Override
	public List<SmsInfoVo> findWxSmsInfoByLinkManId(Long linkManId) {
		List<SmsInfoVo> listVo = new ArrayList<SmsInfoVo>();
		List<SmsInfo> listPo = this.smsInfoDAO.findWxSmsInfoByLinkManId(linkManId);
		if (null != listPo && listPo.size()>0) {
			for (SmsInfo smsInfo : listPo) {
				SmsInfoVo smsInfoVo = new SmsInfoVo(smsInfo);
				listVo.add(smsInfoVo);
			}
		}
		return listVo;
	}
	/**
	 * 更新已读状态和回复消息
	 * @param smsInfoVo
	 */
	@Override
	public String updateReadFlagAndReplyMsg(SmsInfoVo smsInfoVo) {
		String result ="";
		if (null != smsInfoVo && !"".equals(smsInfoVo.getId())) {
			String replyMsg = smsInfoVo.getReplyMsg();
			SmsInfo smsInfo = this.smsInfoDAO.findObjectById(smsInfoVo.getId());
			smsInfo.setReadFlag(true);
			if (null != replyMsg && !"".equals(replyMsg)) {
				smsInfo.setReplyMsg(replyMsg);
				result = "回复成功!";
			}
			this.smsInfoDAO.update(smsInfo);
			
		}
		return result;
	}
	/**
	 * 修改已读状态和查询详细信息
	 */
	public String  updateReadFlagAndFindReplyMsg(SmsInfoVo smsInfoVo){
		if (null != smsInfoVo && !"".equals(smsInfoVo.getId())) {
			SmsInfo smsInfo = this.smsInfoDAO.findObjectById(smsInfoVo.getId());
			if(smsInfo != null){
				smsInfo.setReadFlag(true);
				return smsInfo.getReplyMsg();
			}
		}
		return null;
	}
	
	/**
	 * 发送微信和短信消息（群发）
	 */
	@Override
	public void saveSendMessage(SmsTaskVo smsTaskVo) {
		// 1.先查询出来未发送的短信指令，按照发送状态和等待发送时间判断
		List<SmsTask> smsTaskVoList = this.smsTaskDAO.findSmsTaskVoListBySednFlagAndWaitDate(); 
		if (null != smsTaskVoList && smsTaskVoList.size()>0) {
			for (SmsTask smsTask : smsTaskVoList) {
				smsTask.setAcceptFlag(true);
				smsTask.setAcceptDate(new Date());
				String taskType = smsTask.getTaskType();
				String taskId = smsTask.getTaskId();
				// 查询字段
				String propertyName = "smsTask.taskId";
				if (null != taskType && !"".equals(taskType)) {
					//判断是微信还是短信
					if (SmsTaskVo.tyskType.ONE.getCode().equals(taskType)) {
						// 根据指令id查询smsinfo
						List<SmsInfo> smsInfoList = this.smsInfoDAO.findByProperty(propertyName, taskId);
						if (null != smsInfoList && smsInfoList.size()>0) {
							//执行发送短信
							if (null != smsInfoList && smsInfoList.size() > 0) {
								if (null != smsInfoList.get(0) && null != smsInfoList.get(0).getMessage()
										&& smsInfoList.get(0).getMessage().length() > 0) {

									String tempSign = this.getSmsSignMap().get(
											smsInfoList.get(0).getExamArea());
									String tempMessage = tempSign + smsInfoList.get(0).getMessage();
									int msgNum = (tempMessage.length() + 69) / 70;
									// 获取短信群发最大列表数
									int size = smsInfoList.size();
									// 计算步长
									int length = (size + mobileMaxsum - 1) / mobileMaxsum;
									// 定义手机号码构成对象
									StringBuffer stringBuffer = new StringBuffer();
									// 定义临时200个一组集合
									List<SmsInfo> tempList = null;
									// 循环构造手机号码
									for (int i = 0; i < length; i++) {
										int fromIndex = i * mobileMaxsum;
										int toIndex = fromIndex + mobileMaxsum;
										toIndex = toIndex > size ? size : toIndex;
										tempList = smsInfoList.subList(fromIndex, toIndex);
										// 构造一组手机号码
										int sum = 0;
										for (SmsInfo temp : tempList) {
											sum++;
											stringBuffer.append(temp.getPhone());
											if (sum != tempList.size()) {
												stringBuffer.append(",");
											}
										}
										String mobiles = stringBuffer.toString();
										// 短信群发
										// 获得短信平台唯一标识
										String seqNumber = DateUtils
												.getCurFormatDate(DateUtils.C_YYYYMMDDHHMMSS);
										String[] retValue = this.sendMsg(mobiles, tempMessage,
												seqNumber);

										// 向短信平台提交数据返回标志
										boolean sendFlag = false;

										if (retValue[0].equals(SUCCESS)) {
											sendFlag = true;
										}

										// 更新短发记录发送信息
										for (SmsInfo temp1 : tempList) {
											temp1.setSendFlag(sendFlag);
											temp1.setMessage(tempMessage);
											temp1.setMsgNum(Long.parseLong(msgNum + ""));
											this.getSmsInfoDAO().update(temp1);
										}
										// 清空一组手机号码
										stringBuffer.setLength(0);
									}
								}
							}
						}
						// 执行发送短信任务
					}else{
						//微信
					}
				}
			}
		}
	}
	/**
	 * 单条发送短信
	 */
	@Override
	public void saveMessageOnly(String creater, String createIp, String userId, String phone, String name, String areaId, SmsTaskVo smsTaskVo) {
		SmsTask smsTask = new SmsTask();
		String taskType = smsTaskVo.getTaskType();
		smsTask.setTaskType(taskType);
		smsTask.setSmsDescribe(smsTaskVo.getSmsDescribe());
		Long smsLength = (long) smsTaskVo.getSmsDescribe().length();
		smsTask.setSmsLength(smsLength);
		smsTask.setSendCount(1L);//单条
		smsTask.setAcceptFlag(false);
		smsTask.setWaitSendDate(new Date());
		this.smsTaskDAO.save(smsTask);
		//登录人员的信息
//		LinkMan linkMan = this.linkManDAO.findObjectById(userId);
		
		SmsInfo smsInfo = new SmsInfo();
		// 如果消息类型是微信，则添加已读标注
		if ("2".equals(taskType)) {
			smsInfo.setReadFlag(false);
		}else {
			smsInfo.setReadFlag(null);
		}
		// 消息类型
		smsInfo.setTaskType(taskType);
		// 考试地区代码
		smsInfo.setExamArea(areaId);
		// 短信内容
		smsInfo.setMessage(smsTaskVo.getSmsDescribe());
		// 电话
		smsInfo.setPhone(phone);
		// 人员名
		smsInfo.setLinkman(name);
		// 组名
		smsInfo.setLinkgroup(null);
		// 创建人
		smsInfo.setCreater(creater);
		// 创建时间
		Date date = new Date();
		smsInfo.setCreateDate(date);
		// 短信长度
		smsInfo.setMsgNum((long)smsTaskVo.getSmsDescribe().length());
		// 发送状态
		smsInfo.setSendFlag(false);
		// 联系人ID
		smsInfo.setLinkManId(null);
		// 微信ID
		smsInfo.setWexinId(null);
		// taskvo
		smsInfo.setSmsTask(smsTask);
		this.smsInfoDAO.save(smsInfo);
	}
	public List<LinkManJsonVo> findLinkManJsonList(String area) {
		List<LinkManJsonVo> linkManJsonVoList = new ArrayList<LinkManJsonVo>();
		List<LinkGroup> linkGroupList = this.getLinkGroupDAO().findByProperty(
				"examArea", area);
		if (null != linkGroupList && linkGroupList.size() > 0) {
			for (LinkGroup temp : linkGroupList) {
				LinkManJsonVo linkManJsonVo1 = new LinkManJsonVo();
				linkManJsonVo1.setId(temp.getId());
				linkManJsonVo1.setParentId(0L);
				linkManJsonVo1.setName(temp.getName());
				linkManJsonVo1.setOpen("true");
				linkManJsonVoList.add(linkManJsonVo1);
				List<LinkMan> linkManList = this.getLinkManDAO()
						.findByProperty("linkGroup.id", temp.getId());
				if (null != linkManList && linkManList.size() > 0) {
					for (LinkMan temp2 : linkManList) {
						LinkManJsonVo linkManJsonVo2 = new LinkManJsonVo();
						linkManJsonVo2.setId(temp2.getId() + 100000000);
						linkManJsonVo2.setParentId(temp.getId());
						linkManJsonVo2.setName(temp2.getName());
						linkManJsonVoList.add(linkManJsonVo2);
					}
				}
			}
			return linkManJsonVoList;
		}
		return null;
	}

	public List<LinkGroupVo> findlinkGroupByArea(String area) {
		List<LinkGroupVo> linkGroupVoList = new ArrayList<LinkGroupVo>();

		List<LinkGroup> linkGroupList = this.getLinkGroupDAO().findByProperty(
				"examArea", area);
		if (null != linkGroupList && linkGroupList.size() > 0) {
			for (LinkGroup temp : linkGroupList) {
				linkGroupVoList.add(new LinkGroupVo(temp));
			}
			return linkGroupVoList;
		}
		return null;
	}

	public LinkGroupVo getlinkGroupById(LinkGroupVo linkGroupVo) {
		if (null != linkGroupVo && null != linkGroupVo.getId()) {
			LinkGroup linkGroup = this.getLinkGroupDAO().findObjectById(
					linkGroupVo.getId());
			if (null != linkGroup) {
				return new LinkGroupVo(linkGroup);
			}
		}
		return null;
	}

	public LinkManVo getLinkManById(LinkManVo linkManVo) {
		if (null != linkManVo && null != linkManVo.getId()) {
			LinkMan linkMan = this.getLinkManDAO().findObjectById(
					linkManVo.getId());
			if (null != linkMan) {
				return new LinkManVo(linkMan);
			}
		}
		return null;
	}

	public SmsInfoVo getSmsInfoById(SmsInfoVo smsInfoVo) {
		if (null != smsInfoVo && null != smsInfoVo.getId()) {
			SmsInfo smsInfo = this.getSmsInfoDAO().findObjectById(
					smsInfoVo.getId());
			if (null != smsInfo) {
				return new SmsInfoVo(smsInfo);
			}
		}
		return null;
	}

	public void savelinkGroup(LinkGroupVo linkGroupVo) {
		LinkGroup linkGroup = new LinkGroup();
		linkGroup.setName(linkGroupVo.getName());
		linkGroup.setRemark(linkGroupVo.getRemark());
		linkGroup.setCreater(linkGroupVo.getCreater());
		linkGroup.setCreateDate(linkGroupVo.getCreateDate());
		linkGroup.setExamArea(linkGroupVo.getExamArea());

		this.getLinkGroupDAO().save(linkGroup);
	}

	public void updatelinkGroup(LinkGroupVo linkGroupVo) {
		if (null != linkGroupVo && null != linkGroupVo.getId()) {
			LinkGroup linkGroup = this.getLinkGroupDAO().findObjectById(
					linkGroupVo.getId());
			if (null != linkGroup) {
				linkGroup.setName(linkGroupVo.getName());
				linkGroup.setRemark(linkGroupVo.getRemark());
				linkGroup.setCreater(linkGroupVo.getCreater());
				linkGroup.setCreateDate(linkGroupVo.getCreateDate());
				linkGroup.setExamArea(linkGroupVo.getExamArea());
				this.getLinkGroupDAO().update(linkGroup);
			}
		}
	}

	public void deletelinkGroup(LinkGroupVo linkGroupVo) {
		if (null != linkGroupVo && null != linkGroupVo.getId()) {
			LinkGroup linkGroup = this.getLinkGroupDAO().findObjectById(
					linkGroupVo.getId());
			this.getLinkGroupDAO().delete(linkGroup);
		}

	}

	public void savelinkMan(LinkManVo linkManVo) {
		LinkMan linkMan = new LinkMan();
		if (null != linkManVo.getLinkGroup()) {
			LinkGroup linkGroup = new LinkGroup(linkManVo.getLinkGroup()
					.getId());
			linkMan.setLinkGroup(linkGroup);
		}
		linkMan.setName(linkManVo.getName());
		linkMan.setPhone(linkManVo.getPhone());
		linkMan.setRemark(linkManVo.getRemark());
		linkMan.setCreater(linkManVo.getCreater());
		linkMan.setCreateDate(linkManVo.getCreateDate());
		linkMan.setExamArea(linkManVo.getExamArea());
		this.getLinkManDAO().save(linkMan);
	}

	public void updatelinkMan(LinkManVo linkManVo) {
		if (null != linkManVo && null != linkManVo.getId()) {
			LinkMan linkMan = this.getLinkManDAO().findObjectById(
					linkManVo.getId());
			if (null != linkMan) {
				if (null != linkManVo.getLinkGroup()) {
					LinkGroup linkGroup = new LinkGroup(linkManVo
							.getLinkGroup().getId());
					linkMan.setLinkGroup(linkGroup);
				}
				linkMan.setName(linkManVo.getName());
				linkMan.setPhone(linkManVo.getPhone());
				linkMan.setRemark(linkManVo.getRemark());
				linkMan.setCreater(linkManVo.getCreater());
				linkMan.setCreateDate(linkManVo.getCreateDate());
				linkMan.setExamArea(linkManVo.getExamArea());
				this.getLinkManDAO().update(linkMan);
			}
		}

	}

	public void deletelinkMan(LinkManVo linkManVo) {
		if (null != linkManVo && null != linkManVo.getId()) {
			LinkMan linkMan = this.getLinkManDAO().findObjectById(
					linkManVo.getId());
			if (null != linkMan) {
				this.getLinkManDAO().delete(linkMan);
			}
		}
	}

	public void saveSendMessage(List<LinkManVo> linkManList, SmsInfoVo smsInfoVo) {
		if (null != linkManList && linkManList.size() > 0) {
			if (null != smsInfoVo && null != smsInfoVo.getMessage()
					&& smsInfoVo.getMessage().length() > 0) {

				String tempSign = this.getSmsSignMap().get(
						smsInfoVo.getExamArea());
				String tempMessage = tempSign + smsInfoVo.getMessage();
				int msgNum = (tempMessage.length() + 69) / 70;
				// 获取短信群发最大列表数
				int size = linkManList.size();
				// 计算步长
				int length = (size + mobileMaxsum - 1) / mobileMaxsum;
				// 定义手机号码构成对象
				StringBuffer stringBuffer = new StringBuffer();
				// 定义临时200个一组集合
				List<LinkManVo> tempList = null;
				// 循环构造手机号码
				for (int i = 0; i < length; i++) {
					int fromIndex = i * mobileMaxsum;
					int toIndex = fromIndex + mobileMaxsum;
					toIndex = toIndex > size ? size : toIndex;
					tempList = linkManList.subList(fromIndex, toIndex);
					// 构造一组手机号码
					int sum = 0;
					for (LinkManVo temp : tempList) {
						/*LinkMan linkMan = this.getLinkManDAO().findObjectById(
								temp.getId());*/
						sum++;
						stringBuffer.append(temp.getPhone());
						if (sum != tempList.size()) {
							stringBuffer.append(",");
						}
					}
					String mobiles = stringBuffer.toString();
					// 短信群发
					// 获得短信平台唯一标识
					String seqNumber = DateUtils
							.getCurFormatDate(DateUtils.C_YYYYMMDDHHMMSS);
					String[] retValue = this.sendMsg(mobiles, tempMessage,
							seqNumber);

					// 向短信平台提交数据返回标志
					boolean sendFlag = false;

					if (retValue[0].equals(SUCCESS)) {
						sendFlag = true;
					}

					// 更新短发记录发送信息
					for (LinkManVo temp1 : tempList) {
//						LinkMan linkMan2 = this.getLinkManDAO().findObjectById(
//								temp1.getId());
						try{
							SmsInfo tempSms = new SmsInfo();
							tempSms.setId(smsInfoVo.getId());
							tempSms.setLinkman(temp1.getName());
							tempSms.setPhone(temp1.getPhone());
							tempSms.setSendFlag(sendFlag);
							tempSms.setMessage(tempMessage);
							tempSms.setCreater(smsInfoVo.getCreater());
							tempSms.setCreateDate(smsInfoVo.getCreateDate());
							tempSms.setExamArea(smsInfoVo.getExamArea());
							tempSms.setMsgNum(Long.parseLong(msgNum + ""));
							this.getSmsInfoDAO().update(tempSms);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					// 清空一组手机号码
					stringBuffer.setLength(0);
				}
			}
		}

	}
	private String[] sendMsg(String mobiles, String message, String seqNumber) {
		// 构造短信URL
		StringBuffer sb = new StringBuffer(serverAddress);
		sb.append("?username=").append(userId);
		sb.append("&password=").append(password);
		try {
			sb.append("&message=").append(URLEncoder.encode(message, "gb2312"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("&phone=").append(mobiles);
		sb.append("&epid=").append(epid);
		sb.append("&linkid=").append(seqNumber);
		sb.append("&subcode=").append("");
		String sendMessageUrl = sb.toString();
		// System.out.println(sendMessageUrl);
		// 向短信平台POST数据
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(sendMessageUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "gb2312");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 读取返回内容

		StringBuffer retBuffer = new StringBuffer();
		try {
			/*
			 * BufferedReader reader = new BufferedReader(new InputStreamReader(
			 * con.getInputStream(), "gb2312"));
			 */
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String temp;
			while ((temp = reader.readLine()) != null) {
				retBuffer.append(temp);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		String retFlag = retBuffer.toString();
		String[] retValue = new String[2];
		/*
		 * System.out .println(
		 * "========================================================================== "
		 * ); System.out.println("retFlag====" + retFlag + "==========");
		 * System.out .println(
		 * "========================================================================== "
		 * );
		 */
		if (retFlag.equals("00")) {
			retValue[0] = SUCCESS;
			// System.out.println("retValue[0]====SUCCESS");
		} else {
			retValue[0] = FAIL;
			// System.out.println("retValue[0]====FAIL");
		}
		/*
		 * System.out .println(
		 * "========================================================================== "
		 * );
		 */
		retValue[1] = retFlag;
		return retValue;
	}

	public LinkGroupDAO getLinkGroupDAO() {
		return linkGroupDAO;
	}

	public void setLinkGroupDAO(LinkGroupDAO linkGroupDAO) {
		this.linkGroupDAO = linkGroupDAO;
	}

	public LinkManDAO getLinkManDAO() {
		return linkManDAO;
	}

	public void setLinkManDAO(LinkManDAO linkManDAO) {
		this.linkManDAO = linkManDAO;
	}

	public SmsInfoDAO getSmsInfoDAO() {
		return smsInfoDAO;
	}

	public void setSmsInfoDAO(SmsInfoDAO smsInfoDAO) {
		this.smsInfoDAO = smsInfoDAO;
	}

	public Map<String, String> getSmsSignMap() {
		smsSignMap = new HashMap<String, String>();
		smsSignMap.put("AREA", "【考点安全指挥平台】");
		smsSignMap.put("AREA_31", "【上海司考办】");
		smsSignMap.put("AREA_3199", "【上海司考办】");
		return smsSignMap;
	}

	public void setSmsSignMap(Map<String, String> smsSignMap) {
		this.smsSignMap = smsSignMap;
	}

	public SmsTemplateDAO getSmsTemplateDAO() {
		return smsTemplateDAO;
	}

	public void setSmsTemplateDAO(SmsTemplateDAO smsTemplateDAO) {
		this.smsTemplateDAO = smsTemplateDAO;
	}
	public SmsTaskDAO getSmsTaskDAO() {
		return smsTaskDAO;
	}
	public void setSmsTaskDAO(SmsTaskDAO smsTaskDAO) {
		this.smsTaskDAO = smsTaskDAO;
	}
	
	public static void main(String[] args) {
		SmsServiceImpl serviceImpl = new SmsServiceImpl();
		serviceImpl.sendMsg("13121717948", "【上海司考办】测试", "1123123232323");
	}

}
