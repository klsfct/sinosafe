package com.sinotn.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 微信基础类
 */
public class WeixinUtils {

	private static Logger logger = Logger.getLogger(WeixinUtils.class);
	
	
	/**
	 * 接受消息格式化
	 */
	public static String formatStr = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
	
	/**
	 * 接受XML微信号字段
	 */
	public static String xml_element_toUserName = "ToUserName";
	/**
	 * 接受XML加密信息字段
	 */
	public static String xml_element_encrypt = "Encrypt";
	/**
	 * 消息加密
	 */
	public static String ENCODE_TPYE_ENCRYPT = "1";
	/**
	 * 消息明文
	 */
	public static String ENCODE_TPYE_DECODE = "0";
	
	/**
	 * 一级菜单
	 */
	public static String BUTTON_LEVEL1 = "1";
	
	/**
	 * 二级菜单
	 */
	public static String BUTTON_LEVEL2 = "2";
	
	/**
	 * 代码类别 接收消息类型
	 */
	public static final String BASIC_CODE_CATEGORY_RECEIVE = "wx_receive";
	/**
	 * 代码类别 接受事件类型
	 */
	public static final String BASIC_CODE_CATEGORY_EVENT = "wx_event";
	
	/**
	 * 代码类别发送消息类型
	 */
	public static final String BASIC_CODE_CATEGORY_SEND = "wx_send";
	
	
	/**
	 * 代码类别 错误代码
	 */
	public static final String BASIC_CODE_CATEGORY_ERRORCODE = "wx_errorcode";
	
	/**
	 * 代码类别 按钮事件
	 */
	public static final String BASIC_CODE_BUTTON_EVENT = "wx_button";
	
	/**
	 * 代码类别 按钮触发KEY
	 */
	public static final String BASIC_CODE_BUTTON_KEY = "wx_button_key";

	/**
	 * 发送消息类型：文本
	 */
	public static final String SEND_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 发送消息类型：音乐
	 */
	public static final String SEND_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 发送消息类型：图文
	 */
	public static final String SEND_MESSAGE_TYPE_NEWS = "news";
	
	/**
	 * 发送消息类型：图片
	 */
	public static final String SEND_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 接收消息类型：文本
	 */
	public static final String RECEIVE_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 接收消息类型：图片
	 */
	public static final String RECEIVE_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 接收消息类型：链接
	 */
	public static final String RECEIVE_MESSAGE_TYPE_LINK = "link";

	/**
	 * 接收消息类型：地理位置
	 */
	public static final String RECEIVE_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 接收消息类型：音频
	 */
	public static final String RECEIVE_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * 接收消息类型：视频
	 */
	public static final String RECEIVE_MESSAGE_TYPE_VIDEO = "video";
	/**
	 * 接收消息类型：小视频
	 */
	public static final String RECEIVE_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
	

	/**
	 * 接收消息类型：推送
	 */
	public static final String RECEIVE_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：点击菜单拉取消息时的事件
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 事件类型：点击菜单跳转链接时的事件
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";
	
	/**
	 * 事件类型：用户已关注时的事件推送
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";
	
	
	/**
	 * 按钮事件类型：单击推事件
	 */
	public static final String BUTTON_EVENT_CLICK = "click";
	/**
	 * 按钮事件类型：跳转URL
	 */
	public static final String BUTTON_EVENT_VIEW = "view";
	
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	// 菜单创建（POST） 限100（次/天）  
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	//获取用户基本信息
	public static String get_userinfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	
	//获取资源列表
	public static String get_material_list_url ="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	//获取资源
	public static String get_material_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	
	//通过小程序code换取用户openId
	public static String get_xiaochengxu_openId_url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	
	
	
	
	/**
	 * 通过小程序code换取用户openId
	 */
	public static JSONObject getXiaochengxuOpenId(String appId, String secret,String jsCode) {
		// 拼装url
		String url = get_xiaochengxu_openId_url.replace("APPID", appId).replace("SECRET", secret).replace("JSCODE", jsCode);
		// 调用接口
		JSONObject jsonObject = httpRequest(url, "POST", null);
		return jsonObject;
	}
	
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new X509TrustManage() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)){
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			//logger.info(WeixinUtils.getLineInfo()+"WeixinUtils--httpRequest--buffer.toString():"+buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
			//logger.info(WeixinUtils.getLineInfo()+"WeixinUtils--httpRequest--jsonObject:"+jsonObject);
		} catch (ConnectException ce) {
			logger.error(WeixinUtils.getLineInfo()+"WeixinManage--JSONObject--ConnectException:"+ce.getMessage());
		} catch (Exception e) {
			logger.error(WeixinUtils.getLineInfo()+"WeixinManage--JSONObject--Exception:"+e.getMessage());
		}
		return jsonObject;
	}
	
	
	
	/**
	 * 验证签名
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String token,String signature, String timestamp,
			String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、nonce 三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1 加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		content = null;
		// 将sha1 加密后的字符串可与signature 对比，标识该接收来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}


	/**
	 * 解析接收微信发来的消息（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String xmlStr) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		
		// 读取输入流
		Document document = DocumentHelper.parseText(xmlStr);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList){
			map.put(e.getName(), e.getText());
		}
		return map;
	}


	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	public static String getXMLByHttp(String serverAddress){
		StringBuffer sb = new StringBuffer(serverAddress);
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(serverAddress);
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "gb2312");
			//osw.write(sb.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 读取返回内容

		StringBuffer retBuffer = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream(),"utf-8"));
			String temp;
			while ((temp = reader.readLine()) != null) {
				retBuffer.append(temp);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		String result = retBuffer.toString();
		return result;
	}
	
	public static String getLineInfo() 

    { 
        StackTraceElement ste = new Throwable().getStackTrace()[1]; 
        return "[Line " + ste.getLineNumber()+"]"; 

    }
	
	
	/***
	* 将对象转换为JSON对象
	* @param object
	* @return
	*/
	public static JSONObject toJSONObject(Object object){
		return JSONObject.fromObject(object);
	}

	
	
	
	public static void  main(String[] args){
		
	}
	
}
