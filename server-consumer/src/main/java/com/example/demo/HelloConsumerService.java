package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lichao01 on 2019/3/4.
 */
@Service
public class HelloConsumerService {

    private static String HELLO_URL = "http://server-provide/provider/hello";
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloServerError")
    public String hello(String name){
        MultiValueMap<String,String> stringMultiValueMap = new LinkedMultiValueMap<String, String>(1);
        stringMultiValueMap.add("name",name);
        return restTemplate.postForObject(HELLO_URL,stringMultiValueMap,String.class);
    }

    public String helloServerError(String name){
        return "hello server error" + name;
    }
}
