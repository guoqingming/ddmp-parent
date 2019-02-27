package idata.dmp.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-26 21:22
 **/
@Service
public class TestService {

    @Autowired
    private HelloConsumer helloConsumer;

    public void test() {

    }
}
