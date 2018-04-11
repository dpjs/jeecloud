package com.jeecloud.common.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecloud.common.dao.system.CompanyMenuDao;
import com.jeecloud.common.dao.system.SysMenuDao;
import com.jeecloud.common.dao.system.SysUserDao;
import com.jeecloud.common.entity.system.CompanyMenu;
import com.jeecloud.common.entity.system.SysMenu;
import com.jeecloud.common.entity.system.SysUser;
import com.jeecloud.common.util.Constant;
import com.jeecloud.common.util.ShiroUtil;

/**
 * @ClassName: AuthRealm
 * @Description: TODO
 * @author: admin
 * @date: 2018年2月28日 下午3:00:24
 */
public class AuthRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private CompanyMenuDao companyMenuDao;

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 获取用户输入的token
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 查询用户信息
		SysUser user = new SysUser();
		user.setUsername(token.getUsername());
		user = sysUserDao.selectOne(user);
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		SecurityUtils.getSubject().getSession().setAttribute("sysUser", user);
		// 放入shiro.调用CredentialsMatcher检验密码
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), getName());
		return info;
	}
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SysUser user = (SysUser) principal.getPrimaryPrincipal();
		int type = user.getType();
		int isAdmin = user.getIsAdmin();
		long userId = user.getId();
		List<String> permsList;
		// 系统用户
		if (0 == type) {
			// 系统管理员，拥有最高权限
			if (isAdmin == Constant.SUPER_ADMIN) {
				SysMenu sysMenu = new SysMenu();
				sysMenu.setIsDeleted(0);
				List<SysMenu> menuList = sysMenuDao.select(sysMenu);
				permsList = new ArrayList<>(menuList.size());
				for (SysMenu menu : menuList) {
					permsList.add(menu.getPerms());
				}
			} else {
				permsList = sysUserDao.querySysAllPerms(userId);
			}
		} else {
			long companyId = user.getCompanyId();
			// 系统管理员，拥有最高权限
			if (isAdmin == Constant.SUPER_ADMIN) {
				CompanyMenu companyMenu = new CompanyMenu();
				companyMenu.setIsDeleted(0);
				companyMenu.setCompanyId(companyId);
				List<CompanyMenu> menuList = companyMenuDao.select(companyMenu);
				permsList = new ArrayList<>(menuList.size());
				for (CompanyMenu menu : menuList) {
					permsList.add(menu.getPerms());
				}
			} else {
				permsList = sysUserDao.queryCompanyAllPerms(userId, companyId);
			}
		}
		// 用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}
	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		shaCredentialsMatcher.setHashAlgorithmName(ShiroUtil.HASH_ALGORITHM_NAME);
		shaCredentialsMatcher.setHashIterations(ShiroUtil.HASH_ITERATIONS);
		super.setCredentialsMatcher(shaCredentialsMatcher);
	}
}
