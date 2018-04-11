package com.jeecloud.system.role.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecloud.common.token.Token;
import com.jeecloud.common.util.AbstractController;
import com.jeecloud.system.menu.service.SysMenuService;

/**
 * 
 * @ClassName: SysRoleController 
 * @Description: 系统角色
 * @author: admin
 * @date: 2018年3月29日 下午1:36:40
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController{
	@Autowired
	private SysMenuService sysMenuService;
	
	@GetMapping
	@Token(save = true)
	@RequiresPermissions("sys:menu:list")
	public String toPage(HttpServletRequest request, HttpServletResponse response, long menuId) {
		List<Map<String, Object>> theads = sysMenuService.getTableThead(getUserId(), menuId);
		request.setAttribute("theads", theads);
		request.setAttribute("tableTheads",
				theads.stream().filter(obj -> MapUtils.getIntValue(obj, "status") == 0).collect(Collectors.toList()));
		return "system/role/role";
	}
}
