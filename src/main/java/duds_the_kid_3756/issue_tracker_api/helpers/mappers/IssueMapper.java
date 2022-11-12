package duds_the_kid_3756.issue_tracker_api.helpers.mappers;

import duds_the_kid_3756.issue_tracker_api.domains.issue.Issue;
import duds_the_kid_3756.issue_tracker_api.dtos.IssueDto;
import duds_the_kid_3756.issue_tracker_api.dtos.ReminderDto;
import org.springframework.stereotype.Component;

@Component
public
class IssueMapper {
    private final ReminderMapper mapper;

    public IssueMapper(ReminderMapper mapper) {
        this.mapper = mapper;
    }

    public IssueDto toDto(Issue issue) {
        String title = issue.getTitle();
        String comment = issue.getComment();
        String created = issue.getCreated();
        String color = issue.getColor();
        boolean hasReminder = issue.isHasReminder();
        boolean isCompleted = issue.isIsCompleted();
        ReminderDto reminderDto = mapper.toDto(issue.getReminder());

        return new IssueDto(title, comment, created, color, hasReminder, isCompleted, reminderDto);
    }

    public Issue toDomain(IssueDto dto) {
        return new Issue(dto.getTitle(), dto.getComment(), dto.getCreated(), dto.getColor(), dto.isHasReminder(), dto.isCompleted(), mapper.toDomain());
    }
}
