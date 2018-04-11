package com.jeecloud.system.menu.service;

import java.util.List;
import java.util.Map;

import com.jeecloud.common.entity.Rest;
import com.jeecloud.common.entity.system.SysMenu;
import com.jeecloud.common.entity.system.SysUser;

/**
 * @ClassName: SysMenuService
 * @Description: 系统菜单
 * @author: admin
 * @date: 2018年3月6日 下午4:18:28
 */
public interface SysMenuService {
	/**
	 * @Title: getUserMenuList
	 * @Description: 获取用户菜单列表
	 * @param user
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getUserMenuList(SysUser user);
	/**
	 * @Title: getTableThead
	 * @Description: 获取用户数据列
	 * @param userId
	 * @param menuId
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getTableThead(long userId, long menuId);
	/**
	 * @Title: queryPage
	 * @Description: 查询菜单列表数据
	 * @param params
	 * @return
	 * @return: Rest
	 */
	Rest queryPage(Map<String, Object> params);
	/**
	 * @Title: insert
	 * @Description: 添加菜单
	 * @param menu
	 * @return: void
	 */
	void insert(SysMenu menu);
	/**
	 * @Title: queryListParentId
	 * @Description: 查询子菜单或按钮
	 * @param menuId
	 * @return
	 * @return: List<SysMenu>
	 */
	List<SysMenu> queryListParentId(long menuId);
	/**
	 * @Title: delete
	 * @Description: 删除菜单
	 * @param menuId
	 * @return: void
	 */
	void delete(long menuId);
	/**
	 * @Title: updateById
	 * @Description: 修改菜单
	 * @param menu
	 * @return: void
	 */
	void updateById(SysMenu menu);
}
