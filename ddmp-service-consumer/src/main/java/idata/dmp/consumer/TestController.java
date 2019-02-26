package idata.dmp.consumer;

//import idata.dmp.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 12:49
 **/
@RestController
public class TestController {

    @Resource
    private HelloConsumer  helloConsumer;

    @GetMapping("/test")
    public String test(@RequestParam(required = false,name = "name") String name) {
       return helloConsumer.hello(name);

    }

    @GetMapping("/test1")
    public String test1(String name) {
        return "xxxx world";
    }

}
