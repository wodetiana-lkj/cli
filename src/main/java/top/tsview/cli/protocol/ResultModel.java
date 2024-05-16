package top.tsview.cli.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultModel<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4413338081770739054L;

    private Integer code;

    private String message;

    private T data;

    public static <R> ResultModel<R> ok(R data) {
        return new ResultModel<>(200, null, data);
    }

    public static <R> ResultModel<R> failure(Integer code, String message) {
        return new ResultModel<>(code, message, null);
    }
}
