package com.bluesky.zhz.core.constants;

public class Constant {
	/** 当前用户 **/
	public static final String USER_SESSION = "USER_SESSION";
	/** 当前用户菜单 **/
	public static final String USER_MENU = "USER_MENU";
	/** 没有权限 **/
	public static final String NO_AUTH_MESSAGE = "没有权限";
	/** 二维码生成地址 **/
	public static final String QRCODE_PATH = "D:/appServer/train/pub/qrcode/";
	/** 验证码 **/
	public static final String CAPTCHA_IMAGE_CODE = "CAPTCHA_IMAGE_CODE";
	/** 修改手机号验证码 **/
	public static final String CAPTCHA_IMAGE_CODE_PHONE = "CAPTCHA_IMAGE_CODE_PHONE";
	/** 重置验证码 **/
	public static final String VALIDATE_IMAGE_CODE = "VALIDATE_IMAGE_CODE";
	/** 短信验证码 **/
	public static final String SMS_CODE = "SMS_CODE";
	/** 默认parentId Integer **/
	public static final int DEFAULT_PARENT_ID = 0;
	/** 默认parentId String**/
	public static final String DEFAULT_PARENT_ID_STRING = "0000000000000000000000000";
	// 如果是AJAX请求 则响应1010状态码
	public final static Integer AJAX_STATUS = 1010;
	/** 默认关于我们 字符串**/
	public static final String ABOUT_US = "关于我们";
	/** 默认联系我们 字符串**/
	public static final String CONTACT_US = "联系我们";
	/** 默认底部管理 字符串**/
	public static final String FOOT_MANAGE = "底部管理";
	/** 默认匿名 字符串**/
	public static final String ANONYMOUS = "匿名";
	/** 默认取证流程字符串**/
	public static final String CERTPROCESS = "取证流程";
	/** 默认公司实力字符串**/
	public static final String COMPANYPOWER = "公司实力";
	/** 视频学习心跳redis键过期时间**/
	public static final Integer VIDEO_HEART_KEY_EXPIRE_SECONDS = 20;
	/** 用户默认密码**/
	public static final String DEFAULT_USER_PASSWORD = "123abc";
	/** 用户默认密码加密后缀**/
	public static final String DEFAULT_USER_PASSWORD_SUFFIX = "abc";
	/** 系统滚屏**/
	public static final String SCROLL_SCREEN = "SCROLL_SCREEN";
	/** 乐视视频同步ID列表**/
	public static final String LECLOUD_VIDEO_ID_SET = "LECLOUD_VIDEO_ID_SET";
	/** 视频同步TOPIC**/
	public static final String VIDEO_TOPIC = "videoTopic";
	/** 视频同步redis key**/
	public static final String REDIS_VIDEO_LIST = "REDIS_VIDEO_LIST";
	/** 用户OPENID**/
	public static final String USER_OPENID = "USER_OPENID";
	/** 视频上传中key**/
	public static final String VIDEO_UPLOAFING = "VIDEO_UPLOAFING";
	
}
