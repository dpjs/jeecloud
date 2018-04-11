package com.jeecloud.system.menu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeecloud.common.dao.system.CompanyMenuDao;
import com.jeecloud.common.dao.system.SysMenuDao;
import com.jeecloud.common.entity.Rest;
import com.jeecloud.common.entity.system.SysMenu;
import com.jeecloud.common.entity.system.SysUser;
import com.jeecloud.common.util.Constant;
import com.jeecloud.system.menu.service.SysMenuService;

/**
 * @ClassName: SysMenuServiceImpl
 * @Description: 系统菜单
 * @author: admin
 * @date: 2018年3月6日 下午4:18:48
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private CompanyMenuDao companyMenuDao;

	/**
	 * 获取用户菜单列表
	 */
	@Override
	public List<Map<String, Object>> getUserMenuList(SysUser user) {
		int type = user.getType();
		int isAdmin = user.getIsAdmin();
		long userId = user.getId();
		List<Map<String, Object>> menuList;
		// 系统用户
		if (type == 0) {
			if (isAdmin == Constant.SUPER_ADMIN) {
				menuList = sysMenuDao.getUserAllMenuList();
			} else {
				menuList = sysMenuDao.getUserMenuList(userId);
			}
			// 企业用户
		} else {
			if (isAdmin == Constant.SUPER_ADMIN) {
				menuList = companyMenuDao.getUserAllMenuList();
			} else {
				menuList = companyMenuDao.getUserMenuList(userId);
			}
		}
		List<Map<String, Object>> parentMenuList = menuList.stream()
				.filter(obj -> MapUtils.getLong(obj, "parentId") == 0).collect(Collectors.toList());
		menuList.removeAll(parentMenuList);
		return getMenuTreeList(menuList, parentMenuList);
	}
	/**
	 * @Title: getMenuTreeList
	 * @Description: 递归获取菜单数
	 * @param menuList 菜单列表
	 * @param parentMenuList 上级菜单列表
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	private List<Map<String, Object>> getMenuTreeList(List<Map<String, Object>> menuList,
			List<Map<String, Object>> parentMenuList) {
		parentMenuList.stream().forEach(pmenu -> {
			long id = MapUtils.getLong(pmenu, "id");
			List<Map<String, Object>> subMenuList = menuList.stream()
					.filter(smenu -> id == MapUtils.getLong(smenu, "parentId")).collect(Collectors.toList());
			pmenu.put("children", subMenuList);
			menuList.removeAll(subMenuList);
			getMenuTreeList(menuList, subMenuList);
		});
		return parentMenuList;
	}
	/**
	 * 获取用户数据列
	 */
	@Override
	public List<Map<String, Object>> getTableThead(long userId, long menuId) {
		return sysMenuDao.getTableThead(userId, menuId);
	}
	/**
	 * 查询菜单列表
	 */
	@Override
	public Rest queryPage(Map<String, Object> params) {
		// 每页数据的条数
		int pageSize = MapUtils.getIntValue(params, "pageSize", 15);
		// 当前页数
		int pageNum = MapUtils.getIntValue(params, "pageNum", 1);
		PageInfo<Map<String,Object>> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> sysMenuDao.getMenuList(params));
		return Rest.ok(pageInfo);
	}
	/**
	 * 保存
	 */
	@Override
	public void insert(SysMenu menu) {
		sysMenuDao.insert(menu);
	}
	/**
	 * 查询子菜单或按钮
	 */
	@Override
	public List<SysMenu> queryListParentId(long menuId) {
		return null;
	}
	/**
	 * 删除
	 */
	@Override
	public void delete(long menuId) {
	}
	/**
	 * 修改
	 */
	@Override
	public void updateById(SysMenu menu) {
		sysMenuDao.updateByPrimaryKey(menu);
	}
}
