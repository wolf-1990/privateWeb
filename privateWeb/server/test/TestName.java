import com.bluesky.zhz.core.utils.PinYinUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/8/12 15:28
 **/
public class TestName {
    @Test
    public void testNameForNumeber(){
        List<String> strArrays = new ArrayList<>();
        strArrays.add("赵赫智");
        strArrays.forEach(param->{
            handleArrays(param);
        });
    }

    private void handleArrays(String str) {
        String name = PinYinUtil.chTraSpelAll(str);
        char[] chars = name.toCharArray();
        Integer num =0;
        for (char aChar : chars) {
            if(aChar == 'a'){
                num+=1;
            }else if(aChar == 'o'){
                num+=6;
            }else if(aChar == 'e'){
                num+=5;
            }else if(aChar == 'i'){
                num+=9;
            }else if(aChar == 'u'){
                num+=3;
            }
        }
        Integer resultNum = num%10+ num/10;
        //内驱数字
        String strPre = str+" 您的内驱数字为:";
        System.out.println(str+" 您的数字密码是："+num+resultNum);
        System.out.println(strPre+resultNum);
    }
}
