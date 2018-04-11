package com.jeecloud.common.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeecloud.common.entity.system.SysUser;
import com.jeecloud.common.util.MyMapperUtil;

/**
 * 
 * @ClassName: SysUserDao 
 * @Description: 系统用户
 * @author: admin
 * @date: 2018年3月6日 下午4:17:45
 */
public interface SysUserDao extends MyMapperUtil<SysUser>{
    /**
     * 
     * @Title: queryCompanyAllPerms 
     * @Description: 查询企业用户所有权限
     * @param userId
     * @param companyId
     * @return
     * @return: List<String>
     */
	List<String> queryCompanyAllPerms(@Param("userId")long userId, @Param("companyId")long companyId);
	
	/**
	 * 
	 * @Title: querySysAllPerms 
	 * @Description: 查询系统用户所有权限
	 * @param userId
	 * @return
	 * @return: List<String>
	 */
	List<String> querySysAllPerms(long userId);
	
	/**
	 * 
	 * @Title: getUserList 
	 * @Description: 获取系统用户列表
	 * @param params
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> getUserList(Map<String, Object> params);
	
	/**
	 * 
	 * @Title: deleteUserById 
	 * @Description: 删除用户
	 * @param userIds
	 * @return
	 * @return: int
	 */
	int deleteUserById(List<Long> userIds);
}