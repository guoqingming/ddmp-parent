package idata.dmp.web.config;

import idata.dmp.utils.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;


public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {

  private HandlerMethodReturnValueHandler proxyObject;

  public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler proxyObject) {
    this.proxyObject = proxyObject;
  }

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return proxyObject.supportsReturnType(returnType);
  }

  @Override
  public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest) throws Exception {
//    class returnClass = returnType.getParameterType();
    String className = returnType.getParameterType().getSimpleName();
    if("void".equals(className)){
      proxyObject.handleReturnValue(Result.createSuccess(), returnType, mavContainer, webRequest);
    }else if("Result".equals(className)){

      //不做处理
    } else {
      proxyObject.handleReturnValue(Result.createSuccessData(returnValue), returnType, mavContainer, webRequest);
    }


  }

}