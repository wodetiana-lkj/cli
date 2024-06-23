package top.tsview.cli.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.tsview.cli.config.intercept.MDCInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private MDCInterceptor mdcInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mdcInterceptor).addPathPatterns("/**");
    }
}
