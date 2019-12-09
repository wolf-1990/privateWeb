import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/8/22 18:26
 **/
public class TestZodiac {
    public static void main(String[] args) {
//        String zodiacByAge = findZodiacByAge(18);
//        System.out.println("根据您的年龄判断您的属相为："+zodiacByAge);
        List<Integer> resultAge = findAgeByZodiac("蛇","猪");
        System.out.println("根据您的属相可能得出您的年龄可能是："+resultAge.toString());
    }
    //通过年龄得出属相
    public static String findZodiacByAge(Integer age){
        age = age+1;
        String str ="鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪";
        String[] split = str.split("、");
        Integer num = age%12;
        String  zodiac= split[num];
        return zodiac;
    }
    //通过属相给出年龄选项
    public static List<Integer> findAgeByZodiac(String zodiac,String nowZodiac){
        String str ="鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪";
        String[] split = str.split("、");
        List<String> strList = Arrays.asList(split);
        int indexOf = strList.indexOf(zodiac);

        int nowIndexOf = strList.indexOf(nowZodiac);

        indexOf=nowIndexOf-indexOf;
        List<Integer> resultNums = new ArrayList<>();
        for(int i = 0;i<10;i++){
            int num = indexOf + (12 * i);
            if(num<=100 && num >=0){
                resultNums.add(num);
            }else{
                break;
            }
        }
        return resultNums;
    }

}
