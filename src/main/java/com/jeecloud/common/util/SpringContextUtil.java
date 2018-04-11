package com.jeecloud.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ApplicationContextHolder
 * @Description: bean工具类
 * @author: admin
 * @date: 2018年3月15日 上午11:20:29
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		applicationContext = ctx;
	}
	/**
	 * Get application context from everywhere
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	/**
	 * Get bean by class
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	/**
	 * Get bean by class name
	 * @param name
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}
}
