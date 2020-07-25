package com.yzit.demo.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/static/")
@CrossOrigin // 支持跨域
public class StaticController {

    // 注入freemarker自带的configuration类
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @GetMapping("/test")
    public String staticTest() throws Exception {

        // 1、调用freeMarkerConfig 获取freemarker的配置类
        Configuration configuration = freeMarkerConfig.getConfiguration();

        // 2、定义存储数据用的map容器
        Map<String,Object> dataModel = new HashMap<String, Object>();


        // 3、从Configuration中获取freemarker模板对象
        //    参数必须带后缀.html,不能只是test，
        Template template = configuration.getTemplate("test.html");

        // 4、存储数据
        dataModel.put("name","张三");

        // 5、创建一个路径,用来存放生成的静态文件
        String filePath = "D:\\temp\\staticPage\\test.html";

        // 6、执行，生成文件，将静态页面，将页面放入指定的文件中
        template.process(dataModel,new FileWriter(new File(filePath)));

        return "操作成功";
    }



}
