package com.hive.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class HtmlController {
    @RequestMapping
    public String home(){
        return "homepage";
    }


    @RequestMapping("trend")
    public String trend(){
        return "trend";
    }

    @RequestMapping("table")
    public String table(){
        return "table";
    }
}
