package com.example.facerecognitiondemo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        //添加视图控制器
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/success").setViewName("success");
    }

}
