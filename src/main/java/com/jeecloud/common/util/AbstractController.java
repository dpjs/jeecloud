package com.jeecloud.common.util;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecloud.common.entity.system.SysUser;

/**
 * 
 * @ClassName: AbstractController 
 * @Description: Controller公共组件
 * @author: admin
 * @date: 2018年3月6日 下午2:35:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 * @Title: getUser 
	 * @Description: 获取用户
	 * @return
	 * @return: SysUser
	 */
	protected SysUser getUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}
	/**
	 * 
	 * @Title: getUserId 
	 * @Description: 获取用户ID
	 * @return
	 * @return: Long
	 */
	protected Long getUserId() {
		return getUser().getId();
	}
	/**
	 * 
	 * @Title: getDeptId 
	 * @Description: 获取部门ID
	 * @return
	 * @return: Long
	 */
	protected Long getDeptId() {
		return getUser().getDeptId();
	}
	/**
	 * 
	 * @Title: getCompanyId 
	 * @Description: 获取企业ID
	 * @return
	 * @return: Long
	 */
	protected Long getCompanyId() {
		return getUser().getCompanyId();
	}
	/**
	 * 
	 * @Title: getUserName 
	 * @Description: 获取用户名
	 * @return
	 * @return: String
	 */
	protected String getUserName() {
		return getUser().getUsername();
	}
}
