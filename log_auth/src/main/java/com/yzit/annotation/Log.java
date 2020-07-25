package com.yzit.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 作用目标：表示注解配置：是类、变量、方法等很多东西
@Retention(RetentionPolicy.RUNTIME) // 保留时间，一般注解就是为了框架开发时代替配置文件使用，JVM运行时用反射取参数处理，所以一般都为RUNTIME类型
@Documented // 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化
public @interface Log {

    String  name() default "";//方法名称
    String  option() default  "SELECT";//表示操作  SELECT  INSERT  UPDATE  DELETE
}
