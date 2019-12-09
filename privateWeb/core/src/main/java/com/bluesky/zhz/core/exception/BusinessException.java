package com.bluesky.zhz.core.exception;

import static com.bluesky.zhz.core.enums.CommonEnum.ResponseStatus.ERROR;
import com.bluesky.zhz.core.enums.CommonEnum.ResponseStatus;

/**
 * 自定义异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2245683116636678073L;

    private String errorCode;
    private String errorMsg;

    /**
     * 定义无参构造方法
     */
    public BusinessException() {
        super();
    }

    /**
     * 只定义消息内容，返回码默认为"100000"
     * @param errorMsg
     */
    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorCode = ERROR.getCode();
        this.errorMsg = errorMsg;
    }

    /**
     * 自定义返回码和消息
     * @param errorCode
     * @param errorMsg
     */
    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 从定义返回枚举中获取返回内容
     * @param returnStatus
     */
    public BusinessException(ResponseStatus returnStatus) {
        super(returnStatus.getComment());
        this.errorCode = returnStatus.getCode();
        this.errorMsg = returnStatus.getComment();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
