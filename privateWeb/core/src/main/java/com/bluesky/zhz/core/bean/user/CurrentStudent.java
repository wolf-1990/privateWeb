package com.bluesky.zhz.core.bean.user;

import lombok.Data;

/**
 * 当前登录学生
 */
@Data
public class CurrentStudent {

	private String id;
	
	private String userName;
	
	private String token;
	
	private Integer agentId;

	private String headImgUrl;

}
