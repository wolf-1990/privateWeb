import org.junit.Test;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/10/12 17:52
 **/
public class PracticeDemo {
    /*求Sn=a+aa+aaa+…+aa…aaa（有n个a）之值，
    其中a是一个数字。
    例如：2+22+222+2222+22222（n=5），
    n由键盘输入。并且已知a=2.*/
    @Test
    public void testOne (){
        int sum = this.sumFunction(2, 3);
        System.out.println(sum);
    }

    private static int sumFunction(int baseNum,int n){
        int sum = 0;
        StringBuffer strBuf = new StringBuffer();
        int newBase = 0;
        for(int i = 0;i<n;i++){//循环次数
            strBuf.append(baseNum);
            newBase = Integer.parseInt(strBuf.toString());
            sum += newBase;
        }
        return sum;
    }
    
    /*给出一个不多余8位的整数，
    要求：1、求出它是几位数，
    2分别输出每一位数字，
    3、翻转输出 该数字 */
    @Test
    public void testTwo(){
        dealNum(123);
    }
    private void dealNum(int num){
        String numStr = num +"";
        boolean contains = numStr.contains("-");
        if(contains){
            numStr = numStr.replace("-","");
        }
        System.out.println(num +"是"+numStr.length()+"位数");
        System.out.print("依次输出每一位数字：");
        char[] chars = numStr.toCharArray();
        for (char aChar : chars) {
            System.out.print(aChar+" ");
        }
        System.out.println();
        StringBuffer strBuff = new StringBuffer();
        for (int i = chars.length-1; i >=0; i--) {
            strBuff.append(chars[i]);
        }
        String resultStr = strBuff.toString();
        if(contains){
            resultStr= "-"+resultStr;
        }
        System.out.print("数字翻转的结果："+resultStr);
    }
}
