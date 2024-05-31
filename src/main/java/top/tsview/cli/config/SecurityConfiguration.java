package top.tsview.cli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * 委托线程池，继承了SpringSecurity的context上下文
     * @return
     */
    @Bean
    public Executor securityExecutor() {
        ExecutorService executorService = Executors.newWorkStealingPool(5);
        return new DelegatingSecurityContextExecutor(executorService, SecurityContextHolder.getContext());
    }

}





