package Reptile;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/10/8 15:30
 **/
public class SimpleReptile {
    public static void main(String[] args) throws Exception{

    }
    @Test
    public void testGetMethod(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://yun.itheima.com/search?keys=Java");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.print(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPostMethod(){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet请求
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");

        //声明存放参数的List集合
        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("keys","java"));

        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf-8");
            httpPost.setEntity(formEntity);

            CloseableHttpResponse respose = httpClient.execute(httpPost);
            if(respose.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(respose.getEntity(), "utf-8");
                System.out.print(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPool(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //    设置最大连接数
        cm.setMaxTotal(200);

        //    设置每个主机的并发数
        cm.setDefaultMaxPerRoute(20);

        doGet(cm);
        doGet(cm);

    }

    private void doGet(PoolingHttpClientConnectionManager cm) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)//设置创建连接的最长时间
                .setConnectionRequestTimeout(500)//设置获取连接的最长时间
                .setSocketTimeout(10 * 1000).build();//设置数据传输的最长时间

        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);

            // 判断状态码是否是200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content.length());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsoupUrl(){
        try {
            //    解析url地址
            Document document = Jsoup.parse(new URL("http://www.itcast.cn/"), 1000);
            //获取title的内容
            Element title = document.getElementsByTag("title").first();
            System.out.print(title.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsoupString() throws Exception{
        String html = FileUtils.readFileToString(new File("D://jsoup.html"), "utf-8");
        Document document = Jsoup.parse(html);
        //获取title的内容
        Element title = document.getElementsByTag("title").first();
        System.out.print(title.text());
    }

    @Test
    public void testJsoupHtml() throws Exception{
        Document document = Jsoup.parse(new File("D://jsoup.html"),"utf-8");


        //1.    根据id查询元素getElementById
        Element element = document.getElementById("city_bj");

        //获取title的内容
        element = document.getElementsByTag("title").first();

        //3.   根据class获取元素getElementsByClass
        document.getElementsByClass("s_name").last();

        //4.   根据属性获取元素getElementsByAttribute
        element = document.getElementsByAttribute("abc").first();

        element = document.getElementsByAttributeValue("class", "city_con").first();

        System.out.print(element.text());
    }
}
