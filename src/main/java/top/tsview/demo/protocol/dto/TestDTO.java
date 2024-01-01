package top.tsview.demo.protocol.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TestDTO implements Serializable {
    private static final long serialVersionUID = 4664328137181821446L;

    private String name;

    private LocalDateTime create;
}
