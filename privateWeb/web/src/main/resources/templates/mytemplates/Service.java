package com.zhongda.crm.service;

import com.github.pagehelper.PageInfo;
import com.zhongda.crm.pojo.dto.${entity}DTO;
import com.zhongda.crm.pojo.entity.${entity};
import com.zhongda.kernel.service.BaseService;

import java.util.List;


public interface ${entity}Service extends BaseService<${entity}> {

    /**
     *@Description 公共池展示规则分页列表
     *@author 赵赫智
     *@date 2019/10/29 15:15
     *@param ${entityDown}BO
     *@return com.github.pagehelper.PageInfo<com.zhongda.crm.pojo.dto.${entity}DTO>
    **/
    PageInfo<${entity}DTO> listPublicPoolShowPageInfo(com.zhongda.crm.pojo.bo.${entity}BO ${entityDown}BO);
    
    /**
     *@Description 更新公共池展示规则
     *@author 赵赫智
     *@date 2019/10/29 15:18
     *@param ${entityDown}UpdateBO
     *@return java.lang.Boolean
    **/
    Boolean update${entity}(com.zhongda.crm.pojo.bo.${entity}UpdateBO ${entityDown}UpdateBO);
    
    /**
     *@Description  获取单个公共池展示规则对象
     *@author 赵赫智
     *@date 2019/10/29 15:21
     *@param ${entityDown}Id
     *@return ${entity}OneDTO
    **/
    com.zhongda.crm.pojo.dto.${entity}OneDTO get${entity}One(Integer ${entityDown}Id);

    /**
     *@Description  删除公共池展示规则对象
     *@author 赵赫智
     *@date 2019/10/29 15:21
     *@param ${entityDown}Ids
     *@return ${entity}OneDTO
     **/
    Boolean delete${entity}One(List<Integer> ${entityDown}Ids, String userId, String userName);
}
