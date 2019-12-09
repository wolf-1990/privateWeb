package com.bluesky.zhz.core.bean;

import lombok.Data;

@Data
public class SysClass{

	private String id;
	private String name;
	private Integer classType;//项目类型
	private String parentId;//父id
	private Integer sortNum;//排序
}
