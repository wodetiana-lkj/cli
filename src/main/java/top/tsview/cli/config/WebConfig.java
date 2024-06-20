package top.tsview.cli.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 匹配所有请求路径
                .allowedOrigins("*") // 允许所有源
                .allowedMethods("*") // 允许跨域的RESTFUL
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(false) // 是否允许携带cookie
                .maxAge(3600); // 预检请求的有效期，单位秒
    }

}

