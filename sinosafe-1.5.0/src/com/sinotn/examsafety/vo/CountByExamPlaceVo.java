package com.sinotn.examsafety.vo;

public class CountByExamPlaceVo {
	private String examPlaceId;//考点ID
	private String examPlaceName;//考点名称
	private Integer imeiNoTotal;//通道数量
	private Integer total;//安检人数
	private Integer passN;//未通过人数
	private Integer passY;//已通过人数
	private Integer count;//报名人数
	public String getExamPlaceId() {
		return examPlaceId;
	}
	public void setExamPlaceId(String examPlaceId) {
		this.examPlaceId = examPlaceId;
	}
	public String getExamPlaceName() {
		return examPlaceName;
	}
	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}
	public Integer getImeiNoTotal() {
		return imeiNoTotal;
	}
	public void setImeiNoTotal(Integer imeiNoTotal) {
		this.imeiNoTotal = imeiNoTotal;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPassN() {
		return passN;
	}
	public void setPassN(Integer passN) {
		this.passN = passN;
	}
	public Integer getPassY() {
		return passY;
	}
	public void setPassY(Integer passY) {
		this.passY = passY;
	}
	public CountByExamPlaceVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CountByExamPlaceVo [examPlaceId=" + examPlaceId
				+ ", examPlaceName=" + examPlaceName + ", imeiNoTotal="
				+ imeiNoTotal + ", total=" + total + ", passN=" + passN
				+ ", passY=" + passY + ", count=" + count + "]";
	}
	
	
}
