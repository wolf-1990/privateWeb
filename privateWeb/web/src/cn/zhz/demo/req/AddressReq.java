package com.zhongda.vo.req.baseconf;

import com.zhongda.kernel.interactivehttp.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description TOO 地址入参
 * @Author 赵赫智
 * @Date 2019-11-04T10:27:16.889
 **/
@Data
@ApiModel("地址查询入参")
public class AddressReq extends PageRequest{

}
