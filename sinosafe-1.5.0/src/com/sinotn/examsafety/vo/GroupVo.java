package com.sinotn.examsafety.vo;
/**
 * 
 * @Description 中政法院系统计对象
 * @Copyright: Copyright (c) 2017 Company:北京信诺软通
 * @author <a href="mailto:libin@sinotn.com">Libin</a>
 * @date 2018年2月8日 上午10:06:47
 * @version V1.0
 */
public class GroupVo {
	private Integer d1;//总人数
	private Integer d2;//应到人数
	private String d3;//报到率
	public Integer getD1() {
		return d1;
	}
	public void setD1(Integer d1) {
		this.d1 = d1;
	}
	public Integer getD2() {
		return d2;
	}
	public void setD2(Integer d2) {
		this.d2 = d2;
	}
	public String getD3() {
		return d3;
	}
	public void setD3(String d3) {
		this.d3 = d3;
	}
	public GroupVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GroupVo [d1=" + d1 + ", d2=" + d2 + ", d3=" + d3 + "]";
	}
	
	
}
