package duds_the_kid_3756.issue_tracker_api.dtos;

public class IssueDto {

    private String title;

    private String comment;

    private String created;

    private String color;

    private boolean hasReminder;

    private boolean isCompleted;

    private ReminderDto reminderDto;

    public IssueDto(String title, String comment, String created, String color, boolean hasReminder, boolean isCompleted, ReminderDto reminderDto) {
        this.title = title;
        this.comment = comment;
        this.created = created;
        this.color = color;
        this.hasReminder = hasReminder;
        this.isCompleted = isCompleted;
        this.reminderDto = reminderDto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public ReminderDto getReminder() {
        return reminderDto;
    }

    public void setReminder(ReminderDto reminderDto) {
        this.reminderDto = reminderDto;
    }
}
