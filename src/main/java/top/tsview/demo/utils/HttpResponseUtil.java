package top.tsview.demo.utils;

import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import top.tsview.demo.protocol.ResultModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class HttpResponseUtil {

    public static synchronized void handleJson(HttpServletResponse response, ResultModel<?> resultModel) {
        Gson gson = SpringUtil.getBean(Gson.class);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().write(gson.toJson(resultModel));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponseUtil() {
    }

}
