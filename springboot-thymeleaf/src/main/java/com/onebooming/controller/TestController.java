package com.onebooming.controller;

import com.onebooming.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-08 22:16
 * @ApiNote
 */
// @RestController是响应json数据，如果要是实现页面跳转，则需要@Controller注解
@Controller
@RequestMapping("/test")
public class TestController {

    /**
     * 访问/test/hello  跳转到demo1页面
     * @param model
     * @return
     */
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message","hello welcome");
        //集合数据
        List<User> users = new ArrayList<User>();
        users.add(new User(1,"张三","深圳"));
        users.add(new User(2,"李四","北京"));
        users.add(new User(3,"王五","武汉"));
        model.addAttribute("users",users);

        //后台添加map数据
        //Map定义
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("No","123");
        dataMap.put("address","深圳");
        model.addAttribute("dataMap",dataMap);

        //后台添加数组
        String[] names = {"张三","李四","王五"};
        model.addAttribute("names",names);

        //后台添加日期
        model.addAttribute("now",new Date());

        //if条件
        model.addAttribute("age",22);

        //用于字符串拼接
        model.addAttribute("passage1","passage2");
        model.addAttribute("passage2","passage1");

        return "demo1";
    }

}
