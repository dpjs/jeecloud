package com.jeecloud.system.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeecloud.common.dao.system.SysUserDao;
import com.jeecloud.common.entity.Rest;
import com.jeecloud.common.entity.system.SysUser;
import com.jeecloud.common.util.Constant;
import com.jeecloud.common.util.ShiroUtil;
import com.jeecloud.system.user.service.SysUserService;

/**
 * 
 * @ClassName: SysUserServiceImpl 
 * @Description: 系统用户
 * @author: admin
 * @date: 2018年3月8日 上午11:59:41
 */
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	
	/**
	 * 查询用户列表数据
	 */
	@Override
	public Rest queryPage(Map<String, Object> params) {
		// 每页数据的条数
		int pageSize = MapUtils.getIntValue(params, "pageSize", 15);
		// 当前页数
		int pageNum = MapUtils.getIntValue(params, "pageNum", 1);
		params.put("superAdmin", Constant.SUPER_ADMIN);
		PageInfo<Map<String,Object>> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> sysUserDao.getUserList(params));
		return Rest.ok(pageInfo);
	}
	
	/**
	 * 系统用户
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUser sysUser) {
		sysUser.setGmtCreate(new Date());
		// sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		sysUser.setSalt(salt);
		sysUser.setPassword(ShiroUtil.sha256(sysUser.getPassword(), sysUser.getSalt()));
		sysUserDao.insert(sysUser);
	}
	
	/**
	 * 修改用户
	 */
	@Override
	public void update(SysUser user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(ShiroUtil.sha256(user.getPassword(), user.getSalt()));
		}
		sysUserDao.updateByPrimaryKey(user);
		//保存用户与角色关系
		//sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
	}

	/**
	 * 根据用户ID批量删除
	 */
	@Override
	public void deleteBatchIds(List<Long> userIds) {
		sysUserDao.deleteUserById(userIds);
	}
}
