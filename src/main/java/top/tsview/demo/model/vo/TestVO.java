package top.tsview.demo.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试出参
 */
@Data
public class TestVO implements Serializable {
    private static final long serialVersionUID = -4011790912890111727L;

    /**
     * 出参名称
     *
     * @mock name
     */
    private String name;
}
