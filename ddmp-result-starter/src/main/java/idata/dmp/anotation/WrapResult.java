package idata.dmp.anotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface WrapResult {

    String basePackage() default "";
}
