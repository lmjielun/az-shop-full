package com.yzit.shop.freemarker.tag;
import com.yzit.shop.entity.AD;
import com.yzit.shop.freemarker.utils.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.*;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class AdListTag implements TemplateDirectiveModel {
    // 注入restTemplate
    @Autowired
    private RestTemplate restTemplate;

    // 注入redisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    // 实现execute方法
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        // 获取广告总数 转为Integer类型
        Integer count = FreemarkerUtils.getInteger(map,"count");
        // 获取code码，商品分类的key唯一表示   转为string类型
        String code = FreemarkerUtils.getString(map,"code");

        // 定义发送请求的地址
        String url = "http://localhost:90/api/aD/list?count={count}&code={code}";

        // 远程调用传递参数
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("count",count);
        paramMap.put("code",code);

        // 方式一  调用restTempalate 的getForObject ，传递url,list,paramMap参数
        //List<AD> adList = restTemplate.getForObject(url,List.class,paramMap);

        //方式二添加缓存
        // 设置缓存的key
        String key  = "INDEX_AD_"+code.toUpperCase();
        // 调用redis的模板对象，获取key
        List<AD> adList = (List<AD>) redisTemplate.opsForValue().get(key);
        // 如果获取到的key的集合是null值
        if(adList == null){
            System.out.println("从数据库中获取广告列表");
            // 调用rest的模板对象，发送请求，从数据库中获取数据
            adList = restTemplate.getForObject(url, List.class, paramMap);
            redisTemplate.opsForValue().set(key,adList);
            // 设置缓存的时间
            redisTemplate.expire(key,5*60, TimeUnit.SECONDS);// 5分钟
        }else{
            System.out.println("从缓存中获取广告列表");
        }
        //将查询的结果存储到freeamer的数据中
        environment.setVariable("dataList", ObjectWrapper.DEFAULT_WRAPPER.wrap(adList));

        // 渲染页面
        templateDirectiveBody.render(environment.getOut());
    }
}
