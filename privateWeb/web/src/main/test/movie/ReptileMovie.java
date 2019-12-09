package movie;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/10/25 13:56
 **/
public class ReptileMovie {
    public static void main(String[] args) {
        //爬取影片名 and 评分
        String url = "https://www.1905.com/vod/?fr=homepc_menu_vod";
        List<Movie> list = doPost(url);
        Collections.sort(list);
        list.forEach(param->{
            String s = param.toString();
            System.out.println(s);
        });
    }

    /* 发送HTTP的POST请求
     */
    public static List<Movie> doPost(String url) {
        try {
            //设置网址打开链接
            URL uri = new URL(url);
            //创建HttpClient对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建HttpGet请求
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = null;

            //使用HttpClient发起请求
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            List<Movie> resultList = new ArrayList<>(30);
            if (statusCode == HttpURLConnection.HTTP_OK) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                Document parse = Jsoup.parse(content);
                Element carousel_banner = parse.getElementById("carousel_banner");

                Elements li = carousel_banner.getElementsByClass("grid-3x-md");

                for (Element element : li) {
                    Movie movie = new Movie();
                    Element a = element.select("a").get(0);
                    String name = a.select("h3").get(0).text();
                    String scoreLast = a.select(".score").get(0).text();
                    if(StringUtils.isNotEmpty(scoreLast)){
                        double score = Double.parseDouble(scoreLast);
                        movie.setScore(score);
                    }
                    movie.setName(name);
                    resultList.add(movie);
                }

                return resultList;
            } else {//如果响应状态码不是200，则加载失败
                new Exception("请求失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Movie>();
    }

}
