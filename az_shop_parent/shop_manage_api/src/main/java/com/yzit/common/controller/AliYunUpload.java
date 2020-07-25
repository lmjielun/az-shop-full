package com.yzit.common.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AliYunUpload {

    public static void main(String[] args) throws FileNotFoundException {

         // 这个是固定的访问域名
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";


        /**
         *  获取accessKeyId 和 accessKeySecret  需要到阿里云获取
         */
        //String accessKeyId ="LTAI4GHPoPE5oqVrnWY3EUhf"  这是我自己的
        String accessKeyId = "LTAI4GHPoPE5oqVrnWY3EUhf";

        // String accessKeySecret = "1uHWlF8XL6lRiHaTbH1e6tJSRQHuXv";
        String accessKeySecret = "1uHWlF8XL6lRiHaTbH1e6tJSRQHuXv";


        // 创建OSSClient实例。 构建: 域名，accessKeyId,accessKeySecret
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流

        // 获取本地的文件 我的本地文件在D盘下，名叫123.jpg
        String localFile = "D://temp//123.jpg";
        // 将其转为流
        FileInputStream inputStream = new FileInputStream(localFile);

        // 然后存储到 阿里云服务器上
        // 第一个参数是 你的bucketName名称
        // 第二个参数是 你上传的路径bucketName下的哪个文件夹下，然后文件名想叫什么名字
        // 第三个不用管 是输入流
        ossClient.putObject("az-shop-img","20200428/321.jpg",inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
