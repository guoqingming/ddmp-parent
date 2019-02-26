package idata.dmp.exception;

import lombok.Getter;

/**
 * @program: result-spring-boot-starter
 * @description:
 * @author: guoqingming
 * @create: 2018-12-12 21:44
 **/
@Getter
public class InternalException extends RuntimeException {

    private int code;

    private String msg;

    public InternalException( String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
    }


}
