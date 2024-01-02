package top.tsview.demo.protocol.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TestDTO implements Serializable {
    private static final long serialVersionUID = 4664328137181821446L;

    @NotBlank(message = "名称不为空")
    private String name;

    private LocalDateTime create;
}
