package idata.dmp.exception;

import lombok.Getter;

/**
 * @program: result-spring-boot-starter
 * @description:
 * @author: guoqingming
 * @create: 2018-12-12 21:44
 **/
@Getter
public class AuthException extends RuntimeException {

    private int code;

    private String msg;

    public AuthException(String msg) {
        super(msg);
        this.code = 401;
        this.msg = msg;
    }


}
