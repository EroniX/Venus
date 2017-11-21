package venus.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import venus.logic.security.JWTAuthenticationFilter;
import venus.logic.security.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/register").permitAll();

        http.addFilterBefore(new JWTLoginFilter("/api/user/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }
}