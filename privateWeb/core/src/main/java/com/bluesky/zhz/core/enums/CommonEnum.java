package com.bluesky.zhz.core.enums;

/**
 * 通用枚举，只存放公共枚举信息
 */
public interface CommonEnum {

	public enum ResponseStatus {

		SUCCESS("000000", "操作成功"), 
		ERROR("100000", "操作失败"),
		SYSTEM_ERROR("110000", "系统错误"), 
		UNKOWN_ERROR("120000", "访问出现未知异常"),
		EXIST("130000","您已经购买过此商品"),
		NO_AUTH("200000", "没有权限"), 
		PARAM_ERROR_ILLEGAL("300000", "参数错误"), 
		PARAM_ERROR_NULL("310000", "参数为空"), 
		VERSION_ERROR("400000", "版本错误"), 
		UPDATE_WARN("410000", "请升级版本"), 
		TOKEN_ERROR("500000", "请重新登录"), 
		TOKEN_ERROR_REPLACE("510000", "当前账号在其他地方登录"), 
		REPEAT_REQUEST("600000", "重复请求"), 
		USER_SHOW_ERROR("900000", "自定义错误");

		private String code;
		private String comment;

		ResponseStatus(String code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

	}
	enum State {

		YES(1, "是"), NO(2, "否");

		private Integer code;
		private String comment;

		State(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

	}
	enum IsDelete {

		NORMAL(0, "正常"), REMOVED(1, "删除");

		private Integer code;
		private String comment;

		IsDelete(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

	}

	enum Payment {

		UNPAID(1, "未付款"), PREPAID(2, "已付款"), CANCELPAID(3, "已取消");

		private Integer code;
		private String comment;

		Payment(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

	}

	enum StuPassword {
		PASSWORD("123456", "密码");

		private String code;
		private String comment;

		StuPassword(String code, String comment) {
			this.code = code;
			this.comment = comment;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}

	enum StuSource {
		WEBSITE(1, "网站购课"), SCHOOL(2, "学校添加");

		private Integer code;
		private String comment;

		StuSource(Integer code, String comment) {
			this.code = code;
			this.comment = comment;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}

	/**
	 * 基本资源类型
	 *
	 * @author yangrui
	 *
	 */
	enum ResourceType {
		PIC(1, "图片"), VIDEO(2, "视频"), AUDIO(3, "音频"), FILE(4, "文件");

		private Integer code;
		private String comment;

		ResourceType(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}


	/**
	 * 基本资源类型 二级分类
	 *
	 * @author yangrui
	 *
	 */
	enum SmallResourceType {

		THUMBNAIL(1, "缩略图"), SOURCE(2, "PCBanner原图"), LIST(3, "组图"), AUDITION(4, "banner移动端图片"), SelfBuiltCourseCover(5, "自建课程封面"),
		HEADPORTRAIT(6,"头像"),LOGO(7,"机构logo"),WECHATCODE(8, "微信二维码");

		private Integer code;
		private String comment;

		SmallResourceType(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}

	enum SelfBuildGoodsCheck {
//		是否已审核 -1未发起审核 0 未处理 1 审核通过 2审核拒绝
		UNCOMMITTED(1, "未发起审核"), UNTREATED(2, "未处理"), PASS(3, "审核通过"), REJECT(4, "审核拒绝");

		private Integer code;
		private String comment;

		SelfBuildGoodsCheck(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}
	
	enum ModuleType {
		MATERIAL(0, "资料"), QUESTION(1, "试题"), COURSE(2, "课程"), BOOK(3, "图书"),KNOWLEDGE(4, "知识点");
		
		private Integer code;
		private String comment;
		
		ModuleType(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public void setCode(Integer code) {
			this.code = code;
		}
		
		public String getComment() {
			return comment;
		}
		
		public void setComment(String comment) {
			this.comment = comment;
		}
	}

	enum LabelCode {

		INDEX(10,"shouye"),YUNKE(20,"云课"),PERSONAL(30,"个人中心");

		private Integer code;
		private String comment;

		LabelCode(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}
	
	enum MenuCenter {
		MYCOURSE(10, "我的课程"), COURSEACTIVE(11, "课程激活"), 
		ACCOUNT(20, "账户信息"), COLLECTION(21, "我的收藏"),ORDER(22, "我的订单"),MESSAGE(23, "我的消息");
		
		private Integer code;
		private String comment;
		
		MenuCenter(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public void setCode(Integer code) {
			this.code = code;
		}
		
		public String getComment() {
			return comment;
		}
		
		public void setComment(String comment) {
			this.comment = comment;
		}
	}
	
	enum NavMenu {
		INDEX(1, "首页"), COURSECENT(2, "课程商品"),STUDYCENTER(3, "学习中心"),PERSONALCENTER(4, "个人中心");
		
		private Integer code;
		private String comment;
		
		NavMenu(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public void setCode(Integer code) {
			this.code = code;
		}
		
		public String getComment() {
			return comment;
		}
		
		public void setComment(String comment) {
			this.comment = comment;
		}
	}
	
	enum SiteSource {
		WAP(0, "WAP站点"), PC(1, "PC站点");
		
		private Integer code;
		private String comment;
		
		SiteSource(Integer code, String commont) {
			this.code = code;
			this.comment = commont;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public void setCode(Integer code) {
			this.code = code;
		}
		
		public String getComment() {
			return comment;
		}
		
		public void setComment(String comment) {
			this.comment = comment;
		}
	}
	
}
