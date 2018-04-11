package com.jeecloud.common.jwt;

import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;

/**
 * 
 * @ClassName: JwtFilter 
 * @Description: jwt过滤器
 * @author: admin
 * @date: 2018年3月5日 下午3:17:00
 */
public class JwtFilter extends GenericFilterBean {
	@Autowired
	private Audience audience;

	/**
	 * Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：
	 * iss(Issuser)：代表这个JWT的签发主体； sub(Subject)：代表这个JWT的主体，即它的所有人； aud(Audience)：代表这个JWT的接收对象；
	 * exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间； nbf(Not
	 * Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的； iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
	 * jti(JWT ID)：是JWT的唯一标识。
	 * @param req
	 * @param res
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		// 等到请求头信息authorization信息
		final String authHeader = request.getHeader("authorization");
		String method = "OPTIONS";
		if (method.equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			String header = "bearer;";
			if (authHeader == null || !authHeader.startsWith(header)) {
				try {
					throw new LoginException("登录异常");
				} catch (LoginException e) {
					e.printStackTrace();
				}
			}
			final String token = authHeader.substring(7);
			try {
				if (audience == null) {
					BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
					audience = (Audience) factory.getBean("audience");
				}
				final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
				if (claims == null) {
					throw new LoginException("登录异常");
				}
				chain.doFilter(req, res);
			} catch (final Exception e) {
				try {
					throw new LoginException("登录异常");
				} catch (LoginException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
