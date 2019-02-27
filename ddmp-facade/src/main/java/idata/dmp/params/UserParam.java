package idata.dmp.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: ddmp-parent
 * @description: 测试
 * @author: guoqingming
 * @create: 2019-02-24 23:22
 **/
@Data
public class UserParam implements Serializable {

    /**
     *
     */
    private String userName;

    /**
     *
     */
    private String password;
}
