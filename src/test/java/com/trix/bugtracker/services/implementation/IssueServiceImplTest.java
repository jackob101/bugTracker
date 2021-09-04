package com.trix.bugtracker.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;



import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.trix.bugtracker.DTO.IssueDTO;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.model.enums.Priority;
import com.trix.bugtracker.repository.IssueRepository;
import com.trix.bugtracker.services.interfaces.ProjectService;
import com.trix.bugtracker.services.interfaces.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {


    @Mock
    IssueRepository issueRepository;


    @Mock
    UserService userService;

    @Mock
    ProjectService projectService;

    @InjectMocks
    IssueServiceImpl issueService;


    Issue issue;


    @BeforeEach
    void setUp() {
        issue = Issue.builder()
                .id(1L)
                .title("title")
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
        assertThrows(IllegalArgumentException.class, () -> issueService.saveAll(issues));
        assertThrows(IllegalArgumentException.class, () -> issueService.saveAll(null));
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
        assertFalse(issueService.delete((Issue) null));
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

    @Test
    void update() {
        //given
        List<User> users = List.of(
                new User(1L, "UserName1", "UserLastName1", "Email1@gmail.com", 21),
                new User(2L, "UserName2", "UserLastName2", "Email2@gmail.com", 22),
                new User(3L, "UserName3", "UserLastName3", "Email3@gmail.com", 23),
                new User(4L, "UserName4", "UserLastName4", "Email4@gmail.com", 24),
                new User(5L, "UserName5", "UserLastName5", "Email5@gmail.com", 25)
        );

        IssueDTO issueDTO = new IssueDTO(1L, "Example issue", "Example description", false, null, List.of(1L, 2L, 3L, 4L, 5L));


        //when
        when(userService.findAllByIds(anyList())).thenReturn(users);
        when(issueRepository.findById(anyLong())).thenReturn(Optional.ofNullable(issue));
        when(issueRepository.save(Mockito.any(Issue.class))).then(AdditionalAnswers.returnsFirstArg());

        Issue update = issueService.update(issueDTO);

        //then
        assertEquals(issueDTO.getTitle(), update.getTitle());
        assertEquals(issueDTO.getDescription(), update.getDescription());
        assertNull(update.getClosedTime());
        assertEquals(Priority.IMPORTANT, update.getPriority());
        assertEquals(users.size(), update.getUsers().size());
    }

    @Test
    void testMethods(){
		ExampleClass exampleClass = new ExampleClass();
		exampleClass.setExampleName("THis is example name");
		exampleClass.setExampleSecondName("This is example second name");
		System.out.println(exampleClass);
    }
}
