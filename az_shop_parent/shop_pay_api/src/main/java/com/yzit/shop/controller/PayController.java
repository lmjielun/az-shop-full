package com.yzit.shop.controller;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.config.AlipayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RequestMapping("/api/alipay")
@RestController
@CrossOrigin
@Api("支付接口")
public class PayController {


    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private AlipayClient alipayClient;


    /**
     *
     * @param orderNo 订单编号
     * @param amount  订单金额
     * @param subject 订单名称
     * @param body  订单简介
     * @return
     */
    @ApiOperation("订单支付")
    @GetMapping("/pay")
    public AjaxResult pay(@RequestParam("orderNo") String orderNo,
                          @RequestParam("amount") String amount,
                          @RequestParam("subject") String subject,
                          @RequestParam("body") String body){

        try {
            // 设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            // 设置异步跳转地址
            alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
            // 设置同步跳转地址
            alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ orderNo +"\","
                    + "\"total_amount\":\""+ amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            // 请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            AjaxResult  ajax = new AjaxResult();
            ajax.setSuccess(true);
            ajax.setCode(200);
            ajax.setData(result);
            return ajax;

        } catch (AlipayApiException e) {
            e.printStackTrace();
            return AjaxResult.ERROR(e.getMessage());
        }

    }




    // 这是异步跳转
    @RequestMapping("/notify")
    public String notify(@RequestParam Map<String,String> paramMap) throws AlipayApiException {
        //验证签名
        boolean verifyResult = AlipaySignature.rsaCheckV1(paramMap, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        if (verifyResult) {
            System.out.println(alipayConfig.getReturnUrl() + "：同步地址");
            String tradeStatus = paramMap .get("trade_status"); // 交易状态
            String outTradeNo = paramMap .get ("out_trade_no"); // 商户订单号
            String tradeNo = paramMap .get("trade_no"); // 支付宝订单号

            System.out.println("交易状态 :"+tradeStatus);
            System.out.println("商户订单号 :"+outTradeNo);
            System.out.println("支付宝订单号 :"+tradeNo);
            return "success";
        }
        //   return "fail";
        return "error: not exists out_trade_no:";

    }
}
