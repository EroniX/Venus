package venus.logic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterDTO extends AccountCredentialsDTO {

    private String email;

    public UserRegisterDTO(
            @JsonProperty("username")String username,
            @JsonProperty("password")String password,
            @JsonProperty("email")String email) {
        super(username, password);

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
