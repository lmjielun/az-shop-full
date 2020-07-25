package com.yzit.shop.freemarker.tag;


import com.yzit.shop.entity.Category;
import com.yzit.shop.freemarker.utils.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;


/**
 *  商品分类自定标签
 */
@Component
public class CategoryTag  implements TemplateDirectiveModel{

    //注入restTemplate
    @Autowired
    private RestTemplate restTemplate;

    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        //System.out.println("restTemplate ==="+restTemplate);

        Integer id = FreemarkerUtils.getInteger(map , "id");
        String   url = "http://localhost:90/api/category/";
        url = url+id;
        //System.out.println("url = "+url);
        Category category  =   restTemplate.getForObject(url, Category.class);

        //System.out.println("category =="+category);
        //将查询的结果存储到freeamer的数据中
        environment.setVariable("category", ObjectWrapper.DEFAULT_WRAPPER.wrap(category));
        templateDirectiveBody.render(environment.getOut());

    }

}
