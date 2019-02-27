package idata.dmp.web.controller;

import idata.dmp.params.UserParam;
import idata.dmp.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-25 23:16
 **/
@RestController
@Slf4j
public class TestController {


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(String name) {
        String greeting;
        if (StringUtils.isNotBlank(name)) {
            greeting =  "hello , " + name;
        }else {
            greeting =  "hello world";
        }

        System.out.println(greeting);
        return greeting;
    }

    @PostMapping("/login")
    public void login(@RequestBody UserParam user){
        CheckUtil.isNull(user,"传入参数为空");
        CheckUtil.isBlank(user.getUserName(),"用户名为空");
        CheckUtil.isBlank(user.getPassword(),"密码为空");
    }



}
