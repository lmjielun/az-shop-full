package com.yzit.anntion;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 开发该类，在方法上使用
@Retention(RetentionPolicy.RUNTIME) // 保留策略：运行时
@Documented // 文档
public @interface Log {
    /**
     *  自定义注解只支持两种修饰符，一种public 一种 默认的
     * @return
     */
    String  title() default "";//
    String  option() default  "SELECT";//表示操作  SELECT  INSERT  UPDATE  DELETE
    int  type() default 0;//0 表示业务， 1表示结构
}
