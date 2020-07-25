package com.yzit.shop;

import org.junit.Test;
import org.omg.CORBA.CurrentHelper;

import java.util.*;

public class TestJDK8 {


    @Test
    public void testJDK8(){

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(5);
        list.add(4);
        list.add(1);

        // 将list升序排列  使用collections工具类 默认自然排序 就是0-n由小到大
        Collections.sort(list);
        System.out.println(list);

        /**
         *  传统降序的写法，调用collections工具类，将返回结果写为负数
         */
        Collections.sort(list, new Comparator<Integer>() {
            // 然后出来了匿名内部类 得到正数是自然排序，得到负数就是逆向排序
            public int compare(Integer integer, Integer t1) {
                // 把integer-1 然后 前边加上 负号  就是将正数变为负数
                return -(integer-t1);
            }
        });

        /**
         *  使用lambda表达式，将后边的new Comparator省略掉，大括号直接，
         *  然后大括号直接返回结果
         */
        Collections.sort(list,(integer,t1)->  {return integer-t1; } );

        System.out.println(list);


    }


    @Test
    public void testMap(){
        HashMap<Integer, String> map = new HashMap<>();

        for(int i=0 ; i < 50000 ; i++){
            map.put(i,"值"+i);
        }


        /*Set<Integer> keys = map.keySet();
        //600毫秒 5万条数据
        System.out.println("开始时间："+ new Date().getTime());
        for(Integer key : keys){
            System.out.println(key);
        }
        System.out.println("结束时间："+ new Date().getTime());*/

    /*  Collection<String> values = map.values();
        // 600毫秒 5万条数据
        System.out.println("开始时间："+ new Date().getTime());
        for (String value : values){
            System.out.println(value);
        }
        System.out.println("结束时间："+ new Date().getTime());*/




        // 700毫秒 5万条数据
       /* Set<Map.Entry<Integer, String>> entries = map.entrySet();

        System.out.println("开始时间："+ new Date().getTime());
        for(Map.Entry<Integer, String> entry :entries){
            System.out.println(" key = "+entry.getKey() + " value = "+entry.getValue());
        }
        System.out.println("结束时间："+ new Date().getTime());*/


        // 740毫秒以上 5万条数据
      /*  Iterator<Map.Entry<Integer, String>> it=map.entrySet().iterator();
        System.out.println("开始时间："+ new Date().getTime());
        while(it.hasNext()){
            Map.Entry<Integer, String> entry=it.next();
            System.out.println("键key ："+entry.getKey()+" value ："+entry.getValue());
        }
        System.out.println("结束时间："+ new Date().getTime());*/
    }

}
