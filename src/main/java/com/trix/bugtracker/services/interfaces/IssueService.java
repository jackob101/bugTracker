package com.trix.bugtracker.services.interfaces;

import com.trix.bugtracker.model.Issue.Issue;

import java.util.List;

public interface IssueService extends CRUDService<Issue> {

    List<Issue> findIssuesByProjectId(Long id);

    Issue update(Issue issue, Long issueId);
}
