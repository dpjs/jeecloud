package com.jeecloud.common.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: Audience 
 * @Description: jwt配置
 * @author: admin
 * @date: 2018年3月5日 下午3:11:44
 */
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
	private String clientId;
	private String base64Secret;
	private String name;
	private int expiresSecond;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getBase64Secret() {
		return base64Secret;
	}

	public void setBase64Secret(String base64Secret) {
		this.base64Secret = base64Secret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExpiresSecond() {
		return expiresSecond;
	}

	public void setExpiresSecond(int expiresSecond) {
		this.expiresSecond = expiresSecond;
	}

}
