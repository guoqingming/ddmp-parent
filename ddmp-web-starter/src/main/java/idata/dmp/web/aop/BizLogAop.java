package idata.dmp.web.aop;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import idata.dmp.anotation.BizLog;
import idata.dmp.utils.TypeUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: webabc
 * @description:
 * @author: guoqingming
 * @create: 2018-12-17 23:06
 **/
@Aspect
@Component
@Slf4j
public class BizLogAop {

    @Autowired
    private LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer;

    @Pointcut("execution(* idata.dmp.*.controller..*.*(..))")
    public  void executeService(){

    }

    @Around("executeService()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        BizLog bizLog = method.getAnnotation(BizLog.class);
        StringBuffer sb = new StringBuffer();
        if(bizLog != null && StringUtils.isNotBlank(bizLog.value())){
            sb.append("业务 【 " +bizLog.value() + " 】, ");
        }
        sb.append("传入参数： ");
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < parameters.length; i++) {
            ApiParam apiParam = parameters[i].getAnnotation(ApiParam.class);
            boolean baseType = TypeUtil.isBaseType(parameterTypes[i]);
            if(baseType == true){
                if(apiParam != null && StringUtils.isNotBlank(apiParam.value())){
                    list.add(apiParam.value() + " : "+args[i] + "") ;
                }else {
                    list.add(parameterNames[i] + " : " +args[i] + "") ;
                }
            }else if(args[i]instanceof MultipartFile || args[i] instanceof File){
                if(apiParam != null && StringUtils.isNotBlank(apiParam.value())){
                    list.add(apiParam.value() + " : 文件类型参数不做记录 ") ;
                }else {
                    list.add(parameterNames[i] + " : 文件类型参数不做记录 ") ;
                }
            }else {
                if(apiParam != null && StringUtils.isNotBlank(apiParam.value())){
                    list.add(apiParam.value() + " : "+(baseType?args[i]:JSONUtil.toJsonStr(args[i])));
                }else {
                    list.add(parameterNames[i] + " : " +(baseType?args[i]:JSONUtil.toJsonStr(args[i])));
                }
            }

        }
        String join = StrUtil.join(" , ",list);
        sb.append("【 "+join+" 】");
        log.info(sb.toString());

        Object proceed = pjp.proceed(args);
        return proceed;

    }
}
