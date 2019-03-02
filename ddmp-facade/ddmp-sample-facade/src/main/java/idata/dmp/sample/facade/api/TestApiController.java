package idata.dmp.sample.facade.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-28 18:25
 **/
@Api(tags = "测试API")
public interface TestApiController {

    @GetMapping(value = "/hello")
    @ApiOperation(value = "打招呼")
     String hello(String name,String word);

    @RequestMapping(value = "/upload")
    @ApiOperation(value = "测试上传")
    void upload(MultipartFile file);
}
