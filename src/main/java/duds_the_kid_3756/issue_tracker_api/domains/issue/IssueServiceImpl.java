package duds_the_kid_3756.issue_tracker_api.domains.issue;

import duds_the_kid_3756.issue_tracker_api.exceptions.Invalid;
import duds_the_kid_3756.issue_tracker_api.exceptions.ResourceNotFound;
import duds_the_kid_3756.issue_tracker_api.exceptions.ServerError;
import duds_the_kid_3756.issue_tracker_api.helpers.ListFormatter;
import duds_the_kid_3756.issue_tracker_api.helpers.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static duds_the_kid_3756.issue_tracker_api.constants.ObjectsAndLists.ALERT_OPTIONS;
import static duds_the_kid_3756.issue_tracker_api.constants.ObjectsAndLists.PLACEHOLDER_REMINDER;
import static duds_the_kid_3756.issue_tracker_api.constants.StringConstants.*;

@Service
public class IssueServiceImpl implements IssueService {

    private final Logger logger = LogManager.getLogger(IssueServiceImpl.class);

    @Autowired
    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public List<Issue> getIssues() {
        try {
            logger.info("Got all issues");
            return issueRepository.findAll().stream().toList();
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }
    }

    @Override
    public Issue getIssueById(Long id) {
        Issue issue;
        try {
            issue = issueRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }

        if (issue == null) {
            logger.error(String.format("Issue%s", String.format(DOES_NOT_EXIST, id)));
            throw new ResourceNotFound(String.format("Issue%s", String.format(DOES_NOT_EXIST, id)));
        }

        logger.info("Got issue with id " + id);
        return issue;
    }

    @Override
    public Issue addIssue(Issue issue) {
        var reminder = issue.getReminder();
        issue.setCreated(PLACEHOLDER);
        var message = "";
        var errors = "";

        if (issue.isHasReminder()) {
            if (reminder == null) {
                errors += String.format("Reminder%s", NULL);
                logger.error(errors);
                throw new Invalid(errors);
            }

            var formattedOptions = ListFormatter.Formatter(ALERT_OPTIONS);
            if (!ALERT_OPTIONS.contains(reminder.getAlert())) {
                errors += String.format("Alert%sTry%s", INVALID, formattedOptions);
            }
        } else {
            reminder = null;
            message = "Issue was added without set Reminder as 'hasReminder' was left 'false'. Update Issue to add Reminder";
        }

        issue.setReminder(PLACEHOLDER_REMINDER);
        issue.setColor(issue.getColor().substring(1));

        try {
            errors += Validation.getErrors(issue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }

        issue.setColor(String.format("#%s", issue.getColor()));
        errors += Validation.checkHexCode(issue.getColor());

        if (errors.length() != 0) {
            logger.error(errors);
            throw new Invalid(errors);
        }

        issue.setReminder(reminder);
        issue.setCreated(
                LocalDateTime.now()
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        .substring(0, 16)
                        .replace("T", " ")
        );

        try {
            logger.info(message.isBlank() ? "New issue added" : message);
            return issueRepository.save(issue);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }
    }

    @Override
    public Issue updateIssue(Issue issue, Long id) {
        var reminder = issue.getReminder();
        Issue existingIssue;
        try {
            existingIssue = issueRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }

        if (existingIssue == null) {
            logger.error(String.format("Issue%s", String.format(DOES_NOT_EXIST, id)));
            throw new ResourceNotFound(String.format("Issue%s", String.format(DOES_NOT_EXIST, id)));
        }

        issue.setCreated(PLACEHOLDER);
        var errors = "";
        var message = "";

        if (issue.isHasReminder()) {
            if (reminder == null) {
                errors += String.format("Reminder%s", NULL);
                logger.error(errors);
                throw new Invalid(errors);
            }

            var formattedOptions = ListFormatter.Formatter(ALERT_OPTIONS);
            if (!ALERT_OPTIONS.contains(reminder.getAlert())) {
                errors += String.format("Alert%sTry%s", INVALID, formattedOptions);
            }
        } else {
            message = "'hasReminder' field was false. Reminder was set to null";
        }

        issue.setReminder(PLACEHOLDER_REMINDER);
        issue.setColor(issue.getColor().substring(1));

        try {
            errors += Validation.getErrors(issue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        issue.setColor(String.format("#%s", issue.getColor()));
        errors += Validation.checkHexCode(issue.getColor());

        if (errors.length() != 0) {
            logger.error(errors);
            throw new Invalid(errors);
        }

        issue.setCreated(existingIssue.getCreated());

        if (issue.getId() == null) {
            issue.setId(id);
        }

        issue.setReminder(issue.isHasReminder() ? reminder : null);
        if (issue.getReminder() != null){
            issue.getReminder().setId(existingIssue.getReminder().getId());
        }

        try {
            logger.info(String.format("Issue with id %s updated. %s", id, message));
            return issueRepository.save(issue);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }
    }

    @Override
    public void deleteIssue(Long id) {
        try {
            issueRepository.deleteById(id);
            logger.info(String.format("Issue with id %s deleted", id));
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }
    }
}
