package movie;

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
import java.util.List;

/**
 * @Description TOO 分页抓取电影
 * @Author 赵赫智
 * @Date 2019/10/25 16:46
 **/
public class ReptilePageMovie {
    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 1; i <= 27; i++) {
            String url = "https://www.1905.com/vod/subject/lst/o2p"+i+".html";
            List<Movie> moviesNew = doPost(url);
            movies.addAll(moviesNew);
        }
        movies.stream().forEach(param->{System.out.println(param.getName());});
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
                Element carousel_banner = parse.getElementById("content");

                Elements li = carousel_banner.getElementsByClass("theme-item");

                for (Element element : li) {
                    Movie movie = new Movie();
                    String name = element.select(".title").get(0).select("a").get(0).text();
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
