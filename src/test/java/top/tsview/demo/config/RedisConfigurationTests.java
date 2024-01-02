package top.tsview.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import top.tsview.demo.protocol.vo.TestVO;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
public class RedisConfigurationTests {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void add() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("test:add", new TestVO(1L, "name", LocalDateTime.now()));
    }

}
