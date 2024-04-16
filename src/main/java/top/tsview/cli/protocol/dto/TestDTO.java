package top.tsview.cli.protocol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4664328137181821446L;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "创建时间不能空")
    private LocalDateTime create;
}
