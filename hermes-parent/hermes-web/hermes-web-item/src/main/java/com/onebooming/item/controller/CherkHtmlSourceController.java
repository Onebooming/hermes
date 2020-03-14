package com.onebooming.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-12 21:24
 * @ApiNote
 */

@Controller
@RequestMapping("/items")
public class CherkHtmlSourceController {

    @GetMapping("/test")
    public String staticHtml(@RequestParam(required = false) String id, Model model){
        String lacation = id;
        System.out.println("/items/"+lacation);
        model.addAttribute("aa",'a');
        return "/items/1228604867858137088.html";
    }

}
