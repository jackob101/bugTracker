package com.trix.bugtracker.frontend.controllers;

import com.trix.bugtracker.IssuePojo;
import com.trix.bugtracker.frontend.exceptions.IssueNotFoundException;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.services.interfaces.IssueService;
import com.trix.bugtracker.services.interfaces.ProjectService;
import com.trix.bugtracker.services.interfaces.UserService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping(path = "issue", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class IssueController {

    private final IssueService issueService;
    private final ProjectService projectService;
    private final UserService usersService;
    private final Validator validator;

    public IssueController(IssueService issueService, ProjectService projectService, UserService usersService, Validator validator) {
        this.issueService = issueService;
        this.projectService = projectService;
        this.usersService = usersService;
        this.validator = validator;
    }

    @GetMapping(path = "{id}")
    public Issue findById(@PathVariable(value = "id") Long id) {

        Issue issueById = issueService.findById(id);

        if (issueById == null)
            throw new IssueNotFoundException(id);

        return issueById;
    }


    @GetMapping(path = "all")
    public List<Issue> findAll() {
        return issueService.findAll();
    }

    @GetMapping(path = "project")
    public List<Issue> findByProjectId(@RequestParam("id") Long id) {
        return issueService.findIssuesByProjectId(id);
    }

    @PutMapping(path = "update")
    public Issue updateIssue(@RequestParam("id") Long issueId, @RequestBody Issue issue) {

        return issueService.update(issue, issueId);
    }

    @PostMapping(path = "new")
    public Issue createIssue(@RequestBody IssuePojo issuePojo, HttpServletResponse httpResponse, BindingResult bindingResult) {

        Issue issue = new Issue();
        issue.setDescription(issuePojo.getDescription());
        issue.setProject(projectService.findById(issuePojo.getProjectId()));
        issue.setCreatedBy(usersService.findById(issuePojo.getCreatedBy()));
        issue.setTitle(issuePojo.getTitle());

        validator.validate(issue, bindingResult);

        Issue saved = issueService.save(issue);

        if (saved != null)
            httpResponse.setStatus(HttpServletResponse.SC_CREATED);

        return saved;
    }


}
