package com.bluesky.zhz.core.bean.base;

import com.bluesky.zhz.core.enums.CommonEnum;
import com.bluesky.zhz.core.exception.BusinessException;
import com.bluesky.zhz.core.exception.MsgException;
import com.bluesky.zhz.core.response.ApiResponse;
import com.bluesky.zhz.core.response.JsonResult;
import com.bluesky.zhz.core.response.WXApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 基础控制器抽象类
 * 
 * <ul>
 * <li>统一异常处理
 * <li>统一数据返回格式
 * </ul>
 *
 */
@Controller
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected static final String RETURNMESSAGE = "返回结果 -- code:000000(成功),code:100000(失败),code:200000(非法参数,code:400000)";
	private static final String businessException = "com.zhongda.yunxiao.core.exception.BusinessException:";

	/**
	 * 异常统一处理。
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler
	@ResponseBody
	public Object handleException(HttpServletRequest request, Model model, HttpServletResponse response, Exception ex) {
		// root cause
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		// 用户未登录处理异常
		Throwable handleEx = rootCause != null ? rootCause : ex;

		if (handleEx instanceof MsgException) {
			String message = handleEx.getLocalizedMessage();
			logger.error(handleEx.getMessage(), handleEx);
			return error(message);
		} 
		
		// 日志记录，业务异常日志警告
		if (handleEx instanceof BusinessException) {
			String errCode = ((BusinessException) handleEx).getErrorCode();
			String errMsg = ((BusinessException) handleEx).getErrorMsg();
			logger.warn(errMsg);
			return new ApiResponse(errCode, errMsg);
		}

		if (handleEx instanceof ExpiredJwtException) {
			return new ApiResponse(CommonEnum.ResponseStatus.TOKEN_ERROR_REPLACE.getCode(), "当前登录已过期,请重新登录");
		}
		
		// 验证框架捕获的异常
		if (handleEx instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) handleEx;
			BindingResult bindingResult = exception.getBindingResult();
			StringBuilder sb = new StringBuilder();
			if (bindingResult.hasErrors()) {
				List<ObjectError> errors = bindingResult.getAllErrors();
				for (ObjectError err : errors) {
					sb.append(err.getDefaultMessage());
					break;
				}
				return new ApiResponse(CommonEnum.ResponseStatus.PARAM_ERROR_ILLEGAL.getCode(), sb.toString());
			}
		} else {
			// 非业务异常报警处理
			logger.error(handleEx.getMessage(), handleEx);
		}

		// 运行时异常
		if (handleEx instanceof RuntimeException) {
			String message = handleEx.getLocalizedMessage();
			if(message != null && message.startsWith(businessException)) {
				String[] split = message.split(businessException);
				if(split.length>1) {
					logger.error(handleEx.getMessage(), handleEx);
					return error(split[1].replace("\r\n", "").replace("\n", ""));
				}
			}
			logger.error(handleEx.getMessage(), handleEx);
			//return error("系统异常");
			return error(model,"系统异常");
		}

		// 统一错误返回格式
		return new ApiResponse(CommonEnum.ResponseStatus.UNKOWN_ERROR);
	}

	public static <T> ApiResponse<T> error(JsonResult jsonResult) {
		return new ApiResponse<T>(CommonEnum.ResponseStatus.ERROR.getCode(),jsonResult.getMessage(),(T)jsonResult.getData());
	}

	/**
	 * 处理误码消息的返回结果
	 * @param message
	 * @param <T>
	 * @return
	 */
	protected <T> ApiResponse<T> error(String message) {
		return ApiResponse.error(message);
	}

	/**
	 * 请求失败返回体
	 * 
	 */
//	protected ApiResponse failed() {
//		return new ApiResponse(ERROR);
//	}

	/**
	 * 升级服务
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected ApiResponse needupdate() {
		return new ApiResponse(CommonEnum.ResponseStatus.UPDATE_WARN);
	}

	/**
	 * 请求参数错误
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected ApiResponse errorparam() {
		return new ApiResponse(CommonEnum.ResponseStatus.PARAM_ERROR_ILLEGAL);
	}
	
	/**
	 * 空数据
	 * @return
	 */
//	protected ApiResponse empty() {
//		return new ApiResponse(NORESULT);
//	}

	/**
	 * 自定义错误
	 * @param errMessage
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected ApiResponse customFailed(String errMessage) {
		return new ApiResponse(CommonEnum.ResponseStatus.USER_SHOW_ERROR.getCode(),errMessage);
	}

	protected ModelAndView error(Model model){
		Map modelMap = model.asMap();
		String errMsg = (String) modelMap.get("errorMessage");
		return error(model,errMsg);
	}

	protected ModelAndView error(Model model, String errMsg){
		String alertString = "<script language='javascript'>layer.alert('"+errMsg+"', function(index){ history.back()})</script>";
		model.addAttribute("errorMessage",alertString);
		return new ModelAndView("error","errorModel",model);
	}

	/**
	 * 请求成功返回体
	 * @param data
	 */
	protected <T> ApiResponse<T> success(T data) {
		return new ApiResponse<T>(CommonEnum.ResponseStatus.SUCCESS.getCode(), "", data);
	}

	protected <T> ApiResponse<T> success(JsonResult jsonResult){
		return new ApiResponse<T>(jsonResult.getCode().getCode(),jsonResult.getMessage(), (T) jsonResult.getData());
	}
	
	protected <T> ApiResponse<T> success() {
		return new ApiResponse<T>(CommonEnum.ResponseStatus.SUCCESS.getCode(), "", null);
	}
	
//	protected <T> ApiResponse<T> success(String msg) {
//		return new ApiResponse<T>(SUCCESS.getCode(), msg, null);
//	}

	/**
	 * 创建成功时返回结果对象
	 *
	 * @param data
	 * @return ApiResponse<T>
	 */
	protected <T> WXApiResponse<T> successWx(T data) {
		return WXApiResponse.success(data);
	}
	/**
	 * 处理误码消息的返回结果
	 *
	 * @param message
	 * @return ApiResponse<T>
	 */
	protected <T> WXApiResponse<T> errorWx(String message) {
		return WXApiResponse.error(message);
	}

	/**
	 * 未登录
	 * @return
	 */
//	protected ApiResponse unlogin() {
//		return new ApiResponse(UNLOGIN);
//	}

    /**
	 * 登录状态返回体
	 *
	 */
//	protected ApiResponse loginInfo(StatusCode.LoginStatus loginStatus){
//		return new ApiResponse(loginStatus);
//	}

	/**
	 * 参数校验
	 */
//	protected  ApiResponse checkingParameter(StatusCode.Http http){
//		return new ApiResponse(http);
//	}
	/**
	 * 获取当前HttpServletRequest 
	 */
//	protected HttpServletRequest getRequest() {
//		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//	}

	/**
	 * 处理指定异常的返回结果
	 *
	 * @param e
	 * @return ApiResponse<T>
	 */
	protected <T> ApiResponse<T> error(Throwable e) {
		return ApiResponse.error(e);
	}

	/**
	 * 记录info级别的日志
	 * 
	 * @param message
	 */
	protected void infoLog(String message) {
		logger.info(loggerMessageFormat(message));
	}

	/**
	 * 记录info级别的日志，并且记录参数
	 * 
	 * @param message
	 * @param paramsJson
	 */
	protected void infoLog(String message, String paramsJson) {
		logger.info(loggerMessageFormat(message, paramsJson));
	}

	/**
	 * 记录warn级别日志
	 * 
	 * @param message
	 */
	protected void warnLog(String message) {
		logger.warn(loggerMessageFormat(message));
	}

	/**
	 * 记录warn级别日志，并且记录参数
	 * 
	 * @param message
	 * @param paramsJson
	 */
	protected void warnLog(String message, String paramsJson) {
		logger.warn(loggerMessageFormat(message, paramsJson));
	}

	/**
	 * 记录warn级别日志，并且记录参数
	 * 
	 * @param message
	 * @param paramsJson
	 * @param e
	 */
	protected void warnLog(String message, String paramsJson, Throwable e) {
		logger.warn(loggerMessageFormat(message, paramsJson), e);
	}

	/**
	 * 记录error级别日志
	 * 
	 * @param message
	 */
	protected void errorLog(String message) {
		logger.error(loggerMessageFormat(message));
	}

	/**
	 * 记录error级别日志，并且记录参数
	 * 
	 * @param message
	 * @param paramsJson
	 */
	protected void errorLog(String message, String paramsJson) {
		logger.error(loggerMessageFormat(message, paramsJson));
	}

	/**
	 * 记录error级别日志，并且记录参数
	 * 
	 * @param message
	 * @param paramsJson
	 * @param e
	 */
	protected void errorLog(String message, String paramsJson, Throwable e) {
		logger.error(loggerMessageFormat(message, paramsJson), e);
	}

	private String loggerMessageFormat(String message) {
		return "message:'" + message + "'";
	}

	private String loggerMessageFormat(String message, String paramsJson) {
		return "message:'" + message + "',params:'" + paramsJson + "'";
	}
}