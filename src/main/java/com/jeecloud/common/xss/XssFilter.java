package com.jeecloud.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName: XssFilter
 * @Description: XSS过滤
 * @author: admin
 * @date: 2018年3月2日 下午5:13:58
 */
public class XssFilter implements Filter {
	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}
	@Override
	public void destroy() {
	}
}
