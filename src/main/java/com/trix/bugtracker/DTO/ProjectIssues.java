package com.trix.bugtracker.DTO;

import com.trix.bugtracker.model.Issue.Issue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectIssues {

    private Long id;
    private String projectName;
    private List<Issue> issues;
}
