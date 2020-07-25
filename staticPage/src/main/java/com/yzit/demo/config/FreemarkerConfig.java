package com.yzit.demo.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component // 添加注解，将该类交给spring管理
public class FreemarkerConfig {


    // 注入freemarker自带的Configuration，注意导包是freemarker的包
    @Autowired
    private Configuration configuration;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        // 将自定义标签注册到freemarker中
    }


}
