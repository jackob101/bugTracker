package com.trix.bugtracker.converters;

import com.trix.bugtracker.DTO.ProjectIssues;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProjectConverterTest {

    @Test
    void toDTO() {
        //given
        Project project = Project.builder()
                .id(1L)
                .name("Test project")
                .issues(List.of(Issue.builder()
                        .description("Test Issue")
                        .id(1L)
                        .title("Title")
                        .build()))
                .build();

        //when
        ProjectIssues projectIssues = ProjectConverter.toProjectIssues(project);

        //then
        assertEquals(project.getId(), projectIssues.getId());
        assertEquals(project.getName(), projectIssues.getProjectName());
        assertEquals(project.getIssues().size(), projectIssues.getIssues().size());
    }
}