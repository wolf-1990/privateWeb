package coreJava.genericity;

import lombok.Data;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 13:39
 **/
@Data
public class ApplyRequest<T> extends ApplyEntity {
    /**
     * 泛型属性，作为流程内容
     */
    private T  applyContent;

    @Override
    public String toString() {
        return "ApplyRequest{" +
                "applyContent=" + applyContent +
                '}';
    }
}
