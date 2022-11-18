package duds_the_kid_3756.issue_tracker_api.helpers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Formatters {

    public static String ListFormatter(List<String> list) {
        var lastItem = list.get(list.size() - 1);
        list.add(list.indexOf(lastItem), "or");
        var joined = String.join(", ", list);
        return joined.replace("or,", "or");
    }

    public static String DateFormatter(LocalDate date, LocalTime time) {
        var isoDate = date.format(DateTimeFormatter.ISO_DATE);
        var isoTime = time.format(DateTimeFormatter.ISO_TIME);

        var dateParts = isoDate.split("-");
        var year = dateParts[0];
        var month = dateParts[1];
        var day = dateParts[2];

        return String.format("%s/%s/%s %s", month, day, year, isoTime.substring(0, 8));
    }
}
