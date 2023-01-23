package duds_the_kid_3756.issue_tracker_api.domains.issue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static duds_the_kid_3756.issue_tracker_api.constants.Paths.BASE_PATH;

@RestController
@RequestMapping(value = BASE_PATH)
public class IssueController {

    private final IssueService issueService;
    private final Logger logger = LogManager.getLogger(IssueController.class);

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getIssues() {
        logger.info("Request received for getIssues");
        return new ResponseEntity<>(issueService.getIssues(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        logger.info(String.format("Request received for getIssueById %s", id));
        return new ResponseEntity<>(issueService.getIssueById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Issue> addIssue(@RequestBody Issue issue) {
        logger.info("Request received for addIssue");
        return new ResponseEntity<>(issueService.addIssue(issue), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@RequestBody Issue issue, @PathVariable Long id) {
        logger.info(String.format("Request received for updateIssue %s", id));
        return new ResponseEntity<>(issueService.updateIssue(issue, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Issue> deleteIssue(@PathVariable Long id) {
        logger.info(String.format("Request received for deleteIssue %s", id));
        issueService.deleteIssue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
