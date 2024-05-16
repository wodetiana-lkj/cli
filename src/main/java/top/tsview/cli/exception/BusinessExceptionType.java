package top.tsview.cli.exception;

public enum BusinessExceptionType {

	DEMO(1000, "样例异常");

	public final int code;
	public final String message;

	BusinessExceptionType(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
