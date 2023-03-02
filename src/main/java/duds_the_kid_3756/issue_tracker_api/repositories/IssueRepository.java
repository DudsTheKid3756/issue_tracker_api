package duds_the_kid_3756.issue_tracker_api.repositories;

import duds_the_kid_3756.issue_tracker_api.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query(value = "SELECT * FROM issue i WHERE i.created_by = :username", nativeQuery = true)
    List<Issue> findByUsername(@Param("username") String username);
}
