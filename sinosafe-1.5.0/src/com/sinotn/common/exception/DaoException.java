package com.sinotn.common.exception;


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
public class DaoException extends RuntimeException {
	/**
	 * 用传入值作为异常消息信息构造异常类
	 */
	public DaoException(String message) {
		super(message);
	}
	public DaoException(Throwable throwable) {
		super(throwable);
	}

	public DaoException(Throwable throwable, String key) {
		super(throwable);
	}
}
