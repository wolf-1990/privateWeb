package com.zhongda.vo.req.baseconf;

import com.zhongda.kernel.interactivehttp.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description TOO ${entityName}入参
 * @Author ${author}
 * @Date ${date}
 **/
@Data
@ApiModel("${entityName}查询入参")
public class ${entity}Req extends PageRequest{

}
