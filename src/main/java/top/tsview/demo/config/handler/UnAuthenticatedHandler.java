package top.tsview.demo.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.tsview.demo.protocol.ResultModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class UnAuthenticatedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("authenticate error", authException);
        HttpStatus unAuth = HttpStatus.UNAUTHORIZED;
        response.getWriter().println(ResultModel.failure(unAuth.value(), unAuth.getReasonPhrase()));
    }
}
