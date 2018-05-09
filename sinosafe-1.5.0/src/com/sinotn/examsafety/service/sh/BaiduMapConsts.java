package com.sinotn.examsafety.service.sh;

import net.sf.json.JSONObject;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @Description:   百度地图常量类
 * @Author:        李斌
 * @Version:       V1.0.0 
 * @Date:          2017年7月31日 下午1:45:37
 */
public class BaiduMapConsts {
	/**
	 * AK
	 */
	public static final String AK = "IjIyosBw42y8x9NTGLSuGEnhsAfGHBE0";
	/**
	 * service_id
	 */
	public static final String SERVICE_ID = "144664";
	/**
	 * 地理围栏半径（米）
	 */
	public static final String RADIUS = "500";
	/**
	 * 坐标类型(wgs84：GPS经纬度  gcj02：国测局经纬度    bd09ll：百度经纬度)
	 */
	public static final String COORD_TYPE_BD = "bd09ll";
	
	public static final String COORD_TYPE_WGS = "wgs84";
	
	public static final String COORD_TYPE_GCJ = "gcj02";
	
	/**
	 * 百度地图地理编码、逆地理编码服务请求URL
	 */
	public static final String GET_POINT_AND_ADDRESS_URL = "http://api.map.baidu.com/geocoder/v2/";
	/**
	 * 查询终端请求URL
	 */
	public static final String FIND_ENTITY_URL = "http://yingyan.baidu.com/api/v3/entity/list";
	/**
	 * 创建圆形围栏请求URL
	 */
	public static final String CREATE_CIRCLE_FENCE_URL = "http://yingyan.baidu.com/api/v3/fence/createcirclefence";
	/**
	 * 更新圆形围栏请求URL
	 */
	public static final String UPDATE_CIRCLE_FENCE_URL = "http://yingyan.baidu.com/api/v3/fence/updatecirclefence";
	/**
	 * 更新设备描述请求URL
	 */
	public static final String UPDATE_ENTITY_DESC_URL = "http://yingyan.baidu.com/api/v3/entity/update";
	/**
	 * 查询监控对象在围栏内或外请求URL
	 */
	public static final String QUERY_STATUS_URL = "http://yingyan.baidu.com/api/v3/fence/querystatus";
	/**
	 * 坐标转换
	 */
	public static final String POINT_GEPCONV = "http://api.map.baidu.com/geoconv/v1/?";
	/**
	 * 查询监控对象在围栏内或外
	 * *@param entityName
	 * @param fenceId
	 * @return
	 */
	public static JSONObject queryStatus(String entityName, String fenceId) {
		String params = "ak=" + AK 
				+ "&service_id=" + SERVICE_ID 
				+ "&monitored_person=" + entityName 
				+ "&fence_ids=" + fenceId;
		JSONObject jsonObject = SendUrlConsts.sendGetUrl(QUERY_STATUS_URL, params);
		return jsonObject;
	}
	/**
	 * 更新设备描述
	 * @param entityName
	 * @param entityDesc
	 * @return
	 */
	public static JSONObject updateEntityDesc(String entityName, String entityDesc) {
		String params = "ak=" + AK 
				+ "&service_id=" + SERVICE_ID 
				+ "&entity_name=" + entityName 
				+ "&entity_desc=" + entityDesc;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(UPDATE_ENTITY_DESC_URL, params);
		return jsonObject;
		
	}
	/**
	 * 更新设备联系人和联系电话
	 * @param entityName
	 * @param entityDesc
	 * @return
	 */
	public static JSONObject updateEntityLink(String entityName, String linkMan, String phone) {
		String params = "ak=" + AK 
				+ "&service_id=" + SERVICE_ID 
				+ "&entity_name=" + entityName 
				+ "&linkMan=" + linkMan
				+ "&phone=" + phone;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(UPDATE_ENTITY_DESC_URL, params);
		return jsonObject;
		
	}
	/**
	 * 获取百度鹰眼下所有终端设备
	 * @return
	 */
	public static JSONObject getEntity() {
		String param = "ak=" + AK + "&service_id=" + SERVICE_ID;
		JSONObject jsonObject = SendUrlConsts.sendGetUrl(FIND_ENTITY_URL, param);
		return jsonObject;
	}
	/**
	 * 根据地址发送远程请求获取坐标
	 * @param address
	 * @return
	 */
	public static JSONObject getPointByAddress(String address) {
		String params = "address=" + address +"&output=json&ak=" + AK;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(GET_POINT_AND_ADDRESS_URL, params);
		return jsonObject;
	}
	/**
	 * 根据坐标发送远程请求获取地址
	 * @param point  （格式：39.983424,116.32298   lat<纬度>,lng<经度>）
	 * @return
	 */
	public static JSONObject getAddressByPoint(String point) {
		String params = "location=" + point +"&output=json&ak=" + AK;
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(GET_POINT_AND_ADDRESS_URL, params);
		return jsonObject;
	}
	/**
	 * 创建圆形围栏
	 * @param fenceName  围栏名称
	 * @param entityName 监控对象名称
	 * @param lngStr	围栏圆心经度
	 * @param latStr	围栏圆心纬度
	 * @return
	 */
	public static JSONObject createCircleFence(String fenceName, String entityName, 
			String lngStr, String latStr) {
		String param = "ak=" + AK 
				+ "&service_id=" + SERVICE_ID 
				+ "&fence_name=" + fenceName 
				+ "&monitored_person=" + entityName 
				+ "&longitude=" + lngStr 
				+ "&latitude=" + latStr 
				+ "&radius=" + RADIUS
				+ "&coord_type=" + COORD_TYPE_BD;
				
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(CREATE_CIRCLE_FENCE_URL, param);
		return jsonObject;
		
	}
	/**
	 * 更新圆形围栏
	 * @param fenceName  围栏名称
	 * @param entityName 监控对象名称
	 * @param lngStr	围栏圆心经度
	 * @param latStr	围栏圆心纬度
	 * @return
	 */
	public static JSONObject updateCircleFence(String fenceId, String fenceName, String entityName, 
			String lngStr, String latStr) {
		String param = "ak=" + AK 
				+ "&service_id=" + SERVICE_ID 
				+ "&fence_id=" + fenceId 
				+ "&fence_name=" + fenceName 
				+ "&monitored_person=" + entityName 
				+ "&longitude=" + lngStr 
				+ "&latitude=" + latStr 
				+ "&radius=" + RADIUS
				+ "&coord_type=" + COORD_TYPE_BD;
				
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(UPDATE_CIRCLE_FENCE_URL, param);
		return jsonObject;
		
	}
	/**
	 * 根据终端名称查询单个终端设备信息
	 * @param entityName
	 * @return
	 */
	public static JSONObject findEntityByName(String entityName) {
		String param = "ak=" + AK + "&service_id=" + SERVICE_ID + "&filter=entity_names:" + entityName;
		JSONObject jsonObject = SendUrlConsts.sendGetUrl(FIND_ENTITY_URL, param);
		return jsonObject;
	}
	/**
	 * wgs84坐标转换成百度坐标
	 * @param lat,lng
	 * @return
	 */
	public static JSONObject wgs84ToBd09ll(String latStr, String lngStr) {
		String param = "coords=" + lngStr + "," + latStr 
				+ "&ak=" + BaiduMapConsts.AK + "&from=1&to=5";
		JSONObject jsonObject = SendUrlConsts.sendPostUrl(BaiduMapConsts.POINT_GEPCONV, param);
		return jsonObject;
		
	}
	
}
