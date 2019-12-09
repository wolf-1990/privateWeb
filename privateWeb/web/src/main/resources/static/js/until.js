'use strict';

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

/**
 * ajaxParams
 *  {
    type:'',
    url:'',
    async:true,
    dataType:'json',
    data:{},
    beforeSend:function(xhr){},
    complete:function(chr,Ts){},
    success:function(){}
    }
 */
var wx = {
    requireUrl: '/api',
    REGULAR_EXPRESSION: {
        phone: /^1\d{10}$/
    },
    ajax: function ajax(ajaxOption) {
        $.ajax({
            type: ajaxOption.type,
            url: this.requireUrl + ajaxOption.url,
            async: ajaxOption.async ? ajaxOption.async : false,
            dataType: 'json',
            data: ajaxOption.data ? JSON.stringify(ajaxOption.data) : '',
            beforeSend: function beforeSend(xhr) {
                typeof ajaxOption.beforeSend === 'function' ? ajaxOption.beforeSend(xhr) : '';
            },
            complete: function complete(chr, Ts) {
                typeof ajaxOption.complete === 'function' ? ajaxOption.complete(chr, Ts) : '';
            },
            success: function success(response) {
                typeof ajaxOption.success === 'function' ? ajaxOption.success(response) : '';
            }
        });
    }
    /**
     * 模拟Set集合
     */
};function Set() {
    this.data = {};
    this.length = 0;
}
Set.prototype.has = function (item) {
    return typeof this.data[item] !== "undefined";
};
Set.prototype.add = function (item) {
    if (!this.has(item)) {
        this.data[item] = [];
        this.length++;
    }
};
Set.prototype.remove = function (item) {
    if (!this.has(item)) {
        delete this.data[item];
        this.length--;
    }
};
Set.prototype.size = function (item) {
    return this.length;
};

/**
 * 数组isArray方法扩展
 */
if (!Array.isArray) {
    Array.isArray = function (arg) {
        return Object.prototype.toString.call(arg) === '[object Array]';
    };
}

function toUppercase(index) {
    var str = ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二', '十三', '十四', '十五', '十六', '十七', '十八', '十九', '二十', '二十一'];
    return str[index];
}

/*
    延时执行 节流 防止快速重复执行，只有在wait毫秒后不再此触发才执行。
    @param fn function
    @param wait number
    @return function
*/
function debounce(func, wait, immediate) {
    var timeout;
    return function () {
        var context = this,
            args = arguments;
        var later = function later() {
            timeout = null;
            if (!immediate) func.apply(context, args);
        };
        var callNow = immediate && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
        if (callNow) func.apply(context, args);
    };
};
/*
    节流函数
    @param fn function
    @param wait number
    @param maxTimeLong number
    @return function
*/
function throttle(func, wait, mustRun) {
    var timeout,
        startTime = new Date();

    return function () {
        var context = this,
            args = arguments,
            curTime = new Date();

        clearTimeout(timeout);
        timeout = null;
        // 如果达到了规定的触发时间间隔，触发 handler
        if (curTime - startTime >= mustRun) {
            func.apply(context, args);
            startTime = curTime;
            // 没达到触发间隔，重新设定定时器
        } else {
            timeout = setTimeout(func, wait);
        }
    };
};
//获取url参数
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
function zdAjax(options) {
    $.ajax({
        type: options.type,
        url: '/' + options.url,
        data: options.data,
        contentType: "application/json; charset=utf-8",
        dataType: 'JSON',
        success: function success(res) {
            if (typeof options.success == 'function') {
                options.success(res);
            }
        },
        error: function error(res) {
            if (typeof options.error == 'function') {
                options.error(res);
            }
        }
    });
}
function setCookie(name, value, lostTime, path) {
    //设置Cookie
    //首先判断用户传入参数的个数，至少传入2个参数
    if (arguments.length == 2) {
        document.cookie = name + "=" + value; //直接设置
    } else if (arguments.length == 3) {
        var ifLp = _typeof(arguments[2]); //需要判断用户输入的第三个参数类型
        if (ifLp == "number") {
            var Cookietime = new Date();
            Cookietime.setDate(Cookietime.getDate() + lostTime);
            document.cookie = name + '=' + value + ';expires=' + Cookietime; //设置自定义时间的Cookie
        } else {
            document.cookie = name + "=" + value + ";path=" + arguments[2]; //设置自定义地址的Cookie
        }
    } else {
        var Cookietime = new Date();
        Cookietime.setDate(Cookietime.getDate() + lostTime);
        document.cookie = name + '=' + value + ';expires=' + Cookietime + ";path=" + path; //设置全部自定义属性的Cookie
    }
}
function getCookie(name) {
    //获取Cookie
    var arr,
        reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) return unescape(arr[2]);else return null;
}
function removeCookie(name, path) {
    //删除cookie
    var that = this;
    if (path) {
        that.setCookie(name, 1, -1, path);
    } else {
        that.setCookie(name, 1, -1);
    }
}