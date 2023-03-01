package duds_the_kid_3756.issue_tracker_api;

import duds_the_kid_3756.issue_tracker_api.exceptions.ServerError;
import duds_the_kid_3756.issue_tracker_api.models.ERole;
import duds_the_kid_3756.issue_tracker_api.models.Role;
import duds_the_kid_3756.issue_tracker_api.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IssueTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadRoles(RoleRepository roleRepository) {
        return (args) -> {
            List<Role> existingRoles = new ArrayList<>();
            try {
                existingRoles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElse(null));
                existingRoles.add(roleRepository.findByName(ERole.ROLE_USER).orElse(null));
            } catch (DataAccessException dae) {
                throw new ServerError(dae.getMessage());
            }

            if (existingRoles.contains(null)) {
                roleRepository.save(new Role(ERole.ROLE_ADMIN));
                roleRepository.save(new Role(ERole.ROLE_USER));
            }
        };
    }

}
