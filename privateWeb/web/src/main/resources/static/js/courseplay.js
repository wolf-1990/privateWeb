"use strict";

$(function () {
	var setTime=null,stopPlay=0;
    var isFirstPlay = true;
	var courseStudy = {

		init: function init() {
			if ($("input[name='playerStatus']").val() == 3) {
				this.ccInitPlayBox();
				
			} 
			else if($("input[name='playerStatus']").val() == 1) {
				this.initLetcPlayBox();
			}
			// this.playAuth();
			this.clickBottom();
		
			this.courseList();
			this.onlinQuestion(); //在线提问
			this.courseEvaluation(); //课时评价
			//待删end
			this.courseRight();
			//跳转直播判断
			this.toLive();
			this.scrollAuto();
			this.clickEvalu();
			this.suggestFn();
		},
		seekTo: true,
		
		stuCourseLessonId: "",

		// cc初始化播放器
		ccInitPlayBox: function ccInitPlayBox() {
			var that = this;
			RC.ajax('get', '/myCenter/stuCourse/findPalyUrl?courseLessonId='+$("input[name='lessonId']").val(), true, '', function (data) {
				if (data.code == '000000') {
					var dataObj = data.data;

					//add by jxx 2019.04.15 云点播支持 status == 2
					var status = $("input[name='status']").val();
					if(status == 2){
                        $('#playbackPlayer').hide();
                        $('#h5player').on('play',function(e){
                            if(isFirstPlay){
                                courseStudy.startPlay(document.getElementById('h5player').duration,function(){
                                    isFirstPlay = false;
                                    if(!setTime){
                                        setTime = setInterval(function () {
                                            courseStudy.currTime($("input[name='totalSeconds']").val(), document.getElementById('h5player').currentTime);
                                        }, 3000);
                                    }
                                });
                            }else{
                                if(!setTime){
                                    setTime = setInterval(function () {
                                        courseStudy.currTime($("input[name='totalSeconds']").val(), document.getElementById('h5player').currentTime);
                                    }, 3000);
                                }
                            }
                        })
                        $('#h5player').on('pause',function(e){
                            if(setTime){
                                clearInterval(setTime);
                                setTime = null
                            }
                            courseStudy.stopPlay(document.getElementById('h5player').currentTime,document.getElementById('h5player').duration);
                        })

					}else{
                        $.DW.config({
                            userId: dataObj.userId,
                            roomId: dataObj.roomId,
                            recordId: dataObj.recordId,
                            groupId: dataObj.groupId,
                            viewername: dataObj.viewername,
                            viewertoken: dataObj.viewertoken,
                            isH5play: dataObj.isH5play
                        });
					}
					// $.DW.seek("100"); 
					// 播放开始
					var status = 0;
					window.on_player_start = function () {
						if (status == 0) {
							that.startPlay($.DW.getDuration());
							status = 1;
						}
						if(stopPlay==0){
							 courseStudy.currTime($.DW.getDuration(), $.DW.getPlayerTime());
                            courseStudy.setTime = window.setInterval(function () {
                            	courseStudy.currTime($.DW.getDuration(), $.DW.getPlayerTime());
                            }, 3000);
							 stopPlay=1;
						}
						
						//that.startPlay();
					};

					//恢复播放
					window.on_spark_player_resume = function () {
						console.log("恢复播放");
						// courseStudy.setTime = setInterval(function () {
						// 	courseStudy.currTime($.DW.getDuration(), $.DW.getPlayerTime());
						// }, 3000);
					};
					// 播放暂停
					window.on_spark_player_pause = function () {
						status = 0;
						stopPlay=1;
						courseStudy.currTime($.DW.getDuration(), $.DW.getPlayerTime());  
						courseStudy.stopPlay($.DW.getPlayerTime(), $.DW.getDuration());
						// window.clearInterval(setTime);
                        window.clearInterval(setTime);
					};
					// 播放停止
					window.on_spark_player_end = function () {
						stopPlay=1;
						courseStudy.currTime($.DW.getDuration(), $.DW.getPlayerTime());  
						courseStudy.stopPlay($.DW.getPlayerTime(), $.DW.getDuration());
                        window.clearInterval(setTime);
					};
					window.on_cc_login_error = function () {
						console.log('登录失败');
					};

					window.on_cc_login_success = function () {
						console.log('登录成功');
					};
				} else {}
			});
		},
		// 停止播放
		stopPlay: function(getPlayerTime, getDuration) {
			if(courseStudy.isFreeLesson()){
				return;
			}
			var that = this,
			    dataObj = {
				classHoursCount: $("input[name='classHoursCount']").val(),//"10",
				seconds: getPlayerTime, //视频当前播放时间,
				stuCourseLessonId: that.stuCourseLessonId,
				totalSeconds: getDuration //视频总时长
			};
			RC.ajax('post', '/myCenter/stuCourse/stopStudy', true, dataObj, function (data) {
				if (data.code == '000000') {} else {}
			});
		},
		// 开始播放
		startPlay: function startPlay(getDuration) {
            if(courseStudy.isFreeLesson()){
                return;
            }
			var that = this,
			    dataObj = {
				lessonId: $("input[name='lessonId']").val(),//"1cb39823-5f87-46fe-8480-b9d279914927",
				courseId: $("input[name='courseId']").val(),//"2bc694fc-8693-415a-afc6-20ae8e3e31e8",
				lessonName: $("input[name='lessonName']").val(),//"建设工程管理课程hhhh",
				productId: $("input[name='productId']").val(),//"12ad806f-2c0b-4271-9ab1-04315dfb9490",
				stuCourseId:$("input[name='stuCourseId']").val(),// "0",
				totalSeconds: getDuration //视频总时长
			};
			RC.ajax('post', '/myCenter/stuCourse/startStudy', true, dataObj, function (data) {
				if (data.code == '000000') {
					that.stuCourseLessonId = data.data.stuCourseLessonId;
					that.currentPosition = data.data.currentPosition;
					if (that.seekTo) {
						if ($("input[name='playerStatus']").val() == 3) {
							$.DW.seek(that.currentPosition);
							
						} else if($("input[name='playerStatus']").val() == 1) {
							window.player.sdk.seekTo(that.currentPosition);
						}

						that.seekTo = false;
					}
				} else {}
			});
		},
		//播放心跳
		currTime: function currTime(getDuration, getPlayerTime) {
            if(courseStudy.isFreeLesson()){
                return;
            }
			var that = this,
			    dataObj = {
				classHoursCount:  $("input[name='classHoursCount']").val(),//10, //课时总数
				stuCourseLessonId: that.stuCourseLessonId, //学习进度 id
				totalSeconds: getDuration, //视频总时长
				seconds: getPlayerTime //视频当前播放时间
			};
			RC.ajax('post', '/myCenter/stuCourse/studyHeart', true, dataObj, function (data) {
				if (data.code == '000000') {} else {}
			});
		},
		// 初始化乐视播放器
		initLetcPlayBox: function initLetcPlayBox() {
			window.player = new CloudVodPlayer();
			window.player.init({
				uu: $("input[name='uu']").val(), //用户唯一标识码
				vu: $("input[name='vu']").val(), //视频唯一标识码
				pu: "fc021b825c", //播放器唯一标识码
				auto_play: 0, //1自动播放
				pic: "", //封面地址oo
				width:845,
				height:524,
				callbackJs: "playerCallBack",
				type: 'video'
			}, "playbackPlayer");
		},
		// 提建议1
		suggestFn:function(){
			$("#suggest-box").on("click",function(){
				if($(this).is(".active")){
					$(this).removeClass("active");
                    $("#shadow-message").show();
				}else{
					$(this).addClass("active");
					$("#shadow-message").show();
				}
			})
		},
		clickEvalu:function(lessonid){
			var that=this;
			//弹框操作
			$("#close-selcted,#shadow-message").on('click',function(){
				$("#shadow-message").hide();
				if($("#showevalu").length>0){
					$("#showevalu").removeClass("active")
				}
				
				$("html,body").removeClass("no-scroll");
			})
			$(".class-evalu,.course-ware-box").on('click',function(event){
				var event = event || window.event; 
				if(event && event.stopPropagation) {           
					event.stopPropagation(); 
				} else {           
					event.cancelBubble = true; 
				}
			})
			// 点击标签
			$(".evalu-list-item li").on('click',function(){
			    if($(this).is(".active")){
				    $(this).removeClass("active");
			    }else{
					$(this).addClass("active");
					$(this).siblings().removeClass("active");
			    }
			})
		
			// 文本域输入内容
			$('.evalu-list textarea').on('input propertychange',function(){
				var length = $(this).val().length;
				if (length>500) {
					$(this).val($(this).val().substring(0,500));
					$('#text-all').text("500");
				}else{
					$('#text-all').text(500-length);
					$('#text-small').text(length);
				}
			})
			//提交
			$('.submit-btn').on('click',function(){
				if($(".evalu-list-item li.active").length==0){
					layer.msg("对老师评价一下吧", {
						icon: 5,
						anim: 6
					});layer.mess
					return;
				}
			
				if($('.evalu-list textarea').val().length>500){
					layer.msg("最多输入500字符", {
							icon: 5,
							anim: 6
						});layer.mess
					return;
				}
				// 公共信息
				var templateContentIdList=[];
				if( $(".evalu-list-item li.active").length>0){
					$(".evalu-list-item li.active").each(function(i,item){
						templateContentIdList.push($(item).attr("data-id"));
					});
				}
				//评价
				var evaluationText = $('.evalu-list textarea').val();
				
				var evaluData = {
						contents:evaluationText,
						title: $("input[name='suggestTitle']").val(),
						type:$(".evalu-list-item").find("li.active").attr("data-id")
				};
				
				RC.ajax('post', '/myCenter/stuCourse/addNewSuggestion', true, evaluData, function(data) {
					if(data.code == '000000') {
						layer.msg(data.data, {
							icon: 6,
							anim: 5
						});
						$("#suggest-box").removeClass("active");
						$("#shadow-message").hide();
	
					} else {
						layer.msg(data.message, {
							icon: 5,
							anim: 6
						});
					}
				})
			})
		},
	
		playAuth: function playAuth() {
			var that = this;
			if ($("#videoType").val() == 2) {
				that.playerFn("");
				return;
			}
			RC.ajax('post', '/user/study/lesson/getvideocertificate', true, { id: $("#playCourseId").val() }, function (data) {
				if (data.code == '000000') {
					that.playerFn(data.data);
				} else {}
			});
		},

		heartData: {
			hearTime: '', //心跳
			lessonId: '', //课时id
			studentCourseId: '', //学生课程id
			goodId: '' //商品id
		},
		getURlKey: function getURlKey(key) {
			var search = window.location.search.substring(1);
			var searchArr = search.split('&');
			var obj = {};
			for (var i = 0; i < searchArr.length; i++) {
				obj[searchArr[i].split('=')[0]] = searchArr[i].split('=')[1];
			}
			return obj[key];
		},
		//点击课时视频列表
		courseList: function courseList() {
			var _this = this;
			$(".add-course-title").on('click', function () {
				var currDom = $(this).parent(".course_list_every");
				if ($(this).parent(".course_list_every").is(".curr-play")) {
					$(this).parent(".course_list_every").removeClass("curr-play");
				} else {
					$(this).parent(".course_list_every").addClass("curr-play");
				}
				_this.scrollAuto(); //滚动条
			});
		},
		//点击课时视频 显示列表目录
		courseRight: function courseRight() {
			var _this = this;
			var tag = true;

			$('.course_right .click-obj').on('click', function () {
				if ($(this).is(".active")) {
					$(this).removeClass("active");
				} else {
					$(this).addClass("active");
					$(this).siblings().removeClass("active");
				}
				$(this).siblings().find('.course_list').hide();

				$(this).find('.course_list').stop().slideToggle(function () {
					if (tag) {
						_this.scrollAuto(); //滚动条
						tag = false; //仅需要执行一次
					}
				});
			});
			$('.course_list').on('click', function (event) {
				event.stopPropagation();
			});
		},
		//点击按钮显示输入框
		clickBottom: function clickBottom() {
			$("#showevalu").on("click", function () {
				if ($(this).is(".active")) {
					$(this).removeClass("active");
				} else {
					$(this).addClass("active");
					//判断是否评价过---显示评价的信息 
					courseAfterObj.parmLessonEvalu($("#playCourseId").val(), 1);
				}
			});
		},
		//登录
		loginFn: function loginFn() {
			$("#user_register").trigger("click");
		},
		//在线提问
		onlinQuestion: function onlinQuestion() {
			var that = this;
			//this.loginFn();
			$("#question-box .submit-bottom").on('click', function () {
				var quetionText = $(".question-text").val();
				if (quetionText.trim() == "" || quetionText.length < 5) {
					layer.msg('问答字符必须为5个字符以上，请重新输入', {
						icon: 5,
						anim: 6
					});
					return;
				}
				if (quetionText.length > 200) {
					layer.msg('问答字符必须为200个字符以内，请重新输入', {
						icon: 5,
						anim: 6
					});
					return;
				}
				var dataObj = {
					lessonId: $("#playCourseId").val(),
					questionContent: quetionText
				};
				RC.ajax('post', 'user/study/lesson/qa', true, dataObj, function (data) {
					if (data.code == '000000') {
						$(".question-text").val("");
						$(".question-list").prepend('<div class="add-question-box">\
							<div class="clearfix question-top">\
								<span class="fl">' + quetionText + '</span>\
								<span class="rt">' + that.timeChange(Date.parse(new Date())) + '</span>\
							</div>\
						</div>');
						//提示
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
		},
		timeChange: function timeChange(timestamp) {
			var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
			var Y = date.getFullYear() + '-';
			var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
			var D = date.getDate() + ' ';
			var h = date.getHours() + ':';
			var m = date.getMinutes() + ':';
			var s = date.getSeconds();
			return Y + M + D;
		},
		//课时评价
		courseEvaluation: function courseEvaluation() {
			var that = this;
			$("#course-evaluation .submit-bottom").on('click', function () {
				var starLevels = [];
				var evaluationText = $(".evaluation-text").val();

				if (evaluationText.trim() == "" || evaluationText.length < 5) {
					layer.msg('评价内容必须为5个字符以上，请重新输入', {
						icon: 5,
						anim: 6
					});
					return;
				}
				var evaluData = {
					bizType: 2, //1课程,2课时
					content: evaluationText,
					lessonId: $("#playCourseId").val(),
					starLevels: [],
					types: [1, 2, 3] //1专业能力,2课程风采,3资料提供

				};
				var evaCore = 0;
				$("#course-evaluation .evaluation-list>ul>li").each(function (i, item) {
					evaluData.starLevels.push(parseFloat($(item).find("p span").attr("data-core")));
					evaCore += parseFloat($(item).find("p span").attr("data-core")) / 5;
				});

				RC.ajax('post', 'user/study/lesson/evaluate', true, evaluData, function (data) {
					if (data.code == '000000') {
						$("#textarea-box").hide();
						$(".evaluation-text").val("");
						$(".my-evaluation-box").show();
						$(".my-evaluation-box").find("ul").prepend('<li class="clearFix">\
										<div class="commen_score_evaluateName">\
											<img src="' + $("#user-name .user-img").attr("src") + '" alt="">\
											<span class="restrictTextLength">' + $("#userName").text() + '</span>\
											<p><span style="width:' + parseFloat(evaCore / 3) * 100 + '%;"></span></p>\
											<span class="rt color9">' + that.timeChange(Date.parse(new Date())) + '</span>\
										</div>\
										<div class="commen_score_evaluateText">\
											<div class="commen_list">' + evaluationText + '</div>\
										</div>\
									</li>');
						//提示
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
		},
		scrollAuto: function scrollAuto() {
			var moveTag = false; //是否在滑块按下
			var startMove = 0; //开始移动距离
			var move = 0; //移动距离
			var topOrBottom = 0; //判断向上向下
			var scrollTop = 0; //滑块top值
			var listScrollTop = 0; //列表滑块top
			var scrollHeight = $('.scroll').height() - $('.scroll p').height();
			var listHeight = $('.add-course-box').height() - $('.scroll_scoped').height();
			if (listHeight < 0) {
				//太少，不出现滚动条
				$('.scroll').hide();
				return;
			} else {
				$('.scroll').show();
			}
			$('.scroll p').on('mousedown', function (event) {
				//是否按下鼠标
				moveTag = true;
				startMove = event.pageY;
			});
			$('document,body').on('mouseup', function () {
				moveTag = false;
				startMove = event.pageY;
			});
			$('#videoStudy').on('mousemove', function (event) {
				if (moveTag) {
					move = event.pageY;
					topOrBottom = move - startMove;
					startMove = move;
					scrollTop = Math.abs($('.scroll p').position().top);
					listScrollTop = parseInt($('.add-course-box').css('marginTop'));
					if (topOrBottom > 0) {
						//向下
						if (scrollTop < scrollHeight) {
							//滑块未到底部
							$('.scroll p').css('top', scrollTop + topOrBottom);
						}
					} else {
						//向上
						if (scrollTop > 0) {
							//滑块未到底部
							$('.scroll p').css('top', scrollTop + topOrBottom);
						}
					};
					// 移动列表
					$('.add-course-box').css('marginTop', Math.round(-listHeight / scrollHeight * (scrollTop + topOrBottom)) - 5 + 'px');
				}
			});
		},
		//点击播放状态
		toLive: function toLive() {
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
		},
		//判断当前课时 是否是免费试听课
        isFreeLesson : function isFreeLesson(){
            var isFreeLesson = $("input[name='isFreeLesson']").val();
            //是 试听课
            if(isFreeLesson != undefined && isFreeLesson == 1){
                return true;
            }
            return false;
        }
	};
	window.playerCallBack = pcb;
	function pcb(type, data) {
		//改成用结束时间来判断
		switch (type) {
			case "videoResume":
				console.log("恢复");
				courseStudy.startPlay($("input[name='totalSeconds']").val());
				setTime = setInterval(function () {
					courseStudy.currTime($("input[name='totalSeconds']").val(), window.player.sdk.getVideoTime());
				}, 3000);

				break;
			case "videoStop":
				console.log("停止");
				courseStudy.stopPlay(window.player.sdk.getVideoTime(),$("input[name='totalSeconds']").val());
				window.clearInterval(setTime);
				break;
			case "videoPause":
				console.log("停止");
				courseStudy.stopPlay(window.player.sdk.getVideoTime(),$("input[name='totalSeconds']").val());
				window.clearInterval(setTime);
				break;
			case "videoStart":
				console.log("考试 ");
				courseStudy.startPlay($("input[name='totalSeconds']").val());
				break;
			default:
		}
	}
	courseStudy.init();
});