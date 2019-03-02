package idata.dmp.utils;


import idata.dmp.exception.BizException;

import java.util.Collection;
import java.util.Objects;

/**
 * @program:
 * @description: 用于参数检查
 * @author: guoqingming
 * @create: 2018-12-10 16:02
 **/
public class CheckUtil {

    /**
     * 检查字符串是否为空
     * @param str
     * @param msg
     */
    public static void isBlank(String str,String msg){
        if(null == str || "".equals(str)){
            throw new BizException(msg);
        }
    }

    /**
     * 检查表达式是否为真
     * @param expression
     * @param msg
     */
    public static void isTrue(boolean expression,String msg){
        if(expression){
            throw new BizException(msg);
        }
    }

    /**
     * 检查对象是否为空
     * @param obj
     * @param msg
     */
    public static void isNull(Object obj, String msg) {
        if(Objects.isNull(obj)){
            throw new BizException(msg);
        }
    }

    /**
     * 检查集合是否为空及集合是否有元素
     * @param c
     * @param msg
     */
    public static void isEmpty(Collection c, String msg) {
        if(c == null || c.isEmpty()){
            throw new BizException(msg);
        }
    }
}
