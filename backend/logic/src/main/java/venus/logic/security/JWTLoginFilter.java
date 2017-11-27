package venus.logic.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import venus.logic.dto.UserDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    private HashSet<GrantedAuthority> toGrantedAuthorities(List<String> roleNames) {
        //return new HashSet<>(0);
        HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>(roleNames.size());
        for (String roleName : roleNames) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
        }
        return grantedAuthorities;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        UserDTO userDTO = new ObjectMapper()
                .readValue(req.getInputStream(), UserDTO.class);

        ArrayList<String> asd = new ArrayList<String>();
        asd.add("TRAINING_LIST_REGISTERED");
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword(),
                        toGrantedAuthorities(asd)
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService
                .addAuthentication(res, auth.getName());
    }
}