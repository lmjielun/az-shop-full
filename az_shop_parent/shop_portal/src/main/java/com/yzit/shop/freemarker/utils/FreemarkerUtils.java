package com.yzit.shop.freemarker.utils;

import freemarker.template.SimpleScalar;

import java.util.Map;

/***
 * freemaker工具类
 */
public class FreemarkerUtils {
    /**
     * 从freekarker自定义标签中获取string类型参数
     * @param map
     * @param paramName
     * @return
     */
    public static  String getString(Map map, String paramName){
        //从map中获取参数值
        Object  valObj = map.get(paramName);
        if(valObj instanceof SimpleScalar){
            return ((SimpleScalar)valObj).getAsString();
        }
        return null;
    }

    public static Integer  getInteger(Map map,String paramName){
        String objValue = getString(map,paramName);
        if(objValue != null){
            return Integer.parseInt(  objValue  );
        }
        return null;
    }
    public static Long getLong(Map map,String paramName){
        String objValue = getString(map,paramName);
        if(objValue != null){
            return Long.parseLong(  objValue  );
        }
        return null;
    }
}
