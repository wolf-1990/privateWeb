package com.zhongda.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongda.admin.service.AdminRoleService;
import com.zhongda.admin.service.AdminService;
import com.zhongda.crm.dao.ConfigurationHistoryMapper;
import com.zhongda.crm.pojo.bo.PoolShowRuleSourceBO;
import com.zhongda.crm.pojo.dto.${entity}DTO;
import com.zhongda.crm.pojo.entity.ConfigurationHistory;
import com.zhongda.crm.pojo.entity.${entity};
import com.zhongda.kernel.dao.BaseMapper;
import com.zhongda.util.JsonUtils;
import com.zhongda.util.enums.StatusCode;
import com.zhongda.util.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TOO
 * @Author ${author}
 * @Date 2019/10/29 15:05
 **/
@Transactional
@Service
public class ${entity}ServiceImpl implements com.zhongda.crm.service.${entity}Service {

    @Autowired
    private com.zhongda.crm.dao.${entity}Mapper publicPoolShowRuleMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private ConfigurationHistoryMapper configurationHistoryMapper;
    @Override
    public BaseMapper<${entity}> getMapper() {
        return publicPoolShowRuleMapper;
    }
    
    /**
     *@Description 分页展示${entityName}列表
     *@author ${author}
     *@date ${date}
     *@param publicPoolShowRuleBO
     *@return com.github.pagehelper.PageInfo<com.zhongda.crm.pojo.dto.${entity}DTO>
    **/
    @Override
    public PageInfo<${entity}DTO> listPublicPoolShowPageInfo(com.zhongda.crm.pojo.bo.${entity}BO publicPoolShowRuleBO) {
        PageHelper.startPage(publicPoolShowRuleBO.getPageNum(),publicPoolShowRuleBO.getPageSize());
        List<${entity}> list = publicPoolShowRuleMapper.selectByObj(publicPoolShowRuleBO);
        List<${entity}DTO> resultList = new ArrayList<>();
        return new PageInfo<>(resultList);
    }

    /**
     *@Description 更新${entityName}
     *@author ${author}
     *@date ${date}
     *@param publicPoolShowRuleUpdateBO
     *@return java.lang.Boolean
     **/
    @Override
    @Transactional("crmDataSourceTransactionManager")
    public Boolean update${entity}(com.zhongda.crm.pojo.bo.${entity}UpdateBO publicPoolShowRuleUpdateBO) {

        return true;
    }
    /**
     *@Description 
     *@author ${author}
     *@date ${date}
     *@param tag 1 新增 2 修改 3 删除
     *@parama dminName 操作人userName
     *@param adminId 操作人Id
     *@return void
    **/
    public void updateOperation(Integer tag,Integer id, String adminName, String adminId) {
        //操作日志记录
        ConfigurationHistory configurationHistory = new ConfigurationHistory();
        configurationHistory.setCreatorName(adminName);
        configurationHistory.setCreatorId(adminId);
        configurationHistory.setCreationTime(LocalDateTime.now());
        configurationHistory.setBizId(id);
        configurationHistory.setBizType(7);
        if(tag == 2){
            configurationHistory.setOperationType(StatusCode.OperateType.UPDATE.getCode());
            configurationHistory.setContent("修改公共池展示规则");
        }else if(tag == 1){
            configurationHistory.setOperationType(StatusCode.OperateType.ADD.getCode());
            configurationHistory.setContent("新增${entityName}");
        }else if(tag == 3){
            configurationHistory.setOperationType(StatusCode.OperateType.DELETE.getCode());
            configurationHistory.setContent("删除${entityName}");
        }
        configurationHistoryMapper.insertSelective(configurationHistory);
    }

    /**
     *@Description 查询单个${entityName}
     *@author ${author}
     *@date ${date}
     *@param publicPoolShowRuleId
     *@return com.zhongda.crm.pojo.dto.${entity}OneDTO
    **/
    @Override
    public com.zhongda.crm.pojo.dto.${entity}OneDTO get${entity}One(Integer publicPoolShowRuleId) {

        return publicPoolShowRuleOneDTO;
    }
    
    /**
     *@Description 删除${entityName}对象
     *@author ${author}
     *@date ${date}
     *@param publicPoolShowRuleIds 
     *@return java.lang.Boolean
    **/
    @Override
    public Boolean delete${entity}One(List<Integer> publicPoolShowRuleIds,String userId,String userName) {
        //删除${entityName}
        publicPoolShowRuleMapper.deleteBatch(publicPoolShowRuleIds);
        //操作日志记录
        ConfigurationHistory configurationHistory = new ConfigurationHistory();
        configurationHistory.setCreatorName(userName);
        configurationHistory.setCreatorId(userId);
        configurationHistory.setCreationTime(LocalDateTime.now());
//        configurationHistory.setBizType(7);
//        configurationHistory.setOperationType(StatusCode.OperateType.DELETE.getCode());
//        configurationHistory.setContent("删除${entityName}");
        configurationHistoryMapper.insertBatch(configurationHistory,publicPoolShowRuleIds);
        return true;
    }
}
