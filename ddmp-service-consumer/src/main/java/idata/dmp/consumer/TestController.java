package idata.dmp.consumer;

//import idata.dmp.utils.result.Result;
import idata.dmp.params.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 12:49
 **/
@RestController
@Api(tags = "测试")
public class TestController {

    @Resource
    private HelloConsumer  helloConsumer;

    @GetMapping("/test")
    @ApiOperation(value = "测试请求")
    public String test(@ApiParam(name = "名称") @RequestParam(required = false,name = "name") String name) {
       return helloConsumer.hello(name);

    }

    @PostMapping("/test1")
    public String login(@RequestBody UserParam user) {
        return helloConsumer.login(user);
    }



}
