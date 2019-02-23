package com.example.springcloudfeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@FeignClient(value = "eureka-provider")
public interface HelloService {

    @GetMapping(value = "/hello")
    String sayHello();

}
