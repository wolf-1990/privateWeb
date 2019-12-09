package com.bluesky.zhz.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bluesky.zhz.core.constants.Constant;
import com.bluesky.zhz.core.exception.BusinessException;

import java.io.Serializable;

/**
 * 定义RESTful服务数据返回格式
 * 
 */
public class WXApiResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功
	 */
	public static final String SUCCESS = "000000";

	/**
	 * 失败
	 */
	public static final String ERROR = "100000";

	/**
	 * 参数校验失败
	 */
	public static final String VALIDATE = "200000";

	/**
	 * 没有权限
	 */
	public static final String NOAUTH = "300000";
	
	/**
	 * 没有权限
	 */
	public static final String NOUSER = "310000";

	/**
	 * 数据绑定失败
	 */
	public static final String BingData = "400000";
	
	/**
	 * 版本错误
	 */
	public static final String VERSIONERROR = "500000";
	
	/**
	 * 加密
	 */
	public static final int DATATYPE_ENCRYPTED  = 1;
	
	/**
	 * 不加密
	 */
	public static final int DATATYPE_UNENCRYPTED  = 0;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "dataType")
	private Integer dataType;

	@JsonProperty(value = "message")
	private String message;

	@JsonProperty(value = "data")
	private T data;

	protected WXApiResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public WXApiResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public WXApiResponse(String code, String message, Integer dataType, T data) {
		super();
		this.code = code;
		this.dataType = dataType;
		this.message = message;
		this.data = data;
	}

	/**
	 * 创建请求成功时返回结果对象。
	 * 
	 * @return
	 */
	public static <T> WXApiResponse<T> success(T data) {
		return new WXApiResponse<T>(SUCCESS, "", data);
	}
	
	/**
	 * 创建没有用户时返回结果对象。
	 * @param data
	 * @return
	 */
	public static <T> WXApiResponse<T> noUser(T data) {
		return new WXApiResponse<T>(NOUSER,  "", DATATYPE_ENCRYPTED, data);
	}
	
	/**
	 * 创建版本错误时返回结果对象。
	 * @param data
	 * @return
	 */
	public static <T> WXApiResponse<T> versionError(T data) {
		return new WXApiResponse<T>(VERSIONERROR,  "", DATATYPE_ENCRYPTED, data);
	}

	public static <T> WXApiResponse<T> success2(String message, Integer dataType, T data2) {
		return new WXApiResponse<T>(SUCCESS, message, dataType, data2);
	}

	/**
	 * 处理指定异常的返回结果。
	 * 
	 * @param e
	 * @return
	 */
	public static <T> WXApiResponse<T> error(Throwable e) {
		// 处理业务异常
		if (e instanceof BusinessException) {
			String message = ((BusinessException) e).getMessage();
			if (Constant.NO_AUTH_MESSAGE.equals(message)) {
				return noAuthError(message);
			}
			return error(message);
		}
		return error(e.toString());
	}

	/**
	 * 处理指定异常的返回结果。
	 *
	 * @param message
	 * @return
	 */
	public static <T> WXApiResponse<T> errorBingData(String message) {

		return error(BingData, message, null);
	}

	/**
	 * 处理错误消息的返回结果
	 * 
	 * @param message
	 * @return
	 */
	public static <T> WXApiResponse<T> error(String message) {
		return error(ERROR, message, null);
	}

	public static <T> WXApiResponse<T> error2(String message) {
		return error2(ERROR, message);
	}

	/**
	 * 非法参数返回
	 */
	public static <T> WXApiResponse<T> validateError(String message) {
		return error(VALIDATE, message, null);
	}

	public static <T> WXApiResponse<T> noAuthError(String message) {
		return error(NOAUTH, message, null);
	}

	/**
	 * 错误消息及具体的数据
	 * 
	 * @param code
	 * @param message
	 * @param data
	 * @return ApiResponse<T>
	 */
	public static <T> WXApiResponse<T> error(String code, String message, T data) {
		return new WXApiResponse<T>(code, message, data);
	}

	@SuppressWarnings("unchecked")
	public static <T> WXApiResponse<T> error2(String code, String message) {
		return new WXApiResponse<T>(code, message, 0, (T) "");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
