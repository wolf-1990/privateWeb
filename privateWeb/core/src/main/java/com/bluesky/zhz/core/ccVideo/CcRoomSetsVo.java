package com.bluesky.zhz.core.ccVideo;

import lombok.Data;

/**
 * cc 直播参数设置
 * @author 孙志勇   2018年5月22日
 *
 */
@Data
public class CcRoomSetsVo {

	private String userid;//CC账户ID	
	
	private String name;//	直播间名称	
	
	private String desc;//	直播间描述	
	
	private String templatetype;//直播模板类型，请求模板信息接口可获得模板类型的详细信息。
	
	private Integer authtype;//	验证方式，0：接口验证，需要填写下面的checkurl；1：密码验证，需要填写下面的playpass；2：免密码验证	
	
	private String publisherpass;//	推流端密码，即讲师密码	
	
	private String assistantpass;//	助教端密码	
	
	private String playpass;//播放端密码	可选
	
	private String checkurl;//	验证地址	可选
	
	private Integer barrage;//	是否开启弹幕。0：不开启；1：开启	可选，默认为0
	
	private Integer foreignpublish;//	是否开启第三方推流。0：不开启；1：开启	可选，默认为0
	
	private Integer openlowdelaymode;//	开启直播低延时模式。0为关闭；1为开启	可选，默认为关闭
	
	private Integer showusercount;//	在页面显示当前在线人数。0表示不显示；1表示显示	可选，默认显示当前人数，模板一暂不支持此设置
	
	private Integer openhostmode;//开启主持人模式，"0"表示不开启；"1"表示开启	可选，默认不开启，开通主持人模式权限后方可使用
	
	private String warmvideoid;//	插播暖场视频，填写同一账号下云点播视频vid	可选，默认关闭；参数值为空，表示关闭
	
	private String livestarttime;//	直播开始时间；格式：yyyy-MM-dd HH:mm:ss	可选

	private String playerbackgroundhint;//	播放器提示语。未直播时播放器将显示该提示语	可选，最多15个字符
	
	private Integer manuallyrecordmode;//	手动录制模式。0：关闭；1：开启	可选，默认关闭
	
	private Integer  clientdocpermissions;//	讲师文档权限。0：关闭；1：开启	可选，默认关闭；
	
	private Integer repeatedloginsetting;//	重复登录设置；0：允许后进入者登录;1:禁止后进入者登录，对讲师端和观看端生效	可选，默认0
	
	private Integer maxaudiencenum;//直播间并发人数上限	可选，默认为0，表示不做限制
	
	private Integer documentdisplaymode;//	文档显示模式。1：适合窗口;2:适合宽度	可选，适合窗口
	
	private Integer openlivecountdown;//	倒计时功能。0：关闭；1：开启	可选，默认关闭
	
	private Integer showlectueronlinenum;//	讲师端显示在线人数。0：不显示；1：显示	可选，默认显示
	
	private Integer showassistonlinenum;//		助教主持人端显示在线人数。0：不显示；1：显示	可选，默认显示

    private Integer openchatmanage; //开启聊天审核。0：不开启；1：开启	可选，默认为0

    private Integer hostloginmode; //主持人登录模式，"0"表示网页登录；"1"表示客户端登录	可选，默认为0

    private Integer openmarquee;// 跑马灯功能。0：关闭；1：开启	可选，默认为0

    private String alarms; // 直播提醒设置，格式如下：[{"time":"","desc":""},{"time":"","desc":""},{"time":"","desc":""}] time为提醒时间点（单位：分钟），类型为字符串，取值范围1-1000的整数；desc为提醒内容，类型为字符串，最多20个字符	可选，默认为空
	

}
