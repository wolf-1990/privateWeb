package com.bluesky.zhz.web.controller;

import com.bluesky.zhz.core.bean.base.BaseController;
import com.bluesky.zhz.server.entity.bean.ConditionCoordinate;
import com.bluesky.zhz.server.entity.bean.Coordinate;
import com.bluesky.zhz.server.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/5/27 17:25
 **/
//@Controller
//@RequestMapping("/map")
public class MapController extends BaseController{
    @Autowired
    private MapService mapService;
    @RequestMapping("/index")
    public String indexShow(HttpServletRequest request, Model model){

//        return "index/index";
        return "map/indexMap";
    }
    /**
     *@Author 赵赫智
     *@Description //TODO 通过两个坐标位置返回第三位置
     *@Date 18:20 2019/5/27
     *@return com.bluesky.zhz.server.entity.bean.Coordinate
     **/
    @RequestMapping("/getCoordinate")
    public Coordinate getCoordinate(List<ConditionCoordinate> list){
        Coordinate result = mapService.getCoordinate(list);
        return result;
    }
}
