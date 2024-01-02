package top.tsview.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class JwtUtilTests {

    @Test
    void token() {
        String token = getToken();
    }

    @Test
    void validate() {
        String claims = JwtUtil.verifyToken(getToken(), String.class);
        log.info("payload => {}", claims);
    }

    private String getToken() {
        String token = JwtUtil.getToken("abc123");
        log.info("token => {}", token);
        return token;
    }

}
