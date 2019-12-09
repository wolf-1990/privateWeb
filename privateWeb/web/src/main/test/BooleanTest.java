import org.junit.Test;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/11 11:24
 **/
public class BooleanTest {
    @Test
    public void aa(){
        String s1 = "true";
        System.setProperty(s1,"true");
        boolean aa = Boolean.getBoolean(s1);
        System.out.println(aa);
        for(int i = 0;i<100;i++){
            System.out.println(i);
        }
    }
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
    Integer converted = converter.convert("123");

    Converter<String, Integer> converter1 = Integer::valueOf;
    Integer converted1 = converter.convert("123");

}
