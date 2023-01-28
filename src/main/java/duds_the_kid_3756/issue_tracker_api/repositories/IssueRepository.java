package duds_the_kid_3756.issue_tracker_api.repositories;

import duds_the_kid_3756.issue_tracker_api.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
