package venus.logic.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import venus.dal.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


public final class UserDTO {

    private static final String ROLE_USER = "ROLE_USER";

    private final String email;
    @Size(min = 8, max = 100)
    private final String password;
    private final String username;

    public UserDTO(@JsonProperty("password") String password,
                   @JsonProperty("username") String username) {
        this.email = "vaczi8@gmail.com";
        this.password = password;
        this.username = username;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getEncodedPassword() {
        return Optional.ofNullable(password).map(p -> new BCryptPasswordEncoder().encode(p));
    }

    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setEmail(email);
        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password, getAuthorities());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> ROLE_USER);
    }

}