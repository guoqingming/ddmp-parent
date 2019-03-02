package idata.dmp;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2Doc
@MapperScan(value = "idata.dmp.business.mapper")
public class DdmpServiceConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(DdmpServiceConsumerApplication.class, args);
    }

}
