package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lichao01 on 2019/3/4.
 */
@RestController
public class HelloController {

    @RequestMapping("provider/hello")
    public String say(@RequestParam("name")String name){
        return "hello :"+name;
    }

    @RequestMapping("zuul-route-test")
    public String zuulRouteTest(){
        return "zuul route test success";
    }
}
