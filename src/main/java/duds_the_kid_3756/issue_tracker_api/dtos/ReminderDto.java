package duds_the_kid_3756.issue_tracker_api.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReminderDto {

    private LocalTime time;

    private LocalDate date;

    private String alert;

    public ReminderDto(LocalTime time, LocalDate date, String alert) {
        this.time = time;
        this.date = date;
        this.alert = alert;
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
}
