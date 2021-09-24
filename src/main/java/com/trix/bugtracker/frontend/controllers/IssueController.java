package com.trix.bugtracker.frontend.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.trix.bugtracker.DTO.IssueDTO;
import com.trix.bugtracker.DTO.IssuePojo;
import com.trix.bugtracker.DTO.ProjectIssues;
import com.trix.bugtracker.converters.ProjectConverter;
import com.trix.bugtracker.frontend.exceptions.IssueNotFoundException;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.model.enums.Priority;
import com.trix.bugtracker.services.interfaces.IssueService;
import com.trix.bugtracker.services.interfaces.ProjectService;
import com.trix.bugtracker.services.interfaces.UserService;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ProjectIssues findByProjectId(@RequestParam("id") Long id) {

        Project byId = projectService.findById(id);
        return ProjectConverter.toProjectIssues(byId);

    }

    @PostMapping(path = "update")
    public Issue updateIssue(@RequestBody @Valid IssueDTO issueDto) {

        return issueService.update(issueDto);
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

    @DeleteMapping(path = "delete")
    public Boolean deleteIssue(@RequestParam("id") Long id) {
        return issueService.delete(id);
    }

    @PostMapping(path = "close")
    public Issue closeIssue(@RequestParam("id") Long id) {
        return issueService.switchIssueClosedStatus(id);
    }

    @PostMapping(path = "assign")
    public Issue assignUserToIssue(@RequestParam("issueId") Long issueId, @RequestParam("userId") Long userId) {
        return issueService.assignUser(issueId, userId);
    }

    @PostMapping(path = "unassign")
    public Issue unAssignUser(@RequestParam("issueId") Long issueId, @RequestParam("userId") Long userId) {
        return issueService.unAssignUser(issueId, userId);
    }

    @PostMapping(path = "edit/priority")
    public Issue changePriority(@RequestParam("issueId") Long issueId, @RequestParam("priority") Priority priority){
	return issueService.changePriority(issueId, priority);
    }

}
