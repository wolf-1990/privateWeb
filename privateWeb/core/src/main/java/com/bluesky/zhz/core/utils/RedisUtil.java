package com.bluesky.zhz.core.utils;

import java.util.concurrent.TimeUnit;

import com.bluesky.zhz.core.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 保存商品课时数量
	 * @param productId
	 * @param updateCount
	 * @param totalCount
	 */
	public void setProductLessonCount( String productId, Integer updateCount, Integer totalCount) {
		redisTemplate.opsForValue().set(CommonConstants.Redis.PRODUCT_LESSON_COUNT + productId,
				updateCount + "-" + updateCount, 60 * 60 * 6, TimeUnit.SECONDS);
	}
	
	/**
	 * 获得商品课时数量
	 * @param productId
	 * @return
	 */
	public Object getProductLessonCount( String productId) {
		return redisTemplate.opsForValue().get(CommonConstants.Redis.PRODUCT_LESSON_COUNT + productId);
	}

	/**
	 * 保存商品-课程-课时信息
	 * @param productId
	 * @param productsDetailResponse
	 */
	public void setProductLessonList( String productId, String data) {
		redisTemplate.opsForValue().set(CommonConstants.Redis.PRODUCT_LESSON_LIST + productId, 
        		data,60 * 60 * 6, TimeUnit.SECONDS);
	}

	/**
	 * 查询商品-课程-课时信息
	 * @param productId
	 * @return
	 */
	public Object getProductLessonList( String productId) {
		return redisTemplate.opsForValue().get(CommonConstants.Redis.PRODUCT_LESSON_LIST + productId);
	}

	/**
	 * 查看是否有待审核的状态
	 * @return
	 */
	public Object getHasNeCheck() {
		return redisTemplate.opsForValue().get(CommonConstants.Redis.CHECK_STATUS);
	}

	/**
	 * 设置未审核状态
	 */
	public void setHasNeCheck(String status) {
		redisTemplate.opsForValue().set(CommonConstants.Redis.CHECK_STATUS,status);
	}

	/**
	 * 是否存在String 类型key值
	 * @param string
	 * @return
	 */
	public Boolean hasValueKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 根据域名获取代理商信息
	 * @param domain
	 * @return
	 */
	public Object getDomainAgentInfo(String domain) {
		return redisTemplate.opsForValue().get(CommonConstants.Redis.AGENG_DOMAIN + domain);
	}

	/**
	 * 保存代理商域名与代理商信息
	 * @param domain
	 * @param objToJson
	 */
	public void setAgentDomain(String domain, String value) {
		 redisTemplate.opsForValue().set(CommonConstants.Redis.AGENG_DOMAIN + domain,value, 60 , TimeUnit.SECONDS);
	}

	/**
	 * 获取商品详细信息
	 * @param string
	 * @return
	 */
	public Object getProductInfo(String num) {
		return redisTemplate.opsForValue().get(CommonConstants.Redis.PRODUCT_INFO + num);
	}

	/**
	 * 设置相拼详情
	 * @param num
	 * @param value
	 */
	public void setProductInfo(String num, String value) {
		redisTemplate.opsForValue().set(CommonConstants.Redis.PRODUCT_INFO + num, value, 60 * 60 , TimeUnit.SECONDS);
	}

	/**
	 * 获取redis中指定键的信息
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 设置类别下的科目信息
	 * @param key 类别ID
	 * @param objToJson
	 */
	public void setSysClassSubject(Object key, String value) {
		redisTemplate.opsForValue().set(CommonConstants.Redis.SYS_CLASS_SUBJECT + key, value, 60 * 60 * 24, TimeUnit.SECONDS);
	}

	
}
