package coreJava.myhashMap;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/25 11:36
 **/
public class Node {
    private Object key;
    private Object value;
    private Node next;
    public Node(){
    }
    public Node(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }
    public Node getNext(){
        return next;
    }
    public void setKey(String s){
        this.key=s;
    }
    public void setValue(Object value){
        this.value=value;
    }
    public Object getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
}
