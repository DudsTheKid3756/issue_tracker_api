package duds_the_kid_3756.issue_tracker_api.repositories;

import duds_the_kid_3756.issue_tracker_api.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
