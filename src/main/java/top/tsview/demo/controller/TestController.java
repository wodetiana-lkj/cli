package top.tsview.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.tsview.demo.model.ResultModel;
import top.tsview.demo.model.dto.TestDTO;
import top.tsview.demo.model.vo.TestVO;

import javax.validation.Valid;

/**
 * 测试控制器
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/test/")
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultModel<TestVO> test1(@Valid TestDTO dto) {
        return new ResultModel<>();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultModel<TestVO> test2(@Valid @RequestBody TestDTO dto) {
        return new ResultModel<>();
    }

}
