package idata.dmp.utils.result;

/**
 * 自定义系统异常类
 * @author cht
 *
 */
@SuppressWarnings("serial")
public class ExceptionResult extends Exception {

	// 系统统一使用的结果类，包括了 提示信息类型和信息内容
	private Result result;

	public ExceptionResult(Result result) {
		super(result.getMsg());
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Result result) {
		this.result = result;
	}


}
