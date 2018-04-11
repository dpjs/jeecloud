package com.jeecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * 入口
 * @author dingping
 */
@SpringBootApplication
@MapperScan("com.jeecloud.common.dao")
public class Application implements WebMvcConfigurer {
	/**
	 * （1）在启动类App.java类中继承：WebMvcConfigurerAdapter （2）覆盖方法：configureContentNegotiation
	 * favorPathExtension表示支持后缀匹配，
	 * 属性ignoreAcceptHeader默认为fasle，表示accept-header匹配，defaultContentType开启默认匹配。
	 * 例如：请求aaa.xx，若设置<entry key="xx" value="application/xml"/> 也能匹配以xml返回。
	 * 根据以上条件进行一一匹配最终，得到相关并符合的策略初始化ContentNegotiationManager
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("system/login/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
