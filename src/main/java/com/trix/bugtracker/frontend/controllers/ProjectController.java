package com.trix.bugtracker.frontend.controllers;

import com.trix.bugtracker.frontend.exceptions.ProjectNotFoundException;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.services.interfaces.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "project", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ProjectController {


    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping(path = "{id}")
    public Project findById(@PathVariable(value = "id")Long id){

        Project issueById = projectService.findById(id);

        if (issueById == null)
            throw new ProjectNotFoundException(id);

        return issueById;
    }


    @GetMapping(path = "all")
    public List<Project> findAll(){
        return projectService.findAll();
    }


    @PostMapping(path = "new")
    public Project createIssue(@RequestBody @Valid Project project, HttpServletResponse httpResponse){

        Project saved = projectService.save(project);

        if(saved!=null)
            httpResponse.setStatus(HttpServletResponse.SC_CREATED);

        return saved;
    }

}
