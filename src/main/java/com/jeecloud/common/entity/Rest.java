package com.jeecloud.common.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: Rest 
 * @Description: 返回数据
 * @author: admin
 * @date: 2018年2月28日 下午2:12:24
 */
public class Rest extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public Rest() {
		put("code", 0);
		put("msg", "success");
	}
	public static Rest error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	public static Rest error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	public static Rest error(int code, String msg) {
		Rest r = new Rest();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	public static Rest ok(String msg) {
		Rest r = new Rest();
		r.put("msg", msg);
		return r;
	}
	public static Rest ok(Map<String, Object> map) {
		Rest r = new Rest();
		r.putAll(map);
		return r;
	}
	public static Rest ok() {
		return new Rest();
	}
	public static <T> Rest ok(PageInfo<T> pageInfo) {
		Rest r = new Rest();
		r.put("count", pageInfo.getTotal());
		r.put("data", pageInfo.getList());
		return r;
	}
	@Override
	public Rest put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
