package idata.dmp.consumer;

//import idata.dmp.utils.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 11:50
 **/
@FeignClient(name="service-a")
public interface HelloConsumer {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello( String name);
}
