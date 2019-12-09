package com.bluesky.zhz.core.bean.user;

import lombok.Data;

/**
 *当前登录代理商
 */
@Data
public class CurrentAgent {

	private String id;
	
	private String agentName;
	
	private String token;
	
	private Integer agentId;

	private String webUrl;

}
