package duds_the_kid_3756.issue_tracker_api.controllers;

import duds_the_kid_3756.issue_tracker_api.exceptions.Invalid;
import duds_the_kid_3756.issue_tracker_api.exceptions.ResourceNotFound;
import duds_the_kid_3756.issue_tracker_api.exceptions.ServerError;
import duds_the_kid_3756.issue_tracker_api.models.ERole;
import duds_the_kid_3756.issue_tracker_api.models.Role;
import duds_the_kid_3756.issue_tracker_api.models.User;
import duds_the_kid_3756.issue_tracker_api.payload.request.LoginRequest;
import duds_the_kid_3756.issue_tracker_api.payload.request.PassResetRequest;
import duds_the_kid_3756.issue_tracker_api.payload.request.SignupRequest;
import duds_the_kid_3756.issue_tracker_api.payload.response.JwtResponse;
import duds_the_kid_3756.issue_tracker_api.payload.response.MessageResponse;
import duds_the_kid_3756.issue_tracker_api.repositories.RoleRepository;
import duds_the_kid_3756.issue_tracker_api.repositories.UserRepository;
import duds_the_kid_3756.issue_tracker_api.security.jwt.JwtUtils;
import duds_the_kid_3756.issue_tracker_api.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static duds_the_kid_3756.issue_tracker_api.constants.Paths.*;
import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.ADMIN_ROLE_TYPE;
import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.ROLE_NOT_FOUND;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(BASE_PATH + AUTH_PATH)
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(SIGN_IN_PATH)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        logger.info("Successfully signed in!");
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping(SIGN_UP_PATH)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            logger.error("Error: Username is already in use");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already in use!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            logger.error("Error: Email is already in use");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword())
        );

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equalsIgnoreCase(ADMIN_ROLE_TYPE)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        logger.info("User registered successfully!");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping(RESET_PATH)
    public ResponseEntity<?> resetPassword(@RequestBody PassResetRequest resetRequest) {
        User existingUser;
        try {
            existingUser = userRepository.findByUsername(resetRequest.getUsername()).orElse(null);
        } catch (DataAccessException dae) {
            throw new ServerError(dae.getMessage());
        }

        if (existingUser == null) throw new ResourceNotFound(
                "Error: User with username '" + resetRequest.getUsername() + "' not found");

        String errors = "";
        if (!Objects.equals(resetRequest.getCurrPassword(), existingUser.getPassword())) {
            errors += "Current password is incorrect. ";
        } else {
            if (!Objects.equals(resetRequest.getNewPassword(), resetRequest.getRepeatPassword())) {
                errors += "Passwords don't match. ";
            }

            if (!errors.isBlank()) throw new Invalid(errors);
            existingUser.setPassword(resetRequest.getNewPassword());
        }

        return ResponseEntity.ok(new MessageResponse("Password successfully updated"));
    }
}

