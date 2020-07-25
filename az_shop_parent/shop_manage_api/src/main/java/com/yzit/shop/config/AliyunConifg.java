package com.yzit.shop.config;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
// 表示当前类是一个配置类，也就是.xml文件
@Configuration
// 属性文件资源 value = 属性文件的路径(就是在哪方法，classpath代表在src下) application-oss是文件名称
@PropertySource(value = {"classpath:application-oss.properties"})
// 这个配置的意思是  application-oss的属性文件里，所有属性的前缀叫aliyun. 相当于在类的属性上@value("aliyun.endpoint")
@ConfigurationProperties(prefix = "aliyun")
public class AliyunConifg {

    // @Value("aliyun.endpoint") 优化使用@ConfigurationProperties 替换 @value
    private String endpoint;
    // @Value("aliyun.accessKeyId") 优化使用@ConfigurationProperties 替换 @value
    private String accessKeyId;
    // @Value("aliyun.accessKeySecret") 优化使用@ConfigurationProperties 替换 @value
    private String accessKeySecret;
    private String bucketName;
    private String folder;

    private String urlPrefix;

    // 创建一个实例，交给spring管理 ，用处就是 能够实现 依赖注入 @Autowired
    @Bean
    public OSS oSSClient(){
        //return new OSSClient(endpoint,accessKeyId,accessKeySecret);
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    // get set
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }
}
