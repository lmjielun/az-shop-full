package com.yzit.interceptor;

import com.yzit.anntion.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取自定义注解 将Object handler与HandlerMethod比对，看是否是方法处理，
        // 如果比对成功了，将handler强制转换为HandlerMethod方法处理
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            Log log = handlerMethod.getMethod().getAnnotation(Log.class);
            System.out.println("标题："+log.title());
            System.out.println("操作："+log.option());
            System.out.println("类型："+log.type());

        }

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
    }
}
