package com.sinotn.examsafety.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sinotn.examsafety.vo.DeviceInfoVo;


public class JsonService {
	
	public static String jsonStr = "{\"page\": {\"total\": 2,\"page\": 0,\"size\": 2},\"data\": [{\"deviceSerial\": \"427734000\",\"deviceName\": \"xiaoge的设备\",\"deviceType\":\"C1\",\"status\": 1,\"defence\": 1,\"deviceVersion\": \"V4.2.5 build 151223\"},{\"deviceSerial\": \"519266666\",\"deviceName\": \"测试\",\"deviceType\": \"UNKNOWN\",\"status\": 0,\"defence\": 0,\"deviceVersion\": \"V5.3.0 build 150824\"}],\"code\": \"200\",\"msg\": \"操作成功!\"}";

	public static void main(String args[]){
		JsonService.getDevListFromJson(JsonService.jsonStr);
	}
	
	/**
	 * 获取设备列表map信息
	 * @param jsonStr 服务器返回的设备列表json串
	 * @return 设备列表信息
	 */
	public static Map<String,DeviceInfoVo> getDevListFromJson(String jsonStr){
		JSONObject json = JSONObject.fromObject(jsonStr);
		JSONArray jsonDateArray = json.getJSONArray("data");
		Map<String,DeviceInfoVo> map = new HashMap<String,DeviceInfoVo>();
		for(int i = 0 ; i < jsonDateArray.size() ; i++){
			JSONObject jsonDate = (JSONObject)jsonDateArray.get(i);
			String mapKey = jsonDate.getString("deviceSerial");
			DeviceInfoVo deviceInfoVo = new DeviceInfoVo();
			JsonService.jsonToBean(deviceInfoVo, jsonDate);
			map.put(mapKey, deviceInfoVo);
		}
        return map;
	}
	
	/**
	 * 将json对象转换成vo对象，要求vo对象的属性与json对象的节点名对应
	 * @param ObjVo vo对象
	 * @param json json对象valuse
	 */
	public static void jsonToBean(Object ObjVo,JSONObject json){
		Field[] fields = ObjVo.getClass().getDeclaredFields();
		Object jsonVal = null;
		try{
			for(Field field : fields){
				jsonVal = json.get(field.getName());
				BeanUtils.setProperty(ObjVo, field.getName(), jsonVal);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
