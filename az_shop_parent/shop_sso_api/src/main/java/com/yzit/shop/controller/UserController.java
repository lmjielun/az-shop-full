package com.yzit.shop.controller;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.common.MD5Util2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import com.yzit.shop.entity.User;
import com.yzit.shop.service.UserService;

import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    // 登录
    @PostMapping("/login")
    public AjaxResult doLogin(@RequestBody User user){
        String md5 = MD5Util2.encode(user.getPassword(), "MD5");
        user.setPassword(md5);

        // 调用service登录
        User userDB = userService.doLogin(user);

        // 判断返回结果
        if(userDB != null){

            // 生成token令牌
            String token = UUID.randomUUID().toString().replaceAll("-","");

            // 存储到redis服务器：将token 与 userDB 对象存储到redis
            redisTemplate.opsForValue().set("USER_INFO_"+token,userDB);
            // 设置redis的生命周期 30分钟
            redisTemplate.expire("USER_INFO_"+token,30, TimeUnit.MINUTES);


            // 将token返回给前台
            return AjaxResult.OK(token);

        }else{
            return AjaxResult.ERROR("账号或者密码错误");
        }
    }

    // 检查是否登录,返回用户
    @GetMapping("/token/{token}")
    public AjaxResult checkLogin(@PathVariable String token){
        // 拼接key，查询redis数据库，Key是，/login方法里定义的，前缀是USER_INFO_
        System.out.println(token);
        String key = "USER_INFO_"+token;
        // 调用redis模板对象，到redis里进行查询
        User user = (User) redisTemplate.opsForValue().get(key);

        // 每次根据token获取用户的时候，重新设置key的生命周期
        redisTemplate.expire("USER_INFO_"+token,30, TimeUnit.MINUTES);

        // 判断查询是否有值
        if(user != null ){
            // 如果有值，放入到AjaxResult的data属性里
            return AjaxResult.OK(user);
        }else {
            return AjaxResult.ERROR("未登录");
        }
    }

    // 注册
    @PostMapping("/register")
    public AjaxResult register(@RequestBody User user){

        AjaxResult ajaxResult = userService.checkUser(user);

        return ajaxResult;
    }

}