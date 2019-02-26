package idata.dmp.sample2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-25 23:16
 **/
@RestController
@Slf4j
public class TestController {


    @GetMapping("/hello")
    public String hello(String name) {
        String greeting = "";
        if (StringUtils.isNotBlank(name)) {
            greeting =  "hello2 , " + name;
        }
        greeting =  "hello2 world";
        System.out.println(greeting);
        return greeting;
    }



}
