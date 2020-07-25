package com.yzit.shop.controller;

public class StackOverOut {
    // main方法执行jc()方法，并且传个值
    public static void main(String[] args) {
        int[] a = new int[1024*1024*1024 ];
    }
}
