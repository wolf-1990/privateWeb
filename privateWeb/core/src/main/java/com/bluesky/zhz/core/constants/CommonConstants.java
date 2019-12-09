package com.bluesky.zhz.core.constants;

/**
 * 通用常量，只存放公共常量信息
 */
public interface CommonConstants {

	/******************************* header中的key值 *******************************/
	public static class Header{
		// header中token的key值
		public static final String HEADER_PARAM_TOKEN = "token";
		// header中请求来源的key值
		public static final String HEADER_PARAM_SOURCE = "source";
	}

	/******************************* rsa加密的公钥私钥 *******************************/
	public static class Rsa{
		// 公钥
		public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOug3idE//45a68XelymNFF6BfR0k5E4mMoHgkjGChntpkxMz9s7wCojlEl+NgX5fB2uY5WU49xXXXqRZeoquth9YXQNEmmUH9C4FzQErFEALyCAQFJqSds5L4YKoGPdO9xEnX5biMgtoShNT9AsWNKY+NCOTlUUeoo4eodzemOQIDAQAB";
		// 私钥
		public static final String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM66DeJ0T//jlrrxd6XKY0UXoF9HSTkTiYygeCSMYKGe2mTEzP2zvAKiOUSX42Bfl8Ha5jlZTj3FddepFl6iq62H1hdA0SaZQf0LgXNASsUQAvIIBAUmpJ2zkvhgqgY9073ESdfluIyC2hKE1P0CxY0pj40I5OVRR6ijh6h3N6Y5AgMBAAECgYEAnqXsd+jZaEDJ8WfUcbiTXHfYTKaRjCrxOaoA6dONaRtW8VSTGPRxiffjST/jcJeMCGqsmkBuKju6xyntj+z5IE7L51+iYyqr6JvEZONC+Jpsw9cTtk5O6HBLe9ZjaVAq7rLZ7jOQosyO9hCXCTGw3+ZdsEJ32XbrEHD2pjIyogECQQD0Yc0vsKtkRP5J8b3QClY74gbziH6Z/35Y9d3yFJw9ANbK0nBI1/6eBHD0+GZpzOTiQmWMzISO0FdDQ1jNDYX5AkEA2I36P5RDiaY65+oNZ7OF3SLOKNwlgk+wSjBR23eNgLfevr7OeoKxgeWa97eGMvUyv/n50mPXmfZnA2uVpw4yQQJBAKE9+m0R6zPgVcuiJfQjLdya+K0sjSO8sLevmb5FHQUfDZmLGazmXsgR9Bmd36zFFOu4lVo+AuRb0Ie/4tKArrkCQFWk+QayHW/QBVX5E+oibtvBSyWPw2nhGEiPCaCxjI4OqhJoJgL/szI7W2mC9JksrjCMrYfOotMqRx1sLBiQncECQH6gFm00F1WskCJVsKKXletNZWFPMYhnwXgpKekv0iRRVYPBYHlBjksT6GVSsl2/s9m3EME1pXIFLGTpXvUsUDs=";
	}

	/******************************* redis key值 *******************************/
	public static class Redis{
		//存储会员的key
		public static final String REDIS_CURRENT_USER = "currentuser";
		//存储云校管理员的key
		public static final String REDIS_CURRENT_MANAGE = "currentmanage";
		//存储学生的key
		public static final String REDIS_CURRENT_STUDENT = "currentstudent";
		//存储代理商的key
		public static final String REDIS_CURRENT_AGENT = "currentagent";
		//存储敏感词的key
		public static final String SENSITIVE_WORD = "SENSITIVE_WORD";
		//代理商配置类别树
		public static final String SYS_CLASS_TREE = "SYS_CLASS_TREE";
		//sysClassId包含的科目列表
		public static final String SYS_CLASS_SUBJECT = "SYS_CLASS_SUBJECT_LIST:";
		//商品课时包列表
		public static final String PRODUCT_LESSON_LIST = "PRODUCT_LESSON_LIST:";
		//代理商域名
		public static final String AGENG_DOMAIN = "AGENG_DOMAIN:";
		//商品已更新及总数数量
		public static final String PRODUCT_LESSON_COUNT = "PRODUCT_LESSON_COUNT:";
		//待审核状态0-无 1-有
		public static final String CHECK_STATUS = "CHECK_STATUS";
		//商品详细信息
		public static final String PRODUCT_INFO = "PRODUCT_INFO:";
	}
	
	/******************************* 操作常量 *******************************/
	public static class Operators {
        public static final int LOGIN = 1;
        public static final int LOGINOUT = 2;
        public static final int INSERT = 3;
        public static final int UPDATE = 4;
        public static final int DELETE = 5;
        public static final int SELECT = 6;
    }
	
	/******************************* 平台 *******************************/
	public static class Platform {
        public static final int MANAGE = 1;
        public static final int AGENT = 2;
        public static final int STUDENT = 3;
    }
	
	/******************************* 平台 *******************************/
	public static class BusinessType {
		public static final int LOGINOUT = 1;
		public static final int BUSINESS = 2;
	}
	/********************************.net接口******************************/
	public static class Net {
		public static final String AIP_CORE_WANGXIAO_CN = "http://coreapi.wangxiao.cn";
		public static final String AIP_YUNXIAO_WANGXIAO_CN = "http://apiyunxiao.wangxiao.cn";


		public static final String BIGCATEGORYS = AIP_CORE_WANGXIAO_CN + "/api/Category/GetBigCategorys";
		public static final String TOPCATEGORYS = AIP_CORE_WANGXIAO_CN + "/api/Category/GetTopCategorys";
		public static final String STU_USER_SYNCREG = AIP_YUNXIAO_WANGXIAO_CN + "/api/User/SyncReg";
		public static final String STU_USER_VALIDATELOGIN = AIP_YUNXIAO_WANGXIAO_CN + "/api/User/ValidateLogin";
		public static final String STU_USER_USERPSDREST = AIP_YUNXIAO_WANGXIAO_CN + "/api/User/UserPsdReset";
		public static final String SYNCREG = AIP_YUNXIAO_WANGXIAO_CN + "/api/Agent/SyncReg";
		public static final String AGENTINFOUPDATE = AIP_YUNXIAO_WANGXIAO_CN + "/api/Agent/AgentInfoUpdate";
		public static final String VALIDATELOGIN = AIP_YUNXIAO_WANGXIAO_CN + "/api/Agent/ValidateLogin";
		public static final String ADMIN_USER_LOGIN = AIP_YUNXIAO_WANGXIAO_CN + "/api/Admin/ValidateLogin";
		public static final String MNG_GET_LIVETCLIST = AIP_YUNXIAO_WANGXIAO_CN + "/api/Products/GetLiveTaocanList";
		public static final String MNG_GET_PRODUCTCOURSE = AIP_YUNXIAO_WANGXIAO_CN + "/api/Products2/GetProductCourse";
		public static final String AGENTPSDMODIFY = AIP_YUNXIAO_WANGXIAO_CN + "/api/Agent/AgentPsdModify";
		public static final String GETCHILDREN = AIP_YUNXIAO_WANGXIAO_CN + "/api/Area/GetChildren/{parentId}";
		public static final String GETBYID = AIP_YUNXIAO_WANGXIAO_CN + "/api/Area/GetById/{id}";
		public static final String GETCHILDRENBYID = AIP_YUNXIAO_WANGXIAO_CN + "/api/SysClass/GetChildrenById/{id}";

		public static final String CC_GET_TEACHERINFO = AIP_YUNXIAO_WANGXIAO_CN + "/api/SysTeacher/GetBySysClassId";
		public static final String STU_PRODUCT_DETAIL = AIP_YUNXIAO_WANGXIAO_CN + "/api/Products/GetCourseDetail";
		public static final String PRODUCT_BASE_DETAIL = AIP_YUNXIAO_WANGXIAO_CN + "/api/Products/GetProductDetail";
		public static final String STU_PRODUCT_COURSE_LIST = AIP_YUNXIAO_WANGXIAO_CN + "/api/Products/GetClassHours";
		public static final String STU_PRODUCT_COMMENT_LIST = AIP_YUNXIAO_WANGXIAO_CN + "/api/Products/GetCommentList";
		public static final String STU_CODE_ACTIVIATION = AIP_YUNXIAO_WANGXIAO_CN + "/api/BKCode/ActiveCode";
		public static final String STU_ORDER_ORGANIZATION = AIP_YUNXIAO_WANGXIAO_CN + "/api/BKCode/AgentBuyCode";
		public static final String STU_ORDER_CANCEL = AIP_YUNXIAO_WANGXIAO_CN + "/api/Order/CancelOrder";

		public static final String STU_IS_REGISTRY = AIP_YUNXIAO_WANGXIAO_CN + "/api/User/ValiateIsRegMobile";
		public static final String STU_UPDATE_MOBILE = AIP_YUNXIAO_WANGXIAO_CN + "/api/User/UserInfoUpdateMobile";
		public static final String STU_LOAD_VPLAY_LLIST = AIP_YUNXIAO_WANGXIAO_CN + "/api/ClassHour/getClassHour";
		public static final String PRO_LESSON_COUNT = AIP_YUNXIAO_WANGXIAO_CN + "/api/Products/GetClasshoursStatistics";

		public static final String GETSUBJECTS = AIP_CORE_WANGXIAO_CN + "/api/Category/GetSubjects?id={id}&callback={callback}&Level={Level}";
		public static final String SYS_TEACHER_BYID = AIP_YUNXIAO_WANGXIAO_CN + "/api/SysTeacher/GetById/";
		
		public static final String GETADMIN_DATA = AIP_YUNXIAO_WANGXIAO_CN + "/api/Admin/GetByUserName?username=";

	}
	/********************************普通常量******************************/
	public static class Normal {
		/** 验证码 **/
		public static final String CAPTCHA_IMAGE_CODE = "CAPTCHA_IMAGE_CODE";
		/** 菜单顶级ID **/
		public static final int PERMISSION_TOP_PARENT_ID = 0;
		/** 代理项目  **/
		public static final String PROXY_BIG_TYPE_NAME = "工程类,财会类,金融类,家庭教育,外贸类";
		/** 默认父级  **/
		public static final String DEFAULT_PARENT_ID = "00000000-0000-0000-0000-000000000000";
	}
	/********************************商品类型常量******************************/
	public static class ProductType {
		public static final int COURSE = 1;// "课程"
		public static final int BOOK = 3;// "图书"),
		public static final int BOOKPACKAGE = 5;// "图书套餐"),
		public static final int COURSEPACKAGE = 6;// "课程套餐"),
		public static final int LIVEPACKAGE = 7;// "重播套餐"),
		public static final int LIVE = 8;// "直播课程"),
		public static final int CHAPTER = 9;// "章节课"),
		public static final int EXAM = 10;// "试券"),
		public static final int HIGHERROR = 11;// "高频易错"),
		public static final int EXAMPRACTICE = 12;// "考点练习"),
		public static final int CHAPTERPRODUCT = 13;// "章节商品"),
		public static final int HIGHEXAM = 14;// "高频高点"),
		public static final int TAGPRODUCT = 15;// "标签商品"),
		public static final int SUBJECTEXAMPRODUCT = 16;// "科目考点练习商品"),
		public static final int SUBJECTCHAPTERPRODUCT = 17;// "科目章节课商品"),
		public static final int RECORDLESSON = 18;// "录播课时"),
		public static final int LIVELESSON = 19;// "直播课时"),
		public static final int COREEXAM = 20;// "核心考点"),
		public static final int COREEXAMPACKAGE = 21;// "核心考点套餐"),
		public static final int ANNEXDATA = 22;// "附件资料"),
		public static final int ANNEXDATAPACKAGE = 23;// "附件资料套餐"),
		public static final int UPGRADE = 24;// "升级服务"),
		public static final int UPGRADEPACKAGE = 25;// "升级服务套餐");
	}
}
