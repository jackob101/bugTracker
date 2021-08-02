package com.trix.bugtracker.frontend.controllers;

import com.trix.bugtracker.frontend.exceptions.IssueNotFoundException;
import com.trix.bugtracker.model.Issue;
import com.trix.bugtracker.services.interfaces.IssueService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "issue", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }


    @GetMapping(path = "{id}")
    public Issue findById(@PathVariable(value = "id")Long id){

        Issue issueById = issueService.findById(id);

        if (issueById == null)
            throw new IssueNotFoundException(id);

        return issueById;
    }


    @GetMapping(path = "all")
    public List<Issue> findAll(){
        return issueService.findAll();
    }


    @PostMapping(path = "new")
    public Issue createIssue(@RequestBody @Valid Issue issue, HttpServletResponse httpResponse){

        Issue saved = issueService.save(issue);

        if(saved!=null)
            httpResponse.setStatus(HttpServletResponse.SC_CREATED);

        return saved;
    }


}
