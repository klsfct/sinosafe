package com.sinotn.common.exception;

import com.sinotn.common.utils.PropertiesReader;

/**
 * <p>
 * Description:业务逻辑异常类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: 信诺数码
 * </p>
 * <p>
 * CreateDate:2008-12-26
 * </p>
 * 
 * @author 白春秀
 * @version 1.0
 */

@SuppressWarnings("serial")
public class EncryptException extends RuntimeException {
	/**
	 * 用指定资源文件key的值作为异常消息信息构造异常类
	 */
	public EncryptException(String key) {
		super(PropertiesReader.getValue(key));
	}
	/**
	 * 用指定资源文件key的值与参数数组作为异常消息信息构造异常类
	 */
	public EncryptException(String key,Object[] params) {
		super(PropertiesReader.getValue(key,params));
	}

	public EncryptException(Throwable throwable) {
		super(throwable);
	}

	public EncryptException(Throwable throwable, String key) {
		super(throwable);
	}
}
