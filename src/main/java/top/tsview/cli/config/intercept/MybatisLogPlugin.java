package top.tsview.cli.config.intercept;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Properties;


@Slf4j
@Intercepts({
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
		@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Component
public class MybatisLogPlugin implements Interceptor {

	/**
	 * 切面方法
	 *
	 * @param invocation 切面方法
	 * @return Object result
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// log.info("mybatis before method : {}", invocation.getMethod());
		Object[] params = invocation.getArgs();
		if (ObjectUtils.isEmpty(params)) {
			throw new IllegalArgumentException();
		}
		// sql打印
		Object firstParam = params[0];
		if (firstParam instanceof MappedStatement statement) {
			BoundSql boundSql = statement.getBoundSql(params[1]);
			log.info(boundSql.getSql()
					.replaceAll("[\r\n]", " ")
					.replaceAll(" +", " "));
		}

		Object result = invocation.proceed();
		log.info("mybatis after method : {}", invocation.getMethod());
		return result;
	}
}
