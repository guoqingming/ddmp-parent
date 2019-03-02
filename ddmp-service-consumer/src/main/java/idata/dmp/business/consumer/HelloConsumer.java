package idata.dmp.business.consumer;

import feign.Headers;
import feign.Logger;
import feign.Param;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import idata.dmp.sample.facade.param.UserParam;
import idata.dmp.utils.result.Result;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 11:50
 **/
@FeignClient(name="service-sample")
public interface HelloConsumer {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    Result hello(@RequestParam("name") String name,@RequestParam("word") String word);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Result login( @RequestBody UserParam user);

    @RequestMapping(value = "/upload",method = RequestMethod.POST,consumes = {"multipart/form-data"})
    Result upload(@RequestBody MultipartFile file);


    class ClientConfiguration {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        @Primary
        @Scope("prototype")
        public Encoder feignEncoder () {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }

        @Bean
        public Logger.Level feignLogger () {
            return Logger.Level.FULL;
        }
    }
}
