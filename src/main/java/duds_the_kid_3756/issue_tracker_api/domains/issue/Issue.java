package duds_the_kid_3756.issue_tracker_api.domains.issue;

import duds_the_kid_3756.issue_tracker_api.domains.reminder.Reminder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "issue")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String comment;

    private String created;

    private String color = "#000000";

    private boolean isCompleted = false;

    private boolean hasReminder = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reminder_id", referencedColumnName = "id")
    private Reminder reminder;

    public Issue() {
    }

    public Issue(Long id, String title, String comment, String created, String color, boolean isCompleted, boolean hasReminder, Reminder reminder) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.created = created;
        this.color = color;
        this.isCompleted = isCompleted;
        this.hasReminder = hasReminder;
        this.reminder = reminder;
    }

    public Issue(String title, String comment, String created, String color, boolean isCompleted, boolean hasReminder, Reminder reminder) {
        this.title = title;
        this.comment = comment;
        this.created = created;
        this.color = color;
        this.isCompleted = isCompleted;
        this.hasReminder = hasReminder;
        this.reminder = reminder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue issue)) return false;
        return isCompleted == issue.isCompleted && isHasReminder() == issue.isHasReminder() && getId().equals(issue.getId()) && getTitle().equals(issue.getTitle()) && getComment().equals(issue.getComment()) && Objects.equals(getCreated(), issue.getCreated()) && Objects.equals(getColor(), issue.getColor()) && Objects.equals(getReminder(), issue.getReminder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getComment(), getCreated(), getColor(), isCompleted, isHasReminder(), getReminder());
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", created='" + created + '\'' +
                ", color='" + color + '\'' +
                ", isCompleted=" + isCompleted +
                ", hasReminder=" + hasReminder +
                ", reminder=" + reminder +
                '}';
    }
}
