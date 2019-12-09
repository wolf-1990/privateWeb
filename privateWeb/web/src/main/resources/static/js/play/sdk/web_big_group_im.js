/* WebBigGroupIM API definitions
 * v1.0
 */
var WebBigGroupIM = { // namespace object WebBigGroupIM

	/* function login
	 *   登录SDK
	 * params:
	 *   loginInfo		- Object, 登录身份相关参数集合，详见下面
	 *   {
	 *     sdkAppID		- String, 用户标识接入SDK的应用ID，必填
	 *     appIDAt3rd	- String, App用户使用OAuth授权体系分配的Appid，和sdkAppID一样，必填
         *     accountType	- int, 账号类型，必填
	 *     identifier	- String, 用户帐号，选填
	 *     identifierNick	- String, 用户昵称，选填
	 *     userSig		- String, 鉴权Token，选填
	 *   }
	 *   listeners		- Object, 事件回调函数集合, 详见下面
	 *   {
	 *     onConnNotify - function(connInfo), 用于收到连接状态相关通知的回调函数
	 *     jsonpCallback -function(rspData),//IE9(含)以下浏览器用到的jsonp回调函数
	 *     onBigGroupMsgNotify	- function(msgList), 用于接收群消息的回调函数, 
         *               msgList为[Msg对象]
	 *   			 	  
	 *   }
	 *   options		- Object, 其它选项
	 * return:
	 *   (无)
	 */
	login: function(loginInfo, listeners, options,cbOk,cbErr) {},


	/* function sendMsg
	 *   发送一条消息
	 * params:
	 *   msg	- WebBigGroupIM.Msg类型, 要发送的消息对象
	 *   cbOk	- function()类型, 当发送消息成功时的回调函数
	 *   cbErr	- function(err)类型, 当发送消息失败时的回调函数, err为错误对象
	 * return:
	 *   (无)
	 */
	sendMsg: function(msg, cbOk, cbErr) {},
        
	
	/* class WebBigGroupIM.Msg
	 *   一条消息的描述类, 消息发送、接收的API中都会涉及此类型的对象
	 * properties:
	 *   sess	- Session object-ref, 消息所属的会话(e.g:我与好友A的C2C会话，我与群组G的GROUP会话)
	 *   isSend	- Boolean, true表示是我发出消息, false表示是发给我的消息
	 *   seq	- Integer, 消息序列号, 用于判断消息是否同一条
	 *   random	- Integer, 消息随机数,用于判断消息是否同一条
	 *   time	- Integer, 消息时间戳, 为unix timestamp
	 *   fromAccount -String,  消息发送者帐号
	 *   subType -Integer,  消息子类型，c2c消息时，0-表示普通消息；群消息时，0-普通消息，1-点赞消息，2-提示消息
	 *   fromAccountNick -String,  消息发送者昵称
	 *   elems	- Array of WebBigGroupIM.Msg.Elem, 描述消息内容的元素列表
	 * constructor:
	 *   Msg(sess, isSend, seq,random time,fromAccount) - 构造函数, 参数定义同上面properties中定义
	 * methods:
	 *   addText(text)	- 向elems中添加一个TEXT元素
	 *   addFace(face)	- 向elems中添加一个FACE元素
	 *   toHtml()		- 转成可展示的html String
	 *addFace
	 * sub-class WebBigGroupIM.Msg.Elem
	 *   消息中一个组成元素的描述类, 一条消息的内容被抽象描述为N个元素的有序列表
	 * properties:
	 *   type	- 元素类型, 目前有TEXT(文本)、FACE(表情)、IMAGE(图片)等
	 *   content- 元素内容体, 当TEXT时为String, 当PIC时为UrlString
	 * constructor:
	 *   Elem(type, content) - 构造函数, 参数定义同上面properties中定义
	 *
	 * sub-class WebBigGroupIM.Msg.Elem.TextElem
	 *   文本
	 * properties:
	 *   text  - String 内容
	 * constructor:
	 *   TextElem(text) - 构造函数, 参数定义同上面properties中定义
         *   
	 * sub-class WebBigGroupIM.Msg.Elem.FaceElem
	 *   表情
	 * properties:
	 *   index  - Integer 表情索引, 用户自定义
	 *   data   - String 额外数据，用户自定义
	 * constructor:
	 *   FaceElem(index,data) - 构造函数, 参数定义同上面properties中定义
         *   
	 *
	 */
	Msg: function(sess, isSend, seq, random,time,fromAccount,subType,fromAccountNick) {/*Class constructor*/},

	/* singleton object MsgStore
	 * WebBigGroupIM.MsgStore是消息数据的Model对象(参考MVC概念), 它提供接口访问当前存储的会话和消息数据
	 * 下面说明下会话数据类型: Session
	 *
	 * class Session
	 *   一个Session对象描述一个会话，会话可简单理解为最近会话列表的一个条目，它由两个字段唯一标识:
	 *     type	- String, 会话类型(如"C2C", "GROUP", ...)
	 *     id	- String, 会话ID(如C2C类型中为对方帐号,"C2C"时为好友ID,"GROUP"时为群ID)
	 * properties:
	 *   (Session对象未对外暴露任何属性字段, 所有访问通过下面的getter方法进行)
	 * methods:
	 *   type()		- String, 返回会话类型,"C2C"表示与好友私聊，"GROUP"表示群聊
	 *   id()		- String, 返回会话ID 
	 *   name()		- String, 返回会话标题(如C2C类型中为对方的昵称)
	 *   icon()		- String, 返回会话图标(对C2C类型中为对方的头像URL)
	 *   unread()           - Integer, 返回会话未读条数
	 *   time()		- Integer, 返回会话最后活跃时间, 为unix timestamp
	 *   curMaxMsgSeq()	- Integer, 返回会话最大消息序列号
	 *   msgCount()	- Integer, 返回会话中所有消息条数
	 *   msg(index)	- WebBigGroupIM.Msg, 返回会话中第index条消息
	 */
	MsgStore: {
		/* function sessMap
		 *   获取所有会话
		 * return:
		 *   所有会话对象
		 */
		sessMap: function() {return {/*Object*/};},
		/* function sessCount
		 *   获取当前会话的个数
		 * return:
		 *   Integer, 会话个数
		 */
		sessCount: function() {return 0;},

		/* function sessByTypeId
		 *   根据会话类型和会话ID取得相应会话
		 * params:
		 *   type	- String, 会话类型(如"C2C", "GROUP", ...)
		 *   id		- String, 会话ID(如对方ID)
		 * return:
		 *   Session, 会话对象(说明见上面)
		 */
		sessByTypeId: function(type, id) {return {/*Session Object*/};},
		/* function delSessByTypeId
		 *   根据会话类型和会话ID删除相应会话
		 * params:
		 *   type	- String, 会话类型(如"C2C", "GROUP", ...)
		 *   id		- String, 会话ID(如对方ID)
		 * return:
		 *   Boolean, 布尔类型
		 */
		delSessByTypeId: function(type, id) {return true;},
                
		/* function resetCookieAndSyncFlag
		 *   重置上一次读取新c2c消息Cookie和是否继续拉取标记
		 * return:
		 *   
		 */
		resetCookieAndSyncFlag: function() {}
	}

};

/* WebBigGroupIM API implementation
 */
(function(WebBigGroupIM) {
        //版本
        var SDK={
            'VERSION':'1.0',//WebBigGroupIM sdk版本号
            'APPID':'537046642'//WebBigGroupIM sdk 版本 appid
        };
        
        //是否启用正式环境，默认启用
        var isAccessFormaEnvironment=true;
        
        //后台接口主机
        var SRV_HOST={
            'FORMAL':{
                'COMMON':'https://webim.tim.qq.com',
                'BIG_GROUP':'https://webapi.tim.qq.com'
            },
            'TEST':{
                'COMMON':'https://test.tim.qq.com',
                'BIG_GROUP':'https://test.tim.qq.com'
            }
        };
        
        //服务名
        var SRV_NAME={
            'OPEN_IM':'openim',//私聊（拉取未读c2c消息，长轮询，c2c消息已读上报等）服务名
            'GROUP':'group_open_http_svc',//群组管理（拉取群消息，创建群，群成员管理，群消息已读上报等）服务名
            'FRIEND':'sns',//关系链管理（好友管理，黑名单管理等）服务名
            'PROFILE':'profile',//资料管理（查询，设置个人资料等）服务名
            'PIC':'openpic',//图片（上传图片）服务名
            'BIG_GROUP':'group_open_http_noauth_svc',//直播大群 群组管理（创建群，申请加群）服务名
            'BIG_GROUP_LONG_POLLING':'group_open_long_polling_http_noauth_svc'//直播大群 长轮询（拉取消息等）服务名
        };
        
        //不同服务对应的版本号
        var SRV_NAME_VER={
            'openim':'v4',
            'group_open_http_svc':'v3',
            'sns':'v3',
            'profile':'v3',
            'openpic':'v1',
            'group_open_http_noauth_svc':'v1',
            'group_open_long_polling_http_noauth_svc':'v1'
        };
        
        //会话类型
        var SESSION_TYPE={
            'C2C':'C2C',//私聊类型,即和好友聊天
            'GROUP':'GROUP'//群聊类型
        };
        
        //消息最大长度（字节）
        var MSG_MAX_LENGTH={
            'C2C':12000,//私聊消息
            'GROUP':8898//群聊
        };
        var ACTION_STATUS={
            'OK':'OK',//后台接口返回成功
            'FAIL':'FAIL'//后台接口返回失败
        };
        
        //消息元素类型
        var MSG_ELEMENT_TYPE={
            'TEXT':'TIMTextElem',//文本
            'FACE':'TIMFaceElem',//表情
            'IMAGE':'TIMImageElem',//图片
            'CUSTOM':'TIMCustomElem',//自定义
            'SOUND':'TIMSoundElem',//语音，只支持显示
            'FILE':'TIMFileElem',//文件，只支持显示
            'LOCATION':'TIMLocationElem',//地理位置,暂不支持
            'GROUP_TIP':'TIMGroupTipElem'//群提示消息
        };
        
        //图片类型
        var IMAGE_TYPE={
            'ORIGIN':1,//原图
            'LARGE':2,//缩略大图
            'SMALL':3//缩略小图
        };
        
        //下载文件
        var DOWNLOAD_FILE={
            'BUSSINESS_ID':'10001',//业务ID
            'AUTH_KEY':'617574686b6579',//文件authkey
            'SERVER_IP':'182.140.186.147',//文件服务器IP
            'TYPE':{//文件类型
                'SOUND':'2106',//语音
                'FILE':'2107'//普通文件
            }
        };

        //长轮询消息类型
        var LONG_POLLINNG_EVENT_TYPE={
            "C2C":1,//新的c2c消息通知
            "GROUP_COMMON":3,//新的群普通消息
            "GROUP_TIP":4,//新的群提示消息
            "GROUP_SYSTEM":5,//新的群系统消息
            "GROUP_TIP2":6//新的群提示消息
        };
        
        //c2c消息子类型
        var C2C_MSG_SUB_TYPE={
            "COMMON":0//普通消息
        };
        
        //群消息子类型
        var GROUP_MSG_SUB_TYPE={
            "COMMON":0,//普通消息
            "LOVEMSG":1,//点赞消息
            "TIP":2,//提示消息
            "REDPACKET":3//红包消息
        };
        
        //群消息优先级类型
        var GROUP_MSG_PRIORITY_TYPE={
            "REDPACKET":1,//红包消息
            "COMMON":2,//普通消息
            "LOVEMSG":3,//点赞消息
            "MEMBERJOIN":4,//进群
            "MEMBERQUIT":5//退群
        };
        
        //群提示消息类型
        var GROUP_TIP_TYPE={
            "JOIN":1,//加入群组
            "QUIT":2,//退出群组
            "KICK":3,//被踢出群组
            "SET_ADMIN":4,//被设置为管理员
            "CANCEL_ADMIN":5,//被取消管理员
            "MODIFY_GROUP_INFO":6,//修改群资料
            "MODIFY_MEMBER_INFO":7//修改群成员信息
        };
        
        //群提示消息-群资料变更类型
        var GROUP_TIP_MODIFY_GROUP_INFO_TYPE={
            "FACE_URL":1,//修改群头像URL
            "NAME":2,//修改群名称
            "OWNER":3,//修改群主
            "NOTIFICATION":4,//修改群公告
            "INTRODUCTION":5//修改群简介
        };
        
        //群系统消息类型
        var GROUP_SYSTEM_TYPE={
            "JOIN_GROUP_REQUEST":1,//申请加群请求（只有管理员会收到）
            "JOIN_GROUP_ACCEPT":2,//申请加群被同意（只有申请人能够收到）
            "JOIN_GROUP_REFUSE":3,//申请加群被拒绝（只有申请人能够收到）
            "KICK":4,//被管理员踢出群(只有被踢者接收到)
            "DESTORY":5,//群被解散(全员接收)
            "CREATE":6,//创建群(创建者接收, 不展示)
            "INVITED_JOIN_GROUP_REQUEST":7,//邀请加群(被邀请者接收)
            "QUIT":8,//主动退群(主动退出者接收, 不展示)
            "SET_ADMIN":9,//设置管理员(被设置者接收)
            "CANCEL_ADMIN":10,//取消管理员(被取消者接收)
            "REVOKE":11,//群已被回收(全员接收, 不展示)
            "CUSTOM":255//用户自定义通知(默认全员接收)
        };
        
        //连接状态
        var CONNECTION_STATUS={
            'ON':0,//连接正常
            'OFF':9999//连接已断开,可能是用户网络问题，或者长轮询接口报错引起的
        };
        
        //群提示消息最多显示人数
        var GROUP_TIP_MAX_USER_COUNT=10;
        
        //当前大群长轮询返回错误次数
        var curBigGroupLongPollingRetErrorCount=0;
        
        //最大允许长轮询返回错误次数
        var LONG_POLLING_MAX_RET_ERROR_COUNT=15;
        
        //ie8,9采用jsonp方法解决ajax跨域限制
        var jsonpRequestId=0;//jsonp请求id
        //最新jsonp请求返回的json数据
        var jsonpLastRspData=null;
        //兼容ie7/8/9,jsonp回调函数
        var jsonpCallback=null;
        
        //浏览器版本信息
        var BROWSER_INFO={};
        //是否为ie9（含）以下
        var lowerBR=false;
            
        //当前登录用户
	var ctx = {
                sdkAppID:null,
                appIDAt3rd:null,
                accountType:null,
                identifier:null,
                identifierNick:null,
                userSig:null,
		contentType: 'json',
		apn: 1
	};
	var opt = {};
        var xmlHttpObjSeq=0;
	var xmlHttpObjMap={};
	var curSeqMap = {};
        
        //表情标识字符和索引映射关系对象，用户可以自定义
        var emotionDataIndexs = {
                    "[礼物]": "0",
                    "[愤怒]": "1",
                    "[鄙视]": "2",
                    "[伤心]": "3",
                    "[再见]": "4",
                    "[高兴]": "5",
                    "[流汗]": "6",
                    "[无聊]": "7",
                    "[疑问]": "8",
                    "[你好]": "9",
                    "[反对]": "10",
                    "[赞同]": "11",
                    "[鼓掌]": "12",
                    "[太快了]": "13",
                    "[太慢了]": "14",
                    "[值得思考]": "15",
                    "[凋谢]": "16",
                    "[鲜花]": "17"
                };
        
        //表情对象，用户可以自定义
        var emotions = {
                    "[礼物]": "http://static.gensee.com/webcast/static/emotion/chat.gift.png",
                    "[愤怒]": "http://static.gensee.com/webcast/static/emotion/emotion.angerly.gif",
                    "[鄙视]": "http://static.gensee.com/webcast/static/emotion/emotion.bs.gif",
                    "[伤心]": "http://static.gensee.com/webcast/static/emotion/emotion.cry.gif",
                    "[再见]": "http://static.gensee.com/webcast/static/emotion/emotion.goodbye.gif",
                    "[高兴]": "http://static.gensee.com/webcast/static/emotion/emotion.laugh.gif",
                    "[流汗]": "http://static.gensee.com/webcast/static/emotion/emotion.lh.gif",
                    "[无聊]": "http://static.gensee.com/webcast/static/emotion/emotion.nod.gif",
                    "[疑问]": "http://static.gensee.com/webcast/static/emotion/emotion.question.gif",
                    "[你好]": "http://static.gensee.com/webcast/static/emotion/emotion.smile.gif",
                    "[反对]": "http://static.gensee.com/webcast/static/emotion/feedback.against.gif",
                    "[赞同]": "http://static.gensee.com/webcast/static/emotion/feedback.agreed.png",
                    "[鼓掌]": "http://static.gensee.com/webcast/static/emotion/feedback.applaud.png",
                    "[太快了]": "http://static.gensee.com/webcast/static/emotion/feedback.quickly.png",
                    "[太慢了]": "http://static.gensee.com/webcast/static/emotion/feedback.slowly.png",
                    "[值得思考]": "http://static.gensee.com/webcast/static/emotion/feedback.think.png",
                    "[凋谢]": "http://static.gensee.com/webcast/static/emotion/rose.down.png",
                    "[鲜花]": "http://static.gensee.com/webcast/static/emotion/rose.up.png"
                };
                        //腾讯登录服务错误码（用于托管模式）
        var TLS_ERROR_CODE={
            'OK':0,//成功
            'SIGNATURE_EXPIRATION':11//用户身份凭证过期
        };
        //工具类
	var tool = new function() {
		
                //格式化时间戳
		this.formatTimeStamp = function(time,format) {
                    if(time==0){
                        return time;
                    }
                    format=format || 'yyyy-MM-dd hh:mm:ss';
                    var date = new Date(time * 1000);
                    var formatTime;
                    var o = {
                        "M+": date.getMonth() + 1, //月份 
                        "d+": date.getDate(), //日 
                        "h+": date.getHours(), //小时 
                        "m+": date.getMinutes(), //分 
                        "s+": date.getSeconds() //秒   
                    };
                    if (/(y+)/.test(format)) 
                        formatTime = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
                    for (var k in o){
                        if (new RegExp("(" + k + ")").test(formatTime)) 
                            formatTime = formatTime.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    } 
                    return formatTime;
                };
                //根据名字获取url参数值
                this.getQueryString=function(name) { 
                    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
                    var r = window.location.search.substr(1).match(reg); 
                    if (r != null) return unescape(r[2]); 
                    return null; 
                };
                
                //根据群类型英文名转换成中文名
                this.groupTypeEn2Ch = function(type_en) {
                        var type_ch = null;
                        switch (type_en) {
                            case 'Public':
                                type_ch = '公开群';
                                break;
                            case 'ChatRoom':
                                type_ch = '聊天室';
                                break;
                            case 'Private':
                                type_ch = '讨论组';
                                break;
                            default:
                                type_ch = type_en;
                                break;
                        }
                        return type_ch;
                };
                //根据群类型中文名转换成英文名
                this.groupTypeCh2En = function(type_ch) {
                        var type_en = null;
                        switch (type_ch) {
                            case '公开群':
                                type_en = 'Public';
                                break;
                            case '聊天室':
                                type_en = 'ChatRoom';
                                break;
                            case '讨论组':
                                type_en = 'Private';
                                break;
                            default:
                                type_en = type_ch;
                                break;
                        }
                        return type_en;
                };
                //根据群身份英文名转换成群身份中文名
                this.groupRoleEn2Ch = function(role_en) {
                        var role_ch = null;
                        switch (role_en) {
                            case 'Member':
                                role_ch = '成员';
                                break;
                            case 'Admin':
                                role_ch = '管理员';
                                break;
                            case 'Owner':
                                role_ch = '群主';
                                break;
                            default:
                                role_ch = role_en;
                                break;
                        }
                        return role_ch;
                };
                //根据群身份中文名转换成群身份英文名
                this.groupRoleCh2En = function(role_ch) {
                        var role_en = null;
                        switch (role_ch) {
                            case '成员':
                                role_en = 'Member';
                                break;
                            case '管理员':
                                role_en = 'Admin';
                                break;
                            case '群主':
                                role_en = 'Owner';
                                break;
                            default:
                                role_en = role_ch;
                                break;
                        }
                        return role_en;
                };
                //根据群消息提示类型英文转换中文
                this.groupMsgFlagEn2Ch = function(msg_flag_en) {
                        var msg_flag_ch = null;
                        switch (msg_flag_en) {
                            case 'AcceptAndNotify':
                                msg_flag_ch = '接收并提示';
                                break;
                            case 'AcceptNotNotify':
                                msg_flag_ch = '接收不提示';
                                break;
                            case 'Discard':
                                msg_flag_ch = '屏蔽';
                                break;
                            default:
                                msg_flag_ch = msg_flag_en;
                                break;
                        }
                        return msg_flag_ch;
                };
                //根据群消息提示类型中文名转换英文名
                this.groupMsgFlagCh2En = function(msg_flag_ch) {
                        var msg_flag_en = null;
                        switch (msg_flag_ch) {
                            case '接收并提示':
                                msg_flag_en = 'AcceptAndNotify';
                                break;
                            case '接收不提示':
                                msg_flag_en = 'AcceptNotNotify';
                                break;
                            case '屏蔽':
                                msg_flag_en = 'Discard';
                                break;
                            default:
                                msg_flag_en = msg_flag_ch;
                                break;
                        }
                        return msg_flag_en;
                };
                //将空格和换行符转换成HTML标签
                this.formatText2Html=function(text){
                    var html=text;
                    if(html){
                        html=this.xssFilter(html);//用户昵称或群名称等字段会出现脚本字符串
                        html=html.replace(/ /g,"&nbsp;");
                        html=html.replace(/\n/g,"<br/>");
                    }
                    return html;
                };
                //将HTML标签转换成空格和换行符
                this.formatHtml2Text=function(html){
                    var text=html;
                    if(text){
                        text=text.replace(/&nbsp;/g," ");
                        text=text.replace(/<br\/>/g,"\n");
                    }
                    return text;
                };
                //获取字符串所占字节数 
                this.getStrBytes=function(str){
                    if (str == null) return 0;
                    if (typeof str != "string"){
                        str += "";
                    }
                    return str.replace(/[^x00-xff]/g, "012").length;
                };
                //防止XSS攻击
                this.xssFilter = function (val) {
                    val = val.toString();
                    val = val.replace(/[<]/g, "&lt;");
                    val = val.replace(/[>]/g, "&gt;");
//                    val = val.replace(/"/g, "&quot;");
                    //val = val.replace(/'/g, "&#039;");
                    return val;
                };
             
                //去掉头尾空白符
                this.trimStr=function(str){
                    if(!str) return '';
                    str=str.toString();
                    return str.replace(/(^\s*)|(\s*$)/g,"");
                };
                //判断是否为8位整数
                this.validNumber=function(str){
                    str=str.toString();
                    return str.match(/(^\d{1,8}$)/g);
                };
                this.getReturnError=function(errorInfo,errorCode){
                    var error={
                        'ActionStatus':ACTION_STATUS.FAIL,
                        'ErrorCode':errorCode,
                        'ErrorInfo':errorInfo
                    };
                    return error;
                };
                //设置cookie
                //name 名字
                //value 值
                //expires 有效期(单位：秒)
                //path 
                //domain 作用域
                this.setCookie=function(name,value,expires,path,domain){
                    
                    var exp  = new Date();
                    exp.setTime(exp.getTime() + expires*1000);
                    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
                };
                //获取cookie
                this.getCookie=function (name){
                    var result = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
                    if(result != null){
                        return unescape(result[2]);
                    }  
                    return null;
                };
                //删除cookie
                this.delCookie=function (name){
                    var exp = new Date();
                    exp.setTime(exp.getTime() - 1);
                    var value=this.getCookie(name);
                    if(value!=null) 
                        document.cookie= name + "="+escape (value)+";expires="+exp.toGMTString();
                };
                //判断IE版本号，ver表示版本号
                this.isIE = function(ver){
                    var b = document.createElement('b')
                    b.innerHTML = '<!--[if IE ' + ver + ']><i></i><![endif]-->'
                    return b.getElementsByTagName('i').length === 1;
                };
                //判断浏览器版本
                this.getBrowserInfo=function(){
                    var Sys={};
                    var ua=navigator.userAgent.toLowerCase();
                    var s;
                    (s=ua.match(/msie ([\d.]+)/))?Sys.ie=s[1]:
                    (s=ua.match(/firefox\/([\d.]+)/))?Sys.firefox=s[1]:
                    (s=ua.match(/chrome\/([\d.]+)/))?Sys.chrome=s[1]:
                    (s=ua.match(/opera.([\d.]+)/))?Sys.opera=s[1]:
                    (s=ua.match(/version\/([\d.]+).*safari/))?Sys.safari=s[1]:0;
                    if(Sys.ie){//Js判断为IE浏览器

                        return {
                            'type':'ie',
                            'ver':Sys.ie
                        };
                    }
                    if(Sys.firefox){//Js判断为火狐(firefox)浏览器
                        return {
                            'type':'firefox',
                            'ver':Sys.firefox
                        };
                    }
                    if(Sys.chrome){//Js判断为谷歌chrome浏览器
                        return {
                            'type':'chrome',
                            'ver':Sys.chrome
                        }; 
                    }
                    if(Sys.opera){//Js判断为opera浏览器
                        return {
                            'type':'opera',
                            'ver':Sys.opera
                        };
                    }
                    if(Sys.safari){//Js判断为苹果safari浏览器
                        return {
                            'type':'safari',
                            'ver':Sys.safari
                        };
                    }
                    return {
                        'type':'unknow',
                        'ver':-1
                    };
                };
                  
	};
        
        //控制台日志对象
        var log = new function () {

            var on=true;
            
            this.setOn=function(onFlag){
                on=onFlag;
            };
            
            this.getOn=function(){
                return on;  
            };
            
            this.error = function (logStr) {
                try {
//                    on && console.error(logStr);
                } catch (e) {}
            };
            this.warn = function (logStr) {
                try {
//                   on && console.warn(logStr);
                } catch (e) {}
            };
            this.info = function (logStr) {
                try {
//                    on && console.info(logStr);
                } catch (e) {}
            };
            this.debug = function (logStr) {
                try {
//                    on && console.debug(logStr);
                } catch (e) {}
            };
        };
        
	//获取unix时间戳
	var unixtime = function(d) {
		if (!d) d = new Date();
		return Math.round(d.getTime()/1000);
	};
        //时间戳转日期
	var fromunixtime = function(t) {
		return new Date(t*1000);
	};
        //获取下一个消息序号
	var nextSeq = function(userId) {
                var tempSeq=curSeqMap[userId];
                if(tempSeq){
                    tempSeq=curSeqMap[userId]=tempSeq+1;
                }else{
                    tempSeq=curSeqMap[userId]=1;
                }
		return tempSeq;
	};
        //产生随机数
        var createRandom = function() {
		return  Math.round(Math.random() * 4294967296);
	};
        
        //获取ajax请求对象
	var getXmlHttp = function() {
		var xmlhttp = null;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			try {
				xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					return null
				}
			}
		}
		return xmlhttp;
	}
        //发起ajax请求
	var ajaxRequest = function(meth, url, req, timeout,cbOk, cbErr) {
                
                var xmlHttpObj = getXmlHttp();
             
                var error,errInfo;
		if (!xmlHttpObj){
                    errInfo = "创建请求失败";
                    var error=tool.getReturnError(errInfo,-1);
                    log.error(errInfo);
                    if (cbErr) cbErr(error);
                    return;
                }
                //保存ajax请求对象
                xmlHttpObjSeq++;
                xmlHttpObjMap[xmlHttpObjSeq]=xmlHttpObj;
                
		xmlHttpObj.open(meth, url, true);
		xmlHttpObj.onreadystatechange = function() {
			if (xmlHttpObj.readyState == 4) {
                                xmlHttpObjMap[xmlHttpObjSeq]=null;//清空
				if (xmlHttpObj.status == 200||xmlHttpObj.status==0) {
					if (cbOk) cbOk(xmlHttpObj.responseText);
                                        xmlHttpObj=null;
				} else {
//					errInfo = "["+xmlHttpObj.status+"]请求服务器出错";
//                                        error=tool.getReturnError(errInfo,xmlHttpObj.status);
//                                        xmlHttpObj=null;
//					if (cbErr) cbErr(error);
				}
			}
		};
                if(BROWSER_INFO.type=='safari'){
                    xmlHttpObj.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');//为了兼容safari浏览器
                }
                //设置超时时间
                if(timeout){
                    xmlHttpObj.timeout = timeout;
                }else{
                    xmlHttpObj.timeout = 5000;
                }
                xmlHttpObj.ontimeout = function(event){
                    var errInfo = "请求服务器超时";
                    var error=tool.getReturnError(errInfo,-2);
                    xmlHttpObj=null;
//                    if (cbErr) cbErr(error);
                }
                //
		xmlHttpObj.send(req);
	}
        //发起ajax请求（json格式数据）
	var ajaxRequestJson = function(meth, url, req, timeout,cbOk, cbErr) {
		ajaxRequest(meth, url, JSON.stringify(req), timeout,function(resp) {
			var json = null;
			if (resp) json=JSON.parse(resp);//将返回的json字符串转换成json对象
			if (cbOk) cbOk(json);
		}, cbErr);
	}
        //判断用户是否已登录
	var isLogin = function() {
		return ctx.sdkAppID && ctx.identifier;
	};
        //检查是否登录
	var checkLogin = function(cbErr,isNeedCallBack) {
		if (!isLogin()) {
                        if(isNeedCallBack){
                            var errInfo = "请登录";
                            var error=tool.getReturnError(errInfo,-3);
                            log.error('请登录');
                            if(cbErr) cbErr(error);
                        }
			
			return false;
		}
		return true;
	}
        //检查是否访问正式环境
        var checkAccessFormalEnv=function(){
            return isAccessFormaEnvironment;
        };
        
        //根据不同的服务名和命令，获取对应的接口地址
	var getApiUrl = function(srvName,cmd) {
                var host,url;
                if(checkAccessFormalEnv()){
                    host=SRV_HOST.FORMAL.COMMON;
                }else{
                    host=SRV_HOST.TEST.COMMON;
                }
                //如果访问的接口是大群(长轮询和进群和退群)接口
                if (srvName==SRV_NAME.BIG_GROUP_LONG_POLLING || srvName==SRV_NAME.BIG_GROUP){
                    if(checkAccessFormalEnv()){
                        host=SRV_HOST.FORMAL.BIG_GROUP;
                    }else{
                        host=SRV_HOST.TEST.BIG_GROUP;
                    }
                    
                }
                url=host+'/'+SRV_NAME_VER[srvName]+'/'+srvName+'/'+cmd+'?websdkappid='+SDK.APPID+'&v='+SDK.VERSION+'&sdkappid='+ctx.sdkAppID+'&appidat3rd='+ctx.appIDAt3rd+'&reqtime='+unixtime();
                
                //已登录
                if(isLogin()){
                    //需要对用户帐号进行url编码（兼容游客模式下，帐号会包含＃）
                    url+='&identifier='+encodeURIComponent(ctx.identifier)+'&accounttype='+ctx.accountType+'&usersig='+ctx.userSig+'&apn='+ctx.apn+'&contenttype='+ctx.contentType;
                }
                
		return url;
	};
        
        //重置ajax请求
        var clearXmlHttpObjMap=function(){
            //遍历xmlHttpObjMap{}
            for ( var seq in xmlHttpObjMap ){ 
                var xmlHttpObj=xmlHttpObjMap[seq];
                if(xmlHttpObj){
                    xmlHttpObj.abort();//中断ajax请求(长轮询)
                    xmlHttpObjMap[xmlHttpObjSeq]=null;//清空
                }
            }
            xmlHttpObjSeq=0;
            xmlHttpObjMap={};
        };
        
        //重置sdk全局变量
        var clearSdk=function(){
            
            clearXmlHttpObjMap();
            
            //当前登录用户
            ctx = {
                sdkAppID:null,
                appIDAt3rd:null,
                accountType:null,
                identifier:null,
                identifierNick:null,
                userSig:null,
		contentType: 'json',
		apn: 1
            };
            opt = {};
            
            curSeqMap={};
            
            //ie8,9采用jsonp方法解决ajax跨域限制
            jsonpRequestId=0;//jsonp请求id
            //最新jsonp请求返回的json数据
            jsonpLastRspData=null;
            
            ConnManager.clear();
            MsgManager.clear();
        };
        
        //登录
        var _login=function(loginInfo,listeners,options,cbOk,cbErr){
            
                clearSdk();
                
                if (options) opt = options;
                if(opt.isAccessFormalEnv==false){
                    isAccessFormaEnvironment=opt.isAccessFormalEnv;
                }
                if(opt.isLogOn==false){
                    log.setOn(opt.isLogOn);
                }
                /*
                if(opt.emotions){
                    emotions=opt.emotions;
                    WebBigGroupIM.Emotions= emotions;
                }
                if(opt.emotionDataIndexs){
                    emotionDataIndexs=opt.emotionDataIndexs;
                    WebBigGroupIM.EmotionDataIndexs= emotionDataIndexs;
                }*/
                
                //initBrowserInfo();
                if(!loginInfo){
                    if(cbErr){
                        cbErr(tool.getReturnError("loginInfo is empty",-4));
                        return;
                    }
                }
                if(!loginInfo.sdkAppID){
                    if(cbErr){
                        cbErr(tool.getReturnError("loginInfo.sdkAppID is empty",-5));
                        return;
                    }
                }
                if(!loginInfo.appIDAt3rd){
                    if(cbErr){
                        cbErr(tool.getReturnError("loginInfo.appIDAt3rd is empty",-6));
                        return;
                    }
                }
                if(loginInfo.sdkAppID!=loginInfo.appIDAt3rd){
                    if(cbErr){
                        cbErr(tool.getReturnError("loginInfo.sdkAppID and loginInfo.appIDAt3rd is not equal",-7));
                        return;
                    }
                }
                if(!loginInfo.accountType){
                    if(cbErr){
                        cbErr(tool.getReturnError("loginInfo.accountType is empty",-8));
                        return;
                    }
                }
                
                ctx.sdkAppID = loginInfo.sdkAppID;
		ctx.appIDAt3rd = loginInfo.appIDAt3rd;
                ctx.accountType = loginInfo.accountType;
		ctx.identifier = loginInfo.identifier;
                if(ctx.identifierNick){
                    ctx.identifierNick = loginInfo.identifierNick;
                }else{
                    ctx.identifierNick = loginInfo.identifier;
                }
                if(loginInfo.identifier && !loginInfo.userSig){
                    if(cbErr){
                        //cbErr(tool.getReturnError("loginInfo.userSig is empty",-9));
                        return;
                    }
                }
		ctx.userSig = loginInfo.userSig;
                
                if(ctx.identifier && ctx.userSig){//带登录态
                    //登录
                    proto_login(
                         function(identifierNick){
                             MsgManager.init(
                                        listeners.onBigGroupMsgNotify,
                                        listeners.onGroupSystemNotifys,
                                        listeners.onGroupInfoChangeNotify,
                                        function(mmInitResp){
                                            if(cbOk){
                                                mmInitResp.identifierNick=identifierNick;
                                                cbOk(mmInitResp);
                                            }
                                            //初始化文件服务器ip和authkey
                                            //initIpAndAuthkey(function(resp){
                                                            
                                            //                  },cbErr);
                                        },cbErr);
                         },
                         cbErr
                    );
                }else{//不带登录态
                    MsgManager.init(listeners.onBigGroupMsgNotify,
                                     listeners.onGroupSystemNotifys,
                                     listeners.onGroupInfoChangeNotify,
                                     cbOk,cbErr);
                }
        };
        
        
        
        //初始化浏览器信息
        var initBrowserInfo=function(){
                //初始化浏览器类型
                BROWSER_INFO=tool.getBrowserInfo();
                log.info('BROWSER_INFO: type='+BROWSER_INFO.type+', ver='+BROWSER_INFO.ver);
                if (BROWSER_INFO.type=="ie") {
                    if (parseInt(BROWSER_INFO.ver) < 10) {
                        lowerBR = true;
                    }
                }
        };
        
        // REST API calls
        
        //获取语音和文件下载IP和authkey
        var proto_getIpAndAuthkey = function(cbOk, cbErr) {
		if (!checkLogin(cbErr,false)) return;
		ConnManager.apiCall(SRV_NAME.OPEN_IM,"authkey", {}, cbOk, cbErr);
	};

	
        
        //登录
	var proto_login = function(cbOk, cbErr) {
		if (!checkLogin(cbErr,true)) return;
                //获取昵称
		ConnManager.apiCall(SRV_NAME.OPEN_IM,"login", {"State":"Online"}, 
                            function(loginResp){
                                //if(cbOk) cbOk(ctx.identifierNick);//回传当前用户昵称
                                var tag_list = [
                                    "Tag_Profile_IM_Nick"
                                    //"Tag_Profile_IM_Gender",
                                    //"Tag_Profile_IM_AllowType"
                                ];
                                var options = {
                                    'From_Account': ctx.identifier,
                                    'To_Account': [ctx.identifier],
                                    'LastStandardSequence': 0,
                                    'TagList': tag_list
                                };
                                proto_getProfilePortrait(
                                    options,
                                    function (resp) {
                                        
                                        var nick, gender, allowType;
                                        if (resp.UserProfileItem && resp.UserProfileItem.length > 0) {
                                            for (var i in resp.UserProfileItem) {
                                                
                                                for (var j in resp.UserProfileItem[i].ProfileItem) {
                                                    switch (resp.UserProfileItem[i].ProfileItem[j].Tag) {
                                                        case 'Tag_Profile_IM_Nick':
                                                            nick = resp.UserProfileItem[i].ProfileItem[j].Value;
                                                            if(nick) ctx.identifierNick = nick;
                                                            break;
                                                        
                                                    }
                                                }     
                                            }
                                        }
                                        if(cbOk) cbOk(ctx.identifierNick);//回传当前用户昵称
                                    },cbErr);
                            }
                        , cbErr);
	};
        //登出
	var proto_logout = function(cbOk, cbErr) {
		if (!checkLogin(cbErr,false)){//不带登录态
                    clearSdk();
                    if(cbOk) cbOk({
                            'ActionStatus':ACTION_STATUS.OK,
                            'ErrorCode':0,
                            'ErrorInfo':'logout success'
                        });
                    return;
                }
		ConnManager.apiCall(SRV_NAME.OPEN_IM,"logout", {}, 
                        function(resp){
                            clearSdk();
                            if(cbOk) cbOk(resp);
                        }, 
                        cbErr);
	};
        
        //发送消息，包括私聊和群聊
	var proto_sendMsg = function(msg, cbOk, cbErr) {
		if (!checkLogin(cbErr,true)) return;
                var msgInfo = null;
                
                switch(msg.sess.type()){
                    case SESSION_TYPE.C2C:
                        msgInfo = {
                            'From_Account': ctx.identifier,
                            'To_Account': msg.sess.id(),
                            'MsgTimeStamp': msg.time,
                            'MsgSeq': msg.seq,
                            'MsgRandom':msg.random,
                            'MsgBody': []
                        };
                        break;
                    case SESSION_TYPE.GROUP:
                        var subType=msg.subType;
                        msgInfo = {
                            'GroupId': msg.sess.id(),
                            'From_Account': ctx.identifier,
                            'Random': msg.random,
                            'MsgBody': []
                        };
                        switch(subType){
                            case GROUP_MSG_SUB_TYPE.COMMON:
                                msgInfo.MsgPriority="COMMON";
                                break;
                            case GROUP_MSG_SUB_TYPE.REDPACKET:
                                msgInfo.MsgPriority="REDPACKET";
                                break;
                            case GROUP_MSG_SUB_TYPE.LOVEMSG:
                                msgInfo.MsgPriority="LOVEMSG";
                                break;
                            case GROUP_MSG_SUB_TYPE.TIP:
                                log.error("不能主动发送群提示消息,subType="+subType);
                                break;
                            default:
                                log.error("发送群消息时，出现未知子消息类型：subType="+subType);
                                return;
                                break;
                        };
                        break;
                    default:
                        log.error("发送消息时，出现未知消息类型：type="+msg.sess.type());
                        return;
                        break;
                }
		
		for (var i in msg.elems) {
			var elem = msg.elems[i];
                        var msgContent=null;
                        var msgType=elem.type;
                        switch(msgType){
                            case MSG_ELEMENT_TYPE.TEXT://文本
                                msgContent={'Text':elem.content.text};
                                break;
                            case MSG_ELEMENT_TYPE.FACE://表情
                                msgContent={'Index':elem.content.index,'Data':elem.content.data};
                                break;
                            case MSG_ELEMENT_TYPE.IMAGE://图片
                                var ImageInfoArray=[];
                                for(var j in elem.content.ImageInfoArray){
                                    ImageInfoArray.push(
                                        {
                                            'Type':elem.content.ImageInfoArray[j].type,
                                            'Size':elem.content.ImageInfoArray[j].size,
                                            'Width':elem.content.ImageInfoArray[j].width,
                                            'Height':elem.content.ImageInfoArray[j].height,
                                            'URL':elem.content.ImageInfoArray[j].url
                                        }
                                    );
                                }
                                msgContent={'UUID':elem.content.UUID,'ImageInfoArray':ImageInfoArray};
                                break;
                            case MSG_ELEMENT_TYPE.SOUND://
                                msgContent={'Text':'web端暂不支持发送语音消息'};
                                msgType=MSG_ELEMENT_TYPE.TEXT;
                                break;
                            case MSG_ELEMENT_TYPE.LOCATION://
                                msgContent={'Text':'web端暂不支持发送地理位置消息'};
                                msgType=MSG_ELEMENT_TYPE.TEXT;
                                break;
                            case MSG_ELEMENT_TYPE.FILE://
                                msgContent={'Text':'web端暂不支持发送文件消息'};
                                msgType=MSG_ELEMENT_TYPE.TEXT;
                                break;
                            case MSG_ELEMENT_TYPE.CUSTOM://
                                msgContent={'Data':elem.content.data,'Desc':elem.content.desc,'Ext':elem.content.ext};
                                msgType=MSG_ELEMENT_TYPE.CUSTOM;
                                break;
                            default :
                                msgContent={'Text':'web端暂不支持发送'+elem.type+'消息'};
                                msgType=MSG_ELEMENT_TYPE.TEXT;
                                break;
                        }
			msgInfo.MsgBody.push({'MsgType':msgType,'MsgContent':msgContent});
		}
                if(msg.sess.type()==SESSION_TYPE.C2C){//私聊
                    ConnManager.apiCall(SRV_NAME.OPEN_IM,"sendmsg", msgInfo, cbOk, cbErr);
                }else if(msg.sess.type()==SESSION_TYPE.GROUP){//群聊
                    ConnManager.apiCall(SRV_NAME.GROUP,"send_group_msg", msgInfo, cbOk, cbErr);
                }
               
	};
        
        
        //长轮询接口( 大群消息)
        var proto_bigGroupLongPolling = function(options,cbOk, cbErr,timeout) {
                ConnManager.apiCall(SRV_NAME.BIG_GROUP_LONG_POLLING,"get_msg", options, cbOk, cbErr,timeout);

	};

        //群组接口
        
        //申请加入大群
        var proto_applyJoinBigGroup = function(options,cbOk, cbErr) {
                var srvName;
		if (!checkLogin(cbErr,false)){//未登录
                    srvName=SRV_NAME.BIG_GROUP;
                }else{//已登录
                    srvName=SRV_NAME.GROUP;
                }
		ConnManager.apiCall(srvName,"apply_join_group", {
                                                'GroupId':options.GroupId,
                                                'ApplyMsg':options.ApplyMsg,
                                                'UserDefinedField':options.UserDefinedField
                                              }, 
                                   function(resp){
                                       if(resp.JoinedStatus && resp.JoinedStatus=='JoinedSuccess'){
                                           MsgManager.setBigGroupLongPollingOn(true);//开启长轮询
                                           MsgManager.setBigGroupLongPollingKey(resp.LongPollingKey);//更新大群长轮询key
                                           MsgManager.setBigGroupLongPollingMsgMap(options.GroupId,0);//收到的群消息置0
                                           MsgManager.bigGroupLongPolling();//开启长轮询
                                       }
                                       if(cbOk) cbOk(resp);
                                   }
                                   , function(err){

                                        if(cbErr) cbErr(err);
                                   });
	};
        
        //退出大群
        var proto_quitBigGroup = function(options,cbOk, cbErr) {
                var srvName;
		if (!checkLogin(cbErr,false)){//未登录
                    srvName=SRV_NAME.BIG_GROUP;
                }else{//已登录
                    srvName=SRV_NAME.GROUP;
                }
		ConnManager.apiCall(srvName,"quit_group", 
                                            {'GroupId':options.GroupId}, 
                                            function(resp){
                                                //重置当前再请求中的ajax
                                                clearXmlHttpObjMap();
                                                //退出大群成功之后需要重置长轮询信息
                                                MsgManager.resetBigGroupLongPollingInfo();
                                                if(cbOk) cbOk(resp);
                                            },
                                            cbErr);
	};
        
        
        //资料接口
        //查看个人资料
        var proto_getProfilePortrait = function(options,cbOk, cbErr) {
		if (!checkLogin(cbErr,true)) return;
		ConnManager.apiCall(SRV_NAME.PROFILE,"portrait_get", {
                                              'From_Account':options.From_Account,
                                              'To_Account':options.To_Account,
                                              'LastStandardSequence':options.LastStandardSequence,
                                              'TagList':options.TagList
                                          }, 
                                    function(resp){
                                        var errorAccount=[];
                                        if(resp.Fail_Account && resp.Fail_Account.length){
                                            errorAccount=resp.Fail_Account;
                                        }
                                        if(resp.Invalid_Account && resp.Invalid_Account.length){
                                            for(var k in resp.Invalid_Account){
                                                errorAccount.push(resp.Invalid_Account[k]);
                                            } 
                                        }
                                        if(errorAccount.length){
                                            resp.ActionStatus=ACTION_STATUS.FAIL;
                                            resp.ErrorCode=ERROR_CODE_CUSTOM;
                                            resp.ErrorInfo='';
                                            for(var i in errorAccount){
                                                var failCount=errorAccount[i];
                                                for(var j in resp.UserProfileItem){
                                                    if(resp.UserProfileItem[j].To_Account==failCount){
                                                        var resultCode=resp.UserProfileItem[j].ResultCode;
                                                        resp.UserProfileItem[j].ResultInfo = "[" + resultCode + "]" + resp.UserProfileItem[j].ResultInfo;
                                                        resp.ErrorInfo+="账号:"+failCount+","+resp.UserProfileItem[j].ResultInfo+"\n";
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        if(resp.ActionStatus==ACTION_STATUS.FAIL ){
                                            if(cbErr) cbErr(resp);
                                        }else if(cbOk) {
                                            cbOk(resp);
                                        }
                                    }, 
                                    cbErr);
	};
        
        //end
        //初始化浏览器信息
        initBrowserInfo();
	// singleton object ConnManager     
	var ConnManager = lowerBR == false 
        ? new function() {
		var onConnCallback = null;//回调函数
		this.init = function(onConnNotify,cbOk,cbErr) {
                        if(onConnNotify) onConnCallback=onConnNotify;
		};
                this.callBack=function(info){
                    if(onConnCallback) onConnCallback(info); 
                };
                this.clear=function(){
                    onConnCallback=null;
                };
                //请求后台服务接口
		this.apiCall = function(type,cmd, data, cbOk, cbErr,timeout) {
                        //封装后台服务接口地址
                        var url=getApiUrl(type,cmd);
                        //发起ajax请求
			ajaxRequestJson("POST", url, data, timeout,function(resp) {
                                //成功
				if (resp.ActionStatus == ACTION_STATUS.OK) {
                                        
                                        log.info("["+type+"]["+cmd+"]success:\nurl:\n"+url+"\ndata:\n"+JSON.stringify(data)+"\nresponse:\n"+JSON.stringify(resp));
                                            
					if (cbOk) cbOk(resp);//回调
				} else {
                                        //报错
					if (cbErr){
                                            resp.ErrorInfo="["+resp.ErrorCode+"]"+resp.ErrorInfo;
                                            
                                            if(cmd!='get_msg'){
                                                log.error("["+type+"]["+cmd+"]fail:\nurl:\n"+url+"\ndata:\n"+JSON.stringify(data)+"\nresponse:\n"+JSON.stringify(resp));
                                            }else{
                                                log.info("["+type+"]["+cmd+"]fail:\nurl:\n"+url+"\ndata:\n"+JSON.stringify(data)+"\nresponse:\n"+JSON.stringify(resp));
                                            }
                                            cbErr(resp);
                                        }
                                            
				}
			}, cbErr);
		};
	} 
        :new function () {
		var onConnCallback = null;        //回调函数
		this.init = function(onConnNotify,cbOk,cbErr) {
			if(onConnNotify) onConnCallback=onConnNotify;
		};
                this.callBack=function(info){
                    if(onConnCallback) onConnCallback(info);
                };
                this.clear=function(){
                    onConnCallback=null;
                };
                //请求后台服务接口
		this.apiCall = function(type,cmd, data, cbOk, cbErr) {
                        //封装后台服务接口地址
                        var url=getApiUrl(type,cmd);
                        //发起jsonp请求
                        var reqId = jsonpRequestId++,
                        cbkey = 'jsonpcallback', // the 'callback' key
                        cbval = 'jsonpRequest' + reqId, // the 'callback' value
                        script = document.createElement('script'),
                        loaded = 0;
                        
                        window[cbval] = jsonpCallback;
                        script.type = 'text/javascript';
                        url=url+"&"+cbkey+"="+cbval+"&jsonpbody="+encodeURIComponent(JSON.stringify(data));
                        script.src = url;
                        script.async = true;
                        
                        if (typeof script.onreadystatechange !== 'undefined') {
                            // need this for IE due to out-of-order onreadystatechange(), binding script
                            // execution to an event listener gives us control over when the script
                            // is executed. See http://jaubourg.net/2010/07/loading-script-as-onclick-handler-of.html
                            script.event = 'onclick';
                            script.htmlFor = script.id = '_jsonpRequest_' + reqId;
                        }
                        
 
                        script.onload = script.onreadystatechange = function () {
                            if ((this.readyState && this.readyState!== 'complete' && this.readyState!== 'loaded') || loaded) {
                                return false;
                            }
                            script.onload = script.onreadystatechange = null;
                            script.onclick && script.onclick();
                            // Call the user callback with the last value stored and clean up values and scripts.
                            var resp=jsonpLastRspData;
                            if (resp.ActionStatus == ACTION_STATUS.OK){
                                log.info("["+type+"]["+cmd+"]success:\nurl:\n"+url+"\ndata:\n"+JSON.stringify(data)+"\nresponse:\n"+JSON.stringify(resp));
                                cbOk && cbOk(resp);
                            }else{
                                resp.ErrorInfo="["+resp.ErrorCode+"]"+resp.ErrorInfo;
                                if(cmd!='get_msg'){
                                    log.error("["+type+"]["+cmd+"]fail:\nurl:\n"+url+"\ndata:\n"+JSON.stringify(data)+"\nresponse:\n"+JSON.stringify(resp));
                                }else{
                                    log.info("["+type+"]["+cmd+"]fail:\nurl:\n"+url+"\ndata:\n"+JSON.stringify(data)+"\nresponse:\n"+JSON.stringify(resp));
                                }
                                cbErr && cbErr(resp);
                            }
                            jsonpLastRspData = undefined;
                            document.body.removeChild(script);
                            loaded = 1;
                        };
 
                        // Add the script to the DOM head
                        document.body.appendChild(script);
		};
        };
	// class Session
	var Session = function(type, id, name, icon, time,seq) {
		this._impl = {
			skey: Session.skey(type, id),
			type: type,
			id: id,
			name: name,
			icon: icon,
			unread: 0,//未读消息数
			isAutoRead: false,
			time: time >= 0 ? time : 0,
                        curMaxMsgSeq:seq >= 0 ? seq : 0,
			msgs: []
		};
	};
	Session.skey = function(type, id) {
		return type + id;
	};
	Session.prototype.type = function() {return this._impl.type;};
	Session.prototype.id = function() {return this._impl.id;};
	Session.prototype.name = function() {return this._impl.name;};
	Session.prototype.icon = function() {return this._impl.icon;};
	Session.prototype.unread = function() {return this._impl.unread;};
	Session.prototype.time = function() {return this._impl.time;};
	Session.prototype.curMaxMsgSeq = function() {return this._impl.curMaxMsgSeq;};
	Session.prototype.msgCount = function() {return this._impl.msgs.length;};
	Session.prototype.msg = function(index) {return this._impl.msgs[index];};
        
	Session.prototype._impl_addMsg = function(msg) {
		this._impl.msgs.push(msg);
		
		if (msg.time > this._impl.time)
			this._impl.time = msg.time;
                
                if (msg.seq > this._impl.curMaxMsgSeq)
			this._impl.curMaxMsgSeq = msg.seq;
                //自己发送的消息不计入未读数
		if (!msg.isSend && !this._impl.isAutoRead){
			this._impl.unread++;
                        
                }
	};
        //class C2CMsgReadedItem
        var C2CMsgReadedItem=function(toAccount,lastedMsgTime){
            this.toAccount=toAccount;
            this.lastedMsgTime=lastedMsgTime;
        }
	// class Msg
	var Msg = function(sess, isSend, seq, random,time,fromAccount,subType,fromAccountNick) {
		this.sess = sess;
                this.subType = subType>=0 ? subType : 0;//消息子类型,c2c消息时：type取值为0；group消息时：type取值0、1、2：0-普通群消息，1-群点赞消息，2-群提示消息
                this.fromAccount = fromAccount;
                this.fromAccountNick=fromAccountNick ? fromAccountNick : fromAccount;
                this.isSend = Boolean(isSend);
		this.seq = seq >= 0 ? seq : nextSeq(fromAccount);
		this.random = random >= 0 ? random : createRandom();
		this.time = time >= 0 ? time : unixtime();
		this.elems = [];
	};
        
        Msg.prototype.getSession = function() {
		return this.sess;
	};
        
        Msg.prototype.getSubType = function() {
		return this.subType;
	};
        Msg.prototype.getFromAccount = function() {
		return this.fromAccount;
	};
        Msg.prototype.getFromAccountNick = function() {
		return this.fromAccountNick;
	};
        Msg.prototype.getIsSend = function() {
		return this.isSend;
	};
        Msg.prototype.getSeq = function() {
		return this.seq;
	};
        Msg.prototype.getTime = function() {
		return this.time;
	};
        Msg.prototype.getRandom= function() {
		return this.random;
	};
        Msg.prototype.getElems= function() {
		return this.elems;
	};
        
        //文本
	Msg.prototype.addText = function(text) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.TEXT, text));
	};
        //表情
	Msg.prototype.addFace = function(face) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.FACE, face));
	};
        //图片
	Msg.prototype.addImage = function(image) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.IMAGE, image));
	};
        //语音
	Msg.prototype.addSound = function(sound) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.SOUND, sound));
	};
        //文件
	Msg.prototype.addFile = function(file) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.FILE, file));
	};
        //位置
	Msg.prototype.addLocation = function(location) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.LOCATION, location));
	};
        //自定义
	Msg.prototype.addCustom = function(custom) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.CUSTOM, custom));
	};
        //群提示消息
	Msg.prototype.addGroupTip = function(groupTip) {
		this.addElem(new WebBigGroupIM.Msg.Elem(MSG_ELEMENT_TYPE.GROUP_TIP, groupTip));
	};
        
	Msg.prototype.addElem = function(elem) {
		this.elems.push(elem);
	};
	Msg.prototype.toHtml = function() {
		var html = "";
		for (var i in this.elems) {
			var elem = this.elems[i];
			html += elem.toHtml();
		}
                return html;
	};
	// class Msg.Elem
	Msg.Elem = function(type, content) {
		this.type = type;
		this.content = content;
	};
	Msg.Elem.prototype.toHtml = function() {
		var html;
                html=this.content.toHtml();
		return html;
	};
        Msg.Elem.prototype.getType = function() {
		return this.type;
	};
        Msg.Elem.prototype.getContent = function() {
		return this.content;
	};
        // class Msg.Elem.Text
	Msg.Elem.Text = function(text) {
		this.text = tool.xssFilter(text);
	};
        Msg.Elem.Text.prototype.getText = function() {
                return	this.text;
	};
        Msg.Elem.Text.prototype.toHtml = function() {
                return	this.text;
	};
        // class Msg.Elem.Face
	Msg.Elem.Face = function(index,data) {
		this.index = index;
		this.data = data;
	};
        Msg.Elem.Face.prototype.getIndex = function() {
                return this.index;
	};
        Msg.Elem.Face.prototype.getData = function() {
                return this.data;     
	};
        Msg.Elem.Face.prototype.toHtml = function() {
                var emotion=emotions[this.index];
		if(emotion && emotion[1]){
                    return	"<img src='" + emotion[1] + "'/>";
                }else{
                    return this.data;
                }
	};
        
        //图片消息
        // class Msg.Elem.Images
	Msg.Elem.Images = function(imageId) {
		this.imageId = imageId;
		this.ImageInfoArray=[];
	};
        Msg.Elem.Images.prototype.addImage = function(image) {
                this.ImageInfoArray.push(image);    
	};
        Msg.Elem.Images.prototype.toHtml = function() {
                var smallImage=this.getImage(IMAGE_TYPE.SMALL);
                var bigImage=this.getImage(IMAGE_TYPE.LARGE);
                var oriImage=this.getImage(IMAGE_TYPE.ORIGIN);
                if(!bigImage){
                    bigImage=smallImage;
                }
                if(!oriImage){
                    oriImage=smallImage;
                }
                return	"<img src='" + smallImage.getUrl() + "#"+bigImage.getUrl()+"#"+oriImage.getUrl()+"' style='CURSOR: hand' id='"+this.getImageId()+"' bigImgUrl='"+bigImage.getUrl()+"' onclick='imageClick(this)' />";
                
	}; 
        Msg.Elem.Images.prototype.getImageId = function() {
                return this.imageId;
	};
        Msg.Elem.Images.prototype.getImage = function(type) {
                for(var i in this.ImageInfoArray){
                    if(this.ImageInfoArray[i].getType()==type){
                        return this.ImageInfoArray[i];
                    }
                }
                return null;
	};
        // class Msg.Elem.Images.Image
	Msg.Elem.Images.Image = function(type,size,width,height,url) {
		this.type = type;
		this.size = size;
		this.width = width;
		this.height = height;
		this.url = url;
	};
        Msg.Elem.Images.Image.prototype.getType= function() {
                return this.type;  
	};
        Msg.Elem.Images.Image.prototype.getSize= function() {
                return this.size;  
	};
        Msg.Elem.Images.Image.prototype.getWidth= function() {
                return this.width;  
	};
        Msg.Elem.Images.Image.prototype.getHeight= function() {
                return this.height;  
	};
        Msg.Elem.Images.Image.prototype.getUrl= function() {
                return this.url;  
	};
        
        // class Msg.Elem.Location
	Msg.Elem.Location = function(longitude,latitude,desc) {
		this.longitude = longitude;//经度
		this.latitude = latitude;//纬度
		this.desc = desc;//描述
	};
        Msg.Elem.Location.prototype.getLongitude = function() {
                return this.longitude;
	};
        Msg.Elem.Location.prototype.getLatitude = function() {
                return this.latitude;
	};
        Msg.Elem.Location.prototype.getDesc = function() {
                return this.desc;     
	};
        Msg.Elem.Location.prototype.toHtml = function() {
                return '经度='+this.longitude+',纬度='+this.latitude+',描述='+this.desc;
	};
        
        // class Msg.Elem.Sound
	Msg.Elem.Sound = function(uuid,second,size,senderId,downUrl) {
		this.uuid = uuid;//语音id
		this.second = second;//时长，单位：秒
		this.size = size;//大小，单位：字节
		this.senderId = senderId;//发送者id
		this.downUrl = downUrl;//下载url
	};
        Msg.Elem.Sound.prototype.getUUID = function() {
                return this.uuid;
	};
        Msg.Elem.Sound.prototype.getSecond = function() {
                return this.second;     
	};
        Msg.Elem.Sound.prototype.getSize = function() {
                return this.size;     
	};
        Msg.Elem.Sound.prototype.getSenderId = function() {
                return this.senderId;     
	};
        Msg.Elem.Sound.prototype.getDownUrl = function() {
                return this.downUrl;     
	};
        Msg.Elem.Sound.prototype.toHtml = function() {
                if(BROWSER_INFO.type=='ie' && parseInt(BROWSER_INFO.ver)<=8){
                    return '[这是一条语音消息]demo暂不支持ie8(含)以下浏览器播放语音,语音URL:'+this.downUrl;
                }
		return '<audio src="'+this.downUrl+'" controls="controls" onplay="onChangePlayAudio(this)" preload="none"></audio>';
	};
        
        // class Msg.Elem.File
	Msg.Elem.File = function(uuid,name,size,senderId,downUrl) {
		this.uuid = uuid;//文件id
		this.name = name;//文件名
		this.size = size;//大小，单位：字节
		this.senderId = senderId;//发送者
                this.downUrl = downUrl;//下载地址
	};
        Msg.Elem.File.prototype.getUUID = function() {
                return this.uuid;
	};
        Msg.Elem.File.prototype.getName = function() {
                return this.name;     
	};
        Msg.Elem.File.prototype.getSize = function() {
                return this.size;     
	};
        Msg.Elem.File.prototype.getSenderId = function() {
                return this.senderId;     
	};
        Msg.Elem.File.prototype.getDownUrl = function() {
                return this.downUrl;     
	};
        Msg.Elem.File.prototype.toHtml = function() {
                var fileSize=Math.round(this.size/1024);
		return '<a href="'+this.downUrl+'" title="点击下载文件" ><i class="glyphicon glyphicon-file">&nbsp;'+this.name+'('+fileSize+'KB)</i></a>';
	};
        
        // class Msg.Elem.GroupTip 群提示消息对象
	Msg.Elem.GroupTip = function(opType,opUserId,groupId,groupName,userIdList) {
		this.opType = opType;//操作类型
		this.opUserId = opUserId;//操作者id
		this.groupId = groupId;//群id
		this.groupName = groupName;//群名称
                this.userIdList = userIdList ? userIdList : [];//被操作的用户id列表
                this.groupInfoList = [];//新的群资料信息，群资料变更时才有值
                this.memberInfoList = [];//新的群成员资料信息，群成员资料变更时才有值
	};
        Msg.Elem.GroupTip.prototype.addGroupInfo = function(groupInfo) {
                this.groupInfoList.push(groupInfo);   
	};
        Msg.Elem.GroupTip.prototype.addMemberInfo = function(memberInfo) {
                this.memberInfoList.push(memberInfo);    
	};
        Msg.Elem.GroupTip.prototype.getOpType = function() {
                return this.opType;
	};
        Msg.Elem.GroupTip.prototype.getOpUserId = function() {
                return this.opUserId;     
	};
        Msg.Elem.GroupTip.prototype.getGroupId = function() {
                return this.groupId;     
	};
        Msg.Elem.GroupTip.prototype.getGroupName = function() {
                return this.groupName;     
	};
        Msg.Elem.GroupTip.prototype.getUserIdList = function() {
                return this.userIdList;     
	};
        Msg.Elem.GroupTip.prototype.getGroupInfoList = function() {
                return this.groupInfoList;     
	};
        Msg.Elem.GroupTip.prototype.getMemberInfoList = function() {
                return this.memberInfoList;     
	};
        Msg.Elem.GroupTip.prototype.toHtml = function() {
                var text="[群提示消息]";
                var maxIndex=GROUP_TIP_MAX_USER_COUNT-1;
                switch (this.opType) {
                    case GROUP_TIP_TYPE.JOIN://加入群
                        text += this.opUserId + "邀请了";
                        for (var m in this.userIdList) {
                            text += this.userIdList[m] + ",";
                            if (this.userIdList.length > GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                                text += "等" + this.userIdList.length + "人";
                                break;
                            }
                        }
                        text += "加入该群";
                        break;
                    case GROUP_TIP_TYPE.QUIT://退出群
                        text += this.opUserId + "主动退出该群";
                        break;
                    case GROUP_TIP_TYPE.KICK://踢出群
                        text += this.opUserId + "将";
                        for (var m in this.userIdList) {
                            text += this.userIdList[m] + ",";
                            if (this.userIdList.length > GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                                text += "等" + this.userIdList.length + "人";
                                break;
                            }
                        }
                        text += "踢出该群";
                        break;
                    case GROUP_TIP_TYPE.SET_ADMIN://设置管理员
                        text += this.opUserId + "将";
                        for (var m in this.userIdList) {
                            text += this.userIdList[m] + ",";
                            if (this.userIdList.length > GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                                text += "等" + this.userIdList.length + "人";
                                break;
                            }
                        }
                        text += "设为管理员";
                        break;
                    case GROUP_TIP_TYPE.CANCEL_ADMIN://取消管理员
                        text += this.opUserId + "取消";
                        for (var m in this.userIdList) {
                            text += this.userIdList[m] + ",";
                            if (this.userIdList.length > GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                                text += "等" + this.userIdList.length + "人";
                                break;
                            }
                        }
                        text += "的管理员资格";
                        break;
        
                    case GROUP_TIP_TYPE.MODIFY_GROUP_INFO://群资料变更
                        text += this.opUserId + "修改了群资料：";
                        for (var m in this.groupInfoList) {
                            var type=this.groupInfoList[m].getType();
                            var value=this.groupInfoList[m].getValue();
                            switch(type){
                                case GROUP_TIP_MODIFY_GROUP_INFO_TYPE.FACE_URL:
                                    text += "群头像为" + value + "; ";
                                    break;
                                case GROUP_TIP_MODIFY_GROUP_INFO_TYPE.NAME:
                                    text += "群名称为" + value + "; ";
                                    break;
                                case GROUP_TIP_MODIFY_GROUP_INFO_TYPE.OWNER:
                                    text += "群主为" + value + "; ";
                                    break;
                                case GROUP_TIP_MODIFY_GROUP_INFO_TYPE.NOTIFICATION:
                                    text += "群公告为" + value + "; ";
                                    break;
                                case GROUP_TIP_MODIFY_GROUP_INFO_TYPE.INTRODUCTION:
                                    text += "群简介为" + value + "; ";
                                    break;
                                default:
                                    text += "未知信息为:type=" + type+",value="+value + "; ";
                                    break;
                            } 
                        }
                        break;
        
                    case GROUP_TIP_TYPE.MODIFY_MEMBER_INFO://群成员资料变更(禁言时间)
                        text += this.opUserId + "修改了群成员资料:";
                        for (var m in this.memberInfoList) {
                            var userId=this.memberInfoList[m].getUserId();
                            var shutupTime=this.memberInfoList[m].getShutupTime();
                            text += userId+": ";
                            if (shutupTime != null && shutupTime !== undefined) {
                                if (shutupTime == 0) {
                                    text += "取消禁言; ";
                                } else {
                                    text += "禁言" + shutupTime + "秒; ";
                                }
                            } else{
                                text += " shutupTime为空";
                            }
                            if (this.memberInfoList.length > GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                                text += "等" + this.memberInfoList.length + "人";
                                break;
                            }
                        }
                        break;
                    default:
                        text += "未知群提示消息类型：type="+this.opType;
                        break;
                }
                return text;
	};
        
        // class Msg.Elem.GroupTip.GroupInfo，变更的群资料信息对象
	Msg.Elem.GroupTip.GroupInfo = function(type,value) {
		this.type = type;//群资料信息类型
		this.value = value;//对应的值
	};
        Msg.Elem.GroupTip.GroupInfo.prototype.getType = function() {
                return this.type;     
	};
        Msg.Elem.GroupTip.GroupInfo.prototype.getValue = function() {
                return this.value;     
	};
        
        // class Msg.Elem.GroupTip.MemberInfo，变更的群成员信息对象
	Msg.Elem.GroupTip.MemberInfo = function(userId,shutupTime) {
		this.userId = userId;//群成员id
		this.shutupTime = shutupTime;//群成员被禁言时间，0表示取消禁言，大于0表示被禁言时长，单位：秒
	};
        Msg.Elem.GroupTip.MemberInfo.prototype.getUserId = function() {
                return this.userId;     
	};
        Msg.Elem.GroupTip.MemberInfo.prototype.getShutupTime = function() {
                return this.shutupTime;     
	};
        
        // 自定义消息类型 class Msg.Elem.Custom
	Msg.Elem.Custom = function(data,desc,ext) {
		this.data =data;//数据
                this.desc =desc;//描述
                this.ext =ext;//扩展字段
	};
        Msg.Elem.Custom.prototype.getData = function() {
                return	this.data;
	};
        Msg.Elem.Custom.prototype.getDesc = function() {
                return	this.desc;
	};
        Msg.Elem.Custom.prototype.getExt = function() {
                return	this.ext;
	};
        Msg.Elem.Custom.prototype.toHtml = function() {
                return	'data='+this.data+', desc='+this.desc+", ext="+this.ext;
	};
        
	// singleton object MsgStore
	var MsgStore = new function() {
		var sessMap = {};//跟所有用户或群的聊天记录MAP
		var sessTimeline = [];//按时间降序排列的会话列表
		var msgCache = {};//消息缓存，用于判重
                //C2C
		this.cookie = "";//上一次拉取新c2c消息的cookie
		this.syncFlag = 0;//上一次拉取新c2c消息的是否继续拉取标记
                
		var visitSess = function(visitor) {
			for (var i in sessMap) {
				visitor(sessMap[i]);
			}
		};
                //消息查重
		var checkDupMsg = function(msg) {
			var dup = false;
			var first_key = msg.sess._impl.skey;
			var second_key = msg.isSend + msg.seq+msg.random;
			var tempMsg = msgCache[first_key] && msgCache[first_key][second_key];
			if (tempMsg && Math.abs(msg.time - tempMsg.time) < 1800){//时间间隔小于30min
                            dup = true;
                            log.error("消息存在重复:first_key="+first_key+",second_key="+second_key+",消息时间:"+msg.time+",以重复消息时间："+tempMsg.time);
                            return dup;
                        }	
                        if(msgCache[first_key]){
                            msgCache[first_key][second_key] = {time: msg.time};
                        }else{
                            msgCache[first_key]={};
                            msgCache[first_key][second_key] = {time: msg.time};
                        }
			return dup;
		};
                
                this.sessMap=function(){
                    return sessMap;
                };
		this.sessCount = function() {
			return sessTimeline.length;
		};
		this.sessByTypeId = function(type, id) {
			var skey = Session.skey(type, id);
			if (skey === 'undefined' || skey == null) return null;
			return sessMap[skey];
		};
                this.delSessByTypeId = function(type, id) {
			var skey = Session.skey(type, id);
			if (skey === 'undefined' || skey == null) return false;
			if(sessMap[skey]){
                            delete sessMap[skey];
                            delete msgCache[skey];
                        }
                        return true;
		};
                this.resetCookieAndSyncFlag = function() {
			this.cookie = "";
                        this.syncFlag = 0;
		};
		
                //切换将当前会话的自动读取消息标志为isOn,重置其他会话的自动读取消息标志为false
		this.setAutoRead = function(selSess, isOn, isResetAll) {
			if (isResetAll)
				visitSess(function(s){s._impl.isAutoRead=false;});
			if (selSess) {
				selSess._impl.isAutoRead = isOn;//
				if (isOn) {//是否调用已读上报接口
                                    selSess._impl.unread = 0;
                                    
                                    if(selSess._impl.type==SESSION_TYPE.C2C){//私聊消息已读上报
                                        var tmpC2CMsgReadedItem=[];
                                        tmpC2CMsgReadedItem.push(new C2CMsgReadedItem(selSess._impl.id,selSess._impl.time));
                                        //调用C2C消息已读上报接口
                                        proto_c2CMsgReaded(MsgStore.cookie,
                                                      tmpC2CMsgReadedItem,
                                                      function(resp) {                                                    
                                                        log.info("c2CMsgReaded success");   
                                                      },
                                                      function(err) {
                                                        log.error("c2CMsgReaded failed:" + err.ErrorInfo);
                                                      });
                                    }else if(selSess._impl.type==SESSION_TYPE.GROUP){//群聊消息已读上报
                                        var tmpOpt={
                                            'GroupId':selSess._impl.id,
                                            'MsgReadedSeq':selSess._impl.curMaxMsgSeq
                                        };
                                        //调用group消息已读上报接口
                                        proto_groupMsgReaded(tmpOpt,
                                                      function(resp) {
                                                        log.info("groupMsgReaded success");                  
                                                      },
                                                      function(err) {
                                                        log.error("groupMsgReaded failed:" + err.ErrorInfo);
                                                      });
                                    }
                                    
                                }
			}
		};
                
                this.c2CMsgReaded=function(opts,cbOk,cbErr){
                    var tmpC2CMsgReadedItem=[];
                    tmpC2CMsgReadedItem.push(new C2CMsgReadedItem(opts.To_Account,opts.LastedMsgTime));
                    //调用C2C消息已读上报接口
                    proto_c2CMsgReaded(MsgStore.cookie,
                        tmpC2CMsgReadedItem,
                            function(resp) {  
                                if(cbOk){
                                    cbOk(resp);
                                }
                            },
                            function(err) {
                                if(cbErr){
                                    cbErr(err);
                                }
                                
                            });
                };
                
		this.addSession = function(sess) {
			sessMap[sess._impl.skey] = sess;
		};
		this.delSession = function(sess) {
			delete sessMap[sess._impl.skey];
		};
		this.addMsg = function(msg) {
			if (checkDupMsg(msg)) return false;
			var sess = msg.sess;
			if (!sessMap[sess._impl.skey]) this.addSession(sess);
			sess._impl_addMsg(msg);
			return true;
		};
		this.updateTimeline = function() {
			var arr = new Array;
			visitSess(function(sess){arr.push(sess);});
			arr.sort(function(a,b){return b.time-a.time;});
			sessTimeline = arr;
		};
	};
	// singleton object MsgManager
	var MsgManager = new function() {

                var onBigGroupMsgCallback=null;//大群消息回调
                
                var onGroupInfoChangeCallback = null;//群资料变化回调
                //收到新群系统消息回调列表
                var onGroupSystemNotifyCallbacks={
                    "1":null,//申请加群请求（只有管理员会收到）
                    "2":null,//申请加群被同意（只有申请人能够收到）
                    "3":null,//申请加群被拒绝（只有申请人能够收到）
                    "4":null,//被管理员踢出群(只有被踢者接收到)
                    "5":null,//群被解散(全员接收)
                    "6":null,//创建群(创建者接收)
                    "7":null,//邀请加群(被邀请者接收)
                    "8":null,//主动退群(主动退出者接收)
                    "9":null,//设置管理员(被设置者接收)
                    "10":null,//取消管理员(被取消者接收)
                    "11":null,//群已被回收(全员接收)
                    "255":null//用户自定义通知(默认全员接收)
                };
                
		var bigGroupLongPollingOn=false;//是否开启长轮询
                var bigGroupLongPollingStartSeq=0;//请求拉消息的起始seq(大群长轮询)
                var bigGroupLongPollingHoldTime=90;//客户端长轮询的超时时间，单位是秒(大群长轮询)
                var bigGroupLongPollingKey=null;//客户端加入群组后收到的的Key(大群长轮询)
                
                var bigGroupLongPollingMsgMap={};//记录收到的群消息数
                
                var ipList=[];//文件下载地址
                var authkey=null;//文件下载票据
                var expireTime=null;//票据超时时间
                
                //初始化文件下载ip和票据
                var initIpAndAuthkey=function(cbOk,cbErr){
                    proto_getIpAndAuthkey(function(resp){
                            ipList=resp.IpList;
                            authkey=resp.AuthKey;
                            expireTime=resp.ExpireTime;
                            if(cbOk) cbOk(resp);
                        },
                        function(err) {
				log.error("initIpAndAuthkey failed:" + err.ErrorInfo);
				if (cbErr) cbErr(err);
			}
                    );
                };
                
                //设置长轮询开关
                //isOn=true 开启
                //isOn=false 停止
                this.setBigGroupLongPollingOn=function(isOn){
                    bigGroupLongPollingOn=isOn;
                };
                //设置key
                this.setBigGroupLongPollingKey=function(key){
                    bigGroupLongPollingKey=key;
                };
                //重置长轮询变量
                this.resetBigGroupLongPollingInfo=function(){
                    bigGroupLongPollingOn=false;
                    bigGroupLongPollingStartSeq=0;
                    bigGroupLongPollingKey=null;
                    bigGroupLongPollingMsgMap={};
                };
                
                //重置
                this.clear=function(){
                    onBigGroupMsgCallback=null;
                    onGroupInfoChangeCallback=null;
                    onGroupSystemNotifyCallbacks={
                        "1":null,//申请加群请求（只有管理员会收到）
                        "2":null,//申请加群被同意（只有申请人能够收到）
                        "3":null,//申请加群被拒绝（只有申请人能够收到）
                        "4":null,//被管理员踢出群(只有被踢者接收到)
                        "5":null,//群被解散(全员接收)
                        "6":null,//创建群(创建者接收)
                        "7":null,//邀请加群(被邀请者接收)
                        "8":null,//主动退群(主动退出者接收)
                        "9":null,//设置管理员(被设置者接收)
                        "10":null,//取消管理员(被取消者接收)
                        "11":null,//群已被回收(全员接收)
                        "255":null//用户自定义通知(默认全员接收)
                    };
                    bigGroupLongPollingOn=false;
                    bigGroupLongPollingStartSeq=0;
                    bigGroupLongPollingKey=null;
                    bigGroupLongPollingMsgMap={};
                    ipList=[];//文件下载地址
                    authkey=null;//文件下载票据
                    expireTime=null;//票据超时时间
                };
                
                //设置群消息数据条数
                this.setBigGroupLongPollingMsgMap=function(groupId,count){
                    var bigGroupLongPollingMsgCount=bigGroupLongPollingMsgMap[groupId];
                    if(bigGroupLongPollingMsgCount){
                        bigGroupLongPollingMsgCount=parseInt(bigGroupLongPollingMsgCount)+count;
                        bigGroupLongPollingMsgMap[groupId]=bigGroupLongPollingMsgCount;
                    }else{
                        bigGroupLongPollingMsgMap[groupId]=count;
                    }
                };

                //大群 长轮询
                this.bigGroupLongPolling=function(cbOk,cbErr){
                    
                    var opts={
                        'StartSeq':bigGroupLongPollingStartSeq,//请求拉消息的起始seq
                        'HoldTime':bigGroupLongPollingHoldTime,//客户端长轮询的超时时间，单位是秒
                        'Key':bigGroupLongPollingKey//客户端加入群组后收到的的Key
                    };
                    
                    proto_bigGroupLongPolling(opts,function(resp) {
                        
                        var msgObjList = [];
                        bigGroupLongPollingStartSeq=resp.NextSeq;
                        bigGroupLongPollingHoldTime=resp.HoldTime;
                        bigGroupLongPollingKey=resp.Key;
                        
                        if(resp.RspMsgList && resp.RspMsgList.length>0){
                            var msgCount=0,msgInfo,event,msg;
                            for (var i=resp.RspMsgList.length-1;i>=0;i--) {
                                    msgInfo = resp.RspMsgList[i];
                                    //如果是已经删除的消息或者发送者帐号为空或者消息内容为空
                                    //IsPlaceMsg=1
                                    if(msgInfo.IsPlaceMsg || !msgInfo.From_Account || !msgInfo.MsgBody){
                                        continue;
                                    }
                                    
                                    event=msgInfo.Event;//群消息类型
                                    switch(event){
                                        case LONG_POLLINNG_EVENT_TYPE.GROUP_COMMON://群普通消息
                                            log.info("longpolling: return new group msg");
                                            msg=handlerGroupMsg(msgInfo,false,false);
                                            msg && msgObjList.push(msg);
                                            msgCount=msgCount+1;
                                            break;
                                        case LONG_POLLINNG_EVENT_TYPE.GROUP_TIP://群提示消息
                                        case LONG_POLLINNG_EVENT_TYPE.GROUP_TIP2://群提示消息
                                            log.info("longpolling: return new group tip");
                                            msg=handlerGroupMsg(msgInfo,false,false);
                                            msg && msgObjList.push(msg);
                                            //msgCount=msgCount+1;
                                            break;
                                        case LONG_POLLINNG_EVENT_TYPE.GROUP_SYSTEM://群系统消息
                                            log.info("longpolling: new group system msg");
                                            handlerGroupSystemMsg(msgInfo);
                                            break;
                                        default:
                                            log.error("longpolling收到未知新消息类型: Event="+event);
                                            break;
                                    }
                                    
                                    
                            } // for loop
                            if (msgCount>0){
                                MsgManager.setBigGroupLongPollingMsgMap(msgInfo.ToGroupId,msgCount);//
                                log.warn("current bigGroupLongPollingMsgMap: "+JSON.stringify(bigGroupLongPollingMsgMap));
                            }
                        }
                        curBigGroupLongPollingRetErrorCount=0;
                        //返回连接状态
                        var successInfo={
                            'ActionStatus':ACTION_STATUS.OK,
                            'ErrorCode':CONNECTION_STATUS.ON,
                            'ErrorInfo':'connection is ok...'
                        };
                        ConnManager.callBack(successInfo);
                        
                        if (cbOk) cbOk(msgObjList);
                        else if(onBigGroupMsgCallback) onBigGroupMsgCallback(msgObjList);//返回新消息
                        
                        //重新启动长轮询
                        bigGroupLongPollingOn && MsgManager.bigGroupLongPolling();
                        
                    }, function(err) {
                                
                                if(err.ErrorCode!=60008){
                                    //console.error(err.ErrorInfo);
                                    //记录长轮询返回错误次数
                                    curBigGroupLongPollingRetErrorCount++;
                                }
                                //累计超过一定次数，不再发起长轮询请求
                                if(curBigGroupLongPollingRetErrorCount<LONG_POLLING_MAX_RET_ERROR_COUNT){
                                    bigGroupLongPollingOn && MsgManager.bigGroupLongPolling();
                                }else{
                                    var errInfo={
                                        'ActionStatus':ACTION_STATUS.FAIL,
                                        'ErrorCode':CONNECTION_STATUS.OFF,
                                        'ErrorInfo':'connection is off'
                                    };
                                    ConnManager.callBack(errInfo);
                                }
				if (cbErr) cbErr(err);
                                
			},bigGroupLongPollingHoldTime*1000);
                };
                
               
                
                //处理群消息(普通消息+提示消息)
                //isSyncGroupMsgs 是否主动拉取群消息标志
                //isAddMsgFlag 是否需要保存到MsgStore，如果需要，这里会存在判重逻辑
                var handlerGroupMsg=function (msgInfo,isSyncGroupMsgs,isAddMsgFlag){
                                        var isSendMsg, fromAccount,headUrl='',fromAccountNick;
                                        var groupId=msgInfo.ToGroupId;
                                        var groupName=groupId;
                                        if(msgInfo.GroupInfo){//取出群名称
                                            if(msgInfo.GroupInfo.GroupName){
                                                groupName=msgInfo.GroupInfo.GroupName;
                                            }
                                        }
                                        
                                        //获取发送者帐号
                                        fromAccount=getFromAccount(msgInfo);
                                        
                                        //取出成员昵称
                                        fromAccountNick=fromAccount;
                                        if(msgInfo.GroupInfo){
                                            if(msgInfo.GroupInfo.From_AccountNick){
                                                fromAccountNick=msgInfo.GroupInfo.From_AccountNick;
                                            }
                                        }
					if (fromAccount == ctx.identifier) {//当前用户发送的消息
						isSendMsg = true;
					} else {//当前用户收到的消息
						isSendMsg = false;
					}
					var sess = MsgStore.sessByTypeId(SESSION_TYPE.GROUP, groupId);
					if (!sess) {
						sess = new Session(SESSION_TYPE.GROUP, groupId, groupName, headUrl, msgInfo.MsgTimeStamp,msgInfo.MsgSeq);
					}
                                        var subType=GROUP_MSG_SUB_TYPE.COMMON;//消息子类型
                                        //群提示消息,重新封装下
                                        if(LONG_POLLINNG_EVENT_TYPE.GROUP_TIP==msgInfo.Event || LONG_POLLINNG_EVENT_TYPE.GROUP_TIP2==msgInfo.Event){
                                            subType=GROUP_MSG_SUB_TYPE.TIP;//群提示消息
                                            var groupTip=msgInfo.MsgBody;
                                            msgInfo.MsgBody=[];
                                            msgInfo.MsgBody.push({
                                                    "MsgType":MSG_ELEMENT_TYPE.GROUP_TIP,
                                                    "MsgContent":groupTip
                                                }
                                            );
                                        }else if (msgInfo.MsgPriority){
                                            if (msgInfo.MsgPriority==GROUP_MSG_PRIORITY_TYPE.REDPACKET){
                                                //群红包消息
                                                subType=GROUP_MSG_SUB_TYPE.REDPACKET;
                                            }else if(msgInfo.MsgPriority==GROUP_MSG_PRIORITY_TYPE.LOVEMSG){
                                                //群点赞消息
                                                subType=GROUP_MSG_SUB_TYPE.LOVEMSG;
                                            }
                                        }
					var msg = new Msg(sess, isSendMsg, msgInfo.MsgSeq, msgInfo.MsgRandom,msgInfo.MsgTimeStamp,fromAccount,subType,fromAccountNick);                                       
                                        var msgBody=null;
                                        var msgContent=null;
                                        var msgType=null;
					for (var mi in msgInfo.MsgBody) {
                                                msgBody=msgInfo.MsgBody[mi];
                                                msgType=msgBody.MsgType;
                                                switch(msgType){
                                                    case MSG_ELEMENT_TYPE.TEXT:
                                                        msgContent=new Msg.Elem.Text(msgBody.MsgContent.Text);
                                                        break;
                                                    case MSG_ELEMENT_TYPE.FACE:                                                       
                                                         msgContent=new Msg.Elem.Face(
                                                                        msgBody.MsgContent.Index,
                                                                        msgBody.MsgContent.Data
                                                                    );
                                                        break;
                                                    case MSG_ELEMENT_TYPE.IMAGE:
                                                        msgContent=new Msg.Elem.Images(
                                                                        msgBody.MsgContent.UUID
                                                        );
                                                        for(var j in msgBody.MsgContent.ImageInfoArray){
                                                            msgContent.addImage(
                                                                new Msg.Elem.Images.Image(
                                                                    msgBody.MsgContent.ImageInfoArray[j].Type,
                                                                    msgBody.MsgContent.ImageInfoArray[j].Size,
                                                                    msgBody.MsgContent.ImageInfoArray[j].Width,
                                                                    msgBody.MsgContent.ImageInfoArray[j].Height,
                                                                    msgBody.MsgContent.ImageInfoArray[j].URL
                                                                )
                                                            );
                                                        }
                                                        break;
                                                    case MSG_ELEMENT_TYPE.SOUND:
                                                        
                                                        var soundUrl=getSoundDownUrl(msgBody.MsgContent.UUID,fromAccount);
                                                        if(soundUrl){
                                                            msgContent=new Msg.Elem.Sound(
                                                                        msgBody.MsgContent.UUID,
                                                                        msgBody.MsgContent.Second,
                                                                        msgBody.MsgContent.Size,
                                                                        msgInfo.From_Account,
                                                                        soundUrl
                                                                    );
                                                        }else{
                                                            msgType=MSG_ELEMENT_TYPE.TEXT;
                                                            msgContent=new Msg.Elem.Text('[语音消息]下载地址解析出错');
                                                        }
                                                        break;
                                                    case MSG_ELEMENT_TYPE.LOCATION:
                                                        msgType=MSG_ELEMENT_TYPE.TEXT;
                                                        msgContent=new Msg.Elem.Text('web端暂不支持地理位置消息');
                                                        break;
                                                    case MSG_ELEMENT_TYPE.FILE:
                                                        var fileUrl=getFileDownUrl(msgBody.MsgContent.UUID,fromAccount,msgBody.MsgContent.FileName);
                                                        if(fileUrl){
                                                            msgContent=new Msg.Elem.File(
                                                                        msgBody.MsgContent.UUID,
                                                                        msgBody.MsgContent.FileName,
                                                                        msgBody.MsgContent.FileSize,
                                                                        msgInfo.From_Account,
                                                                        fileUrl
                                                                    );
                                                        }else{
                                                            msgType=MSG_ELEMENT_TYPE.TEXT;
                                                            msgContent=new Msg.Elem.Text('[文件消息]地址解析出错');
                                                        }
                                                        break;
                                                    case MSG_ELEMENT_TYPE.GROUP_TIP:
                                                        var opType=msgBody.MsgContent.OpType;
                                                        if(!msgBody.MsgContent.Operator_Account){//收到没有登录态的用户进群或者退群消息
                                                            return null;
                                                        }
                                                        var operatorAccount=msgBody.MsgContent.Operator_Account;
                
                                                        //获取操作者帐号
                                                        if(msgBody.MsgContent.MsgOperatorMemberExtraInfo && msgBody.MsgContent.MsgOperatorMemberExtraInfo.UserId){
                                                            
                                                            operatorAccount=msgBody.MsgContent.MsgOperatorMemberExtraInfo.UserId;
                                                        }
                                                        
                                                        //获取操作涉及到的用户帐号
                                                        var listAcount=msgBody.MsgContent.List_Account;
                                                        var listAccountNew=[];
                                                        var msgMembers=msgBody.MsgContent.MsgMemberExtraInfo;
                                                        if(msgMembers){
                                                            
                                                            for(var tempIndex in listAcount){
                                                                if(listAcount[tempIndex]){
                                                                    if(msgMembers[tempIndex].UserId){
                                                                        listAccountNew.push(msgMembers[tempIndex].UserId);
                                                                    }else{
                                                                        listAccountNew.push(listAcount[tempIndex]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        msgContent=new Msg.Elem.GroupTip(
                                                                        opType,
                                                                        operatorAccount,
                                                                        groupId,
                                                                        msgInfo.GroupInfo.GroupName,
                                                                        listAccountNew
                                                                    );
                                                        if(GROUP_TIP_TYPE.MODIFY_GROUP_INFO==opType){//群资料变更
                                                            var tempIsCallbackFlag=false;
                                                            var tempNewGroupInfo={
                                                                "GroupId":groupId,
                                                                "GroupFaceUrl":null,
                                                                "GroupName":null,
                                                                "OwnerAccount":null,
                                                                "GroupNotification":null,
                                                                "GroupIntroduction":null
                                                            };
                                                            var msgGroupNewInfo=msgBody.MsgContent.MsgGroupNewInfo;
                                                            if(msgGroupNewInfo.GroupFaceUrl){
                                                                var tmpNGIFaceUrl=new Msg.Elem.GroupTip.GroupInfo(
                                                                                    GROUP_TIP_MODIFY_GROUP_INFO_TYPE.FACE_URL,
                                                                                    msgGroupNewInfo.GroupFaceUrl
                                                                                );
                                                                msgContent.addGroupInfo(tmpNGIFaceUrl);
                                                                tempIsCallbackFlag=true;
                                                                tempNewGroupInfo.GroupFaceUrl=msgGroupNewInfo.GroupFaceUrl;
                                                            }
                                                            if(msgGroupNewInfo.GroupName){
                                                                var tmpNGIName=new Msg.Elem.GroupTip.GroupInfo(
                                                                                    GROUP_TIP_MODIFY_GROUP_INFO_TYPE.NAME,
                                                                                    msgGroupNewInfo.GroupName
                                                                                );
                                                                msgContent.addGroupInfo(tmpNGIName);
                                                                tempIsCallbackFlag=true;
                                                                tempNewGroupInfo.GroupName=msgGroupNewInfo.GroupName;
                                                            }
                                                            if(msgGroupNewInfo.Owner_Account){
                                                                var tmpNGIOwner=new Msg.Elem.GroupTip.GroupInfo(
                                                                                    GROUP_TIP_MODIFY_GROUP_INFO_TYPE.OWNER,
                                                                                    msgGroupNewInfo.Owner_Account
                                                                                );
                                                                msgContent.addGroupInfo(tmpNGIOwner);
                                                                tempIsCallbackFlag=true;
                                                                tempNewGroupInfo.OwnerAccount=msgGroupNewInfo.Owner_Account;
                                                            }
                                                            if(msgGroupNewInfo.GroupNotification){
                                                                var tmpNGINotification=new Msg.Elem.GroupTip.GroupInfo(
                                                                                    GROUP_TIP_MODIFY_GROUP_INFO_TYPE.NOTIFICATION,
                                                                                    msgGroupNewInfo.GroupNotification
                                                                                );
                                                                msgContent.addGroupInfo(tmpNGINotification);
                                                                tempIsCallbackFlag=true;
                                                                tempNewGroupInfo.GroupNotification=msgGroupNewInfo.GroupNotification;
                                                            }
                                                            if(msgGroupNewInfo.GroupIntroduction){
                                                                var tmpNGIIntroduction=new Msg.Elem.GroupTip.GroupInfo(
                                                                                    GROUP_TIP_MODIFY_GROUP_INFO_TYPE.INTRODUCTION,
                                                                                    msgGroupNewInfo.GroupIntroduction
                                                                                );
                                                                msgContent.addGroupInfo(tmpNGIIntroduction);
                                                                tempIsCallbackFlag=true;
                                                                tempNewGroupInfo.GroupIntroduction=msgGroupNewInfo.GroupIntroduction;
                                                            }
                                                            
                                                            //长轮询返回的新提示消息，则回调群资料变化通知方法
                                                            if(isSyncGroupMsgs==false && tempIsCallbackFlag && onGroupInfoChangeCallback){
                                                                onGroupInfoChangeCallback(tempNewGroupInfo);
                                                            }
                                                            
                                                        }else if(GROUP_TIP_TYPE.MODIFY_MEMBER_INFO==opType){//群成员变更
                                                            var memberInfos=msgBody.MsgContent.MsgMemberInfo;
                                                            for(var n in memberInfos){
                                                                var memberInfo=memberInfos[n];
                                                                msgContent.addMemberInfo(
                                                                        new Msg.Elem.GroupTip.MemberInfo(
                                                                            memberInfo.User_Account,memberInfo.ShutupTime
                                                                        )
                                                                );
                                                            }
                                                        }
                                                        break;
                                                    case MSG_ELEMENT_TYPE.CUSTOM:
                                                        msgContent=new Msg.Elem.Custom(
                                                                        msgBody.MsgContent.Data,
                                                                        msgBody.MsgContent.Desc,
                                                                        msgBody.MsgContent.Ext
                                                                    );
                                                        
                                                        break;
                                                    default :
                                                        msgType=MSG_ELEMENT_TYPE.TEXT;
                                                        msgContent=new Msg.Elem.Text('web端暂不支持'+msgBody.MsgType+'消息');
                                                        break;
                                                }
						msg.elems.push(new Msg.Elem(msgType, msgContent));
					}
                                        
                                        
                                        if(isAddMsgFlag==false){//不需要保存消息
                                            return msg;
                                        }
                                        
					if (MsgStore.addMsg(msg)) {
                                                //更新我的群最大的消息seq
                                                /*var tempCurMaxSeq=myGroupMaxSeqs[groupId];
                                                if(tempCurMaxSeq){
                                                    if(msgInfo.MsgSeq>tempCurMaxSeq){
                                                        myGroupMaxSeqs[groupId]=msgInfo.MsgSeq;
                                                    }
                                                }else{
                                                    myGroupMaxSeqs[groupId]=msgInfo.MsgSeq;
                                                }*/
                                                return msg;
					}else{
                                            return null;
                                        }
                };
                
                //解析发送者帐号
                var getFromAccount=function (msgInfo){
                    var fromAccount=msgInfo.From_Account;
                    if(msgInfo.GroupInfo.MsgFrom_AccountExtraInfo && msgInfo.GroupInfo.MsgFrom_AccountExtraInfo.UserId){
                        fromAccount=msgInfo.GroupInfo.MsgFrom_AccountExtraInfo.UserId;
                    }
                    return fromAccount;
                };
                
                
                //处理新的群系统消息
                var handlerGroupSystemMsg=function(groupTip){
                                        var groupReportTypeMsg=groupTip.MsgBody;
                                        var reportType=groupReportTypeMsg.ReportType;
                                        var toAccount=groupTip.GroupInfo.To_Account;
                                        //过滤本不应该给自己的系统消息
                                        //if(!toAccount || toAccount!=ctx.identifier){
                                        //    log.error("收到本不应该给自己的系统消息: To_Account="+toAccount);
                                        //    continue;
                                        //}
                                        var notify={
                                                    "ReportType":reportType,
                                                    "GroupId":groupTip.ToGroupId,
                                                    "GroupName":groupTip.GroupInfo.GroupName,
                                                    "Operator_Account":groupReportTypeMsg.Operator_Account,
                                                    "MsgTime":groupTip.MsgTimeStamp
                                        };
                                        switch(reportType){
                                            case GROUP_SYSTEM_TYPE.JOIN_GROUP_REQUEST://申请加群(只有管理员会接收到)
                                                notify["RemarkInfo"]=groupReportTypeMsg.RemarkInfo;
                                                notify["MsgKey"]=groupReportTypeMsg.MsgKey;
                                                notify["Authentication"]=groupReportTypeMsg.Authentication;
                                                notify["UserDefinedField"]=groupTip.UserDefinedField;
                                                notify["From_Account"]=groupTip.From_Account;
                                                notify["MsgSeq"]=groupTip.ClientSeq;
                                                notify["MsgRandom"]=groupTip.MsgRandom;
                                                break;
                                            case GROUP_SYSTEM_TYPE.JOIN_GROUP_ACCEPT://申请加群被同意(只有申请人自己接收到)
                                            case GROUP_SYSTEM_TYPE.JOIN_GROUP_REFUSE://申请加群被拒绝(只有申请人自己接收到)
                                                notify["RemarkInfo"]=groupReportTypeMsg.RemarkInfo;
                                                break;
                                            case GROUP_SYSTEM_TYPE.KICK://被管理员踢出群(只有被踢者接收到)
                                            case GROUP_SYSTEM_TYPE.DESTORY://群被解散(全员接收)
                                            case GROUP_SYSTEM_TYPE.CREATE://创建群(创建者接收, 不展示)
                                            case GROUP_SYSTEM_TYPE.INVITED_JOIN_GROUP_REQUEST://邀请加群(被邀请者接收)
                                            case GROUP_SYSTEM_TYPE.QUIT://主动退群(主动退出者接收, 不展示)
                                            case GROUP_SYSTEM_TYPE.SET_ADMIN://群设置管理员(被设置者接收)
                                            case GROUP_SYSTEM_TYPE.CANCEL_ADMIN://取消管理员(被取消者接收)
                                            case GROUP_SYSTEM_TYPE.REVOKE://群已被回收(全员接收, 不展示)
                                                break;
                                            case GROUP_SYSTEM_TYPE.CUSTOM://用户自定义通知(默认全员接收)
                                                notify["UserDefinedField"]=groupReportTypeMsg.UserDefinedField;
                                                break;
                                            default:
                                                log.error("未知群系统消息类型：reportType="+reportType);
                                                break;
                                        }
                                        //回调
                                        if(onGroupSystemNotifyCallbacks[reportType]) onGroupSystemNotifyCallbacks[reportType](notify);
                                    
                };
                
                //发消息（私聊或群聊）
		this.sendMsg = function(msg, cbOk, cbErr) {
			proto_sendMsg(msg, function(resp) {
                                //私聊时，加入自己的发的消息，群聊时，由于seq和服务器的seq不一样，所以不作处理
                                if(msg.sess.type()==SESSION_TYPE.C2C){
                                    if (!MsgStore.addMsg(msg)) {                                   
					var errInfo = "sendMsg: local addMsg failed!";
                                        var error=tool.getReturnError(errInfo,-10);
					log.error(errInfo);
					if (cbErr) cbErr(error);
					return;
                                    }
                                    //更新信息流时间
                                    //MsgStore.updateTimeline();
                                }
				if (cbOk) cbOk(resp);
			}, function(err) {
				if (cbErr) cbErr(err);
			});
		};
                
                
                //初始化
		this.init = function(onBigGroupMsgNotify,onGroupSystemNotifys,onGroupInfoChangeNotify,cbOk,cbErr) {

                        if(!onBigGroupMsgNotify){
                            log.error('onBigGroupMsgNotify is empty');
                            if(cbErr){
                                var errInfo = "onBigGroupMsgNotify is empty";
                                var error=tool.getReturnError(errInfo,-11);
                                cbErr(error);
                            }
                            return; 
                        }
			onBigGroupMsgCallback = onBigGroupMsgNotify;
                        
                        if(onGroupSystemNotifys){
                            onGroupSystemNotifyCallbacks=onGroupSystemNotifys;
                        }else{
                            log.warn('onGroupSystemNotifys is empty');
                        }
                        if(onGroupInfoChangeNotify){
                            onGroupInfoChangeCallback=onGroupInfoChangeNotify;
                        }else{
                            log.warn('onGroupInfoChangeNotify is empty');
                        }
                        if(cbOk){
                                var success={
                                    'ActionStatus':ACTION_STATUS.OK,
                                    'ErrorCode':0,
                                    'ErrorInfo':"login success"
                                };
                                cbOk(success);
                        }
                        
                        
                        
		};

                //获取语音下载url
                var getSoundDownUrl=function(uuid,senderId){
                    var soundUrl=null;
                    if(authkey && ipList[0]){
                        soundUrl="http://"+ipList[0]+"/asn.com/stddownload_common_file?authkey="+authkey+"&bid="+DOWNLOAD_FILE.BUSSINESS_ID+"&subbid="+ctx.appIDAt3rd+"&fileid="+uuid+"&filetype="+DOWNLOAD_FILE.TYPE.SOUND+"&openid="+senderId+"&ver=0";
                    }else{
                        log.error("拼接语音下载url不报错：ip或者authkey为空");
                    }
                    return soundUrl;
                };
                
                //获取文件下载地址
                var getFileDownUrl=function(uuid,senderId,fileName){
                    var fileUrl=null;
                    if(authkey && ipList[0]){
                        fileUrl="http://"+ipList[0]+"/asn.com/stddownload_common_file?authkey="+authkey+"&bid="+DOWNLOAD_FILE.BUSSINESS_ID+"&subbid="+ctx.appIDAt3rd+"&fileid="+uuid+"&filetype="+DOWNLOAD_FILE.TYPE.FILE+"&openid="+senderId+"&ver=0&filename="+encodeURIComponent(fileName);
                    }else{
                        log.error("拼接文件下载url不报错：ip或者authkey为空");
                    }
                    return fileUrl;
                };
	};
        
        //WebBigGroupIM 基础对象
        //
        
        //常量对象
        
        //会话类型
        WebBigGroupIM.SESSION_TYPE=SESSION_TYPE;
        
        WebBigGroupIM.MSG_MAX_LENGTH=MSG_MAX_LENGTH;
        
        //c2c消息子类型
        WebBigGroupIM.C2C_MSG_SUB_TYPE=C2C_MSG_SUB_TYPE;
        
        //群消息子类型
        WebBigGroupIM.GROUP_MSG_SUB_TYPE=GROUP_MSG_SUB_TYPE;
        
        //消息元素类型
        WebBigGroupIM.MSG_ELEMENT_TYPE=MSG_ELEMENT_TYPE;
        
        //群提示消息类型
        WebBigGroupIM.GROUP_TIP_TYPE=GROUP_TIP_TYPE;
        
        //图片类型
        WebBigGroupIM.IMAGE_TYPE=IMAGE_TYPE;
        
        //群系统消息类型
        WebBigGroupIM.GROUP_SYSTEM_TYPE=GROUP_SYSTEM_TYPE;
        
        //群提示消息-群资料变更类型
        WebBigGroupIM.GROUP_TIP_MODIFY_GROUP_INFO_TYPE=GROUP_TIP_MODIFY_GROUP_INFO_TYPE;
        
        //表情对象
	WebBigGroupIM.Emotions= emotions;
        //表情标识符和index Map
	WebBigGroupIM.EmotionDataIndexs= emotionDataIndexs;
        
        //浏览器信息
        WebBigGroupIM.BROWSER_INFO=BROWSER_INFO;
        
        //腾讯登录服务错误码(托管模式)
        WebBigGroupIM.TLS_ERROR_CODE=TLS_ERROR_CODE;
        
        //连接状态
        WebBigGroupIM.CONNECTION_STATUS=CONNECTION_STATUS;
        
        /**************************************/
        
        //类对象
        
        //会话对象
        WebBigGroupIM.Session = Session;
        
        //消息对象
        WebBigGroupIM.Msg = Msg;
        
        //工具对象
        WebBigGroupIM.Tool = tool;
        
        //控制台日志打印对象
        WebBigGroupIM.Log=log;
        
        /**************************************/
        
        //API
        
        //登录
	WebBigGroupIM.login = function(loginInfo, listeners,opts,cbOk,cbErr) {
                
                //初始化连接状态回调函数
                ConnManager.init(listeners.onConnNotify,cbOk,cbErr);
                
                //设置ie9以下浏览器jsonp回调
                if(listeners.jsonpCallback) jsonpCallback=listeners.jsonpCallback;
                //登录
                _login(loginInfo,listeners,opts,cbOk,cbErr);
	};
        
        //登出
	WebBigGroupIM.logout = function(cbOk,cbErr) {return proto_logout(cbOk, cbErr);};
        
        //消息接口
        //发消息
        WebBigGroupIM.sendMsg=function(msg,cbOk, cbErr) {return MsgManager.sendMsg(msg,cbOk, cbErr);};
        
        //群组接口
        //进入大群
        WebBigGroupIM.applyJoinBigGroup = function(options,cbOk, cbErr) {return proto_applyJoinBigGroup(options,cbOk, cbErr);};
        
        //退出大群
        WebBigGroupIM.quitBigGroup = function(options,cbOk, cbErr) {return proto_quitBigGroup(options,cbOk, cbErr);};
        
        //兼容ie9（含）以下浏览器
        //设置jsonp返回的值
        WebBigGroupIM.setJsonpLastRspData=function(rspData){
            jsonpLastRspData=typeof (rspData) == "string" ? JSON.parse(rspData) : rspData;
        }; 
           
})(WebBigGroupIM);