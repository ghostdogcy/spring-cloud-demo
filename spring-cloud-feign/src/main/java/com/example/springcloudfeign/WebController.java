package com.example.springcloudfeign;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    HelloService helloService;

    @HystrixCommand(fallbackMethod = "serviceFailure")
    @GetMapping(value = "/hello")
    public String sayHello()
    {
        return helloService.sayHello();
    }

    public String serviceFailure()
    {
        return "Hello service is not available!";
    }

}
