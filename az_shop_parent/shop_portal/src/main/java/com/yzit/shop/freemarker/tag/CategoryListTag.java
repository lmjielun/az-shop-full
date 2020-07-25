package com.yzit.shop.freemarker.tag;

import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.entity.Category;
import com.yzit.shop.freemarker.utils.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 商品分类自定义标签
 */
@Component
public class CategoryListTag implements TemplateDirectiveModel {

    @Autowired
    private RestTemplate restTemplate;

    // 注入redis的模板对象
    @Autowired
    private RedisTemplate redisTemplate;

    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        //System.out.println("restTemplate ==="+restTemplate);
        //获取参数
        Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put( "parentId", FreemarkerUtils.getInteger(map,"parentId"));// 父类目id,顶级类目填0
        paramMap.put( "isShow", FreemarkerUtils.getInteger(map,"isShow"));//是否显示，0为否，1为是
        paramMap.put( "isMenu", FreemarkerUtils.getInteger(map,"isMenu"));//是否菜单，0为否，1为是
        paramMap.put( "isCascade", FreemarkerUtils.getInteger(map,"isCascade"));// 是否级联（三级级联）
        // key就是存储在缓存中的key  INDEX表示首页  CATEGORY表示商品分类  parentId商品分类的父Id
        String key = "INDEX_CATEGORY:"+paramMap.get("parentId");

        // 数据库的请求地址
        String   url = "http://localhost:90/api/category/cascadeList?parentId={parentId}&isShow={isShow}&isMenu={isMenu}&isCascade={isCascade}";

        // 根据key 查询缓存
        List<Category> categoryList = (List<Category>) redisTemplate.opsForValue().get(key);

        // 如果缓存是Null值,那么就需要去数据库进行查询
        if(categoryList == null){
            // 到数据库查询
            categoryList = restTemplate.getForObject(url, List.class,paramMap);

            // 将查询到结果存储到缓存中
            redisTemplate.opsForValue().set(key,categoryList);

            //System.out.println("********从数据库中查询*********");
        }else{
            //System.out.println("********从缓存中获取*********");
        }

        //将查询的结果存储到freeamer的数据中
        environment.setVariable("dataList", ObjectWrapper.DEFAULT_WRAPPER.wrap(categoryList));
        //渲染到页面
        templateDirectiveBody.render(environment.getOut());
    }

}
