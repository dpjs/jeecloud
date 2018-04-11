package com.jeecloud.common.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeecloud.common.entity.system.SysMenu;
import com.jeecloud.common.util.MyMapperUtil;

/**
 * 
 * @ClassName: SysMenuDao 
 * @Description: 系统菜单Dao
 * @author: admin
 * @date: 2018年3月5日 下午3:09:34
 */
public interface SysMenuDao extends MyMapperUtil<SysMenu>{
    /**
     * 
     * @Title: getUserMenuList 
     * @Description: 获取用户菜单列表
     * @param userId
     * @return
     * @return: List<Map<String,Object>>
     */
	List<Map<String, Object>> getUserMenuList(long userId);
	
	/**
	 * 
	 * @Title: getUserAllMenuList 
	 * @Description: 获取管理员菜单列表
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getUserAllMenuList();
	
	/**
	 * 
	 * @Title: getTableThead 
	 * @Description: 获取用户数据列
	 * @param userId
	 * @param menuId
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getTableThead(@Param("userId")long userId, @Param("menuId")long menuId);
	/**
	 * 
	 * @Title: getMenuList 
	 * @Description: 获取菜单列表
	 * @param params
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String,Object>> getMenuList(Map<String, Object> params);
}