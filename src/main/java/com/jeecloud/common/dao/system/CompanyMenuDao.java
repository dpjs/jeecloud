package com.jeecloud.common.dao.system;

import java.util.List;
import java.util.Map;

import com.jeecloud.common.entity.system.CompanyMenu;
import com.jeecloud.common.util.MyMapperUtil;

/**
 * 
 * @ClassName: CompanyMenuDao 
 * @Description: 企业菜单
 * @author: admin
 * @date: 2018年3月6日 下午4:17:17
 */
public interface CompanyMenuDao extends MyMapperUtil<CompanyMenu>{
    /**
     * 
     * @Title: getUserMenuList 
     * @Description: 获取企业用户菜单列表
     * @param userId
     * @return
     * @return: List<Map<String,Object>>
     */
	List<Map<String, Object>> getUserMenuList(long userId);
	/**
	 * 
	 * @Title: getUserAllMenuList 
	 * @Description: 获取企业管理员菜单列表
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getUserAllMenuList();
}