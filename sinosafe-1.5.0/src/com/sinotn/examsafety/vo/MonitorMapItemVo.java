package com.sinotn.examsafety.vo;

/**
 * 监控VO
 * @author Administrator
 *
 */
public class MonitorMapItemVo {

	private String name; //地区名称（省份为缩写，地市为全称）
	private String value; //地图主显示值：参考率
	private String d1;//地区代码
	private String d2;//参考人数
	private String d3;//通过率
	private String d4;//通过人数
	private String d5;//总人数
	// Constructors
	/** default constructor */
	public MonitorMapItemVo() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getD1() {
		return d1;
	}
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public String getD2() {
		return d2;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}
	public String getD3() {
		return d3;
	}
	public void setD3(String d3) {
		this.d3 = d3;
	}
	public String getD4() {
		return d4;
	}
	public void setD4(String d4) {
		this.d4 = d4;
	}
	public String getD5() {
		return d5;
	}
	public void setD5(String d5) {
		this.d5 = d5;
	}
	
	

}