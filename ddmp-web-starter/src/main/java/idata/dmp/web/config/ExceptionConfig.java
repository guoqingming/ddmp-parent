package idata.dmp.web.config;

import idata.dmp.exception.ExceptionHandlerAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: result-spring-boot-starter
 * @description:
 * @author: guoqingming
 * @create: 2018-12-12 23:18
 **/
@Configuration
public class ExceptionConfig {

    @Bean
    public ExceptionHandlerAdvice exceptionHandlerAdvice(){
        return  new ExceptionHandlerAdvice();
    }
}
