package com.sinotn.common.utils;
import java.text.MessageFormat;
import java.util.*;

/**
 * <p>Title: 06_全国司法考试</p>
 *
 * <p>Description:读取指定的properties文件中的指定key  </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: 信诺数码科技</p>
 *
 * @author 李志东，臧国成，张岩
 * @version 1.0
 */

public class PropertiesReader {
    public PropertiesReader() {}

    /**
     * 取得国际化资源文件指定key的value
     * @param file_name properties文件的名字（没有扩展名）
     * @param key 所指定的key
     * @return  指定key对应的value值
     * @throws MissingResourceException 当没有这个properties文件，或该文件中不存在这个key时。
     */
    public static String getValue(String key) throws MissingResourceException{
        return getValue("messageResource_zh_CN",key);
    }
    /**
     * 取得国际化资源文件指定key的value
     * @param file_name properties文件的名字（没有扩展名）
     * @param key 所指定的key
     * @param params 参数数组
     * @return  指定key对应的value值
     * @throws MissingResourceException 当没有这个properties文件，或该文件中不存在这个key时。
     */
    public static String getValue(String key,Object[]params) throws MissingResourceException{
        return getValue("messageResource_zh_CN",key,params);
    }
    /**
     * 更具key获得系统配置文件的值
     * @param key 所指定的key
     * @return  指定key对应的value值
     * @throws MissingResourceException 当没有这个properties文件，或该文件中不存在这个key时。
     */
    public static String getParaValue(String key) throws MissingResourceException{
    	return getValue("exam",key);
    }
    
    /**
     * 取得国际化资源文件指定key的value
     * @param fileName properties文件的名字（没有扩展名）
     * @param key 所指定的key
     * @return  指定key对应的value值
     * @throws MissingResourceException 当没有这个properties文件，或该文件中不存在这个key时。
     */
    private static String getValue(String fileName,String key) throws MissingResourceException{
    	Locale locale = Locale.getDefault(); //获取地区:默认

        final ResourceBundle res = ResourceBundle.getBundle(fileName,locale);
        String value ="";
        try{
            value = res.getString( key );
        }catch(MissingResourceException e){
            value = "";
        }
        return value;
    }
    
    /**
     * 取得国际化资源文件指定key的value
     * @param fileName properties文件的名字（没有扩展名）
     * @param key 所指定的key
     * @param params 参数数组
     * @return  指定key对应的value值
     * @throws MissingResourceException 当没有这个properties文件，或该文件中不存在这个key时。
     */
    private static String getValue(String fileName,String key,Object[]params) throws MissingResourceException{
    	Locale locale = Locale.getDefault(); //获取地区:默认

        final ResourceBundle res = ResourceBundle.getBundle(fileName,locale);
        String value ="";
        try{
            value = res.getString( key );
            if(params.length>0)
            {
            	value=MessageFormat.format(value,params);
            }
        }catch(MissingResourceException e){
            throw e;
        }
        return value;
    }
}
