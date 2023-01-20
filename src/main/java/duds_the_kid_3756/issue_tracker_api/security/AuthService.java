package duds_the_kid_3756.issue_tracker_api.security;

public interface AuthService {

    AuthToken login(Credential credential);
}
