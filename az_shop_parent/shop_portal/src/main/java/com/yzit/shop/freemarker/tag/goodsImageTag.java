package com.yzit.shop.freemarker.tag;

import com.yzit.framework.web.ui.FileVo;
import com.yzit.shop.entity.Goods;
import com.yzit.shop.freemarker.utils.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class goodsImageTag implements TemplateDirectiveModel {

    // 注入restTemplate
    @Autowired
    private RestTemplate restTemplate;

    // 注入redisTemplate
    @Autowired
    private RedisTemplate redisTemplate;


    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        //  获取自定义标签传递的id 进行转换
        Long categoryId = FreemarkerUtils.getLong(map,"categoryId");

        // 定义发送请求的地址
        String url = "http://localhost:90/api/goods/category/";
        url = url + categoryId;
        System.out.println(url);

        // 获取参数
        Map<String,Object> goodsMap  = new HashMap<String, Object>();

        goodsMap.put("imgSrc",FreemarkerUtils.getString(map,"imgSrc"));

        // 调用restTemplate,发送请求
        List<FileVo> goodsList = restTemplate.getForObject(url, List.class,goodsMap);

        System.out.println("获取到的集合 goodsList = " + goodsList);

        // 将结果存入freemarker中
        environment.setVariable("goodsList", ObjectWrapper.DEFAULT_WRAPPER.wrap(goodsList));

        // 渲染到页面
        templateDirectiveBody.render(environment.getOut());
    }
}
