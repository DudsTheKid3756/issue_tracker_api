package duds_the_kid_3756.issue_tracker_api.repositories;

import duds_the_kid_3756.issue_tracker_api.models.ERole;
import duds_the_kid_3756.issue_tracker_api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
