package duds_the_kid_3756.issue_tracker_api.constants;

public class StringConstants {
    // Validation
    public static final String NOT_FOUND = "404 Not Found";
    public static final String SERVER_ERROR = "500 An unexpected error occurred";
    public static final String BAD_REQUEST = "400 Bad request";
    public static final String DOES_NOT_EXIST = " with id %s does not exist in database";
    public static final String NULL = " field cannot be null. ";
    public static final String INVALID = " field is invalid. ";
    public static final String PLACEHOLDER = "Placeholder";
    public static final String ROLE_NOT_FOUND = "Error: Role is not found.";

    // Auth
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String ADMIN_ROLE_TYPE = "admin";
    public static final String DEVELOPER_ROLE_TYPE = "developer";
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
}
