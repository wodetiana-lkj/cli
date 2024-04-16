package top.tsview.cli.utils;

import jakarta.annotation.Nonnull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static <T> T getBean(Class<T> aClass) {
        return CONTEXT.getBean(aClass);
    }

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
}
