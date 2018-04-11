package com.jeecloud.common.token;

/**
 * @ClassName: FormRepeatException
 * @Description: 表单重复提交异常类
 * @author: admin
 * @date: 2018年3月15日 上午11:22:53
 */
public class FormRepeatException extends RuntimeException {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public FormRepeatException(String message) {
		super(message);
	}
	public FormRepeatException(String message, Throwable cause) {
		super(message, cause);
	}
}
