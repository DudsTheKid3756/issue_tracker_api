package duds_the_kid_3756.issue_tracker_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import duds_the_kid_3756.issue_tracker_api.domains.user.User;
import duds_the_kid_3756.issue_tracker_api.domains.user.UserService;
import duds_the_kid_3756.issue_tracker_api.exceptions.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private static boolean userIsAdmin(String role) {
        return role.equals(ADMIN_ROLE_TYPE);
    }

    @Override
    public AuthToken login(Credential credentials) {
        if (credentials.getEmail() == null || credentials.getPassword() == null) {
            throw new Invalid(INVALID_EMAIL_PASSWORD);
        }

        String email = credentials.getEmail();
        String password = credentials.getPassword();

        User user = userService.findByEmail(email);

        if (user == null) {
            throw new Invalid(INVALID_EMAIL_PASSWORD);
        }

        String passwordOnFile = user.getPassword();

        if (!bCryptPasswordEncoder.matches(password, passwordOnFile)) {
            throw new Invalid(INVALID_EMAIL_PASSWORD);
        }

        String userRole = DEVELOPER_ROLE_TYPE;

        if (userIsAdmin(user.getRole())) {
            userRole = ADMIN_ROLE_TYPE;
        }

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String jwtToken =
                JWT.create()
                        .withIssuer(ISSUER)
                        .withClaim(ROLES_ATTRIBUTE, userRole)
                        .withSubject(email)
                        .withIssuedAt(new Date())
                        .sign(algorithm);

        return new AuthToken(jwtToken);
    }
}
