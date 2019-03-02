package idata.dmp.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-03-01 17:09
 **/
@Configuration
public class CommonConfig {

    @Bean
    public LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer(){
        return new LocalVariableTableParameterNameDiscoverer();
    }
}
