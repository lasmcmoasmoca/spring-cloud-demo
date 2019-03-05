package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lichao01 on 2019/3/4.
 */
@RestController
@RefreshScope
public class ConsumerController {
   @Autowired
   private HelloConsumerService helloConsumerService;
   /**
    * http://localhost:9092/actuator/bus-refresh
    */


   @Value("${config.test}")
   String configString;

   @RequestMapping("hello")
   public String hello(@RequestParam("name")String name){
      return helloConsumerService.hello(name);
   }

   @RequestMapping("pb/zuul-auth-test")
   public String zuulAuthTest(){
      return "ok -" +configString;
   }
}
