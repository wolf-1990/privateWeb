package com.bluesky.zhz.core.enums;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/2/16 16:15
 **/
public enum AgentOperationEnum {
    禁用_prohibit(1, "禁用"),
    删除_del(2,"删除");

    private Integer tag;
    private String message;

    AgentOperationEnum(Integer tag, String message) {
        this.tag = tag;
        this.message = message;
    }

    public Integer getTag() {
        return tag;
    }
}
