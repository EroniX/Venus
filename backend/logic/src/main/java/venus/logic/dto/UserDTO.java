package venus.logic.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import venus.dal.model.User;

public class UserDTO {
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncodedPassword() {
        return new BCryptPasswordEncoder().encode(this.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toUser() {
        return new User(
            this.getUsername(),
            this.getEncodedPassword(),
            this.getEmail()
        );
    }
}
