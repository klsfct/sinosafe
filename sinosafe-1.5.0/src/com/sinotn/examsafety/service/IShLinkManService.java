package com.sinotn.examsafety.service;

import java.util.List;

import com.sinotn.examsafety.vo.LinkGroupVo;
import com.sinotn.examsafety.vo.LinkManJsonVo;
import com.sinotn.examsafety.vo.LinkManVo;
import com.sinotn.examsafety.vo.SmsInfoVo;



public interface IShLinkManService {
	
	
	public final static String SUCCESS = "success";

	public final static String FAIL = "fail";
	
	/**
	 * 根据地区查询联系人
	 * @return
	 */
	public List<LinkManJsonVo> findLinkManJsonList(String area);
	
	/**
	 * 根据地区查询联系人组
	 * @return
	 */
	public List<LinkGroupVo> findlinkGroupByArea(String area);
	
	/**
	 * 查询联系人组
	 * @return
	 */
	public LinkGroupVo getlinkGroupById(LinkGroupVo linkGroupVo);
	/**
	 * 新建联系人组
	 * @return
	 */
	public void savelinkGroup(LinkGroupVo linkGroupVo);
	
	/**
	 * 修改联系人组
	 * @return
	 */
	public void updatelinkGroup(LinkGroupVo linkGroupVo);
	
	
	/**
	 * 删除联系人组
	 * @return
	 */
	public void deletelinkGroup(LinkGroupVo linkGroupVo);
	
	
	/**
	 * 查询联系人
	 * @return
	 */
	public LinkManVo getLinkManById(LinkManVo linkManVo);
	/**
	 * 新建联系人
	 * @return
	 */
	public void savelinkMan(LinkManVo linkManVo);
	
	
	/**
	 * 修改联系人
	 * @return
	 */
	public void updatelinkMan(LinkManVo linkManVo);
	
	/**
	 * 删除联系人
	 * @return
	 */
	public void deletelinkMan(LinkManVo linkManVo);
	
	
	
	/**
	 * 根据手机号和密码判断登陆用户是否存在
	 * username：手机号
	 * password：密码
	 */
	public String findLinkManByUserPass(String username,String password);
}
