package com.zhongda.crm.dao;

import com.zhongda.crm.pojo.bo.PublicPoolShowRuleBO;
import com.zhongda.crm.pojo.entity.PublicPoolShowRule;
import com.zhongda.kernel.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公共池展示规则
 * @Auther zhz
 * @CreateTime 2019.10.29
 * @Description
 */
public interface ${entity}Mapper extends BaseMapper<${entity}> {

    /**
     *@Description 批量删除公共池展示规则
     *@author ${author}
     *@date ${date}
     *@param ${authorDown}Ids
     *@return void
    **/
    void deleteBatch(@Param("params") List<Integer> ${authorDown}Ids);
    
    /**
     *@Description 通过对象查询对象列表
     *@author ${author}
     *@date ${date}
     *@param ${authorDown}BO
     *@return java.util.List<com.zhongda.crm.pojo.dto.${entity}DTO>
    **/
    List<${entity}> selectByObj(@Param("param") ${entity}BO ${authorDown}BO);
    
    /**
     *@Description 查询非自身
     *@author ${author}
     *@date ${date}
     *@param pprParam
     *@return java.util.List<com.zhongda.crm.pojo.entity.${entity}>
    **/
    List<${entity}> selectNoSelf(@Param("param") ${entity} pprParam);
}
