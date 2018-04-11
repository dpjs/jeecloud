package com.jeecloud.common.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.jeecloud.common.util.UuidUtil;

/**
 * @ClassName: TokenContract
 * @Description: token切面
 * @author: admin
 * @date: 2018年3月15日 上午11:21:07
 */
@Aspect
@Component
public class TokenContract {
	private static final Logger logger = LoggerFactory.getLogger(TokenContract.class);

	/**
	 * @Title: testToken
	 * @Description: 前置通知
	 * @param joinPoint
	 * @param token
	 * @return: void
	 */
	@Before("within(@org.springframework.stereotype.Controller *) && @annotation(token)")
	public void testToken(final JoinPoint joinPoint, Token token) {
		try {
			if (token != null) {
				// 获取 joinPoint 的全部参数
				Object[] args = joinPoint.getArgs();
				HttpServletRequest request = null;
				@SuppressWarnings("unused")
				HttpServletResponse response = null;
				for (int i = 0; i < args.length; i++) {
					// 获得参数中的 request && response
					if (args[i] instanceof HttpServletRequest) {
						request = (HttpServletRequest) args[i];
					}
					if (args[i] instanceof HttpServletResponse) {
						response = (HttpServletResponse) args[i];
					}
				}
				boolean needSaveSession = token.save();
				if (needSaveSession) {
					String uuid = UuidUtil.getUUID();
					request.getSession().setAttribute("token", uuid);
					logger.debug("进入表单页面，Token值为：" + uuid);
				}
				boolean needRemoveSession = token.remove();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						logger.error("表单重复提交");
						throw new FormRepeatException("表单重复提交");
					}
					request.getSession(false).removeAttribute("token");
				}
			}
		} catch (FormRepeatException e) {
			throw e;
		} catch (Exception e) {
			logger.error("token 发生异常 : " + e);
		}
	}
	/**
	 * @Title: isRepeatSubmit
	 * @Description: 判断是否重复提交
	 * @param request
	 * @return
	 * @throws FormRepeatException
	 * @return: boolean
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) throws FormRepeatException {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null) {
			// throw new FormRepeatException("session 为空");
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (StringUtils.isEmpty(clinetToken)) {
			// throw new FormRepeatException("请从正常页面进入！");
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			// throw new FormRepeatException("重复表单提交！");
			return true;
		}
		logger.debug("校验是否重复提交：表单页面Token值为：" + clinetToken + ",Session中的Token值为:" + serverToken);
		return false;
	}
}
