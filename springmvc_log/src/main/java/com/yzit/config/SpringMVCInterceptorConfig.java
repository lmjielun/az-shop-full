package com.yzit.config;
import com.yzit.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;

@Configuration
public class SpringMVCInterceptorConfig implements WebMvcConfigurer {

    // 注入自己开发的log拦截器
    @Resource
    private LogInterceptor logInterceptor;

    // 注册拦截器：就是将你自己开发的拦截器，注册进去
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册你的拦截器，然后设置拦截哪些请求
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }
}
