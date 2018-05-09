package com.sinotn.examsafety.service.sh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.MD5;
import com.sinotn.examsafety.dao.DevicePhoneInfoDAO;
import com.sinotn.examsafety.dao.ExamExceptionDAO;
import com.sinotn.examsafety.dao.ExamPlaceDAO;
import com.sinotn.examsafety.dao.ExamineeDAO;
import com.sinotn.examsafety.dao.ExamineeMissDAO;
import com.sinotn.examsafety.dao.ExamineePunishDAO;
import com.sinotn.examsafety.dao.LinkGroupDAO;
import com.sinotn.examsafety.dao.LinkManDAO;
import com.sinotn.examsafety.dao.LinkManLoginDAO;
import com.sinotn.examsafety.dao.PaperMoveDAO;
import com.sinotn.examsafety.po.DevicePhoneInfo;
import com.sinotn.examsafety.po.ExamException;
import com.sinotn.examsafety.po.ExamPlace;
import com.sinotn.examsafety.po.Examinee;
import com.sinotn.examsafety.po.ExamineeMiss;
import com.sinotn.examsafety.po.ExamineePunish;
import com.sinotn.examsafety.po.LinkGroup;
import com.sinotn.examsafety.po.LinkMan;
import com.sinotn.examsafety.po.LinkManLogin;
import com.sinotn.examsafety.po.PaperMove;
import com.sinotn.examsafety.vo.ExamExceptionVo;
import com.sinotn.examsafety.vo.ExamineeMissVo;
import com.sinotn.examsafety.vo.ExamineePunishVo;
import com.sinotn.examsafety.vo.ExamineeVo;
import com.sinotn.examsafety.vo.LinkGroupVo;
import com.sinotn.examsafety.vo.LinkManJsonVo;
import com.sinotn.examsafety.vo.LinkManLoginVo;
import com.sinotn.examsafety.vo.LinkManVo;
import com.sinotn.examsafety.vo.PaperMoveVo;
import com.sinotn.examsafety.vo.WxLinkGroupVo;
import com.sinotn.examsafety.vo.WxLinkManVo;

public class ShLinkManServiceImpl {

	private ExamPlaceDAO examPlaceDAO;	
	private LinkGroupDAO linkGroupDAO = null;
	private LinkManDAO linkManDAO = null;
	private DevicePhoneInfoDAO devicePhoneInfoDAO = null;
	private LinkManLoginDAO linkManLoginDAO = null;
	private ExamineeDAO examineeDAO = null;
	private ExamExceptionDAO examExceptionDAO = null;
	private ExamineePunishDAO examineePunishDAO = null;
	private PaperMoveDAO paperMoveDAO = null;
	private ExamineeMissDAO examineeMissDAO;
	
	private static final String PASSWORD = "123456";
	/**
	 * 批量发送的手机号码最大上限
	 */
	private int mobileMaxsum = 50;

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

	/**
	 * 获取联系人列表（微信小程序JSON格式）
	 * @return
	 */
	public List<WxLinkGroupVo> findWxLinkManJsonList(LinkManVo linkManVo) {
		
		if(null!=linkManVo && null!=linkManVo.getId()){
			LinkMan linkMan = this.getLinkManDAO().findObjectById(linkManVo.getId());
			if(null!=linkMan){
				String areaId = linkMan.getExamArea();
				List<WxLinkGroupVo> wxLinkGroupVoList = new ArrayList<WxLinkGroupVo>();
				List<LinkGroup> linkGroupList = this.getLinkGroupDAO().findByProperty(
						"examArea", areaId);
				if (null != linkGroupList && linkGroupList.size() > 0) {
					for (LinkGroup temp : linkGroupList) {
						WxLinkGroupVo wxLinkGroupVo = new WxLinkGroupVo();
						wxLinkGroupVo.setGroupName(temp.getName());
						wxLinkGroupVo.setIsGroupSel("false");
						List<LinkMan> linkManList = this.getLinkManDAO()
								.findByProperty("linkGroup.id", temp.getId(),"id");
						if (null != linkManList && linkManList.size() > 0) {
							List<WxLinkManVo> wxLinkManVoList = new ArrayList<WxLinkManVo>();
							for (LinkMan temp2 : linkManList) {
								WxLinkManVo wxLinkManVo = new WxLinkManVo();
								wxLinkManVo.setLinkManId(temp2.getId());
								wxLinkManVo.setName(temp2.getName());
								wxLinkManVo.setFamilyName(temp2.getName().substring(0, 1));
								wxLinkManVo.setTel(temp2.getPhone());
								wxLinkManVo.setIsPersonSel(false);
								wxLinkManVo.setIsPersonShow(false);
								wxLinkManVoList.add(wxLinkManVo);
							}
							wxLinkGroupVo.setPersonList(wxLinkManVoList);
						}
						wxLinkGroupVoList.add(wxLinkGroupVo);
					}
				}
				return wxLinkGroupVoList;
			}
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


	public void savelinkGroup(LinkGroupVo linkGroupVo) {
		LinkGroup linkGroup = new LinkGroup();
		linkGroup.setName(linkGroupVo.getName());
		linkGroup.setRemark(linkGroupVo.getRemark());
		linkGroup.setCreater(linkGroupVo.getCreater());
		linkGroup.setExamArea(linkGroupVo.getExamArea());
		linkGroup.setCreateDate(linkGroupVo.getCreateDate());
		linkGroup.setRoleCode(linkGroupVo.getRoleCode());
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
				linkGroup.setRoleCode(linkGroupVo.getRoleCode());
				this.getLinkGroupDAO().update(linkGroup);
			}
		}
	}

	public void deletelinkGroup(LinkGroupVo linkGroupVo) {
		if (null != linkGroupVo && null != linkGroupVo.getId()) {
			List<LinkMan>  linkMans= this.getLinkManDAO().findByProperty("linkGroup.id", linkGroupVo.getId());
			if(linkMans != null && linkMans.size() > 0){
				throw new RuntimeException("组内有成员，不能删除组信息！");
			}
			LinkGroup linkGroup = this.getLinkGroupDAO().findObjectById(
					linkGroupVo.getId());
			this.getLinkGroupDAO().delete(linkGroup);
		}

	}
	
	public void savelinkMan(LinkManVo linkManVo) {
		LinkMan linkMan = new LinkMan();
		if (null != linkManVo.getLinkGroup()) {
			LinkGroup linkGroup = this.getLinkGroupDAO().findObjectById(linkManVo.getLinkGroup().getId());
			linkMan.setLinkGroup(linkGroup);
		}
		if (null != linkManVo.getExamPlaceVo()) {
			ExamPlace examPlace = this.getExamPlaceDAO().findObjectById(linkManVo.getExamPlaceVo().getExamPlace());
			linkMan.setExamPlace(examPlace);
		}
		linkMan.setName(linkManVo.getName().trim());
		linkMan.setPhone(linkManVo.getPhone());
		linkMan.setRemark(linkManVo.getRemark().trim());
		linkMan.setCreater(linkManVo.getCreater());
		linkMan.setCreateDate(linkManVo.getCreateDate());
		linkMan.setExamArea(linkManVo.getExamArea());
		linkMan.setPassword(MD5.md5s(linkManVo.getPassword()));
		this.getLinkManDAO().save(linkMan);
	}

	public void updatelinkMan(LinkManVo linkManVo) {
		if (null != linkManVo && null != linkManVo.getId()) {
			LinkMan linkMan = this.getLinkManDAO().findObjectById(
					linkManVo.getId());
			if (null != linkMan) {
				if (null != linkManVo.getLinkGroup()) {
					LinkGroup linkGroup = this.getLinkGroupDAO().findObjectById(linkManVo.getLinkGroup().getId());
					linkMan.setLinkGroup(linkGroup);
				}
				if (null != linkManVo.getExamPlaceVo()) {
					ExamPlace examPlace = this.examPlaceDAO.findObjectById(linkManVo.getExamPlaceVo().getExamPlace());
					linkMan.setExamPlace(examPlace);
				}
				linkMan.setName(linkManVo.getName().trim());
				linkMan.setPhone(linkManVo.getPhone());
				linkMan.setRemark(linkManVo.getRemark().trim());
				linkMan.setCreater(linkManVo.getCreater());
				linkMan.setCreateDate(linkManVo.getCreateDate());
				this.getLinkManDAO().update(linkMan);
			}
		}

	}

	public void deletelinkMan(LinkManVo linkManVo) {
		if (null != linkManVo && null != linkManVo.getId()) {
			List<LinkManLogin> linkManLogins = this.getLinkManLoginDAO().findByProperty("linkMan.id", linkManVo.getId());
			if(linkManLogins != null && linkManLogins.size() >0){
				throw new RuntimeException("已签到不能删除人员信息！");
			}
			LinkMan linkMan = this.getLinkManDAO().findObjectById(
					linkManVo.getId());
			if (null != linkMan) {
				this.getLinkManDAO().delete(linkMan);
			}
		}
	}
	
	/**
	 * 重置固定密码
	 * @param linkManVo
	 */
	public void modifylinkManToPass(LinkManVo linkManVo) {
		if (null != linkManVo && null != linkManVo.getId()) {
			LinkMan linkMan = this.getLinkManDAO().findObjectById(
					linkManVo.getId());
			if (null != linkMan) {
				linkMan.setPassword(MD5.md5s(PASSWORD));
				this.getLinkManDAO().update(linkMan);
			}
		}
	}


	/**
	 * 根据手机号和密码判断联系人
	 * @param linkManVo
	 */
	public LinkManVo updateLinkManByPhone(LinkManVo linkManVo){
		List<LinkMan> linkMans= this.getLinkManDAO().findByProperty("phone", linkManVo.getPhone());
		if(linkMans != null && linkMans.size() > 0){
			LinkMan linkMan  = linkMans.get(0);
			if(!linkMan.getPassword().equals(MD5.md5s(linkManVo.getPassword()))){
				throw new RuntimeException("密码错误！");
			}else{
				linkMan.setWeixinId(linkManVo.getWeixinId());
				//返回信息
				return new LinkManVo(linkMan);
			}
		}else{
			throw new RuntimeException("用户名错误！");
		}
	}
	
	
	/**
	 * 签到
	 * @param linkManVo
	 */
	public void  updateLinkManLoginBylinkManVo(LinkManVo linkManVo){
		if(linkManVo != null && linkManVo.getId() != null){
			LinkMan linkMan = this.getLinkManDAO().findObjectById(linkManVo.getId());
			if(linkMan != null){
				String latStr = linkManVo.getLastLoginLat();
				String lngStr = linkManVo.getLastLoginLng();
				//wgs84坐标转换成百度坐标
				JSONObject jsonObject = BaiduMapConsts.wgs84ToBd09ll(latStr, lngStr);
				JSONObject jsonObject2 = jsonObject.optJSONArray("result").getJSONObject(0);
				String baiduLng = jsonObject2.optString("x");
				String baiduLat = jsonObject2.optString("y");
				//更新联系人表（link_man）
				linkMan.setLastLoginAddr(linkManVo.getLastLoginAddr());
				linkMan.setLastLoginLat(baiduLat);
				linkMan.setLastLoginLng(baiduLng);
				linkMan.setLastLoginDate(DateUtils.getCurrentDate());
				linkMan.setWeixinId(linkManVo.getWeixinId());
				this.getLinkManDAO().update(linkMan);
				
				//添加签到表（link_man_login）
				LinkManLogin linkManLogin = new LinkManLogin();
				linkManLogin.setLinkMan(linkMan);
				linkManLogin.setLoginDate(DateUtils.getCurrentDate());
				linkManLogin.setLoginAddr(linkManVo.getLastLoginAddr());
				linkManLogin.setLoginLat(baiduLat);
				linkManLogin.setLoginLng(baiduLng);
				linkManLogin.setLoginPhone(linkMan.getPhone());
				//通过手机号查询手机系统
				if(linkMan.getPhone() != null){
					List<DevicePhoneInfo> devicePhoneInfos = this.getDevicePhoneInfoDAO().findByProperty("phone", linkMan.getPhone());
					if(devicePhoneInfos != null && devicePhoneInfos.size() > 0){
						linkManLogin.setLonginOs(devicePhoneInfos.get(0).getPhoneOs());
					}
				}
				linkManLogin.setLonginWeixinId(linkMan.getWeixinId());
				this.getLinkManLoginDAO().save(linkManLogin);
			}
			else{
				throw new RuntimeException("联系人不存在！");
			}
		}
		else{
			throw new RuntimeException("ID为NULL，签到失败！");
		}
		
	}
	
	public void updateDevicePhoneInfoVoByVo(LinkManVo linkManVo){
		if(linkManVo.getWeixinId() != null){
			List<DevicePhoneInfo> devicePhoneInfos =  this.getDevicePhoneInfoDAO().findByProperty("weixinId", linkManVo.getWeixinId());
			
			if(devicePhoneInfos != null && devicePhoneInfos.size() > 0){
				DevicePhoneInfo info = devicePhoneInfos.get(0);
				info.setPhone(linkManVo.getPhone());
				info.setPhoneModel(linkManVo.getPhoneModel());
				info.setPhoneOs(linkManVo.getPhoneOs());
				info.setWeixinName(linkManVo.getWeixinName());
				info.setCreateDate(DateUtils.getCurrentDate());
				this.getDevicePhoneInfoDAO().update(info);
			}
			else{
				DevicePhoneInfo info = new DevicePhoneInfo();
				info.setPhone(linkManVo.getPhone());
				info.setPhoneModel(linkManVo.getPhoneModel());
				info.setPhoneOs(linkManVo.getPhoneOs());
				info.setWeixinName(linkManVo.getWeixinName());
				info.setWeixinId(linkManVo.getWeixinId());
				info.setCreateDate(DateUtils.getCurrentDate());
				this.getDevicePhoneInfoDAO().save(info);
			}
		}
	}
	/**
	 * 查询所有的分组
	 * @return
	 */
	public List<LinkGroupVo> findLinkGroupVoList(){
		List<LinkGroupVo> groupVos = new ArrayList<LinkGroupVo>();
		List<LinkGroup>  groups = this.getLinkGroupDAO().findByInstance(new LinkGroup());
		if(groups != null && groups.size() > 0){
			for(LinkGroup po : groups){
				groupVos.add(new LinkGroupVo(po));
			}
			return groupVos;
		}else{
			return null;
		}
	}
	
	public List<ExamineeVo> findExamineeByLicece(ExamineeVo licence){
		if(licence != null){
			String str="";
			if(licence.getLicence() != null){
				if(!(licence.getLicence().trim().equals("undefined") || licence.getLicence().trim().equals(""))){
					str +=" licence = '"+licence.getLicence()+"' and ";
				}			
			}
			if(licence.getIdentity() != null){
				if(!(licence.getIdentity().trim().equals("undefined") || licence.getIdentity().trim().equals(""))){
					str +=" identity = '"+licence.getIdentity()+"' and ";
				}
			}
			if(licence.getExamineeName() != null){
				if(!(licence.getExamineeName().trim().equals("undefined") || licence.getExamineeName().trim().equals(""))){
					str +=" examineeName = '"+licence.getExamineeName()+"' and ";
				}
			}
			if(str.length() > 0){
				str = "from Examinee  where "+str;
				str = str.substring(0, str.length()-4);
				str += " order by examineeName ";
				List<ExamineeVo> examineeVos = (List<ExamineeVo>)this.getExamineeDAO().findListByHql(str);
				if(examineeVos.size() > 10){
					List<ExamineeVo> examineeVos2 = new ArrayList<ExamineeVo>();
					for(int i=0 ; i<10 ;i++){
						examineeVos2.add(examineeVos.get(i));
					}
					return examineeVos2;
				}
				return examineeVos;
			}
			else{
				throw new RuntimeException("值不能为空！");
			}
		}
		else{
			throw new RuntimeException("值不能为空！");
		}
	}
	
	public void addExamExceptionByVo(ExamExceptionVo exceptionVo){
		if(exceptionVo == null){
			throw new RuntimeException("值不能为空！");
		}
		ExamException examException = new ExamException();
		examException.setExceptionName(exceptionVo.getExceptionName());
		examException.setExceptionDescript(exceptionVo.getExceptionDescript());
		examException.setCreateDate(DateUtils.getCurrentDate());
		examException.setAlterUserName(exceptionVo.getAlterUserName());
		examException.setExamPlaceName(exceptionVo.getExamPlaceName());
		examException.setLinkManId(exceptionVo.getLinkManId());
		examException.setPhotoPath(exceptionVo.getPhotoPath());
		
		examException.setLoginAddr(exceptionVo.getLoginAddr());
		examException.setLoginLat(exceptionVo.getLoginLat());
		examException.setLoginLng(exceptionVo.getLoginLng());
		this.getExamExceptionDAO().save(examException);
	}
	
	public List<LinkManLoginVo> findLinkManLoginByManId(LinkManVo linkManVo){
		List<LinkManLoginVo> linkManLoginVos = new ArrayList<LinkManLoginVo>();
		List<LinkManLogin> linkManLogins = this.getLinkManLoginDAO().findByProperty("linkMan.id", linkManVo.getId() , "loginDate desc");
		if(linkManLogins != null){
			for(LinkManLogin linkManLogin : linkManLogins){
				linkManLoginVos.add(new LinkManLoginVo(linkManLogin));
			}
			if(linkManLoginVos.size() > 10){
				List<LinkManLoginVo> linkManLoginVos2 = new ArrayList<LinkManLoginVo>();
				for(int i=0 ; i<10 ;i++){
					linkManLoginVos2.add(linkManLoginVos.get(i));
				}
				return linkManLoginVos2;
			}
			return linkManLoginVos;
		}else{
			return null;
		}
	}
	
	public ExamExceptionVo findExamExceptionById(ExamExceptionVo examExceptionVo){
		if(examExceptionVo != null){
			ExamException examException = this.getExamExceptionDAO().findObjectById(examExceptionVo.getExceptionId());
			if(examException != null){
				return new ExamExceptionVo(examException);
			}
		}
		return null;
	}
	
	public List<ExamExceptionVo> findExamExceptionListByAll(){
		List<ExamExceptionVo> examExceptionVos = new ArrayList<ExamExceptionVo>();
		List<ExamException> examExceptions = this.getExamExceptionDAO().findByInstance(new ExamException());
		if(examExceptions != null && examExceptions.size() > 0){
			for(ExamException exception : examExceptions){
				examExceptionVos.add(new ExamExceptionVo(exception));
			}
			return examExceptionVos;
		}
		else{
			return null;
		}
	}
	
	public List<ExamExceptionVo> findExamExceptionListByManId(ExamExceptionVo exceptionVo){
		List<ExamExceptionVo> examExceptionVos = new ArrayList<ExamExceptionVo>();
		List<ExamException> examExceptions = this.getExamExceptionDAO().findByProperty("linkManId", exceptionVo.getLinkManId(),"createDate desc");
		if(examExceptions != null && examExceptions.size() > 0){
			for(ExamException exception : examExceptions){
				examExceptionVos.add(new ExamExceptionVo(exception));
			}
			return examExceptionVos;
		}
		else{
			return null;
		}
	}

	/**
	 * 微信添加上报违纪信息
	 * @param examineePunishVo
	 */
	public void addExamineePunishBy(ExamineePunishVo examineePunishVo){
		if(examineePunishVo != null){
			ExamineePunish punish = new ExamineePunish();
			punish.setExaminee(new Examinee(examineePunishVo.getExaminee().getLicence()));
			punish.setExamYear(DateUtils.getYear()+"");
			punish.setPunishActionName(examineePunishVo.getPunishActionName());
			punish.setPunishName(examineePunishVo.getPunishName());
			punish.setSubject(examineePunishVo.getSubject());
			punish.setAlterUserName(examineePunishVo.getAlterUserName());
			punish.setLinkManId(examineePunishVo.getLinkManId());
			punish.setLoginAddr(examineePunishVo.getLoginAddr());
			punish.setLoginLat(examineePunishVo.getLoginLat());
			punish.setLoginLng(examineePunishVo.getLoginLng());
			punish.setCreateDate(DateUtils.getCurrentDate());
			punish.setRemarks(examineePunishVo.getRemarks());
			punish.setPhotoPath(examineePunishVo.getPhotoPath());
			
			this.getExamineePunishDAO().save(punish);
		}
		else{
			throw new RuntimeException("值不能为空！");
		}
		
	}
	
	/**
	 * 根据Id查询违纪信息
	 */
	public ExamineePunishVo findExamineePunishById(ExamineePunishVo examineePunishVo){
		if(examineePunishVo != null){
			ExamineePunish examineePunish =  this.getExamineePunishDAO().findObjectById(examineePunishVo.getId());
			if(examineePunish != null){
				return new ExamineePunishVo(examineePunish);
			}
		}
		return null;
	}
	
	/**
	 * 微信根据上报人的ID查询上传的违纪信息
	 * @param linkManVo
	 * @return
	 */
	public List<ExamineePunishVo> findExamineePunishBy(ExamineePunishVo examineePunishVo){
		if(examineePunishVo != null ){
			ExamineePunishVo punishVo = new ExamineePunishVo();
			List<ExamineePunish> punish = this.getExamineePunishDAO().findByProperty("linkManId", examineePunishVo.getLinkManId() , "createDate desc");
			if(punish != null){
				List<ExamineePunishVo> examineePunishVos = new ArrayList<ExamineePunishVo>();
				for(ExamineePunish examineePunish : punish){
					punishVo = new ExamineePunishVo(examineePunish);
					examineePunishVos.add(punishVo);
				}
				return examineePunishVos;
			}
			else{
				return null;
			}
		}
		else{
			throw new RuntimeException("值不能为空！");
		}
		
	}
	/**
	 * 微信添加试卷交接
	 */
	public void addPaperMoveBy(PaperMoveVo papermoveVo){
		if(papermoveVo != null){
			LinkMan  linkMan = this.getLinkManDAO().findObjectById(papermoveVo.getLinkManId());
			if(linkMan == null){
				throw new RuntimeException("上报人信息上传不全，请重新上传！");
			}
			
			PaperMove paperMove = new PaperMove();
			paperMove.setSubjectName(papermoveVo.getSubjectName());
			paperMove.setLinkManId(linkMan.getId());
			paperMove.setLinkManName(linkMan.getName());
			paperMove.setExamPlace(linkMan.getExamPlace());
			paperMove.setMoveType(papermoveVo.getMoveType());
			
			paperMove.setMoveAddr(papermoveVo.getMoveAddr());
			paperMove.setMoveLat(papermoveVo.getMoveLat());
			paperMove.setMoveLng(papermoveVo.getMoveLng());
			paperMove.setCareateDate(DateUtils.getCurrentDate());
			
			this.getPaperMoveDAO().save(paperMove);
		}
		else{
			throw new RuntimeException("值不能为空！");
		}
	}
	
	/**
	 * 查询试卷交接信息
	 * @param papermoveVo
	 * @return
	 */
	public List<PaperMoveVo> findPaperMoveBy(PaperMoveVo papermoveVo){
		if(papermoveVo != null ){
			 List<PaperMove> punish = this.getPaperMoveDAO().findByProperty("linkManId", papermoveVo.getLinkManId(),"careateDate desc");
			if(punish != null){
				List<PaperMoveVo> examineePunishVos = new ArrayList<PaperMoveVo>();
				for(PaperMove examineePunish : punish){
					examineePunishVos.add(new PaperMoveVo(examineePunish));
				}
				return examineePunishVos;
			}
			else{
				return null;
			}
		}
		else{
			throw new RuntimeException("值不能为空！");
		}
		
	}
	/**
	 * 根据登录用户查询联系人列表
	 * @param loginArea
	 * @return
	 */
	public List<LinkManVo> getLinkManByLoginArea(String loginArea) {
		List<LinkManVo> listVo = new ArrayList<LinkManVo>();
		// 今天
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = fmt.format(new Date());
		// 查询机关督考工作人员List
		String [] propertyNames = {"examArea", "linkGroup.id"};// 参数名数组
		Object [] values = {loginArea, 1042L};// 参数值数组
		List<LinkMan> listPo = this.linkManDAO.findByPropertyArry(propertyNames, values, "id asc");
		if (null != listPo && listPo.size()>0) {
			for (LinkMan linkMan : listPo) {
				String linkName = linkMan.getName();
				LinkManVo linkManVo = new LinkManVo(linkMan);
				linkManVo.setName(linkName);
				// 最后登录时间当天
				if (null != linkMan.getLastLoginDate()) {
					String lastLoginDate = fmt.format(linkMan.getLastLoginDate());
					// 判断是否相同
					if (todayDate.equals(lastLoginDate)) {
						linkManVo.setSignStatus("on");
					}else {
						linkManVo.setSignStatus("off");
					}
				}
				else {
					linkManVo.setSignStatus("off");
				}
				
				listVo.add(linkManVo);
			}
				
			
		}
		return listVo;
	}
	/**
	 * 缺考上报
	 * @param examineeMissVo
	 */
	public void updateExamMiss(ExamineeMissVo examineeMissVo) {
		if(examineeMissVo != null){
			 List<ExamineeMiss> listExamineeMiss = this.getExamineeMissDAO().findByProperty("examPlaceId", examineeMissVo.getExamPlaceId());
			 if (null != listExamineeMiss && listExamineeMiss.size()>0) {
				ExamineeMiss examineeMiss = listExamineeMiss.get(0);
				examineeMiss.setExamPlaceName(examineeMissVo.getExamPlaceName());
				examineeMiss.setMissSum1(examineeMissVo.getMissSum1());
				examineeMiss.setMissSum2(examineeMissVo.getMissSum2());
				examineeMiss.setMissSum3(examineeMissVo.getMissSum3());
				examineeMiss.setMissSum4(examineeMissVo.getMissSum4());
			 }else{
				/*ExamineeMiss examineeMiss = new ExamineeMiss();
				examineeMiss.setExamArea(examineeMissVo.getExamArea());
				examineeMiss.setExamPlaceId(examineeMissVo.getExamPlaceId());
				examineeMiss.setExamPlaceName(examineeMissVo.getExamPlaceName());
				examineeMiss.setExamineeSum(examineeMissVo.getExamineeSum());
				examineeMiss.setCreater(examineeMissVo.getCreater());
				examineeMiss.setCreateDate(new Date());
				examineeMiss.setMissSum1(examineeMissVo.getMissSum1());
				examineeMiss.setMissSum2(examineeMissVo.getMissSum2());
				examineeMiss.setMissSum3(examineeMissVo.getMissSum3());
				examineeMiss.setMissSum4(examineeMissVo.getMissSum4());
				this.getExamineeMissDAO().save(examineeMiss);*/
				throw new RuntimeException("未找到数据！");
			}
		}else{
			throw new RuntimeException("值不能为空！");
		}

	}
	/**
	 * 查询缺考信息
	 * @param examineeMissVo
	 */
	public ExamineeMissVo findExamMiss(ExamineeMissVo examineeMissVo) {
		if(examineeMissVo != null ){
			 List<ExamineeMiss> listExamineeMiss = this.getExamineeMissDAO().findByProperty("examPlaceId", examineeMissVo.getExamPlaceId());
			 if (null != listExamineeMiss && listExamineeMiss.size()>0) {
				 ExamineeMiss examineeMiss = listExamineeMiss.get(0);
				 return new ExamineeMissVo(examineeMiss);
			 }else{
				return null;
			}
		}else{
			throw new RuntimeException("值不能为空！");
		}
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

	public DevicePhoneInfoDAO getDevicePhoneInfoDAO() {
		return devicePhoneInfoDAO;
	}

	public void setDevicePhoneInfoDAO(DevicePhoneInfoDAO devicePhoneInfoDAO) {
		this.devicePhoneInfoDAO = devicePhoneInfoDAO;
	}

	public LinkManLoginDAO getLinkManLoginDAO() {
		return linkManLoginDAO;
	}

	public void setLinkManLoginDAO(LinkManLoginDAO linkManLoginDAO) {
		this.linkManLoginDAO = linkManLoginDAO;
	}

	public ExamineeDAO getExamineeDAO() {
		return examineeDAO;
	}

	public void setExamineeDAO(ExamineeDAO examineeDAO) {
		this.examineeDAO = examineeDAO;
	}

	public ExamExceptionDAO getExamExceptionDAO() {
		return examExceptionDAO;
	}

	public void setExamExceptionDAO(ExamExceptionDAO examExceptionDAO) {
		this.examExceptionDAO = examExceptionDAO;
	}

	public ExamineePunishDAO getExamineePunishDAO() {
		return examineePunishDAO;
	}

	public void setExamineePunishDAO(ExamineePunishDAO examineePunishDAO) {
		this.examineePunishDAO = examineePunishDAO;
	}

	public PaperMoveDAO getPaperMoveDAO() {
		return paperMoveDAO;
	}

	public void setPaperMoveDAO(PaperMoveDAO paperMoveDAO) {
		this.paperMoveDAO = paperMoveDAO;
	}

	public ExamineeMissDAO getExamineeMissDAO() {
		return examineeMissDAO;
	}

	public void setExamineeMissDAO(ExamineeMissDAO examineeMissDAO) {
		this.examineeMissDAO = examineeMissDAO;
	}
	
	public ExamPlaceDAO getExamPlaceDAO() {
		return examPlaceDAO;
	}

	public void setExamPlaceDAO(ExamPlaceDAO examPlaceDAO) {
		this.examPlaceDAO = examPlaceDAO;
	}

	public static void main(String[] args) {
		String latStr = "31.19508";
		String lngStr = "121.45598";
		JSONObject jsonObject = BaiduMapConsts.wgs84ToBd09ll(latStr, lngStr);
		JSONObject jsonObject2 = jsonObject.optJSONArray("result").getJSONObject(0);
		String baiduLng = jsonObject2.optString("x");
		String baiduLat = jsonObject2.optString("y");
		System.out.println(baiduLng+","+baiduLat);
	}
}
