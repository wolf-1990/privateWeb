"use strict";
function querySecondList(that){
    var sysClassId = $(that).attr("id");
    $.ajax({
        url:"/index/getChildSysClass",
        data:{"sysClassId":sysClassId},
        type:"post",
        dataType: "json",
        success:function(data){
            $("#secondClassDiv").empty();
            var divHtml = "<a href=\"javascript:void(0)\" onclick=\"queryCourseList(2,2,this)\" class=\"active\">全部</a>";
            console.log(data);
            data.forEach(function(item,index){
                var flag = item.selectTag;
                divHtml +="<span id=\""+item.subjectId+"\"\n" +
                    "onclick=\"queryCourseList(2,2,this)\"\n" +
                    " text=''>"+item.subjectName+"</span>";
            });
            $("#secondClassDiv").html(divHtml);
        },
        error:function(){
            alert("请求失败");
        }
    });
}


$(function () {
    var indexObj = {
        init: function init() {
            this.banner();
            if ($(".scrolling_annunciate").attr("data-show") == "1") {
                $(".scrolling_annunciate").show();
                this.scroll($(".scrolling_annunciate").attr("data-mess"));
            }
            //this.serachConditionJump();
        },
        scroll: function scroll(info) {
            var marginLeft,
                scrollTime,
                strLi = '';
            for (var i = 0; i < 5; i++) {
                strLi += '<li><img style="width:20px;margin-top:-2px;margin-right:5px;" src="./static/images/notice.png" alt="">  ' + info + '  </li>';
            }
            $('.scrolling_scope ul').html(strLi);
            marginLeft = parseInt($('.scrolling_annunciate .scrolling_ani').css('marginLeft'));
            scrollTime = setTimeout(scrolling, 50);

            function scrolling() {
                if ($('.scrolling_annunciate .scrolling_scope').width() + marginLeft < $(window).width() - 100) {
                    var clone = $('.scrolling_annunciate ul').first().clone();
                    $('.scrolling_annunciate .scrolling_scope').append(clone);
                }
                marginLeft = marginLeft - 1;
                $('.scrolling_annunciate .scrolling_ani').css('marginLeft', marginLeft);
                scrollTime = setTimeout(scrolling, 16);
            }
        },
        banner: function banner() {
            //首页banner
            var ban = $('#banner>div:first div a');
            var bannerBgArr = [];
            $(ban).each(function (index, item) {
                var banBg = $(item).attr('data-bannerbg');
                bannerBgArr.push(banBg);
            });
            $('.banner-box').css({'background': bannerBgArr[0]});
            var carousel = layui.carousel;
            carousel.render({
                elem: '#banner',
                width: '1200px',
                height: '100%',
                arrow: 'hover' //始终显示箭头
                , anim: 'fade' //切换动画方式
                , interval: 5000
            });
            // 轮播切换事件
            carousel.on('change(banner)', function (obj) {
                $('.banner-box').css({'background': bannerBgArr[obj.index], 'opacity': 0.1}).animate({
                    'opacity': '1'
                }, 800);
            });
        },
        scrollingAnnunciate: function scrollingAnnunciate() {
            //滚屏通知
            var marginLeft,
                scrollTime,
                strLi = '';
        }
    }
});

(function ($) {
    var courseContentdom = $('.course_center_rt').eq(0);
    var courseHeight = courseContentdom.css('height', 'auto').height();
    courseContentdom.css('height', '30px');
    if (courseHeight > 30) {
        $('.open-btn-wap').css('visibility', 'visible');
    }
    $('.open-btn-wap').click(function (e) {
        if ($(this).find('.down-ico').length > 0) {
            //如果点击展开操作
            var courseContentHeight = courseContentdom.css('height', 'auto').height();
            courseContentdom.css('height', '30px');
            courseContentdom.animate({ 'height': courseContentHeight });
            $('.open-btn-wap').html('<span class="bg-ico up-ico"></span>收起');
        } else {
            courseContentdom.animate({ 'height': '30px' });
            $('.open-btn-wap').html('<span class="bg-ico down-ico"></span>展开');
        }

    });
    indexObj.init();

});
function queryCourseList2(type,value){
    if(type =='sort'){
        $("#sort").val(value);
    }else if(type =='type'){
        $("#typeTag").val(value);
    }
    // $("#sortForm").submit();
    $("#courseForm").submit();
}