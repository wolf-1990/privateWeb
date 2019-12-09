package com.bluesky.zhz.core.ccVideo;

/**
 * 常量类
 * 
 * @author 孙志勇 2018年5月16日
 *
 */
public class CcConstant {

	/**
	 * cc账号id
	 */
	public static final String CC_USER_ID = "7F595509922FD9C3";

	/**
	 * 直播房间默认密码
	 */
	public static final String ROOM_DEFAULT_PASSWORD = "123456";

	/**
	 * cc回调 录制开始
	 */
	public static final int BACK_VIDEO_REC_CREATE = 101;

	/**
	 * cc回调 录制结束
	 */
	public static final int BACK_VIDEO_REC_END = 102;

	/**
	 * cc回调 录制完成
	 */
	public static final int BACK_VIDEO_REC_COMPLETE = 102;

	/**
	 * 点播-视频分块size 1Mb file chunk max size MB
	 */
	public static final int CHUNK_SIZE = 1 * 1024 * 1024;

	/**
	 * cc离线下载域名
	 */
	public static final String OFF_DOWNLOAD_DOMAIN = "http://ccr.csslcloud.net";

	/**
	 * 直播apiKey
	 */
	public static String API_KEY = "SrzDBUyW5eoALlJr6HMpnuJcJh77EFjs";

	/**
	 * 点播apiKey
	 */
	public static String VIDEO_API_KEY = "nKAIocxSpu9jkrLb30UimuCepHeLjUbJ";

	/**
	 * 直播接口前缀
	 */
	private static String CC_API_URL = "http://api.csslcloud.net/api/";

	/**
	 * 点播接口前缀
	 */
	private static String VIDEO_API_URL = "http://spark.bokecc.com/api/";

	/**
	 * 获取直播间信息 传递一下 roomid 直播间id userid CC账户id
	 */
	public static String ROOM_INFO = CC_API_URL + "room/search";

	/**
	 * 查询回放信息 传递一下 recordid 回放id userid CC账户id
	 */
	public static String REPLAY_INFO = CC_API_URL + "v2/record/search";

	/**
	 * 删除回放视频 传递一下 videoid 视频id userid CC账户id format 返回格式
	 */
	public static String DELETE_VIDEO = VIDEO_API_URL + "video/delete";

	/**
	 * 创建直播房间 http://doc.bokecc.com/live/dev/liveapi/#toc_11
	 */
	public static String ROOM_CREATE = CC_API_URL + "room/create";

	/**
	 * 直播间状态
	 */
	public static String PUBLISHING = CC_API_URL + "rooms/publishing";

	/**
	 * 获取直播间连接数
	 */
	public static String CONNECTIONS = CC_API_URL + "statis/connections";

	/**
	 * 获取直播间内用户进出信息
	 */
	public static String USERACTION = CC_API_URL + "room/useraction";

	/**
	 * 查询回放列表
	 */
	public static String RECORDINFO = CC_API_URL + "v2/record/info";

	/**
	 * 点播-获取视频信息
	 */
	public static String GETVIDEOINFO = VIDEO_API_URL + "video/v5";

	/**
	 *点播-搜索视频
	 */
	public static String VIDEOSEARCH = VIDEO_API_URL + "videos/search";

	/**
	 *点播-删除视频
	 */
	public static String VIDEODELETE = VIDEO_API_URL + "video/delete";

	/**
	 * 点播-获取视频源 下载视频
	 */
	public static String VIDEOORIGINAL = VIDEO_API_URL + "video/original";

	/**
	 * 点播-获取上传信息(videoid, servicetype, metaurl, chunkurl等)的接口路径
	 */
	public static String UPLOADINFO_URL = VIDEO_API_URL + "video/create/v2";

	/**
	 * 获取视频播放代码
	 */
	public static String PLAYCODE = VIDEO_API_URL + "video/playcode";

}
