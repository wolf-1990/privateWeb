package com.bluesky.zhz.core.response;

import lombok.Data;

import java.io.Serializable;

import static com.bluesky.zhz.core.enums.CommonEnum.ResponseStatus;
import static com.bluesky.zhz.core.enums.CommonEnum.ResponseStatus.SUCCESS;

@Data
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -7490942683077994257L;
    private ResponseStatus code;
    private String message;
    private Object data;

    public JsonResult(Object data){
        this(SUCCESS,"",data);
    }
    public JsonResult(ResponseStatus code,Object data){
        this(code,"",data);
    }
    public JsonResult(ResponseStatus code,String message){
        this(code,message,null);
    }
    public JsonResult(ResponseStatus code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

