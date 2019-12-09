package com.bluesky.zhz.core.ccVideo;

import com.alibaba.fastjson.JSONObject;
import com.bluesky.zhz.core.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * cc对接工具类
 * 
 * @author 孙志勇 2018年5月15日
 *
 */
@Slf4j
public class CcVideoUtil {

	private static final String REST_REQUEST_UTIL_NAME = "restRequestUtil";

	public static String createHashedQueryString(Map<String, String> queryMap, long time, String salt) throws UnsupportedEncodingException {

		Map<String, String> map = new TreeMap<String, String>(queryMap);
		String qs = createQueryString(map); // 生成queryString方法可自己编写
		if (qs == null) {
			return null;
		}

		time = time / 1000;
		System.out.println(String.format("%s&time=%d&salt=%s", qs, time, salt));
		String hash = MD5Util.getMD5(String.format("%s&time=%d&salt=%s", qs, time, salt));
		hash = hash.toUpperCase();
		String thqs = String.format("%s&time=%d&hash=%s", qs, time, hash);

		return thqs;
	}

	public static String createQueryString(Map<String, String> map) throws UnsupportedEncodingException {
		Set<String> key = map.keySet();
		String params = "";
		for (String ss : key) {
			if (map.get(ss) != null) {
				// System.out.println(ss);
				params += ss + "=" + URLEncoder.encode(map.get(ss).toString(), "utf-8") + "&";
			}
		}

		if (!StringUtils.isBlank(params)) {
			params = params.substring(0, params.lastIndexOf("&"));
		}

		return params;
	}

	/**
	 * 直播doGet请求
	 * @param url
	 * @param queryMap
	 * @return
	 */
	@SuppressWarnings({ "resource", "static-access" })
	public static JSONObject doGet(String url, Map<String, String> queryMap) {
		/*JSONObject result = null;
		try {
			url += "?"+ createHashedQueryString(queryMap, System.currentTimeMillis(),CcConstant.API_KEY);
			result = ((RestRequestUtil)SpringTool.getBean(REST_REQUEST_UTIL_NAME)).getter(url,null);
		} catch (Exception ex) {
			log.error("ccAip 请求实时代付API异常" + url, ex);
		}
		log.info(url + "返回的信息：" + result);
		return result;*/
		HttpClient httpClient = null;
		HttpGet get = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			url += "?"
					+ createHashedQueryString(queryMap, System.currentTimeMillis(),
					CcConstant.API_KEY);
			get = new HttpGet(url);
			HttpResponse response = httpClient.execute(get);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "UTF-8");
				}
			}
		} catch (Exception ex) {
			log.error("ccAip 请求实时代付API异常" + url, ex);
		} finally {
			if (get != null) {
				get.releaseConnection();
			}
		}
		log.info(url + "返回的信息：" + result);
		return new JSONObject().parseObject(result);
	}

	/**
	 * 点播doGet请求
	 * 
	 * @param url
	 * @param queryMap
	 * @return
	 */
	@SuppressWarnings({ "resource", "static-access" })
	public static JSONObject doGetForVideo(String url, Map<String, String> queryMap) {
		//JSONObject result = null;
		HttpClient httpClient = null;
		HttpGet get = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			url += "?" + createHashedQueryString(queryMap, System.currentTimeMillis(), CcConstant.VIDEO_API_KEY);
			//result = ((RestRequestUtil) SpringTool.getBean(REST_REQUEST_UTIL_NAME)).getter(url,null);
			//url += "?" + createHashedQueryString(queryMap, System.currentTimeMillis(), CcConstant.API_KEY);
			get = new HttpGet(url);
			HttpResponse response = httpClient.execute(get);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "UTF-8");
				}
			}
		} catch (Exception ex) {
			log.error("ccAip 请求API异常" + url, ex);
		} finally {
			if (get != null) {
				get.releaseConnection();
			}
		}
		log.info(url + "返回的信息：" + result);
		return new JSONObject().parseObject(result);
		//return result;
	}

}
