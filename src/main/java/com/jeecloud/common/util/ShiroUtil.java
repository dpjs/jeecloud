package com.jeecloud.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jeecloud.common.entity.system.SysUser;
import com.jeecloud.common.exception.CustomException;

/**
 * @ClassName: ShiroUtil
 * @Description: Shiro工具类
 * @author: admin
 * @date: 2018年3月8日 上午11:10:23
 */
public class ShiroUtil {
	/** 加密算法 */
	public final static String HASH_ALGORITHM_NAME = "SHA-256";
	/** 循环次数 */
	public final static int HASH_ITERATIONS = 16;

	public static String sha256(String password, String salt) {
		return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
	}
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	public static SysUser getUserEntity() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}
	public static Long getUserId() {
		return getUserEntity().getId();
	}
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
			throw new CustomException("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}
}
