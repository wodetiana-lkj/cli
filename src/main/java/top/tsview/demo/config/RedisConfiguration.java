package top.tsview.demo.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;
import top.tsview.demo.config.properties.RedisProperties;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
public class RedisConfiguration {

    @Bean
    @Primary
    public LettuceConnectionFactory singleFactory(RedisProperties redisProperties) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisProperties.getServer().get(0), redisProperties.getPort());
        configuration.setPassword(redisProperties.getPassword());
        configuration.setDatabase(redisProperties.getDatabase());
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, ? extends Serializable> redisTemplate(LettuceConnectionFactory factory, RedisSerializer<Object> gsonRedisSerializer) {
        factory.afterPropertiesSet();
        RedisTemplate<String, ? extends Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(gsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> gsonRedisSerializer(Gson gson) {
        return new RedisSerializer<Object>() {
            @Override
            public byte[] serialize(Object o) throws SerializationException {
                if (ObjectUtils.isEmpty(o)) {
                    return null;
                }
                return gson.toJson(o).getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                if (bytes == null || bytes.length == 0) {
                    return null;
                }
                return gson.fromJson(new String(bytes), Object.class);
            }
        };
    }

    @Bean
    public LettuceConnectionFactory sslSingleFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl().and()
                .commandTimeout(Duration.ofSeconds(2))
                .shutdownTimeout(Duration.ZERO)
                .build();

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379), clientConfig);
    }

}
