package com.app.framework.core.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by yangyijun on 2018/5/11.
 */
@Configuration
public class FileUploadConfig {
    @Value("${fileUpload.singleMaxSize}")
    private String singleMaxSize;
    @Value("${fileUpload.totalMaxSize}")
    private String totalMaxSize;

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(singleMaxSize); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(totalMaxSize);
        return factory.createMultipartConfig();
    }
}
