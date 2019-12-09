package com.zhongda.crm.service;

import com.github.pagehelper.PageInfo;
import com.zhongda.crm.pojo.dto.AddressDTO;
import com.zhongda.crm.pojo.entity.Address;
import com.zhongda.kernel.service.BaseService;

import java.util.List;


public interface AddressService extends BaseService<Address> {

    /**
     *@Description 公共池展示规则分页列表
     *@author 赵赫智
     *@date 2019/10/29 15:15
     *@param addressBO
     *@return com.github.pagehelper.PageInfo<com.zhongda.crm.pojo.dto.AddressDTO>
    **/
    PageInfo<AddressDTO> listPublicPoolShowPageInfo(com.zhongda.crm.pojo.bo.AddressBO addressBO);
    
    /**
     *@Description 更新公共池展示规则
     *@author 赵赫智
     *@date 2019/10/29 15:18
     *@param addressUpdateBO
     *@return java.lang.Boolean
    **/
    Boolean updateAddress(com.zhongda.crm.pojo.bo.AddressUpdateBO addressUpdateBO);
    
    /**
     *@Description  获取单个公共池展示规则对象
     *@author 赵赫智
     *@date 2019/10/29 15:21
     *@param addressId
     *@return AddressOneDTO
    **/
    com.zhongda.crm.pojo.dto.AddressOneDTO getAddressOne(Integer addressId);

    /**
     *@Description  删除公共池展示规则对象
     *@author 赵赫智
     *@date 2019/10/29 15:21
     *@param addressIds
     *@return AddressOneDTO
     **/
    Boolean deleteAddressOne(List<Integer> addressIds, String userId, String userName);
}
