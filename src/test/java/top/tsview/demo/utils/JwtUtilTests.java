package top.tsview.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JwtUtilTests {

    @Test
    public String getToken() {
        String token = JwtUtil.getToken("abc123");
        log.info("token => {}", token);
        return token;
    }

    @Test
    public void validate() {
        String claims = JwtUtil.verifyToken(getToken());
        log.info("payload => {}", claims);
    }

}
