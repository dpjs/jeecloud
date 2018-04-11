package com.jeecloud.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName: HttpContextUtil
 * @Description: http工具类
 * @author: admin
 * @date: 2018年3月15日 上午11:17:20
 */
public class HttpContextUtil {
	/**
	 * @Title: getHttpServletRequest
	 * @Description: 获取HttpServletRequest
	 * @return
	 * @return: HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
