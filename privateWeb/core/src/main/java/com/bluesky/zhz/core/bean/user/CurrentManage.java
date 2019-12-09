package com.bluesky.zhz.core.bean.user;

/**
 *云校管理员
 *id  name均为username
 */
public class CurrentManage {

	private String id;
	
	private String name;
	
	private String token;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
