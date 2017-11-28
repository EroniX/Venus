package venus.logic.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import venus.dal.model.User;

import java.util.List;

public class UserDTO {
    private String username;
    private String password;
    private String email;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String username, String email, List<String> roles) {
        this.setUsername(username);
        this.setEmail(email);
        this.setRoles(roles);
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User toUser() {
        return new User(
            this.getUsername(),
            new BCryptPasswordEncoder().encode(this.getPassword()),
            this.getEmail()
        );
    }

    public static UserDTO create(User user) {
        return new UserDTO(
            user.getUsername(),
            user.getEmail(),
            user.getRoleNames()
        );
    }
}
