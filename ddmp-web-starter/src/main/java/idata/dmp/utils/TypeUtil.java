package idata.dmp.utils;

/**
 * @program: webabc
 * @description:
 * @author: guoqingming
 * @create: 2018-12-18 11:55
 **/
public class TypeUtil {

    /**
     *
     * @param className
     * @return
     */
    public static boolean isBaseType(Class className) {
        if (className.equals(Integer.class) ||
                className.equals(Byte.class) ||
                className.equals(Long.class) ||
                className.equals(Double.class) ||
                className.equals(Float.class) ||
                className.equals(Character.class) ||
                className.equals(Short.class) ||
                className.equals(Boolean.class)||
                className.equals(String.class)) {


            return true;
        }
        return false;
    }
}
