package com.sinotn.examsafety.service.sh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sinotn.common.utils.WeixinUtils;

import oracle.jdbc.util.Login;
import net.sf.json.JSONObject;

/**
 * 
 * Copyright (c) 2017 by 信诺软通
 * @Description:   后台发送HTTP请求的公共方法
 * @Author:        李斌
 * @Version:       V1.0.0 
 * @Date:          2017年7月31日 下午1:51:25
 */
public class SendUrlConsts {
	
	private static Logger logger = Logger.getLogger(SendUrlConsts.class);
	/**
	 * 发送远端POST请求的公共方法
	 * @param sendUrl	（远程请求的URL）
	 * @param param	（远程请求参数）
	 * @return JSONObject	（远程请求返回的JSON）
	 */
	public static JSONObject sendPostUrl(String url, String param){
		PrintWriter out = null;
		BufferedReader in = null;
		JSONObject jsonObject = null;
		String result = "";
        try {
        	URL realUrl = new URL(url);
        	HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.connect();
            out = new PrintWriter(
            		new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            jsonObject = JSONObject.fromObject(result);
		} catch (IOException e) {
			logger.info(e.getMessage());
		} finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
            	logger.info(ex.getMessage());
            }
        }
        
        return jsonObject;
    }  
	/**
	 * 发送远端GET请求的公共方法
	 * @param sendUrl	（远程请求的URL）
	 * @param param	（远程请求参数）
	 * @return JSONObject	（远程请求返回的JSON）
	 */
	public static JSONObject sendGetUrl(String url, String param) {
		JSONObject jsonObject = null;
		String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            jsonObject = JSONObject.fromObject(result);
        } catch (Exception e) {
        	logger.info(e.getMessage());
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
            	logger.info(e2.getMessage());
            }
        }
        return jsonObject;
		
	}
	
	
}
