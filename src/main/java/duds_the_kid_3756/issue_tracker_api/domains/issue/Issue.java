package duds_the_kid_3756.issue_tracker_api.domains.issue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String comment;

    private String created;

    private boolean isCompleted;

    private boolean hasReminder;

    public Issue() {
    }

    public Issue(Long id, String title, String comment, String created, boolean isCompleted, boolean hasReminder) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.created = created;
        this.isCompleted = isCompleted;
        this.hasReminder = hasReminder;
    }

    public Issue(String title, String comment, String created, boolean isCompleted, boolean hasReminder) {
        this.title = title;
        this.comment = comment;
        this.created = created;
        this.isCompleted = isCompleted;
        this.hasReminder = hasReminder;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue issue)) return false;
        return isCompleted() == issue.isCompleted() && isHasReminder() == issue.isHasReminder() && getId().equals(issue.getId()) && getTitle().equals(issue.getTitle()) && getComment().equals(issue.getComment()) && getCreated().equals(issue.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getComment(), getCreated(), isCompleted(), isHasReminder());
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", created='" + created + '\'' +
                ", isCompleted=" + isCompleted +
                ", hasReminder=" + hasReminder +
                '}';
    }
}
