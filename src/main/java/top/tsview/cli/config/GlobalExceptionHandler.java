package top.tsview.cli.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.tsview.cli.exception.BusinessException;
import top.tsview.cli.protocol.ResultModel;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResultModel<?> validArgs(MethodArgumentNotValidException e) {
		FieldError fieldError = e.getFieldError();
		String errMsg = "field{%s}:{%s}".formatted(Objects.requireNonNull(fieldError).getField(), fieldError.getDefaultMessage());
		return ResultModel.failure(HttpStatus.BAD_REQUEST.value(), errMsg);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {Exception.class})
	public ResultModel<?> businessError(BusinessException e) {
		log.error("business :", e);
		return ResultModel.failure(e.getCode(), e.getMessage());
	}

}
