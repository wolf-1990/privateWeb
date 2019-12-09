package com.bluesky.zhz.web.controller;

import com.bluesky.zhz.core.bean.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description TODO
 * @Author 赵赫智
 * @Date 2019/2/19 15:02
 **/
@Controller
@RequestMapping("/home")
public class IndexController extends BaseController{

    /**
     *@Author 赵赫智
     *@Description 首页展示
     *@Date 15:05 2019/2/19
     *@return java.lang.String
     **/
    @RequestMapping("/")
    public String indexShow(HttpServletRequest request,Model model){


//        return "index/index";
        return "index/zhz";
    }
}
