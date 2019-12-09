import org.junit.Test;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/10/9 13:59
 **/

public class Lambda {
     public static void main(String[] args) {
//Lambda简化写法
         useMyString((s,x,y) -> s.substring(x,y));
         //引用类的实例方法
         useMyString(String::substring);
     }
     private static void useMyString(MyString my) {
          String s = my.mySubString("HelloWorld", 2, 5);
          System.out.println(s);
     }

     @Test
     public void aa(){
         useMyString(String::substring);
     }
}
interface MyString {
    String mySubString(String s,int x,int y);
}
