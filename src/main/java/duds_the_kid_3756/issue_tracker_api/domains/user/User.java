package duds_the_kid_3756.issue_tracker_api.domains.user;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email data was not supplied")
    private String email;

    @NotBlank(message = "Password data was not supplied")
    private String password;

    @NotBlank(message = "Role data was not supplied")
    private String role;

    public User() {
    }

    public User(@NotBlank(message = "Email data was not supplied") String email,
                @NotBlank(message = "Password data was not supplied") String password,
                @NotBlank(message = "Role data was not supplied") String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) && getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
