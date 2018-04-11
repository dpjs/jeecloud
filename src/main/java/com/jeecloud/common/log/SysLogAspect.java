package com.jeecloud.common.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.jeecloud.common.entity.system.SysLog;
import com.jeecloud.common.entity.system.SysUser;
import com.jeecloud.common.util.HttpContextUtil;
import com.jeecloud.common.util.IpUtil;
import com.jeecloud.system.log.service.SysLogService;

/**
 * 
 * @ClassName: SysLogAspect 
 * @Description: 系统日志切面
 * @author: admin
 * @date: 2018年3月15日 上午11:13:41
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 
	 * @Title: logPointCut 
	 * @Description: 切点
	 * @return: void
	 */
	@Pointcut("@annotation(com.jeecloud.common.log.Log)")
	public void logPointCut() {
	}
	/**
	 * 
	 * @Title: around 
	 * @Description: 环绕通知
	 * @param point
	 * @return
	 * @throws Throwable
	 * @return: Object
	 */
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveSysLog(point, time);
		return result;
	}
	/**
	 * 
	 * @Title: saveSysLog 
	 * @Description: 保存日志
	 * @param joinPoint
	 * @param time
	 * @return: void
	 */
	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog sysLog = new SysLog();
		Log syslog = method.getAnnotation(Log.class);
		if (syslog != null) {
			// 注解上的描述
			sysLog.setOperation(syslog.value());
		}
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try {
			String params = new Gson().toJson(args[0]);
			sysLog.setParams(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取request
		HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
		// 设置IP地址
		sysLog.setIp(IpUtil.getIpAddr(request));
		// 用户名
		String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
		sysLog.setUsername(username);
		sysLog.setTime(time);
		sysLog.setGmtCreate(new Date());
		// 保存系统日志
		sysLogService.save(sysLog);
	}
}
