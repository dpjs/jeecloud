package com.jeecloud.common.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.Configuration;

/**
 * 
 * @ClassName: ShiroTagFreeMarkerConfigurer 
 * @Description: TODO
 * @author: admin
 * @date: 2018年3月5日 下午3:08:51
 */
@Component
public class ShiroTagFreeMarkerConfigurer implements InitializingBean {
	@Autowired
	private Configuration configuration;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 加上这句后，可以在页面上使用shiro标签
		configuration.setSharedVariable("shiro", new ShiroTags());
		// 加上这句后，可以在页面上用${context.contextPath}获取contextPath
		//resolver.setRequestContextAttribute("request");
	}
}
