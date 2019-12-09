package com.bluesky.zhz.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.bluesky.zhz.core.constants.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@Component
public class RestRequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(RestRequestUtil.class);
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private SafeUtils safeUtils;
	@Autowired
	private CurrentUserUtil currentUserUtil;
	@Autowired
	private RedisUtil redisUtil;

	/*******************************************************************************************
	 * 方法区
	 *******************************************************************************************/
	private JSONObject get(String url, Map<String, Object> params) {
		String responseMessage = null;
		if (null == params) {
			responseMessage = restTemplate.getForObject(url, String.class);
		} else {
			responseMessage = restTemplate.getForObject(url, String.class, params);
		}
		if (null != responseMessage) {
			JSONObject results = JSONObject.parseObject(responseMessage);
			return results;
		}
		return null;
	}
	
	private JSONObject getWithHeader(String url, Map<String, Object> params, Map<String, String> headerMap) {
		ResponseEntity<String> response = null;
		String responseMessage = null;
		
		HttpHeaders requestHeaders = new HttpHeaders();
		String token = safeUtils.getNowToken(headerMap);
		requestHeaders.add("token", token);
		if( null != headerMap.get("username") ){
			requestHeaders.add("yun_username", headerMap.get("username"));
		}
		if( null != headerMap.get("agentname") ){
			requestHeaders.add("yun_agentname", headerMap.get("agentname"));
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
		
		if (null == params) {
			response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		} else {
			response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class,params);
		}
		responseMessage = response.getBody();
		if (null != responseMessage) {
			JSONObject results = JSONObject.parseObject(responseMessage);
			return results;
		}
		return null;
	}

	private JSONObject postJson(String urls, String json, Map<String, String> headerMap) {

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String token = safeUtils.getNowToken(headerMap);
		headers.add("token", token);
		if( null != headerMap.get("username") ){
			headers.add("yun_username", headerMap.get("username"));
		}
		if( null != headerMap.get("agentname") ){
			headers.add("yun_agentname", headerMap.get("agentname"));
		}
		
		HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
		String responseMessage = "";
		try {
			responseMessage = restTemplate.postForObject(urls, formEntity, String.class);
		} catch (Exception e) {
			logger.info("获取信息错误信息");
		}

		JSONObject results = JSONObject.parseObject(responseMessage);
		return results;
	}

	private JSONObject postForm(String urls, Map<String, Object> params) {

		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		for( Entry<String, Object> entry :params.entrySet() ){
			postParameters.add(entry.getKey(), entry.getValue());
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
		String responseMessage = restTemplate.postForObject(urls, r, String.class);
		JSONObject results = JSONObject.parseObject(responseMessage);
		return results;
	}
	
	/*******************************************************************************************
	 * 对外服务区
	 *******************************************************************************************/

	public JSONObject postJsonser(String urls, String json, Map<String, String> headerMap) {
		return postJson(urls,json,headerMap);
	}

	/**
	 * 获取全部大分类
	 * @param params
	 * @return
	 */
	public JSONObject getBigCategorys(Map<String, Object> params){
		return get(CommonConstants.Net.BIGCATEGORYS, params);
	}
	/*
	 *@Author 赵赫智
	 *@Description 获取分类树
	 *@Date 15:38 2019/2/20
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject getTopCategorys(Map<String, Object> params){
		return get(CommonConstants.Net.TOPCATEGORYS, params);
	}
	/**
	 *@Author 赵赫智
	 *@Description 代理商注册
	 *@Date 11:28 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject syncReg(Map<String, Object> params){
		return get(CommonConstants.Net.SYNCREG, params);
	}
	/**
	 *@Author 赵赫智
	 *@Description 代理商修改
	 *@Date 13:39 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject agentInfoUpdate(Map<String, Object> params) {
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("agentname", currentUserUtil.getUserName());
		return getWithHeader(CommonConstants.Net.AGENTINFOUPDATE, params,headerMap);
	}
	/**
	 *@Author 赵赫智
	 *@Description 代理商登录
	 *@Date 13:39 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject validateLogin(Map<String, Object> params) {
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("agentname", currentUserUtil.getUserName());
		return postJson(CommonConstants.Net.VALIDATELOGIN,JSONObject.toJSONString(params),headerMap);
	}
	/**
	 *@Author 赵赫智
	 *@Description 代理商重置密码
	 *@Date 13:39 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject agentPsdModify(Map<String, Object> params) {
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("agentname", currentUserUtil.getUserName());
		return postJson(CommonConstants.Net.AGENTPSDMODIFY,JSONObject.toJSONString(params),headerMap);
	}
	/**
	 *@Author 赵赫智
	 *@Description 区域通过id查找name
	 *@Date 13:39 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject findAreaById(Map<String, Object> params) {
		return getWithHeader(CommonConstants.Net.GETBYID, params,new HashMap<>());
	}
	/**
	 *@Author 赵赫智
	 *@Description 区域通过parentId查找areaList
	 *@Date 13:39 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject findAreaListByParentId(Map<String, Object> params) {
		return getWithHeader(CommonConstants.Net.GETCHILDREN+"?parentId="+params.get("parentId"),params, new HashMap<>());
	}

	/**
	 *@Author 赵赫智
	 *@Description 通过classId 查询下面所有子集ids
	 *@Date 13:39 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject getSubjects(Map<String, Object> params) {
		
		Object subjects = redisUtil.get(CommonConstants.Redis.SYS_CLASS_SUBJECT + params.get("id"));
		JSONObject jo = null;
		if( null == subjects ){
			jo = get(CommonConstants.Net.GETSUBJECTS,params);
			redisUtil.setSysClassSubject(params.get("id"),JsonUtils.objToJson(jo));
		}else{
			jo = JSONObject.parseObject(subjects.toString());
		}
		return jo;
	}
	/**
	 *@Author 赵赫智
	 *@Description 区域通过parentId查找areaList
	 *@Date 13:39 2019/2/21
	 *@return com.alibaba.fastjson.JSONObject
	 **/
	public JSONObject getClassChildrenById(Map<String, Object> params) {
		return getWithHeader(CommonConstants.Net.GETCHILDRENBYID,params, new HashMap<>());
	}

    /**
     * 学生pc端注册 同步到.net
     * @param params
     * @return
     */
	public JSONObject getStuUserSyncReg(Map<String, Object> params){
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("username", "username");
		return postJson(CommonConstants.Net.STU_USER_SYNCREG,JSONObject.toJSONString(params),headerMap);
	}

    /**
     * 学生pc端 登录 .net 验证
     * @param params
     * @return
     */
	public JSONObject getStuUserValidateLogin(Map<String, Object> params){
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("username", "username");
		return postJson(CommonConstants.Net.STU_USER_VALIDATELOGIN,JSONObject.toJSONString(params),headerMap);
	}

    /**
     * 学生pc端 .net 修改密码 或 重置密码
     * @param params
     * @return
     */
	public JSONObject doStuUserPsdReset(Map<String, Object> params){
		Map<String, String> headerMaps = new HashMap<>();
		headerMaps.put("username", currentUserUtil.getUserName());
        return postJson(CommonConstants.Net.STU_USER_USERPSDREST,JSONObject.toJSONString(params), headerMaps);
    }
	/**
	 * 云校管理员登录 
	 */
	public JSONObject getAdminValidateLogin(Map<String, Object> params) {
		return postJson(CommonConstants.Net.ADMIN_USER_LOGIN,JSONObject.toJSONString(params), new HashMap<>());
	}
	
	/**
	 * 调用私有get方法
	 * cc直播穿路径 用
	 * @param url
	 * @param params
	 * @return
	 */
	public JSONObject getter(String url, Map<String, Object> params){
		return get(url,params);
	}

	/**
	 * 获取直播课（正在直播或将要直播的课）
	 * @param params
	 * @return
	 */
	public JSONObject getLiveTaocanList(Map<String, Object> params){
		return postJson(CommonConstants.Net.MNG_GET_LIVETCLIST,JSONObject.toJSONString(params), new HashMap<>());
	}

	/**
	 * 根据商品名称查询商品详情
	 * @param params
	 * @return
	 */
	public JSONObject getProductCourseList(Map<String, Object> params){
		return postJson(CommonConstants.Net.MNG_GET_PRODUCTCOURSE,JSONObject.toJSONString(params), new HashMap<>());
	}

	/**
	 * 通过sys_class_id 查询教师信息
	 * @param params
	 * @return
	 */
	public JSONObject getTeacherInfoByClassId(String paramStr, Map<String, Object> params){
		return getWithHeader(CommonConstants.Net.CC_GET_TEACHERINFO + paramStr, params, new HashMap<>());
	}

	/**
	 * 根据product_id 查询商品详情的 课程介绍
	 * @param params
	 * @return
	 */
	public JSONObject getPruductsDetailByProId(Map<String, Object> params){
		return postJson(CommonConstants.Net.STU_PRODUCT_DETAIL,JSONObject.toJSONString(params), new HashMap<>());
	}
	
	/**
	 * 根据product_id 查询商品详情的 课程介绍(商品详情页面)
	 * @param params
	 * @return
	 */
	public JSONObject getPruductsBaseDetailByProId(Map<String, Object> params){
		Object productInfo = redisUtil.getProductInfo(params.get("Num").toString() + "_" + params.get("FromSite").toString());
		if(StringUtils.isEmpty(productInfo)){
			JSONObject result = postJson(CommonConstants.Net.PRODUCT_BASE_DETAIL,JSONObject.toJSONString(params), new HashMap<>());
			if (!StringUtils.isEmpty(result)){
				redisUtil.setProductInfo(params.get("Num").toString() + "_" + params.get("FromSite").toString(),JsonUtils.objToJson(result));
			}
			return result;
		}else{
			return JSONObject.parseObject(productInfo.toString());
		}
	}

	/**
	 * 根据product_id 查询商品课程列表
	 * @param params
	 * @return
	 */
	public JSONObject getProductCourseListByProId(Map<String, Object> params){
		return postJson(CommonConstants.Net.STU_PRODUCT_COURSE_LIST,JSONObject.toJSONString(params), new HashMap<>());
	}

	/**
	 * 根据product_id 查询商品的评价列表
	 * @param params
	 * @return
	 */
	public JSONObject getProductCommentListByProId(Map<String,Object> params){
		return postJson(CommonConstants.Net.STU_PRODUCT_COMMENT_LIST,JSONObject.toJSONString(params),new HashMap<>());
	}

	/**
	 * 课程激活（学生id，报课码）
	 * @param params
	 * @return
	 */
	public JSONObject clickActivation(Map<String,Object> params){
		String tempUrl = "?code="+ params.get("code").toString() + "&username=" + params.get("username").toString();
		return getWithHeader(CommonConstants.Net.STU_CODE_ACTIVIATION + tempUrl , null, new HashMap<>());
	}

	/**
	 * 购买报课码
	 * @param map
	 * @return
	 */
	public JSONObject submitOrderInfo(Map<String, Object> map) {
		return postJson(CommonConstants.Net.STU_ORDER_ORGANIZATION ,JSONObject.toJSONString(map),new HashMap<>());
	}

	/**
	 * 学员端取消订单
	 * @param map
	 * @return
	 */
	public JSONObject cancelOrder(Map<String, Object> map) {
		return postJson(CommonConstants.Net.STU_ORDER_CANCEL ,JSONObject.toJSONString(map),new HashMap<>());
	}

	/**
	 * 0 可以注册 其他值不能注册
	 * @param mobile
	 * @return
	 */
	public JSONObject isRegistry(String mobile){
		String tempUrl = "?mobile=" + mobile+"&yun_username=";
		return getWithHeader(CommonConstants.Net.STU_IS_REGISTRY + tempUrl, null, new HashMap<>());
	}

	/**
	 * 修改绑定手机号
	 * @param map
	 * @return
	 */
	public JSONObject updatePhone(Map<String, Object> map) {
		Map<String, String> headerMaps = new HashMap<>();
		headerMaps.put("username", currentUserUtil.getUserName());
		return postJson(CommonConstants.Net.STU_UPDATE_MOBILE ,JSONObject.toJSONString(map),headerMaps);
	}

	/**
	 * 调.net接口 在视频播放页面 加载课时列表
	 * @param map
	 * @return
	 */
	public JSONObject getClessonList(Map<String, Object> map){
		return postJson(CommonConstants.Net.STU_LOAD_VPLAY_LLIST ,JSONObject.toJSONString(map),new HashMap<>());
	}

	/**
	 * 查询商品课时更新信息
	 * @param
	 * @return
	 */
	public JSONObject getLessonsCountInfo(Map<String, Object> map) {
		return postJson(CommonConstants.Net.PRO_LESSON_COUNT ,JSONObject.toJSONString(map), new HashMap<>());
	}

	/**
	 * 根据teacherId 查询教师信息
	 * @param params
	 * @return
	 */
	public JSONObject getSysTeacherById(Map<String, Object> params){
		String tempUrl = CommonConstants.Net.SYS_TEACHER_BYID + params.get("id").toString();
		return getWithHeader(tempUrl,null,new HashMap<>());
	}

	/**
	 * 根据用户名查询用户ID
	 * @param userName
	 * @return
	 */
	public JSONObject getAdminInfo(String userName) {
		String tempUrl = CommonConstants.Net.GETADMIN_DATA + userName;
		return getWithHeader(tempUrl,null,new HashMap<>());
	}
}
