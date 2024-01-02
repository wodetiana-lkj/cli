package top.tsview.demo.config.handler;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import top.tsview.demo.entity.UserDetailsEntity;
import top.tsview.demo.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authValue = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasText(authValue)) {
            String token = getToken(authValue);
            // TODO 获取Claims
            UserDetailsEntity userDetails = JwtUtil.verifyToken(token, UserDetailsEntity.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
        SecurityContextHolder.clearContext();
    }

    private String getToken(String authValue) {
        if (authValue.startsWith(AUTH_PREFIX)) {
            return authValue.replaceFirst(AUTH_PREFIX, "");
        }
        return authValue;
    }
}
