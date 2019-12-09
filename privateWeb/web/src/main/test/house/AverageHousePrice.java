package house;

import com.bluesky.zhz.core.utils.PinYinUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description TOO  各地区房屋均价
 * @Author 赵赫智
 * @Date 2019/9/27 17:09
 **/
public class AverageHousePrice {

        //根据地区请求链接 并获取均价

    public static void main(String[] args) {
        String[] areaSties = new String[]{"西安","长沙","北京","太原","成都","杭州"};
        for (String areaOne : areaSties) {
            Double areaPrice = getAvgHousePrice(areaOne);
            System.out.println(areaOne+" "+areaPrice);
        }

    }
    private static Double getAvgHousePrice(String area) {
        //拼音处理
        String citySimple = PinYinUtil.cn2py(area);
        String url = "https://"+citySimple+".anjuke.com/market/";
        //请求路径获取页面
        String price = doPost(url);
        Double resultPrice = Double.parseDouble(price.trim());
        //获取均价
        return resultPrice;
    }

    /* 发送HTTP的POST请求，获取指定国家的网页地址
     * 传入参数：country(国家): String类型
     */
    public static String doPost(String url) {
        try {
            //设置网址打开链接
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();


//设置post请求头和请求体，请求体的参数位国家
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36";
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");


//传入post请求体的参数
            conn.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//            osw.write(postParams);
            osw.flush();
            osw.close();


//获取相应结果的状态码
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
//将html内容解析成utf-8内容

                Document doc = Jsoup.parse(conn.getInputStream(), "utf-8", url);
                Element content = doc.getElementById("content");
                String text = content.select(".priceTrend").get(0).
                        select(".trendR").get(0).
                        select(".highLight").get(0).select(".up").get(0).text();
//刷选需要的网页内容
                return text;
            } else {//如果响应状态码不是200，则加载失败
                return "Get page failed!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Get page failed!";
    }
}
