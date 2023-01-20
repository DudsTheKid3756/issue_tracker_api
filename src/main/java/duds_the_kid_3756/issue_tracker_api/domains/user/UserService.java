package duds_the_kid_3756.issue_tracker_api.domains.user;

public interface UserService {

    User createUser(User user);

    User findByEmail(String email);
}
