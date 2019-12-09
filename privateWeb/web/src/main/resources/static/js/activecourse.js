'use strict';

var activeCourseObj = {
	init: function init() {
		this.listFn(1); //获取列表
		this.bindEvent();
	},
	firstEnter: true,
	pageList: function pageList(count) {
		var that = this;
		if (count > 10) {
			var laypage = layui.laypage;
			laypage.render({
				elem: "page",
				count: count,
				groups: 4,
				curr: 1,
				limit: 1,
				jump: function jump(obj, first) {
					if (!first) {
						that.listFn(obj.curr);
					}
				}
			});
		}
	},
	listFn: function listFn(curr) {
		var that = this,
		    ajaxObj = {
			page: curr,
			rows: 10
		};
		var strHtml='<div class="no-date-box ver-middle">\
					<p class="tc no-data">\
						<img class="no-data-img" src="../../assets/no_data_img.png" data-bd-imgshare-binded="1">\
					</p>\
					<p class="tc no-data-text">抱歉，暂无相关内容</p>\
				</div>';
		RC.ajax('post', '/myCenter/activationCode/courseActivationList', true, ajaxObj, function (data) {
			var str = ' <tr class="table-row">\n\t\t\t\t\t\t<th class="table-col">\u6FC0\u6D3B\u7801\u7F16\u53F7</th>\n\t\t\t\t\t\t<th class="table-col">\u5546\u54C1\u540D\u79F0</th>\n\t\t\t\t\t\t<th class="table-col">\u4EF7\u683C</th>\n\t\t\t\t\t\t<th class="table-col">\u53D1\u653E\u65F6\u95F4</th>\n\t\t\t\t\t\t<th class="table-col">\u64CD\u4F5C</th>\n\t\t\t\t\t</tr>';
			if (data.code == '000000') {
				if (data.data.rows && data.data.rows.length > 0) {
					var rowsLength = data.data.records;
					if (that.firstEnter) {
						that.pageList(rowsLength);
						that.firstEnter = false;
					}
					data.data.rows.forEach(function (item, index) {
						str += '<tr class="table-row">\n\t\t\t\t\t\t\t<td class="table-col" title="10148">' + (index + 1) + '</td>\n\t\t\t\t\t\t\t<td class="table-col" title="' + item.titles + '">' + item.titles + '</td>\n\t\t\t\t\t\t\t<td class="table-col" title="' + item.actualPrice + '">' + item.actualPrice + '</td>\n\t\t\t\t\t\t\t<td class="table-col" title="' + item.creationTimeStr + '">' + item.creationTimeStr + '</td>\n\t\t\t\t\t\t\t<td class="table-col">\n\t\t\t\t\t\t\t\t<span class="table-btn ' + (item.status == 1 ? 'disable-btn' : 'active-btn') + '" data-code="' + item.code + '" data-title="' + item.titles + '">' + (item.status == 1 ? "已激活" : "激活") + '</span>\n\t\t\t\t\t\t\t</td>\n\t\t\t\t\t\t</tr>';
					});
					$("#table-list").find(".table-content tbody").html(str);
				}else{
					$("#table-list").html(strHtml);
				}

				
			} else {
				$("#table-list").html(strHtml);
			}
		});
	},
	bindEvent: function bindEvent() {
		$(document).on("click", ".active-btn", function (e) {
			var obj = $(this);
			var ajaxObj = {
				code: $(this).attr("data-code"),
				title: $(this).attr("data-title")
			};
			RC.ajax('post', '/myCenter/activationCode/clickActivation', true, ajaxObj, function (data) {
				if (data.code == '000000') {
					if (data.data) {
						$(obj).addClass("disable-btn");
						$(obj).removeClass("active-btn");
						$(obj).html("已激活");
					}
				} else {
					layer.msg(data.message, { icon: 5, anim: 6 });
				}
			});
		});
	}
};
$(function () {
	activeCourseObj.init();
});