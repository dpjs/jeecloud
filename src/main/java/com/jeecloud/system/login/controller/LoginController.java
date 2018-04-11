package com.jeecloud.system.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecloud.common.entity.Rest;
import com.jeecloud.common.jwt.Audience;
import com.jeecloud.common.jwt.JwtHelper;

/**
 * @ClassName: LoginController
 * @Description: 登录控制类
 * @author: admin
 * @date: 2018年3月5日 下午3:47:57
 */
@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	private Audience audience;

	/**
	 * @Title: login
	 * @Description: 登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 * @return: Rest
	 */
	@ResponseBody
	@PostMapping("/login")
	public Rest login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// shiro登陆验证
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			return Rest.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return Rest.error("账号或密码不正确");
		} catch (LockedAccountException e) {
			return Rest.error("账号已被锁定,请联系管理员");
		} catch (AuthenticationException e) {
			return Rest.error("账户验证失败");
		}
		String jwtToken = JwtHelper.createJWT(username, "1", "admin", audience.getClientId(), audience.getName(),
				audience.getExpiresSecond() * 1000, audience.getBase64Secret());
		String result_str = "bearer;" + jwtToken;
		return Rest.ok().put("jwtToken", result_str);
	}
	/**
	 * @Title: toIndex
	 * @Description: 调整首页
	 * @return
	 * @return: String
	 */
	@GetMapping("/index.html")
	public String toIndex() {
		return "system/index";
	}
}
