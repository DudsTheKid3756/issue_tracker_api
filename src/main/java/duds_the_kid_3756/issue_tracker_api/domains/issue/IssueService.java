package duds_the_kid_3756.issue_tracker_api.domains.issue;

import java.util.List;

public interface IssueService {

    List<Issue> getIssues();

    Issue getIssueById(Long id);

    Issue addIssue(Issue issue);
}
