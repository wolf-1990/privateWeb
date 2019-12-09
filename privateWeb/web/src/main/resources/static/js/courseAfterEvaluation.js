'use strict';

var courseAfterObj = {
    commonObj: {
        lessonid: '',
        playstatus: 1 //type类型1-录播 2-直播
    },
    // 填写评价窗口dom
    opEvaluationDom: function opEvaluationDom() {
        var opDom = '<div class="evalu-content tc">\
        <div class="evaluate_text">\
            <div style="margin-top:0;">\
                <div class="start-list">\
                    <span class="start-list-item active"></span>\
                    <span class="start-list-item active"></span>\
                    <span class="start-list-item active"></span>\
                    <span class="start-list-item active"></span>\
                    <span class="start-list-item active"></span>\
                    <span class="yellow-color">非常好</span>\
                </div>\
            </div>\
        </div>\
        <div class="evalu-box">\
            <div class="evalu-list">\
                <ul class="clearfix evalu-list-item">\
                    <li class="fl tc active" data-id="1">知识点讲解非常清晰</li>\
                    <li class="fl tc" data-id="2">知识点讲解非常清晰</li>\
                    <li class="fl tc" data-id="3">知识点讲解非常清晰</li>\
                    <li class="fl tc" data-id="4">知识点讲解非常清晰</li>\
                </ul>\
            </div>\
            <div class="evalu-list evalu-textarea-box ">\
                <div class="text-area">\
                    <textarea name="name" rows="" cols="" placeholder="分享课时给你带来了什么收货和启发"></textarea>\
                    <div class="evalu-num">\
                        <i class="rt small-text" id="text-all">500</i>\
                        <i class="rt small-text">/</i>\
                        <i class="rt small-text" id="text-small">1</i>\
                    </div>\
                </div>\
            </div>\
            <div class="btn-box clearfix tc">\
                <span class="evalu-btn submit-btn">提交</span>\
            </div>\
        </div>\
    </div>';
        return opDom;
    },
    //评价内容回显
    showEvaluationDom: function showEvaluationDom(dataObj) {
        var starHtml = "";
        for (var i = 0; i < dataObj.score; i++) {
            starHtml += '<span class="start-list-item active"></span>';
        }
        for (var i = 0; i < 5 - dataObj.score; i++) {
            starHtml += '<span class="start-list-item"></span>';
        }
        var tagHtml = "";
        dataObj.templateList.forEach(function (item, i) {
            tagHtml += '<li class="fl tc active" data-id="' + item.id + '">' + item.content + '</li>';
        });
        var opDom = '<div class="evalu-content tc">\
                <div class="evaluate_text">\
                    <div style="margin-top:0;">\
                        <div class="start-list">' + starHtml + '\
                            <span class="yellow-color">' + dataObj.publicContent + '</span>\
                        </div>\
                    </div>\
                </div>\
                <div class="evalu-box">\
                    <div class="evalu-list">\
                        <ul class="clearfix evalu-list-item">' + tagHtml + '</ul>\
                    </div>\
                    <div class="evalu-list evalu-textarea-box ">\
                        <div class="show-evalu tc">\
                            <span class="evalu-line"></span>\
                            <span>评价语</span>\
                            <span class="evalu-line"></span>\
                        </div>\
                        <div class="show-evalu-text tc">' + dataObj.content + '</div>\
                    </div>\
                </div>\
            </div>';
        return opDom;
    },
    //判断当前课时是否评价过
    parmLessonEvalu: function parmLessonEvalu(lessonid, playstatus) {
        var that = this;
        that.commonObj.playstatus = playstatus;
        RC.ajax('get', 'user/study/lesson/evadetail/' + lessonid, true, '', function (data) {
            if (data.code == '000000') {
                $("#shadow-message").show();
                var dataObj = data.data;
                if (!data.data) {
                    //未评价 显示评价
                    $("#shadow-message .course-bottom").html(that.opEvaluationDom());
                    that.getStartContent(5);
                    that.clickstart(lessonid);
                } else {
                    //已评价--显示评价内容
                    $("#shadow-message .course-bottom").html(that.showEvaluationDom(data.data));
                }
                $("#shadow-message").show();
                $("html,body").addClass("no-scroll");
            } else {
                $("#shadow-message").show();
                $("#shadow-message .course-bottom").html(that.opEvaluationDom());
                that.getStartContent(5);
                that.clickstart(lessonid);
            }

            that.operBox();
        });
    },
    operBox: function operBox() {
        //弹框操作
        $("#close-selcted,#shadow-message").on('click', function () {
            $("#shadow-message").hide();
            if ($("#showevalu").length > 0) {
                $("#showevalu").removeClass("active");
            }

            $("html,body").removeClass("no-scroll");
        });
        $(".class-evalu,.course-ware-box").on('click', function (event) {
            var event = event || window.event;
            if (event && event.stopPropagation) {
                event.stopPropagation();
            } else {
                event.cancelBubble = true;
            }
        });
    },
    clickstart: function clickstart(lessonid) {
        var that = this;
        // 点击标签
        // $(".evalu-list-item li").on('click',function(){
        //     if($(this).is(".active")){
        // 	    $(this).removeClass("active");
        //     }else{
        //         $(this).addClass("active");
        //     }
        // })
        //点击星星
        $(".start-list-item").on("click", function () {
            if ($(this).is(".active")) {
                $(this).nextAll().removeClass("active");
                if ($(this).index() > 0) {
                    $(this).removeClass("active");
                }
            } else {
                $(this).prevAll().addClass("active");
                $(this).addClass("active");
            }

            var startStatus = ["非常差", "差", "一般", "好", "非常好"];
            var indexN = $(".start-list-item.active").length;
            $(".start-list .yellow-color").html(startStatus[indexN - 1]);
            that.getStartContent($(".start-list-item.active").length);
        });
        // 文本域输入内容
        $('.evalu-list textarea').on('input propertychange', function () {
            var length = $(this).val().length;
            if (length > 500) {
                $(this).val($(this).val().substring(0, 500));
                $('#text-all').text("500");
            } else {
                $('#text-all').text(500 - length);
                $('#text-small').text(length);
            }
        });
        //提交
        $('.submit-btn').on('click', function () {
            if ($(".evalu-list-item li.active").length == 0) {
                layer.msg("对老师评价一下吧", {
                    icon: 5,
                    anim: 6
                });layer.mess;
                return;
            }
            // if($('.evalu-list textarea').val().length<5){
            //     layer.msg("最少输入5字符", {
            //             icon: 5,
            //             anim: 6
            //         });layer.mess
            //     return;
            // }
            if ($('.evalu-list textarea').val().length > 500) {
                layer.msg("最多输入500字符", {
                    icon: 5,
                    anim: 6
                });layer.mess;
                return;
            }
            // 公共信息
            var templateContentIdList = [];
            if ($(".evalu-list-item li.active").length > 0) {
                $(".evalu-list-item li.active").each(function (i, item) {
                    templateContentIdList.push($(item).attr("data-id"));
                });
            }
            //评价
            var evaluationText = $('.evalu-list textarea').val();
            // professionalSkills=parseFloat($(".professionalSkills").text()),//专业能力
            // courseMien=parseFloat($(".courseMien").text()),//课程风采
            // dataProvision=parseFloat($(".dataProvision").text());//资料提供
            var evaluData = {
                bizType: 2, //1课程,2课时
                content: evaluationText,
                evaluateScore: $(".start-list .start-list-item.active").length,
                evaluateTemplateContentIdList: templateContentIdList,
                evaluateTemplateId: $(".start-list").attr("data-temId"),
                lessonId: lessonid
                //starLevels: [professionalSkills,courseMien,dataProvision],//课程评价
                // types: [1,2,3]//课程评价 1专业能力,2课程风采,3资料提供

            };

            RC.ajax('post', 'user/study/lesson/evaluate', true, evaluData, function (data) {
                if (data.code == '000000') {
                    //评价提交成功--回显评价内容
                    // that.parmLessonEvalu();
                    //提示
                    layer.msg(data.data, {
                        icon: 6,
                        anim: 5
                    });
                    $("#shadow-message").hide();
                    $("#showevalu").removeClass("active");
                } else {
                    layer.msg(data.message, {
                        icon: 5,
                        anim: 6
                    });
                }
            });
        });
    },
    // 根据星星获取标签--获取课时评价公共内容
    getStartContent: function getStartContent(objscore) {
        var that = this,
            temTagHtml = "",
            ajaxObj = {
            score: objscore,
            type: that.commonObj.playstatus //type类型1-录播 2-直播
        };
        RC.ajax('post', 'user/study/lesson/queryEvaluateTemplate', true, ajaxObj, function (data) {
            if (data.code == '000000') {
                // 模板id
                $(".start-list").attr("data-temId", data.data.id);
                // 标签
                data.data.templateList.forEach(function (temItem, temNum) {
                    temTagHtml += '<li class="fl tc" data-id="' + temItem.id + '">' + temItem.content + '</li>';
                });
                $(".evalu-list-item").html(temTagHtml);

                $(".evalu-list-item li").on('click', function () {
                    if ($(this).is(".active")) {
                        $(this).removeClass("active");
                    } else {
                        $(this).addClass("active");
                    }
                });
            } else {
                layer.msg(data.message, { icon: 5, anim: 6 });
            }
        });
    }
};