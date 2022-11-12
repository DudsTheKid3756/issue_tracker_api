package duds_the_kid_3756.issue_tracker_api.helpers.mappers;

import duds_the_kid_3756.issue_tracker_api.domains.reminder.Reminder;
import duds_the_kid_3756.issue_tracker_api.dtos.ReminderDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
class ReminderMapper {

    public ReminderDto toDto(Reminder reminder) {
        LocalTime time = reminder.getTime();
        LocalDate date = reminder.getDate();
        String alert = reminder.getAlert();

        return new ReminderDto(time, date, alert);
    }

    public Reminder toDomain() {
        return new Reminder();
    }
}
