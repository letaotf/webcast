package com.taofeng.webcast.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>启动主页面index</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午12:57
 * @since V1.0
 */
@Controller
public class ApplicationStartController {

//    @RequestMapping(value = "/")
//    public String start(Model model){
//        return "index";
//    }

    @RequestMapping(value = "/login.htm")
    public String login(Model model){
        return "/login";
    }

//    @RequestMapping(value = "/register.htm")
//    public String register(Model model){
//        return "/register";
//    }
//
//    @RequestMapping(value = "/main.htm")
//    public String startMain(Model model){
//        return "/main";
//    }
//
    @RequestMapping(value = "/layout.htm")
    public String layoutMain(Model model){
        return "/admin/layout";
    }

    @RequestMapping(value = "/user.htm")
    public String user(Model model){
        return "user";
    }

    @RequestMapping(value = "/networkBroadcast.htm")
    public String networkBroadcast(Model model){
        return "networkBroadcast";
    }

    @RequestMapping(value = "/addBroadcastType.htm")
    public String addBroadcastType(Model model){
        return "addBroadcastType";
    }

    @RequestMapping(value = "/asset.htm")
    public String asset(Model model){
        return "asset";
    }

    @RequestMapping(value = "/assetDetail.htm")
    public String assetDetail(Model model){
        return "assetDetail";
    }

    @RequestMapping(value = "/goodsDetail.htm")
    public String goodsDetail(Model model){
        return "goodsDetail";
    }

    @RequestMapping(value = "/goodsOperating.htm")
    public String goodsOperating(Model model){
        return "goodsOperating";
    }

    @RequestMapping(value = "/addGoods.htm")
    public String addGoods(Model model){
        return "addGoods";
    }

    @RequestMapping(value = "/intergityValues.htm")
    public String intergityValues(Model model){
        return "intergityValues";
    }

    @RequestMapping(value = "/complain.htm")
    public String complain(Model model){
        return "complain";
    }

    @RequestMapping(value = "/notice.htm")
    public String notice(Model model){
        return "notice";
    }

    @RequestMapping(value = "/addComplain.htm")
    public String addComplain(Model model){
        return "addComplain";
    }

    @RequestMapping(value = "/common.htm")
    public String common(Model model){
        return "common";
    }

    @RequestMapping(value = "/userOperator.htm")
    public String userOperator(Model model){
        return "/admin/userOperator";
    }

    @RequestMapping(value = "/anchorOperator.htm")
    public String anchorOperator(Model model){
        return "/admin/anchorOperator";
    }
}
