"use strict";

var common = {
    init: function init(data) {
        this.oldBrowser(); //i9以下提示
        this.floatingTop();
        this.floatWindow();
        this.news(); //新闻滚屏
        this.userNameDown();
        this.serachCondition();
        this.menuDown();
        this.userRegister(); //登录
        this.getMessage();
        //登录
        //this.loginFn();
        this.inputFn();
        $(".no-open").on("click", function () {
            layer.msg('此功能暂未开放，等待老师通知', { icon: 5, anim: 6 });
        });
        this.downmaterials();
    },
    clickObj: {
        clickTag: true,
        clickphoneTag: true
    },
    //下载资料
    downmaterials: function downmaterials() {
        var that = this;
        $(".materials-item").on('click', function () {
            var dataId = $(this).attr("data-id");
            // 请求接口获取资料列表数据
            var listHtml = "";
            RC.ajax('post', 'user/study/lesson/queryResourceList', true, { id: dataId }, function (data) {
                if (data.code == '000000') {
                    if (data.data.length == 0) {
                        layer.msg('暂无讲义', { icon: 5, anim: 6 });
                        return;
                    }
                    data.data.forEach(function (item, i) {
                        listHtml += '<li class="list-item">\
                                        <span class="list-1 tc">' + (i + 1) + '</span>\
                                        <span class="list-2 over-circle">' + item.fileName + '</span>\
                                        <a href="' + item.url + '" class="list-3 tc down-btn">点击下载</a>\
                                    </li>';
                    });
                    if ($("#shadow-materials").length > 0) {
                        $("#shadow-materials").remove();
                        $("body").append(that.materialsHtml(listHtml));
                    } else {
                        $("body").append(that.materialsHtml(listHtml));
                    }
                    $("html,body").addClass("no-scroll");
                    //弹框操作
                    $("#close-materials,#shadow-materials").on('click', function () {
                        $("#shadow-materials").hide();
                        $(".materials-item").parent("li").removeClass("active");
                        $("html,body").removeClass("no-scroll");
                    });
                }
            });
        });
    },
    //资料弹框
    materialsHtml: function materialsHtml(listHtml) {
        var str = '<div class="shadow-message evalut-box materials-box" id="shadow-materials" style="display:block;">\
		<div class="ver-middle course-ware-box">\
			<div class="clearfix evalut-box-title">\
				<span class="fl box-title">资料下载</span>\
				<span class="rt close-box" id="close-materials">\
					X\
				</span>\
			</div>\
			<div class="materials-bottom">\
				<ul>\
				<li class="list-item list-title">\
				<span class="list-1 tc">序号</span>\
				<span class="list-2">资料名称</span>\
				<span class="list-3 tc">操作</span>\
			</li>' + listHtml + '</ul></div></div></div>';
        return str;
    },
    //input的操作
    inputFn: function inputFn() {
        $(document).on("focus", ".userRegister input", function (e) {
            if ($(this).val().indexOf("请输入") > -1) {
                $(this).val("");
            }
        });
        $(document).on("blur", ".userRegister input", function (e) {
            console.log("失去焦点");
            if ($(this).val().trim() == "") {
                $(this).val($(this).attr("data-value"));
            }
        });
    },
    //登录
    userRegister: function userRegister() {
        //用户登录
        var _this = this;
        //_this.addCookieFile();//引入文件
        $('#user_register').on('click', function () {
            _this.loginFn();
        });
    },
    //显示登录弹框
    loginFn: function loginFn() {
        var that = this,
            form = layui.form;
        if (window.location.href.indexOf("login") == -1) {
            layer.open({
                type: 1,
                content: userRegisterHtml,
                area: ['480px', '555px'], //555px
                title: false,
                resize: false,
                success: function success() {
                    $('.userAccount input,.userPwd input').focus(function () {
                        $(this).parent().css('border-color', '#e8482d');
                    }).blur(function () {
                        $(this).parent().css('border-color', '#e5e5e5');
                    });
                }
            });
        }
        form.render();
        //用户名登录
        that.loginAbout();
        //手机号登录
        that.phoneLogin();
        //显示用户名密码
        that.loginShowPass();
        // 待删shn 
        $(".no-open").on("click", function () {
            layer.msg('此功能暂未开放，等待老师通知', { icon: 5, anim: 6 });
        });
    },
    //引入cookie.js文件
    addCookieFile: function addCookieFile() {
        var new_element = document.createElement("script");
        new_element.setAttribute("type", "text/javascript");
        new_element.setAttribute("src", "static/js/cookie.js");
        document.body.appendChild(new_element);
    },

    //手机号登录
    phoneLogin: function phoneLogin() {
        //回车
        var that = this;
        $(".userregister-box").on('click', function () {
            if ($(this).is(".active")) {
                $(this).removeClass("active");
            } else {
                $(this).addClass("active");
            }
        });
        $("#phoneLoginBtn").on('click', function () {
            if (that.clickObj.clickphoneTag) {
                that.loginPhoneBtn($(this).parent().siblings(".content-list"), $(this).parent().siblings(".content-list").find("#_sendMessagesPhone-box"));
                return false;
            }
        });
    },
    //手机快捷登录
    loginPhoneBtn: function loginPhoneBtn(o, phoneN) {
        var that = this,
            _sendMessagesPhone = phoneN,
            verificationCode = $(o).find("#login_verificationCode"),
            login_verificationCode = verificationCode.val(),
            flag = validatemobilePhone(_sendMessagesPhone); //验证手机号
        if (!flag) {
            return;
        }
        if (login_verificationCode == "请输入验证码" || !login_verificationCode) {
            verificationCode.addClass("active");
            layer.msg('请输入验证码', { icon: 5, anim: 6 });
            return;
        }
        verificationCode.removeClass("active");
        if (!$(o).find(".userregister-box").is(".active")) {
            layer.msg('接收并同意协议', { icon: 5, anim: 6 });
            return;
        }
        var ajaxData = {
            phone: $("#_sendMessagesPhone-box").val(),
            verifyCode: $("#login_verificationCode").val(),
            loginType: 1 //1手机快捷登录
        };
        that.loginUserAjax({params:common.getRSAData(ajaxData)});
    },
    //点击登录按钮--回车按钮
    loginAbout: function loginAbout(ajaxData) {
        //回车
        var that = this;
        $("#login_password_hidden,#login_userName").on('keyup', function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) {
                that.loginBtnFn(ajaxData);
            }
            return false;
        });
        $("#loginBtnUserName").on('click', function () {
            if (that.clickObj.clickTag) {
                that.loginBtnFn(ajaxData);
                return false;
            }
        });
    },
    //用户名登录
    loginBtnFn: function loginBtnFn() {
        var that = this,
            login_userName = $("#login_userName").val(),
            login_password = $("#login_password_hidden").val();
        if (!login_userName) {
            $("#login_userName").addClass("active");
            layer.msg('用户名不能为空', { icon: 5, anim: 6 });
            return;
        }
        $("#login_userName").removeClass("active");
        if (login_password == "请输入密码") {
            $("#login_password_hidden").addClass("active");
            layer.msg('请输入密码', { icon: 5, anim: 6 });
            return;
        }

        if (!login_password) {
            $("#login_password_hidden").addClass("active");
            layer.msg('请输入密码', { icon: 5, anim: 6 });
            return;
        }
        $("#login_password_hidden").removeClass("active");

        var ajaxData = {
            userName: $("#login_userName").val(),
            password: $("#login_password").val(),
            loginType: 0 //0用户名密码登录
        };
        // common.getRSAData(ajaxData)
        that.loginUserAjax({params:common.getRSAData(ajaxData)});
    },
    //判断是否默认显示用户名密码
    loginShowPass: function loginShowPass() {
        $("#login_password_hidden").on('input propertychange', function () {
            $("#login_password").val($(this).val());
        });
        var cookie = getCookie("userObj");
        if (cookie) {
            $("#login_userName").val(JSON.parse(cookie).userName);
            $("#login_password_hidden").val("sdfghj");
            $("#login_password").val(JSON.parse(cookie).password);
        }
    },
    //登录成功--记住密码
    rememberPass: function rememberPass(ajaxData) {
        if ($(".layui-form-checkbox").is(".layui-form-checked")) {
            setCookie("userObj", JSON.stringify(ajaxData), 7);
        } else {
            if (getCookie("userObj")) {
                delCookie("userObj");
            }
        }
    },
    // 登录接口
    loginUserAjax: function loginUserAjax(ajaxData) {
        var that = this;
        RC.ajax('post', '/login/login', true, ajaxData, function (data) {
            if (data.code == '000000') {
                that.clickObj.clickTag = false;
                layer.msg('登录成功！', { icon: 6, anim: 4 });
                layer.closeAll('page');
                that.rememberPass({
                    userName: $('#login_userName').val(),
                    password: $('#login_password').val()
                });
                $('.loginOrExit').css('visibility', 'hidden');
                $('.nav_user').show();
                $('.showUserName').text(data.data.message);
                setTimeout(function () {
                    if (window.location.href.indexOf("login") == -1) {
                        if (window.location.href.indexOf("goRegistryPage") == -1) {
                            location.reload();
                        } else {
                            var loginurl = getCookie("loginUrl");
                            if (loginurl != null && loginurl != '' && loginurl != 'null') {
                                delCookie("loginUrl");
                                window.location.href = loginurl;
                            } else {
                                window.location.href = "/";
                            }
                        }
                    } else {
                        //拦截器被拦截后---当前url存入cookie
                        var loginurl = getCookie("loginUrl");
                        if (loginurl != null && loginurl != '' && loginurl != 'null') {
                            delCookie("loginUrl");
                            window.location.href = loginurl;
                        } else {
                            window.location.href = "/";
                        }
                    }
                }, 1000);
            } else {
                that.clickObj.clickTag = true;
                layer.msg(data.message, { icon: 5, anim: 6 });
            }
        });
    },
    // 判断图形验证码
    parmImgcode: function parmImgcode(obj) {
        var that = this;
        // 判断是否为空
        var imgCode = $(obj).parents(".user-phone").siblings(".content-list").find("input[name='imgcode']");
        if (imgCode.length > 0 && imgCode.val().trim() == "") {
            $(imgCode).addClass("active");
            layer.msg('图形验证码不能为空！', { icon: 5, anim: 6 });
            return;
        }
        $(imgCode).removeClass("active");
        var ajaxData = {
            imgCode: $(imgCode).val()
        };
        //判断图形验证码是否正确
        RC.ajax('post', 'login/validatedImgCode', true, ajaxData, function (data) {
            if (data.code == '000000') {
                that.getMessagefn(obj);
            } else {
                layer.msg(data.message, { icon: 5, anim: 6 });
            }
        });
    },
    //获取短信验证码
    getMessage: function getMessage() {
        var that = this;
        $(document).on('click', "#send-message", function () {
            if ($("#send-message").parent(".verification-code").length == 1) {
                var phoneNum = $(this).parent(".user-phone").siblings().find("#_sendMessagesPhone");
                that.getPhone(phoneNum.val(), $(this));
            } else if (typeof bizType !== "undefined" && bizType == 1) {
                var phone = $(this).parents(".user-phone").siblings().find("input[name='userPhone']");
                var phoneFalg = validatemobilePhone(phone);
                if (phoneFalg) {
                    // that.parmImgcode($(this));
                }
            } else {
                //手机快捷登录--直接发送短信
                that.getMessagefn($(this));
            }
        });
    },
    //判断手机号是否被注册/register/getPhone
    getPhone: function getPhone(phoneNum, o) {
        var that = this;
        RC.ajax('get', 'register/getPhone?phone=' + phoneNum, true, "", function (data) {
            if (data.code == '000000') {
                that.parmImgcode(o);
            } else {
                layer.msg(data.message, { icon: 5, anim: 6 });
            }
        });
    },
    getMessagefn: function getMessagefn(obj) {
        var that = this;
        var time = 60;
        var phone = $(obj).parents(".user-phone").siblings().find("input[name='userPhone']");
        var phoneFalg = validatemobilePhone(phone);
        if (phoneFalg) {
            if(!$(this).is(".sending")){
                $(this).addClass("sending");
                sendMessages(phone.val(), time, obj);
            }

        } else {
        }
    },
    getRSAData:function(data) {
        var encrypt = new JSEncrypt();
        var pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOug3idE//45a68XelymNFF6BfR0k5E4mMoHgkjGChntpkxMz9s7wCojlEl+NgX5fB2uY5WU49xXXXqRZeoquth9YXQNEmmUH9C4FzQErFEALyCAQFJqSds5L4YKoGPdO9xEnX5biMgtoShNT9AsWNKY+NCOTlUUeoo4eodzemOQIDAQAB";
        encrypt.setPublicKey(pubKey);
        var _data = typeof data == 'string' ? data : JSON.stringify(data);
        var _pubLoginDetail = encrypt.encrypt(_data);
        return _pubLoginDetail
    },
    //获取地址栏参数值
    getUrlParms: function getUrlParms(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    },
    //下拉菜单
    menuDown: function menuDown() {
        $(".nav_left").hover(function () {
            $(this).find(".nav-menu-down").stop().slideToggle();
        }, function () {
            $(this).find(".nav-menu-down").stop().slideToggle();
        });

        $("#more-menu").mouseover(function () {
            $(".more-menu-list>ul>li").show();
            $(".nav-menu-down").addClass("height-auto");
            $(".more-menu-list>ul>li.more-menu").hide();
        }).mouseout(function () {
            $(".more-menu-list>ul>li").hide();
            $(".nav-menu-down").removeClass("height-auto");
            $(".more-menu-list>ul>li.more-menu").show();
        });
    },
    userNameDown: function userNameDown() {
        $(".user-name").hover(function () {
            $(this).find(".userCenter-box").stop().toggle();
        });
    },
    changeCourseIcon: function changeCourseIcon(icoStatus) {
        if (icoStatus.is(".course-down-ico")) {
            icoStatus.removeClass("course-down-ico").addClass("course-up-ico");
        } else {
            icoStatus.addClass("course-down-ico").removeClass("course-up-ico");
        }
    },
    serachCondition: function serachCondition() {
        var that = this;
        $("#search-text").focus(function (e) {
            if ($(this).val().indexOf("请输入") > -1) {
                $(this).val("");
            }
        });
        $("#course-menu-box").on('click', function () {
            var icoStatus = $(this).find(".bg-ico");
            that.changeCourseIcon(icoStatus);
            $(this).siblings(".userCenter-box").toggle();
        });
        $("#search-menu .search-item").on('click', function () {
            var icoStatus = $("#course-menu-box .bg-ico");
            currDataId = $(this).attr("data-id"), currText = $(this).text();
            //      		$("#course-btn").attr("data-id",currDataId);
            $("#search-content").val(currDataId);
            $("#course-btn").text(currText);
            that.changeCourseIcon(icoStatus);
            $("#search-menu").hide();
            $("#search-text").val("请输入" + currText + '名称');
        });
        $("#search-text").on('keyup', function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) {
                $('#search-jump').click();
            }
        });
    },
    oldBrowser: function oldBrowser() {
        if (navigator.userAgent.indexOf("MSIE") > 0 && sessionStorage && !sessionStorage.getItem('oldIE')) {
            if (navigator.userAgent.match(/MSIE 8.0|MSIE 7.0|MSIE 5.0/)) {
                setTimeout(function () {
                    $('body').append('<div class="oldIE"><span>当前的浏览器版本过低，部分功能可能会受影响，请尽快升级哦</span><span class="noshowIE">不再提示</span></div>');
                    $('.noshowIE').on('click', function () {
                        $('.oldIE').fadeOut();
                        sessionStorage.setItem('oldIE', 'true');
                    });
                }, 1000);
            }
        }
    },

    floatingTop: function floatingTop() {
        //回顶部
        var tag = true;

        $(window).on('scroll', function () {
            if (tag) {
                tag = false;
                var topTime = setTimeout(function () {
                    if ($(this).scrollTop() > 100) {
                        $('.floating_window_top').fadeIn();
                    } else {
                        $('.floating_window_top').fadeOut();
                    }
                    tag = true;
                }, 200);
            }
        });
    },
    floatWindow: function floatWindow() {
        $(".floating_window").append('<div class="floating_window_top"> <img src="../../assets/floating_window_top.png" alt=""><p>回顶部</p></div>');

        $(document).on('click', ".floating_window_top", function () {
            console.log("sd");
            $('body,html').animate({
                'scrollTop': 0
            }, 500);
        });
    },
    news: function news() {
        //新闻公告
        if ($('.news-box')) {
            var scrollNews = function scrollNews() {
                if ($('.new-list li').length < 4) {
                    return;
                }
                $('.new-list ul').animate({
                    'marginTop': '-38px'
                }, 500, function () {
                    var first = $('.new-list li').eq(0);
                    var second = $('.new-list li').eq(1);
                    $('.new-list ul').append(first).append(second).css('marginTop', 0);
                    setTimeout(scrollNews, 5000);
                });
            };

            setTimeout(scrollNews, 5000);
        }
    }
};
var RC = {
    url: "",
    url2: '/train-user-manager',
    urlPath: '${basePath}',
    ajax: function ajax(type, url, async, data, successFun) {
        $.ajax({
            type: type,
            url: RC.url + url,
            async: async,
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
                // 'userid':"",
                // 'token':"",
                // 'deviceinfo':""
            },
            data: data ? JSON.stringify(data) : null,
            success: function success(dataS) {
                if ($.isPlainObject(dataS) && dataS.code && dataS.code == "333333") {
                    layer.alert(dataS.message, {
                        title: '温馨提示',
                        closeBtn: 0,
                        btn: ['我知道了'],
                        btnAlign: 'c',
                        area: ['300px', '180px'],
                        yes: function yes(index, layero) {

                            window.location.href = "./login";
                        }
                    });
                } else if(dataS.code=="500000"){
                    $("#user_register").trigger("click");
                }
                else {
                    successFun(dataS);
                }
            },
            error: function error(dataerro) {

                if (dataerro.status == 1010) {
                    window.location.href = "./login";
                }
            }
        });
    }
};
$(function () {
    common.init();
});
//设置cookie
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//获取cookie
function getCookie(name) {
    var arr,
        reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }
}

//删除cookie
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) {
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
}
// 发送短信
function sendMessages(phone, time, o) {
    var ajaxData = {
        phone: phone
    };
    RC.ajax('post', '/login/getSmsVerifyCode', true, ajaxData, function (data) {
        if (data.code == '000000') {
            $(o).val('60s');
            $(o).removeClass("sending");
            window.setTime = setInterval(function () {
                time--;
                $(o).val(time + 's');
                if (time <= 0) {
                    clearInterval(setTime);
                    $(o).val('获取验证码');
                }
            }, 1000);
            layer.msg(data.data, { icon: 6, anim: 4 });
        } else {
            layer.msg(data.message, { icon: 5, anim: 6 });
        }
    });

}
function validatemobilePhone(mobile) {
    var mobileVal = mobile.val();
    if (mobileVal.length == 0) {
        $(mobile).addClass("active");
        layer.msg('请输入手机号码！', { icon: 5, anim: 6 });
        return false;
    }
    if (mobileVal.length != 11) {
        $(mobile).addClass("active");
        layer.msg('请输入有效的手机号码！', { icon: 5, anim: 6 });
        return false;
    }

    var myreg = /^1\d{10}$/;
    if (!myreg.test(mobileVal)) {
        $(mobile).addClass("active");
        layer.msg('请输入有效的手机号码！', { icon: 5, anim: 6 });
        return false;
    }
    $(mobile).removeClass("active");
    return true;
}
function topFindCourseCenter(key, value, categoryCode) {
    if (key == 'name') {
        var teacherOrName = $("#search-content").val();
        var searchText = $("#search-text").val();
        if (searchText != null && searchText != '' && searchText != '请输入课程名称' && searchText != '请输入教师名称') {
            key = "teacherOrName=" + teacherOrName + "&" + key;
            value = searchText;
        } else {
            key = "";
            value = "";
        }

    }
    var url = "";
    if (key == 'firstCode') {
        url = "http://clxy.bjou.edu.cn:80/course/list?paramType=1&" + key + "=" + value + "&categoryCode=" + categoryCode;
    } else {
        url = "http://clxy.bjou.edu.cn:80/course/list?paramType=1&" + key + "=" + value;
    }

    window.location.href = url;
}
function logout() {
    //询问框
    layer.confirm('确定要退出登录吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        ajaxLogOut();
    }, function () {
    });
}
//退出
function ajaxLogOut() {
    RC.ajax('post', '/myCenter/personal/loginout', true, null, function (data) {
        if (data.code == '000000') {
            setTimeout(function () {
                layer.msg("退出成功", { icon: 6, anim: 4 });
                window.location.href = RC.url;
            }, 1000)
        } else {
            layer.msg(data.message, { icon: 5, anim: 6 });
        }
    })
}