package duds_the_kid_3756.issue_tracker_api.constants;

import duds_the_kid_3756.issue_tracker_api.models.Reminder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.PLACEHOLDER;

public class ObjectsAndLists {

    public static final Reminder PLACEHOLDER_REMINDER =
            new Reminder(
                    -1L,
                    new Time(0L).toLocalTime(),
                    new Date(1600 - 1 - 1).toLocalDate(),
                    PLACEHOLDER);

    public static final List<String> ALERT_OPTIONS =
            new ArrayList<>(
                    Arrays.asList(
                            "No alert",
                            "On time",
                            "Half hour",
                            "1 hour",
                            "2 hours",
                            "1 day"
                    )
            );
}
