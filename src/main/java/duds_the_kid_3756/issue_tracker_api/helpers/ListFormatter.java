package duds_the_kid_3756.issue_tracker_api.helpers;

import java.util.List;

public class ListFormatter {

    public static String Formatter(List<String> list) {
        var lastItem = list.get(list.size() - 1);
        list.add(list.indexOf(lastItem), "or");
        var joined = String.join(", ", list);
        return joined.replace("or,", "or");
    }
}
