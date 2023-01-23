package duds_the_kid_3756.issue_tracker_api.domains.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Request received for createUser");
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
