package com.sinotn.examsafety.action;


import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.common.utils.WeixinUtils;
import com.sinotn.examsafety.service.sh.ExamPlaceServiceImpl;
import com.sinotn.examsafety.service.sh.ShLinkManServiceImpl;
import com.sinotn.examsafety.vo.ExamExceptionVo;
import com.sinotn.examsafety.vo.ExamPlaceVo;
import com.sinotn.examsafety.vo.ExamineeMissVo;
import com.sinotn.examsafety.vo.ExamineePunishVo;
import com.sinotn.examsafety.vo.ExamineeVo;
import com.sinotn.examsafety.vo.LinkGroupVo;
import com.sinotn.examsafety.vo.LinkManJsonVo;
import com.sinotn.examsafety.vo.LinkManLoginVo;
import com.sinotn.examsafety.vo.LinkManVo;
import com.sinotn.examsafety.vo.PaperMoveVo;
import com.sinotn.examsafety.vo.SmsInfoVo;
import com.sinotn.examsafety.vo.SysUsersVo;
import com.sinotn.examsafety.vo.WxLinkGroupVo;


public class ShLinkManAction extends BaseAction {
	private ShLinkManServiceImpl linkManServiceImpl = null;
	private ExamPlaceServiceImpl examPlaceServiceImpl;
	
	private LinkGroupVo linkGroupVo;
	private LinkManVo linkManVo;
	private SmsInfoVo smsInfoVo;
	private List<LinkManVo> linkManList;
	private List<LinkGroupVo> linkGroupList;
	
	private List<LinkManJsonVo> linkManJsonList;
	private PaperMoveVo papermoveVo;
	private ExamineeVo examineeVo;
	private ExamineePunishVo examineePunishVo;
	private ExamExceptionVo examExceptionVo;
	private ExamineeMissVo examineeMissVo;
	private String receiverList;
	
	private String username;
	
	private String appid;
	private String secret;
	private String jsCode;
	
	private String fileContentType = "gif,jpg,jpeg,png,bmp";
	private List<ExamPlaceVo> examPlaceVoList;
	
	/**
	 * 上传文件对象
	 */
	private File upload;
	/**
	 * 上传文件类型
	 */
	private String uploadContentType;
	/**
	 * 上传文件名称
	 */
	private String uploadFileName;
	
	/**
	 * 保存文件的目录路径(通过注入)
	 */
	private String savePath;
	
	/**
	 * 服务器路径
	 */
	private String realPath;
	
	/**
	 * 新建联系人组
	 * @return
	 */
	public String addLinkGroup(){
		try {
			linkGroupVo.setCreateDate(DateUtils.getCurrentDate());
			linkGroupVo.setCreater(this.getLoginUser().getUserId());
			linkGroupVo.setExamArea(this.getLoginUser().getAreaId());
			this.getLinkManServiceImpl().savelinkGroup(linkGroupVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 修改联系人组
	 * @return
	 */
	public String modifyLinkGroup(){
		try {
			linkGroupVo.setCreateDate(DateUtils.getCurrentDate());
			linkGroupVo.setCreater(this.getLoginUser().getUserId());
			linkGroupVo.setExamArea(this.getLoginUser().getAreaId());
			this.getLinkManServiceImpl().updatelinkGroup(linkGroupVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 根据ID查询组信息
	 */
	public String findLinkGroupById(){
		try {
			linkGroupVo = this.getLinkManServiceImpl().getlinkGroupById(linkGroupVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	/**
	 * 根据ID查询人员信息
	 */
	 public String findLinkManById(){
			try {
				linkManVo = this.getLinkManServiceImpl().getLinkManById(linkManVo);
				this.findLinkGroupList();
			} catch (Exception e) {
				super.setAjaxBackDataErrorMsg(e);
			}
			return SUCCESS;
		}
	
	/**
	 * 删除联系人组
	 * @return
	 */
	public String removeLinkGroup(){
		try {
			this.getLinkManServiceImpl().deletelinkGroup(linkGroupVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到联系人修改页面
	 * @return
	 */
	public String gotoUpdatelinkMan(){
		linkGroupList = this.getLinkManServiceImpl().findlinkGroupByArea(this.getLoginUser().getAreaId());
		linkManVo = this.getLinkManServiceImpl().getLinkManById(linkManVo);
		return SUCCESS;
	}
	
	/**
	 * 新建联系人
	 * @return
	 */
	public String addLinkMan(){
		try {
			linkManVo.setCreateDate(DateUtils.getCurrentDate());
			linkManVo.setCreater(this.getLoginUser().getUserId());
			linkManVo.setExamArea(this.getLoginUser().getAreaId());
			this.getLinkManServiceImpl().savelinkMan(linkManVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	
	/**
	 * 修改联系人
	 * @return
	 */
	public String modifyLinkMan(){
		try {
			linkManVo.setCreateDate(DateUtils.getCurrentDate());
			linkManVo.setCreater(this.getLoginUser().getUserId());
			linkManVo.setExamArea(this.getLoginUser().getAreaId());
			this.getLinkManServiceImpl().updatelinkMan(linkManVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	 
	/**
	 * 删除联系人
	 * @return
	 */
	public String removeLinkMan(){
		try {
			this.getLinkManServiceImpl().deletelinkMan(linkManVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
		
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	public String modifyReplyLinkManToPass(){
		try {
			this.getLinkManServiceImpl().modifylinkManToPass(linkManVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有的分组信息
	 * @return
	 */
	public String findLinkGroupList(){
		linkGroupList = this.getLinkManServiceImpl().findLinkGroupVoList();
		ExamPlaceVo examPlaceVo = new ExamPlaceVo();
		examPlaceVo.setExamArea(this.getLoginUser().getAreaId());
		examPlaceVoList = this.getExamPlaceServiceImpl().getExamPlaceByExamArea(examPlaceVo);
		return SUCCESS;
	}
	
	/**
	 * 空跳转
	 * @return
	 */
	public String getLinkGroup(){
		return SUCCESS;
	}
	////////////////////////////////////////////////  weixin star  ////////////////////////////////////
	/**
	 * 工作人员登陆
	 */
	public String updateLinkManByUserPass(){
		try {
			LinkManVo vo = linkManVo;
			linkManVo = this.getLinkManServiceImpl().updateLinkManByPhone(vo);
			if(null != linkManVo){
				//修改设备表
				this.getLinkManServiceImpl().updateDevicePhoneInfoVoByVo(vo);
				//返回小程序的值
				super.setAjaxBackDataMain(linkManVo);
			}
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 工作人员签到
	 */
	public String updateLinkManLogin(){
		try {
			this.getLinkManServiceImpl().updateLinkManLoginBylinkManVo(linkManVo);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 利用小程序接口 通过code获取微信ID
	 * @return
	 */
	public String getOpenIdByCode(){
		JSONObject jsonObject =  WeixinUtils.getXiaochengxuOpenId(this.getAppid(), this.getSecret(), this.getJsCode());
		if (null != jsonObject) {
			try {
				String openId = jsonObject.getString("openid");
				//String openId = jsonObject.getString("unionid");
				super.setAjaxBackDataMain(openId);
			} catch (Exception e) {
				/*String errcode = jsonObject.getString("errcode");*/
				String errMsg = "";
				try {
					errMsg = jsonObject.getString("errmsg");
				} catch (Exception e2) {
					errMsg = "系统错误";
				}
				super.setAjaxBackDataMain(errMsg);
			}
		}
		return SUCCESS;
	}

	/**
	 * 获取联系人列表（微信小程序JSON格式）
	 * @return
	 */
	public String findWxLinkManJsonList(){
		try {
			List<WxLinkGroupVo> wxLinkGroupVoList = this.getLinkManServiceImpl().findWxLinkManJsonList(linkManVo);
			super.setAjaxBackDataMain(wxLinkGroupVoList);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 通过准考证查询考生信息（最多保留10条）
	 * @return
	 */
	public String findExamineeByInfo(){
		try {
			List<ExamineeVo>  ee = this.getLinkManServiceImpl().findExamineeByLicece(examineeVo);
			super.setAjaxBackDataMain(ee);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 异常上报
	 * @return
	 */
	public String addExamException(){
		try {
			this.getLinkManServiceImpl().addExamExceptionByVo(examExceptionVo);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 缺考上报
	 * @return
	 */
	public String updateExamMiss(){
		try {
			this.getLinkManServiceImpl().updateExamMiss(examineeMissVo);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 查询缺考消息
	 * @return
	 */
	public String findExamMiss(){
		try {
			examineeMissVo = this.getLinkManServiceImpl().findExamMiss(examineeMissVo);
			super.setAjaxBackDataMain(examineeMissVo);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 查询签到信息
	 * @return
	 */
	public String  findLinkManLoginByManId(){
		try {
			 List<LinkManLoginVo>  linkManLoginVos = this.getLinkManServiceImpl().findLinkManLoginByManId(linkManVo);
			 super.setAjaxBackDataMain(linkManLoginVos);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 通过ID
	 * 查询所有异常信息
	 * @return
	 */
	public String gotoViewExamException(){
		examExceptionVo = this.getLinkManServiceImpl().findExamExceptionById(examExceptionVo);
		return SUCCESS;
	}
	/**
	 * 查询所有异常信息
	 * @return
	 */
	public String  findExamException(){
		try {
			List<ExamExceptionVo> examExceptionVos = this.getLinkManServiceImpl().findExamExceptionListByAll();
			super.setAjaxBackDataMain(examExceptionVos);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 查询异常信息根据上报用户ID
	 * @return
	 */
	public String  findExamExceptionByManId(){
		try {
			List<ExamExceptionVo> examExceptionVos = this.getLinkManServiceImpl().findExamExceptionListByManId(examExceptionVo);
			super.setAjaxBackDataMain(examExceptionVos);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	/**
	 * 违纪上传
	 * @return
	 */
	public String  addExamPunishInfo(){
		try {
			this.getLinkManServiceImpl().addExamineePunishBy(examineePunishVo);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	
	public String gotoViewExamineePunish(){
		examineePunishVo = this.getLinkManServiceImpl().findExamineePunishById(examineePunishVo);
		return SUCCESS;
	}
	
	/**
	 * 查询违纪上报
	 * @return
	 */
	public String  findExamPunishInfo(){
		try {
			List<ExamineePunishVo> examineePunishVos = this.getLinkManServiceImpl().findExamineePunishBy(examineePunishVo);
			super.setAjaxBackDataMain(examineePunishVos);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 试卷交接
	 * @return
	 */
	public String addPaperMoveInfo(){
		try {
			this.getLinkManServiceImpl().addPaperMoveBy(papermoveVo);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询试卷交接
	 * @return
	 */
	public String  findPaperMoveInfo(){
		try {
			List<PaperMoveVo> examineePunishVos = this.getLinkManServiceImpl().findPaperMoveBy(papermoveVo);
			super.setAjaxBackDataMain(examineePunishVos);
		} catch (Exception e) {
			super.setAjaxBackDataMain(e);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 微信照片上传
	 * @return
	 */
	public String wxUploadImg(){
		long sizeMax = 1024 * 1014 * 100;
		// 创建上传文件路径
		if (upload == null) return null;
		//提取扩展名
		String fileExt = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(fileContentType.split(",")).contains(fileExt)){
			super.setAjaxBackDataErrorMsg("上传文件格式错误！");
			return SUCCESS;
		}
		if(upload.length() > sizeMax){
			super.setAjaxBackDataErrorMsg("上传文件过大！");
			return SUCCESS;
		}
		
		String realPath = ServletActionContext.getServletContext().getRealPath("/wxPhoto");
		
		File uploadPath = new File(realPath);// 上传文件目录
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		uploadFileName = "wx_" + DateUtils.getCurFormatDate("yyyyMMddHHmmssSSS") + "." + fileExt;
		
		File destFile = new File(uploadPath, uploadFileName);
		try{
			FileUtils.copyFile(upload, destFile);
		} catch (Exception e) {
			e.printStackTrace();
			super.setAjaxBackDataErrorMsg(e);
		}
		super.setAjaxBackDataMain(uploadFileName);
		return SUCCESS;
	}
	/**
	 * 根据登录用户地区ID查询联系人列表 
	 * @return
	 */
	public String getLinkManByLoginArea() {
		try{
			SysUsersVo sessionSysUsersVo = this.getLoginUser();
			String areaId = "";
			if(sessionSysUsersVo!=null){
				areaId = this.getLoginUser().getAreaId();
			}
			List<LinkManVo> list = this.linkManServiceImpl.getLinkManByLoginArea(areaId);
			super.setAjaxBackDataMain(list);
		} catch (Exception e) {
			super.setAjaxBackDataErrorMsg(e);
		}
		
		return SUCCESS;
		
	}
	
	
	////////////////////////////////////////////////weixin end  ////////////////////////////////////
	
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

	public ShLinkManServiceImpl getLinkManServiceImpl() {
		return linkManServiceImpl;
	}

	public void setLinkManServiceImpl(ShLinkManServiceImpl linkManServiceImpl) {
		this.linkManServiceImpl = linkManServiceImpl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getJsCode() {
		return jsCode;
	}

	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}

	public ExamineeVo getExamineeVo() {
		return examineeVo;
	}

	public void setExamineeVo(ExamineeVo examineeVo) {
		this.examineeVo = examineeVo;
	}

	public ExamExceptionVo getExamExceptionVo() {
		return examExceptionVo;
	}

	public void setExamExceptionVo(ExamExceptionVo examExceptionVo) {
		this.examExceptionVo = examExceptionVo;
	}

	public ExamineePunishVo getExamineePunishVo() {
		return examineePunishVo;
	}

	public void setExamineePunishVo(ExamineePunishVo examineePunishVo) {
		this.examineePunishVo = examineePunishVo;
	}

	public PaperMoveVo getPapermoveVo() {
		return papermoveVo;
	}

	public void setPapermoveVo(PaperMoveVo papermoveVo) {
		this.papermoveVo = papermoveVo;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public ExamineeMissVo getExamineeMissVo() {
		return examineeMissVo;
	}

	public void setExamineeMissVo(ExamineeMissVo examineeMissVo) {
		this.examineeMissVo = examineeMissVo;
	}

	public List<ExamPlaceVo> getExamPlaceVoList() {
		return examPlaceVoList;
	}

	public void setExamPlaceVoList(List<ExamPlaceVo> examPlaceVoList) {
		this.examPlaceVoList = examPlaceVoList;
	}

	public ExamPlaceServiceImpl getExamPlaceServiceImpl() {
		return examPlaceServiceImpl;
	}

	public void setExamPlaceServiceImpl(ExamPlaceServiceImpl examPlaceServiceImpl) {
		this.examPlaceServiceImpl = examPlaceServiceImpl;
	}
	
	
	
}
