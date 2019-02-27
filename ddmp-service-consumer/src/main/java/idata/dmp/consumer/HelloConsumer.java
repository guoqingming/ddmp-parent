package idata.dmp.consumer;

//import idata.dmp.utils.result.Result;
import idata.dmp.params.UserParam;
import idata.dmp.utils.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 11:50
 **/
@FeignClient(name="service-sample")
public interface HelloConsumer {

    @RequestMapping(value = "/hello",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    String hello(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    String login( @RequestBody UserParam user);

}
