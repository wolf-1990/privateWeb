package com.bluesky.zhz.core.bean.operator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作信息
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OperationRecord implements Serializable {

    private static final long serialVersionUID = -5355779517965948914L;

    private String id;
	@ApiModelProperty("操作类型")
    private Integer operationType;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("ip地址")
    private String ipAddress;
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date operationTime;
    @ApiModelProperty("操作内容")
    private String content;
    
    
    public OperationRecord(){
    	
    }

    public OperationRecord(Integer operationType,String content) {
		super();
		this.operationType = operationType;
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}