package com.yzit.shop.controller;

import com.yzit.framework.web.ui.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cache")
@CrossOrigin
public class CaCheController {
    // 注入Redis的模板对象
    @Autowired
    private RedisTemplate redisTemplate;

    // 查找key  看key是否存在
    @GetMapping("/{key}")
    public List getCaChe(@PathVariable String key){
        return (List)redisTemplate.opsForValue().get(key);
    }


    // 保存
    @PostMapping("/{key}")
    public AjaxResult saveCaChe(@PathVariable String key, @RequestBody List dataList){
        redisTemplate.opsForValue().set(key,dataList);
        return AjaxResult.OK();
    }



}
