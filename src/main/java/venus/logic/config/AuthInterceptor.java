package venus.logic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import venus.logic.annotations.Authenticated;
import venus.logic.annotations.NotAuthenticated;
import venus.security.service.SecurityService;
import venus.logic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Boolean authenticated = securityService.isAuthenticated();

        // Authenticated annotation
        if(hasAnnotation(handlerMethod, Authenticated.class) && !authenticated) {
            response.setStatus(401);
            return false;
        }

        // NotAuthenticated annotation
        if(hasAnnotation(handlerMethod, NotAuthenticated.class) && authenticated) {
            response.setStatus(403);
            return false;
        }

        return true;
    }

    private <A extends Annotation> Boolean hasAnnotation(HandlerMethod handler, Class<A> annotation) {
        return handler.getMethodAnnotation(annotation) != null;
    }
}