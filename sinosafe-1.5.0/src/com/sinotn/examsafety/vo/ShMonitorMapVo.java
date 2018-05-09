package com.sinotn.examsafety.vo;


import java.util.List;

/**
 * 监控VO
 * @author Administrator
 *
 */
public class ShMonitorMapVo {

	private String mapName; //地图名称（china、北京...）
	private List<ShMonitorMapItemVo> mapItemList; //地图数据
	private ShMonitorMapItemVo totalMapItem; //合计数据
	
	// Constructors

	/** default constructor */
	public ShMonitorMapVo() {
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public List<ShMonitorMapItemVo> getMapItemList() {
		return mapItemList;
	}

	public void setMapItemList(List<ShMonitorMapItemVo> mapItemList) {
		this.mapItemList = mapItemList;
	}

	public ShMonitorMapItemVo getTotalMapItem() {
		return totalMapItem;
	}

	public void setTotalMapItem(ShMonitorMapItemVo totalMapItem) {
		this.totalMapItem = totalMapItem;
	}

	
}