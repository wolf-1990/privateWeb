package coreJava.genericity;

import lombok.Data;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 12:00
 **/
@Data
public class ApplyEntity {
    /**
     * 流程id
     */
    private String applyId;
    /**
     * 流程名称
     */
    private String applyName;
    /**
     * 流程类型
     */
    private Integer applyType;
    /**
     * 流程基本信息
     */
    private String applyBaicInfo;

    @Override
    public String toString() {
        return "ApplyEntity{" +
                "applyId='" + applyId + '\'' +
                ", applyName='" + applyName + '\'' +
                ", applyType=" + applyType +
                ", applyBaicInfo='" + applyBaicInfo + '\'' +
                '}';
    }
}
