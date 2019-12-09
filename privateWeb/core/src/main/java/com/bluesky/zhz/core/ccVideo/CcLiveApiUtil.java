package com.bluesky.zhz.core.ccVideo;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.bluesky.zhz.core.utils.BeanMap;
import lombok.extern.slf4j.Slf4j;

/**
 * cc直播接口调取
 * 
 * @author 孙志勇 2018年5月17日
 *
 */
@Slf4j
public class CcLiveApiUtil {

	/**
	 * 获取直播间的状态
	 * 
	 * @author 孙志勇 2018年5月22日
	 */
	public static JSONObject publishingStatus(String roomids) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomids", roomids);
		map.put("userid", CcConstant.CC_USER_ID);
		JSONObject result = CcVideoUtil.doGet(CcConstant.PUBLISHING, map);
		return result;
	}

	/**
	 * 创建直播间
	 * 
	 * @author 孙志勇 2018年5月22日
	 * @param //CcRommSets
	 *            直播间设置信息
	 * @return
	 */
	public static JSONObject createRoom(CcRoomSetsVo ccRommSets) {
		ccRommSets.setUserid(CcConstant.CC_USER_ID);
		Map<String, String> map = BeanMap.objectToMapForCC(ccRommSets);
		JSONObject result = CcVideoUtil.doGet(CcConstant.ROOM_CREATE, map);
		return result;
	}

	/**
	 * 获得直播间的信息
	 * 
	 * @author 孙志勇 2018年5月22日
	 * @param roomId
	 * @return
	 */
	public static JSONObject roomInfo(String roomId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomid", roomId);
		map.put("userid", CcConstant.CC_USER_ID);
		JSONObject result = CcVideoUtil.doGet(CcConstant.ROOM_INFO, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 查询回放信息
	 * 
	 * @author 杨頔 2018年10月19日
	 * @param recordId
	 * @return
	 */
	public static JSONObject replayInfo(String recordId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("recordid", recordId);
		map.put("userid", CcConstant.CC_USER_ID);
		JSONObject result = CcVideoUtil.doGet(CcConstant.REPLAY_INFO, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 删除回放视频
	 * 
	 * @author 杨頔 2018年10月20日
	 * @param videoId
	 * @return
	 */
	public static JSONObject deleteVideo(String videoId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("videoid", videoId);
		map.put("userid", CcConstant.CC_USER_ID);
		map.put("format", "json");
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.DELETE_VIDEO, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 获取直播间连接数
	 * @param map
	 * @return
	 */
	public static JSONObject connections(Map<String, String> map){
		map.put("userid",CcConstant.CC_USER_ID);
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.CONNECTIONS, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 获取直播间内用户进出信息
	 * @param map
	 * @return
	 */
	public static JSONObject useraction(Map<String, String> map){
		map.put("userid",CcConstant.CC_USER_ID);
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.USERACTION, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 过去视频播放列表
	 * @param map
	 * @return
	 */
	public static JSONObject recordInfo(Map<String, String> map){
		map.put("userid",CcConstant.CC_USER_ID);
		JSONObject result = CcVideoUtil.doGet(CcConstant.RECORDINFO, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 点播功能 - 获取视频信息
	 * @param map
	 */
	public static JSONObject getVideoInfo(Map<String,String> map){
		map.put("userid",CcConstant.CC_USER_ID);
		map.put("format","json");
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.GETVIDEOINFO, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 点播功能 - 搜索视频
	 * @param map
	 */
	public static JSONObject getVideoSearch(Map<String,String> map){
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("userid",CcConstant.CC_USER_ID);
		reqMap.put("q","TITLE:"+map.get("title").toString());
		reqMap.put("sort","CREATION_DATE:DESC");
		reqMap.put("num_per_page","1");
		reqMap.put("page","1");
		reqMap.put("format","json");
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.VIDEOSEARCH, reqMap);
		log.info(result.toString());
		return result;
	}

	/**
	 * 点播功能 - 删除视频
	 * @param map
	 */
	public static JSONObject getVideoDelete(Map<String,String> map){
		map.put("userid",CcConstant.CC_USER_ID);
		map.put("format","json");
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.VIDEODELETE, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 点播功能 - 获取视频原片下载地址
	 * @param map
	 */
	public static JSONObject getVideoOrginal(Map<String,String> map){
		map.put("userid",CcConstant.CC_USER_ID);
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.VIDEOORIGINAL, map);
		log.info(result.toString());
		return result;
	}

	/**
	 * 点播功能 - 获取视频播放代码
	 * @param map
	 * @return
	 *
	 *  videoid	视频id，不可为空
		userid	用户id，不可为空
		playerid	播放器id，若为空，返回默认播放器
		player_width	播放器宽度，单位px
		player_height	播放器高度，单位px
		auto_play	是否自动播放，true 或false
		format	返回格式，xml 或json
	 */
	public static JSONObject getVideoPlayCode(Map<String,String> map){
		map.put("userid",CcConstant.CC_USER_ID);
		map.put("playerid","");
		map.put("auto_play","false");
		map.put("format","json");
		JSONObject result = CcVideoUtil.doGetForVideo(CcConstant.PLAYCODE, map);
		log.info(result.toString());
		return result;
	}
}
