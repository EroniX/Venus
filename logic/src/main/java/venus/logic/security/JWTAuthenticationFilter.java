package venus.logic.security;

import venus.logic.security.TokenAuthenticationService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        try {
            Authentication authentication = TokenAuthenticationService
                    .getAuthentication((HttpServletRequest)request);

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }
        catch(Exception e) {
            int i = 0;
        }
    }
}