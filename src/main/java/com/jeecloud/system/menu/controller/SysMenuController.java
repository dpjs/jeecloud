package com.jeecloud.system.menu.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecloud.common.entity.Rest;
import com.jeecloud.common.entity.system.SysMenu;
import com.jeecloud.common.log.Log;
import com.jeecloud.common.token.Token;
import com.jeecloud.common.util.AbstractController;
import com.jeecloud.common.util.ValidatorUtils;
import com.jeecloud.common.validator.AddGroup;
import com.jeecloud.common.validator.UpdateGroup;
import com.jeecloud.system.menu.service.SysMenuService;

/**
 * @ClassName: SysMenuController
 * @Description: 系统菜单控制类
 * @author: admin
 * @date: 2018年3月6日 下午2:30:58
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * @Title: nav
	 * @Description: 导航菜单
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/nav")
	public Rest nav() {
		List<?> menuList = sysMenuService.getUserMenuList(getUser());
		return Rest.ok().put("menuList", menuList);
	}
	/**
	 * @Title: toPage
	 * @Description: 菜单列表页
	 * @return
	 * @return: String
	 */
	@GetMapping
	@Token(save = true)
	@RequiresPermissions("sys:menu:list")
	public String toPage(HttpServletRequest request, HttpServletResponse response, long menuId) {
		List<Map<String, Object>> theads = sysMenuService.getTableThead(getUserId(), menuId);
		request.setAttribute("theads", theads);
		request.setAttribute("tableTheads",
				theads.stream().filter(obj -> MapUtils.getIntValue(obj, "status") == 0).collect(Collectors.toList()));
		return "system/menu/menu";
	}
	/**
	 * @Title: list
	 * @Description: 所有菜单列表
	 * @param params
	 * @return
	 * @return: R
	 */
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public Rest list(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		return sysMenuService.queryPage(params);
	}
	/**
	 * @Title: save
	 * @Description: 保存
	 * @param menu
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/save")
	@Token(remove = true)
	@Log("保存菜单")
	@RequiresPermissions("sys:menu:save")
	public Rest save(@RequestBody SysMenu menu) {
		// 数据校验
		ValidatorUtils.validateEntity(menu, AddGroup.class);
		sysMenuService.insert(menu);
		return Rest.ok();
	}
	/**
	 * @Title: update
	 * @Description: 修改菜单
	 * @param menu
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/update")
	@Token(remove = true)
	@Log("修改菜单")
	@RequiresPermissions("sys:menu:update")
	public Rest update(@RequestBody SysMenu menu) {
		ValidatorUtils.validateEntity(menu, UpdateGroup.class);
		sysMenuService.updateById(menu);
		return Rest.ok();
	}
	/**
	 * @Title: delete
	 * @Description: 删除菜单
	 * @param menuId
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/delete")
	@Token(remove = true)
	@Log("删除菜单")
	@RequiresPermissions("sys:menu:delete")
	public Rest delete(@RequestBody long menuId) {
		// 判断是否有子菜单或按钮
		List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
		if (menuList.size() > 0) {
			return Rest.error("请先删除子菜单或按钮");
		}
		sysMenuService.delete(menuId);
		return Rest.ok();
	}
	/**
	 * 
	 * @Title: toMenuForm 
	 * @Description: 跳转菜单表单操作也
	 * @param sign
	 * @return
	 * @return: String
	 */
	@GetMapping("/toMenuForm")
	public String toMenuForm(String sign,String menuId) {
		return "system/menu/menu_form";
	}
	
	/**
	 * 
	 * @Title: toIconPage 
	 * @Description: 调整图标按钮选择页
	 * @return
	 * @return: String
	 */
	@GetMapping("/toIconPage")
	public String toIconPage() {
		return "system/menu/icon_list";
	}
}
