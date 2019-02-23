package com.example.springcloudeurekaprovider3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudEurekaProvider3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaProvider3Application.class, args);
	}

	@Value("${server.port}")
	private String port;

	@RequestMapping("/hello")
	public String hello()
	{
		return "hello world from port:" + port;
	}

}
