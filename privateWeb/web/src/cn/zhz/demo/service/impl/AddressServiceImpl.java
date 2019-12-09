package com.zhongda.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongda.admin.service.AdminRoleService;
import com.zhongda.admin.service.AdminService;
import com.zhongda.crm.dao.ConfigurationHistoryMapper;
import com.zhongda.crm.pojo.bo.PoolShowRuleSourceBO;
import com.zhongda.crm.pojo.dto.AddressDTO;
import com.zhongda.crm.pojo.entity.ConfigurationHistory;
import com.zhongda.crm.pojo.entity.Address;
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
 * @Author 赵赫智
 * @Date 2019/10/29 15:05
 **/
@Transactional
@Service
public class AddressServiceImpl implements com.zhongda.crm.service.AddressService {

    @Autowired
    private com.zhongda.crm.dao.AddressMapper publicPoolShowRuleMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private ConfigurationHistoryMapper configurationHistoryMapper;
    @Override
    public BaseMapper<Address> getMapper() {
        return publicPoolShowRuleMapper;
    }
    
    /**
     *@Description 分页展示地址列表
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param publicPoolShowRuleBO
     *@return com.github.pagehelper.PageInfo<com.zhongda.crm.pojo.dto.AddressDTO>
    **/
    @Override
    public PageInfo<AddressDTO> listPublicPoolShowPageInfo(com.zhongda.crm.pojo.bo.AddressBO publicPoolShowRuleBO) {
        PageHelper.startPage(publicPoolShowRuleBO.getPageNum(),publicPoolShowRuleBO.getPageSize());
        List<Address> list = publicPoolShowRuleMapper.selectByObj(publicPoolShowRuleBO);
        List<AddressDTO> resultList = new ArrayList<>();
        return new PageInfo<>(resultList);
    }

    /**
     *@Description 更新地址
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param publicPoolShowRuleUpdateBO
     *@return java.lang.Boolean
     **/
    @Override
    @Transactional("crmDataSourceTransactionManager")
    public Boolean updateAddress(com.zhongda.crm.pojo.bo.AddressUpdateBO publicPoolShowRuleUpdateBO) {

        return true;
    }
    /**
     *@Description 
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
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
            configurationHistory.setContent("新增地址");
        }else if(tag == 3){
            configurationHistory.setOperationType(StatusCode.OperateType.DELETE.getCode());
            configurationHistory.setContent("删除地址");
        }
        configurationHistoryMapper.insertSelective(configurationHistory);
    }

    /**
     *@Description 查询单个地址
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param publicPoolShowRuleId
     *@return com.zhongda.crm.pojo.dto.AddressOneDTO
    **/
    @Override
    public com.zhongda.crm.pojo.dto.AddressOneDTO getAddressOne(Integer publicPoolShowRuleId) {

        return publicPoolShowRuleOneDTO;
    }
    
    /**
     *@Description 删除地址对象
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param publicPoolShowRuleIds 
     *@return java.lang.Boolean
    **/
    @Override
    public Boolean deleteAddressOne(List<Integer> publicPoolShowRuleIds,String userId,String userName) {
        //删除地址
        publicPoolShowRuleMapper.deleteBatch(publicPoolShowRuleIds);
        //操作日志记录
        ConfigurationHistory configurationHistory = new ConfigurationHistory();
        configurationHistory.setCreatorName(userName);
        configurationHistory.setCreatorId(userId);
        configurationHistory.setCreationTime(LocalDateTime.now());
//        configurationHistory.setBizType(7);
//        configurationHistory.setOperationType(StatusCode.OperateType.DELETE.getCode());
//        configurationHistory.setContent("删除地址");
        configurationHistoryMapper.insertBatch(configurationHistory,publicPoolShowRuleIds);
        return true;
    }
}
