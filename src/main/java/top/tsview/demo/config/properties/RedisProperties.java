package top.tsview.demo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.RedisPassword;

import java.util.Collections;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    private List<String> server = Collections.singletonList("localhost");

    private Integer port = 6379;

    private String password = String.valueOf(RedisPassword.none());

    private Integer database = 0;

}
