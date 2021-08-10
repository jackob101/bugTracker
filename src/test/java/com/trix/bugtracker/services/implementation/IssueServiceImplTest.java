package com.trix.bugtracker.services.implementation;

import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.model.enums.Priority;
import com.trix.bugtracker.repository.IssueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {


    @Mock
    IssueRepository issueRepository;

    @InjectMocks
    IssueServiceImpl issueService;


    Issue issue;

    @BeforeEach
    void setUp() {
        issue = Issue.builder()
                .id(1L)
                .openedTime(LocalDateTime.now())
                .description("Random description")
                .priority(Priority.IMPORTANT)
                .build();
    }

    @Test
    void findById() {
        //given

        //when
        when(issueRepository.findById(anyLong())).thenReturn(Optional.ofNullable(issue));
        Issue issueFound = issueService.findById(1L);

        //then
        assertNotNull(issueFound);
        assertEquals(issue, issueFound);

    }

    @Test
    void findById_notFound() {
        //given
        //when

        //then
        assertNull(issueService.findById(1L));
    }

    @Test
    void findById_idNull() {
        //given
        //when
        //then
        assertNull(issueService.findById(null));
    }

    @Test
    void save() {
        //given
        Issue issue2 = new Issue();
        issue2.setDescription(issue.getDescription());
        issue2.setClosedTime(issue.getClosedTime());
        issue2.setPriority(issue.getPriority());
        issue2.setOpenedTime(issue.getOpenedTime());
        issue2.setId(1L);

        //when
        when(issueRepository.save(issue)).thenReturn(issue2);

        //then
        assertEquals(1L, issueService.save(issue).getId());
    }

    @Test
    void save_entityNull() {
        //given

        //when

        //then
        assertNull(issueService.save(null));
    }

    @Test
    void saveAll() {
        //given
        List<Issue> issues = Arrays.asList(Issue.builder()
                        .id(1L)
                        .openedTime(LocalDateTime.now())
                        .description("Random description 1")
                        .priority(Priority.IMPORTANT)
                        .build(),
                Issue.builder()
                        .id(2L)
                        .openedTime(LocalDateTime.now())
                        .description("Random description 2")
                        .priority(Priority.URGENT)
                        .build(),
                Issue.builder()
                        .id(3L)
                        .openedTime(LocalDateTime.now())
                        .description("Random description 3")
                        .priority(Priority.IMPORTANT)
                        .build()
        );

        //when
        when(issueRepository.saveAll(issues)).thenReturn(issues);

        //then
        assertEquals(3, issueService.saveAll(issues).size());
    }

    @Test
    void saveAll_null() {
        //given
        List<Issue> issues = Arrays.asList(Issue.builder()
                        .id(1L)
                        .openedTime(LocalDateTime.now())
                        .description("Random description 1")
                        .priority(Priority.IMPORTANT)
                        .build(),
                null,
                Issue.builder()
                        .id(2L)
                        .openedTime(LocalDateTime.now())
                        .description("Random description 2")
                        .priority(Priority.URGENT)
                        .build(),
                Issue.builder()
                        .id(3L)
                        .openedTime(LocalDateTime.now())
                        .description("Random description 3")
                        .priority(Priority.IMPORTANT)
                        .build()
        );

        //when

        //then
        assertThrows(IllegalArgumentException.class,() -> issueService.saveAll(issues));
        assertThrows(IllegalArgumentException.class,() -> issueService.saveAll(null));
    }

    @Test
    void delete_entity() {
        //given
        //when
        //then
        assertTrue(issueService.delete(issue));
    }

    @Test
    void delete_id() {
        //given
        //when
        //then
        assertTrue(issueService.delete(1L));
    }

    @Test
    void delete_fail_entity() {
        //given
        //when
        //then
        assertFalse(issueService.delete((Issue)null));
    }

    @Test
    void delete_fail_id() {
        //given
        //when
        //then
        assertFalse(issueService.delete((Long) null));
    }

    @Test
    void findByProjectId() {
        //given
        Long projectId = 1L;
        Project project = Project.builder()
                .name("Test project 1")
                .description("Test desc")
                .id(projectId)
                .build();

        List<Issue> issues = Arrays.asList(Issue.builder()
                .id(1L)
                .description("Test desc 1")
                .priority(Priority.IMPORTANT)
                .project(project)
                .build(), Issue.builder()
                .id(2L)
                .description("Test desc 2")
                .priority(Priority.IMPORTANT)
                .project(project)
                .build(), Issue.builder()
                .id(3L)
                .description("Test desc 3")
                .priority(Priority.IMPORTANT)
                .project(project)
                .build(), Issue.builder()
                .id(4L)
                .description("Test desc 4")
                .priority(Priority.IMPORTANT)
                .project(project)
                .build());

        project.setIssues(issues);

        //when
        when(issueRepository.findByProjectId(anyLong())).thenReturn(issues);

        //then
        assertEquals(issues.size(), issueService.findIssuesByProjectId(1L).size());
    }
}