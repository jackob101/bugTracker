package com.trix.bugtracker.converters;


import com.trix.bugtracker.DTO.ProjectDTO;
import com.trix.bugtracker.DTO.ProjectIssues;
import com.trix.bugtracker.model.Project.Project;

public class ProjectConverter {

    public static ProjectIssues toProjectIssues(Project project) {

        ProjectIssues projectIssues = new ProjectIssues();
        projectIssues.setIssues(project.getIssues());
        projectIssues.setProjectName(project.getName());
        projectIssues.setId(project.getId());
        return projectIssues;
    }

    public static Project toModel(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setDescription(projectDTO.getDescription());
        project.setName(projectDTO.getName());
        return project;
    }
}
