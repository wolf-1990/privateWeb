"use strict";

/*关闭大图片窗口*/
$(document).on("click", ".close_window,#continueBuy", function () {
	$(".shadow").hide();
});
//
$(".course-lecture-ico").on('click', function () {
	if ($(this).attr("data-status") == 0) {
		layer.msg('暂无讲义', {
			icon: 5,
			anim: 6
		});
	}
});
//我的问答弹框
$(".question-submit-btn").click(function () {
	if ($(this).is(".active")) {
		$(this).removeClass("active");
		$(".text-box").removeClass("active");
	} else {
		$(this).addClass("active");
		$(".text-box").addClass("active");
	}
});
//提交--我的问答
$("#question-submit").on('click', function () {
	var quetionText = $("#my-question").val();
	if (quetionText.trim() == "" || quetionText.length < 5) {
		layer.msg('问答字符必须为5个字符以上，请重新输入', {
			icon: 5,
			anim: 6
		});
		return;
	}
	var dataObj = {
		lessonId: $("#playplayCourseId").val(),
		questionContent: quetionText
	};
	RC.ajax('post', 'user/study/lesson/qa', true, dataObj, function (data) {
		if (data.code == '000000') {
			//提示
			layer.msg(data.data, {
				icon: 6,
				anim: 5
			});
			setTimeout(function () {
				$(".text-box").find("#my-question").val("");
				$(".text-box,.question-submit-btn").removeClass("active");
			}, 1000);
		} else {
			layer.msg(data.message, {
				icon: 5,
				anim: 6
			});
		}
	});
});

$(".app_erw,.weix").hover(function () {
	if ($(".submit-btn").is(".active")) {
		$(".text-box,.submit-btn").removeClass("active");
	}
});
//评价相关
function start() {
	var that = this;
	$(".cancle-btn,#close-selcted").on('click', function () {
		$("#shadow-message").hide();
		$("html,body").removeClass("no-scroll");
	});
	//点击星星
	$('.evaluate_text>div>p').on('click', function (even) {
		var startPageX = parseInt($(this).find('span').offset().left, 10);
		var distance = (even.pageX - startPageX) / 120 * 100 + '%';
		$(this).find('span').css('width', distance);
		$(this).siblings('span').text(((even.pageX - startPageX) / 120 * 5).toFixed(2));
	});
	$('.evalu-list textarea').on('input,propertychange', function () {
		var length = $(this).val().length;
		if (length > 500) {
			$(this).val($(this).val().substring(0, 500));
			$('.small-text').text("还可以输入0个字");
		} else {
			$('.small-text').text("还可以输入" + (500 - length) + "个字");
		}
	});
	//提交
	$('.submit-btn').on('click', function () {
		if ($('.evalu-list textarea').val().length < 5) {
			layer.msg("至少输入5个字", {
				icon: 5,
				anim: 6
			});layer.mess;
			return;
		}
		//评价

		var evaluationText = $('.evalu-list textarea').val(),
		    professionalSkills = parseFloat($(".professionalSkills").text()),
		    //专业能力
		courseMien = parseFloat($(".courseMien").text()),
		    //课程风采
		dataProvision = parseFloat($(".dataProvision").text()); //资料提供
		var evaluData = {
			bizType: 2, //1课程,2课时
			content: evaluationText,
			lessonId: $("#playCourseId").val(),
			starLevels: [professionalSkills, courseMien, dataProvision],
			types: [1, 2, 3] //1专业能力,2课程风采,3资料提供

		};

		RC.ajax('post', 'user/study/lesson/evaluate', true, evaluData, function (data) {
			if (data.code == '000000') {
				//评价提交成功--回显评价内容
				that.parmLessonEvalu();
				//提示
				$("#shadow-message").hide();
				$("html,body").removeClass("no-scroll");
				layer.msg(data.data, {
					icon: 6,
					anim: 5
				});
			} else {

				layer.msg(data.message, {
					icon: 5,
					anim: 6
				});
			}
		});
	});
}

/*聊天和目录切换*/
var tabList = function tabList(idName) {
	idName.find(".tab_title li").click(function () {
		var index = $(this).index();
		$(this).find(".tab_title_list").addClass("blue");
		$(this).siblings().find(".tab_title_list").removeClass("blue");
		idName.find(".tab_content_list").eq(index).show();
		idName.find(".tab_content_list").eq(index).siblings().hide();
		/*滚动条*/
		if (index == 1) {
			$("#catalog").niceScroll({
				cursorcolor: "#CCCCCC",
				cursoropacitymax: 1,
				touchbehavior: false,
				cursorwidth: "8px",
				cursorborder: "0",
				cursorborderradius: "5px"
			});
		} else if (index == 0) {
			$("#sucai").niceScroll({
				cursorcolor: "#CCCCCC",
				cursoropacitymax: 1,
				touchbehavior: false,
				cursorwidth: "8px",
				cursorborder: "0",
				cursorborderradius: "5px"
			});
		}
	});
};
tabList($("#right_plbox"));

$("#send").click(function () {
	if ($("#chat").outerHeight() > $("#sucai").outerHeight()) {
		$("#sucai").niceScroll({
			cursorcolor: "#CCCCCC",
			cursoropacitymax: 1,
			touchbehavior: false,
			cursorwidth: "8px",
			cursorborder: "0",
			cursorborderradius: "5px"
		});
	}
	$("#send_msg_text").val($('#edit').val().trim());
	if ($("#send_msg_text").val().length > 0) {
		onSendMsg(0, $("#edit").val());
	} else {
		alert("请输入内容");
	}
	//onSendMsg(0);
	openEmotionFlag = false;
});
window.onkeypress = function (e) {
	if (e.keyCode == 13) {
		$("#send_msg_text").val($('#edit').val().trim());
		if ($("#send_msg_text").val().length > 0) {
			onSendMsg(0, $("#edit").val());
		} else {
			alert("请输入内容");
		}
		openEmotionFlag = false;
	}
};

var catograyList = function catograyList(eachElem, clickElem, siblingsName) {

	$(eachElem).each(function () {
		$(this).find(clickElem).click(function () {
			if (!$(this).is(".close")) {
				$(this).addClass("close");
				$(this).siblings(siblingsName).show(200);
				$(this).parent().addClass("open");
			} else {

				$(this).parent().removeClass("open");
				$(this).removeClass("close");
				$(this).siblings(siblingsName).hide(200);
			}
		});
	});
};
$(document).ready(function () {
	catograyList(".course_list_box", ".course_list_title", ".course_all");
	catograyList(".course_list", ".course_title", ".class_all");
	$(".new_version").hover(function () {
		$(this).parents(".course_title_time").siblings(".newversion").show();
		$(this).parents(".course_title_time").siblings(".oldversion").hide();
		$(this).addClass("select").siblings().removeClass("select");
	});
	$(".old_version").hover(function () {
		$(this).parents(".course_title_time").siblings(".newversion").hide();
		$(this).parents(".course_title_time").siblings(".oldversion").show();
		$(this).addClass("select").siblings().removeClass("select");
	});
	$("#sucai").niceScroll({
		cursorcolor: "#CCCCCC",
		cursoropacitymax: 1,
		touchbehavior: false,
		cursorwidth: "8px",
		cursorborder: "0",
		cursorborderradius: "5px"
	});
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



$(function () {
	// var vm = initLive();
	// 直播是否结束
	// var endTimeSeconds = $("#endTimeSeconds").val();//视频结束时间
	// var nowTime = new Date().getTime() / 1000;//当前时间
	// var liveProgressStatus = $("#liveProgressStatus").val();//视频状态0未开始  1 进行中 2已结束
	// if (liveProgressStatus == 2) {
	// 	aliVideo.init(liveProgressStatus);
	// 	return;
	// }
	// else if (liveProgressStatus == 0) {
	// 	aliVideo.init(liveProgressStatus);
	// 	setTimeout(function () {
	// 		aliVideo.init(liveProgressStatus);
	// 	}, 30000);
	// }
	// else if (liveProgressStatus == 1) {//进行中
	// 	aliVideo.init(liveProgressStatus);
	// 	setTimeout(function () {
	// 		//判断直播是否结束
	// 		if (nowTime > endTimeSeconds) {//直播结束
	// 			liveProgressStatus = 2
	// 		}
	// 		aliVideo.init(liveProgressStatus);
	// 	}, 30000);
	// }
});