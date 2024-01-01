package top.tsview.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import top.tsview.demo.config.handler.LogoutSuccessHandler;
import top.tsview.demo.config.handler.UnAuthenticatedHandler;
import top.tsview.demo.config.handler.UnAuthorizedHandler;
import top.tsview.demo.utils.SpringUtil;

/**
 * 安全处理配置
 */
@Configuration
public class SecurityConfiguration {

    /**
     * http资源权限管理
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 关闭session存储
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 关闭csrf
                .csrf().disable()
                // 关闭缓存响应头
                .headers().cacheControl().disable().and()
                // 配置过滤认证规则
                .authorizeRequests()
                .antMatchers("/**").anonymous()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling()
                // 匿名用户无权限
                .authenticationEntryPoint(SpringUtil.getBean(UnAuthenticatedHandler.class))
                // 认证过的用户无权限
                .accessDeniedHandler(SpringUtil.getBean(UnAuthorizedHandler.class)).and()
                // 登出处理
                .logout().logoutSuccessHandler(SpringUtil.getBean(LogoutSuccessHandler.class));

        return httpSecurity.build();
    }

    /**
     * 配置静态资源筛选
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring().anyRequest();
    }

}
