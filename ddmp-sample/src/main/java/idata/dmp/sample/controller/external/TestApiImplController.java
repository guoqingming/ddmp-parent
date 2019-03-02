package idata.dmp.sample.controller.external;

import idata.dmp.anotation.BizLog;
import idata.dmp.sample.facade.api.TestApiController;
import idata.dmp.utils.CheckUtil;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: ddmp-parent
 * @description:
 * @author: guoqingming
 * @create: 2019-02-28 18:27
 **/
@RestController
public class TestApiImplController implements TestApiController {

    @Override
    @BizLog("打招呼")
    public String hello(@ApiParam(value = "名称" )  String name,  @ApiParam(value = "词语" ) String word) {
        String greeting;
        if (StringUtils.isNotBlank(name)) {
            greeting =  "hello  "+ word +" , "  + name;
        }else {
            greeting =  "hello world";
        }

        return greeting;
    }

    @Override
    public void upload(MultipartFile file) {
        CheckUtil.isNull(file,"文件为空");
    }


}
