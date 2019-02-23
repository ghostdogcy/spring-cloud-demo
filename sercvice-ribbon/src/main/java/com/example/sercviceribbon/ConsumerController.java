package com.example.sercviceribbon;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "serviceFailure")
    @RequestMapping("/hello")
    public String hello()
    {
        return restTemplate.getForObject("http://EUREKA-PROVIDER/hello", String.class);
    }


    public String serviceFailure()
    {
        return "Hello service is not available!";
    }
}
