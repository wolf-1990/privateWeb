package coreJava.genericity;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 13:56
 **/
public class ResponseObject {
    private String objString;
    public ResponseObject(String request) {
        this.objString = request;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "objString='" + objString + '\'' +
                '}';
    }
}
