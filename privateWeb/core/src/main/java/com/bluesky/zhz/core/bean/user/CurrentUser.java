package com.bluesky.zhz.core.bean.user;

public class CurrentUser {

	private String id;
	
	private String name;
	
	private String token;
	
	private Integer agentId;
	
	

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

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

	public CurrentUser() {
		super();
	}

	public CurrentUser(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
