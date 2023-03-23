package duds_the_kid_3756.issue_tracker_api.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "reminder")
public class Reminder {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "reminder_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    public Long id;

    public LocalTime time;

    public LocalDate date;

    public String alert;

    public Reminder() {
    }

    public Reminder(Long id, LocalTime time, LocalDate date, String alert) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.alert = alert;
    }

    public Reminder(LocalTime time, LocalDate date, String alert) {
        this.time = time;
        this.date = date;
        this.alert = alert;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reminder reminder)) return false;
        return Objects.equals(getId(), reminder.getId()) && Objects.equals(getTime(), reminder.getTime()) && Objects.equals(getDate(), reminder.getDate()) && Objects.equals(getAlert(), reminder.getAlert());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTime(), getDate(), getAlert());
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", time=" + time +
                ", date=" + date +
                ", alert='" + alert + '\'' +
                '}';
    }
}
