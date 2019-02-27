package idata.dmp.consumer;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2Doc
public class DdmpServiceConsumerApplication {

//    @Bean
//    public MapFormHttpMessageConverter mapFormHttpMessageConverter(MultipartFileHttpMessageConverter multipartFileHttpMessageConverter) {
//        MapFormHttpMessageConverter mapFormHttpMessageConverter = new MapFormHttpMessageConverter();
//        mapFormHttpMessageConverter.addPartConverter(multipartFileHttpMessageConverter);
//        return mapFormHttpMessageConverter;
//    }
//
//    @Bean
//    public MultipartFileHttpMessageConverter multipartFileHttpMessageConverter() {
//        return new MultipartFileHttpMessageConverter();
//    }
    public static void main(String[] args) {
        SpringApplication.run(DdmpServiceConsumerApplication.class, args);
    }

}
