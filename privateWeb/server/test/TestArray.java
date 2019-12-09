import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/9/5 10:34
 **/
public class TestArray {
    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        String [] str1 = new String[]{"001","1.0"};
        String [] str2 = new String[]{"001","2.0"};
        String [] str3 = new String[]{"001","3.0"};

        String [] str4 = new String[]{"002","1.0"};
        String [] str5 = new String[]{"002","2.0"};
        String [] str6 = new String[]{"002","3.0"};

        String [] str7 = new String[]{"003","1.0"};
        String [] str8 = new String[]{"003","2.0"};
        String [] str9 = new String[]{"003","3.0"};

        String [] str10 = new String[]{"004","1.0"};
        String [] str11 = new String[]{"004","2.0"};
        String [] str12 = new String[]{"004","3.0"};
        list.add(str1);list.add(str2);list.add(str3);
        list.add(str4);list.add(str5);list.add(str6);
        list.add(str7);list.add(str8);list.add(str9);
        list.add(str10);list.add(str11);list.add(str12);

        //获取重组后集合的数量 3
        String s = list.get(0)[0];
        int size = list.stream().filter(t -> t[0].equals(s)).collect(Collectors.toList()).size();
        //获取每组项的个数  4
        int size1 = list.stream().collect(Collectors.groupingBy(param -> param[0])).size();
        //循环list  拆分集合 返回拆分后的集合
        Map<String,List<String[]>> map = new HashMap<>();
        int k =0;
        for (int i = 0; i < list.size(); i++) {
            k++;
            List<String[]> newArray = null;
            if(i%3==0 || i ==0){
                newArray = new ArrayList<>();
            }
            for(int j = 0;j<size;j+=size1-1){
                newArray.add(list.get(i));
            }
            if(i%3==0){
                map.put(k+"",newArray);
            }
        }
        System.out.print(map.toString());

    }

    @Test
    public void testDemo(){
        List<String[]> list = new ArrayList<>();
        String [] str1 = new String[]{"001","1.0"};
        String [] str2 = new String[]{"001","2.0"};
        String [] str3 = new String[]{"001","3.0"};
        list.add(str1);
        list.add(str2);
        list.add(str3);
        if(list instanceof RandomAccess){
            String[] strings = list.get(1);
            for (String string : strings) {
                System.out.println(string);
            }
        }else{

        }
        TestArray testArray = new TestArray();
        if(Objects.isNull(testArray)){
            return ;
        }
        int i = 0;
        String  s = String.valueOf(i);
        BigDecimal.valueOf(1L);
    }

    public static Stack<Integer> stack = new Stack<Integer>();
    @Test
    public void testSort() {
        int shu[] = {1,2,3,4,5,6,7};
        f(shu,7,0);
    }
    /**
     *
     * @param shu   待选择的数组
     * @param targ  要选择多少个次
     * @param cur   当前选择的是第几次
     */
    private static void f(int[] shu, int targ, int cur) {
        // TODO Auto-generated method stub
        if(cur == targ) {
            System.out.println(stack);
            return;
        }

        for(int i=0;i<shu.length;i++) {
            if(!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f(shu, targ, cur+1);
                stack.pop();
            }

        }
    }
}
