package com.yzit.shop.controller;

import com.alipay.api.AlipayClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试接口")
public class TestController {

    // 注入阿里AlipayConfig的AlipayClient
    @Autowired
    private AlipayClient alipayClient;


    @ApiOperation("alipay测试")
    @GetMapping("/test")
    public String tesdPay(){
        System.out.println(alipayClient);
        String s = alipayClient.toString();
        return "s";
    }

}
