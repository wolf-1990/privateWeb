package com.bluesky.zhz.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.bluesky.zhz.core.enums.CommonEnum.ResponseStatus;
import com.bluesky.zhz.core.exception.BusinessException;

import static com.bluesky.zhz.core.enums.CommonEnum.ResponseStatus.*;

import java.io.Serializable;


/**
 * 返回对象
 * @param <T>
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = -7492259026771402280L;

	// 返回码
	@JsonProperty(value="code")
	private String code;
	// 返回信息
	@JsonProperty(value="message")
	private String message;
	// 返回数据
	@JsonProperty(value="data")
	private T data;

	/**
	 *
	 * @param code
	 * @param message
	 * @param data
	 */
	public ApiResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 *
	 * @param code
	 * @param message
	 */
	public ApiResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 *
	 * @param message
	 */
	public ApiResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * 重写JsonResult
	 * @param status
	 */
	public ApiResponse(ResponseStatus status) {
		this(status.getCode(), status.getComment());
	}

	/**
	 *
	 */
	public ApiResponse() {
		super();
	}

	/**
	 * 操作成功返回内容
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<T>(SUCCESS.getCode(), SUCCESS.getComment(), data);
	}

	/**
	 * 操作失败返回内容
	 * @param //data
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<T>(ERROR.getCode(), message, null);
	}

	/**
	 * 自定义失败内容，可提示给用户看
	 * @param //message
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> showError(ResponseStatus status) {
		return new ApiResponse<T>(status.getCode(), status.getComment(), null);
	}
	
	/**
	 * 重复请求
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> repeatRequest(String message) {
		return new ApiResponse<T>(REPEAT_REQUEST.getCode(), message, null);
	}

	/**
	 * token校验失败
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> validateTokenError(String message) {
		return new ApiResponse<T>(TOKEN_ERROR.getCode(), message, null);
	}

	/**
	 * 被踢掉
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> tokenReplaceError(String message) {
		return new ApiResponse<T>(TOKEN_ERROR_REPLACE.getCode(), message, null);
	}

	/**
	 * 异常返回
	 * @param e
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> error(Throwable e) {
		// 返回自定义异常
		if (e instanceof BusinessException) {
			return new ApiResponse<T>(USER_SHOW_ERROR.getCode(), ((BusinessException) e).getMessage(), null);
		}

		// 返回系统异常
		return new ApiResponse<T>(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getComment(), null);
	}

	/**
	 * 无权限
	 * @param //message
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> noAuth() {
		return new ApiResponse<T>(NO_AUTH.getCode(), NO_AUTH.getComment(), null);
	}

	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	public String getCode() {
		return code;
	}

}
