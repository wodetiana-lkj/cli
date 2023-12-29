package top.tsview.demo.model;

import lombok.Data;

@Data
public class ResultModel<T> {

    private Integer code;

    private String message;

    private T data;

}
