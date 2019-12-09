package util;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TOO  爬虫工具类
 * @Author 赵赫智
 * @Date 2019/9/27 17:09
 **/
public class ReptileUtil {

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
//刷选需要的网页内容
                String page = getString(doc);
                return page;
            } else {//如果响应状态码不是200，则加载失败
                return "Get page failed!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Get page failed!";
    }

    private static String getString(Document doc) {
        return doc.select("div.mcon").get(1).select("ul").select("li")
                            .select("a").attr("href");
    }


    /**
     * @return void    返回类型 
     * @throws
     * @Title: getConnection 
     * @Description: getContent()函数主要实现下载指定国家的国旗
     * @param     设定文件 
     */
    public static void getConnection(String page) {
        String base_url = "http://country.911cha.com/";
        String url = base_url + page;
        try {
//利用url解析网址
            URL uri = new URL(url);
//url连接
            URLConnection conn = uri.openConnection();
//将html内容解析成utf-8
            Document doc = Jsoup.parse(conn.getInputStream(), "utf-8", url);
//刷选需要的网页内容
            Elements image = doc.select("img");
//            Element image = doc.selectFirst("img");
            String flag_name = image.attr("alt").replace("国旗", "");
            String flag_url = image.attr("src");
            URL urls = new URL(base_url + "/" + flag_url);
// 利用FileUtils.copyURLToFile()实现图片下载
            FileUtils.copyURLToFile(urls, new File("D:\\jsoup\\flag\\" + flag_name + ".gif"));
            System.out.println("%s国旗下载成功" + ":" + flag_name);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * @param @param   fileName
     * @param @return    设定文件 
     * @return ArrayList<String>    返回类型 
     * @throws
     * @Title: readyFileByLines 
     * @Description: 以每行的样式读取文件，返回ArrayList，里面的元素位每个国家的名称 
     */

    public static List<String> readyFileByLines(String fileName) {

        File file = new File(fileName);
        BufferedReader br = null;
        List<String> list = new ArrayList<String>();
        try {
            String str = null;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            while ((str = br.readLine()) != null) {
                list.add(str);//把有值的对象添加到集合
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


}
