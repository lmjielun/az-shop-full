package com.yzit.shop.compent;

import com.alibaba.fastjson.JSON;
import com.yzit.shop.entity.SysLog;
import com.yzit.shop.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component // 交给spring管理
public class ActiveMQServer {
    // 注入日志service类
    @Autowired
    private SysLogService sysLogService;

    // 注入redis
    @Autowired
    private RedisTemplate redisTemplate;

    // 添加监听注解：所要监听的队列，就是【业务系统】中的log_queue
    @JmsListener(destination = "log_queue")
    public void receive(String message) {
        System.out.println("收到的 message 是：" + message);

        // 将接受到的消息，转为实体类
        SysLog sysLog = (SysLog) JSON.parseObject(message, SysLog.class);

        System.out.println("循环外的types ======== " + sysLog.getTypes());
        System.out.println("将信息转换为对象，然后存储到数据库中");
        //sysLogService.save(sysLog);

        // 定义Redis的Key键
        String list_key = "log_list_key";
        // 将消息右测放入到 redis的list中
        redisTemplate.opsForList().rightPush(list_key,sysLog);

        //获取redis日志集合长度，
        long length  = redisTemplate.opsForList().size(list_key);
        // 如果长度 > 1000，批量插入到数据库中
        if(length  >= 3){
            // 创建一个list集合
            List<SysLog> sysLogList = new ArrayList<SysLog>();
            // 循环redis日志集合的长度
            for(int i = 0 ; i<length ; i++){
                // 左侧弹出，然后添加到java的list集合中
                SysLog log = (SysLog) redisTemplate.opsForList().leftPop(list_key);
                sysLogList.add(log);
            }

            // 然后将java的List集合插入到数据库中
             sysLogService.batchInsert(sysLogList);
        }
    }
}
