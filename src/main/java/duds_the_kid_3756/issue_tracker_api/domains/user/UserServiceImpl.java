package duds_the_kid_3756.issue_tracker_api.domains.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        User postedUser = null;

        try {
            postedUser = userRepository.save(user);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
        }

        return postedUser;
    }

    @Override
    public User findByEmail(String email) {

        User foundUser = null;

        try {
            foundUser = userRepository.findByEmail(email);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
        }

        return foundUser;
    }
}
