package duds_the_kid_3756.issue_tracker_api.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Credential {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    public Credential() {
    }

    public Credential(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
