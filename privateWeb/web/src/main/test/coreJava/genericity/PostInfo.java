package coreJava.genericity;

import lombok.Data;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 13:37
 **/
@Data
public class PostInfo {
    // 岗位编码
    private String postCode;
    // 岗位名称
    private String postName;

    @Override
    public String toString() {
        return "PostInfo{" +
                "postCode='" + postCode + '\'' +
                ", postName='" + postName + '\'' +
                '}';
    }
}
