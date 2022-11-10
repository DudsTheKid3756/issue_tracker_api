package duds_the_kid_3756.issue_tracker_api.helpers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static duds_the_kid_3756.issue_tracker_api.constants.Regex.ALPHANUMERIC;
import static duds_the_kid_3756.issue_tracker_api.constants.Regex.HEX_CODE;
import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.INVALID;
import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.NULL;

public class Validation {

    private static String validateField(String input, String field, List<String> nullFields) {
        var matched = input.matches(ALPHANUMERIC);
        if (nullFields.contains(field)) return "";
        if (!matched || input.startsWith(" ")) return field + INVALID;
        return "";
    }

    private static String validateEntity(Object o, List<String> nullFields) throws IllegalAccessException {
        StringBuilder errors = new StringBuilder();
        for (Field field : o.getClass().getFields()) {
            field.setAccessible(true);
            if (field.getType().getSimpleName().equals("String") && field.get(o) != null) {
                errors.append(validateField(field.get(o).toString(), field.getType().getSimpleName(), nullFields));
            }
        }

        return errors.toString();
    }

    public static String getErrors(Object o) throws IllegalAccessException {
        var nullFields = Arrays.stream(o.getClass().getFields()).filter((prop) -> {
            prop.setAccessible(true);
            try {
                if (prop.get(o) == null) return Boolean.parseBoolean(prop.getType().getSimpleName());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return false;
        }).toList();
        var nullErrors = String.join(", ", nullFields.stream().map(field -> field.toString() + NULL).toList());
        return validateEntity(o, nullFields.stream().map(Field::toString).toList()) + nullErrors;
    }

    public static String checkHexCode(String hexCode) {
        var match = hexCode.matches(HEX_CODE);
        if (!match || hexCode.startsWith(" ")) return "Color is not valid hex code. ";
        return "";
    }
}
