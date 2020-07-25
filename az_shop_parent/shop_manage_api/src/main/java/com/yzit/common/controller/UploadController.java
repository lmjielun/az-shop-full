package com.yzit.common.controller;

import com.aliyun.oss.OSS;
import com.yzit.shop.config.AliyunConifg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/common/upload")
public class UploadController {
    // 注入阿里云OSS 他这里只有 访问的地域节点
    @Autowired
    private OSS ossClient;
    // 注意阿里云config配置类，因为他里边存的有阿里云Bucket域名(空间域名) 以及 访问路径 和文件夹名
    @Autowired
    private AliyunConifg aliyunConifg;



    // 图片上传  native表示上传到本地，当然名字随便起
    @PostMapping("/nginx")
    public String uploadNginx(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        // 测试用，看该方法是否执行
        System.out.println("nginx......");
        // 1、这里直接写本地磁盘
        String path = "D:/temp/upload-image";
        // 2、获取文件的后缀名称，从最后的"." 开始截取
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 3、使用uuid解决中文乱码，以及文件名重复问题
        String fileName =  UUID.randomUUID() + suffix;


        // 4、获取 文件名称 + 路径
        String filePath = path + "/" + fileName;


        // 5、创建新的文件，将全路径放进去
        File newFileName = new File(filePath);


        // 6、判断
        if(!newFileName.getParentFile().exists()){
            newFileName.mkdir();
        }
        // 7、将用户上传的图片，持久化 存储到服务器磁盘
        try {
            file.transferTo(newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 8、将路径返回 这里返回的是域名 通过windows下的dns映射，将ip地址与域名映射
        return "http://image.shop.com/"+fileName;
    }

    // 图片上传  native表示上传到本地，当然名字随便起
    @PostMapping("/native")
    public String uploadNative(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        // 测试用，看该方法是否执行
        System.out.println("native......");
        // 1、通过request获取服务器地址,"img"是文件夹的名称，你文件夹名称是什么，就写什么,如果没有可以写"/"
        String path = request.getSession().getServletContext().getRealPath("/");
        // 2、获取文件的后缀名称，从最后的"." 开始截取
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 3、使用uuid解决中文乱码，以及文件名重复问题
        String fileName =  UUID.randomUUID() + suffix;

        // 4、获取 文件名称 + 路径
        String filePath = path + "/" + fileName;
        System.out.println(filePath);

        // 5、创建新的文件，将全路径放进去
        File newFileName = new File(filePath);

        // 6、判断
        if(!newFileName.getParentFile().exists()){
            newFileName.mkdir();
        }
        // 7、将用户上传的图片，持久化 存储到服务器磁盘
        try {
            file.transferTo(newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 8、将路径返回
        return "http://api.shop.com/"+fileName;
    }

    // 上传到阿里云服务器
    @PostMapping("/oss")  // 这里和之前写的没有区别，不需要request了
    public String oSSUpload(@RequestParam("file") MultipartFile uploadFile){
        System.out.println("oss执行");
        // 1、定义一个路径先
        String imagePath = null;
        // 2、获取文件的后缀名 比如.jpg
        String suffix = uploadFile.getOriginalFilename()
                .substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        // 3、使用uuid 随机生成一个文件名称，避免中文乱码 以及 文件名重复
        String fileName = UUID.randomUUID() + suffix;
        // 4、文件的路径就是 getFolder上传的文件夹 + 文件的名称
        String filePath = aliyunConifg.getFolder() + "/" + fileName;


        try {
            // 5、上传到阿里云
            ossClient.putObject(aliyunConifg.getBucketName(),filePath,new ByteArrayInputStream(uploadFile.getBytes()));
            // 6、获取完成的路径，提供给用户访问 就是 阿里云的前缀 + 文件的全路径
            imagePath = aliyunConifg.getUrlPrefix()+"/"+filePath;

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(imagePath);

        return imagePath;
    }





}
