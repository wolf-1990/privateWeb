package com.zhongda.controller.baseconf;

import com.github.pagehelper.PageInfo;
import com.zhongda.config.ApiConstant;
import com.zhongda.crm.pojo.bo.AddressBO;
import com.zhongda.crm.pojo.bo.AddressUpdateBO;
import com.zhongda.crm.pojo.dto.AddressDTO;
import com.zhongda.crm.pojo.dto.AddressOneDTO;
import com.zhongda.crm.service.AddressService;
import com.zhongda.kernel.bean.IdIntegerBean;
import com.zhongda.kernel.controller.BaseController;
import com.zhongda.kernel.interactivehttp.PageData;
import com.zhongda.kernel.response.RespEntity;
import com.zhongda.kernel.response.RespUtil;
import com.zhongda.util.CurrentUser;
import com.zhongda.util.CurrentUserUtil;
import com.zhongda.vo.req.baseconf.AddressReq;
import com.zhongda.vo.req.baseconf.AddressUpdateReq;
import com.zhongda.vo.res.baseconf.AddressOneRes;
import com.zhongda.vo.res.baseconf.AddressRes;
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

@Api(value = 地址"接口", tags = {地址"接口"})
@RestController
@Slf4j
@RequestMapping(${entityUrl})
public class AddressController extends BaseController {

    //private String domain = AppConfig.getConfig("agent.api.domain");
    @Autowired
    private ApiConstant apiConstant;
    @Autowired
    private AddressService addressService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     *@Description 地址列表
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param
     *@return com.zhongda.kernel.response.RespEntity<?>
    **/
    @PostMapping("/listAddress")
    @ApiOperation(value = "地址列表", notes = "地址列表")
    public RespEntity<?> listAddress (@RequestBody AddressReq req) {
        AddressBO addressBO = new AddressBO();
        BeanUtils.copyProperties(req,addressBO);
        PageInfo<AddressDTO> pageInfo = addressService.listAddressPageInfo(addressBO);

        PageData<AddressRes> transForm = new PageData<>();
        List<AddressRes> resultList = new ArrayList<>();
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS,
                transForm.transform(pageInfo, AddressRes.class, resultList));

    }

    /**
     *@Description 更新地址
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param
     *@return com.zhongda.kernel.response.RespEntity<java.lang.Boolean>
    **/
    @PostMapping("/updateAddress")
    @ApiOperation(value = "更新地址", notes = "更新地址")
    public RespEntity<Boolean> updateAddress(@Validated @RequestBody AddressUpdateReq req) {
        updateAddressCommon(req);
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, true);
    }

    public void updateAddressCommon(@Validated @RequestBody AddressUpdateReq req) {
        AddressUpdateBO addressUpdateBO = new AddressUpdateBO();
        CurrentUser currentUser =
                CurrentUserUtil.getCurrentUser(redisTemplate);
        String userName = currentUser.getUserName();
        addressUpdateBO.setAdminName(userName);
        addressUpdateBO.setAdminId(currentUser.getId());
        BeanUtils.copyProperties(req,addressUpdateBO);
        addressService.updateAddress(addressUpdateBO);
    }

    /**
     *@Description 新增地址
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param
     *@return com.zhongda.kernel.response.RespEntity<java.lang.Boolean>
     **/
    @PostMapping("/insertAddress")
    @ApiOperation(value = "新增地址", notes = "新增地址")
    public RespEntity<Boolean> insertAddress(@Validated @RequestBody AddressUpdateReq req) {
        updateAddressCommon(req);
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, true);
    }

    /**
     *@Description 查询地址单个对象
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param
     *@return com.zhongda.kernel.response.RespEntity<?>
    **/
    @PostMapping("getAddress")
    @ApiOperation(value = "根据主键查询地址", notes = "根据主键查询地址")
    public RespEntity<?> selectPackageType(@Validated @RequestBody IdIntegerBean req) {
        AddressOneRes addressOneRes = new AddressOneRes();
        AddressOneDTO addressOne = addressService.getAddressOne(req.getId());
        BeanUtils.copyProperties(addressOne,addressOneRes);
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, addressOneRes);
    }

    /**
     *@Description 删除地址单个对象
     *@author 赵赫智
     *@date 2019-11-04T10:27:16.889
     *@param
     *@return com.zhongda.kernel.response.RespEntity<?>
     **/
    @PostMapping("deleteAddress")
    @ApiOperation(value = "根据主键ids 删除地址", notes = "根据主键ids 删除地址")
    public RespEntity<?> deleteAddress(@Validated @RequestBody List<Integer> req) {
        CurrentUser currentUser =
                CurrentUserUtil.getCurrentUser(redisTemplate);
        addressService.deleteAddressOne(req,currentUser.getId(),currentUser.getUserName());
        return RespUtil.genResp(RespUtil.RespStatus.SUCCESS, true);
    }


}
