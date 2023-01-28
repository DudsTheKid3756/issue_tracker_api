package duds_the_kid_3756.issue_tracker_api.services;

import duds_the_kid_3756.issue_tracker_api.models.Issue;

import java.util.List;

public interface IssueService {

    List<Issue> getIssues();

    Issue getIssueById(Long id);

    Issue addIssue(Issue issue);

    Issue updateIssue(Issue issue, Long id);

    void deleteIssue(Long id);
}
