"use strict";

//获取消息的通用方法：
/*
（1）GetUnReads: 获取未读消息的实体
(2) GetUnReadsCount:获取已读消息的数量
*/
if (typeof jQuery != "undefined") {
    var wxMessage = {
        api: "http://users.wangxiao.cn/Manage/MessageCenter.aspx",
        GetUnReads: function GetUnReads(SuccssCallback) {
            this.action("unRead", "", SuccssCallback);
        },
        GetUnReadsCount: function GetUnReadsCount(SuccssCallback) {
            this.action("unReadCount", "", SuccssCallback);
        },
        SetUnReadCount: function SetUnReadCount(id, callback) {
            $this = this;
            var c = 0;
            c = $this.GetUnReadsCount(function (d) {
                if (document.getElementById(id)) {
                    document.getElementById(id).innerHTML = d;
                } else {
                    throw new Error("未获取到" + id);
                }
            });
        },
        action: function action(_action, querystring, SuccessCallback, FailCallback) {
            $.getJSON(this.api + "?callback=?&action=" + _action + querystring, function (message) {
                if (SuccessCallback) {
                    if (!FailCallback) {
                        SuccessCallback(message);
                        return;
                    }
                }
            });
        }
    };
    //加载css样式
    $(document).ready(function () {
        var btag = false;
        $("link").each(function () {
            if ($(this).attr("href").toString().indexOf("cart.css") >= 0) {
                btag = true;
            }
        });
        if (!btag) {
            $($("head").get(0)).append("<link href=\"http://static.wangxiao.cn/classcenter/css/cart.css\" rel=\"stylesheet\" type=\"text/css\" />");
        }
    });
};