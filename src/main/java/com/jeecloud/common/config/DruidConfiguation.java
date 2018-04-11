package com.jeecloud.common.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * @ClassName: DruidConfiguation 
 * @Description: druid配置
 * @author: admin
 * @date: 2018年3月5日 下午3:07:49
 */
@Configuration
public class DruidConfiguation {
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource() {
		DruidDataSource datasource = new DruidDataSource(); 
		return datasource;
	}
	
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidServlet() {
		// return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(),
				"/druid/*");
		// 白名单：
		// servletRegistrationBean.addInitParameter("allow","127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		// servletRegistrationBean.addInitParameter("deny","192.168.1.73");
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "jeecloud");
		servletRegistrationBean.addInitParameter("loginPassword", "jeecloud");
		// 是否能够重置数据.
		// servletRegistrationBean.addInitParameter("resetEnable","false");
		return servletRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
	
	@Bean(name="logFilter")
	public Slf4jLogFilter slf4jLogFilterBean(){
		Slf4jLogFilter self = new Slf4jLogFilter();
		self.setStatementExecutableSqlLogEnable(true);
		return self;
	}
}
