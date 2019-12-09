package com.zhongda.controller.baseconf;

import com.github.pagehelper.PageInfo;
import com.zhongda.config.ApiConstant;
import com.zhongda.crm.pojo.bo.${entity}BO;
import com.zhongda.crm.pojo.bo.${entity}UpdateBO;
import com.zhongda.crm.pojo.dto.${entity}DTO;
import com.zhongda.crm.pojo.dto.${entity}OneDTO;
import com.zhongda.crm.service.${entity}Service;
import com.zhongda.kernel.bean.IdIntegerBean;
import com.zhongda.kernel.controller.BaseController;
import com.zhongda.kernel.interactivehttp.PageData;
import com.zhongda.kernel.response.RespEntity;
import com.zhongda.kernel.response.RespUtil;
import com.zhongda.util.CurrentUser;
import com.zhongda.util.CurrentUserUtil;
import com.zhongda.vo.req.baseconf.${entity}Req;
import com.zhongda.vo.req.baseconf.${entity}UpdateReq;
import com.zhongda.vo.res.baseconf.${entity}OneRes;
import com.zhongda.vo.res.baseconf.${entity}Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = ${entityName}"接口", tags = {${entityName}"接口"})
@RestController
@Slf4j
@RequestMapping(${entityUrl})
public class ${entity}Controller extends BaseController {

    //private String domain = AppConfig.getConfig("agent.api.domain");
    @Autowired
    private ApiConstant apiConstant;
    @Autowired
    private ${entity}Service ${entityDown}Service;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     *@Description ${entityName}列表
     *@author ${author}
     *@date ${date}
     *@param
     *@return com.zhongda.kernel.response.RespEntity<?>
    **/
    @PostMapping("/list${entity}")
    @ApiOperation(value = "${entityName}列表", notes = "${entityName}列表")
    public RespEntity<?> list${entity} (@RequestBody ${entity}Req req) {
        ${entity}BO ${entityDown}BO = new ${entity}BO();
        BeanUtils.copyProperties(req,${entityDown}BO);
        PageInfo<${entity}DTO> pageInfo = ${entityDown}Service.list${entity}PageInfo(${entityDown}BO);

        PageData<${entity}Res> transForm = new PageData<>();
        List<${entity}Res> resultList = new ArrayList<>();
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS,
                transForm.transform(pageInfo, ${entity}Res.class, resultList));

    }

    /**
     *@Description 更新${entityName}
     *@author ${author}
     *@date ${date}
     *@param
     *@return com.zhongda.kernel.response.RespEntity<java.lang.Boolean>
    **/
    @PostMapping("/update${entity}")
    @ApiOperation(value = "更新${entityName}", notes = "更新${entityName}")
    public RespEntity<Boolean> update${entity}(@Validated @RequestBody ${entity}UpdateReq req) {
        update${entity}Common(req);
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, true);
    }

    public void update${entity}Common(@Validated @RequestBody ${entity}UpdateReq req) {
        ${entity}UpdateBO ${entityDown}UpdateBO = new ${entity}UpdateBO();
        CurrentUser currentUser =
                CurrentUserUtil.getCurrentUser(redisTemplate);
        String userName = currentUser.getUserName();
        ${entityDown}UpdateBO.setAdminName(userName);
        ${entityDown}UpdateBO.setAdminId(currentUser.getId());
        BeanUtils.copyProperties(req,${entityDown}UpdateBO);
        ${entityDown}Service.update${entity}(${entityDown}UpdateBO);
    }

    /**
     *@Description 新增${entityName}
     *@author ${author}
     *@date ${date}
     *@param
     *@return com.zhongda.kernel.response.RespEntity<java.lang.Boolean>
     **/
    @PostMapping("/insert${entity}")
    @ApiOperation(value = "新增${entityName}", notes = "新增${entityName}")
    public RespEntity<Boolean> insert${entity}(@Validated @RequestBody ${entity}UpdateReq req) {
        update${entity}Common(req);
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, true);
    }

    /**
     *@Description 查询${entityName}单个对象
     *@author ${author}
     *@date ${date}
     *@param
     *@return com.zhongda.kernel.response.RespEntity<?>
    **/
    @PostMapping("get${entity}")
    @ApiOperation(value = "根据主键查询${entityName}", notes = "根据主键查询${entityName}")
    public RespEntity<?> selectPackageType(@Validated @RequestBody IdIntegerBean req) {
        ${entity}OneRes ${entityDown}OneRes = new ${entity}OneRes();
        ${entity}OneDTO ${entityDown}One = ${entityDown}Service.get${entity}One(req.getId());
        BeanUtils.copyProperties(${entityDown}One,${entityDown}OneRes);
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, ${entityDown}OneRes);
    }

    /**
     *@Description 删除${entityName}单个对象
     *@author ${author}
     *@date ${date}
     *@param
     *@return com.zhongda.kernel.response.RespEntity<?>
     **/
    @PostMapping("delete${entity}")
    @ApiOperation(value = "根据主键ids 删除${entityName}", notes = "根据主键ids 删除${entityName}")
    public RespEntity<?> delete${entity}(@Validated @RequestBody List<Integer> req) {
        CurrentUser currentUser =
                CurrentUserUtil.getCurrentUser(redisTemplate);
        ${entityDown}Service.delete${entity}One(req,currentUser.getId(),currentUser.getUserName());
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, true);
    }


}
