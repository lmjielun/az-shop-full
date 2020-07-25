package com.yzit.SendQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    @Autowired
    private JmsTemplate jmsTemplate;

    /*
     * 发送 队列消息
     */
    @RequestMapping("/sendQueue")
    public String sendQueue() {
        // 指定消息发送的目的地及内容  boot_queqe是名称
        this.jmsTemplate.convertAndSend("boot_queue", "点对点消息:"+System.currentTimeMillis());

        return "消息发送成功！message=" + System.currentTimeMillis();
    }

}
