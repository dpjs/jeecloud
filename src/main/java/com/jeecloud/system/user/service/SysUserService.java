package com.jeecloud.system.user.service;

import java.util.List;
import java.util.Map;

import com.jeecloud.common.entity.Rest;
import com.jeecloud.common.entity.system.SysUser;

/**
 * @ClassName: SysUserService
 * @Description: 系统用户
 * @author: admin
 * @date: 2018年3月8日 下午1:09:56
 */
public interface SysUserService {
	/**
	 * @Title: save
	 * @Description: 保存
	 * @param sysUser
	 * @return: void
	 */
	void save(SysUser sysUser);
	/**
	 * @Title: queryPage
	 * @Description: 查询用户列表
	 * @param params
	 * @return
	 * @return: Rest
	 */
	Rest queryPage(Map<String, Object> params);
	/**
	 * @Title: update
	 * @Description: 修改用户
	 * @param user
	 * @return: void
	 */
	void update(SysUser user);
	/**
	 * @Title: deleteBatchIds
	 * @Description: 根据用户ID批量删除用户
	 * @param asList
	 * @return: void
	 */
	void deleteBatchIds(List<Long> userIds);
}
