package top.tsview.cli.config.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanPostProcessInterceptor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.info("before init bean {}==={}", bean.getClass(), beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.info("after init bean {}==={}", bean.getClass(), beanName);
		return bean;
	}
}
