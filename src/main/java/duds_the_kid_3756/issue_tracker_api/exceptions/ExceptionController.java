package duds_the_kid_3756.issue_tracker_api.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;

import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.NOT_FOUND;
import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.SERVER_ERROR;

@ControllerAdvice
public class ExceptionController {
    private final Logger logger = LogManager.getLogger();

    @ExceptionHandler(ResourceNotFound.class)
    protected ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFound exception) {
        ExceptionResponse response =
                new ExceptionResponse(NOT_FOUND, new Date(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerError.class)
    protected ResponseEntity<ExceptionResponse> serverError(ServerError exception) {
        ExceptionResponse response =
                new ExceptionResponse(SERVER_ERROR, new Date(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String parseMessage(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        StringBuilder message = new StringBuilder();
        for (FieldError err : errors) {
            message.append(err.getDefaultMessage());
            message.append(" ");
        }

        return message.toString().trim();
    }
}
