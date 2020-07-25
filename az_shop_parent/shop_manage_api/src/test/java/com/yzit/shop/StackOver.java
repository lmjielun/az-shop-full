package com.yzit.shop;

import org.junit.Test;

// 方法无限调用，出现内存溢出
public class StackOver {

    @Test
    public static int jc(int n){
        return n * jc(n-1);
    }

}
