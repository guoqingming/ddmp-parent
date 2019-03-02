//package idata.dmp.business.consumer;
//
//import feign.codec.Encoder;
//import feign.form.spring.SpringFormEncoder;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.cloud.netflix.feign.support.SpringEncoder;
//import org.springframework.context.annotation.Bean;
//
//@FeignClient(
//    name = "file-upload-service",
//    configuration = FileUploadServiceClient.MultipartSupportConfig.class
//)
//public interface FileUploadServiceClient extends IFileUploadServiceClient {
//
//  public class MultipartSupportConfig {
//
//    @Autowired
//    private ObjectFactory<HttpMessageConverters> messageConverters;
//
//    @Bean
//    public Encoder feignFormEncoder () {
//      return new SpringFormEncoder(new SpringEncoder(messageConverters));
//    }
//  }
//}