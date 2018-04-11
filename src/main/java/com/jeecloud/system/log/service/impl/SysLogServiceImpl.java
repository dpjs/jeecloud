package com.jeecloud.system.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecloud.common.dao.system.SysLogDao;
import com.jeecloud.common.entity.system.SysLog;
import com.jeecloud.system.log.service.SysLogService;

/**
 * @ClassName: SysLogServiceImpl
 * @Description: 系统日志
 * @author: admin
 * @date: 2018年3月8日 下午1:17:43
 */
@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;

	/**
	 * 保存
	 */
	@Override
	public void save(SysLog sysLog) {
		sysLogDao.insert(sysLog);
	}
}
