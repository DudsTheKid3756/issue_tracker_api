package duds_the_kid_3756.issue_tracker_api.exceptions;

public class ServerError extends RuntimeException {

    public ServerError() {
    }

    public ServerError(String message) {
        super(message);
    }
}
