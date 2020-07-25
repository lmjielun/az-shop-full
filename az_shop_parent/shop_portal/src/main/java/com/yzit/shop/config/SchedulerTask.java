package com.yzit.shop.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

@Component // 将该类交给spring管理
public class SchedulerTask {

    // 注入freemarker自带的configuration类
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    // 定义生成静态页面的路径地址
    // 然后配置SwitchHosts添加一个地址，127.0.0.1 item.shop.com
    // 然后在去nginx的conif文件里，配置一个静态地址
    private String ITEM_PATH = "D:\\temp\\staticPage\\item_html\\";

    // 定义一个计数变量
    private int count = 0;

    // 设置计划
    @Scheduled(cron = "*/10 * * * * ?")
    private void process()  {
        System.out.println("生成一次文件"+(count++));
        try {
            // 1、调用freeMarkerConfig 获取freemarker的配置类
            Configuration configuration = freeMarkerConfig.getConfiguration();

            // 2、定义存储数据用的map容器
            Map<String,Object> dataModel = new HashMap<String, Object>();

            // 3、从Configuration中获取freemarker模板对象
            //    参数必须带后缀.html,不能只是test，
            Template template = configuration.getTemplate("index.html");

            // 4、准备模板数据：因为index页面不需要传递参数，直接访问，所以不需要准备数据

            // 5、创建一个路径,用来存放生成的静态文件
            String filePath = ITEM_PATH+"index"+count+".html";

            // 6、执行，生成文件，将静态页面，将页面放入指定的文件中
            template.process(dataModel,new FileWriter(new File(filePath)));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
