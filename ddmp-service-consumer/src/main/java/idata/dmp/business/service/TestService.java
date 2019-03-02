package idata.dmp.business.service;

import idata.dmp.business.bean.MpUser;
import idata.dmp.business.consumer.*;
import cn.hutool.json.JSONUtil;
import idata.dmp.business.mapper.MpUserMapper;
import idata.dmp.sample.facade.param.UserParam;
import idata.dmp.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 21:22
 **/
@Service
@Slf4j
public class TestService {

    @Resource
    private MpUserMapper mpUserMapper;


    @Autowired
    private HelloConsumer helloConsumer;

    public Result test(String name,String word) {
        return helloConsumer.hello(name,word);
    }

    public Result login(UserParam userParam) {
        Result login = helloConsumer.login(userParam);
        log.info("接口返回结果： {}",JSONUtil.toJsonStr(login));
        return login;
    }


    public List<MpUser> listUser() {
        return mpUserMapper.listUsers();
    }
}
