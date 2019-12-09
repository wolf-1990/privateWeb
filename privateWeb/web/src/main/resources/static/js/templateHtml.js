'use strict';

//<li class="bg-ico">手机快捷登录</li>\
var userRegisterHtml = '<div class="userRegister">\
                            <div class="layui-form">\
								<div class="layui-tab">\
								  <ul class="layui-tab-title">\
										<li class="bg-ico layui-this">账号登录</li>\
										<li class="bg-ico">手机快捷登录</li>\
								  </ul>\
								  <div class="layui-tab-content">\
								    <div class="layui-tab-item layui-show">\
								        <div class="user-name content-list">\
								        	<input class="layui-input" autocomplete="off" type="text" name="userName" id="login_userName" class="layui-input" lay-verify="userName"  placeholder="请输入用户名">\
								    	</div>\
								    	<div class="user-password content-list">\
												<input class="layui-input"  type="password"  class="layui-input"  id="login_password_hidden" lay-verify="userPwd"  placeholder="请输入密码">\
												<input  type="hidden"  name="userPwd" id="login_password"  value="">\
								    	</div>\
								    	<div class="password-opera content-list clearfix">\
										    	<div class="layui-input-block fl">\
										      		<input type="checkbox" name="like1[write]" lay-skin="primary" title="记住密码" checked="">\
										      	</div>\
														<a href="../login/goPwdResetPage" class="rt retrieve-password">找回密码</a>\
										</div>\
										<div class="content-list clearfix">\
												<button class="common-btn active loginBtn layui-btn" lay-submit="" id="loginBtnUserName" lay-filter="loginBtn">登录</button>\
										</div>\
										<div class="content-list clearfix">\
											<a class="common-btn registerBtn layui-btn" href="../login/goRegistryPage" lay-submit="" lay-filter="registerBtn">注册账号</a>\
										</div>\
									</div>\
									<div class="layui-tab-item">\
										<div class="user-phone content-list">\
								        	<input class="layui-input" type="text" name="userPhone" id="_sendMessagesPhone-box" lay-verify="userPhone" data-value="请输入手机号" value="请输入手机号">\
								    	</div>\
								    	<div class="user-phone content-list clearfix">\
								        	<input class="layui-input verification-code fl" type="text" name="verificationCode" id="login_verificationCode" lay-verify="verificationCode" data-value="请输入验证码" value="请输入验证码">\
											<input class="send-message rt" type="button" value="发送短信" id="send-message" lay-filter="sendMessages"/>\
										</div>\
										<div class="color9 content-list " >\
										<span class="layui-form-checkbox active userregister-box" lay-skin="primary">\
											<i class="layui-icon"></i>\
										</span>接受并同意相关<a href="protocol/1" target="_blank" class="color-theme">《用户服务条款》</a>\
									   </div>\
								    	<div style="height:13px;"></div>\
								    	<div class="content-list clearfix">\
												<button class="common-btn active loginBtn layui-btn" lay-submit="" id="phoneLoginBtn" lay-filter="phoneLoginBtn">登录</button>\
										</div>\
										<div class="content-list clearfix">\
										<a class="common-btn registerBtn layui-btn" href="../login/goRegistryPage" lay-submit="" lay-filter="registerBtn">注册账号</a>\
										</div>\
									</div>\
								  </div>\
								</div>\
                            </div>\
												</div>';
//<a href="../retrievepsd/retrievepsd.html" class="rt retrieve-password">找回密码</a>\
//<a class="common-btn registerBtn layui-btn no-open" href="../register/register.html" lay-submit="" lay-filter="registerBtn">注册账号</a>\