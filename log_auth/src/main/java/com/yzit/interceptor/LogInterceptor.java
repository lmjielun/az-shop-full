package com.yzit.interceptor;

import com.alibaba.fastjson.JSON;
import com.yzit.annotation.Log;
import com.yzit.shop.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {

    // 执行过程
    /**
     *  先执行preHandle
     *  再执行controller方法
     *  然后执行postHandle
     *  最后执行afterCompletion
     */

    // 注入jms
    @Autowired
    private JmsTemplate jmsTemplate;

    // 创建本地线程
    //创建本地进程，存储进程数据
    private static final ThreadLocal<Long> startTimeThreadLocal =new NamedThreadLocal<Long>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 使用本地线程，记录方法开始的时间
        long beginTime = System.currentTimeMillis();
        // 设置本地线程的开始时间
        startTimeThreadLocal.set(beginTime);

        System.out.println("方法执行前");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("方法执行后");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("响应用户");

        /********************创建日志对象，拼接日志信息 ***********************/
            SysLog sysLog = new SysLog();
        /**
         *  拼接日志注解信息：
         *  获取日志自定义注解 将Object handler与HandlerMethod比对，看是否是方法处理，
         *  如果比对成功了，将handler强制转换为HandlerMethod方法处理
         *  日志注解信息
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            Log log = handlerMethod.getMethod().getAnnotation(Log.class);
            // 设置日志对象的title属性 也就是方法名称  从Log注解获取
            sysLog.setTitle(log.name());
            // 设置日志对象的type属性 也就是操作类型  从Log注解获取
            sysLog.setTypes(log.option());

        }

        /**
         *  拼接日志请求信息
         */
        // 设置请求地址   获取请求地址：request.getRequestURI()
        sysLog.setRequestUri(request.getRequestURI());

        // 获取请求参数:将转为String类型
        String paramStr = JSON.toJSONString(request.getParameterMap());
        // 设置请求参数map
        sysLog.setParams(paramStr);

        // 设置请求方式  获取请求参数：request.getMethod()
        sysLog.setMethod( request.getMethod());

        // IP地址 使用request获取不准确，到网上找个工具类获取ip地址
        // 工具类见该方法最下边，获取ip地址：getIPAddress(request)
        sysLog.setRemoteAddr(getIPAddress(request) );


        // 设置请求开始时间
        // 需要创建本地线程，然后在方法执行前，执行本地线程，
        // set设置时间,然后在这里get本地线程开始时间
        Long startTime = startTimeThreadLocal.get();
        sysLog.setStartDate(new Date(startTime));
        // 请求结束时间 只需要在这里获取即可
        long endTime = System.currentTimeMillis();
        sysLog.setEndDate(new Date(endTime));

        /**
         *  拼接日志结果
         */
        // 判断异常对象 是否为空
        if(ex !=null){//不为空，说明有异常了
            // 设置异常信息
            sysLog.setExceptions(ex.getMessage());
        }


        /**
         *  将日志对象存储到mq消息队列中
         */
        // 将日志对象转换为json字符串
        String sysLogStr = JSON.toJSONString(sysLog);
        // 将消息（sysLogStr）发送到指定的地址
        // 参数1为：目的地  参数2为：消息内容
        this.jmsTemplate.convertAndSend("log_queue",sysLogStr);


    }

    /**
     *  工具类，获取ip地址
     * @param request
     * @return
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}
