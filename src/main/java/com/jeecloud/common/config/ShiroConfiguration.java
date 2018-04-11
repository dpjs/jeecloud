package com.jeecloud.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.jeecloud.common.shiro.AuthRealm;
import com.jeecloud.common.shiro.CredentialsMatcher;

/**
 * 
 * @ClassName: ShiroConfiguration 
 * @Description: shiro配置
 * @author: admin
 * @date: 2018年3月5日 下午3:08:35
 */
@Configuration
public class ShiroConfiguration {
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		// 配置登录的url和登录成功的url
		bean.setLoginUrl("/");
		bean.setSuccessUrl("/index.html");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/v2/**", "anon");
		filterChainDefinitionMap.put("/doc.html", "anon");
		filterChainDefinitionMap.put("/api/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
		
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/error", "anon");
		filterChainDefinitionMap.put("/plugin/**", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/wechat/**", "anon");
		
		// 表示需要认证才可以访问
		filterChainDefinitionMap.put("/**", "authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}
	/**
	 * 
	 * @Title: securityManager 
	 * @Description: 配置核心安全事务管理器
	 * @param authRealm
	 * @return
	 * @return: SecurityManager
	 */
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		manager.setCacheManager(getEhCacheManager());
		return manager;
	}
	/**
	 * 
	 * @Title: authRealm 
	 * @Description: 配置自定义的权限登录器
	 * @param matcher
	 * @param cacheManager
	 * @return
	 * @return: AuthRealm
	 */
	@Bean(name = "authRealm")
	public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher,
			EhCacheManager cacheManager) {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(matcher);
		authRealm.setCacheManager(cacheManager);
		return authRealm;
	}
	/**
	 * 
	 * @Title: credentialsMatcher 
	 * @Description: 配置自定义的密码比较器
	 * @return
	 * @return: CredentialsMatcher
	 */
	@Bean(name = "credentialsMatcher")
	public CredentialsMatcher credentialsMatcher() {
		return new CredentialsMatcher();
	}
	/**
	 * 保证实现了Shiro内部lifecycle函数的bean执行
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**
	 * 
	 * @Title: defaultAdvisorAutoProxyCreator 
	 * @Description: 启用shrio授权注解拦截方式，AOP式方法级权限检查
	 * @return
	 * @return: DefaultAdvisorAutoProxyCreator
	 */
	@Bean
	@DependsOn(value = "lifecycleBeanPostProcessor") /** 依赖其他bean的初始化*/
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}
}
