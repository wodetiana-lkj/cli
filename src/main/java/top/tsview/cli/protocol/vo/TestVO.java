package top.tsview.cli.protocol.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -310876948119132135L;

    private Long id;

    private String name;

    private LocalDateTime create;
}
