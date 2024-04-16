package top.tsview.cli.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.tsview.cli.protocol.ResultModel;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResultModel<?> validArgs(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getFieldError();
        String errMsg = "field{%s}:{%s}".formatted(fieldError.getField(), fieldError.getDefaultMessage());
        return ResultModel.failure(HttpStatus.BAD_REQUEST.value(), errMsg);
    }

}
