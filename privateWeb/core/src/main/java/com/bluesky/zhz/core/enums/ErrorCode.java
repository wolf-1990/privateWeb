package com.bluesky.zhz.core.enums;

public enum ErrorCode {
    BUSSINESS_ERROR_0001("0001","3", "解密失败"),
    BUSSINESS_ERROR_0002("0002", "3","参数检验失败"),
    BUSSINESS_ERROR_0003("0003", "3","系统异常"),
    BUSSINESS_ERROR_0004("0004", "3","银行参数组装base64转码异常"),
    BUSSINESS_ERROR_0005("0005","3", "加密失败"),
    CHANNEL_ERROR_3003("3003","2", "通道返回数据解密异常"),
    CHANNEL_ERROR_3004("3004","2", "http请求异常");

    private String code;
    private String type;
    private String message;

    ErrorCode(String code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
