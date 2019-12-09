package com.zhongda.vo.req.baseconf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description TOO 公共池展示规则
 * @Author 赵赫智
 * @Date 2019/10/29 14:45
 **/
@Data
@ApiModel("公共池展示规则更新入参")
public class PublicPoolShowRuleUpdateReq {
    @ApiModelProperty("ID")
    private Integer id;

    @NotNull(message = "请填写规则名称")
    @ApiModelProperty(value = "规则名称",example = "规则1")
    private String title;

    @NotNull(message = "请选择规则类型")
    @ApiModelProperty(value = "规则类型",example = "1")
    private Integer type;

    @NotNull(message = "请选择具体业务选项")
    @ApiModelProperty("该类型业务ID数组")
    private List<String> businessIds;

    @NotNull(message = "请选择资源类型")
    @ApiModelProperty("资源类型集合对象")
    private List<PoolShowRuleSourceReq> poolShowRuleSourceList;


}
