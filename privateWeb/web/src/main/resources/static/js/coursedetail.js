"use strict";

var courseDetail = {
    init: function init() {
        this.gotoCart();
        this.courseEnroll();
        this.clickCollection();
        this.nowJoin();
        this.clickList();
        if (!$("#J_prismPlayer").parent().is(":hidden") && $("#J_prismPlayer").attr("data-video-url")) {
            this.playerFn($("#J_prismPlayer").attr("data-video-url"));
        }
        //跳转直播判断
        this.toLive();
        //this.evaluateDetail(); //综合评星
        //this.page();
        //直播跳转
        this.toLive();
        this.clickCatalogue(); //点击课程目录
    },
    // 点击课程目录
    clickCatalogue: function clickCatalogue() {
        $(".show-tip").on("click", function () {
            var courseId = $(this).attr("data-lessonid"),

                //课时id
                dataObj = { id: courseId },
                jumpUrl = 'user/study/course/setCourseSession?courseId=' + $("#courseId").val() + '&id=' + courseId;
            RC.ajax('post', '/course/queryCourseType', true, dataObj, function (data) {
                if (data.code == '000000') {
                    if (data.data.type == 7) {
                        window.location.href = jumpUrl + '&type=1';
                    } else if (data.data.type == 8) {
                        window.location.href = jumpUrl + '&type=2';
                    } else {
                        layer.msg(data.data.returnMsg, { icon: 1, anim: 6 });
                    }
                } else if (data.code == '333333') {
                    $("#user_register").trigger("click");
                } else if (data.code == '100000') {
                    layer.msg(data.data.returnMsg, {
                        icon: 5,
                        anim: 6
                    });
                }
            });
        });
    },
    //播放器初始化
    playerFn: function playerFn(url) {
        var player = new Aliplayer({
            id: 'J_prismPlayer',
            width: '100%',
            autoplay: false,
            cover: $(".banner_show img").attr("src") == "" ? "/static/images/video_default.png" : $(".banner_show img").attr("src"),
            //skinRes: "/static/mages/video_default.png",
            //支持播放地址播放,此播放优先级最高
            source: url,
            preload: false,
            skinLayout: [{ name: "bigPlayButton", align: "blabs", x: 30, y: 80 }, {
                name: "H5Loading", align: "cc"
            }, { name: "errorDisplay", align: "tlabs", x: 0, y: 0 }, { name: "infoDisplay" }, { name: "tooltip", align: "blabs", x: 0, y: 56 }, { name: "thumbnail" }, {
                name: "controlBar", align: "blabs", x: 0, y: 0,
                children: [{ name: "progress", align: "blabs", x: 0, y: 44 }, { name: "playButton", align: "tl", x: 15, y: 12 }, { name: "timeDisplay", align: "tl", x: 10, y: 7 }, { name: "fullScreenButton", align: "tr", x: 10, y: 12 },
                    /*{name:"subtitle", align:"tr",x:15, y:12},*/
                    // {name:"setting", align:"tr",x:15, y:12,children: [
                    // 	{name:"prism-stream-selector", align: "tr", x: 8, y: 12}
                    // ]},
                    { name: "volume", align: "tr", x: 15, y: 10 }]
            }]
        }, function () {
            console.log("播放器");
        });
        //		  player.on('ready',function(e) {
        //		    player.play();
        //		 });
    },
    //开课次数
    courseEnroll: function courseEnroll() {
        if (common.getUrlParms("opentime")) {
            var currCourse = $("#course-enroll .ux-dropdown_bd li").filter(function (i, item) {
                return $(item).attr("data-openid") == common.getUrlParms("opentime");
            });
            var currText = $(currCourse[0]).text();
            $(".ux-dropdown_cnt").text(currText);
        }
        $("#course-enroll").on('click', function () {
            var currDom = $(this).find(".down-time");
            if (currDom.is(".time-down")) {
                currDom.removeClass("time-down").addClass("time-up");
                $(this).find(".ux-dropdown_bd").show();
            } else {
                currDom.addClass("time-down").removeClass("time-up");
                $(this).find(".ux-dropdown_bd").hide();
            }
        });
        $("#course-enroll .ux-dropdown_bd li").on('click', function () {
            $(".ux-dropdown_cnt").text($(this).text());
            $(".ux-dropdown_cnt").attr("data-openid", $(this).attr("data-openid"));
            window.location.href = "course/detail?id=" + $(".ux-dropdown_cnt").attr("data-openid");
            $("#course-enroll").find(".ux-dropdown_bd").hide();
        });
    },
    // 立即参加
    nowJoin: function nowJoin() {
        $("#now-join").on('click', function () {
            var dataStatus = $(this).attr("data-status");
            if (dataStatus == 2) {
                //去学习接口
                return;
            }
            if (dataStatus == 0) {
                var dataObj = {
                    courseId: $(this).attr("data-id"), //商品名称
                    courseName: $(this).attr("data-name") //商品id
                };

                RC.ajax('post', '/myCenter/attendCollect/attendFimmediate', true, dataObj, function (data) {
                    if (data.code == '000000') {
                        // layer.msg("老师会第一时间与您联系，请确认您的联系方式是否正确", {
                        // 	icon: 1,
                        // 	anim: 6
                        // });
                        layer.confirm('已成功申请，老师会第一时间与您取得联系', {
                            btn: ['我知道了'] //按钮
                        }, function () {
                            location.reload();
                        }, function () {});
                    } else {
                        layer.msg(data.message, {
                            icon: 5,
                            anim: 6
                        });
                    }
                });
            }
        });
    },
    //收藏功能
    collectionAjax: function collectionAjax(dataObj, statu, url) {
        RC.ajax('post', url, true, dataObj, function (data) {
            if (data.code == '000000') {
                //提示
                if (statu == 0) {
                    $(".collection-commodity").removeClass("active");
                    $(".collection-commodity").find(".collection-text").text("取消收藏");
                    layer.msg("收藏成功", { icon: 1, anim: 6 });
                } else {
                    $(".collection-commodity").addClass("active");
                    $(".collection-commodity").find(".collection-text").text("收藏");
                    layer.msg('取消收藏成功', { icon: 1, anim: 6 });
                }
            } else if (data.code == '100000') {

                if (data.data && data.data.type == 1) {
                    //type==1是未登录
                    $("#user_register").trigger("click");
                    return;
                }
                layer.msg(data.message, {
                    icon: 5,
                    anim: 6
                });
            } else {
                layer.msg(data.message, {
                    icon: 5,
                    anim: 6
                });
            }
        });
    },
    //点击收藏
    clickCollection: function clickCollection() {
        var that = this,
            type = 0,
            ajaxUrl = "";
        $(".collection-commodity").on('click', function () {
            var dataObj = {
                productName: $(this).attr("data-name"), //商品名称
                productId: $(this).attr("data-id"), //商品id
                lessonNum: $(this).attr("data-lessonNum") //课时数量
            };
            //type=0收藏 =1取消收藏
            if ($(this).is(".active")) {
                type = 0;
                ajaxUrl = "/myCenter/favorite/addToStuFavorit";
                that.collectionAjax(dataObj, type, ajaxUrl);
            } else {
                ajaxUrl = "/myCenter/favorite/cancelStuFavorite";
                type = 1;
                that.collectionAjax(dataObj, type, ajaxUrl);
            }
        });
    },
    //加入购物车
    gotoCart: function gotoCart() {
        $(".course_single_btn.goto-cart").on('click', function () {
            if ($(this).is(".have-cart")) {
                //已加入购物车--不操作
                return;
            }

            RC.ajax('post', 'cart/addgood', true, $("#courseId").val(), function (data) {
                if (data.code == '000000') {
                    //如果购物车数量大于99--显示提示框
                    if (data.data < 99) {
                        //成功--修改样式为灰色
                        $(".course_single_btn.goto-cart").removeClass("goto-cart").addClass("have-cart");
                        layer.msg("添加购物车成功", {
                            icon: 6,
                            anim: 6
                        });
                        $(".header-box .cart-num").text(data.data);
                    } else {
                        layer.open({
                            title: ' ',
                            skin: 'my-cart-class',
                            content: '<div class="my-lay-box">\
								 			<p class="tc img-box"><img src="./images/cart_ico.png"/></p>\
								  			<p class="tc">您的购物车已满,请整理后再次添加</p>\
								  			</div>',
                            btn: []
                        });
                    }
                } else if (data.code == '100000') {

                    if (data.data && data.data.type == 1) {
                        //type==1是未登录
                        $("#user_register").trigger("click");
                        return;
                    }
                    layer.msg(data.message, {
                        icon: 5,
                        anim: 6
                    });
                } else {
                    layer.msg(data.message, {
                        icon: 5,
                        anim: 6
                    });
                    window.location.reload();
                }
            });
        });
    },
    //点击课程目录
    clickList: function clickList() {

        var element = layui.element;
        element.on('tab()', function (data) {
            console.log(data);
        });
        $('.catalogue_one h3').on('click', function () {
            $(this).siblings().slideToggle();
            var changeIco = $(this).find(".downup-level");
            if (changeIco.is(".first-down-ico")) {
                changeIco.removeClass("first-down-ico").addClass("first-up-ico");
            } else {
                changeIco.removeClass("first-up-ico").addClass("first-down-ico");
            }
        });
    },
    // 判断直播状态
    toLive: function toLive() {
        $(".courseFade_title").on('click', function () {
            var _objclass = this;
            $(".courseFade_title").on('click', function () {
                var liveStatus = $(this).attr("data-status"),
                    msgtext = "";
                if (liveStatus == 0) {
                    msgtext = "直播未开始";
                } else if (liveStatus == 1) {
                    msgtext = "正在转码中";
                }
                layer.msg(msgtext, {
                    icon: 5,
                    anim: 6
                });
            });
        });
    },
    //综合评星
    evaluateDetail: function evaluateDetail() {
        var EvaluateDetail = {
            firstResult: 0,
            id: $("#courseId").val(),
            page: "0",
            rows: "2",
            sidx: "string",
            sord: "string",
            type: 1
        };
        //星星
        RC.ajax('post', 'course/queryCourseEvaluateDetail', true, EvaluateDetail, function (data) {
            if (data.code == '000000') {
                var allCore = data.data.avgStar,

                    //平均分
                    professionalStar = data.data.professionalStar,

                    //专业能力分数
                    courseStar = data.data.courseStar,

                    //课程风采分数
                    materialStar = data.data.materialStar; //资料提供分数
                $("#all-core").text(parseFloat(allCore).toFixed(1));
                $("#all-rate").html('<span  style="width:' + parseFloat(allCore / 5) * 100 + '%;"></span>');
                $(".comment_score_single").html('<ul>\
									<li>\
										<span>专业能力</span>\
										<p><span style="width:' + parseFloat(professionalStar / 5) * 100 + '%;"></span></p>\
										<span>' + professionalStar + '</span>\
										<span>' + (professionalStar == 5 ? "力荐" : " ") + '</span>\
									</li>\
									<li>\
										<span>课程风采</span>\
										<p><span style="width:' + parseFloat(courseStar / 5) * 100 + '%;"></span></p>\
										<span>' + courseStar + '</span>\
										<span>' + (courseStar == 5 ? "力荐" : " ") + '</span>\
									</li>\
									<li>\
										<span>资料提供</span>\
										<p><span style="width:' + parseFloat(materialStar / 5) * 100 + '%;"></span></p>\
										<span>' + materialStar + '</span>\
										<span>' + (materialStar == 5 ? "力荐" : " ") + '</span>\
									</li>\
								</ul>');
            }
        });
    },
    page: function page() {
        //分页
        var laypage = layui.laypage;
        laypage.render({
            elem: "page",
            count: $('#totalList').val(),
            limit: 6,
            groups: 3,
            hash: 'page',
            jump: function jump(obj, first) {
                evaluateData(obj.curr);
            }
        });
        function evaluateData(page) {
            var EvaluateListData = {
                id: $("#courseId").val(),
                page: page,
                rows: 6,
                type: 1
            };
            //评价列表
            RC.ajax('post', 'course/queryCourseEvaluateList', true, EvaluateListData, function (data) {
                if (data.code == '000000') {
                    var str = '';
                    var evaStr = '';
                    if (data.data.total < 2) {
                        //总条数小于1--不显示分页
                        $('.page_scope').hide();
                    }
                    if (!data.data.rows.length) {
                        str = '<div class="no-data-re">\
									<div class="no-date-box ver-middle">\
										<p class="tc no-data">\
											<img class="no-data-img"  src="static/images/no_data_img.png" />\
										</p>\
										<p class="tc no-data-text" >抱歉，暂无相关内容</p>\
									</div>\
								</div>';
                    } else {
                        $.each(data.data.rows, function (index, item) {
                            str += '<li class="clearFix">\
										<div class="commen_score_evaluateName">\
											<img src="' + item.studentUrl + '" alt="">\
											<span class="restrictTextLength">' + item.userName + '</span>\
											<p><span style="width:' + parseFloat(item.avgStar / 5) * 100 + '%;"></span></p>\
											<span class="rt color9">' + item.evaluateTime + '</span>\
										</div>\
										<div class="commen_score_evaluateText">\
											<div class="commen_list">' + item.content + '</div>\
										</div>';
                            if (item.replyContent) {
                                str += '<div class="admin_eva">\
											<p class="top">\
												<i class="bg-ico admin-ico"></i> 管理员回复\
												<span>' + item.replyTime + '</span>\
											</p>\
											<p class="botom">' + item.replyContent + '</p>\
										</div>';
                            }

                            str += '</li>';
                        });
                    };
                    $('.commen_score_evaluate ul').html(str);
                }
            });
        }
    }
    //追加评论
    /*<p class="top"><i class="bg-ico add-commen-ico"></i> 追加评价<span>2018-2-2 13:24:34</span></p><p class="botom">我的评价评价我的评价</p>*/
};$(function () {
    courseDetail.init();
});

$('#all-core').html(''); //综合评分
$('#all-rate').find('span').css('width', '50%'); //综合评分星星

/*
var str = '';
str += '<ul>';
str += '<li><span>专业能力</span><p><span style="width:50%;"></span></p><span>4.92</span><span>力荐</span></li>';
str += '<li><span>课程风采</span><p><span style="width:50%;"></span></p><span>4.92</span><span>力荐</span></li>';
str += '<li><span>资料提供</span><p><span style="width:50%;"></span></p><span>4.92</span><span>力荐</span></li>';
str += '</ul>';
$('.comment_score_single').html(str);

var arr = [1, 2, 3];
if (Array.isArray && arr.length > 0) {
    str = '<ul style="min-height:300px;">';
    arr.forEach(function (item, index) {
        str += '<li class="clearFix"><div class="commen_score_evaluateName"><img src="images/bb.png"alt=""data-bd-imgshare-binded="1"><span class="restrictTextLength">姓名</span><p><span style="width:50%;"></span></p><span class="rt color9">2018-1-6 14:34:56</span></div><div class="commen_score_evaluateText"><div class="commen_list">评价评价评价评价评价评价评价评价评价评价评价评价评价评价评评价评价评价评价评价评价评价评价评价评价评价评价评价评价评</div></div><div class="admin_eva"></div></li>';
    });
    str += '</ul>';
    $('.commen_score_evaluate').html(str);
} else {
    $('.commen_score_evaluate').html('<div class="no-data-re"><div class="no-date-box ver-middle"><p class="tc no-data"><img class="no-data-img"src="../../assets/no_data_img.png"data-bd-imgshare-binded="1"></p><p class="tc no-data-text">抱歉，暂无相关内容</p></div></div>');
}*/
