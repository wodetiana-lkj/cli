package top.tsview.cli.exception;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = -7527894058015830601L;

	private int code;
	private String message;

	private BusinessException() {}

	public static BusinessException of(BusinessExceptionType type, @Nonnull Exception origin) {
		BusinessException exception = new BusinessException();
		exception.setCode(type.code);
		exception.setMessage(type.message);
		exception.initCause(origin);
		return exception;
	}

}
