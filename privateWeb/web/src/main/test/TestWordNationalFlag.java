import java.util.List;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/9/27 17:48
 **/
public class TestWordNationalFlag {

    public static void main(String[] args) {

        String fileName = "D:\\jsoup\\flag\\demoflag.txt";
        List<String> list = WordNationalFlag.readyFileByLines(fileName);
        for (String country : list) {
            String page = WordNationalFlag.doPost(country);
            if (page.indexOf("html") >= 0) {
                WordNationalFlag.getConnection(page);
            }
        }
        System.out.println("国旗下载完毕");
    }
}
