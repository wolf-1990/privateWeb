package com.bluesky.zhz.core.utils;

import javax.servlet.http.HttpServletRequest;

import com.bluesky.zhz.core.constants.CommonConstants;
import com.bluesky.zhz.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bluesky.zhz.core.bean.user.CurrentAgent;
import com.bluesky.zhz.core.bean.user.CurrentManage;
import com.bluesky.zhz.core.bean.user.CurrentStudent;
import com.bluesky.zhz.core.bean.user.CurrentUser;
import com.bluesky.zhz.core.enums.CommonEnum;

/**
 * 当前登录人信息操作工具类
 */
@Component
@Slf4j
public class CurrentUserUtil {
	
	ThreadLocal<String> userIdLocal = new ThreadLocal<>();
	ThreadLocal<String> userNameLocal = new ThreadLocal<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 从Redis中获取用户信息
     * @param request
     * @param isThrowException
     *              true: 未获取到用户时抛出异常
     *              false: 未获取到用户时返回null
     * @return
     */
    public CurrentUser getCurrentUser(HttpServletRequest request, boolean isThrowException) {

        // add by jxx 2019.02.20 getUserId() 如果为null 存redis 会出错
       String curUser = getUserId();
       if(StringUtils.isBlank(curUser)){
           log.error(CommonEnum.ResponseStatus.TOKEN_ERROR.toString() + CommonEnum.ResponseStatus.TOKEN_ERROR.getComment().toString());
           return null;
       }

        Object userRedisValue = redisTemplate.opsForHash().get(CommonConstants.Redis.REDIS_CURRENT_USER, curUser);
        if (userRedisValue == null && isThrowException) {
            throw new BusinessException(CommonEnum.ResponseStatus.TOKEN_ERROR);
        }
        if (userRedisValue == null && !isThrowException) {
            return null;
        }

        return JSON.parseObject(userRedisValue.toString(), CurrentUser.class);
    }
    
    /**
     * 从Redis中获取用户信息
     * @param request
     * @param isThrowException
     *              true: 未获取到用户时抛出异常
     *              false: 未获取到用户时返回null
     * @return
     */
    public CurrentManage getCurrentManage(HttpServletRequest request, boolean isThrowException) {
       
        Object userRedisValue = redisTemplate.opsForHash().get(CommonConstants.Redis.REDIS_CURRENT_MANAGE, getUserId());
        if (userRedisValue == null && isThrowException) {
            throw new BusinessException(CommonEnum.ResponseStatus.TOKEN_ERROR);
        }
        if (userRedisValue == null && !isThrowException) {
            return null;
        }

        return JSON.parseObject(userRedisValue.toString(), CurrentManage.class);
    }
    
    /**
     * 从Redis中获取用户信息
     * @param request
     * @param isThrowException
     *              true: 未获取到用户时抛出异常
     *              false: 未获取到用户时返回null
     * @return
     */
    public CurrentStudent getCurrentStudent(HttpServletRequest request, boolean isThrowException) {
        Object userRedisValue = null;
        if (StringUtil.isNotBlank(getUserId())) {
            userRedisValue= redisTemplate.opsForHash().get(CommonConstants.Redis.REDIS_CURRENT_STUDENT,getUserId() );
        }
        if (userRedisValue == null && isThrowException) {
            throw new BusinessException(CommonEnum.ResponseStatus.TOKEN_ERROR);
        }
        if (userRedisValue == null && !isThrowException) {
            return null;
        }
        return JSON.parseObject(userRedisValue.toString(), CurrentStudent.class);
    }
    
    /**
     * 从Redis中获取用户信息
     * @param request
     * @param isThrowException
     *              true: 未获取到用户时抛出异常
     *              false: 未获取到用户时返回null
     * @return
     */
    public CurrentAgent getCurrentAgent(HttpServletRequest request, boolean isThrowException) {

        Object userRedisValue = redisTemplate.opsForHash().get(CommonConstants.Redis.REDIS_CURRENT_AGENT, getUserId());
        if (userRedisValue == null && isThrowException) {
            throw new BusinessException(CommonEnum.ResponseStatus.TOKEN_ERROR);
        }
        if (userRedisValue == null && !isThrowException) {
            return null;
        }

        return JSON.parseObject(userRedisValue.toString(), CurrentAgent.class);
    }
    
    public void setUserId(String userId){
    	userIdLocal.set(userId);
    }
    
    public String getUserId(){
        return userIdLocal.get();
    }
 
    public void clearUserId(){
    	userIdLocal.remove();
    }
    
    public void setUserName(String userName){
    	userNameLocal.set(userName);
    }
    
    public String getUserName(){
        return userNameLocal.get();
    }
 
    public void clearUserName(){
    	userNameLocal.remove();
    }
    
}
