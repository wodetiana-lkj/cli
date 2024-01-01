package top.tsview.demo.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.tsview.demo.protocol.ResultModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class UnAuthorizedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("have not authorization", accessDeniedException);
        HttpStatus unauthorized = HttpStatus.FORBIDDEN;
        response.getWriter().println(ResultModel.failure(unauthorized.value(), unauthorized.getReasonPhrase()));
    }
}
