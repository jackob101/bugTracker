package com.trix.bugtracker.services.interfaces;

import com.trix.bugtracker.DTO.IssueDTO;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.enums.Priority;

import java.util.List;

public interface IssueService extends CRUDService<Issue> {

    List<Issue> findIssuesByProjectId(Long id);

    Issue update(IssueDTO issueDTO);

    Issue switchIssueClosedStatus(Long id);

    Issue assignUser(Long issueId, Long userId);

    Issue unAssignUser(Long issueId, Long userId);

    Issue changePriority(Long issueId, Priority priority);
}
