package top.tsview.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 测试入参
 */
@Data
public class TestDTO implements Serializable {
    private static final long serialVersionUID = 6537281918303139312L;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 时间
     */
    private LocalDateTime dateTime;
}
