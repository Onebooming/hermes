package com.onebooming.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-21 15:09
 * @ApiNote
 */
@Controller
@RequestMapping(value = "/oauth")
public class LoginRedirect {

    /***
     * 跳转到登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "FROM",required = false,defaultValue = "") String from, Model model){
        //储存from   未登录用户，跳转到登录页，然后根据登录状态，如果登录成功，则跳转到来源页。
        model.addAttribute("from",from);
        return "login";
    }
}
