package com.sinotn.examsafety.vo;


import java.util.List;

/**
 * 监控VO
 * @author Administrator
 *
 */
public class MonitorMapVo {

	private String mapName; //地图名称（china、北京...）
	private List<String> barTitleList; //柱形图纵轴数据
	private List<MonitorMapItemVo> mapItemList; //地图数据
	private MonitorMapItemVo totalMapItem; //合计数据
	
	// Constructors

	/** default constructor */
	public MonitorMapVo() {
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}


	public List<String> getBarTitleList() {
		return barTitleList;
	}

	public void setBarTitleList(List<String> barTitleList) {
		this.barTitleList = barTitleList;
	}

	public List<MonitorMapItemVo> getMapItemList() {
		return mapItemList;
	}

	public void setMapItemList(List<MonitorMapItemVo> mapItemList) {
		this.mapItemList = mapItemList;
	}

	public MonitorMapItemVo getTotalMapItem() {
		return totalMapItem;
	}

	public void setTotalMapItem(MonitorMapItemVo totalMapItem) {
		this.totalMapItem = totalMapItem;
	}

	
	
}