package com.yzit.controller;

import com.yzit.anntion.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Log(title = "测试方法",option ="SELECT",type = 2)
    @RequestMapping("test")
    public String test(){
        System.out.println("核心方法执行了");
        return "测试";
    }
}
