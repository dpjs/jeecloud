package com.jeecloud.system.log.service;

import com.jeecloud.common.entity.system.SysLog;

/**
 * 
 * @ClassName: SysLogService 
 * @Description: 系统日志
 * @author: admin
 * @date: 2018年3月15日 上午11:11:53
 */
public interface SysLogService {
	/**
	 * 
	 * @Title: save 
	 * @Description: 保存
	 * @param sysLog
	 * @return: void
	 */
	void save(SysLog sysLog);
}
