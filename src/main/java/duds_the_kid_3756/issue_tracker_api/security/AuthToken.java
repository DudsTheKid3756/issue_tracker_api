package duds_the_kid_3756.issue_tracker_api.security;

public class AuthToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
