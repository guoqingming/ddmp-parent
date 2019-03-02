package idata.dmp.sample.controller.internal;

import cn.hutool.json.JSONUtil;
import idata.dmp.sample.facade.param.UserParam;
import idata.dmp.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
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



    @PostMapping("/login")
    public UserParam login(@RequestBody UserParam user){
        log.info("接口传入参数，{}" ,JSONUtil.toJsonStr(user));
        CheckUtil.isNull(user,"传入参数为空");
        CheckUtil.isBlank(user.getUserName(),"用户名为空");
        CheckUtil.isBlank(user.getPassword(),"密码为空");
        return user;
    }



}
