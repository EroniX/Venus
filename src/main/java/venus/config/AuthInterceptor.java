package venus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import venus.model.User;
import venus.service.SecurityService;
import venus.service.annotations.Authenticated;
import venus.service.annotations.NotAuthenticated;
import venus.service.annotations.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Boolean authenticated = securityService.isAuthenticated();

        if(hasAnnotation(handlerMethod, Authenticated.class) && !authenticated) {
            response.setStatus(401);
            return false;
        }

        if(hasAnnotation(handlerMethod, NotAuthenticated.class) && authenticated) {
            response.setStatus(403);
            return false;
        }

        List<String> routeRoles = getRoles((HandlerMethod) handler);
        if (routeRoles.isEmpty()) {
            return true;
        }

        if (authenticated) {
            User user = securityService.getUser();
            if(user.getRoleNames().containsAll(routeRoles)) {
                return true;
            }
        }

        response.setStatus(401);
        return false;
    }

    private List<String> getRoles(HandlerMethod handler) {
        Role role = handler.getMethodAnnotation(Role.class);
        return role == null
                ? Collections.emptyList()
                : Arrays.asList(role.value());
    }

    public <A extends Annotation> Boolean hasAnnotation(HandlerMethod handler, Class<A> annotation) {
        return handler.getMethodAnnotation(annotation) != null;
    }
}