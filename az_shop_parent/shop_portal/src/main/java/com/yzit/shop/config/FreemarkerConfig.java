package com.yzit.shop.config;

import com.yzit.shop.freemarker.tag.AdListTag;
import com.yzit.shop.freemarker.tag.CategoryListTag;
import com.yzit.shop.freemarker.tag.CategoryTag;
import com.yzit.shop.freemarker.tag.goodsImageTag;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FreemarkerConfig {
    //注入configration
    @Autowired
    private Configuration configuration;
    // 注入商品分类的自定义标签
    @Autowired
    private CategoryTag categoryTag ;//查询分类某一个对象
    // 注入商品分类自定义标签
    @Autowired
    private CategoryListTag categoryListTag ;//查询分类集合
    // 注入广告集合
    @Autowired
    private AdListTag adListTag;
    // 注入商品对象自定义标签
    @Autowired
    private goodsImageTag goodsImageTag;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        // 将自定义标签注册到freemarker中
        configuration.setSharedVariable("categoryTag", categoryTag);
        // 将自定义标签注册到freemarker中
        configuration.setSharedVariable("categoryListTag", categoryListTag);
        // 将自定义标签注册到freemarker中
        configuration.setSharedVariable("adListTag",adListTag);
        // 将自定义标签注册到freemarker中
        configuration.setSharedVariable("goodsImageTag",goodsImageTag);
    }
}
