//文本消息 表情消息 文本加表情消息
var sendMessageObj={
    variableObj:{
        expArrAll:["[礼物]","[愤怒]","[鄙视]","[伤心]", "[再见]","[高兴]","[流汗]","[无聊]","[疑问]","[你好]","[反对]","[赞同]","[鼓掌]","[太快了]","[太慢了]","[值得思考]","[凋谢]","[鲜花]"],
        expArrfilter:[], 
        matArr:[],  
        ajaxaccounts:[],
        msg:"",
        isZJ:window.location.href&&window.location.href.toLocaleLowerCase().indexOf("/api/")>0
    },
    sendMessagefn:function(sendmessage){
        this.variableObj.msg=sendmessage;
        this.matchfn(sendmessage);
    },
    matchfn:function(sendmessage){
        var that=this;
        //匹配表情 []格式
        that.variableObj.matArr=sendmessage.match(/\[[\u4e00-\u9fa5]+\]/g);
        if(!that.variableObj.matArr){//没有匹配到[]---全部是普通消息 
            //普通消息
            that.variableObj.ajaxaccounts=[];
            that.pushmessage("TIMTextElem",sendmessage);
            that.sendAjax();
            return;
        }
        that.filterEmail(sendmessage);

    }, 
    //过滤匹配出来的表情[]
    filterEmail:function(sendmessage){
        var that=this;
        that.variableObj.expArrfilter=that.variableObj.matArr.filter(function(item){
            return that.variableObj.expArrAll.join("").indexOf(item)>-1;
        })
        if(that.variableObj.expArrfilter.length==0){//没有匹配到[表情]----全部是普通消息 
            //普通消息 
            that.variableObj.ajaxaccounts=[];
            that.pushmessage("TIMTextElem",sendmessage)
            that.sendAjax();
            return;
        }
        that.emailMessage(sendmessage);
    },
    //包含表情
    emailMessage:function(sendmessage){
        var that=this,m_list=[],tempstr="";
        var tempmessage=sendmessage;
        var tempIndex=0;
         //消息中存在的表情数组
        var expArrfilter=that.variableObj.expArrfilter;
        that.variableObj.ajaxaccounts=[];
        expArrfilter.forEach(function(item){
            tempstr=tempmessage.substring(0,tempmessage.indexOf(item));
            if(tempmessage.indexOf(item)>0)	that.pushmessage("TIMTextElem",tempstr);//普通消息
                that.pushmessage("TIMFaceElem",item);//表情消息
                tempIndex=tempmessage.indexOf(item)+item.length;
                tempmessage=tempmessage.substr(tempIndex);
        });
        that.sendAjax();
        return;
    },
    //发送消息数组 普通消息TIMTextElem 表情消息TIMFaceElem
    pushmessage:function(msgType,msg){
        var that=this;
        that.variableObj.ajaxaccounts.push({
            data: "",
            msgType: msgType,
            text: msg
        })
       
    },
    // 发送消息接口
    sendAjax:function(){
        var that=this,
        ajaxDataObj={
            accounts: that.variableObj.ajaxaccounts,
            fromAccount: loginInfo.identifier,
            fromAccountNick:loginInfo.identifierNick,
            groupId: avChatRoomId
          }
        RC.ajax('post', 'liveChat/sendGroupMsg', true, ajaxDataObj, function (data) {
            if (data.code == '000000') {
                $("#send_msg_text,#edit").val('');
                
                // if (!selSess) {
                //     selSess = new webim.Session(selType, selToID, selToID, selSessHeadUrl, Math.round(new Date().getTime() / 1000));
                // }
                // var isSend = true; //是否为自己发送
                // var seq = -1; //消息序列，-1表示sdk自动生成，用于去重
                // var random = Math.round(Math.random() * 4294967296); //消息随机数，用于去重
                // var msgTime = Math.round(new Date().getTime() / 1000); //消息时间戳
                // var subType; //消息子类型
                // if (selType == webim.SESSION_TYPE.GROUP) {
                //     //群消息子类型如下：
                //     //webim.GROUP_MSG_SUB_TYPE.COMMON-普通消息,
                //     //webim.GROUP_MSG_SUB_TYPE.LOVEMSG-点赞消息，优先级最低
                //     //webim.GROUP_MSG_SUB_TYPE.TIP-提示消息(不支持发送，用于区分群消息子类型)，
                //     //webim.GROUP_MSG_SUB_TYPE.REDPACKET-红包消息，优先级最高
                //     subType = webim.GROUP_MSG_SUB_TYPE.COMMON;
            
                // } else {
                //     //C2C消息子类型如下：
                //     //webim.C2C_MSG_SUB_TYPE.COMMON-普通消息,
                //     subType = webim.C2C_MSG_SUB_TYPE.COMMON;
                // }
                //  var msg = new webim.Msg(selSess, isSend, seq, random, msgTime, loginInfo.identifier, subType, loginInfo.identifierNick);
                 
                 //showMsg(msg)
                //that.msgtodom(that.variableObj.msg,"",2,true)
            } else {
            }
        })
    },
    msgtodom:function(mes, name, level, isSelfSend){
        //处理的消息放入dom
        
            //判断dom样式
            //stu判断图标不同
            var stu = " stu";
            //textmes不同用户名的颜色
            var textmes = " other";
            //level为老师
            if (level == 1) { stu = " tea"; textmes = " tea" }
            if (isSelfSend) {
                textmes = " my"; 
            }
            var li = "<li class='chat_list'><span class=\"userimg " + stu + "\"></span><span class=\"loginuser_name" + textmes + "\">" + name + ":</span><span>" + mes + "</span></li>";
            //系统消息
            if (level == 3) {
                li = "<li class=\"chat_list\" style=\"text-align: center;\"><span class=\"system-class\">" + mes + "</span></li>";
            }
            $("#chat").append(li);
            $('#sucai').animate({ scrollTop: $("#chat").height() }, 50)
        },
    sendAjaxSystemNotification:function(message){
        var _data={content:message,groupId:avChatRoomId};
        RC.ajax('post', 'liveChat/sendGroupSystemNotification', true,_data , function (data) {
            if (data.code == '000000') {
                console.log("系统消息发送成功！");
            }
        });
    },
    /**
     * 发送普通消息 助教、用户同时使用
     * @param message
     */
    sendMess:function(message){
        var that=this;
        var level=that.variableObj.isZJ?1:0;
        that.variableObj.ajaxaccounts=[];
        that.pushmessage("TIMTextElem",JSON.stringify({name:loginInfo.identifierNick,content:message,level:level}));
            var ajaxDataObj={
                accounts: that.variableObj.ajaxaccounts,
                fromAccount: loginInfo.identifier,
                fromAccountNick:loginInfo.identifierNick,
                groupId: avChatRoomId
            }
            var requestUrl="liveChat/sendGroupMsg";
            if(that.variableObj.isZJ){
                requestUrl="api/"+requestUrl;
            }
        RC.ajax('post', requestUrl, true, ajaxDataObj, function (data) {
            if (data.code == '000000') {
                $("#send_msg_text,#edit").val('');
                console.log("消息发送成功");
            }
        })
    }
    

}