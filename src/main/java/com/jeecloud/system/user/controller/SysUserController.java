package com.jeecloud.system.user.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import com.jeecloud.common.entity.system.SysUser;
import com.jeecloud.common.log.Log;
import com.jeecloud.common.token.Token;
import com.jeecloud.common.util.AbstractController;
import com.jeecloud.common.util.ValidatorUtils;
import com.jeecloud.common.validator.AddGroup;
import com.jeecloud.common.validator.UpdateGroup;
import com.jeecloud.system.menu.service.SysMenuService;
import com.jeecloud.system.user.service.SysUserService;

/**
 * @ClassName: SysUserController
 * @Description: 系统用户
 * @author: admin
 * @date: 2018年3月8日 上午11:41:27
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * @Title: toPage
	 * @Description: 用户列表页
	 * @return
	 * @return: String
	 */
	@GetMapping
	@Token(save = true)
	@RequiresPermissions("sys:user:list")
	public String toPage(HttpServletRequest request, HttpServletResponse response, long menuId) {
		List<Map<String, Object>> theads = sysMenuService.getTableThead(getUserId(), menuId);
		request.setAttribute("theads", theads);
		request.setAttribute("tableTheads",
				theads.stream().filter(obj -> MapUtils.getIntValue(obj, "status") == 0).collect(Collectors.toList()));
		return "system/user/user";
	}
	/**
	 * @Title: list
	 * @Description: 所有用户列表
	 * @param params
	 * @return
	 * @return: R
	 */
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("sys:user:list")
	public Rest list(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		return sysUserService.queryPage(params);
	}
	/**
	 * @Title: save
	 * @Description: 保存用户
	 * @param sysUser
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/save")
	@Token(remove = true)
	@Log("保存用户")
	@RequiresPermissions("sys:user:save")
	public Rest save(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
		ValidatorUtils.validateEntity(sysUser, AddGroup.class);
		sysUser.setGmtCreater(getUserName());
		sysUserService.save(sysUser);
		return Rest.ok();
	}
	/**
	 * @Title: update
	 * @Description: 修改用户
	 * @param user
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/update")
	@Token(remove = true)
	@Log("修改用户")
	@RequiresPermissions("sys:user:update")
	public Rest update(@RequestBody SysUser user) {
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.update(user);
		return Rest.ok();
	}
	/**
	 * @Title: delete
	 * @Description: 删除用户
	 * @param userIds
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/delete")
	@Token(remove = true)
	@Log("删除用户")
	@RequiresPermissions("sys:user:delete")
	public Rest delete(@RequestBody Long[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return Rest.error("系统管理员不能删除");
		}
		if (ArrayUtils.contains(userIds, getUserId())) {
			return Rest.error("当前用户不能删除");
		}
		sysUserService.deleteBatchIds(Arrays.asList(userIds));
		return Rest.ok();
	}
}
