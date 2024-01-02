package top.tsview.demo.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.tsview.demo.protocol.ResultModel;
import top.tsview.demo.protocol.dto.TestDTO;
import top.tsview.demo.protocol.vo.TestVO;
import top.tsview.demo.utils.JwtUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private Gson gson;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResultModel<TestVO> get(TestDTO dto) {
        log.info("request => {}", dto);
        return ResultModel.ok(new TestVO(1L, "test", LocalDateTime.now()));
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResultModel<TestVO> post(@RequestBody TestDTO dto) {
        log.info("request => {}", dto);
        return ResultModel.ok(new TestVO(1L, "test", LocalDateTime.now()));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel<String> login(@RequestBody @Valid TestDTO dto) {
        return ResultModel.ok(JwtUtil.getToken(gson.toJson(dto)));
    }

}
