package idata.dmp.business.controller.internal;

import idata.dmp.anotation.BizLog;
import idata.dmp.business.bean.MpUser;
import idata.dmp.business.consumer.HelloConsumer;
import idata.dmp.business.service.*;
import idata.dmp.sample.facade.param.UserParam;
import idata.dmp.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 12:49
 **/
@RestController
@Api(tags = "测试")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private HelloConsumer helloConsumer;

    @Resource
    private Tracer tracer;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation(value = "测试请求")
    @BizLog("测试请求")
    public Result test(/*@ApiParam(value = "名称")*/ @RequestParam(required = false) String name,String word) {
        log.info("测试开始---------------- {}",tracer.getCurrentSpan().getTraceId());
        return testService.test(name,word);

    }

    @RequestMapping(value = "/test1",method = RequestMethod.POST)
    @BizLog("测试登录请求")
    public Result login( UserParam user) {
        return testService.login(user);
    }



    @GetMapping("/test2")
    @BizLog("测试请求1")
    public List<MpUser> listUsers() {
        return testService.listUser();
    }


    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        return helloConsumer.upload(file);
    }
}
