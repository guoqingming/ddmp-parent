package idata.dmp.sample.facade.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: ddmp-parent
 * @description: 测试
 * @author: guoqingming
 * @create: 2019-02-24 23:22
 **/
@Data
@ApiModel
public class UserParam implements Serializable {

    /**
     *
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     *
     */
    @ApiModelProperty("密码")
    private String password;
}
