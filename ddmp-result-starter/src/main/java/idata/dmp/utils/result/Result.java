package idata.dmp.utils.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Collection;

/**
 *
  * 类名称:  	Result
  * 类描述:	返回结果封装
  * 创建人:  	kaixin
  * 创建时间: 	2018年3月5日 下午4:07:55
 */
public class Result<T> implements Serializable{

    private static final long serialVersionUID = -6550960756413850294L;

    /**
     * 结果体
     */
    private T data;

    /**
     * 状态码
     */
    private int code;


    /**
     * 信息
     */
    private String msg;

    public Result() {
        super();
    }

    /**
     * @param data
     * @param code
     * @param msg
     */
    public Result(int code, T data, String msg) {
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }


    private Result(Integer code) {
        this.code = code;
    }

    public static Result create(Integer code) {
        return new Result(code);
    }

    /**
     * 方法说明：	返回成功
     * 创建时间：	2018年3月31日 下午2:58:02
     * 创建人：	kele
     * 修改记录：	修改人	修改记录	2018年3月31日 下午2:58:02
     * @return
     */
    public static Result createSuccess() {
        return create(ResultCode.SUCCESS);
    }

    /**
     * 方法说明：	包含内容的成功结果
     * 创建时间：	2018年3月31日 下午2:57:44
     * 创建人：	kele
     * 修改记录：	修改人	修改记录	2018年3月31日 下午2:57:44
     * @param data
     * @return
     */
    public static <T>Result<T> createSuccessData(T data) {

        return createSuccess().setData(data).setMsg("成功");
    }

    public static Result createSuccessMsgCode(int msgCode) {
        return createSuccessDataMsgCode("");
    }

    public static Result createSuccessDataMsgCode(Object data) {

        return createSuccess().setData(data).setMsg("成功");
    }

    public static Result createFail() {
        return create(ResultCode.FAIL);
    }

    public static Result createFail(String msg) {
        return create(ResultCode.FAIL).setMsg(msg);
    }

    public static Result createFailData(Object data) {
        return createFail().setData(data);
    }

    public static Result createFailMsgCode(int msgCode) {
        return createFail().setCode(msgCode);
    }

//    public static Result createFailDataMsgCode(Object data, int msgCode) {
//
//        return createFail().setData(data).setMsg(msg);
//    }

    /**
     * 抛出异常
     * @param result
     * @throws ExceptionResult
     */
    public static void throwExcepion(Result result) throws ExceptionResult{
        throw new ExceptionResult(result);
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
